package pt.ipp.estg.assistenteviagens

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.entity.AuthNavigationItems
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.screens.ForgotScreen
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.screens.LoginScreen
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.screens.MainScreen
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.screens.RegisterScreen
import pt.ipp.estg.assistenteviagens.ui.theme.AssistenteViagensTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistenteViagensTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AuthNavigation()
                    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                            return@OnCompleteListener
                        }
                        val token = task.result
                        val msg = getString(R.string.msg_token_fmt, token)
                        Log.d(TAG, msg)
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        onNewToken(token)
                    })
                }
            }
        }
    }
    fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
    }
}

@Composable
fun AuthNavigation() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        AuthNavigationScreens(navController = navController)
    }
}

@Composable
fun AuthNavigationScreens(navController: NavHostController) {
    NavHost(navController = navController, startDestination = AuthNavigationItems.Home.route) {
        composable(NavigationItems.Home.route) {
            MainScreen(navController)
        }
        composable(AuthNavigationItems.Login.route) {
            LoginScreen(navController)
        }
        composable(AuthNavigationItems.Register.route) {
            RegisterScreen(navController)
        }
        composable(AuthNavigationItems.Forgot.route) {
            ForgotScreen(navController)
        }
    }
}