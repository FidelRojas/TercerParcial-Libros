package bo.edu.ucb.cba.tercerparciallibros.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bo.edu.ucb.cba.domain.Book
import bo.edu.ucb.cba.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfigurationViewModel(val getBooks: GetBooks,val addBook: AddBook, val editBook: EditBook,val deleteBook: DeleteBook,val deleteAllBooks: DeleteAllBooks) : ScopedViewModel() {

    init {
        initScope()
    }

    private val _model = MutableLiveData<UiModel>()

    val model: LiveData<UiModel>
        get() = _model

    sealed class UiModel{
        class Content(val books: List<Book>): UiModel()
    }

    fun  loadBooks() {
        launch (Dispatchers.IO){
            val a =getBooks.invoke()
            _model.postValue(UiModel.Content(a))
        }

    }

    fun  insertBooks(book : Book) {
        launch (Dispatchers.IO){
            addBook.invoke(book)
            loadBooks()
        }
    }

    fun  editBooks(book : Book) {
        launch (Dispatchers.IO){
            editBook.invoke(book)
            loadBooks()
        }
    }

    fun  deleteBooksDB(book : Book) {
        launch (Dispatchers.IO){
            deleteBook.invoke(book)
            Log.d(TAG, "deleteBosoksDB: "+book.id)
            loadBooks()
        }
    }

    fun  deleteAllBooksDB() {
        launch (Dispatchers.IO){
            deleteAllBooks.invoke();
            loadBooks()
        }
    }
}