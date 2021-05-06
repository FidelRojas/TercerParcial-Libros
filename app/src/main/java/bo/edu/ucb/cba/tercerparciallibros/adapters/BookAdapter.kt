package bo.edu.ucb.cba.tercerparciallibros.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import bo.edu.ucb.cba.domain.Book
import bo.edu.ucb.cba.tercerparciallibros.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_book_listbooks.view.*

class BookAdapter (val list: List<Book>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.row_book_listbooks, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = list.get(position)
        holder.itemView.findViewById<TextView>(R.id.txtTittle).text = book.title
        holder.itemView.findViewById<TextView>(R.id.txtIsbn).text = book.isbn
        holder.itemView.findViewById<TextView>(R.id.txtAuthor).text = book.author
        holder.itemView.findViewById<TextView>(R.id.txtDatePublish).text = book.datePublish
        holder.itemView.findViewById<TextView>(R.id.txtNumberPages).text = book.numberPages.toString()
        holder.itemView.findViewById<TextView>(R.id.txtDescription).text = book.description

        holder.itemView.findViewById<ImageView>(R.id.ImageViewBook)
        Picasso.get().load(book.photoURL).into(holder.itemView.ImageViewBook)



    }
}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view)
