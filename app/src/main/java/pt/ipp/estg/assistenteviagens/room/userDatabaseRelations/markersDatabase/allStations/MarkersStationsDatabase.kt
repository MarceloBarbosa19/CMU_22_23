package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.allStations

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.Marker
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.markersDatabase.oneStation.MarkerDao

@Database(entities = [Marker::class], version = 1)
abstract class MarkersStationsDatabase: RoomDatabase() {
    abstract fun markerDao(): MarkerDao

    companion object {
        private var INSTANCE: MarkersStationsDatabase? = null

        fun getInstance(context: Context): MarkersStationsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MarkersStationsDatabase::class.java,
                        "markers-Database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}