package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel (application: Application): AndroidViewModel(application) {
    private val fAuth : FirebaseAuth
    val authState : MutableLiveData<AuthStatus>


    init {
        authState = MutableLiveData(AuthStatus.NOLOGGIN)
        fAuth = Firebase.auth
    }

    fun register(email:String, password:String){
        viewModelScope.launch {
            try{
                val result = fAuth.createUserWithEmailAndPassword(email, password).await()
                if (result != null && result.user != null){
                    authState.postValue(AuthStatus.LOGGED)
                    Log.d("Register","Logged in")
                    return@launch
                }
                Log.d("Register","Failed")
                authState.postValue(AuthStatus.NOLOGGIN)
                return@launch
            } catch( e:Exception) {}
        }
    }

    fun login(email:String, password:String){
        viewModelScope.launch {
            try{
                val result = fAuth.signInWithEmailAndPassword(email, password).await()
                if (result != null && result.user != null){
                    authState.postValue(AuthStatus.LOGGED)
                    Log.d("Login","logged in")
                    return@launch
                }
                Log.d("Login","anonymous")
                authState.postValue(AuthStatus.NOLOGGIN)
                return@launch
            } catch( e:Exception) {}
        }
    }

    fun logout(){
        viewModelScope.launch {
            fAuth.signOut()
            authState.postValue(AuthStatus.NOLOGGIN)
            Log.d("Logout","logout")
        }
    }

    enum class AuthStatus {
        LOGGED, NOLOGGIN
    }
}