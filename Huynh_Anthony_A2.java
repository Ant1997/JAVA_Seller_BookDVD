/* Huynh_Anthony_A2.java
 * Anthony-Tien Nhat Huynh
 * 24 September 2017 
 * 
 * This program allows users to organize array lists through input. 
 * Then the user can add dvds or books to their shopping cart. 
 * The output will be through the user's inputed purchases. It will display the user's shopping cart. 
 *
 */

import java.text.DecimalFormat; // to format money values.
import java.util.Scanner; // Scanner is used for input values. 

public class Huynh_Anthony_A2{

	// This program uses arrays, for-loop, DecimalFormat, and methods to create a program that mimics
	// online shopping. 
	
	//displayCustomerMenu() will be used to restart every iteration of loop after an option has been chosen. 
	// This will not return anything but instead, print the menu to output. 
	
	public static void displayCustomerMenu(){
		String [] displayMenuTexts = {"**Welcome to the Comets Books and DVDs Store**\n",
		"Choose from the following options:", "1 - Browse books inventory (price low to high) ",
		"2 - Browse DVDs inventory (price low to high)", "3 - Add a book to the cart ", 
		"4 - Add a DVD to the cart", "5 - View cart ", "6 - Checkout",
		"7 - Cancel Order", "8 - Exit store "};

		for (int i = 0; i < displayMenuTexts.length ;i++)
		{
			System.out.println(displayMenuTexts[i]);
		}
	}

	// displayArrays() will be used for option 1 and option 5. An if statement was used with the 
	// argument itemType to distinguish between option 1 and option 5. Option 1 will take on the 
	// first block of the if-else-statement. 
	// Option 5 will take on the second block of the if-else-statement. 
	// Arguments will be the item array, the price array, item type. 
	// This will not return anything but instead prints out a display. 
	
	public static void displayArrays(String[] itemArray, double[] pricesArray, String itemType){
		DecimalFormat df = new DecimalFormat("#.##");

		if (itemType != "cart")
		{
			// Copying the arrays allow me to organize the prices using selection sort. 
			// By doing this, I will not effect the original arrays. The inventory number will
			// be the same as the element number + 1. 
			double[] copyPricesArray = {0, 0, 0, 0, 0}; 
			String[] copyItemArray = {"","","","",""};
			
			int minimumElement = 0;
			String tempName; 
			double tempValue; 
			
			// The for-loop copys the arrays sent through the argument. 
			for (int i = 0; i < pricesArray.length ;i++)
			{
				copyPricesArray[i] = pricesArray[i];
				copyItemArray[i] = itemArray[i];
			}
			
			//Selection Sorting the copied arrays.
			for (int j = 0; j < copyPricesArray.length - 1 ;j++)
			{
				minimumElement = j;
				for (int k = j + 1; k < copyPricesArray.length ;k++)
				{
					if (copyPricesArray[k] < copyPricesArray[minimumElement])
					{
						minimumElement = k;
					}
				}
				tempValue = copyPricesArray[j];
				tempName = copyItemArray[j];
				copyPricesArray[j] = copyPricesArray[minimumElement];
				copyItemArray[j] = copyItemArray[minimumElement];
				copyPricesArray[minimumElement] = tempValue;
				copyItemArray[minimumElement] = tempName; 
			}
			
			//Displaying the items with their inventory number, item type, and their prices. 
			System.out.printf("%-20s%-23s%6s\n", "Inventory Number", itemType, "Prices");
			System.out.println("-------------------------------------------------");
			
	
			for (int l = 0; l < copyPricesArray.length ;l++)
			{
	
				for (int m = 0; m < pricesArray.length ;m++)
				{
					if (copyItemArray[l] == itemArray[m])
					{
						System.out.printf("%-20s%-23s%6s\n", m + 1, itemArray[m], "$" + pricesArray[m]);
					}
				}
			}
		}
		else // this starts option 5
		{
			//the boolean empty is used to see the status of the cart. This will help print out 
			// necessary display between the dash format. 
			
			boolean empty = true; 
			System.out.printf("%-18s%-6s\n", "Items", "Prices");
			System.out.println("------------------------");
			
			for (int i = 0; i < pricesArray.length ;i++)
			{
				if(pricesArray[i] != -1)
				{
					empty = false; // if the if-statement works, that means that the cart is not empty. 
					System.out.printf("%-18s%-6s\n", itemArray[i], "$" + df.format(pricesArray[i]));
				}
			}
			if (empty == true)
			{
				System.out.println("Your cart is empty!");
			}
			System.out.println("------------------------");
			if (empty == false)
			{
				double total = getTotal(pricesArray);
				System.out.printf("%-18s%-6s\n", "Total + tax", "$" + df.format(total));
			}
		}
	}
	
	// getInventoryNumber() will be used for option 3 and 4. 
	// All this method does is get the number for the inventory. The rest will be done
	// in the main method. 
	public static int getInventoryNumber(){
		Scanner input = new Scanner(System.in); // To enable Scanner for input.
		int invNum = -1; 
		System.out.println("Enter the inventory number (enter -1 to return to menu): "); 
		invNum = input.nextInt();
		
		return invNum; 
	}
	
	//getCartSpaceTotal() checks the total amount of slots available in cart. 
	// it takes in the cart's price array and return an int value. 
	
	public static int getCartSpaceTotal(double[] cartPrices){
		int availableSlots = 0; 
		for (int i = 0; i < cartPrices.length ;i++)
		{
			if (cartPrices[i] != -1)
			{
				availableSlots++; 
			}
		}
		return availableSlots; 
	}
	
	//getCartSpace() will be used in Option 3 and 4. This method helps locate empty slots so that
	// the user could add books or DVDs later in the program.  
	// This take in the cart's prices array and return the element (int). 
	public static int getCartSpace(double[] cartPrices){
		for (int i = 0; i < cartPrices.length ;i++)
		{
			if (cartPrices[i] == -1)
			{
				return i;  
			}
		}
		return 0; //hopefully it does not return 0. 
	}
	
	//clearArrays() change all values in cartArray to a null string and changes cartPrices to -1. 
	// This will be used in option 6 and 7. 
	
	public static void clearArrays(String[] cartArray, double[] cartPrices){
		for (int i = 0; i < cartArray.length ;i++)
		{
			cartArray[i] = "";
		}
		for (int j = 0; j < cartPrices.length ;j++)
		{
			cartPrices[j] = -1; 
		}
		
	}
	
	//getTotal will be used to display the total amount cost for books and dvds in cart.
	// the total amount will include the 8.25% taxes as well. 
	// The method will be used in option 6 and 5. 
	
	public static double getTotal(double[] cartPrices){
		double total = 0;
		for (int i = 0; i < cartPrices.length ;i++)
		{
			if(cartPrices[i] != -1)
			{
			total += cartPrices[i];
			}
		}
		return (total * 1.0825); //The 1.0825 is accounting for the tax + the subtotal. 
		
	}
	
	
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in); // To enable Scanner for input.
		DecimalFormat df = new DecimalFormat("#.##");
		
		String[] booksName = {"Intro to Java", "Intro to C++",
							"Python","Perl","C#"};
		double[] booksPrices = {45.99,89.34,100,25.00,49.99};
		String[] dvdsName = {"Snow White", "Cinderella", "Dumbo",
							"Bambi", "Frozen"};
		double[] dvdsPrices = {19.99, 24.99, 17.99, 21.99, 24.99};
		
		String[] cartArray = {"","","","",""};
		double[] cartPrices = {-1,-1,-1,-1,-1};
		
		int itemInCart = 0; // a counter to see how many items are in cart. 
		
		boolean optionExist = false; 
		int optionNumber = -1; 
		String junk = " "; // junk is used to allow a pause for the user. It helps organizing each input values later on/ 
		
		do { 
			displayCustomerMenu(); 
			optionNumber = input.nextInt(); // this will get the option number from the user that corresponds with the menu.
			
			//If the optionNumber is not in the range of 8 and 1 (inclusive), then the block of code will not run.
			// This will print "The option is not acceptable.", set optionExist to false
			// and go straight back to the do-while loop.
			
			if (optionNumber >= 1 && optionNumber <= 8)
			{
				optionExist = true;  // this is used just in case the program going in an infinite loop. 
				
				if (optionNumber == 1) // option 1, displays all books and its corresponding prices/inventory #
				{
					displayArrays(booksName, booksPrices, "Books");
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false;
					
				}
				else if (optionNumber == 2) // option 2, displays all dvds and its corresponding prices/inventory #
				{
					displayArrays(dvdsName, dvdsPrices, "DVDs");
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false;
				}
				else if (optionNumber == 3) // option 3, allow user to add books in cart. 
				{
					itemInCart = getCartSpaceTotal(cartPrices);
					System.out.println("Total items in cart: " + itemInCart);
					
					if (itemInCart == cartPrices.length) {
						System.out.println("Sorry, your cart is full.");
						System.out.println("Press any key follow by an [ENTER] to return to menu");
						junk = input.next(); 
						optionExist = false; 
					}
					else {
						boolean exit = false;
						do {
							if (itemInCart >= cartPrices.length)
							{
								exit = true; 
								optionExist = false; 
								System.out.println("Your cart is full.");
								System.out.println("Press any key follow by an [ENTER] to return to menu");
								junk = input.next();
							}
							else {
								int invNum = getInventoryNumber(); 
								String itemType3 = "books";
								
								if (invNum == -1) //exit 
								{
									exit = true; 
									optionExist = false;
									System.out.println("You have entered -1 to return to the menu."); 
								}
								else if(invNum >= 1 && invNum <= cartPrices.length) // else valid values
								{
									//System.out.println("invNum: " + invNum + " cartPrices.length: " + cartPrices.length);
							
									int availableElement = getCartSpace(cartPrices);
									System.out.println("You have entered the inventory #" + invNum + " for " + itemType3);
									cartArray[availableElement] = booksName[invNum - 1]; 
									cartPrices[availableElement] = booksPrices[invNum - 1]; 
									System.out.println("The slot that \"" + cartArray[availableElement] + "\" ($" + cartPrices[availableElement] +
												") is slot #" + (availableElement + 1) + " of your cart");
									itemInCart = getCartSpaceTotal(cartPrices);
										//System.out.println("Item in cart: " + itemInCart);

								} // end for else valid values
								else 
								{
									exit = false; 
									System.out.println("You have entered " + invNum + " which is an invalid inventory number for " + itemType3);
								}
							}//else (itemCart>= cartPrices.length)
				
						}while(exit == false);
					} //else
				}//else if
				
				else if (optionNumber == 4)  // option 4, allow user to add dvds in cart. 
				{
					itemInCart = getCartSpaceTotal(cartPrices);
					System.out.println("Total items in cart: " + itemInCart);
					
					if (itemInCart == cartPrices.length) {
						System.out.println("Sorry, your cart is full.");
						System.out.println("Press any key follow by an [ENTER] to return to menu");
						junk = input.next(); 
						optionExist = false; 
					}
					else {
						boolean exit = false;
						do {
							if (itemInCart >= cartPrices.length)
							{
								exit = true; 
								optionExist = false; 
								System.out.println("Your cart is full.");
								System.out.println("Press any key follow by an [ENTER] to return to menu");
								junk = input.next();
							}
							else {
								int invNum = getInventoryNumber(); 
								String itemType3 = "DVDs";
								
								if (invNum == -1) //exit 
								{
									exit = true; 
									optionExist = false;
									System.out.println("You have entered -1 to return to the menu."); 
								}
								else if(invNum >= 1 && invNum <= cartPrices.length) // else valid values
								{
									//System.out.println("invNum: " + invNum + " cartPrices.length: " + cartPrices.length);
							
									int availableElement = getCartSpace(cartPrices);
									System.out.println("You have entered the inventory #" + invNum + " for " + itemType3);
									cartArray[availableElement] = dvdsName[invNum - 1]; 
									cartPrices[availableElement] = dvdsPrices[invNum - 1]; 
									System.out.println("The slot that \"" + cartArray[availableElement] + "\" ($" + cartPrices[availableElement] +
												") is slot #" + (availableElement + 1) + " of your cart");
									itemInCart = getCartSpaceTotal(cartPrices);
										//System.out.println("Item in cart: " + itemInCart);

								} // end for else valid values
								else 
								{
									exit = false; 
									System.out.println("You have entered " + invNum + " which is an invalid inventory number for " + itemType3);
								}
							}//else (itemCart>= cartPrices.length)
				
						}while(exit == false);
					} //else
				}//else if

				
				else if (optionNumber == 5) // OPTION 5, DISPLAY CART AND ITS TOTAL. 
				{
					displayArrays(cartArray, cartPrices, "cart");
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false;
				}
				else if (optionNumber == 6) // OPTION 6, CHECK OUT. ADD ALL PRICES IN CART, THEN CLEAR OUT CART. 
				{
					double total = getTotal(cartPrices); 
					if (total <= 0)
					{
						System.out.println("Your cart is empty!");
						System.out.println("Press any key follow by an [ENTER] to return to menu");
						junk = input.next(); 
						optionExist = false;
					}
					else {
						System.out.printf("%-18s%-6s\n", "Total + tax", "$" + df.format(total));
						clearArrays(cartArray, cartPrices);
						System.out.println("Press any key follow by an [ENTER] to return to menu");
						junk = input.next(); 
						optionExist = false;
					}
				}
				else if (optionNumber == 7) // OPTION 7, CLEARS OUT CART. 
				{
					clearArrays(cartArray, cartPrices);
					System.out.println("Cart has been cleared!");
					System.out.println("Press any key follow by an [ENTER] to return to menu");
					junk = input.next(); 
					optionExist = false;
				}
				else if (optionNumber == 8) // OPTION 8, EXIT LOOP. 
				{
					
					System.out.println("Thanks you! Good bye!");
					optionExist = true;  
				}
			} // if(option = 1-8)
			else
			{
				System.out.println("The option is not acceptable.");
				System.out.println("Press any key follow by an [ENTER] to continue");
				junk = input.next(); 
				optionExist = false;
			}
			
		}while(optionExist == false);
		
	}
	
	
	
}
