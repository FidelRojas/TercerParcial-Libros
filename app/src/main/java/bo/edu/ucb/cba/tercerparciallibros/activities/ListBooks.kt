package bo.edu.ucb.cba.tercerparciallibros.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
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
import bo.edu.ucb.cba.usecases.*
import kotlinx.android.synthetic.main.activity_list_books.*
import kotlinx.android.synthetic.main.add_book_dialog.view.*

class ListBooks : AppCompatActivity() {
    lateinit var configurationViewModel: ConfigurationViewModel
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_books)

        val bookDao = BookDatabase.getDatabase(applicationContext).bookDao()
        val getBooks = GetBooks(BooksRepository((BookDataSource(bookDao))))
        val addBook = AddBook(BooksRepository((BookDataSource(bookDao))))
        val edit = EditBook(BooksRepository((BookDataSource(bookDao))))
        val deleteBook = DeleteBook(BooksRepository((BookDataSource(bookDao))))
        val deleteAllBooks = DeleteAllBooks(BooksRepository((BookDataSource(bookDao))))

        configurationViewModel = ConfigurationViewModel(getBooks, addBook, edit, deleteBook , deleteAllBooks)
        recyclerView = findViewById(R.id.recyclerViewBooks)

        val layoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = layoutManager

        configurationViewModel.model.observe(this, Observer(::updateUi))
        configurationViewModel.loadBooks()

        addButton()



    }

    private fun updateUi(uiModel: ConfigurationViewModel.UiModel) {
        when( uiModel) {
            is ConfigurationViewModel.UiModel.Content -> loadBooks( uiModel.books)
        }
    }

    private fun loadBooks(movies: List<Book>) {
        recyclerView.adapter = BookAdapter(movies)
    }

    private fun addButton(){
        floatingActionButtonAdd.setOnClickListener {
            val newBookDialogView = LayoutInflater.from(this).inflate(R.layout.add_book_dialog,null);
            val newBookBuilder = AlertDialog.Builder(this)
                    .setView(newBookDialogView)
                    .setTitle("Nuevo Libro")
            val newPlayerAlertDialog = newBookBuilder.show()
            newBookDialogView.btnDialogEdit.setOnClickListener{
                newPlayerAlertDialog.dismiss()
                val titel = newBookDialogView.txtInputTitelEdit.text.toString()
                val isbn = newBookDialogView.txtInputIsbnEdit.text.toString()
                val author = newBookDialogView.txtInputAuthEdit.text.toString()
                val datePublish = newBookDialogView.txtInputDateEdit.text.toString()
                val numberPages = newBookDialogView.txtInputNumberPagesEdit.text.toString().toInt()
                val description = newBookDialogView.txtInputDescriptionEdit.text.toString()
                val url = newBookDialogView.txtInputUrlEdit.text.toString()

                val newBook = Book(0,titel,isbn,author,datePublish,numberPages,description,url)
                configurationViewModel.insertBooks(newBook)
            }
            newBookDialogView.btnDialogCancel.setOnClickListener {
                newPlayerAlertDialog.dismiss()
            }
        }
    }
}