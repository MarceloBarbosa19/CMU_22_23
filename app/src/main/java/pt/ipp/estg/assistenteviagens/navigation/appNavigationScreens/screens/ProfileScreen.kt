package pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens


import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.SuggestScreen
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.FirestoreUserViewModel
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.viewModels.FirestoreCarViewModel
import pt.ipp.estg.assistenteviagens.ui.theme.AssistenteViagensTheme

@Preview
@Composable
fun ProfileScreen() {
    val email = Firebase.auth.currentUser?.email!!
    val firestoreUserViewModel: FirestoreUserViewModel = viewModel()
    val firestoreCarsViewModel: FirestoreCarViewModel = viewModel()
    val userData = firestoreUserViewModel.getUserData(email).observeAsState()
    val carData = firestoreCarsViewModel.getCarData(email)
    val cars by carData.observeAsState(initial = listOf())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        userData.value?.let { user->
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp)
                    .clip(RoundedCornerShape(percent = 10)),
                painter = painterResource(id = R.drawable.ic_outline_account_circle_24),
                contentDescription = "ProfileIcon",
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = user.fullName, fontSize = 50.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_description_24),
                    contentDescription = "IconCar"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = user.description,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_directions_car_24),
                    contentDescription = "IconCar"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Cars:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(200.dp))

            }
            Spacer(modifier = Modifier.height(1.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 80.dp)
            ) {
                Log.d(ContentValues.TAG, "carData.value: ${carData.value}")
                cars?.forEach { car ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                            contentDescription = "IconArrow"
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "${car.brand} - ${car.fuel}",
                            fontSize = 15.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    AssistenteViagensTheme {
        ProfileScreen()
    }
}