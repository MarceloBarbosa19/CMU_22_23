package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.screens


import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import pt.ipp.estg.assistenteviagens.Navigation
import pt.ipp.estg.assistenteviagens.R

import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.viewModels.AuthViewModel
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.FirestoreUserViewModel
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.entity.AuthNavigationItems
import pt.ipp.estg.assistenteviagens.ui.theme.AssistenteViagensTheme

@Composable
fun LoginScreen(navController: NavHostController) {
    val mContext = LocalContext.current

    var inputEmail by remember { mutableStateOf("") }
    var inputPass by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val authViewModel: AuthViewModel = viewModel()
    val authStatus = authViewModel.authState.observeAsState()
    val firestoreUserViewModel: FirestoreUserViewModel = viewModel()


    if (authStatus.value == AuthViewModel.AuthStatus.NOLOGGIN) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(colorResource(id = R.color.color_background_Drawer)),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.navigate(AuthNavigationItems.Home.route) }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "ArrowBackIcon",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 5.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AuthNavigationItems.Login.title,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome Back",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.color_text_login),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Please Login to continue",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.color_text_login),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(50.dp))
            OutlinedTextField(
                label = { Text(text = "Email Address") },
                value = inputEmail,
                onValueChange = { inputEmail = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "IconEmail"
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(286.dp)
                    .size(60.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.size(15.dp))
            OutlinedTextField(
                label = { Text(text = "Password ") },
                value = inputPass,
                onValueChange = { inputPass = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "IconPassword"
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(286.dp)
                    .size(60.dp)
                    .align(Alignment.CenterHorizontally),
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }, trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "PasswordOn"
                            )
                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "PasswordOff"
                            )
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.size(15.dp))
            Button(
                modifier = Modifier
                    .width(286.dp)
                    .size(60.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    firestoreUserViewModel.verifyEmail(
                        mContext,
                        inputEmail,
                        inputPass,
                        authViewModel
                    )
                }) {
                Text(text = "Login", fontSize = 25.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.size(30.dp))
            Text(
                text = "or",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.color_text_login),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                modifier = Modifier
                    .width(286.dp)
                    .size(60.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp),
                enabled = false,
                onClick = { /*TODO - LOGIN WITH GOOGLE*/ }) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.icon_google),
                        contentDescription = "GoogleIcon"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Login With Google",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            ClickableText(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = TextStyle(color = colorResource(id = R.color.color_buttons)),
                text = AnnotatedString("Forgot Password"),
                onClick = { navController.navigate(AuthNavigationItems.Forgot.route) }
            )
        }
    }
    if (authStatus.value != null && authStatus.value!! == AuthViewModel.AuthStatus.LOGGED) {
        val intent = Intent(mContext, Navigation::class.java)
        mContext.startActivity(intent)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    AssistenteViagensTheme {
        val navController = rememberNavController()
        LoginScreen(navController)
    }
}