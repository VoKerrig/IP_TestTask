package com.example.iptesttask.roomdb.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.example.iptesttask.main_screen.ListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Update
    suspend fun updateItem(item: ListItem)

    @Delete
    suspend fun deleteItem(item: ListItem)

    @Query("SELECT * FROM item")
    fun getAllItemsFlow(): Flow<List<ListItem>>
}