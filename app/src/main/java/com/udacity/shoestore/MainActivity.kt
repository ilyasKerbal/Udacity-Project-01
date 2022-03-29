package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Will be used to configure the app bar
    private lateinit var appBarConfiguration: AppBarConfiguration
    // Will hold reference to the toolbar (from: androidx)
    private lateinit var toolbar: Toolbar
    // The activity-level ViewModel
    private lateinit var viewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        /*
         *  This is different from the nanodegree materials,
         * because we are using the androidx.fragment.app.FragmentContainerView
         * https://developer.android.com/guide/fragments/fragmentmanager
         * https://stackoverflow.com/questions/58703451/fragmentcontainerview-as-navhostfragment
         * */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Assigning the toolbar
        toolbar = binding.toolbar

        // I set the action bar for the whole activity.
        // Any hosted fragment can interact with action bar
        setSupportActionBar(toolbar)

        // I want to remove the toolbar when we are in the login, instruction, and onBoarding fragments
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id in listOf<Int>(R.id.loginFragment, R.id.instructionFragment, R.id.onboardingFragment)){
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }
        // Set the ShoeListFragment as the main so the back button is not showing after logging.
        appBarConfiguration = AppBarConfiguration(mutableSetOf<Int>(R.id.shoeListFragment))
        // Tie everything together for the navigation
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)

        // Triggering the activity-level ViewModel init block
        viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
    }

    /*
    * Handle the navigation up
    * */
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
    }
}
