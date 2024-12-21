package com.example.ucp2.repository

import com.example.ucp2.data.dao.JadwalDao
import com.example.ucp2.data.entity.Jadwal
import kotlinx.coroutines.flow.Flow

class LocalRepoJadwal (
    private val jadwalDao: JadwalDao
) : RepositoryJadwal{

    //untuk insert data jadwal ke database
    override suspend fun insertJadwal(jadwal: Jadwal) {
    jadwalDao.insertJadwal(jadwal)
    }

    //untuk mengambil data jadwal dari database
    override fun getAllJadwal(): Flow<List<Jadwal>> {
        return jadwalDao.getAllJadwal()
    }

    //untuk mengambil data jadwal berdasarkan id dari database
    override fun getJadwal(id: String): Flow<Jadwal> {
        return jadwalDao.getJadwal(id)
    }

    //untuk menghapus data jadwal dari database
    override suspend fun deleteJadwal(jadwal: Jadwal) {
        jadwalDao.deleteJadwal(jadwal)
    }

    //untuk mengupdate data jadwal dari database
    override suspend fun updateJadwal(jadwal: Jadwal) {
        jadwalDao.updateJadwal(jadwal)
    }
}