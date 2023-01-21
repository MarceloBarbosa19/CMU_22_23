package pt.ipp.estg.assistenteviagens.navigation.authNavigationScreens.models.entity


sealed class AuthNavigationItems(
    var route: String,
    var title: String,
){
    object Home: AuthNavigationItems("home", "Home")
    object Login: AuthNavigationItems("login", "Login")
    object Register: AuthNavigationItems("register", "Register")
}
