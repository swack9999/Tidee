/*
 * B-Team
 * Matthew London, Aley Krayem, Monzur Ahmed, Spencer Wack
 * Creation Date: 11/9/2017
 * Last Updated: 11/10/2017
 * InventorySystem.java
 * All rights reserved. (C) 2017
 */
package Tidee;
import java.util.ArrayList;
public class InventorySystem {
	// Attributes
	/*clearance db?		do we store locally or just ref database?
	sales db*/
	private Layout storeLayout, stockLayout;
	
	// Constructor
	public InventorySystem() {
		
	}
	public Item queryForItem(String name) {
		/*
		 * Purpose: Given specific keyword(s), queries the item database for matches
		 * Pre : name is not empty
		 * 		 Item database is populated
		 * Post: The matching Item is returned OR
		 * 		 a null Item is returned
		 */
		//temp
		Item item = new Item();
		return item;
	}
	
	// Operations
	public void changeInventory(Item item, int locType, int quant) {
		/*
		 * Purpose: Change the stockroom or store floor inventory of a given item
		 * Pre : locType is either 0 or 1
		 * Post: The desired quantity of the item is changed
		 */
		item.changeInventory(locType, quant);
	}
}