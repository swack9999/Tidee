/*
 * B-Team
 * Matthew London, Aley Krayem, Monzur Ahmed, Spencer Wack
 * Creation Date: 11/9/2017
 * Last Updated: 11/10/2017
 * SalesEvent.java
 * All rights reserved. (C) 2017
 */
package Tidee;

public class SalesEvent extends ClearanceEvent {
	// Attribute
	private int duration;
	
	//Constructor
	public SalesEvent(String itemID, String date, double md, int[] loc, int dur) {
		super(itemID, date, md, loc);
		duration = dur;
	}
}
