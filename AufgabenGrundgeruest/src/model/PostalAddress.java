package model;

public class PostalAddress extends AbstractAddress {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5628835642710170867L;
	//Konstanten
	
	
	//Instanzen
	
	private String name;
	private String emailaddress;
	private String street;
	private String houseNumber;
	private String postal;
	private String location;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String eol = System.getProperty("line.separator");  
		
		
		String string=eol +"Name: "+ this.getName()+ eol;
		string+="Street: "+ this.getStreet()+eol;
		return (String)string;
	}
}
