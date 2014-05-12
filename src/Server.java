/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 4/29/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class contains the server main method.
 */
public class Server
{
	/**
	 * This method contains the main method to create the socket to talk to the client. I never figured out exactly
	 * how to get this connected to the client.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{

		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		ServerSocket servSock = new ServerSocket(12345);

		boolean t = true;

		while (t)
		{
			Socket s = servSock.accept();
			new Thread(new RequestHandler(s)).start();
		}

		servSock.close();

	}
}
