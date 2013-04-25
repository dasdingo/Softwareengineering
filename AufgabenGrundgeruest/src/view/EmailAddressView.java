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
import model.EmailonlyAddress;
import model.AddressList;

@SuppressWarnings("serial")
public class EmailAddressView extends AbstractAddressView {

	protected EmailonlyAddress address;

	private JTextField nameTextField;
	private JTextField emailaddressTextField;

	public EmailAddressView(EmailonlyAddress address, AddressList addressList) {
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

		
		upperPanel.setBorder(BorderFactory.createTitledBorder("Addressinformationen"));

		upperPanel.setLayout(new GridLayout(2, 2, 5, 5));

		JLabel nameLabel = new JLabel("Name");
		nameTextField = new JTextField();

		JLabel emailaddressLabel = new JLabel("Emailadresse");
		emailaddressTextField = new JTextField();

		upperPanel.add(nameLabel);
		upperPanel.add(nameTextField);
		upperPanel.add(emailaddressLabel);
		upperPanel.add(emailaddressTextField);

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

	protected void populateFields() {
		nameTextField.setText(address.getName());
		emailaddressTextField.setText(address.getEmailaddress());
	
	}

	protected void retrieveFields() {
		address.setName(nameTextField.getText());
		address.setEmailaddress(emailaddressTextField.getText());
	}
}
