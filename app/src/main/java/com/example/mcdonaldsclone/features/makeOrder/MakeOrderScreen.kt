package com.example.mcdonaldsclone.features.makeOrder

import android.R.attr.category
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.composables.BottomBarButton
import com.example.mcdonaldsclone.core.database.model.MenuItem
import com.example.mcdonaldsclone.core.database.model.Set

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeOrderScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
           StepperTopAppBar(currentStep = 0, scrollBehavior = scrollBehavior) { }
        },
        bottomBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(WindowInsets.navigationBars.asPaddingValues())
            ) {
                BottomBarButton(
                    text = "Następnie: Potwierdź i zapłać",
                    modifier = Modifier.weight(1f),
                    onClick = {},
                )
            }
        }
    ) { innerPading ->
        LazyColumn(modifier = Modifier.padding(innerPading)) {
            item{
                Text(
                    text = "Wybierz miejsce odbioru",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp)
                )
            }

            item{
                ListItem(
                    modifier = Modifier.clickable {},
                    leadingContent = {
                        Image(
                            painter = painterResource(id = R.drawable.zestaw1), // Podmień na swoje źródło
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(56.dp) // Kwadratowy rozmiar
                                .clip(RoundedCornerShape(8.dp)) // Opcjonalnie – zaokrąglenia
                        )
                    },
                    headlineContent = {
                        Text(
                            text = "Przy ladzie",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "Dostępne: 07:00-00:59",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black
                        )
                    }
                )
            }


        }


    }
}