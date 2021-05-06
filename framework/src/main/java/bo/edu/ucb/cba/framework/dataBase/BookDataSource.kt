package bo.edu.ucb.cba.framework.dataBase

import bo.edu.ucb.cba.data.ILocalDataSource
import bo.edu.ucb.cba.domain.Book


class BookDataSource(private val bookDao: IBookDao): ILocalDataSource {
    override suspend fun getListOfBooks(): List<Book> {
        val response=bookDao.getList().map { it.toDomainBook() }
        return response
    }

    override suspend fun insertBook(book: Book) {
        bookDao.insert(book.toLocalBook())
    }


}
