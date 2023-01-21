package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.viewModels

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.entity.CarFirebase
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase.CarViewModel

class FirestoreCarViewModel(application: Application) : AndroidViewModel(application) {
    private val db: FirebaseFirestore = Firebase.firestore

    fun addCarsToFirestore(mContext: Context, brand: String, emailUser: String, fuel: String) {
        try {
            val carRef = db.collection("Cars").document()
            val car = hashMapOf(
                "brand" to brand,
                "emailUser" to emailUser,
                "fuel" to fuel
            )
            carRef.set(car).addOnSuccessListener {
                Toast.makeText(mContext, "Your car has been added to Firestore", Toast.LENGTH_SHORT)
                    .show()
                Log.d(ContentValues.TAG, "DocumentSnapshot ID: ${carRef.id}")
            }.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding doc", e)
            }
        } catch (e: Exception) {
        }
    }

    fun getCarData(emailUser: String): MutableLiveData<List<CarFirebase>?> {
        val carData = MutableLiveData<List<CarFirebase>?>()
        val carRef = db.collection("Cars").whereEqualTo("emailUser", emailUser)
        carRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val cars = mutableListOf<CarFirebase>()
                for (document in task.result!!) {
                    val car = document.toObject(CarFirebase::class.java)
                    cars.add(car)


                }
                carData.value = cars
                Log.d(TAG, "Cars retrieved from Firestore: $cars")
            } else {
                Log.e(TAG, "Error getting cars: ", task.exception)
            }
        }
        return carData
    }

    fun deleteCar(emailUser: String, brand: String) {
        val carRef =
            db.collection("Cars").whereEqualTo("emailUser", emailUser).whereEqualTo("brand", brand)
        carRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    document.reference.delete()
                        .addOnSuccessListener { Log.d(TAG, "Car deleted from Firestore") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error deleting car", e) }
                }
            }
        }
    }


}