package com.example.ucp2.repository

import com.example.ucp2.data.dao.DokterDao
import com.example.ucp2.data.entity.Dokter
import kotlinx.coroutines.flow.Flow

class LocalRepoDr(
    private val dokterDao: DokterDao
):RepositoryDr {

    //untuk insert data dokter ke database
    override suspend fun insertDr(dokter: Dokter) {
        dokterDao.insertDokter(dokter)
    }

    //untuk mengambil data dokter dari database
    override fun getAllDr(): Flow<List<Dokter>> {
        return dokterDao.getAllDokter()
    }
}