package com.example.whowon

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.whowon.configs.DatabaseSingleton
import com.example.whowon.databinding.ActivityMainBinding
import com.example.whowon.workers.DatabaseOperations

import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var mainContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainContext=this@MainActivity
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        init()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_raffles,R.id.nav_beginners, R.id.nav_free_participation, R.id.nav_car_win,R.id.nav_car_win,R.id.nav_win_phone_tablet,R.id.nav_win_vacation,R.id.nav_what_i_follow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                exitApp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun processData(url: String, type: String) {
        val databaseOperations = DatabaseOperations(url, type)
        databaseOperations.dataInsert()
        databaseOperations.startRefreshingRaffle()
    }

    private fun init() {
        val dataUrls = listOf(
            "https://www.kimkazandi.com/cekilisler",
            "https://www.kimkazandi.com/cekilisler/araba-kazan",
            "https://www.kimkazandi.com/cekilisler/bedava-katilim",
            "https://www.kimkazandi.com/cekilisler/telefon-tablet-kazan",
            "https://www.kimkazandi.com/cekilisler/tatil-kazan",
            "https://www.kimkazandi.com/cekilisler/yeni-baslayanlar"
        )

        val dataTypes = listOf(
            "Raffle",
            "CarWin",
            "FreeParticipation",
            "WinPhoneTablet",
            "WinVacation",
            "Beginners"
        )

        for (i in dataUrls.indices) {
            processData(dataUrls[i], dataTypes[i])
        }
    }

    private fun exitApp() {
        finishAffinity()
        exitProcess(0)
    }
}