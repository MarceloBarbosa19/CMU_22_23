package pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.room.userDatabaseRelations.favoriteDatabase.entitys.Favorite


class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    var readAllData: LiveData<List<Favorite>>
    private var repository: FavoriteRepository

    init {
        val favoriteDao = FavoriteDatabase.getInstance(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
        readAllData = repository.getallFavorite()
    }

    fun insertFavorite(favorite: Favorite){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(favorite)
        }
    }
}