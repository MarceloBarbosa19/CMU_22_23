package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.FirestoreUserViewModel
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.entity.AuthNavigationItems
import pt.ipp.estg.assistenteviagens.ui.theme.AssistenteViagensTheme

@Composable
fun ForgotScreen(navController: NavHostController) {
    val firestoreUserViewModel: FirestoreUserViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(colorResource(id = R.color.color_background_Drawer)),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigate(AuthNavigationItems.Login.route) }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "ArrowBackIcon",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 5.dp, top = 5.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Forgot Password",
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
            text = "Reset Password",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.color_text_login),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = "Enter your email to reset Password",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.color_text_login),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(20.dp))
        OutlinedTextField(
            label = { Text(text = "Email Address") },
            value = firestoreUserViewModel.email.value,
            onValueChange = { firestoreUserViewModel.email.value = it },
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
        if (firestoreUserViewModel.isLoading.value) {
            CircularProgressIndicator()
        } else {
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .size(60.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    firestoreUserViewModel.sendPasswordResetEmail()
                    navController.navigate(AuthNavigationItems.Home.route)
                },
            ) {
                Text("Send reset link")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewForgotScreen() {
    AssistenteViagensTheme {
        val navController = rememberNavController()
        ForgotScreen(navController)
    }
}