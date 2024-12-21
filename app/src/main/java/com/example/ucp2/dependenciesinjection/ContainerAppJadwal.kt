package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.data.database.IRMDatabase
import com.example.ucp2.repository.LocalRepoJadwal
import com.example.ucp2.repository.RepositoryJadwal

interface InterfaceJadwal{
    val repositoryJadwal: RepositoryJadwal
}

class ContainerAppJadwal(private val context: Context) : InterfaceJadwal{
    override val repositoryJadwal: RepositoryJadwal by lazy {
        LocalRepoJadwal(IRMDatabase.getDatabase(context).jadwalDao())
    }
}