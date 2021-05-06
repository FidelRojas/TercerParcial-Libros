package bo.edu.ucb.cba.usecases

import bo.edu.ucb.cba.data.BooksRepository
import bo.edu.ucb.cba.domain.Book

class EditBook (val repository: BooksRepository){
    suspend fun invoke(book: Book) = repository.editBook(book)
}