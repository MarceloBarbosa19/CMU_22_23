package pt.ipp.estg.assistenteviagens.navigation

import android.content.Intent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import pt.ipp.estg.assistenteviagens.MainActivity
import pt.ipp.estg.assistenteviagens.R
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.FirestoreUserViewModel
import pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.viewModels.FirestoreCarViewModel
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType.GasTypeViewModel

@Composable
fun SettingsScreen() {
    val mContext = LocalContext.current

    val email = Firebase.auth.currentUser?.email!!
    val firestoreUserViewModel: FirestoreUserViewModel = viewModel()
    val userData = firestoreUserViewModel.getUserData(email).observeAsState()
    val firestoreCarsViewModel: FirestoreCarViewModel = viewModel()
    val carData = firestoreCarsViewModel.getCarData(email)

    val gasTypesViewModel: GasTypeViewModel = viewModel()
    val gasType = gasTypesViewModel.getAllGasTypes().observeAsState()

    userData.value?.let { user ->
        var inputName by remember { mutableStateOf(user.fullName) }
        var inputEmail by remember { mutableStateOf(user.email) }
        var inputDescription by remember { mutableStateOf(user.description) }
        var inputPasswordAntiga by remember { mutableStateOf("") }
        var inputPasswordNova by remember { mutableStateOf("") }
        var inputPasswordNova1 by remember { mutableStateOf("") }
        var showPasswordAntiga by remember { mutableStateOf(false) }
        var showPasswordNova by remember { mutableStateOf(false) }
        var showPasswordNova1 by remember { mutableStateOf(false) }
        var inputBrand by remember { mutableStateOf("") }
        var mSelectedTextTypeGas by remember { mutableStateOf("") }
        var mExpandedTypeGas by remember { mutableStateOf(false) }
        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
        var dialogOpen by remember { mutableStateOf(false) }
        var dialogOpenRemove by remember { mutableStateOf(false) }
        var dialogOpenPassword by remember { mutableStateOf(false) }
        var dialogOpenDelete by remember { mutableStateOf(false) }

        var emailUserCar by remember { mutableStateOf("") }
        var brandCar by remember { mutableStateOf("") }
        val cars by carData.observeAsState(initial = listOf())

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
            ) {
                cars?.forEach { car ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                            contentDescription = "IconArrow"
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "${car.brand} - ${car.fuel}",
                            fontSize = 15.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Image(
                            modifier = Modifier.clickable {
                                emailUserCar = car.emailUser
                                brandCar = car.brand
                                dialogOpenRemove = true
                            },
                            painter = painterResource(id = R.drawable.ic_baseline_remove_24),
                            contentDescription = "IconRemove"
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
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
                    onClick = {
                        firestoreUserViewModel.updateUserData(email, inputName, inputDescription)
                    }) {
                    Text(text = "Save Changes", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    modifier = Modifier
                        .width(325.dp)
                        .height(48.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.color_buttons)),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { dialogOpenPassword = true }) {
                    Text(
                        text = "Change Password",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
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
                    Text(
                        text = "Apagar a Conta",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
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
                        OutlinedTextField(
                            value = mSelectedTextTypeGas,
                            onValueChange = { mSelectedTextTypeGas = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    mTextFieldSize = coordinates.size.toSize()
                                },
                            placeholder = { Text("Selecione Tipo de Combustivel") },
                            trailingIcon = {
                                Icon(
                                    imageVector = if (mExpandedTypeGas) {
                                        Icons.Filled.KeyboardArrowUp
                                    } else {
                                        Icons.Filled.KeyboardArrowDown
                                    },
                                    contentDescription = "Icon Dropdown",
                                    modifier = Modifier.clickable {
                                        mExpandedTypeGas = !mExpandedTypeGas
                                    })
                            }
                        )
                        DropdownMenu(
                            expanded = mExpandedTypeGas,
                            onDismissRequest = { mExpandedTypeGas = false },
                            modifier = Modifier.width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                        ) {
                            gasType.value?.forEach { item ->
                                DropdownMenuItem(onClick = {
                                    mSelectedTextTypeGas = item.Descritivo
                                    mExpandedTypeGas = false
                                }) {
                                    Text(text = item.Descritivo)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(15.dp))
                        Row() {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(
                                        id = R.color.color_buttons
                                    )
                                ),
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(5.dp),
                                onClick = {
                                    firestoreCarsViewModel.addCarsToFirestore(
                                        mContext,
                                        inputBrand,
                                        email,
                                        mSelectedTextTypeGas
                                    )
                                    dialogOpen = false
                                }) {
                                Text(text = "ADD", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(
                                        id = R.color.white
                                    )
                                ),
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
                        Text(text = "Are you sure you want delete this vehicle?", fontSize = 20.sp)
                        Spacer(modifier = Modifier.size(15.dp))
                        Row() {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(
                                        id = R.color.color_buttons
                                    )
                                ),
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(5.dp),
                                onClick = {
                                    firestoreCarsViewModel.deleteCar(emailUserCar, brandCar)
                                    dialogOpenRemove = false
                                }) {
                                Text(text = "Yes", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(
                                        id = R.color.white
                                    )
                                ),
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
                            label = { Text(text = "Repita Password Nova") },
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
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(
                                        id = R.color.color_buttons
                                    )
                                ),
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(5.dp),
                                onClick = {
                                    /*TODO - CHANGE PASSWORD*/
                                }) {
                                Text(
                                    text = "Update",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(
                                        id = R.color.white
                                    )
                                ),
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(5.dp),
                                onClick = { dialogOpenPassword = false }) {
                                Text(
                                    text = "Cancel",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
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
                        Text(text = "Are you sure you want delete this account?", fontSize = 20.sp)
                        Spacer(modifier = Modifier.size(15.dp))
                        Row() {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(
                                        id = R.color.color_red
                                    )
                                ),
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(5.dp),
                                onClick = {
                                    val intent = Intent(mContext, MainActivity::class.java)
                                    mContext.startActivity(intent)
                                    firestoreUserViewModel.deleteAccount(email)
                                    dialogOpenDelete = false
                                }) {
                                Text(text = "Yes", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = colorResource(
                                        id = R.color.white
                                    )
                                ),
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
}


@Preview(showBackground = true)
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen()
}
