package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.viewModels

import android.app.Application
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.service.controls.ControlsProviderService
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.entity.CarFirebase
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.entity.FavFirestore

class FirestoreFavViewModel(application: Application) : AndroidViewModel(application) {
    private val db: FirebaseFirestore = Firebase.firestore

    fun addFavsToFirestore(mContext: Context, emailUser: String, name: String, idStation: Int) {
        try {
            val favRef = db.collection("Favorites").document()
            val favs = hashMapOf(
                "idStation" to idStation,
                "emailUser" to emailUser,
                "name" to name
            )
            favRef.set(favs).addOnSuccessListener {
                Toast.makeText(mContext, "Your fav has been added to Firestore", Toast.LENGTH_SHORT)
                    .show()
                Log.d(TAG, "DocumentSnapshot ID: ${favRef.id}")
            }.addOnFailureListener { e ->
                Log.w(TAG, "Error adding doc", e)
            }
        } catch (e: Exception) {
        }
    }

    fun getFavsData(email: String): MutableLiveData<List<FavFirestore>?> {
        val favData = MutableLiveData<List<FavFirestore>?>()
        val favRef = db.collection("Favorites").whereEqualTo("emailUser", email)
        favRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val favs = mutableListOf<FavFirestore>()
                for (document in task.result!!) {
                    val fav = document.toObject(FavFirestore::class.java)
                    favs.add(fav)
                }
                favData.value = favs
                Log.d(TAG, "Favs retrieved from Firestore: $favs")
            } else {
                Log.e(TAG, "Error getting favs: ", task.exception)
            }
        }
        return favData
    }

    fun deleteFav(emailUser: String, name: String) {
        val favRef =
            db.collection("Favorites").whereEqualTo("emailUser", emailUser)
                .whereEqualTo("name", name)
        favRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    document.reference.delete()
                        .addOnSuccessListener {
                            Log.d(TAG, "Fav deleted from Firestore")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error deleting fav", e)
                        }
                }
            }
        }
    }
}