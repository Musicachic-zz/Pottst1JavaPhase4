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
	private Action actionToTake;
	private Employee employee;
	private Product product;

	public enum Action{
	ADD,
	REMOVE,
	MODIFY,
	GETPRODUCTS,
	GETEMPLOYEES;
}

	public RequestCriteria(Employee employee, Product product, Action actionToTake)
	{

		this.employee = employee;
		this.product = product;
		this.actionToTake = actionToTake;
	}

	public RequestCriteria(String add, Employee employee)
	{
		//To change body of created methods use File | Settings | File Templates.
	}

	public Employee getEmployee()
	{
		return employee;
	}

	public Product getProduct()
	{
		return product;
	}
	public Action getActionToTake()
	{
		return actionToTake;
	}
}