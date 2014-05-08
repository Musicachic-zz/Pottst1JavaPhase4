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

public class Client
{
	public static void main(String[] args)
	{

		try
		{
			Socket s = new Socket("localhost", 12345);
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

			os.writeObject(new RequestCriteria(RequestCriteria.Action.GETEMPLOYEES, null, null));
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

		try {
			Socket s = new Socket("localhost", 12345);
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

			Employee m = new Employee();
			m.toDataText();
			os.writeObject(new RequestCriteria(RequestCriteria.Action.ADD, m, null));
			os.flush();

			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
			Response resp = (Response) is.readObject();
			System.out.println(resp.getHeader());

			s.close();

		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}

		try {
			Socket s = new Socket("localhost", 12345);
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));

			os.writeObject(new Request("LIST EMPLOYEE", null));
			os.flush();

			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
			Response resp = (Response) is.readObject();

			if (resp.getHeader().equals("SUCCESS")) {

				@SuppressWarnings("unchecked")
				List<Employee> em = (List<Employee>) resp.getPayload();
				for (Employee m: em) {
					System.out.println(m);
				}

			}

			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
