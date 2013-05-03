package model;

import java.io.Serializable;

public class HighBudgetVerhalten implements SendMessageStrategy, Serializable{

	
	@Override
public void sendeNachricht(AbstractAddress address) {
		
		
		if(address instanceof EmailonlyAddress){
		
			System.out.println("E-Mail to: " +((EmailonlyAddress)address).getEmailaddress());
			System.out.println();
		}
		else {
			System.out.println("Post an: " +((PostalAddress)address).toString());
			System.out.println();
		}
		}
	}

