/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 5/2/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DerbyDatabaseView
{

	private static final String DERBY_DRIVER_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DERBY_CREATE_CONNECTION = "jdbc:derby:POPDB;create=true";

	private static final String VIEW_EMPLOYEE_TABLE =
			"SELECT USERNAME, PASSWORD, MANAGER from EMPLOYEE";

	private static final String VIEW_INVENTORY_TABLE =
			"SELECT UPC, DESCRIPTION, PRICE, NUM_STOCK FROM INVENTORY";

	public static void viewEmployee() throws ClassNotFoundException, SQLException
	{
		Class.forName(DERBY_DRIVER_CLASS);
		Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION);

		PreparedStatement viewEmployeeTable = derbyCon.prepareStatement(VIEW_EMPLOYEE_TABLE);
		viewEmployeeTable.execute();
		viewEmployeeTable.close();
		System.out.println("Viewing the employee table.");
	}

	public static void ViewInventory() throws ClassNotFoundException, SQLException
	{
		Class.forName(DERBY_DRIVER_CLASS);
		Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION);

		PreparedStatement viewInventoryTable = derbyCon.prepareStatement(VIEW_INVENTORY_TABLE);
		viewInventoryTable.execute();
		viewInventoryTable.close();
		System.out.println("Viewing the inventory table.");
	}
}
