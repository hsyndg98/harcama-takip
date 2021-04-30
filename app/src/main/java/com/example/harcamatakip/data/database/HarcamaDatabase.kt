package com.example.harcamatakip.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harcamatakip.data.model.Harcama

@Database(entities = [Harcama::class], version = 1, exportSchema = false)
abstract class HarcamaDatabase : RoomDatabase() {

    abstract fun harcamaDao(): HarcamaDao

    companion object {

        @Volatile
        private var INSTANCE: HarcamaDatabase? = null

        fun getDatabase(context: Context): HarcamaDatabase {
            val tempInsctance = INSTANCE
            if (tempInsctance != null) {
                return tempInsctance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    HarcamaDatabase::class.java,
                    "urun_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}