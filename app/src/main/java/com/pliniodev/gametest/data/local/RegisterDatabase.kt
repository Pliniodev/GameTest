package com.pliniodev.gametest.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pliniodev.gametest.data.local.dao.StepDAO
import com.pliniodev.gametest.data.local.model.StepEntity

@Database(entities = [StepEntity::class], version = 1)
abstract class RegisterDatabase : RoomDatabase() {
    abstract val stepDAO: StepDAO
}

internal fun provideDB(application: Application): RegisterDatabase {
    return Room.databaseBuilder(application, RegisterDatabase::class.java, "registerDB")
        .allowMainThreadQueries()
        .build()
}

internal fun provideStepDAO(database: RegisterDatabase): StepDAO {
    return database.stepDAO
}