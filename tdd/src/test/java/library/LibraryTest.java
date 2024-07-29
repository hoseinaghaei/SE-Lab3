package library;

import library.classes.Book;
import library.classes.Library;
import library.classes.Student;
import library.classes.SearchByType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book3;
    private Student student1;
    private Student student2;

    @Before
    public void Prepare() {
        library = new Library();
        book1 = new Book("Book-1", "Author-1", 10);
        Book book2 = new Book("Book-2", "Author-2", 11);
        book3 = new Book("Book-3", "Author-1", 12);
        Book book4 = new Book("Book-4", "Author-2", 13);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        student1 = new Student("Alice1", 10);
        student2 = new Student("Alice2", 11);
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

    @Test
    public void searchStudentsById() {
        // Arrange
        ArrayList<Object> keys = new ArrayList<>();
        keys.add(10);
        keys.add(11);
        library.addStudent(student1);
        library.addStudent(student2);

        // Act
        ArrayList<Student> result = library.searchStudents(SearchByType.ID, keys);

        // Assert
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains(student1));
        Assert.assertTrue(result.contains(student2));
    }

    @Test
    public void searchStudentsByName() {
        // Arrange
        ArrayList<Object> keys = new ArrayList<>();
        keys.add("Alice1");
        library.addStudent(student1);
        library.addStudent(student2);

        // Act
        ArrayList<Student> result = library.searchStudents(SearchByType.NAME, keys);

        // Assert
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.contains(student1));
    }

    @Test(expected = InvalidParameterException.class)
    public void searchStudentsByInvalidType() {
        // Arrange
        ArrayList<Object> keys = new ArrayList<>();
        keys.add("Alice1");

        // Act
        library.searchStudents(SearchByType.TITLE, keys);
    }

    @Test
    public void searchBooksById() {
        // Arrange
        ArrayList<Object> keys = new ArrayList<>();
        keys.add(10);
        keys.add(12);

        // Act
        ArrayList<Book> result = library.searchBooks(SearchByType.ID, keys);

        // Assert
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains(book1));
        Assert.assertTrue(result.contains(book3));
    }

    @Test
    public void searchBooksByTitle() {
        // Arrange
        ArrayList<Object> keys = new ArrayList<>();
        keys.add("Book-1");

        // Act
        ArrayList<Book> result = library.searchBooks(SearchByType.TITLE, keys);

        // Assert
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.contains(book1));
    }

    @Test
    public void searchBooksByAuthor() {
        // Arrange
        ArrayList<Object> keys = new ArrayList<>();
        keys.add("Author-1");

        // Act
        ArrayList<Book> result = library.searchBooks(SearchByType.AUTHOR, keys);

        // Assert
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains(book1));
        Assert.assertTrue(result.contains(book3));
    }

    @Test(expected = InvalidParameterException.class)
    public void searchBooksByInvalidType() {
        // Arrange
        ArrayList<Object> keys = new ArrayList<>();
        keys.add("Alice1");

        // Act
        library.searchBooks(SearchByType.NAME, keys);
    }

    @Test
    public void searchStudentsInEmptyLibrary() {
        // Arrange
        Library emptyLibrary = new Library();
        ArrayList<Object> keys = new ArrayList<>();
        keys.add(10);

        // Act
        ArrayList<Student> result = emptyLibrary.searchStudents(SearchByType.ID, keys);

        // Assert
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void searchBooksInEmptyLibrary() {
        // Arrange
        Library emptyLibrary = new Library();
        ArrayList<Object> keys = new ArrayList<>();
        keys.add(10);

        // Act
        ArrayList<Book> result = emptyLibrary.searchBooks(SearchByType.ID, keys);

        // Assert
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void lendBookNotINLibrary() {
        // Arrange
        var book5 = new Book("Book-5", "Author-1", 14);

        // Act
        var result = library.lendBook(book5, student1);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void returnBookThatNotHave() {
        // Arrange
        var book5 = new Book("Book-5", "Author-1", 14);
        library.addStudent(student1);

        // Act
        var result = library.returnBook(book5, student1);

        // Assert
        Assert.assertFalse(result);
    }
}
