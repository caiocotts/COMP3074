package ca.gbc.comp3074.lab4

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(
    entities = [Customer::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDAO(): CustomerDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}