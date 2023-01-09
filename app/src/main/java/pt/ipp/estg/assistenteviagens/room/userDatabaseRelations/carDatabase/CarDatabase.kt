package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.carDatabase.entitys.Car

@Database(entities = [Car::class], version = 2)
abstract class CarDatabase: RoomDatabase() {
    abstract fun carDao(): CarDao

    companion object {
        private var INSTANCE: CarDatabase? = null

        fun getInstance(context: Context): CarDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CarDatabase::class.java,
                        "cars-Database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}