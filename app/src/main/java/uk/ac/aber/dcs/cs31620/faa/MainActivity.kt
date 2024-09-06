package uk.ac.aber.dcs.cs31620.faa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.aber.dcs.cs31620.faa.model.CatsViewModel
import uk.ac.aber.dcs.cs31620.faa.ui.authentication.LoginScreen
import uk.ac.aber.dcs.cs31620.faa.ui.cats.CatsScreenTopLevel
import uk.ac.aber.dcs.cs31620.faa.ui.home.HomeScreenTopLevel
import uk.ac.aber.dcs.cs31620.faa.ui.navigation.Screen
import uk.ac.aber.dcs.cs31620.faa.ui.theme.FAATheme

/**
 * Starting activity class. Entry point for the app.
 * @author Chris Loftus
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FAATheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavigationGraph()
                }
            }
        }
    }
}

@Composable
private fun BuildNavigationGraph(
    catsViewModel: CatsViewModel = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route){ HomeScreenTopLevel(navController, catsViewModel) }
        composable(Screen.Cats.route){ CatsScreenTopLevel(navController, catsViewModel) }
        composable(Screen.Login.route) { LoginScreen(navController) }
    }
}


