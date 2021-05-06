package bo.edu.ucb.cba.usecases

import bo.edu.ucb.cba.data.BooksRepository
import bo.edu.ucb.cba.domain.Book

class GetBooks (val repository: BooksRepository){
    suspend fun invoke(): List<Book> {
        return repository.getBooks()
    }
}