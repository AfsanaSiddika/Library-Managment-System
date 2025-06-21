import javax.swing.*;
import java.util.ArrayList;

// Created an abstract class
abstract class StudentInfo{

    public abstract void printTheStudentInfo();

}

// Extended the abstract StudentInfo class
class LibraryMember extends StudentInfo {
    private String stuName;
    private String stuId;
    private String dep;
    private String seme;

    // Encapsulation: Getter and Setter methods for private fields
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getSeme() {
        return seme;
    }

    public void setSemi(String seme) {
        this.seme = seme;
    }
    //create a constructor
    public LibraryMember(String stuName, String stuId, String dep, String seme) {
        this.stuName = stuName;
        this.stuId = stuId;
        this.dep = dep;
        this.seme = seme;
    }

    // the abstract methode of StudentInfo class is overridden
    @Override public void printTheStudentInfo(){
//        System.out.println();
        JOptionPane.showMessageDialog(null, "Student name :" +stuName+ "\nStudent ID :" +stuId+ "\nDepartment name :" +dep+ "\nSemester :" +seme);
    }
}
// Created an interface class
interface LibraryItems{
    public Book findBook(String bookTitle, String author);
    public void borrow(String bookTitle, String author, int noOfCopies, String tokenNum);
    public void returnBook(String tokenNum,int noOfCopies);
    public void display();
}

// created a Book class
class Book {
    private String bookTitle;
    private String bookAuthor;
    private int numOfCopies;
    private String tokenNo;

    public Book(String bookTitle, String bookAuthor, int numOfCopies,String tokenNo) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.numOfCopies = numOfCopies;
        this.tokenNo = tokenNo;
    }
    // Encapsulation: Getter and Setter methods for private fields
    public String getBookTitle() {
        return bookTitle;    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;   }
    public String getBookAuthor() {
        return bookAuthor;  }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;   }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }
    public int getNumOfCopies() {
        return numOfCopies;   }
    public void setNumOfCopies(int numOfCopies) {
        this.numOfCopies = numOfCopies;   }
    public int display() {
//        System.out.println();
        JOptionPane.showMessageDialog(null, "Title :"+ bookTitle +"\nAuthor :" + bookAuthor +"\nQuantity :" + numOfCopies + "\n Token number :"+tokenNo);
        return 0;  }
}

// Library class has implemented interface class LibraryItems
class Library  implements LibraryItems{

    // Created an array list of Book type to handle multiple objects
    public ArrayList<Book> books = new ArrayList<Book>();

    public void addNewBook(Book b){
        this.books.add(b);              // taking an object of type Book as a parameter and adds it to the books list

    }

    // overloading the addNewBook method
    public void addNewBook(String bookTitle,String bookAuthor,int numOfCopies,String tokenNo) {

        Book b = new Book(bookTitle,bookAuthor,numOfCopies,tokenNo);

        this.addNewBook(b);    // Calling the first addNewBook method by passing the newly created Book object as a parameter
    }

    ////  The methods of interface class are overridden

    // to find a specific book through id
    @Override
    public Book findBook(String bookTitle, String author) {

        for (Book b : this.books) {
            if(b.getBookTitle().equals(bookTitle) && b.getBookAuthor().equals(author))      // Through the e reference type calling getId method of Book class
            {
//                    System.out.println("This book is available in library");
                JOptionPane.showMessageDialog(null, "This book is available in library");

                return b;
            }
        }
//            System.out.println("The book is not found !!");
        JOptionPane.showMessageDialog(null, "Sorry, The book is not found !!");

        return null;
    }
    @Override
    public void borrow(String bookTitle, String author, int noOfCopies, String tokenNum) {

        Book b = findBook(bookTitle, author);

        if (b!= null) {
            if (b.getNumOfCopies() >= noOfCopies) {
                int currentCopies = b.getNumOfCopies();
                b.setNumOfCopies(currentCopies - noOfCopies);
                JOptionPane.showMessageDialog(null, "Book borrowed successfully");

                return;
            } else {
                JOptionPane.showMessageDialog(null, "Sorry, we do not have enough copies of this book");

            }
        }
//            System.out.println("Sorry, the requested book is not available");
        JOptionPane.showMessageDialog(null, "Sorry, the requested book is not available");

    }

    // to return a book to library
    @Override
    public void returnBook(String tokenNum, int noOfCopies) {

        for (Book b : this.books) {
            if (b.getTokenNo().equals(tokenNum)) {
                b.setNumOfCopies(b.getNumOfCopies() + noOfCopies);
                JOptionPane.showMessageDialog(null, " returned successfully." +tokenNum);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "The book is not found!!" +tokenNum);

    }


    // to remove book from library stock
    public Book toRemoveBook(String bookId) {
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            if (b.getTokenNo().equals(bookId)) {
                books.remove(i);
                JOptionPane.showMessageDialog(null, "Book removed successfully.");
                return b;
            }
        }
        JOptionPane.showMessageDialog(null, "This book is not available.");
        return null;
    }



    //display all books library have along with details
    public void display() {

        if (books.size() == 0) {
            JOptionPane.showMessageDialog(null, "There are no books available in the library.");

        } else {
            JOptionPane.showMessageDialog(null, "List of books available in the library:");

            for (Book b : books) {
                b.display();
            }
        }
    }
}
public class Library_Management_System {
    public static void main(String[] args) {

        Library l = new Library();       // Created an object of library class

        ArrayList<LibraryMember> members = new ArrayList<>();     // Created an arraylist to handle library members

        loop:
        while (true){
            String option = JOptionPane.showInputDialog(null,"Enter -" +
                    "\n ‘1’ to add a new Library member." +
                    "\n ‘2’ to add new books to library stock." +
                    "\n ‘3’ to display the list of Library members." +
                    "\n ‘4’ to cancel the library Membership " +
                    "\n ‘5’ to display the list of books." +
                    "\n ‘6’ to find a book." +
                    "\n ‘7’ to borrow book from library stock." +
                    "\n ‘8’ to return borrowed books." +
                    "\n ‘9’ to remove a book from library stock " +
                    "\n '0' to exit the system ");

            switch (option){


                case "1":   // add a new Library member
                    String stuName = "";
                    try {
                        boolean checkingName = true;
                        while(checkingName){
                            stuName = JOptionPane.showInputDialog("Enter the name of Student :");
                            try{
                                for(int i = 0 ;i < stuName.length(); i++){
                                    if(stuName.charAt(i) < 57){
                                        throw new NumberFormatException();
                                    }
                                }
                                checkingName = false;
                            } catch (NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "Name can not contain numbers!!");
                            }
                        }
                        String stuId = "";

                        boolean checkingid = true;
                        while(checkingid){
                            stuId = JOptionPane.showInputDialog("Enter id of Student :");
                            try{
                                for(int i = 0 ;i < stuId.length(); i++){
                                    if(stuId.charAt(i) > 57){
                                        throw new NumberFormatException();
                                    }
                                }
                                checkingid = false;
                            } catch (NumberFormatException e){
                                JOptionPane.showMessageDialog(null, "Id can not contain character!!");
                            }
                        }
                        String dep = JOptionPane.showInputDialog("Enter the department of Student :");
                        String seme = JOptionPane.showInputDialog("Enter the semester of Student :");

                        //// Created an object of LibraryMember class and added to the members ////
                        LibraryMember lm = new LibraryMember(stuName, stuId, dep, seme);
                        members.add(lm);

                        JOptionPane.showMessageDialog(null, "Registration done successfully");

                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                    }
                    break;

                case "2":   //   add new books to library stock
                    String bookTitle = JOptionPane.showInputDialog("Enter the name of book :");
                    String bookAuthor = JOptionPane.showInputDialog("Enter the name of book writer :");
                    String numOfCopies1 = "";
                    int numOfCopies = 0;
                    while(numOfCopies <= 0) {
                        numOfCopies1 = JOptionPane.showInputDialog("Enter the quantity of books :");
                        try {
                            numOfCopies = Integer.parseInt(numOfCopies1);
                            if (numOfCopies <= 0) {
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Please enter a positive number greater than zero");
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "The book ID entered already exists in the library's book stock.");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                        }
                    }
                    String tokenNo = JOptionPane.showInputDialog("Enter token number :");

                    ////  Calling the method of Library class through reference type of LibraryMember class ////
                    l.addNewBook(bookTitle,bookAuthor,numOfCopies,tokenNo);

                    JOptionPane.showMessageDialog(null, "Book added successfully!");

                    break;


                case "3":   //  display the list of Library members
                    try {
                        if (members.size() == 0) {
//                                System.out.println("Need to enter the list of libraryMembers!");
                            JOptionPane.showMessageDialog(null, "Need to enter the list of libraryMembers!");

                        } else {
                            for (int i = 0; i < members.size(); i++) {
                                members.get(i).printTheStudentInfo();
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                    }
                    break;


                case "4": // to cancel the library Membership

                    try {
                        String memId = JOptionPane.showInputDialog("Enter the library member's id :");
                        boolean found = false;
                        for (LibraryMember m : members) {
                            if (m.getStuId().equals(memId)) {
                                members.remove(m);
                                found = true;
                                JOptionPane.showMessageDialog(null, "Membership canceled successfully!");
                                break;
                            }
                        }
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "No library member found with the provided ID.");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                    }
                    break;


                case "5":  // to display the list of books.

                    l.display();
                    break;

                case "6":  // to find a book.

                    try {
                        String bookTitle1 = JOptionPane.showInputDialog("Enter the name of book :");
                        String bookAuthor1 = JOptionPane.showInputDialog("Enter the name of book writer :");

                        l.findBook(bookTitle1,bookAuthor1);

                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                    }
                    break;

                case "7":   //to borrow book from library stock.
                    String bookTitle2 = JOptionPane.showInputDialog("Enter the name of book :");
                    String bookAuthor2 = JOptionPane.showInputDialog("Enter the name of book writer :");
                    int noOfCopies = 0;
                    String numOfCopies2 = "";
                    while(noOfCopies <= 0){
                        numOfCopies2 = JOptionPane.showInputDialog("Enter the quantity of books :");
                        try{
                            noOfCopies = Integer.parseInt(numOfCopies2);
                            if(noOfCopies <= 0){
                                throw new IllegalArgumentException();
                            }
                        }catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number for the number of book copies.");
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "Please enter a positive number");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                        }
                    }
                    String tokenNum = JOptionPane.showInputDialog("Enter a token number :");
                    l.borrow(bookTitle2,bookAuthor2,noOfCopies,tokenNum);
                    break;

                case "8":   // to return borrowed books.
                    numOfCopies = 0;
                    while(numOfCopies <= 0) {
                        numOfCopies1 = JOptionPane.showInputDialog("Enter the quantity of books :");
                        try {
                            numOfCopies = Integer.parseInt(numOfCopies1);
                            if (numOfCopies <= 0) {
                                throw new NumberFormatException();
                            }
                        } catch (NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Please enter a positive number (greater than zero)");
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, "The book ID entered already exists in the library's book stock.");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                        }
                    }
                    tokenNum = JOptionPane.showInputDialog("Enter token number :");
                    l.returnBook(tokenNum,numOfCopies);
                    break;


                case "9":     // to remove a book from library stock

                    try {
                        String bookId = JOptionPane.showInputDialog("Enter tokenNum of the book to remove the collection ");

                        l.toRemoveBook(bookId);

                    }catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid number for book id and the number of book copies.");
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
                    }
                    break;


                case "0":

                    JOptionPane.showMessageDialog(null, "Exiting the system...");
                    break loop;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                    break;
            }
        }
    }
}