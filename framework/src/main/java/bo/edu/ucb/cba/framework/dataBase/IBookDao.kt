package bo.edu.ucb.cba.framework.dataBase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import bo.edu.ucb.cba.framework.dataBase.Book

@Dao
interface IBookDao {

    @Query("SELECT * FROM book_table")
    fun getList(): List<Book>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM book_table")
    suspend fun deleteAll()
}
