package com.example.ucp2.ui.view.dokter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.data.entity.Dokter
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.shadow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.viewmodel.HomeDrUiState
import com.example.ucp2.viewmodel.HomeDrViewModel
import com.example.ucp2.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun CardDokter(
    dr: Dokter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Card(
        modifier = modifier
            .fillMaxWidth(1F)
            .padding(20.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Icon Person",
                    modifier = Modifier.padding(end = 8.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = dr.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Icon Date",
                    modifier = Modifier.padding(end = 8.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = dr.id,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Icon Home",
                    modifier = Modifier.padding(end = 8.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(4.dp))
                val textColor = when (dr.spesialis.trim()) {
                    "Spesialis Anak" -> Color.Blue
                    "Spesialis Gigi" -> Color.Red
                    "Spesialis Kulit" -> Color.Green
                    "Spesialis Bedah" -> Color.Magenta
                    else -> Color.Black // Default Color
                }
                Text(
                    text = dr.spesialis,
                    fontWeight = FontWeight.Bold,
                    color = textColor // Properti Warna Ditambahkan
                )
            }
        }
    }
}

@Composable
fun ListDokter(
    listDr: List<Dokter>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(
            items = listDr,
            key = { it.id }
        ) { dr ->
            CardDokter(
                dr = dr
            )
        }
    }
}

@Composable
fun BodyHomeDr(
    homeDrUiState: HomeDrUiState,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    when {
        homeDrUiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeDrUiState.isError -> {
            LaunchedEffect(homeDrUiState.errorMessage) {
                homeDrUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeDrUiState.listDr.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak Ada Data Dokter",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListDokter(
                listDr = homeDrUiState.listDr,
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeDrView(
    viewModel: HomeDrViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDr: () -> Unit = { },
    onAddJad: () -> Unit = { },
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                judul = "Daftar Dokter",
                showBackButton = false,
                onBack = { },
            )
        }
    ) { innerPadding ->
        val homeDrUiState by viewModel.homeDrUiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp) // Memberikan jarak antar tombol
            ) {
                // Tombol Tambah Dokter
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .clickable { onAddDr() }
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color(0XFF000080), shape = RoundedCornerShape(8.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Tambah Dokter Icon",
                            modifier = Modifier.padding(end = 8.dp),
                            tint = Color.White
                        )
                        Text(
                            text = "Tambah Dokter",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }

                // Tombol Jadwal
                Box(
                    modifier = Modifier
                        .weight(1f) // Membuat tombol ini mengisi ruang yang tersedia
                        .padding(8.dp)
                        .clickable { onAddJad() }
                        .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color(0XFF000080), shape = RoundedCornerShape(8.dp)) // Ubah warna jika perlu
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Jadwal Icon",
                            modifier = Modifier.padding(end = 8.dp),
                            tint = Color.White
                        )
                        Text(
                            text = "Jadwal Pasien",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }

            // Tampilan BodyHomeDr
            BodyHomeDr(
                homeDrUiState = homeDrUiState,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}