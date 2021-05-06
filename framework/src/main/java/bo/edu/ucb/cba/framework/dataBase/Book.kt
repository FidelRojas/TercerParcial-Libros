package bo.edu.ucb.cba.framework.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "book_table")
class Book(@ColumnInfo(name = "title") var title: String, @ColumnInfo(name = "isbn") var isbn: String,@ColumnInfo(name = "author") var author: String,@ColumnInfo(name = "datePublish") var datePublish: String,@ColumnInfo(name = "numberPages") var numberPages: Int,@ColumnInfo(name = "description") var description: String,@ColumnInfo(name = "photoURL") var photoURL: String) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
