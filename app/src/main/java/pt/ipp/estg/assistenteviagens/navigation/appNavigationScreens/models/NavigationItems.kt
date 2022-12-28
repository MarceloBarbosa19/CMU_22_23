package pt.ipp.estg.assistenteviagens.navigation.appNavigationScreens.models

import pt.ipp.estg.assistenteviagens.R

sealed class NavigationItems(
    var route: String,
    var icon: Int,
    var title: String
){
    object Home: NavigationItems("home", R.drawable.ic_outline_home_24, "Home")
    object Profile: NavigationItems("profile", R.drawable.ic_outline_account_circle_24, "Profile")
    object Favorites: NavigationItems("favorites", R.drawable.ic_outline_star_outline_24, "Favoritos")
    object Suggest: NavigationItems("suggest", R.drawable.ic_outline_add_circle_outline_24, "Sugestões")
    object Settings: NavigationItems("settings", R.drawable.ic_outline_settings_24, "Settings")
    object Logout: NavigationItems("logout", R.drawable.ic_baseline_login_24, "Logout")


    object SearchStation: NavigationItems("searchStation", R.drawable.ic_baseline_local_gas_station_24, "SearchStation")
    object FoundStation: NavigationItems("foundStation", R.drawable.ic_baseline_local_gas_station_24, "FoundStation")
    object InfoStation: NavigationItems("infoStation", R.drawable.ic_baseline_local_gas_station_24, "InfoStation")
    object LocalizationStation: NavigationItems("localizationStation", R.drawable.ic_baseline_local_gas_station_24, "LocalizationStation")
    object TopStations: NavigationItems("topStations", R.drawable.ic_baseline_local_gas_station_24, "TopStations")

}