package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.viewModels.AuthViewModel

class FirestoreUserViewModel(application: Application) : AndroidViewModel(application) {
    private val db: FirebaseFirestore = Firebase.firestore
    private val fAuth: FirebaseAuth = Firebase.auth

    //register
    fun addUserToFirestore(
        mContext: Context,
        inputEmail: String,
        inputName: String,
        inputDescription: String
    ) {
        try {
            val email = inputEmail.trim()
            val userRef = db.collection("User").document(email)
            val user = hashMapOf(
                "email" to inputEmail,
                "fullName" to inputName,
                "description" to inputDescription,
            )
            userRef.set(user).addOnSuccessListener {
                Toast.makeText(
                    mContext, "Your user has been added to Firebase Firestore",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d(TAG, "DocumentSnapshot ID: $email")
            }.addOnFailureListener { e ->
                Log.w(TAG, "Error adding doc", e)
            }
        } catch (e: Exception) {
        }
    }

    //login
    fun verifyEmail(
        mContext: Context, inputEmail: String, inputPass: String, authViewModel: AuthViewModel
    ) {
        try {
            val userRef = db.collection("User").document(inputEmail)
            userRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document!!.exists()) {
                        val checkEmail = document.getString("email")
                        if (inputEmail == checkEmail) {
                            authViewModel.login(inputEmail, inputPass)
                            Toast.makeText(mContext, "Welcome back!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(
                                mContext,
                                "Email or password incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(mContext, "Email not registered", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
        } catch (e: Exception) {
        }
    }

    fun getUserData(email: String): MutableLiveData<UserFirebase> {
        val userData = MutableLiveData<UserFirebase>()
        val userRef = db.collection("User").document(email)
        userRef.get().addOnCompleteListener { task ->
            val document = task.result
            if (document!!.exists()) {
                val fullName = document.getString("fullName")!!
                val description = document.getString("description")!!
                val user = UserFirebase(email, fullName, description)
                userData.value = (user)
            } else {
                Log.e("UserViewModel", "Error getting user data: ", task.exception)
            }
        }
        return userData
    }

    fun updateUserData(email: String, fullName: String, description: String) {
        Log.e(TAG, email)
        val userRef = db.collection("User").document(email)
        userRef.update("fullName", fullName, "description", description)
            .addOnSuccessListener {
                Log.d(TAG, "User data successfully updated")
            }
            .addOnFailureListener {
                Log.e(TAG, "Error updating user data: ", it)
            }
    }

    fun deleteAccount(email: String) {
        // Delete user document
        db.collection("User").document(email).delete()
        // Delete cars documents
        db.collection("Cars").whereEqualTo("emailUser", email).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    document.reference.delete()
                }
            }
        }
        // Delete favorites documents
        db.collection("Favorites").whereEqualTo("emailUser", email).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        document.reference.delete()
                    }
                }
            }
        // Delete user from Firebase Authentication
        val user = Firebase.auth.currentUser
        user?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "User account deleted.")
            }
        }
    }







}