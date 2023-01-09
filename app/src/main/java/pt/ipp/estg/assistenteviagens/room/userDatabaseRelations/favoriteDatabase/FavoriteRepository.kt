package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase.entitys.Favorite

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    fun getallFavorite(): LiveData<List<Favorite>> {
        return favoriteDao.loadFavorite()
    }

    suspend fun insertFavorite(favorite: Favorite){
        favoriteDao.insertFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        favoriteDao.deleteAllFavorites(favorite)
    }
}