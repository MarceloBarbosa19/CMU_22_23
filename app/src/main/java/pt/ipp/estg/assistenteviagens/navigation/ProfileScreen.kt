package pt.ipp.estg.assistenteviagens.navigation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.ipp.estg.assistenteviagens.R

@Preview
@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Ricardo Castro", fontSize = 50.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_description_24),
                contentDescription = "IconCar"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Small Description of the User",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_directions_car_24),
                contentDescription = "IconCar"
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Cars:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(200.dp))

        }
        Spacer(modifier = Modifier.height(1.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 80.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                    contentDescription = "IconArrow"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Renault Clio - Gasoleo",
                    fontSize = 15.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                    contentDescription = "IconArrow"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Kia - Gasolina", fontSize = 15.sp, modifier = Modifier.weight(1f))
            }
        }
    }
}



