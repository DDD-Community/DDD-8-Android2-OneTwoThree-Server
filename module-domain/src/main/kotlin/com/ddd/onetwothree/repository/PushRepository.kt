package com.ddd.onetwothree.repository

import com.ddd.onetwothree.entity.Push
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.repository.Repository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils
import javax.sql.DataSource

interface PushRepository: Repository<Push, Long>, PushJdbcRepository {
    fun findByAlarmId(alarmId: Long): List<Push>
    @Modifying
    fun deleteAllByAlarmId(alarmId: Long)

}

interface PushJdbcRepository {
    fun saveAll(pushList: List<Push>): List<Push>
}

class PushJdbcRepositoryImpl(
    private val datasource: DataSource
): PushJdbcRepository {
    override fun saveAll(pushList: List<Push>): List<Push> {
        val jdbcTemplate = NamedParameterJdbcTemplate(datasource)
        val records = pushList.map { it.toRecord() }

        jdbcTemplate.batchUpdate(
            this.createBulkInsertQuery(records[0].keys.toSet()),
            SqlParameterSourceUtils.createBatch(records.toTypedArray())
        )
        return jdbcTemplate.queryForObject("SELECT last_insert_id()", emptyMap<String, Any>(), Long::class.java)?.let { id ->
            pushList.onEachIndexed { idx, push -> push.id = id + idx }
        } ?: emptyList()
    }

    private fun createBulkInsertQuery(columnNames: Set<String>) =
        "INSERT INTO push (${columnNames.joinToString(", ")}) VALUES " +
                "(${columnNames.joinToString(", ") { ":$it" }})"
}
