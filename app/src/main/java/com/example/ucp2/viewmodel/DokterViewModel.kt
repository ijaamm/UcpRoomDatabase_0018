package com.example.ucp2.viewmodel

import com.example.ucp2.data.entity.Dokter


//data input form
data class DokterEvent(
    var id: String = "",
    var nama: String = "",
    var spesialis: String = "",
    var klinik: String = "",
    var noHp: String = "",
    var jamPraktik: String = ""
)

//menyimpan input ke dalam entity
fun DokterEvent.toDokterEntity(): Dokter = Dokter(
    id = id,
    nama = nama,
    spesialis = spesialis,
    klinik = klinik,
    noHp = noHp,
    jamPraktik = jamPraktik
)

data class FormErrorState(
    val id : String? = null,
    val nama : String? = null,
    val spesialis : String? = null,
    val klinik : String? = null,
    val noHp : String? = null,
    val jamPraktik : String? = null
){
    fun isValid(): Boolean {
        return id == null &&
                nama == null &&
                spesialis == null &&
                klinik == null &&
                noHp == null &&
                jamPraktik == null
    }
}

data class DrUiState(
    val DokterEvent: DokterEvent = DokterEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackbarMessage: String? = null
)