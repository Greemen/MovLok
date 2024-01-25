package com.example.movlok

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

class AppExecutors private constructor() {

    private val mNetworkIO: ScheduledExecutorService = Executors.newScheduledThreadPool(3)

    fun networkIO(): ScheduledExecutorService {
        return mNetworkIO
    }

    companion object {
        @Volatile
        private var instance: AppExecutors? = null

        fun getInstance(): AppExecutors =
            instance ?: synchronized(this) {
                instance ?: AppExecutors().also { instance = it }
            }
    }
}