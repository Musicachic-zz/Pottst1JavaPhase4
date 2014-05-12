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
