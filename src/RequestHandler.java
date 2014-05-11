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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RequestHandler implements Runnable {

	private Socket sock;

	public RequestHandler(Socket sock) {
		this.sock = sock;
	}

	@Override
	public void run() {
		Request req = null;

		// Step 2 - Receive request from client
		try {
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(sock.getInputStream()));
			req = (Request) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Step 3 - Send something back
		Response resp = null;

		if (req != null) {
			switch(req.getHeader()) {
				case "LIST EMPLOYEE":
					ArrayList<Employee> emps = listEmployees();
					resp = new Response("SUCCESS", emps);
					break;
				case "ADD EMPLOYEE":
					addEmployee((Employee)req.getPayload());
					resp = new Response("SUCCESS", (Employee)req.getPayload());
					break;
			}
		}

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(sock.getOutputStream()));
			oos.writeObject(resp);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Employee> listEmployees() {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		try {
			emps.addAll(DaoFactory.getEmployeeDao().listAll());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return emps;
	}

	public void addEmployee(Employee m) {
		try {
			DaoFactory.getEmployeeDao().create(m);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}