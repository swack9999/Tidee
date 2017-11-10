/*
 * B-Team
 * Matthew London, Aley Krayem, Monzur Ahmed, Spencer Wack
 * Creation Date: 11/9/2017
 * Last Updated: 11/10/2017
 * ClearanceEvent.java
 * All rights reserved. (C) 2017
 */
package Tidee;
public class ClearanceEvent {
	// Attributes
	private String itemID;
	private String eventDate;
	private double markdownPrice;
	private int[] location;
	
	// Constructor
	public ClearanceEvent(String item, String date, double md, int[] loc) {
		itemID = item;
		eventDate = date;
		markdownPrice = md;
		location = loc;
	}
}
