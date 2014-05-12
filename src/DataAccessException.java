/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 5/8/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

/**
 * This class is currently not being used since I never got the database integrated with the server.
 * @author HoffmanZ
 */
public class DataAccessException extends Exception
{

	/**
	 * This is a error message method that would be used in the case of an error from the database.
	 * @param message
	 */
	public DataAccessException(String message)
	{
		super(message);
	}
}
