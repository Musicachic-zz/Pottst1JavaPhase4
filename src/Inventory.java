/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 4/27/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is currently not being used.
 */
public class Inventory
{
	private int numInStock;

	Map<String, Integer> inventoryMap = new HashMap<>();

	public void addInventory()
	{

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a upc to add to the stock: ");
		String upc = sc.next();

		System.out.println("Enter the quantity you have in stock: ");
		int numInStock = sc.nextInt();

		inventoryMap.put(upc, numInStock);
	}
}
