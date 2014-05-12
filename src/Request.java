/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 5/6/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.io.Serializable;

/**
 * This class contains the constructor and getters for the request header and payload.
 */
public class Request implements Serializable
{
	private String header;
	private Serializable payload;

	public Request(String header, Serializable payload)
	{
		this.header = header;
		this.payload = payload;
	}

	public String getHeader()
	{
		return header;
	}

	public Object getPayload()
	{
		return payload;
	}

}
