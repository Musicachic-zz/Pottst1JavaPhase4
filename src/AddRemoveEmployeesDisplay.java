/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 3/15/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class contains the methods to check if the employee file exists, add employee,
 * and remove employee that the managers use.
 */
public class AddRemoveEmployeesDisplay
{
	private static Scanner sc = new Scanner(System.in);
	private static Employee em;
	static String e = Employee.EMPLOYEE_LEVEL;
	static String m = Employee.MANAGER_LEVEL;
	static File f;
	static PrintWriter pw = null;

	/**
	 * This method checks to see if the employee.txt file exists (which it should).
	 */

	public static void doesFileExist()
	{
		f = new File("Employee.txt");

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
	 * This method prompts the manager to enter in the information to add a new employee. Once they enter in the
	 * information it appends to the employee.txt file.
	 */
	public static void addEmployee()
	{
		//todo: verify someone cannot enter a duplicate username.

		File f = new File("Employee.txt");

		String choice = "y";
		try
		{
			PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));

			while (choice.equalsIgnoreCase("y"))
			{
				System.out.print("Please enter the username you would like to add: ");
				String username = sc.nextLine().toUpperCase();

				System.out.print("Please enter the password you would like to add: ");
				char[] password = sc.nextLine().toCharArray();

				System.out.print("What is the access level? E for Employee or M for Manager ");
				String levelEntered = sc.nextLine();

				String employeeLevel = null;
				boolean successful = false;

				while (!successful)
				{
					switch (levelEntered.toUpperCase())
					{
						case "E":
							employeeLevel = e;
							successful = true;
							break;
						case "M":
							employeeLevel = m;
							successful = true;
							break;
						default:
							System.out.print("Please enter a valid access level. Employee or Manager ");
							levelEntered = sc.nextLine();
							successful = false;
					}
				}

				em = new Employee(username, password, employeeLevel);
				//String choice = "y";

				pw.write(em.getAccessLevel() + "\t");
				pw.write(em.getUsername() + "\t");
				pw.write(String.valueOf(em.getPassword()) + "\n");

				System.out.print("Would you like to add another user? Y or N ");
				choice = sc.nextLine();

				pw.close();
			}
			AskForInput.initialQuestion();
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	/**
	 * This method prompts the manager to enter in the information to remove a employee. Once they enter in the
	 * information it removes the line from employee.txt file. It does not allow anyone to remove the ADMIN user.
	 */

	public static void removeEmployee()
	{
		File f = new File("Employee.txt");

		String choice = "y";

		try
		{
			PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));

			while (choice.equalsIgnoreCase("y"))
			{

				System.out.print("Please enter the username you would like to remove: ");
				String username = sc.nextLine().toUpperCase();
				Boolean found = false;

				if (username.equalsIgnoreCase("ADMIN"))
				{
					System.out.println("This user cannot be removed.");

				}
				else
				{
					for (Employee e : ExtractEmployees.employee)
					{

						if (e.getUsername().equalsIgnoreCase(username))
						{
							ExtractEmployees.employee.remove(e);
							found = true;
							break;
						}
					}

					if (!found)
					{
						System.out.println("This employee was not found.");

					}
					else
					{
						for (Employee e : ExtractEmployees.employee)
						{
							pw.write(e.getAccessLevel() + "\t");
							pw.write(e.getUsername() + "\t");
							pw.write(String.valueOf(e.getPassword()) + "\n");
							pw.flush();
						}
						pw.close();
						System.out.print("Would you like to remove another user? Y or N ");
						choice = sc.nextLine();

					}
				}

			}
			AskForInput.initialQuestion();
		}
		catch (FileNotFoundException e1)
		{

			e1.printStackTrace();
		}
	}

	public static void sendRequest(Employee employee) throws Exception {
		Socket s = new Socket("localhost", 12345);

		ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

		os.writeObject(new RequestCriteria(RequestCriteria.Action.ADD, employee, null));

		os.close();
		s.close();

	}

}
