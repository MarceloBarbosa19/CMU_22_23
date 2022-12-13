package pt.ipp.estg.assistenteviagens.room.userDatabase

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    fun getallUser(): LiveData<List<User>>{
        return userDao.loadUsers()
    }

    suspend fun insertUser(user: User){
        return userDao.insertUser(user)
    }

}