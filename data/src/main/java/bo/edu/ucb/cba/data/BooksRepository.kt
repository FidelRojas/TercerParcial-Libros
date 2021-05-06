package bo.edu.ucb.cba.data

import bo.edu.ucb.cba.domain.Book

class BooksRepository (val localDataSource: ILocalDataSource) {

    suspend fun getBooks():List<Book>{
        return localDataSource.getListOfBooks()
    }
    suspend fun insertBook(book: Book){
        localDataSource.insertBook(book)
    }

}