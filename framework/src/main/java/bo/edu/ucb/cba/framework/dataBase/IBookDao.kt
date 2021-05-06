package bo.edu.ucb.cba.framework.dataBase
import androidx.room.*
import bo.edu.ucb.cba.framework.dataBase.Book

@Dao
interface IBookDao {

    @Query("SELECT * FROM book_table")
    fun getList(): List<Book>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM book_table")
    suspend fun deleteAll()


    @Update
    suspend fun editBook(book:Book)

    @Delete
    suspend fun deleteBook(book:Book)
}
