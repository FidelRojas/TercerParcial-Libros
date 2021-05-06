package bo.edu.ucb.cba.framework.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Book::class), version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {
    abstract fun bookDao(): IBookDao

    companion object {
        private var INSTANCE : BookDatabase? = null

        fun getDatabase(context: Context) : BookDatabase {
            val tempInstance = INSTANCE
            if ( tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, BookDatabase::class.java, "db_examen3").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}

