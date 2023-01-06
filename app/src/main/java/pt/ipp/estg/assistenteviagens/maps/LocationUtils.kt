package pt.ipp.estg.assistenteviagens.maps

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.LocationSource
import java.util.concurrent.TimeUnit

object LocationUtils {
    val locationSource = MyLocationSource()
    var currentLocation: MutableState<Location?> = mutableStateOf(null)
    var shouldShowMap: MutableState<Boolean> = mutableStateOf(false)
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    @SuppressLint("MissingPermission")
    fun setupFusedLocation(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                for (location in p0.locations) {
                    currentLocation.value = location
                    locationSource.onLocationChanged(location)
                }
            }
        }
        locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.SECONDS.toMillis(10)
            fastestInterval = TimeUnit.SECONDS.toMillis(5)
            maxWaitTime = TimeUnit.SECONDS.toMillis(10)
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )
    }

    @Composable
    fun RequestPerms(context: Context) {
        val requestPermissionLauncher =
            rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                ) {
                    Log.i("Location", "Permission granted")
                    setupFusedLocation(context)
                    shouldShowMap.value = true
                } else {
                    Log.i("Location", "Permission denied")
                }
            }
        when {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("Location", "Permission previously granted")
                shouldShowMap.value = true
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                Manifest.permission.CAMERA
            ) -> Log.i("Location", "Show camera permissions dialog")

            else -> SideEffect {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    class MyLocationSource : LocationSource {
        private var listener: LocationSource.OnLocationChangedListener? = null

        override fun activate(listener: LocationSource.OnLocationChangedListener) {
            this.listener = listener
        }
        override fun deactivate() {
            listener = null
        }
        fun onLocationChanged(location: Location) {
            listener?.onLocationChanged(location)
        }
    }
}