package pt.ipp.estg.assistenteviagens.room.UserDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ipp.estg.assistenteviagens.room.UserDatabase.entity.User

class UserViewModel(application: Application): AndroidViewModel(application) {

    var readAllData: LiveData<List<User>>
    private var repository: UserRepository

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.getallUser()
    }

    fun insertUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }

}