package pt.ipp.estg.assistenteviagens.room.UserDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ipp.estg.assistenteviagens.room.UserDatabase.entity.User

@Dao
interface UserDao {
    @Query("select * from User")
    fun loadUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)


}