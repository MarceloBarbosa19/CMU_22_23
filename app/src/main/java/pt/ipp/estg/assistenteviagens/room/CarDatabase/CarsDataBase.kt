package pt.ipp.estg.assistenteviagens.room.CarDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ipp.estg.assistenteviagens.room.CarDatabase.entity.Car

@Database(entities = [Car::class], version = 1)
abstract class CarsDataBase : RoomDatabase() {

    abstract fun getCarsDao(): CarsDao

    companion object {
        private var INSTANCE: CarsDataBase? = null

        fun getDatabase(context: Context): CarsDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CarsDataBase::class.java,
                    "cars-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}