package library;

import library.classes.Book;
import library.classes.Library;
import library.classes.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Student student1;
    private Student student2;

    @Before
    public void Prepare() {
        library = new Library();
        book1 = new Book("Book-1", "Author-1", 10);;
        book2 = new Book("Book-2", "Author-2", 11);
        library.addBook(book1);
        library.addBook(book2);
        student1 = new Student("Alice", 10);
        student2 = new Student("Alice", 10);
    }

    @Test
    public void bookShouldNotBeLentToStudentThatIsNotInLibrary() {
        //Arrange
        library.addStudent(student1);

        //Act
        library.lendBook(book1, student2);

        //Assert
        Assert.assertFalse(student2.hasBook(book1));
    }

    @Test
    public void bookShouldBeRemovedFromStudentAfterReturning() {
        //Arrange
        library.addStudent(student1);
        library.lendBook(book1, student1);

        //Act
        library.returnBook(book1, student1);

        //Assert
        Assert.assertFalse(student1.hasBook(book1));
    }

}
