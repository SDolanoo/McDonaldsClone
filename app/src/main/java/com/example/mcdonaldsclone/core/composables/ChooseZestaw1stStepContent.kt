package com.example.mcdonaldsclone.core.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mcdonaldsclone.core.database.fakeData.FakeDataProvider

@Composable
fun ChooseZestaw1stStepContent(
    innerPadding: PaddingValues,
    navigateToProductWithExtrasContent:(Long) -> Unit
) {
    var choosenPoduct by remember { mutableStateOf(null) }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding,
        verticalItemSpacing = 2.dp,
    ) {
        item {
            Text(
                text = "Wybierz dodatek do zestawu",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 24.dp, top = 24.dp)
            )
        }
        item{
            Spacer(Modifier.height(5.dp))
        }

        item {
            val frytki = FakeDataProvider.menuItems.find { it.name == "Frytki" }!!
            ListItem(
                modifier = Modifier.clickable {navigateToProductWithExtrasContent(frytki.id)},
                leadingContent = {
                    Image(
                        painter = painterResource(id = frytki.imageResId), // Podmień na swoje źródło
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(56.dp) // Kwadratowy rozmiar
                            .clip(RoundedCornerShape(8.dp)) // Opcjonalnie – zaokrąglenia
                    )
                },
                headlineContent = {
                    Text(
                        text = frytki.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            )
            HorizontalDivider(thickness = 1.dp)
        }

        item {
            val salatka = FakeDataProvider.menuItems.find { it.name == "Sałatka" }!!
            ListItem(
                modifier = Modifier.clickable {navigateToProductWithExtrasContent(salatka.id)},
                leadingContent = {
                    Image(
                        painter = painterResource(id = salatka.imageResId), // Podmień na swoje źródło
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(56.dp) // Kwadratowy rozmiar
                            .clip(RoundedCornerShape(8.dp)) // Opcjonalnie – zaokrąglenia
                    )
                },
                headlineContent = {
                    Text(
                        text = salatka.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            )
            HorizontalDivider(thickness = 1.dp)
        }
    }
}