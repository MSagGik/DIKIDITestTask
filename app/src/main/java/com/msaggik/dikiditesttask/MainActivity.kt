package com.msaggik.dikiditesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.msaggik.commonui.theme.AppTheme
import com.msaggik.dikiditesttask.compose.MainCompose

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainCompose()
            }
        }
    }
}
