package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.brands

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BrandsDB::class], version = 2)
abstract class BrandsDatabase: RoomDatabase() {

    abstract fun getBrandsDao(): BrandsDao

    companion object {
        private var INSTANCE: BrandsDatabase? = null

        fun getDatabase(context: Context): BrandsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    BrandsDatabase::class.java,
                    "brands-database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}