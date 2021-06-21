package com.pliniodev.gametest.data.local.dao

import androidx.room.*
import com.pliniodev.gametest.data.local.model.StepModel

@Dao
interface StepDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStepData(stepModel: StepModel): Long

    @Query("SELECT * FROM step WHERE stepNumber = :stepNumber")
    fun getPhrase(stepNumber: Int): StepModel
}