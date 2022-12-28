package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.screens


import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pt.ipp.estg.assistenteviagens.Navigation
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.AuthNavigationItems
import pt.ipp.estg.assistenteviagens.room.userDatabase.UserViewModel
import pt.ipp.estg.assistenteviagens.room.userDatabase.entitys.User

@Composable
fun LoginScreen(navController: NavHostController) {
    val userViewModel: UserViewModel = viewModel()
    val users = userViewModel.readAllData.observeAsState()

    val mContext = LocalContext.current
    var inputEmail by remember { mutableStateOf("") }
    var inputPass by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }


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
                users.value?.forEach { user ->
                    if (inputEmail == user.email && inputPass == user.password) {
                        val intent = Intent(mContext, Navigation::class.java)
                        mContext.startActivity(intent)
                        userViewModel.insertUser(
                            User(user.email, user.fullName, user.password, true)
                        )
                    } //else {
                       // Toast.makeText(mContext, "Error in Email or Password", Toast.LENGTH_LONG)
                        //    .show()
                   // }
                }
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
            onClick = { /*TODO*/ }) {
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
    }
}