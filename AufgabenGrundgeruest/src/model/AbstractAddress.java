package model;

import java.io.Serializable;

public abstract class AbstractAddress implements Serializable{

	SendMessageStrategy SendMessageVerhalten;
	
	public void nachrichtSenden(){
		 SendMessageVerhalten.sendeNachricht(this);
	 }
	
	public void setSendMessageVerhalten(SendMessageStrategy SendMessageVerhalten){
		this.SendMessageVerhalten = SendMessageVerhalten;
	}

	
}
