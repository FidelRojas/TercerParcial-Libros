package bo.edu.ucb.cba.domain

data class Book(var id:Long, var title: String, var isbn: String, var author: String, var datePublish: String, var numberPages: Int, var description :String, var photoURL:String)