package com.pliniodev.gametest.data.local.repository

import androidx.room.Dao
import androidx.room.Insert
import com.pliniodev.gametest.data.local.model.StepModel

@Dao
interface StepDAO {

    @Insert
    fun saveStepData(stepModel: StepModel): Long

//    @Insert
//    fun saveStepData(step: StepModel): Int

//    @Query("SELECT * FROM " + Constants.TABLE_NAME + "WHERE stepNumber = :stepNumber")
//    fun getPhrase(stepNumber: Int): StepModel
}