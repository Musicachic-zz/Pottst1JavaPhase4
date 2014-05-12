/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 4/28/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.util.Scanner;

/**
 * This class contains the prompts to the user when using the original text file code.
 */
public class AskForInput
{
	static Scanner sc = new Scanner(System.in);

	/**
	 * This method contains the first prompts to the user and then routes them appropriately through Employee or
	 * Product changes based on their selection.
	 */
	public static void initialQuestion()
	{

		System.out.println("Would you like to change an employee or change inventory? (E for Employee/I for " +
				                   "Inventory/X to exit) ");

		String choice = sc.nextLine();

		switch (choice.toUpperCase())
		{
			case "E":
				System.out.println("You selected to change employee data.");
				System.out.println("Do you want to Add, Modify, or Remove an employee?");
				String echoice = sc.nextLine();
				switch (echoice.toUpperCase())
				{
					case "ADD":
						System.out.println("You selected to add an employee.");
						AddRemoveEmployeesDisplay.viewEmployeesList();
						AddRemoveEmployeesDisplay.addEmployee();
						break;
					case "REMOVE":
						System.out.println("You selected to remove an employee.");
						AddRemoveEmployeesDisplay.viewEmployeesList();
						AddRemoveEmployeesDisplay.removeEmployee();
						break;
					case "MODIFY":
						System.out.println("You selected to modify an employee.");
						AddRemoveEmployeesDisplay.viewEmployeesList();
						AddRemoveEmployeesDisplay.modifyEmployee();
						break;
					case "EXIT":
						System.out.println("You selected to exit the program.");
						break;
					default:
						System.out.println("Please enter a valid option.");
						initialQuestion();
				}
				break;

			case "I":
				System.out.println("You selected to change the inventory data.");
				System.out.println("Do you want to Add, Modify, or Remove Products and Inventory");
				String ichoice = sc.nextLine();
				switch (ichoice.toUpperCase())
				{
					case "ADD":
						System.out.println("You selected to add inventory.");
						ProductAndInventoryDisplay.viewInventory();
						ProductAndInventoryDisplay.addNewProduct();
						break;
					case "REMOVE":
						System.out.println("You selected to remove inventory.");
						ProductAndInventoryDisplay.viewInventory();
						ProductAndInventoryDisplay.removeProduct();
						break;
					case "MODIFY":
						System.out.println("You selected to modify inventory.");
						ProductAndInventoryDisplay.viewInventory();
						ProductAndInventoryDisplay.modifyInventory();
						break;
					case "EXIT":
						System.out.println("You selected to exit the program.");
						break;
					default:
						System.out.println("Please enter a valid option.");
						initialQuestion();
				}

				break;

			case "X":
				System.out.println("You selected to exit the program.");
				break;
			default:
				System.out.println("Please enter a valid option");
				initialQuestion();
				break;
		}

	}
}
