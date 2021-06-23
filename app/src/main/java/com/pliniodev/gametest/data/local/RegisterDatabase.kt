package com.pliniodev.gametest.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pliniodev.gametest.data.local.model.StepModel
import com.pliniodev.gametest.data.local.dao.StepDAO


@Database(entities = [StepModel::class], version = 1)
abstract class RegisterDatabase : RoomDatabase() {
    abstract val stepDAO: StepDAO
}

internal fun provideDatabase(application: Application): RegisterDatabase {
    return Room.databaseBuilder(application, RegisterDatabase::class.java, "registerDB")
        .allowMainThreadQueries()
        .build()
}

internal fun provideStepDAO(database: RegisterDatabase): StepDAO {
    return database.stepDAO
}