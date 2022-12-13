package pt.ipp.estg.assistenteviagens.room.gasPriceDatabase.gasType

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GasTypeDB::class], version = 2)
abstract class GasTypeDatabase : RoomDatabase() {

    abstract fun getGasTypeDao(): GasTypeDao

    companion object {
        private var INSTANCE: GasTypeDatabase? = null

        fun getDatabase(context: Context): GasTypeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    GasTypeDatabase::class.java,
                    "gasType-database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}