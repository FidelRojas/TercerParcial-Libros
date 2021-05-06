package bo.edu.ucb.cba.framework.dataBase

import android.content.ContentValues.TAG
import android.util.Log
import bo.edu.ucb.cba.data.ILocalDataSource
import bo.edu.ucb.cba.domain.Book


class BookDataSource(private val bookDao: IBookDao): ILocalDataSource {
    override suspend fun getListOfBooks(): List<Book> {
        val list =bookDao.getList()


        val response=bookDao.getList().map { it.toDomainBook() }

        response.forEachIndexed { index, element ->
            response[index].id=list[index].id
        }
        return response
    }

    override suspend fun insertBook(book: Book) {
        bookDao.insert(book.toLocalBook())
    }

    override suspend fun editBook(book: Book) {
        bookDao.editBook(book.toLocalBook())
    }

    override suspend fun deleteBook(book: Book) {
        var a =book.toLocalBook()
        a.id=book.id
        Log.d(TAG, "deleteBook:"+a.id)

        bookDao.deleteBook(a)
    }

    override suspend fun deleteAllBooks() {
        bookDao.deleteAll()
    }



}
