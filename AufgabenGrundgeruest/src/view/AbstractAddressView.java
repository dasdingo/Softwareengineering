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
	private AbstractAddress address;
	private AddressList addressList;
	JPanel upperPanel;
	JButton speicherButton;
	
	void initabstract(){
		this.setLayout(new BorderLayout());
		upperPanel = new JPanel();
		upperPanel.setBorder(BorderFactory.createTitledBorder("Addressinformationen"));

		upperPanel.setLayout(new GridLayout(2, 2, 5, 5));	
		speicherButton = new JButton("Hinterlegen");
	}
	
  
// Fieldsspeichern	
protected abstract void populateFields();

// Fields holen
protected abstract void retrieveFields();
}

