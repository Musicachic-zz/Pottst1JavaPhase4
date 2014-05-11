/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 4/29/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.io.Serializable;

public class RequestCriteria implements Serializable
{
	public static final String ADD = "ADD";
	public static final String REMOVE = "REMOVE";
	public static final String MODIFY = "MODIFY";
	public static final String GETPRODUCTS = "GETPRODUCTS";
	public static final String GETEMPLOYEES = "GETEMPLOYEES";

	private Action actionToTake;
	private Employee employee;
	private Product product;

	public RequestCriteria(Action actionToTake, Employee employee, Product product)
	{
		this.actionToTake = actionToTake;
		this.employee = employee;
		this.product = product;
	}

	public Action getActionToTake()
	{
		return actionToTake;
	}

	public Employee getEmployee()
	{
		return employee;
	}

	public Product getProduct()
	{
		return product;
	}
}