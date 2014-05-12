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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains the methods to check if the employee file exists, add employee,
 * and remove employee that the managers use.
 */
public class AddRemoveEmployeesDisplay
{
	private static Scanner sc = new Scanner(System.in);
	private static Employee em;
	//public static List<Employee> employee = new ArrayList<>();
	static boolean mgrLevel;
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

				System.out.print("What is the access level? Y for Manager or N for Employee ");
				String levelEntered = sc.nextLine();

				String employeeLevel = null;
				boolean successful = false;

				while (!successful)
				{
					switch (levelEntered.toUpperCase())
					{
						case "Y":
							employeeLevel = "Y";
							successful = true;
							break;
						case "N":
							employeeLevel = "N";
							successful = true;
							break;
						default:
							System.out.print("Please enter a valid access level. Employee or Manager ");
							levelEntered = sc.nextLine();
							successful = false;
					}
				}

				em = new Employee(username, password, mgrLevel);
				//String choice = "y";

				pw.write(em.getAccessLevel() + "\t");
				pw.write(em.getUsername() + "\t");
				pw.write(em.getPassword() + "\n");

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

	/**
	 * This method allows you to modify employee data from the text file.
	 */
	public static void modifyEmployee()
	{

		File f = new File("Employee.txt");
		String choice = "y";

		try
		{
			PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));

			while (choice.equalsIgnoreCase("y"))
			{

				System.out.println("Please enter the username you like to modify: ");
				String username = sc.nextLine();
				Boolean found = false;

				for (Employee e : ExtractEmployees.employee)
				{

					if (e.getUsername().equalsIgnoreCase(username))
					{
						found = true;
						System.out.println("Would you like to change their password or access level? P/A?");
						String change = sc.nextLine();
						Boolean option = false;

						while (option == false)
						{
							int objIndex = ExtractEmployees.employee.indexOf(e);

							if (change.equalsIgnoreCase("p"))
							{
								option = true;
								System.out.println("Please enter the new password for the user: ");
								char[] newPassword = sc.nextLine().toCharArray();
								e.setPassword(newPassword);

								ExtractEmployees.employee.set(objIndex, e);
								writeAllEmployeesToFile(ExtractEmployees.employee);

							}
							else if (change.equalsIgnoreCase("a"))
							{
								option = true;
								System.out.println("Please enter the new access level for the user: A or E");
								String accessLevel = sc.nextLine().toUpperCase();

								switch (accessLevel)
								{
									case "E":
										accessLevel = "N";
										break;
									case "A":
										accessLevel = "Y";
										break;
									default:
										System.out.println("Please enter a valid option: ");
										accessLevel = sc.nextLine().toUpperCase();
								}

								e.setAccessLevel(Boolean.parseBoolean(accessLevel));
								ExtractEmployees.employee.set(objIndex, e);
								writeAllEmployeesToFile(ExtractEmployees.employee);

							}
							else
							{
								option = false;
								System.out.println("Please select a valid option: P/A?: ");

							}
						}
					}

				}
				if (!found)
				{
					System.out.println("Please select a different username.");

				}
				else
				{
					System.out.println("Would you like to modify another user? Y/N: ");
					choice = sc.nextLine();
				}
			}

		}

		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
	}

	/**
	 * This method allows you to see the current employee list in a format that is readable.
	 */
	public static void viewEmployeesList()
	{

		//List<Employee> employee = new ArrayList<>();
		System.out.println("+------------------------------------+");
		System.out.println("USERNAME \t\t ACCESS LEVEL");
		System.out.println("+------------------------------------+");

		for (Employee e : ExtractEmployees.employee)
		{
			String username = e.getUsername();
			char[] password = e.getPassword();
			boolean accessLevel = e.getAccessLevel();

			System.out.println(username + "\t\t\t" + accessLevel);
		}
		System.out.println("+------------------------------------+");

	}

	/**
	 * This method writes all of the employees to a file again. This is used within other methods where changes to
	 * the employee text file is being changed.
	 * @param employeeList
	 */
	public static void writeAllEmployeesToFile(List<Employee> employeeList)
	{

		File f = new File("Employee.txt");

		PrintWriter pw = null;

		try
		{
			pw = new PrintWriter(f);

		}
		catch (FileNotFoundException e1)
		{
			System.out.println("File not found. ");
		}
		for (Employee e : employeeList)
		{
			pw.println(e.toDataText());
		}
		pw.close();
	}

	/**
	 * This method is not being used, but it would be used to send the data from the client to the server.
	 * @param employee
	 * @throws Exception
	 */
	public static void sendRequest(Employee employee) throws Exception
	{
		Socket s = new Socket("localhost", 12345);

		ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

		os.writeObject(new RequestCriteria(employee, null, null));

		os.close();
		s.close();

	}

}
