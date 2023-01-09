package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase.entitys.Favorite


@Dao
interface FavoriteDao {
    @Query("select * from Favorite")
    fun loadFavorite(): LiveData<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteAllFavorites(favorite: Favorite)
}