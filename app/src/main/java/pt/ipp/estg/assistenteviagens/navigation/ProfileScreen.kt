package pt.ipp.estg.assistenteviagens.navigation


import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.ipp.estg.assistenteviagens.Navigation
import pt.ipp.estg.assistenteviagens.R


@Preview
@Composable
fun ProfileScreen() {
    val mContext = LocalContext.current
    var inputName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier
                .width(150.dp)
                .height(40.dp)
                .align(Alignment.End),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                val intent = Intent(mContext, Navigation::class.java)
                mContext.startActivity(intent)
            }
        ) {
            Image(
            painter = painterResource(id = R.drawable.ic_baseline_edit_24),
            contentDescription = "IconEdit"
        )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Editar Perfil", fontSize =10.sp)

        }
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
                .clip(RoundedCornerShape(percent = 10)),
            painter = painterResource(id = R.drawable.ic_outline_account_circle_24),
            contentDescription = "Profileicon",
            contentScale = ContentScale.Fit,
        )
        Text(
            modifier= Modifier.align(Alignment.CenterHorizontally),
            text= "Ricardo Castro", fontSize= 50.sp
        )
        Column(modifier = Modifier
            .padding(vertical= 20.dp, horizontal = 40.dp)
        ) {
            OutlinedTextField(
                label = { Text(text = "Bio") },
                value = inputName,
                onValueChange = { inputName = it },
                maxLines = 3,
                leadingIcon = {
                    Icon(Icons.Default.Newspaper, "IconName")
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier.
        padding(vertical= 10.dp, horizontal = 40.dp)
        ) {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_directions_car_24),
                    contentDescription = "IconCar"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Cars: ", fontSize = 20.sp)
            }
        }
        Column(modifier = Modifier.
        padding(vertical= 10.dp, horizontal = 60.dp)
        ) {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                    contentDescription = "IconCar"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Renault Clio - Gasóleo", fontSize = 15.sp)
            }
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                    contentDescription = "IconCar"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "KIA - Gasolina", fontSize = 15.sp)
            }
        }
    }
}


