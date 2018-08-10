/* Huynh_Anthony_A3.java
 * Anthony-Tien Nhat Huynh
 * 1 November 2017 
 * 
 * This program allows users to add books/audiobooks/dvd to arrayList through input. 
 * This is similar to the 2nd program, however, now we are on the side of the system (management). 
 * 
 * The output will be through the user's inputed catalog items. It will display the catalog. 
 *
 */

import java.text.DecimalFormat; // to format money values (Prices).
import java.util.Scanner; // Scanner is used for input values. 
import java.util.*; // This is for ArrayList (Books & DVD).

public class Huynh_Anthony_A3{

	// This program uses arrayList, advanced for-loop, classes (.this and super), and inheritance.
	
	//displayMenu() will be used to restart every iteration of loop after an option has been chosen. 
	// This will not return anything but instead, print the menu to output. 
	
	public static void displayMenu(){
		String [] displayMenuTexts = {"**Welcome to the Comets Books and DVDs Store (Catalog Section)**\n",
		"Choose from the following options:", "1 - Add Book ",
		"2 - Add AudioBook", "3 - Add DVD ", 
		"4 - Remove Book", "5 - Remove DVD ", "6 - Display Catalog",
		"9 - Exit store"};

		for (int i = 0; i < displayMenuTexts.length ;i++)
		{
			System.out.println(displayMenuTexts[i]);
		}
	}

	// Creating an abstract class for CatalogItem- 
	// (Instruction said that this class is not concrete).
	
	public static abstract class CatalogItem{
		private String title;
		private double price; 
		
		public CatalogItem(String title, double price) {
			this.title = title; 
			this.price = price; 
		}
		
		public String getTitle(){
			return this.title; 
		}
		
		public double getPrice(){
			return this.price; 
		}
		
		void setPrice(double SetPriceNumber){
			this.price = SetPriceNumber; 
		}

	}
	
	// Creating a class for Book
	public static class Book extends CatalogItem{
		DecimalFormat df = new DecimalFormat("#.##"); // Format for price.
		private int ISBN; 
		private String author;
		private double runningTime = 0.0;
		
		public Book(String title, double price, int ISBN, String author, double runningTime) {
			super(title, price); //This is from CatalogItem class
			this.ISBN = ISBN; 
			this.author = author;
			this.runningTime = runningTime; 
		}
		
		public int getISBN(){
			return this.ISBN; 
		}
		
		public String getAuthor(){
			return this.author; 
		}
	
		public double getRunningTime(){
			return this.runningTime; 
		}
		
		// toString will be used for Option 6. It is used in the function displayCatalog()
		public String toString() {
			return "Title: " + this.getTitle() + " | Author: " + this.getAuthor() + " | Price: $" + df.format(this.getPrice()) + " | ISBN: " + this.getISBN() + "\n";
		}
		
	}
	
	// Creating a class for AudioBook.
	public static class AudioBook extends Book{
		DecimalFormat df = new DecimalFormat("#.##"); // Format for price.
		public AudioBook(String title, double price, int ISBN, String author, double runningTime) {
			super(title, price, ISBN, author, runningTime);
			this.setPrice(this.getPrice() * 0.90); 
		}
		
		public String toString() {
			return "Title: " + this.getTitle() + " | Author: " + this.getAuthor() + " | Price: $" + df.format(this.getPrice()) + " | ISBN: " + this.getISBN() + 
					" | RunningTime: " + this.getRunningTime() + "\n";
		}
		
	}
	
	// Creating a class for DVD. 
	public static class DVD extends CatalogItem{
		DecimalFormat df = new DecimalFormat("#.##"); // Format for price.
		private int year, dvdCode; 
		private String director;
		
		public DVD(int year, int dvdCode, String director, String title, double price) {
			super(title, price);
			this.dvdCode = dvdCode; 
			this.director = director;
			this.year = year; 
		}
		
		public int getDvdCode(){
			return this.dvdCode; 
		}
		
		public String getDirector(){
			return this.director; 
		}
		
		public int getYear(){
			return this.year; 
		}
		
		public String toString() {
			return "Title: " + this.getTitle() + " | Director: " + this.getDirector() + " | Price: $" + df.format(this.getPrice()) + " | Year: " + this.getYear() + 
					" | DvdCode: " + this.getDvdCode() + "\n";
		}
		
	}	
	
	// This function checks for valid inputs from Books/Audio books. 
	// What it checks: if the book adding is already there, if the price is non-negative, 
	// if title given is not empty, if ISBN is non-negative/not empty, if author is not empty string,
	// if runningTime is non-negative. 
	public static void addBookCheck(int itemNum, ArrayList<Book> bookArrayList) {
		Scanner input = new Scanner(System.in); // To enable Scanner for input.
		String title, author; 
		double price, runningTime;
		int ISBN; 
		boolean go = true; 
		String junk = "";
		
		do { // Checking ISBN number if it exist or not positive.
			boolean positive = false;
			boolean exist = false;
			System.out.println("Please enter the ISBN: ");
			ISBN = input.nextInt();
			junk = input.nextLine(); // TESTING
			if (ISBN >= 0)
			{
				positive = true; 
				go = true;
				for (Book books : bookArrayList)
				{
					if( books.getISBN() == ISBN )
					{
						exist = true; 
					}
					
				}
			}
			else 
			{
				go = false; 
			}
			
			if (!positive)
			{
				System.out.println("The ISBN entered was not positive. Please try again.");
			}
			else if (exist)
			{
				System.out.println("The book is already in the catalog.");
				return; 
			}
			
		} while (!go); // end of checking ISBN. 
		
		//----------------------------------Title--------------------------------------------------// 
		do { // Checking Title if it is empty or not. 
			System.out.println("Please enter the title of the book: ");
			title = input.nextLine();
			if ((title == null) || (title.isEmpty() ) )
			{
				go = false; 
				System.out.println("The title entered was empty. Please enter a non-empty string title.");
			}
		} while(!go); // end of checking Title
		//----------------------------------Price--------------------------------------------------//
		do { // Checking Price if not positive. 
			System.out.println("Please enter the price of the book: ");
			price = input.nextDouble();
			junk = input.nextLine(); // TESTING
			if (price < 0)
			{
				go = false; 
				System.out.println("The price entered was invalid. Please try again.");
			}
		} while(!go); // end of checking Price
		//----------------------------------Author--------------------------------------------------//
		do { // Checking Author if empty. 
			System.out.println("Please enter the author of the book: ");
			author = input.nextLine();
			if ((author == null) || (author.isEmpty() ) )
			{
				go = false; 
				System.out.println("The author's name entered was empty. Please enter a non-empty string title.");
			}
		} while(!go); // end of checking author
		//----------------------------------RunningTime--------------------------------------------------//
		do { // Checking runningTime if positive. 
			System.out.println("Please enter the running time of the book: ");
			runningTime = input.nextDouble();
			junk = input.nextLine(); // TESTING
			if (runningTime <= 0) // does it make sense to have 0 as running time? 
			{
				go = false; 
				System.out.println("The running time entered was invalid. Please try again. ");
			
			}
		} while(!go); // end of checking runningTime
		//----------------------------------Check if book or audio book--------------------------------------------------//
		if (itemNum == 1)
		{
			//Is book. Add book to arrayList
			Book newBook = new Book(title, price, ISBN, author, runningTime);
			bookArrayList.add(newBook);
		}
		else
		{
			//Is audiobook. Add audio to book arrayList.
			AudioBook newBook = new AudioBook(title, price, ISBN, author, runningTime);
			bookArrayList.add(newBook);
		}
	}
	
	public static void addDVDCheck(ArrayList<DVD> dvdArrayList) {
		//year, dvdCode, director, title, price
		Scanner input = new Scanner(System.in); // To enable Scanner for input.
		String title, director; 
		double price;
		int dvdCode,year; 
		boolean go = true; 
		String junk = "";
		
		do { // Checking dvdCode number if it exist or not positive.
			boolean positive = false;
			boolean exist = false;
			System.out.println("Please enter the DVD Code: ");
			dvdCode = input.nextInt();
			junk = input.nextLine(); // TESTING
			if (dvdCode >= 0)
			{
				positive = true; 
				go = true;
				for (DVD dvds : dvdArrayList)
				{
					if( dvds.getDvdCode() == dvdCode )
					{
						exist = true; 
					}
					
				}
			}
			else 
			{
				go = false; 
			}
			
			if (!positive)
			{
				System.out.println("The DVD Code entered was not positive. Please try again.");
			}
			else if (exist)
			{
				System.out.println("The DVD is already in the catalog.");
				return; 
			}
			
		} while (!go); // end of checking DVDCode. 
		
		//----------------------------------Title--------------------------------------------------// 
		do { // Checking Title if it is empty or not. 
			System.out.println("Please enter the title of the book: ");
			title = input.nextLine();
			if ((title == null) || (title.isEmpty() ) )
			{
				go = false; 
				System.out.println("The title entered was empty. Please enter a non-empty string title.");
			}
		} while(!go); // end of checking Title
		//----------------------------------Price--------------------------------------------------//
		do { // Checking Price if not positive. 
			System.out.println("Please enter the price of the book: ");
			price = input.nextDouble();
			junk = input.nextLine(); // TESTING
			if (price < 0)
			{
				go = false; 
				System.out.println("The price entered was invalid. Please try again.");
			}
		} while(!go); // end of checking Price
		//----------------------------------Director--------------------------------------------------//
		do { // Checking Director if empty. 
			System.out.println("Please enter the author of the book: ");
			director = input.nextLine();
			if ((director == null) || (director.isEmpty() ) )
			{
				go = false; 
				System.out.println("The director's name entered was empty. Please enter a non-empty string title.");
			}
		} while(!go); // end of checking director
		//----------------------------------Year--------------------------------------------------//
		do { // Checking year if positive. 
			System.out.println("Please enter the year of the DVD: ");
			year = input.nextInt();
			junk = input.nextLine(); // TESTING
			if (year <= 0) // does it make sense to have 0 as running time? 
			{
				go = false; 
				System.out.println("The year entered was invalid. Please try again. ");
			
			}
		} while(!go); // end of checking runningTime
		
			DVD newDVD = new DVD(year, dvdCode, director, title, price);
			dvdArrayList.add(newDVD);

	}
	
	public static void removeItem(int itemNum, ArrayList<Book> bookArrayList, ArrayList<DVD> dvdArrayList) {
		int valueToRemove; // either ISBN or DVD code 
		Scanner input = new Scanner(System.in); // To enable Scanner for input.
		boolean go = true; 
		
		if (itemNum == 4) //This is books
		{
				System.out.println("Please enter the ISBN in order to remove the book: ");
				valueToRemove = input.nextInt();
				
				boolean exist = false;
				for (Book books : bookArrayList)
				{
					if( books.getISBN() == valueToRemove )
					{
						exist = true; 
					}
						
				}
				
				if (!exist)
				{
					System.out.println("The book does not exist.");
					go = true; 
					return; 
				}
				else 
				{
					int index = 0; 
					int removeIndex = 0; 
					for (Book books : bookArrayList)
					{
						if( books.getISBN() == valueToRemove )
						{
							removeIndex = index; 
							go = false; 
						}
						index++;
							
					}
					bookArrayList.remove(removeIndex); // will this remove the object from arrayList book
					System.out.println("The book has been removed.");
					//PRINTS THE CATALOG
					displayCatalog(bookArrayList, dvdArrayList);
				}
				
		}
		else
		{
				
				System.out.println("Please enter the DVD code that you want to remove from the catalog: ");
				valueToRemove = input.nextInt();
				
				boolean exist = false;
				for (DVD dvds : dvdArrayList)
				{
					if( dvds.getDvdCode() == valueToRemove )
					{
						exist = true; 
					}
						
				}
				
				if (!exist)
				{
					System.out.println("The DVD does not exist.");
					go = true; 
					return; 
				}
				else 
				{
					int index = 0; 
					int removeIndex = 0; 
					for (DVD dvds : dvdArrayList)
					{
						if( dvds.getDvdCode() == valueToRemove )
						{
							removeIndex = index; 
							go = false; 
						}
						index++;
							
					}
					dvdArrayList.remove(removeIndex); // will this remove the object from arrayList dvd
					System.out.println("The dvd has been removed.");
					//PRINTS THE CATALOG
					displayCatalog(bookArrayList, dvdArrayList);
				}
		}
	}
	
	public static void displayCatalog(ArrayList<Book> bookArrayList, ArrayList<DVD> dvdArrayList){
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("Books:\n");
		for (Book books : bookArrayList)
		{
			System.out.println(books.toString());
		}
		// How to get audio book?
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("Audio Books:\n");
		for (Book books : bookArrayList)
		{
			if (books instanceof AudioBook)
			System.out.println(books.toString());
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("DVDs:\n");
		for (DVD dvds : dvdArrayList)
		{
			System.out.println(dvds.toString());
		}
		
	}
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in); // To enable Scanner for input.
		
		 ArrayList<Book> bookArrayList = new ArrayList<Book>(); // Declaring Books/Audiobooks arraylist
		 ArrayList<DVD> dvdArrayList = new ArrayList<DVD>(); // Declaring DVD arraylist
		 
		
		boolean optionExist = false; 
		int optionNumber = -1; 
		String junk = " "; // junk is used to allow a pause for the user. It helps organizing each input values later on/ 
		
		do { 
			displayMenu(); 
			optionNumber = input.nextInt(); // this will get the option number from the user that corresponds with the menu.
			
			//If the optionNumber is not in the range of 1 and 6 (inclusive) or 9, then the block of code will not run.
			// This will print "The option is not acceptable.", set optionExist to false
			// and go straight back to the do-while loop.
			
			if ((optionNumber >= 1 && optionNumber <= 6) || (optionNumber  == 9)) // Make sure the option inputs are from 1 to 6 or 9.
			{
				optionExist = true;  // this is used just in case the program going in an infinite loop. 
				
				if (optionNumber == 1) // option 1, add book only if book is not already there. 
				{
					// CODE HERE TO ADD BOOKS. 
					// variableName.add(OBJECT/DATATYPE) OBJECT/DATATYPE newObject = new OBJECT/DATATYPE(parameters for the object);
					addBookCheck(optionNumber, bookArrayList);
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false;
					
				}
				else if (optionNumber == 2) // option 2, add audio books.
				{
					// CODE HERE TO ADD AUDIO BOOKS.
					addBookCheck(optionNumber, bookArrayList);
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false;
				}
				else if (optionNumber == 3) // option 3, add DVD.
				{
					// CODE HERE TO ADD DVDS
					addDVDCheck(dvdArrayList);
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false; 
				}//end else if | option 3
				
				else if (optionNumber == 4)  // OPTION 4, REMOVE BOOKS
				{
					removeItem(optionNumber,bookArrayList, dvdArrayList);
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false; 
					
				}// end else if | option 4

				
				else if (optionNumber == 5) // OPTION 5, REMOVE DVDS
				{
					removeItem(optionNumber,  bookArrayList, dvdArrayList);
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false;
				}
				else if (optionNumber == 6) // OPTION 6, DISPLAY CATALOG. 
				{
					displayCatalog(bookArrayList, dvdArrayList);
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false;
				}
				else if (optionNumber == 9) // OPTION 9, EXIT PROGRAM. 
				{
					System.out.println("Goodbye!");
					optionExist = true;
				} 

			} // if(option = 1-6 and 9)
			else
			{
				System.out.println("The option is not acceptable.");
				System.out.println("Press any key follow by an [ENTER] to continue");
				junk = input.next(); 
				optionExist = false;
			}
			
		}while(optionExist == false);
		input.close();
		
	}
	
	
	
}
