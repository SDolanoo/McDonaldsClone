package com.example.mcdonaldsclone.features.makeOrder

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mcdonaldsclone.R
import com.example.mcdonaldsclone.core.composables.BottomBarButton
import com.example.mcdonaldsclone.core.composables.StepperTopAppBar
import com.example.mcdonaldsclone.core.composables.YellowCheck

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeOrderScreen(
    onBackClick: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val scope = rememberCoroutineScope()

    var currentState by remember { mutableIntStateOf(0)}

    var miejsceOdbioru by remember { mutableStateOf("") }
    var pickupOption by remember { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            StepperTopAppBar(
                currentStep = currentState,
                scrollBehavior = scrollBehavior,
                onBackClick = { onBackClick() })
        },
        bottomBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(WindowInsets.navigationBars.asPaddingValues())
            ) {
                if (currentState == 0) {
                    BottomBarButton(
                        text = "Następnie: Potwierdź i zapłać",
                        modifier = Modifier.weight(1f),
                        onClick = { currentState = 1},
                        color = if (miejsceOdbioru != "") Color(0xFFFFC107) else Color.LightGray
                    )
                }
                if (currentState == 1) {
                    BottomBarButton(
                        text = "Zamawiam i płacę",
                        modifier = Modifier.weight(1f),
                        onClick = {currentState = 2}
                    )
                }

            }
        }
    ) { innerPading ->
        if (currentState == 0) {
            StepOne(
                innerPading = innerPading,
                onSheet = {showSheet = true},
                miejsceOdbioru = miejsceOdbioru
            )
        } else if (currentState == 1) {
            StepTwo(
                innerPading = innerPading,
                pickupOption = pickupOption
            )
        } else {
            StepThree(
                innerPading = innerPading
            )
        }

        if (showSheet) {
            PickupOptionBottomSheet(
                sheetState = sheetState,
                onDismiss = { showSheet = false },
                onConfirm = { pickup ->
                    pickupOption = pickup
                    miejsceOdbioru = "Przy ladzie"
                    showSheet = false
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupOptionBottomSheet(
    sheetState: SheetState = rememberModalBottomSheetState(),
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit // (PickupType, Mode)
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier.padding(vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Drugi ekran – wybór trybu
            Text("Odbiór przy ladzie", fontWeight = FontWeight.Bold, fontSize = 20.sp)

            Spacer(modifier = Modifier.height(16.dp))

            BottomBarButton(
                text = "Na miejscu - na tacy",
                modifier = Modifier.fillMaxWidth().height(40.dp).padding(horizontal = 5.dp),
                onClick = {onConfirm("Na miejscu - na tacy")},
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomBarButton(
                text = "Na wynos",
                modifier = Modifier.fillMaxWidth().height(40.dp).padding(horizontal = 5.dp),
                onClick = {onConfirm("Na wynos")},
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            BottomBarButton(
                text = "Wybierz inną opcję",
                modifier = Modifier.fillMaxWidth().height(40.dp).padding(horizontal = 5.dp),
                onClick = { onDismiss() },
                color = Color.White
            )
        }




    }

    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
fun StepOne(
    innerPading: PaddingValues,
    onSheet: () -> Unit,
    miejsceOdbioru: String
) {
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
                modifier = Modifier.clickable { onSheet() },
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
                },
                trailingContent = {
                    if (miejsceOdbioru == "Przy ladzie") {
                        YellowCheck()
                    }
                }
            )
            HorizontalDivider(thickness = 1.dp)
        }
    }
}

@Composable
fun StepTwo(
    innerPading: PaddingValues,
    pickupOption: String = "Na wynos"
) {
    LazyColumn(modifier = Modifier.padding(innerPading).padding(horizontal = 16.dp)) {
        item {
            Text(
                text = "Potwierdź i zapłać",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))
        }



        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Sposób odbioru zamówienia",
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Zmień",
                    color = Color.Blue,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }
            Text(text = "Przy ladzie\npickupOption", color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Płatność", fontWeight = FontWeight.SemiBold)
                Text(text = "17,20 zł", fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background), // Dodaj własny zasób
                        contentDescription = "Visa",
                        modifier = Modifier.height(38.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = " •••• •••• •••• 5647")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Wprowadź numer NIP (opcjonalnie)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Punkty zostaną automatycznie dodane do Twojego konta.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    modifier = Modifier.weight(0.7f),
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Icon(
                    painter = painterResource(id = R.drawable.icon5), // Dodaj własny zasób
                    contentDescription = "Visa",
                    modifier = Modifier.weight(0.2f),
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Box(
                modifier = Modifier
                    .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = "Upewnij się, że będziesz w stanie odebrać zamówienie w przeciągu 3-5 minut.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                text = "Złożone zamówienie nie może być zmienione, ani anulowane.",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        item {
            Text(
                text = "Upewnij się, ze składasz zamówienie we właściwiej restauracji i że zdążysz odebrać zamówienie przed zamnkięciem.",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    }
}

@Composable
fun StepThree(innerPading: PaddingValues,) {
    val baseSize = 100f

    val animatedSize = remember { Animatable(baseSize) }

    var paymentMade by remember { mutableStateOf(false) }

    LaunchedEffect(paymentMade) {
        if (!paymentMade) {
            val startTime = withFrameNanos { it }
            while (!paymentMade && (withFrameNanos { it } - startTime) < 3_000_000_000L) {
                animatedSize.animateTo(
                    targetValue = baseSize * 0.5f,
                    animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                )
                animatedSize.animateTo(
                    targetValue = baseSize,
                    animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                )
            }
        }
        paymentMade = true
    }

    if (!paymentMade) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPading),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Stały box z maksymalnym rozmiarem — rezerwuje miejsce
            Box(
                modifier = Modifier
                    .size(baseSize.dp), // stały rozmiar kontenera
                contentAlignment = Alignment.Center
            ) {
                // Skalujemy obrazek w środku boxa
                Image(
                    painter = painterResource(R.drawable.icon4),
                    contentDescription = "",
                    modifier = Modifier.size(animatedSize.value.dp)
                )
            }

            Text(
                text = "Realizowanie płatności",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(10.dp)
            )
        }
    } else {
        Image(
            painter = painterResource(R.drawable.step_three),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}