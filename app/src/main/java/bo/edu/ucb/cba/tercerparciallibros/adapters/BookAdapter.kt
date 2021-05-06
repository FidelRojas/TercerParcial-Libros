package bo.edu.ucb.cba.tercerparciallibros.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import bo.edu.ucb.cba.data.BooksRepository
import bo.edu.ucb.cba.domain.Book
import bo.edu.ucb.cba.framework.dataBase.BookDataSource
import bo.edu.ucb.cba.framework.dataBase.BookDatabase
import bo.edu.ucb.cba.tercerparciallibros.R
import bo.edu.ucb.cba.tercerparciallibros.activities.ListBooks
import bo.edu.ucb.cba.tercerparciallibros.viewModel.ConfigurationViewModel
import bo.edu.ucb.cba.usecases.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_book_listbooks.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookAdapter (val list: List<Book>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    public lateinit var bookClick:Book
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.row_book_listbooks, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
    lateinit var configurationViewModel: ConfigurationViewModel

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = list.get(position)
        holder.itemView.findViewById<TextView>(R.id.txtTittle).text = book.title
        holder.itemView.findViewById<TextView>(R.id.txtIsbn).text = book.isbn
        holder.itemView.findViewById<TextView>(R.id.txtAuthor).text = book.author
        holder.itemView.findViewById<TextView>(R.id.txtDatePublish).text = book.datePublish
        holder.itemView.findViewById<TextView>(R.id.txtNumberPages).text = book.numberPages.toString()
        holder.itemView.findViewById<TextView>(R.id.txtDescription).text = book.description
        Picasso.get().load(book.photoURL).into(holder.itemView.ImageViewBook)

        val context= holder.itemView.context

        val Intent = Intent(context, ListBooks::class.java)

        val bookDao = BookDatabase.getDatabase(context).bookDao()
        val getBooks = GetBooks(BooksRepository((BookDataSource(bookDao))))
        val addBook = AddBook(BooksRepository((BookDataSource(bookDao))))
        val edit = EditBook(BooksRepository((BookDataSource(bookDao))))
        val deleteBook = DeleteBook(BooksRepository((BookDataSource(bookDao))))
        val deleteAllBooks = DeleteAllBooks(BooksRepository((BookDataSource(bookDao))))

        configurationViewModel = ConfigurationViewModel(getBooks, addBook, edit, deleteBook , deleteAllBooks)



        holder.itemView.findViewById<Button>(R.id.btnEliminar).setOnClickListener {

            //bookClick= list[position]
            configurationViewModel.deleteBooksDB(book)
            //configurationViewModel.insertBooks(book)
            //configurationViewModel.deleteAllBooksDB()
            val intent = Intent( context, ListBooks::class.java)
            context.startActivity(intent)
        }
    }

}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)
