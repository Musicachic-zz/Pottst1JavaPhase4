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

public class DatabaseEmployees
{
	private static final String DERBY_DRIVER_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DERBY_CREATE_CONNECTION = "jdbc:derby:POPDB;create=true";
	private static final String ADD_USER = "INSERT INTO EMPLOYEE (USERNAME, PASSWORD, MANAGER) VALUES (?, ?, ?)";
	private static final String DISPLAY_USERS = "SELECT USERNAME, PASSWORD, MANAGER FROM EMPLOYEE";
	private static final String DELETE_USER = "DELETE EMPLOYEE WHERE USERNAME = ?";
	private static final String SELECT_EMP = "SELECT USERNAME, PASSWORD, MANAGER FROM EMPLOYEE WHERE USERNAME = ?";

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

	public void createUser(){
		Employee em =  new Employee();

		if(em != null){
			try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
			{
				try(PreparedStatement addUser =  derbyCon.prepareStatement(ADD_USER)){
					addUser.setString(1,em.getUsername());
					addUser.setString(2, new String(em.getPassword()));
					addUser.setString(3, em.getAccessLevel());

					addUser.execute();

				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
		}
	}

	public void DisplayAllUsers(){
		ArrayList<Employee> employees = new ArrayList<>();

		try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
		{
			try(PreparedStatement displayUsers = derbyCon.prepareStatement(DISPLAY_USERS)){

				displayUsers.execute();
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

	}

	public void DeleteUser(String username){

		Employee em = null;



		try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
		{
			PreparedStatement stmt = derbyCon.prepareStatement(SELECT_EMP);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()){

			}
			try(PreparedStatement deleteUsers = derbyCon.prepareStatement(DELETE_USER));
			deleteUsers.setString(1, username);



	}
		catch (SQLException e)
		{
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}}
