package bo.edu.ucb.cba.tercerparciallibros.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bo.edu.ucb.cba.data.BooksRepository
import bo.edu.ucb.cba.domain.Book
import bo.edu.ucb.cba.framework.dataBase.BookDataSource
import bo.edu.ucb.cba.framework.dataBase.BookDatabase
import bo.edu.ucb.cba.tercerparciallibros.R
import bo.edu.ucb.cba.tercerparciallibros.adapters.BookAdapter
import bo.edu.ucb.cba.tercerparciallibros.viewModel.ConfigurationViewModel
import bo.edu.ucb.cba.usecases.AddBook
import bo.edu.ucb.cba.usecases.GetBooks

class ListBooks : AppCompatActivity() {
    lateinit var configurationViewModel: ConfigurationViewModel
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_books)

        val bookDao = BookDatabase.getDatabase(applicationContext).bookDao()
        val getBooks = GetBooks(BooksRepository((BookDataSource(bookDao))))
        val addBook = AddBook(BooksRepository((BookDataSource(bookDao))))

        configurationViewModel = ConfigurationViewModel(getBooks, addBook )
        recyclerView = findViewById(R.id.recyclerViewBooks)

        val layoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = layoutManager

        configurationViewModel.model.observe(this, Observer(::updateUi))
        configurationViewModel.loadBooks()

        val newBook = Book("Libro1","Hola","author","12/12/12",100,"descripcion","https://www.laimprentacg.com/wp-content/uploads/2012/09/Las-partes-de-un-libro-foto-cabecera.jpg")
        configurationViewModel.insertBooks(newBook)

    }

    private fun updateUi(uiModel: ConfigurationViewModel.UiModel) {
        when( uiModel) {
            is ConfigurationViewModel.UiModel.Content -> loadBooks( uiModel.books)
        }
    }

    private fun loadBooks(movies: List<Book>) {
        recyclerView.adapter = BookAdapter(movies)
    }
}