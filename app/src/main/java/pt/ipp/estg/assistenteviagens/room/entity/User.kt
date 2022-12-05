package pt.ipp.estg.assistenteviagens.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    /* room nao guarda imagens */
    val name: String,
    val description: String,
    val cars: List<Cars>,
    val email: String,
    val password: String,
) {
    @PrimaryKey(autoGenerate = true)
    var user_id: Int = 0
}