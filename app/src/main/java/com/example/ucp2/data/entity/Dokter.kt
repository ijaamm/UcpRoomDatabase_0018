package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dokter")
data class Dokter (
    @PrimaryKey(autoGenerate = true)
    var id: String,
    var nama: String,
    var spesialis: String,
    var klinik: String,
    var noHp: String,
    var jamPraktik: String
)