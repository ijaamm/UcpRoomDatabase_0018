package com.example.ucp2.repository

import com.example.ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

interface RepositoryDr {

    suspend fun insertDr(dokter: Dokter)

    fun getAllDr(): Flow<List<Dokter>>
}