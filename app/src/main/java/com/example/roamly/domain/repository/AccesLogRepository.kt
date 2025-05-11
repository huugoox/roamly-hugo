package com.example.roamly.domain.repository

import com.example.roamly.data.local.entity.AccessLogEntity

interface AccessLogRepository {
    suspend fun insertAccessLog(accessLog: AccessLogEntity): Boolean
}
