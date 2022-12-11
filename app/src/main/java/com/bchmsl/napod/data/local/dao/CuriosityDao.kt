package com.bchmsl.napod.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bchmsl.napod.data.local.model.CuriosityPhotoEntity

@Dao
interface CuriosityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCuriosityPhoto(curiosityPhotoEntity: CuriosityPhotoEntity)

    @Delete
    suspend fun removeCuriosityPhoto(curiosityPhotoEntity: CuriosityPhotoEntity)

    @Query("SELECT * FROM curiosityphotoentity")
    suspend fun getCuriosityPhotos(): List<CuriosityPhotoEntity>
}