import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.navigation.SetupNavigation

@Composable
@Preview
fun App() {
    lateinit var navController: NavHostController
    di.initializeKoin()
    MaterialTheme {
        navController = rememberNavController()
        SetupNavigation(navController = navController)
    }
}