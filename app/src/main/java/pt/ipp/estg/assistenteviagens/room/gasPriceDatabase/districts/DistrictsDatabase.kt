package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.districts

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DistrictsDB::class], version = 2)
abstract class DistrictsDatabase: RoomDatabase() {

    abstract fun getDistrictDao(): DistrictsDao

    companion object {
        private var INSTANCE: DistrictsDatabase? = null

        fun getDatabase(context: Context): DistrictsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    DistrictsDatabase::class.java,
                    "districts-database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}