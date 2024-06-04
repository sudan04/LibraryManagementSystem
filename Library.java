package librarymanagementsystem;

class Book {
    String bookName;
    String author;
    int quantity;

    public Book(String bookName, String author, int quantity) {
        this.bookName = bookName;
        this.author = author;
        this.quantity = quantity;
    }
}

class User {
    String userName;
    int userId;
    Book[] borrowedBooks;
    int borrowCount;

    public User(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
        this.borrowedBooks = new Book[5];
        this.borrowCount = 0;
    }
}

class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}

class BorrowLimitReachedException extends RuntimeException {
    public BorrowLimitReachedException(String message) {
        super(message);
    }
}

public class Library {
    Book[] books = new Book[1000];
    User[] users = new User[500];
    int bookCount = -1;
    int userCount = -1;

    public void addBook(String bookName, String author, int quantity) {
        if (bookCount++ < 1000) {
            books[bookCount] = new Book(bookName, author, quantity);
        } else {
            System.out.println("Can't add more books in the Library");
        }
    }

    public void removeBook(String title) {
        for (int i = 0; i <= bookCount; i++) {
            if (books[i].bookName.equalsIgnoreCase(title)) {
                for (int j = i; j < bookCount; j++) {
                    books[j] = books[j + 1];
                }
                bookCount--;
                System.out.println("Book removed successfully");
                return;
            }
        }
        System.out.println("Book not found in the library");
    }

    public void display() {
        for (int i = 0; i <= bookCount; i++) {
            System.out.println("Title: " + books[i].bookName + ",  Author: " + books[i].author + ",  Quantity: " + books[i].quantity);
        }
    }

    public void findBookByAuthor(String authorName) {
        boolean found = false;
        for (int i = 0; i <= bookCount; i++) {
            if (books[i].author.equals(authorName)) {
                System.out.println("Book found");
                System.out.println("Book Title: " + books[i].bookName + " Author: " + books[i].author);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Book found of " + authorName + " in Library");
        }
    }

    public boolean findAvailability(String title) {
        for (int i = 0; i <= bookCount; i++) {
            if (books[i].bookName.equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    public void addUser(String userName, int userId) {
        if (userCount++ < 500) {
            users[userCount] = new User(userName, userId);
            System.out.println("User added successfully");
        }
    }

    public void removeUser(String uName) {
        for (int i = 0; i <= userCount; i++) {
            if (users[i].userName.equalsIgnoreCase(uName) && users[i].borrowCount == -1) {
                for (int j = i; j < userCount; j++) {
                    users[j] = users[j + 1];
                }
                userCount--;
                System.out.println("User removed..");
                return;
            }
        }
        System.out.println("Can't remove " + uName + " have some borrowed books");
    }

    public void borrowBook(String uName, String bookTitle) {
        User currentUser = null;
        for (int i = 0; i <= userCount; i++) {
            if (users[i].userName.equalsIgnoreCase(uName)) {
                currentUser = users[i];
                break;
            }
        }
        if (currentUser == null) {
            System.out.println("User not found");
            return;
        }

        boolean bookFound = false;
        for (int i = 0; i <= bookCount; i++) {
            if (books[i].bookName.equalsIgnoreCase(bookTitle)) {
                if (books[i].quantity > 0) {
                    books[i].quantity--;
                    bookFound = true;
                }
                if (currentUser.borrowCount < 5) {
                    currentUser.borrowedBooks[currentUser.borrowCount++] = books[i];
                    System.out.println("Book '" + bookTitle + "' borrowed successfully by " + uName);
                    return;
                } else {
                    throw new BorrowLimitReachedException("Maximum borrow limit reached");
                }
            }
        }
        if (!bookFound) {
            throw new BookNotFoundException("Book not found in the library");
        }
    }

    public void returnBook(String uName, String bookTitle) {
        User currentUser = null;
        for (int i = 0; i <= userCount; i++) {
            if (users[i].userName.equalsIgnoreCase(uName)) {
                currentUser = users[i];
                break;
            }
        }
        if (currentUser == null) {
            System.out.println("User not found");
            return;
        }

        boolean bookFound = false;
        for (int i = 0; i < currentUser.borrowCount; i++) {
            if (currentUser.borrowedBooks[i].bookName.equalsIgnoreCase(bookTitle)) {
                for (int j = 0; j <= bookCount; j++) {
                    if (books[j].bookName.equalsIgnoreCase(bookTitle)) {
                        books[j].quantity++;
                        break;
                    }
                }
                for (int k = i; k < currentUser.borrowCount - 1; k++) {
                    currentUser.borrowedBooks[k] = currentUser.borrowedBooks[k + 1];
                }
                currentUser.borrowCount--;
                bookFound = true;

                System.out.println("Book '" + bookTitle + "' returned successfully by " + uName);
                break;
            }
        }
        if (!bookFound) {
            System.out.println("Book '" + bookTitle + "' not found in the borrowed books of " + uName);
        }
    }

    public void displayBorrowedBooksByUser(String uName) {
        User currentUser = null;
        for (int i = 0; i <= userCount; i++) {
            if (users[i].userName.equalsIgnoreCase(uName)) {
                currentUser = users[i];
                break;
            }
        }
        if (currentUser == null) {
            System.out.println("User not found");
            return;
        }

        System.out.println("Books borrowed by " + uName + ":");
        if (currentUser.borrowCount == 0) {
            System.out.println("No books borrowed.");
        } else {
            for (int i = 0; i < currentUser.borrowCount; i++) {
                System.out.println("Book Title: " + currentUser.borrowedBooks[i].bookName + ", Author: " + currentUser.borrowedBooks[i].author);
            }
        }
    }

    public void displayAllUsers() {
        System.out.println("List of all users:");
        for (int i = 0; i <= userCount; i++) {
            System.out.println("User Name: " + users[i].userName + ", User ID: " + users[i].userId);
        }
    }
}
