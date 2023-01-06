package pt.ipp.estg.assistenteviagens.maps


import android.content.ContentValues.TAG
import android.location.Location
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.maps.LocationUtils.currentLocation
import pt.ipp.estg.assistenteviagens.maps.LocationUtils.locationSource

@Composable
fun ScaffolMap() {
    if (currentLocation.value != null && LocationUtils.shouldShowMap.value) {
        MapScreen(
            newLocation = currentLocation.value!!)
    } else {
        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = currentLocation.value == null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .wrapContentSize()
            )
        }
    }
}

@Composable
fun MapScreen(newLocation: Location) {
    val loc = LatLng(newLocation.latitude, newLocation.longitude)
    locationSource.onLocationChanged(newLocation)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(loc, 14f)
    }
    var isMapLoaded by remember { mutableStateOf(false) }
    val uiSettings by remember { mutableStateOf(MapUiSettings()) }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL, isMyLocationEnabled = true))
    }
    LaunchedEffect(newLocation) {
        Log.d(TAG, "Updating blue dot on map...")
        locationSource.onLocationChanged(newLocation)
    }
    GoogleMap(
        properties = properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState,
        onMapLoaded = {isMapLoaded = true },
        locationSource = locationSource
    )
    var satelite by remember { mutableStateOf(true) }
    Box(
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .clip(RoundedCornerShape(percent = 15)),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick =  { properties = if (properties == properties.copy(mapType = MapType.NORMAL)) {
                satelite = false;
                properties.copy(mapType = MapType.SATELLITE)
            } else {
                satelite = true;
                properties.copy(mapType = MapType.NORMAL)
            }
            }
        ) {
            if(satelite){
                Box(){
                    Image(painter = painterResource(id = R.drawable.sateliteview), contentDescription = "SateliteIcon",
                        modifier = Modifier.width(70.dp).height(70.dp))
                    Text(text = "Satelite", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 4.dp))
                }
            }else{
                Box(){
                    Image(painter = painterResource(id = R.drawable.mapview), contentDescription = "SateliteIcon",
                        modifier = Modifier.width(70.dp).height(70.dp))
                    Text(text = "Mapa", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 4.dp))
                }
            }
        }
    }
}