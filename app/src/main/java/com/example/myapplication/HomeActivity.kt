package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Navbar icon click opens the drawer
        val navbarIcon: ImageView = findViewById(R.id.homenavbar)
        navbarIcon.setOnClickListener {
            drawerLayout.openDrawer(findViewById<NavigationView>(R.id.nav_view))
        }

        // Initialize BottomNavigationView
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
    }

    override fun onBackPressed() {
        // Close the drawer if it's open; otherwise, proceed with default back action
        if (drawerLayout.isDrawerOpen(findViewById<NavigationView>(R.id.nav_view))) {
            drawerLayout.closeDrawer(findViewById<NavigationView>(R.id.nav_view))
        } else {
            super.onBackPressed()
        }
    }
}
