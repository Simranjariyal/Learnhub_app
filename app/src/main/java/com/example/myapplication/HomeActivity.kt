package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView

    // Navigation header views
    private lateinit var navProfilePhoto: ImageView
    private lateinit var navProfileName: TextView

    // Sample variable to determine if a profile is added
    private var isProfileAdded: Boolean = false // Set to true if a profile exists

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Navbar icon click opens the drawer
        val navbarIcon: ImageView = findViewById(R.id.homenavbar)
        navbarIcon.setOnClickListener {
            drawerLayout.openDrawer(findViewById(R.id.nav_view))
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // Set the listener for navigation item selection
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val itemView = bottomNavigationView.findViewById<View>(menuItem.itemId)

            // Apply the slight upward animation
            val moveUpAnimation = AnimationUtils.loadAnimation(this, R.anim.move_up)
            itemView.startAnimation(moveUpAnimation)

            // Change the clicked icon's color to black
            menuItem.icon?.setTint(Color.BLACK)

            // Handle navigation actions
            when (menuItem.itemId) {
                R.id.nav_home -> true
                R.id.nav_profile -> true
                R.id.nav_setting -> true
                else -> false
            }
        }

        // Access the navigation view and set up header views
        val navView: NavigationView = findViewById(R.id.nav_view)
        val headerView = navView.getHeaderView(0)
        navProfilePhoto = headerView.findViewById(R.id.profile_image)
        navProfileName = headerView.findViewById(R.id.profile_image) // Change this line to fetch the actual TextView for the name.

        // Set up profile image click listener
        navProfilePhoto.setOnClickListener {
            toggleProfileDisplay()
        }

        // Initially set the profile display
        toggleProfileDisplay()
    }

    private fun toggleProfileDisplay() {
        if (isProfileAdded) {
            // If profile exists, update the sidebar with user's profile
            navProfilePhoto.setImageResource(R.drawable.profile_photo) // Replace with actual user's photo
            navProfileName.text = "User Name" // Replace with actual user's name
        } else {
            // Show default values
            navProfilePhoto.setImageResource(R.drawable.profile_photo) // Default placeholder
            navProfileName.text = "Add Photo"
        }
    }

    override fun onBackPressed() {
        // Close the drawer if it's open; otherwise, proceed with default back action
        if (drawerLayout.isDrawerOpen(findViewById(R.id.nav_view))) {
            drawerLayout.closeDrawer(findViewById(R.id.nav_view))
        } else {
            super.onBackPressed()
        }
    }
}
