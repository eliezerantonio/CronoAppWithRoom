package com.example.cronoapp_e_room.repository

import com.example.cronoapp_e_room.model.Cronos
import com.example.cronoapp_e_room.room.CronosDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CronosRepository @Inject constructor(private val cronosDatabaseDao: CronosDatabaseDao) {

    suspend fun addCrono(crono: Cronos) = cronosDatabaseDao.insert(crono)
    suspend fun updateCrono(crono: Cronos) {
        cronosDatabaseDao.update(crono)
    }
    suspend fun deleteCrono(crono: Cronos) = cronosDatabaseDao.delete(crono)
    fun getAllCronos(): Flow<List<Cronos>> = cronosDatabaseDao.getCronos().flowOn(Dispatchers.IO).conflate()
    fun getCronoById(id: Long): Flow<Cronos?> = cronosDatabaseDao.getCronosById(id).flowOn(Dispatchers.IO).conflate()



}