/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 5/6/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * This class was intended to be the new client code. It was supposed to ask for the data that would in turn get sent
 * to the server. I was not sure how to hook this up properly unfortunately so it doesn't work.
 */
public class Client
{
	/**
	 * This is the client main method that would be sending data to the server if it worked properly.
	 * @param args
	 */
	public static void main(String[] args)
	{

		try
		{
			Socket s = new Socket("localhost", 12345);
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

			os.writeObject(new RequestCriteria(null, null, RequestCriteria.Action.GETEMPLOYEES));
			os.flush();

			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
			Response resp = (Response) is.readObject();

			if (resp.getHeader().equals("SUCCESS"))
			{

				@SuppressWarnings("unchecked")
				List<Employee> em = (List<Employee>) resp.getPayload();
				for (Employee m : em)
				{
					System.out.println(m.getUsername());
				}

			}

			s.close();

		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		try
		{
			Socket s = new Socket("localhost", 12345);
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

			Employee m = new Employee();
			m.toDataText();
			os.writeObject(new RequestCriteria(null, null, RequestCriteria.Action.ADD));
			os.flush();

			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
			Response resp = (Response) is.readObject();
			System.out.println(resp.getHeader());

			s.close();

		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			;
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		try
		{
			Socket s = new Socket("localhost", 12345);
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

			os.writeObject(new Request("LIST EMPLOYEE", null));
			os.flush();

			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
			Response resp = (Response) is.readObject();

			if (resp.getHeader().equals("SUCCESS"))
			{

				@SuppressWarnings("unchecked")
				List<Employee> em = (List<Employee>) resp.getPayload();
				for (Employee m : em)
				{
					System.out.println(m);
				}

			}

			s.close();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}
}
