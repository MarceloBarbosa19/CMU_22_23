package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Marker::class], version = 1)
abstract class MarkerDatabase: RoomDatabase() {
    abstract fun markerDao(): MarkerDao

    companion object {
        private var INSTANCE: MarkerDatabase? = null

        fun getInstance(context: Context): MarkerDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MarkerDatabase::class.java,
                        "marker-Database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}