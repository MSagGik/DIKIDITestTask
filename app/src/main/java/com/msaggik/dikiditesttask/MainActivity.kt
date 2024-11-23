package com.msaggik.dikiditesttask

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.msaggik.dikiditesttask.databinding.ActivityMainBinding
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private var navController: NavController? = null
    private var destinationChangedListener: NavController.OnDestinationChangedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        navController =
            (supportFragmentManager.findFragmentById(R.id.root_fragments) as NavHostFragment).navController
        navController?.let { binding.bottomNavigationMenu.setupWithNavController(it) }

        destinationChangedListener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                val activityRef = WeakReference(this@MainActivity)
                val activity = activityRef.get()
                activity?.let { instance ->
                    when (destination.id) {
                        com.msaggik.featuremore.R.id.languageFragment -> {
                            instance.binding.bottomNavigationMenu.visibility = View.GONE
                        }

                        else -> {
                            instance.binding.bottomNavigationMenu.visibility = View.VISIBLE
                        }
                    }
                }
            }

        destinationChangedListener?.let { listener ->
            navController?.addOnDestinationChangedListener(
                listener
            )
        }
    }

    override fun onPause() {
        super.onPause()
        destinationChangedListener?.let { navController?.removeOnDestinationChangedListener(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        destinationChangedListener = null
        navController = null
        _binding = null
    }
}
