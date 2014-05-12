/*         ====================================================
*          # Name: Teresa Potts (POTTST1)
*          # Date: 4/27/14
*          # Course Name: CITP 290
*          # Description: 
*          ====================================================
*
*
*/

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * This class contains the getters, setters, and constructors for the
 * attributes of a product. This was created in class as a group.
 */

public class Product implements Serializable
{
	private String upc;
	private String description;
	private BigDecimal price;
	private int quantity;
	private int numInStock;

	public Product()
	{

	}

	public Product(String upc, String description, BigDecimal price, int quantity)
	{
		this.upc = upc;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(String upc)
	{
		this.upc = upc;
	}

	/**
	 * This is the getter for the product's upc.
	 *
	 * @return upc
	 */

	public String getUpc()
	{
		return upc;
	}

	/**
	 * This is the setter for the product's upc.
	 *
	 * @param upc This is the upc of the product.
	 */
	public void setUpc(String upc)
	{
		this.upc = upc;
	}

	/**
	 * This is the getter for the description of the product.
	 *
	 * @return description of the product.
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * This is the setter for the product description.
	 *
	 * @param description This is description of the product.
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * This is the getter for the product price.
	 *
	 * @return price This is the price of the product.
	 */
	public BigDecimal getPrice()
	{
		return price;
	}

	/**
	 * This is the setter for the product price.
	 *
	 * @param price This is the price of the product.
	 */
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	/**
	 * This is the getter for the quantity desired by the customer of the
	 * product they are buying.
	 *
	 * @return quantity
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * This is the setter for the quantity desired by the customer of the
	 * product they are buying.
	 *
	 * @param quantity This is the quantity a customer wants to purchase.
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * This is the getter for the number of items available in stock.
	 *
	 * @return numInStock This is the number available of an item.
	 */
	public int getNumInStock()
	{
		return numInStock;
	}

	/**
	 * This is a setter for the number of items available in stock.
	 *
	 * @param numInStock This is the number of items in stock.
	 */
	public void setNumInStock(int numInStock)
	{
		this.numInStock = numInStock;
	}

	/**
	 * This is the override method that formats the data from the items
	 * selected.
	 *
	 * @return description, price, quantity desired, total amount
	 */
	@Override
	public String toString()
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return "|" + description + "    |" + nf.format(price) + " x " + quantity + " = " +
				       nf.format(price.multiply(BigDecimal.valueOf(quantity))
				       ) + "|";
	}

	/**
	 * This is the override method that checks whether or not a product is equal.
	 *
	 * @return boolean true
	 */
	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product that = (Product) o;

		if (!upc.equals(that.upc)) return false;

		return true;
	}

	/**
	 * This is the required override for the Map to work.
	 *
	 * @return
	 */
	@Override
	public int hashCode()
	{
		return upc.hashCode();
	}

	/**
	 * This method displays the output of the product data in a readable format.
	 * @return
	 */
	public String toDataText()
	{
		return upc + "\t" + description + "\t" + price + "\t" + quantity;
	}
}

