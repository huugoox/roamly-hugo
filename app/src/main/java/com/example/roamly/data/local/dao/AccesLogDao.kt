package com.example.roamly.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roamly.data.local.entity.AccessLogEntity

@Dao
interface AccessLogDao {
    @Insert
    suspend fun insertAccessLog(log: AccessLogEntity):  Long
}