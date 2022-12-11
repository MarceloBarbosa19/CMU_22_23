package pt.ipp.estg.assistenteviagens.navigation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import pt.ipp.estg.assistenteviagens.R

@Composable
fun SettingsScreen() {
    var inputName by remember { mutableStateOf("Marcelo Barbosa") }
    var inputEmail by remember { mutableStateOf("8180343@estg.ipp.pt") }
    var inputPasswordAntiga by remember { mutableStateOf("") }
    var inputPasswordNova by remember { mutableStateOf("") }
    var inputPasswordNova1 by remember { mutableStateOf("") }
    var showPasswordAntiga by remember { mutableStateOf(false) }
    var showPasswordNova by remember { mutableStateOf(false) }
    var showPasswordNova1 by remember { mutableStateOf(false) }
    var inputDescription by remember { mutableStateOf("") }
    var inputBrand by remember { mutableStateOf("") }
    var inputFuel by remember { mutableStateOf("") }
    var dialogOpen by remember { mutableStateOf(false) }
    var dialogOpenRemove by remember { mutableStateOf(false) }
    var dialogOpenPassword by remember { mutableStateOf(false) }
    var dialogOpenDelete by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
                .clip(RoundedCornerShape(percent = 10)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.ic_outline_account_circle_24),
            contentDescription = "AccountCircle",
        )
        OutlinedTextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(330.dp)
                .height(60.dp),
            label = { Text(text = "Full name") },
            value = inputName,
            onValueChange = { inputName = it },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_outline_account_circle_24),
                    contentDescription = "IconName"
                )
            })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(330.dp)
                .height(60.dp),
            label = { Text(text = "Description") },
            value = inputDescription,
            onValueChange = { inputDescription = it },
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_description_24),
                    contentDescription = "IconDescription"
                )
            })
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_directions_car_24),
                contentDescription = "IconCar"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Cars", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(200.dp))
            Image(
                modifier = Modifier.clickable { dialogOpen = true },
                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                contentDescription = "IconAdd"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp)
        )
        {
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
                Image(
                    modifier = Modifier.clickable { dialogOpenRemove = true },
                    painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                    contentDescription = "IconRemove"
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
                Image(
                    modifier = Modifier.clickable { dialogOpenRemove = true },
                    painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                    contentDescription = "IconRemove"
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(330.dp)
                    .height(60.dp),
                label = { Text(text = "Email") },
                value = inputEmail,
                onValueChange = { inputEmail = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "IconEmail"
                    )
                })
        }
        Spacer(modifier = Modifier.height(14.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Button(
                modifier = Modifier
                    .width(325.dp)
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(5.dp),
                onClick = { dialogOpenPassword = true }) {
                Text(text = "Change Password", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(14.dp))
            Button(
                modifier = Modifier
                    .width(325.dp)
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_red)),
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(5.dp),
                onClick = { dialogOpenDelete = true }) {
                Text(text = "Apagar a Conta", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
    if (dialogOpen) {
        Dialog(
            onDismissRequest = { dialogOpen = false },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(size = 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //dps fazer o retrofit para fazer uma lista de postos com o determinado combustivel mais barato, por agora vai assim
                    Row {
                        OutlinedTextField(
                            modifier = Modifier
                                .width(330.dp)
                                .height(60.dp),
                            label = { Text(text = "Brand") },
                            value = inputBrand,
                            onValueChange = { inputBrand = it }
                        )
                    }
                    Spacer(modifier = Modifier.size(5.dp))
                    Row {
                        OutlinedTextField(
                            modifier = Modifier
                                .width(330.dp)
                                .height(60.dp),
                            label = { Text(text = "Fuel") },
                            value = inputFuel,
                            onValueChange = { inputFuel = it }
                        )
                    }
                    Spacer(modifier = Modifier.size(15.dp))
                    Row() {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { dialogOpen = false }) {
                            Text(text = "ADD", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { dialogOpen = false }) {
                            Text(text = "Close", fontSize = 15.sp, fontWeight = FontWeight.Bold)

                        }
                    }
                }
            }
        }
    }
    if (dialogOpenRemove) {
        Dialog(
            onDismissRequest = { dialogOpenRemove = false },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(size = 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //dps fazer o retrofit para fazer uma lista de postos com o determinado combustivel mais barato, por agora vai assim
                    Text(text = "Are you sure you want delete this vehicle?", fontSize = 20.sp)
                    Spacer(modifier = Modifier.size(15.dp))
                    Row() {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { dialogOpenRemove = false }) {
                            Text(text = "Yes", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { dialogOpenRemove = false }) {
                            Text(text = "No", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
    if (dialogOpenPassword) {
        Dialog(
            onDismissRequest = { dialogOpenPassword = false },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(size = 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(330.dp)
                            .height(60.dp),
                        label = { Text(text = "Password Antiga") },
                        value = inputPasswordAntiga,
                        onValueChange = { inputPasswordAntiga = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "IconPassword"
                            )
                        },
                        visualTransformation = if (showPasswordAntiga) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        }, trailingIcon = {
                            if (showPasswordAntiga) {
                                IconButton(onClick = { showPasswordAntiga = false }) {
                                    Icon(
                                        imageVector = Icons.Filled.Visibility,
                                        contentDescription = "PasswordOn"
                                    )
                                }
                            } else {
                                IconButton(onClick = { showPasswordAntiga = true }) {
                                    Icon(
                                        imageVector = Icons.Filled.VisibilityOff,
                                        contentDescription = "PasswordOff"
                                    )
                                }
                            }
                        })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(330.dp)
                            .height(60.dp),
                        label = { Text(text = "Password Nova") },
                        value = inputPasswordNova,
                        onValueChange = { inputPasswordNova = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "IconPassword"
                            )
                        },
                        visualTransformation = if (showPasswordNova) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        }, trailingIcon = {
                            if (showPasswordNova) {
                                IconButton(onClick = { showPasswordNova = false }) {
                                    Icon(
                                        imageVector = Icons.Filled.Visibility,
                                        contentDescription = "PasswordOn"
                                    )
                                }
                            } else {
                                IconButton(onClick = { showPasswordNova = true }) {
                                    Icon(
                                        imageVector = Icons.Filled.VisibilityOff,
                                        contentDescription = "PasswordOff"
                                    )
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(330.dp)
                            .height(60.dp),
                        label = { Text(text = "Password Nova Repetição") },
                        value = inputPasswordNova1,
                        onValueChange = { inputPasswordNova1 = it },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "IconPassword"
                            )
                        },

                        visualTransformation = if (showPasswordNova1) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        }, trailingIcon = {
                            if (showPasswordNova1) {
                                IconButton(onClick = { showPasswordNova1 = false }) {
                                    Icon(
                                        imageVector = Icons.Filled.Visibility,
                                        contentDescription = "PasswordOn"
                                    )
                                }
                            } else {
                                IconButton(onClick = { showPasswordNova1 = true }) {
                                    Icon(
                                        imageVector = Icons.Filled.VisibilityOff,
                                        contentDescription = "PasswordOff"
                                    )
                                }
                            }
                        })
                    Spacer(modifier = Modifier.height(10.dp))
                    Row() {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { dialogOpenPassword = false }) {
                            Text(text = "Update", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { dialogOpenPassword = false }) {
                            Text(text = "Cancel", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

            }
        }
    }
    if (dialogOpenDelete) {
        Dialog(
            onDismissRequest = { dialogOpenDelete = false },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(size = 15.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //dps fazer o retrofit para fazer uma lista de postos com o determinado combustivel mais barato, por agora vai assim
                    Text(text = "Are you sure you want delete this account?", fontSize = 20.sp)
                    Spacer(modifier = Modifier.size(15.dp))
                    Row() {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_red)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { dialogOpenDelete = false }) {
                            Text(text = "Yes", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.white)),
                            border = BorderStroke(1.dp, Color.Black),
                            shape = RoundedCornerShape(5.dp),
                            onClick = { dialogOpenDelete = false }) {
                            Text(text = "No", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()

}
