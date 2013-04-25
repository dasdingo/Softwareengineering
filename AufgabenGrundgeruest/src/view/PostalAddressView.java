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
import model.PostalAddress;

public class PostalAddressView extends AbstractAddressView{

	protected PostalAddress address;

	private JTextField nameTextField;
	private JTextField emailaddressTextField;
	private JTextField streetTextField;
	private JTextField houseNumberTextField;
	private JTextField postalTextField;
	private JTextField locationTextField;
	
	public PostalAddressView(PostalAddress address, AddressList addressList) {
		this.address = address;
		this.addressList = addressList;
		initabstract();
		populateFields();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	protected void init() {
		this.setTitle("Address");
		this.setLayout(new BorderLayout());

		
		upperPanel.setBorder(BorderFactory.createTitledBorder("Postanschrift"));

		upperPanel.setLayout(new GridLayout(0, 2, 2, 2));

		JLabel nameLabel = new JLabel("Name");
		nameTextField = new JTextField();
		JLabel emailaddressLabel = new JLabel("Emailadresse");
		emailaddressTextField = new JTextField();
		JLabel streetLabel = new JLabel("Street");
		streetTextField = new JTextField();
		JLabel houseNumberLabel = new JLabel("HousNumber");
		houseNumberTextField = new JTextField();
		JLabel postalLabel = new JLabel("Postal");
		postalTextField = new JTextField();
		JLabel locationLabel = new JLabel("Location");
		locationTextField = new JTextField();
		

		upperPanel.add(nameLabel);
		upperPanel.add(nameTextField);
		upperPanel.add(emailaddressLabel);
		upperPanel.add(emailaddressTextField);
		upperPanel.add(streetLabel);
		upperPanel.add(streetTextField);
		upperPanel.add(houseNumberLabel);
		upperPanel.add(houseNumberTextField);
		upperPanel.add(postalLabel);
		upperPanel.add(postalTextField);
		upperPanel.add(locationLabel);
		upperPanel.add(locationTextField);
		this.add(upperPanel, BorderLayout.CENTER);

		

		speicherButton.addActionListener(new ActionListener() {
			/**
			 * Wählen Sie für diese anonyme Klasse SpeichernButtonActionListener
			 * als Klassenamen.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				retrieveFields();

				addressList.addAddress(address);
                
				Container container = (Container) e.getSource();
				while (!(container instanceof JFrame)) {
					container = container.getParent();
				}
				((JFrame) container).setVisible(false);
				((JFrame) container).dispose();
			}
		});

		this.add(speicherButton, BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		this.pack();
	}

	
	@Override
	protected void populateFields() {
		nameTextField.setText(address.getName());
		emailaddressTextField.setText(address.getEmailaddress());
		streetTextField.setText(address.getStreet());
		houseNumberTextField.setText(address.getHouseNumber());
		postalTextField.setText(address.getPostal());
		locationTextField.setText(address.getLocation());
		
	}

	@Override
	protected void retrieveFields() {
		address.setName(nameTextField.getText());
		address.setEmailaddress(emailaddressTextField.getText());
		address.setHouseNumber(houseNumberTextField.getText());
		address.setLocation(locationTextField.getText());
		address.setPostal(postalTextField.getText());
		address.setStreet(streetTextField.getText());
	}

}
