package com.example.roamly.data.repository

import com.example.roamly.data.local.dao.AccessLogDao
import com.example.roamly.data.local.entity.AccessLogEntity
import com.example.roamly.domain.repository.AccessLogRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessLogRepositoryImpl @Inject constructor(
    private val accessLogDao: AccessLogDao
) : AccessLogRepository {
    override suspend fun insertAccessLog(accessLog: AccessLogEntity): Boolean {
        return accessLogDao.insertAccessLog(accessLog) > 0
    }
}
