package com.example.iptesttask.main_screen

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "item")
data class ListItem(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val name: String,
    val time: Long,
    val tags: String,
    var amount: Int
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            "${name.first()}"
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

