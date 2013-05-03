package model;

import java.io.Serializable;

public class LowBudgetVerhalten implements SendMessageStrategy, Serializable{

	
	

	@Override
	public void sendeNachricht(AbstractAddress address) {
		
		
		if(address instanceof EmailonlyAddress){
		
			System.out.println("E-Mail to: " +((EmailonlyAddress)address).getEmailaddress());
	        System.out.println();
		}
		else {
			System.out.println("E-Mail to: " +((PostalAddress)address).getEmailaddress());
			System.out.println();
		}
		}
	}
