package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.stationsSearch

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ipp.estg.assistenteviagens.retrofit.stationsSearch.StationsSearch

@Database(entities = [StationsSearchDB::class], version = 3)
abstract class StationsSearchDatabase : RoomDatabase(){

    abstract fun getStationsSearchDao(): StationsSearchDao

    companion object {
        private var INSTANCE: StationsSearchDatabase? = null

        fun getDatabase(context: Context): StationsSearchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    StationsSearchDatabase::class.java,
                    "pesquisa-database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}