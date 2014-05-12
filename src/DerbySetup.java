import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DerbySetup
{

	private static final String DERBY_DRIVER_CLASS = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DERBY_CREATE_CONNECTION = "jdbc:derby:POPDB;create=true";

	private static final String CREATE_EMPLOYEE_TABLE =
			"CREATE TABLE EMPLOYEE (USERNAME VARCHAR(20), PASSWORD VARCHAR(200), MANAGER VARCHAR(1))";

	private static final String CREATE_INVENTORY_TABLE =
			"CREATE TABLE INVENTORY (UPC VARCHAR(12), DESCRIPTION VARCHAR(30), PRICE DECIMAL(7,2), NUM_STOCK INTEGER)";

	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		System.out.println("Seeking connection...");

		Class.forName(DERBY_DRIVER_CLASS);
		Connection derbyCon = DriverManager.getConnection(DERBY_CREATE_CONNECTION);
		System.out.println("Connection opened.");

		PreparedStatement createEmpTable = derbyCon.prepareStatement(CREATE_EMPLOYEE_TABLE);
		createEmpTable.execute();
		createEmpTable.close();
		System.out.println("Employee table created.");

		PreparedStatement createInvTable = derbyCon.prepareStatement(CREATE_INVENTORY_TABLE);
		createInvTable.execute();
		createInvTable.close();
		System.out.println("Inventory table created.");

		derbyCon.close();
		System.out.println("Connection closed.");
	}
}
