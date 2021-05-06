package bo.edu.ucb.cba.framework.dataBase
import bo.edu.ucb.cba.domain.Book as DomainBook
import bo.edu.ucb.cba.framework.dataBase.Book as LocalBook


fun LocalBook.toDomainBook(): DomainBook {
    return DomainBook(title,isbn,author, datePublish,numberPages,description,photoURL)
}
fun DomainBook.toLocalBook(): LocalBook {
    return LocalBook(title,isbn,author, datePublish,numberPages,description,photoURL)
}