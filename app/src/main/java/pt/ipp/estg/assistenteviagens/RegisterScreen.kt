package pt.ipp.estg.assistenteviagens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.ipp.estg.assistenteviagens.room.UserDatabase.UserViewModel
import pt.ipp.estg.assistenteviagens.room.UserDatabase.entity.User
import pt.ipp.estg.assistenteviagens.ui.theme.AssistenteViagensTheme

class RegisterScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistenteViagensTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Register()
                }
            }
        }
    }
}

@Composable
fun Register() {
    val userViewModel: UserViewModel = viewModel()
    val mContext = LocalContext.current

    var inputName by remember { mutableStateOf("") }
    var inputEmail by remember { mutableStateOf("") }
    var inputPass by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column {
        Row() {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "GoInitialScreen",
                tint = Color.Black,
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 5.dp)
                    .clickable {
                        val intent = Intent(mContext, MainActivity::class.java)
                        mContext.startActivity(intent)
                    }
            )
            Spacer(modifier = Modifier.width(120.dp))
            Text(
                text = "Register",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                label = { Text(text = "Full name") },
                value = inputName,
                onValueChange = { inputName = it },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "IconUser")
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .width(286.dp)
                    .size(60.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.size(15.dp))
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
                    userViewModel.insertUser(User(inputEmail, inputName, inputPass))

                    val intent = Intent(mContext, LoginScreen::class.java)
                    mContext.startActivity(intent)
                }) {
                Text(text = "LET'S GET STARTED", fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    AssistenteViagensTheme {
        Register()
    }
}