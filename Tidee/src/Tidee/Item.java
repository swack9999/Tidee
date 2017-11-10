/*
 * B-Team
 * Matthew London, Aley Krayem, Monzur Ahmed, Spencer Wack
 * Creation Date: 11/9/2017
 * Last Updated: 11/10/2017
 * Item.java
 * All rights reserved. (C) 2017
 */
package Tidee;
public class Item {
	// Attributes
	private String itemID, itemName;
	private int depNum, stockQuant, storeQuant;
	private double price;
	private int[] stockLocation, storeLocation;
	
	// Constructors
	public Item() {
		/*
		 * Null item constructor
		 */
		itemID = "null";
	}
	public Item(String ID, String name, int dep, int q1, int q2, double price, int[] stockLoc, int[] storeLoc) {
		/*
		 * Normal constructor
		 */
		itemID = ID;
		itemName = name;
		depNum = dep;
		stockQuant = q1;
		storeQuant = q2;
		this.price = price;
		stockLocation = stockLoc;
		storeLocation = storeLoc;
	}
	
	// Operations
	public void changeInventory(int locType, int quantity) {
		/*
		 * Purpose: Changes the quantity of either the stockroom or store to quantity
		 * Pre : locType is either 0 or 1
		 * Post: The desired quantity of this item is changed
		 */
		if (locType == 0)
			stockQuant = quantity;
		else
			storeQuant = quantity;
	}
}
