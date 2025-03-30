package com.example.projmobile.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.projmobile.model.Ubs
import com.example.projmobile.network.UbsApi
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import androidx.work.*

class UbsWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(UbsApi::class.java)
            val response = api.getUbs().execute()

            if (response.isSuccessful) {
                val lista = response.body() ?: emptyList()
                val json = Gson().toJson(lista)
                val arquivo = File(applicationContext.cacheDir, "ubs_cache.json")
                arquivo.writeText(json)
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}
