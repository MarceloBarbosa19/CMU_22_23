package pt.ipp.estg.assistenteviagens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.navigation.*
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models.NavigationItems
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens.ContactScreen
import pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.screens.stations.*
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.userDatabase.UserViewModel
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.userDatabase.entitys.User
import pt.ipp.estg.assistenteviagens.navigation.utils.searchButton.SearchAppBar
import pt.ipp.estg.assistenteviagens.navigation.utils.searchButton.SearchViewModel
import pt.ipp.estg.assistenteviagens.navigation.utils.searchButton.SearchWidgetState
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.topStations.TopStationsViewModel
import pt.ipp.estg.assistenteviagens.ui.theme.AssistenteViagensTheme

class Navigation : ComponentActivity() {

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssistenteViagensTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationScreen(searchViewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationScreen(searchViewModel: SearchViewModel) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MainAppBar(
                scaffoldState = scaffoldState,
                scope = scope,
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = { searchViewModel.updateSearchTextStage(newValue = it) },
                onCloseClicked = { searchViewModel.updateSearchWidgetStage(newValue = SearchWidgetState.CLOSED) },
                onSearchClicked = { Log.d("SearchedText", it) },
                onSearchTriggered = { searchViewModel.updateSearchWidgetStage(newValue = SearchWidgetState.OPENED) },
                navController = navController
            )
        },
        drawerContent = {
            Drawer(
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        },
        drawerGesturesEnabled = false
    ) {
        NavigationScreens(navController = navController)
    }
}

@Composable
fun MainAppBar(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    navController: NavController,
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            TopBar(
                scope = scope,
                scaffoldState = scaffoldState,
                onSearchClicked = onSearchTriggered,
                navController = navController
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onSearchClicked: () -> Unit,
    navController: NavController
) {
    TopAppBar(
        title = { Text(text = "") },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { scaffoldState.drawerState.open() }
            }) {
                Icon(Icons.Filled.Menu, contentDescription = "")
            }
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search icon")
            }
            IconButton(
                onClick = { navController.navigate(NavigationItems.Profile.route) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_outline_account_circle_24),
                    contentDescription = "Icon Profile Account"
                )
            }
        },
        backgroundColor = colorResource(id = R.color.color_background_Drawer),
        contentColor = Color.Black
    )
}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavHostController) {
    val userViewModel: UserViewModel = viewModel()
    val users = userViewModel.readAllData.observeAsState()

    val items = listOf(
        NavigationItems.Home,
        NavigationItems.Favorites,
        NavigationItems.Suggest,
        NavigationItems.Settings,
        NavigationItems.Contact,
        NavigationItems.Logout,
    )
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(colorResource(id = R.color.color_background_Drawer))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(179.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            users.value?.forEach { user ->
                if (user.isLogin) {
                    Box {
                        IconButton(
                            onClick = { scope.launch { scaffoldState.drawerState.close() } },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "CloseIcon",
                            )
                        }
                        Column( modifier = Modifier.padding(top = 30.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_outline_account_circle_24),
                                contentDescription = "IconImage",
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Text(
                                text = user.fullName,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Divider(color = Color.Gray)
                        }
                    }
                }
            }
        }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRout = navBackStackEntry?.destination?.route
        items.forEach { item ->
            DrawerItem(item = item, selected = currentRout == item.route, onItemClick = {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch { scaffoldState.drawerState.close() }
            })
            Divider(color = Color.Gray)
        }
    }
}

@Composable
fun DrawerItem(item: NavigationItems, selected: Boolean, onItemClick: (NavigationItems) -> Unit) {
    val background = if (selected) R.color.color_text_login else android.R.color.transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(65.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = item.title, fontSize = 18.sp, color = Color.Black)
    }
}

@Composable
fun NavigationScreens(navController: NavHostController) {
    val mContext = LocalContext.current
    val userViewModel: UserViewModel = viewModel()
    val users = userViewModel.readAllData.observeAsState()

    NavHost(navController = navController, startDestination = NavigationItems.Home.route) {
        //Screens of Drawer
        composable(NavigationItems.Home.route) {
            HomeScreen()
        }
        composable(NavigationItems.Favorites.route) {
            FavoritesScreen()
        }
        composable(NavigationItems.Suggest.route) {
            SuggestScreen(navController)
        }
        composable(NavigationItems.Settings.route) {
            SettingsScreen()
        }
        composable(NavigationItems.Contact.route) {
            ContactScreen()
        }
        composable(NavigationItems.Logout.route) {
            Column {
                users.value?.forEach { user ->
                    if (user.isLogin) {
                        val intent = Intent(mContext, MainActivity::class.java)
                        mContext.startActivity(intent)
                        userViewModel.insertUser(
                            User(
                                user.email,
                                user.fullName,
                                user.description,
                                user.password,
                                false
                            )
                        )
                    }
                }
            }
        }
        //Screens of Profile
        composable(NavigationItems.Profile.route) {
            ProfileScreen()
        }
        //Screens of Gas Stations
        composable(NavigationItems.SearchStation.route) {
            SearchStations(navController)
        }
        composable(NavigationItems.TopStations.route) {
            TopStations(navController)
        }
        composable(
            NavigationItems.FoundStation.route + "?auxComb={auxComb}&auxMarca={auxMarca}&auxDist={auxDist}&auxMun={auxMun}",
            arguments = listOf(
                navArgument("auxComb") { type = NavType.IntType },
                navArgument("auxMarca") { type = NavType.IntType },
                navArgument("auxDist") { type = NavType.IntType },
                navArgument("auxMun") { type = NavType.IntType }),
        ) { backStackEntry ->
            val combID = backStackEntry.arguments?.getInt("auxComb") as Int
            val marcaID = backStackEntry.arguments?.getInt("auxMarca") as Int
            val distID = backStackEntry.arguments?.getInt("auxDist") as Int
            val munID = backStackEntry.arguments?.getInt("auxMun") as Int
            FoundStation(navController, combID, marcaID, distID, munID)
        }
        composable(
            NavigationItems.InfoStation.route + "?stationID={stationID}&stationNome={stationNome}",
            arguments = listOf(
                navArgument("stationID") { type = NavType.IntType })
        ) { backStackEntry ->
            val stID = backStackEntry.arguments?.getInt("stationID") as Int
            val stNome = backStackEntry.arguments?.getString("stationNome") as String
            InfoStation(navController, stID, stNome)
        }
        composable(NavigationItems.InfoStationByName.route + "?stationNome={stationNome}") { backStackEntry ->
            val stNome = backStackEntry.arguments?.getString("stationNome") as String
            InfoStationByName(navController, stNome)
        }
    }
}




