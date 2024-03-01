interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private int publicationYear;
    private boolean isBorrowed;

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isBorrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println("Book '" + title + "' is borrowed.");
        } else {
            System.out.println("Book '" + title + "' is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("Book '" + title + "' is returned.");
        } else {
            System.out.println("Book '" + title + "' is not borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return isBorrowed;
    }

    @Override
    public String toString() {
        return "Book: " + title + " by " + author + " (" + publicationYear + ")";
    }
}

class DVD implements LibraryItem {
    private String title;
    private String director;
    private int runningTime;
    private boolean isBorrowed;

    public DVD(String title, String director, int runningTime) {
        this.title = title;
        this.director = director;
        this.runningTime = runningTime;
        this.isBorrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println("DVD '" + title + "' is borrowed.");
        } else {
            System.out.println("DVD '" + title + "' is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("DVD '" + title + "' is returned.");
        } else {
            System.out.println("DVD '" + title + "' is not borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return isBorrowed;
    }

    @Override
    public String toString() {
        return "DVD: " + title + " directed by " + director + " (" + runningTime + " minutes)";
    }
}

abstract class LibraryUser {
    private String name;

    public LibraryUser(String name) {
        this.name = name;
    }

    abstract void borrowItem(LibraryItem item);
    abstract void returnItem(LibraryItem item);
    abstract void printItemsBorrowed();
    
    public String getName() {
        return name;
    }
}

class Student extends LibraryUser {
    private LibraryItem[] borrowedItems = new LibraryItem[5];
    private int numItemsBorrowed = 0;

    public Student(String name) {
        super(name);
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (numItemsBorrowed < 5) {
            item.borrowItem();
            borrowedItems[numItemsBorrowed++] = item;
        } else {
            System.out.println("Cannot borrow more items. Limit reached.");
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        for (int i = 0; i < numItemsBorrowed; i++) {
            if (borrowedItems[i] == item) {
                borrowedItems[i] = borrowedItems[numItemsBorrowed - 1];
                borrowedItems[numItemsBorrowed - 1] = null;
                numItemsBorrowed--;
                item.returnItem();
                return;
            }
        }
        System.out.println("Item not found in borrowed list.");
    }

    @Override
    public void printItemsBorrowed() {
        System.out.println("Student " + getName() + "'s Borrowed Items:");
        for (int i = 0; i < numItemsBorrowed; i++) {
            System.out.println("- " + borrowedItems[i]);
        }
    }
}

class Teacher extends LibraryUser {
    private LibraryItem[] borrowedItems = new LibraryItem[10];
    private int numItemsBorrowed = 0;

    public Teacher(String name) {
        super(name);
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (numItemsBorrowed < 10) {
            item.borrowItem();
            borrowedItems[numItemsBorrowed++] = item;
        } else {
            System.out.println("Cannot borrow more items. Limit reached.");
        }
    }

    @Override
    public void returnItem(LibraryItem item) {
        for (int i = 0; i < numItemsBorrowed; i++) {
            if (borrowedItems[i] == item) {
                borrowedItems[i] = borrowedItems[numItemsBorrowed - 1];
                borrowedItems[numItemsBorrowed - 1] = null;
                numItemsBorrowed--;
                item.returnItem();
                return;
            }
        }
        System.out.println("Item not found in borrowed list.");
    }

    @Override
    public void printItemsBorrowed() {
        System.out.println("Teacher " + getName() + "'s Borrowed Items:");
        for (int i = 0; i < numItemsBorrowed; i++) {
            System.out.println("- " + borrowedItems[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create some Book and DVD objects
        Book book1 = new Book("DUNE", "Frank Herbert", 1965);
        DVD dvd1 = new DVD("Shrek", "Andrew Adamson, Vicky Jenson", 90);

        // Create some Student and Teacher objects
        Student shawn = new Student("Shawn");
        Teacher sean = new Teacher("Sean");

        // Demonstrate borrowing and returning items
        shawn.borrowItem(book1);
        shawn.borrowItem(dvd1);
        sean.borrowItem(book1);
        sean.borrowItem(dvd1);

        shawn.printItemsBorrowed();
        sean.printItemsBorrowed();

        shawn.returnItem(book1);
        sean.returnItem(dvd1);

        shawn.printItemsBorrowed();
        sean.printItemsBorrowed();
    }
}
