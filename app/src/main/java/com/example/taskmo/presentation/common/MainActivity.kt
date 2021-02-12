package com.example.taskmo.presentation.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.taskmo.R
import com.example.taskmo.presentation.main.favorites.FavoritesFragment
import com.example.taskmo.presentation.main.posts.PostListFragment
import com.example.taskmo.utils.Const.BOOLEAN_DEFAULT_VALUE
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var isConnectionAvailable:Boolean = BOOLEAN_DEFAULT_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manageBottomNavigationView()

        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    private fun manageBottomNavigationView() {
        val bottomNavigationView =
            findViewById<View>(R.id.navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.posts -> selectedFragment = PostListFragment.getInstance()
                R.id.favorites -> selectedFragment = FavoritesFragment.getInstance()
            }
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            selectedFragment?.let { transaction.replace(R.id.frame_layout, it) }
            transaction.commit()
            true
        }
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, PostListFragment.getInstance())
        transaction.commit()
    }


    private val connectivityManager: ConnectivityManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val builder: NetworkRequest.Builder by lazy {
        NetworkRequest.Builder()
    }

    private val networkCallback: ConnectivityManager.NetworkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                onInternetConnectionChange(true)
            }

            override fun onLost(network: Network) {
                onInternetConnectionChange(false)
            }
        }
    }

    override fun onStart() {
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (connectivityManager.activeNetwork == null)
                onInternetConnectionChange(false)
        } else {
            if (connectivityManager.activeNetworkInfo == null)
                onInternetConnectionChange(false)
        }

        super.onStart()
    }

    override fun onStop() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
        super.onStop()
    }

    fun onInternetConnectionChange(isConnectionON: Boolean = false) {
        isConnectionAvailable = isConnectionON
        if (!isConnectionON) {
            Snackbar.make(rlMain, getString(R.string.no_internet), Snackbar.LENGTH_LONG).show()
        }
    }
}
