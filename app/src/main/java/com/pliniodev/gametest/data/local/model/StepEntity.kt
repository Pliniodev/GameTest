package com.pliniodev.gametest.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Step")
data class StepEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "stepNumber")
    var stepNumber: Int = 0,

    @ColumnInfo(name = "phrase")
    var phrase: String = "",
)