package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import model.AbstractAddress;
import model.AddressList;
import model.EmailonlyAddress;
import model.PostalAddress;

@SuppressWarnings("serial")
public class AddressListView extends JFrame implements Observer{

	private AddressList addressList;
	private DefaultListModel listModel;

	public AddressListView() {
		this.addressList = new AddressList();
		addressList.addObserver(this);
		init();
		populateFields();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void init() {
		this.setTitle("Address-List");

		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.weighty = 0.9;

		listModel = new DefaultListModel();
		JList list = new JList(listModel);
		JScrollPane scrollpane = new JScrollPane(list);

		this.add(scrollpane, constraints);

		JButton addEmailonlyButton = new JButton("Add Emailonly");
		JButton addPostalAddressButton = new JButton("Add PostalAddress");
		final AddressList alv = addressList;
		addEmailonlyButton.addActionListener(new ActionListener() {
			/**
			 * Wählen Sie für diese anonyme Klasse AddButtonActionListener als
			 * Klassenamen.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EmailonlyAddress address = new EmailonlyAddress();
				new AddressView(address, alv);
			}
		});
		addPostalAddressButton.addActionListener(new ActionListener() {
			/**
			 * Wählen Sie für diese anonyme Klasse AddButtonActionListener als
			 * Klassenamen.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PostalAddress address = new PostalAddress();
				new PostalAddressView(address, alv);
				//TODO PostalAddressView
			}
		});
		constraints.weighty = 0.1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(addEmailonlyButton, constraints);
		constraints.weighty = 0.1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		this.add(addPostalAddressButton, constraints);
		JButton saveButton = new JButton("Save all");

		saveButton.addActionListener(new ActionListener() {
			/**
			 * Wählen Sie für diese anonyme Klasse SaveButtonActionListener als
			 * Klassenamen.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileOutputStream fos = null;
				ObjectOutputStream out = null;
				try {
					fos = new FileOutputStream(("data.ser"));
					out = new ObjectOutputStream(fos);
					out.writeObject(addressList.getAddressList());
					out.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		constraints.gridx = 1;
		constraints.gridy = 1;
		this.add(saveButton, constraints);
        this.setLocationRelativeTo(null);
		this.pack();
	}

	

	private void populateFields() {
		refreshAddressList();
	}

	private void refreshAddressList() {
		listModel.removeAllElements();
		for (AbstractAddress address : addressList.getAddressList()) {
			listModel.addElement(address.toString());
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.addressList = (AddressList)arg0;
		refreshAddressList();
		
	}
}
