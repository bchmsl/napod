package com.bchmsl.napod.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bchmsl.napod.data.local.model.ApodEntity

@Dao
interface ApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apod: ApodEntity)

    @Delete
    suspend fun removeApod(apod: ApodEntity)

    @Query("SELECT * FROM apodentity")
    suspend fun getApod(): List<ApodEntity>
}