package librarymanagementsystem;


import java.util.Scanner;

public class program {

    public static void libraryOperations(Library lib) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Book \n2. Remove Book \n3. Display \n4. Find Book by Author \n5. Find availability of book \n6.Exit ");
            System.out.println("Enter your choice:");
            int ch = scan.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Enter Book title");
                    scan.nextLine();
                    String bookName = scan.nextLine();

                    System.out.println("Enter Author of " + bookName);
                    String author = scan.nextLine();

                    System.out.println("Enter quantity of book");
                    int quantity = scan.nextInt();

                    lib.addBook(bookName, author, quantity);
                    break;

                case 2:
                    System.out.println("Enter BookName which you want to remove");
                    scan.nextLine();
                    bookName = scan.nextLine();
                    lib.removeBook(bookName);
                    break;

                case 3:
                    lib.display();
                    break;

                case 4:
                    scan.nextLine();
                    System.out.println("Enter author name to find their books");
                    author = scan.nextLine();
                    lib.findBookByAuthor(author);
                    break;

                case 5:
                    System.out.println("Enter Bookname to check its availability");
                    scan.nextLine();
                    bookName = scan.nextLine();
                    if (lib.findAvailability(bookName)) {
                        System.out.println("Book is available");
                    } else {
                        System.out.println("Book is not available");
                    }
                    break;

                case 6:
                    System.out.println("Exiting the program!!");
                    return;

                default:
                    System.out.println("Invalid choice!! please enter a valid choice of menu");
            }
        }
    }

    public static void userOperations(Library lib) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.printf("\n1. Add User \n2. Remove User \n3. Borrow Book \n4. Return Book \n5. Dispaly Books borrowed by a user \n6. Dispaly all users \n7. Exit\n");
            System.out.println("Enter your choice");

            int ch = scan.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter Name of the User:");
                    scan.nextLine();
                    String userName = scan.nextLine();

                    System.out.println("Enter userId:");
                    int userId = scan.nextInt();

                    lib.addUser(userName, userId);
                    break;

                case 2:
                    System.out.println("Enter name of the user to remove");
                    scan.nextLine();
                    userName = scan.nextLine();
                    lib.removeUser(userName);
                    break;

                case 3:
                    System.out.println("Enter User Name");
                    scan.nextLine();
                    userName = scan.nextLine();
                    System.out.println("Enter Title of the book to borrow");
                    String bookTitle = scan.nextLine();
                    lib.borrowBook(userName, bookTitle);
                    break;

                case 4:
                    System.out.println("Enter User Name");
                    scan.nextLine();
                    userName = scan.nextLine();
                    System.out.println("Enter Title of the book to return");
                    bookTitle = scan.nextLine();
                    lib.returnBook(userName, bookTitle);
                    break;

                case 5:
                    System.out.println("Enter user name to find the borrowed books");
                    scan.nextLine();
                    userName = scan.nextLine();
                    lib.displayBorrowedBooksByUser(userName);
                    break;

                case 6:
                    lib.displayAllUsers();
                    break;

                case 7:
                    return;

                default:
                    System.out.println("Please enter a valid choice!!");
            }
        }
    }

    public static void main(String[] args) {
        Library lib = new Library();

        // Adding some books in library before
        lib.addBook("Billenium", "J.G. Ballard", 5);
        lib.addBook("Land Ironclads", "H.G. Wells", 10);
        lib.addBook("The Metal Man", "Jack Williamson", 6);
        lib.addBook("Burning Chrome", "William Gibson", 8);

        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("Enter Operation:\n1. Library\n2. User\n3. Exit");
            int operation = scan.nextInt();

            switch (operation) {
                case 1:
                    libraryOperations(lib);
                    break;
                case 2:
                    userOperations(lib);
                    break;
                case 3:
                    System.out.println("Exiting the program");
                    return;
                default:
                    System.out.println("Invalid operation choice.");
            }
        }
    }
}
