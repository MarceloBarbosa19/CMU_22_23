package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.topStations

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TopStationsDB::class], version = 1)
abstract class TopStationsDatabase : RoomDatabase() {

    abstract fun getTopStationsDao(): TopStationsDao

    companion object {
        private var INSTANCE: TopStationsDatabase? = null

        fun getDatabase(context: Context): TopStationsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TopStationsDatabase::class.java,
                    "topStations-database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}