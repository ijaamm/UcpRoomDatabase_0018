package com.example.ucp2.dependenciesinjection

import android.content.Context
import com.example.ucp2.data.database.IRMDatabase
import com.example.ucp2.repository.LocalRepoDr
import com.example.ucp2.repository.RepositoryDr

interface InterfaceDokter {
    val repositoryDr : RepositoryDr
}

class ContainerAppDr(private val context: Context) : InterfaceDokter {
    override val repositoryDr: RepositoryDr by lazy {
        LocalRepoDr(IRMDatabase.getDatabase(context).dokterDao())
    }
}