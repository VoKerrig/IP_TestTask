package com.example.iptesttask.roomdb.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iptesttask.main_screen.ListItem

@Database(
    entities = [ListItem::class],
    version = 1,
    exportSchema = false
)
abstract class MainDb: RoomDatabase() {
    abstract val dao: Dao
}