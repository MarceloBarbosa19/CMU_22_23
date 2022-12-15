package pt.ipp.estg.assistenteviagens.room.userDatabase

import androidx.lifecycle.LiveData
import pt.ipp.estg.assistenteviagens.room.userDatabase.entitys.User

class UserRepository(private val userDao: UserDao) {

    fun getallUser(): LiveData<List<User>>{
        return userDao.loadUsers()
    }

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun updateUser(user:User){
        userDao.updateUser(user)
    }
}