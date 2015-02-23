package com.bll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bll.categories.EmployeeType;
import com.dal.BasicRequests;
import com.dal.Database;

public class Employee {
	private int 				numEmployee;
	private String 				name;
	private String 				firstName;
	private String 				address;
	private String 				postalCode;
	private String  			town;
	private String 				phone;
	private String 				login;
	private String 				psw;
	private boolean 			connected;
	private EmployeeType 		employeeType;
	private	ArrayList<Ticket> 	tickets;
	
	/**
	 * Class constructor (uninitialized)
	 */
	public Employee(){
		numEmployee = 0;
		name = null;
		firstName = null;
		address = null;
		postalCode = null;
		town = null;
		phone = null;
		login = null;
		psw = null;
		connected = false;
		employeeType = new EmployeeType();
		tickets = new ArrayList<Ticket>();
	}
	
	/**
	 * Class constructor (initialized)
	 * @param numEmployee
	 * @param name
	 * @param firstName
	 * @param address
	 * @param postalCode
	 * @param town
	 * @param phone
	 * @param login
	 * @param psw
	 * @param connected
	 */
	public Employee(int numEmployee, String name, String firstName, String address, String postalCode, 
						String town, String phone, String login, String psw, int numEmployeeType) {
		this.numEmployee = numEmployee;
		this.name = name;
		this.firstName = firstName;
		this.address = address;
		this.postalCode = postalCode;
		this.town = town;
		this.phone = phone;
		this.login = login;
		this.psw = psw;
		this.connected = false;
		this.employeeType = new EmployeeType(numEmployeeType);
		this.tickets = BasicRequests.getUserTickets(numEmployee,0);
	}
	
	
	/**
	 * Check if connection info user has provided is right
	 * Creates a new user
	 * @param login
	 * @param psw
	 * @return
	 */
	public int connectUser(String login, String psw){
		Database 	db = new Database();
		ResultSet 	rs;
		String 		t[][] = {{"String",login},{"String",psw}};
		String 		query = "SELECT * "+
						"FROM	employee "+
						"WHERE	login=? "+
						"AND	psw=?";
		
		db.connect();
		rs = db.executePreparedQuery(query,t);
		if (rs != null){
			try{
				if(rs.first()){
					this.numEmployee = rs.getInt("numEmployee");
					this.name = rs.getString("name");
					this.firstName = rs.getString("firstName");
					this.address = rs.getString("address");
					this.postalCode = rs.getString("postalCode");
					this.town = rs.getString("town");
					this.phone = rs.getString("phone");
					this.login = login;
					this.psw = psw;
					this.connected = true;
					this.employeeType = new EmployeeType(rs.getInt("numEmployeeType"));
				}
			}catch(SQLException e){
				System.out.println(e.getMessage());
				return -1;
			}
		}
		db.disconnect();
		
		return this.connected ? 1 : 0;
	}
	
	
	/*************** getters & setters ***************/
	public int getNumEmployee() {
		return numEmployee;
	}
	public void setNumEmployee(int numEmployee) {
		this.numEmployee = numEmployee;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	public EmployeeType getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}
	
	public ArrayList<Ticket> getTickets() {
		return this.tickets;
	}
}


