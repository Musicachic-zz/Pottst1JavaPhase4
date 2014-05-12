/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 5/11/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.sql.*;
import java.util.ArrayList;

/**
 * This class contains the SQL statements for completing changes to the inventory table.
 */
public class DatabaseInventory
{
	private static final String DERBY_DRIVER_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DERBY_CREATE_CONNECTION = "jdbc:derby:POPDB;create=true";
	private static final String ADD_INVENTORY = "INSERT INTO INVENTORY (UPC, DESCRIPTION, PRICE, " +
			                                            "NUM_STOCK) VALUES (?,?,?,?)";
	private static final String DISPLAY_INVENTORY = "SELECT UPC, DESCRIPTION, PRICE, NUM_STOCK FROM INVENTORY";
	private static final String DELETE_INVENTORY = "DELETE INVENTORY WHERE UPC = ?";
	private static final String SELECT_INVENTORY = "SELECT UPC, DESCRIPTION, PRICE, " +
			                                               "NUM_STOCK FROM INVENTORY WHERE UPC = ?";
	private static final String UPDATE_INVENTORY = "UPDATE INVENTORY SET DESCRIPTION = ?, PRICE = ?, " +
			                                               "NUM_STOCK = ? WHERE UPC = ?";

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
	 *  This method contains the SQL statements to complete inserting new inventory into the database.
	 */
	public void createInventory()
	{
		Product p = new Product();

		if (p != null)
		{
			try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
			{
				try (PreparedStatement addProduct = derbyCon.prepareStatement(ADD_INVENTORY))
				{
					addProduct.setString(1, p.getUpc());
					addProduct.setString(2, p.getDescription());
					addProduct.setString(3, String.valueOf(p.getPrice()));
					addProduct.setString(4, String.valueOf(p.getQuantity()));

					addProduct.execute();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method contains the SQL statements to show the inventory currently in the database.
	 */
	public void DisplayAllInventory()
	{
		ArrayList<Product> products = new ArrayList<>();

		try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
		{
			try (PreparedStatement displayInventory = derbyCon.prepareStatement(DISPLAY_INVENTORY))
			{
				displayInventory.execute();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * This method contains the SQL code to delete inventory from the database. It first allows you to search for the
	 * upc you want to delete and then executes the delete statement.
	 * @param upc
	 */
	public void DeleteInventory(String upc)
	{
		Product p = null;

		try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
		{
			PreparedStatement stmt = derbyCon.prepareStatement(SELECT_INVENTORY);
			stmt.setString(1, upc);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{

			}
			try (PreparedStatement deleteUsers = derbyCon.prepareStatement(DELETE_INVENTORY))
			{
				deleteUsers.setString(1, upc);

			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	/**
	 * This method contains the SQL code to update inventory. It first allows you to find the upc you want to
	 * update and then contains the SQL statement to perform the change.
	 * @param upc
	 */
	public void UpdateInventory(String upc)
	{
		Product p = null;

		try (Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION))
		{
			PreparedStatement stmt = derbyCon.prepareStatement(UPDATE_INVENTORY);
			stmt.setString(1, p.getDescription());
			stmt.setString(2, String.valueOf(p.getPrice()));
			stmt.setString(3, String.valueOf(p.getQuantity()));

			System.out.println("Rows updated: " + stmt.executeUpdate());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
