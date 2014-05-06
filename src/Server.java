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

public class Server
{
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
