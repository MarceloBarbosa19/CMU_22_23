package pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems

data class contact(
    val nome: String,
    val number: String,
    val email: String,
)

@Composable
fun ContactScreen() {
    val context = LocalContext.current
    val contacts = listOf(
        contact("Henrique Pereira", "914125248", "henrique@example.com"),
        contact("Marcelo Barbosa", "910231265", "marcelo@example.com"),
        contact("Leandro Soares", "914257896", "leandro@example.com")
    )
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text( modifier = Modifier
            .padding(all = 18.dp),
            text = "Contactos",fontSize = 35.sp, fontWeight = FontWeight.Bold)
        contacts.forEach { contact ->
            Surface(color = MaterialTheme.colors.background) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 5.dp),
                        text = contact.nome,
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                    Divider(color = Color.Gray)
                    Spacer(modifier = Modifier.height(7.dp))
                    Row() {
                        Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
                        Spacer(modifier = Modifier.width(16.dp).padding(top = 5.dp))
                        Text(
                            text = contact.number,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.clickable(onClick = {
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.setData(Uri.parse("tel:${contact.number}"))
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                                context.startActivity(intent)
                            })
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row() {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = contact.email,
                            style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.clickable(onClick = {
                                val intent = Intent(Intent.ACTION_SENDTO)
                                intent.data = Uri.parse("mailto:${contact.email}")
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                context.startActivity(intent)
                            })
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private operator fun NavigationItems.Contact.invoke(
    name: String,
    phone: String,
    email: String
): NavigationItems.Contact {
    return NavigationItems.Contact(name, phone, email)
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoriteScreen() {
    ContactScreen()
}