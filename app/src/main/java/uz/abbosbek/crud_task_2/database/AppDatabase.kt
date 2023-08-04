package uz.abbosbek.crud_task_2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.abbosbek.crud_task_2.database.dao.CountryDao
import uz.abbosbek.crud_task_2.database.entity.CountryEntity


@Database(entities = [CountryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDaoFlag(): CountryDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "my_data_flags")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}