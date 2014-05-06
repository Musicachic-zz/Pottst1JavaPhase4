/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 3/11/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * This class contains method to check if the productsandinventory.txt file exists,
 * the add and remove product method for the manager function to use.
 */

public class ProductAndInventoryDisplay
{
	private static Scanner sc = new Scanner(System.in);
	private static Product prod;
	public static File f;
	public static PrintWriter pw = null;

	/**
	 * This method checks to see if the ProductsAndInventory.txt file exists and if not it will create it.
	 */
	public static void doesFileExist()
	{
		f = new File("ProductsAndInventory.txt");

		if (!f.exists())
		{
			try
			{
				f.createNewFile();
			}
			catch (IOException e)
			{
				System.out.println("Could not create file.");
				System.exit(-1);
			}
		}

		try
		{
			pw = new PrintWriter(new FileOutputStream(f, true));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not locate file.");
		}
	}

	/**
	 * This method will allow a manager to add a new product. After they enter in the answers based on the prompts it
	 * will write the new product to the file. It will also catch the exception if the file is not found.
	 */

	public static void addNewProduct()
	{
		File f = new File("ProductsAndInventory.txt");

		String choice = "y";

		try
		{
			pw = new PrintWriter(new FileOutputStream(f, true));

			while (choice.equalsIgnoreCase("y"))
			{
				System.out.print("What is the upc? ");
				String upc = sc.nextLine();

				System.out.print("What is the product description? ");
				String description = sc.nextLine();

				System.out.print("What is the product price per unit? ");
				BigDecimal price = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));

				System.out.print("What is the number in stock of the product? ");
				int quantity = Integer.parseInt(sc.nextLine());

				prod = new Product(upc, description, price, quantity);

				pw.write(prod.getUpc() + "\t");
				pw.write(prod.getDescription() + "\t");
				pw.write(String.valueOf(prod.getPrice()) + "\t");
				pw.write(prod.getQuantity() + "\n");

				System.out.print("Would you like to add another product? Y or N ");
				choice = sc.nextLine();
			}
			pw.close();
			AskForInput.initialQuestion();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not locate file.");
		}
	}

	/**
	 * This method allow a manager to remove a product completely from the ProductsAndInventory.txt file.
	 */

	public static void removeProduct()
	{
		File f = new File("ProductsAndInventory.txt");

		String choice = "y";

		try
		{
			pw = new PrintWriter(new FileOutputStream(f, false));

			while (choice.equalsIgnoreCase("y"))
			{
				System.out.print("What is the upc of the product you would like to remove? ");
				String upc = sc.nextLine();
				Boolean found = false;

				for (Product p : ExtractProductsAndInventory.prod)
				{
					if (p.getUpc().equals(upc))
					{
						ExtractProductsAndInventory.prod.remove(p);
						found = true;
						break;
					}
				}

				if (!found)
				{
					System.out.println("This upc was not found.");

				}
				else
				{
					for (Product p : ExtractProductsAndInventory.prod)
					{
						pw.write(p.getUpc() + "\t");
						pw.write(p.getDescription() + "\t");
						pw.write(String.valueOf(p.getPrice()) + "\t");
						pw.write(p.getQuantity() + "\n");
						pw.flush();
					}
					pw.close();
					System.out.print("Would you like to remove another product? Y or N ");
					choice = sc.nextLine();

				}

			}
			AskForInput.initialQuestion();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Could not locate file.");
		}
	}

	public static void modifyInventory()
	{

		File f = new File("ProductsAndInventory.txt");
		String choice = "y";

		try
		{
			PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));

			while (choice.equalsIgnoreCase("y"))
			{
				System.out.println("Please enter the upc would like to modify: ");
				String upc = sc.nextLine();
				Boolean found = false;

				for (Product p : ExtractProductsAndInventory.prod)
				{

					if (p.getUpc().equalsIgnoreCase(upc))
					{
						found = true;
						System.out.println("Would you like to update the upc, description, price, or quantity? ");
						String change = sc.nextLine();
						Boolean option = false;

						while (option == false)
						{
							if (change.equalsIgnoreCase("UPC"))
							{

								option = true;
								System.out.println("Please enter the new upc for the product: ");
								String newUpc = sc.nextLine();
								p.setUpc(newUpc);
							}
							else if (change.equalsIgnoreCase("DESCRIPTION"))
							{

								option = true;
								System.out.println("Please enter the new description for the product: ");
								String newDesc = sc.nextLine();
								p.setDescription(newDesc);
							}
							else if (change.equalsIgnoreCase("PRICE"))
							{

								option = true;
								System.out.println("Please enter the new price: ");
								BigDecimal newPrice = sc.nextBigDecimal();
								p.setPrice(newPrice);
							}
							else if (change.equalsIgnoreCase("QUANTITY"))
							{

								option = true;
								System.out.println("Please enter the new quantity: ");
								Integer newQty = Integer.valueOf(sc.nextLine());
								p.setQuantity(newQty);
							}
							else
							{

								option = false;
								System.out.println("Please select a valid option: ");
							}
						}
					}
				}
				if (!found)
				{
					System.out.println("Please select a different upc. ");
				}
				else
				{
					System.out.println("Would you like to modify another product? Y/N: ");
					choice = sc.nextLine();
				}
			}

		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}

	}

	public static void viewInventory()
	{

		System.out.println("+----------------------------------------------------+");
		System.out.println("UPC \t\t DESCRIPTION \t\t PRICE \t\t QUANTITY");
		System.out.println("+----------------------------------------------------+");

		for (Product p : ExtractProductsAndInventory.prod)
		{

			String upc = p.getUpc();
			String desc = p.getDescription();
			BigDecimal price = p.getPrice();
			Integer quantity = p.getQuantity();

			System.out.println(upc + "\t\t" + desc + "\t\t" + price + "\t\t" + quantity);
		}
		System.out.println("+----------------------------------------------------+");
	}
}
