package Task16;
import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean checkedOut;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.checkedOut = false;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    
    public String toString() {
        return title + " by " + author;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }




    public void addBook(Book book) {
        for (Book existingBook : books) {
            if (existingBook.getIsbn().equals(book.getIsbn())) {
                throw new IllegalArgumentException("This book already exists in the library with the same ISBN.");
            }
        }
        books.add(book);
    }




    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }




    public void checkOutBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            if (book.isCheckedOut()) {
                throw new IllegalArgumentException("This book is already checked out.");
            } else {
                book.setCheckedOut(true);
                System.out.println("Checked out: " + book.toString());
            }
        } else {
            throw new IllegalArgumentException("Book with the provided ISBN not found in the library.");
        }
    }





    public void returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            if (book.isCheckedOut()) {
                book.setCheckedOut(false);
                System.out.println("Returned: " + book.toString());
            } else {
                throw new IllegalArgumentException("This book has not been checked out.");
            }
        } else {
            throw new IllegalArgumentException("Book with the provided ISBN not found in the library.");
        }
    }
}


public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        
        Book book1 = new Book("this is book1", "sumadeep", "1234");
        Book book2 = new Book("this is book2", "charan", "5678");
     
     
        library.addBook(book1);
        library.addBook(book2);

        try {
            
            Book duplicateBook = new Book("this is Duplicate Book", "deepak", "1234");
            library.addBook(duplicateBook);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

       
        library.checkOutBook("1234");
        library.checkOutBook("5678");



        try {
           
            library.checkOutBook("1234");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        library.returnBook("1234");       

        try {
           
            library.returnBook("1234");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
