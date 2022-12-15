package pt.ipp.estg.assistenteviagens.room.userDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pt.ipp.estg.assistenteviagens.room.userDatabase.entitys.User

@Dao
interface UserDao {
    @Query("select * from User")
    fun loadUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}