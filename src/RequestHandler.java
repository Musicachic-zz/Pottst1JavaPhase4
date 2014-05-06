/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 4/29/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.io.*;
import java.net.Socket;
import java.util.Collection;
import java.util.List;

public class RequestHandler implements Runnable
{
	private Socket sock;

	public RequestHandler(Socket sock)
	{
		this.sock = sock;

/*		synchronized (counter)
		{
			num = counter;
			counter++;
		}*/
	}

	@Override
	public void run()
	{
		try
		{
			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
			Object o = is.readObject();
			RequestCriteria requestCriteria = (RequestCriteria) o;
			if (requestCriteria.getActionToTake() == RequestCriteria.Action.GETEMPLOYEES)
			{
				//return all employees
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
			}
			else if (requestCriteria.getActionToTake() == RequestCriteria.Action.GETPRODUCTS)
			{
				//return all products
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
				List<Employee> employees = null;
				oos.writeObject(employees);
			}
			else if (requestCriteria.getEmployee() != null)
			{
				//EmployeeHandler handler = new EmployeeHandler(employee);
				//handler.handle(action);
				//handleEmployee(employee, action);
			}
			else
			{
				//handle product
			}
		}
		catch (IOException e2)
		{
			e2.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

}
