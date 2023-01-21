package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.FirestoreUserViewModel

@Composable
fun ForgotScreen(navController: NavHostController) {
    val firestoreUserViewModel: FirestoreUserViewModel = viewModel()
    Column(
        modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "Reset Password",
            modifier = Modifier.height(40.dp).align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Enter your email to reset Password",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.width(10.dp))
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
                onClick = { firestoreUserViewModel.sendPasswordResetEmail() },
                modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally),
            ) {
                Text("Send reset link")

            }
        }
    }
}