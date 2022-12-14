package space.mel.cocktail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import space.mel.cocktail.databinding.ActivityMainBinding
import space.mel.cocktail.fragment.CocktailsFragment



class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        if (savedInstanceState == null) {
            navigateToFragment(CocktailsFragment())
        }
    }
    private fun navigateToFragment(fragment: Fragment) {
        //navController.navigate(R.id.action_homeFragment_to_findInfoFragment2)
    }
}


