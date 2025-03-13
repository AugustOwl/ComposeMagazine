package com.nativetechdemo.creativemagazine.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nativetechdemo.creativemagazine.ui.navigation.AppNavigationGraph
import com.nativetechdemo.creativemagazine.ui.theme.CreativeMagazineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            CreativeMagazineTheme { // main theme for the Creative magazine app
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    AppEntryPoint()
                }

            }
        }
    }
}


@Composable
fun AppEntryPoint() {
    AppNavigationGraph()
}

@Composable
fun ArticleInShortEntryPoint() {

}