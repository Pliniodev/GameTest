package com.pliniodev.gametest.data.local.localdatasource

import com.pliniodev.gametest.data.local.dao.StepDAO
import com.pliniodev.gametest.data.local.mapper.StepMapper
import com.pliniodev.gametest.data.local.model.StepEntity
import com.pliniodev.gametest.domain.model.StepDomain

class LocalDataSourceImpl(
    private val db: StepDAO
) : LocalDataSource {
    override fun updateDB(stepEntity: StepEntity): Long {
        return db.updateDB(stepEntity)
    }

    override fun getPhraseDB(stepNumber: Int): StepDomain {
        val response = db.getPhrase(stepNumber)
        return StepMapper.stepToDomain(response)
    }
}