package model;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class AddressList extends Observable implements Serializable {
	private static final long serialVersionUID = -8436170099085318899L;
	private ArrayList<AbstractAddress> addressList;
	
	
// AddressList aus DatenStrom lesen
	public AddressList(){
		
		InputStream fis = null;
		this.addressList = new ArrayList<AbstractAddress>();   
		try
		{
		  fis = new FileInputStream( ("data.ser") );

		  ObjectInputStream o = new ObjectInputStream( fis );
		   
		  this.addressList = (ArrayList<AbstractAddress>) o.readObject();
		
		}
	catch(Exception e){}	
	}


	
	public ArrayList<AbstractAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(ArrayList<AbstractAddress> addressList) {
		this.addressList = addressList;
	}	
    public void addAddress(AbstractAddress address){
    	this.addressList.add(address);
    	this.setChanged();
    	this.notifyObservers();
    	
    }
public AbstractAddress getAddress(int i){
	return addressList.get(i);
}
}




