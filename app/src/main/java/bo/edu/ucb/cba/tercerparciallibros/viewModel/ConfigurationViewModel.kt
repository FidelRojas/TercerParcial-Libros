package bo.edu.ucb.cba.tercerparciallibros.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import bo.edu.ucb.cba.domain.Book
import bo.edu.ucb.cba.usecases.AddBook
import bo.edu.ucb.cba.usecases.GetBooks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfigurationViewModel(val getBooks: GetBooks,val addBook: AddBook) : ScopedViewModel() {

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
            _model.postValue(UiModel.Content(getBooks.invoke()))
        }

    }

    fun  insertBooks(player : Book) {
        launch (Dispatchers.IO){
            addBook.invoke(player)
            loadBooks()
        }

    }
}