package com.bchmsl.napod.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bchmsl.napod.data.local.dao.ApodDao
import com.bchmsl.napod.data.local.dao.CuriosityDao
import com.bchmsl.napod.data.local.model.ApodEntity
import com.bchmsl.napod.data.local.model.CuriosityPhotoEntity

@Database(
    entities = [ApodEntity::class, CuriosityPhotoEntity::class],
    version = 1
)
abstract class NapodDatabase: RoomDatabase() {
    abstract val apodDao: ApodDao
    abstract val curiosityDao: CuriosityDao
}