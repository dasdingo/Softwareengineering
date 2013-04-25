package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.AbstractAddress;
import model.AddressList;
import model.EmailonlyAddress;

public abstract class AbstractAddressView extends JFrame{
	// InstanzVar
	
	protected AddressList addressList;
	JPanel upperPanel;
	JButton speicherButton;
	
	void initabstract(){
		this.setLayout(new BorderLayout());
		upperPanel = new JPanel();
		speicherButton = new JButton("Hinterlegen");
	    init();
	}
	
//FabrikMethode
protected abstract void init();
// Fieldsspeichern	
protected abstract void populateFields();

// Fields holen
protected abstract void retrieveFields();
}

