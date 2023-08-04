package uz.abbosbek.crud_task_2.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val countryName: String? = null,
    val flagImage: String? = null,
    val area: String? = null,
    val region: String? = null,
    val population: String? = null
)
