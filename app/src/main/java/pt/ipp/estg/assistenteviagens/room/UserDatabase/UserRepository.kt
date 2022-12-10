package pt.ipp.estg.assistenteviagens.room.UserDatabase

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.room.UserDatabase.entity.User

class UserRepository(private val userDao: UserDao) {

    fun getallUser(): LiveData<List<User>>{
        return userDao.loadUsers()
    }

    suspend fun insertUser(user: User){
        return userDao.insertUser(user)
    }

}