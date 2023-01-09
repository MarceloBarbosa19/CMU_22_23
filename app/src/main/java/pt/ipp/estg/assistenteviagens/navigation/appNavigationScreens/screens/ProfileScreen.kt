package pt.ipp.estg.assistenteviagens.navigation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType.GasTypeViewModel
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase.CarViewModel
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.userDatabase.UserViewModel

@Preview
@Composable
fun ProfileScreen() {
    val userViewModel: UserViewModel = viewModel()
    val users = userViewModel.readAllData.observeAsState()
    val carViewModel: CarViewModel = viewModel()
    val car = carViewModel.readAllData.observeAsState()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        users.value?.forEach { user ->
            if(user.isLogin){
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(200.dp)
                        .clip(RoundedCornerShape(percent = 10)),
                    painter = painterResource(id = R.drawable.ic_outline_account_circle_24),
                    contentDescription = "ProfileIcon",
                    contentScale = ContentScale.Fit,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = user.fullName, fontSize = 50.sp
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
                        text = user.description,
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
                    car.value?.forEach { car->
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
                                text = "${car.car_Brand} - ${car.car_Fuel}",
                                fontSize = 15.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}



