package bo.edu.ucb.cba.data

import bo.edu.ucb.cba.domain.Book

interface ILocalDataSource {
    suspend fun getListOfBooks(): List<Book>
    suspend fun insertBook(book: Book)
}