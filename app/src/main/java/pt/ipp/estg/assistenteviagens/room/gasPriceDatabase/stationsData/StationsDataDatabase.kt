package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsData.entitys.StationsDataDB

@Database(entities = [StationsDataDB::class], version = 7)
abstract class StationsDataDatabase : RoomDatabase() {
    abstract fun getStationsDataDao(): StationsDataDao

    companion object {
        private var INSTANCE: StationsDataDatabase? = null

        fun getDatabase(context: Context): StationsDataDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    StationsDataDatabase::class.java,
                    "info-database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}