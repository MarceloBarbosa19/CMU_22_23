package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.countys

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountysDB::class], version = 2)
abstract class CountysDatabase: RoomDatabase() {
    abstract fun getCountysDao(): CountysDao
    companion object {
        private var INSTANCE: CountysDatabase? = null

        fun getDatabase(context: Context): CountysDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CountysDatabase::class.java,
                    "county-database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}