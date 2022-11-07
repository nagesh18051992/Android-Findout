package com.findout.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.findout.R
import com.findout.databinding.ActivityMainBinding
import com.findout.ui.base.BaseActivity
import com.findout.ui.permission.PermissionFragment
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_main)

        if (hasPermissions(applicationContext,PermissionFragment.PERMISSIONS)){
            graph.setStartDestination(R.id.landingFragment)
        }else{
            graph.setStartDestination(R.id.permissionFragment)
        }

        navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }
}