package com.breshy.tracktrail.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.breshy.tracktrail.data.UserDatabase
import com.breshy.tracktrail.repository.UserRepository
import com.breshy.tracktrail.ui.screens.about.AboutScreen
import com.breshy.tracktrail.ui.screens.bookings.BookingsScreen
import com.breshy.tracktrail.ui.screens.bus.BusScreen
import com.breshy.tracktrail.ui.screens.dashboard.DashboardScreen
import com.breshy.tracktrail.ui.screens.home.HomeScreen
import com.breshy.tracktrail.ui.screens.pay.PayScreen
import com.breshy.tracktrail.ui.screens.splash.SplashScreen
import com.breshy.tracktrail.viewmodel.AuthViewModel
import com.breshy.zawadimart.ui.screens.auth.LoginScreen
import com.breshy.zawadimart.ui.screens.auth.RegisterScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    val context = LocalContext.current


    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_BUS) {
            BusScreen(navController)
        }
        composable(ROUT_BOOKINGS) {
            BookingsScreen(navController)
        }
        composable(ROUT_PAY) {
            PayScreen(navController)
        }
        composable(ROUT_DASHBOARD) {
            DashboardScreen(navController)
        }
        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        //AUTHENTICATION

        // Initialize Room Database and Repository for Authentication
        val appDatabase = UserDatabase.getDatabase(context)
        val authRepository = UserRepository(appDatabase.userDao())
        val authViewModel: AuthViewModel = AuthViewModel(authRepository)
        composable(ROUT_REGISTER) {
            RegisterScreen(authViewModel, navController) {
                navController.navigate(ROUT_DASHBOARD) {
                    popUpTo(ROUT_DASHBOARD) { inclusive = true }
                }
            }
        }

        composable(ROUT_LOGIN) {
            LoginScreen(authViewModel, navController) {
                navController.navigate(ROUT_DASHBOARD) {
                    popUpTo(ROUT_DASHBOARD) { inclusive = true }
                }
            }
        }


    }
}