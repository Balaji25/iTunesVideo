package com.bg.itunesvideo.data.view

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bg.itunesvideo.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var navView: BottomNavigationView
    private var x1: Float = 0.0f
    private var x2: Float = 0.0f
    val MIN_DISTANCE = 150  // area of moving

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
       // setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



    }

/**
 *  this method is used to handle the swipe event on screen
 *  using this method we have manually changed  nav controller tab
 *  */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val eventaction = event.action

        when (eventaction) {

            MotionEvent.ACTION_DOWN ->
                x1 = event.getX();


            MotionEvent.ACTION_UP -> {
                x2 = event.getX();
                var deltaX = x2 - x1
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    if (x2 > x1)
                    {
                        navView.selectedItemId

                        navView.setSelectedItemId(R.id.navigation_home);
                       // Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show ();
                    }
                    // Right to left swipe action
                    else
                    {
                        navView.setSelectedItemId(R.id.navigation_dashboard);

                    }
                }

            }
            MotionEvent.ACTION_MOVE ->

                Toast.makeText(this, "MOVE", Toast.LENGTH_SHORT)
            else -> {
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val action: Int = event.actionMasked

        return when (action) {
            MotionEvent.ACTION_DOWN -> {

                true
            }
            MotionEvent.ACTION_MOVE -> {

                true
            }
            MotionEvent.ACTION_UP -> {

                true
            }
            MotionEvent.ACTION_CANCEL -> {

                true
            }
            MotionEvent.ACTION_OUTSIDE -> {

                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}