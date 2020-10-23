package com.yuvrajpatel.thescoretask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.yuvrajpatel.thescoretask.R
import com.yuvrajpatel.thescoretask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBindingMainActivity: ActivityMainBinding
    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBindingMainActivity = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mNavController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, mNavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController, null)
    }
}