/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 5/8/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.sql.*;
import java.util.ArrayList;

/**
 * This class contains the SQL statements for completing changes to the employee table.
 */
public class DatabaseEmployees
{
	private static final String DERBY_DRIVER_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DERBY_CREATE_CONNECTION = "jdbc:derby:POPDB;create=true";
	private static final String ADD_USER = "INSERT INTO EMPLOYEE (USERNAME, PASSWORD, MANAGER) VALUES (?, ?, ?)";
	private static final String DISPLAY_USERS = "SELECT USERNAME, PASSWORD, MANAGER FROM EMPLOYEE";
	private static final String DELETE_USER = "DELETE EMPLOYEE WHERE USERNAME = ? AND USERNAME not 'ADMIN'";
	private static final String SELECT_EMP = "SELECT USERNAME, PASSWORD, MANAGER FROM EMPLOYEE WHERE USERNAME = ?";
	private static final String UPDATE_USER = "UPDATE EMPLOYEE SET PASSWORD = ?, MANAGER = ? WHERE USERNAME = ?";

	/**
	 * This method does a check to see if the database exists.
	 */
	public void dbExist()
	{
		try
		{
			Class.forName(DERBY_DRIVER_CLASS);
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Database not found.");
		}
	}

	/**
	 * This method contains the SQL statements to complete creating a new user.
	 */
	public void createUser()
	{
		Employee em = new Employee();

		if (em != null)
		{
			try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
			{
				try (PreparedStatement addUser = derbyCon.prepareStatement(ADD_USER))
				{
					addUser.setString(1, em.getUsername());
					addUser.setString(2, new String(em.getPassword()));
					addUser.setString(3, String.valueOf(em.getAccessLevel()));

					addUser.execute();

				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
		}
	}

	/**
	 * This method contains the SQL statements to show the employees currently in the database.
	 */
	public void DisplayAllUsers()
	{
		ArrayList<Employee> employees = new ArrayList<>();

		try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
		{
			try (PreparedStatement displayUsers = derbyCon.prepareStatement(DISPLAY_USERS))
			{

				displayUsers.execute();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * This method contains the SQL code to delete a user from the database. It first allows you to search for the
	 * username you want to delete and then executes the delete statement.
	 * @param username
	 */
	public void DeleteUser(String username)
	{

		Employee em = null;

		try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
		{
			PreparedStatement stmt = derbyCon.prepareStatement(SELECT_EMP);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{

			}
			try (PreparedStatement deleteUsers = derbyCon.prepareStatement(DELETE_USER))
			{
				deleteUsers.setString(1, username);

			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	/**
	 * This method contains the SQL code to update a user. It first allows you to find the username you want to
	 * update and then contains the SQL statement to perform the change.
	 * @param username
	 */
	public void UpdateUser(String username)
	{
		Employee em = null;
		try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
		{
			PreparedStatement stmt = derbyCon.prepareStatement(UPDATE_USER);
			stmt.setString(1, new String(em.getPassword()));
			stmt.setString(2, em.getAccessLevel() ? "Y" : "");
			stmt.setString(3, em.getUsername());

			System.out.println("Rows updated: " + stmt.executeUpdate());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
