package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependenciesinjection.ContainerAppDr
import com.example.ucp2.dependenciesinjection.ContainerAppJadwal

class IRMApp : Application() {
    lateinit var containerAppDr: ContainerAppDr
    lateinit var containerAppJadwal: ContainerAppJadwal

    override fun onCreate() {
        super.onCreate()
        containerAppDr = ContainerAppDr(this)
        containerAppJadwal = ContainerAppJadwal(this)
    }

}