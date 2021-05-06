package bo.edu.ucb.cba.usecases

import bo.edu.ucb.cba.data.BooksRepository

class DeleteAllBooks (val repository: BooksRepository){
    suspend fun invoke() = repository.deleteAllBooks()
}