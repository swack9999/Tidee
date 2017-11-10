package Tidee;
/*
 * B-Team
 * Matthew London, Aley Krayem, Monzur Ahmed, Spencer Wack
 * Creation Date: 11/9/2017
 * Last Updated: 11/10/2017
 * Layout.java
 * All rights reserved. (C) 2017
 */
import java.util.ArrayList;
import java.util.Collections;

public class Layout {
	// Attributes
	private ArrayList<String> sections;
	private ArrayList<Integer> subs;
	//private File layFile;
	//private Scanner in;
	
	// Operations
	public Layout() {
		
	}
	public void chgSecOrder(String name, int newLoc) {
		/*
		 * Purpose: Changes the location of a given section
		 * Pre : name is the name of a section
		 * 		 0 <= newLoc < number of sections
		 * Post: the desired section is located in position newLoc
		 * 		 the section that was in position newLoc is in the named section's previous position
		 * 		 the subsections of the two sections involved are swapped as well
		 */
		Collections.swap(sections, sections.lastIndexOf(name), newLoc);
		Collections.swap(subs, sections.lastIndexOf(name), newLoc);
	}
	/*public void chgSubOrder(int oldNum, int newNum) {
		/*
		 * Purpose: Changes the location of a given subsection
		 * Pre : 0 <= oldNum < number of subsections
		 * Post: 
		 
	} this method belongs in item class. It's irrelevant here*/
}
