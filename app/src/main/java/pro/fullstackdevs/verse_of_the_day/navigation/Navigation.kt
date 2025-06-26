package pro.fullstackdevs.verse_of_the_day.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pro.fullstackdevs.verse_of_the_day.VerseListScreen
import pro.fullstackdevs.verse_of_the_day.ProfileScreen
import pro.fullstackdevs.verse_of_the_day.VerseViewModel

sealed class Screen(val route: String) {
    object VerseList : Screen("verse_list")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation(navController: NavHostController, viewModel: VerseViewModel) {
    NavHost(navController = navController, startDestination = Screen.VerseList.route) {
        composable(Screen.VerseList.route) {
            VerseListScreen(viewModel = viewModel, onProfileClick = {
                navController.navigate(Screen.Profile.route)
            })
        }
        composable(Screen.Profile.route) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }
    }
}
