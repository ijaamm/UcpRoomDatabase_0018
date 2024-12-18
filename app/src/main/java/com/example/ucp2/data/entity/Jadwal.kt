package com.example.ucp2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal")
data class Jadwal(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val NmDoker: String,
    val NmPasien: String,
    val noHp: String,
    val tanggal: String,
    val status: String
)
