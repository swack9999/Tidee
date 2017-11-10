/*
 * B-Team
 * Matthew London, Aley Krayem, Monzur Ahmed, Spencer Wack
 * Creation Date: 11/9/2017
 * Last Updated: 11/10/2017
 * EmployeeInfo.java
 * All rights reserved. (C) 2017
 */
package Tidee;
public class EmployeeInfo {
	// Attributes
	private String empID, empPass, fName, lName;
	private EmployeeType empType;
	
	// Constructor
	public EmployeeInfo(String ID, String pass, String first, String last, EmployeeType type) {
		empID = ID;
		empPass = pass;
		fName = first;
		lName = last;
		empType = type;
	}
	
	// Operations
	public boolean verifyLogin(String pass) {
		/*
		 * Purpose: Given a specific password, determine if a login attempt passes or fails
		 * Pre : pass is not empty
		 * 		 empPass is not empty
		 * Post: If login passes, true is returned OR
		 * 		 if login fails, false is returned
		 */
		if (pass.equals(empPass))
			return true;
		return false;
	}
	public String getID() { 
		/*
		 * Purpose: Retrieve this employee's unique ID
		 * Pre : this has a unique ID
		 * Post: unique ID is returned
		 */
		return empID; 
	}
	public EmployeeType getPerm() {
		/*
		 * Purpose: Retrieve this employee's type in order to determine their permissions
		 * Pre : this employee is a REGULAR, a DEPMGR, or a STOREMGR
		 * Post: this employee's type is returned
		 */
		return empType;
	}
	public String getName() {
		/*
		 * Purpose: Retrieve this employee's name in order to greet them upon login
		 * Pre : fName is not empty
		 * 		 lName is not empty
		 * Post: this employee's full name is returned
		 */
		return fName + " " + lName;
	}
}
