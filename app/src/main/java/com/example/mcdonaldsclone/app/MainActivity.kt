package com.example.mcdonaldsclone.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mcdonaldsclone.core.navigation.AppNavGraph
import com.example.mcdonaldsclone.features.makeOrder.MakeOrderScreen
import com.example.mcdonaldsclone.ui.theme.McDonaldsCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            McDonaldsCloneTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                val navController = rememberNavController()
                AppNavGraph(
                    navController = navController
                )
            }
        }

    }
}




@Preview(showBackground = true)
@Composable
fun CardShowcasePreview() {
    McDonaldsCloneTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
        val navController = rememberNavController()
        AppNavGraph(
            navController = navController
        )

    }
}
