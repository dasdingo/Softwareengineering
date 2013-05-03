package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import model.AbstractAddress;
import model.AddressList;
import model.EmailonlyAddress;
import model.HighBudgetVerhalten;
import model.LowBudgetVerhalten;
import model.PostalAddress;
import model.SendMessageStrategy;

@SuppressWarnings("serial")
public class AddressListView extends JFrame implements Observer{

	public static int AddressSendCount = 1;
	private AddressList addressList;
	private DefaultListModel listModel;
    private SendMessageStrategy SendMessageVerhalten;
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

		listModel = new DefaultListModel<AbstractAddress>();
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
				new EmailAddressView(address, alv);
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
		constraints.gridx = 0; 
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		JPanel strategyPanel = new JPanel();
		strategyPanel.setBorder(BorderFactory.createTitledBorder("Strategie"));
		strategyPanel.setLayout(new BoxLayout(strategyPanel, BoxLayout.X_AXIS));
		final JRadioButton lowBudgetButton = new JRadioButton("Wenig Geld");
		lowBudgetButton.setSelected(true);
		strategyPanel.add(lowBudgetButton);
		JRadioButton highBudgetButton = new JRadioButton("Viel Geld");
		strategyPanel.add(highBudgetButton);
		JButton sendButton = new JButton("Nachricht verschicken");
		sendButton.addActionListener(new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
			 if (lowBudgetButton.isSelected()){
				for(AbstractAddress address : addressList.getAddressList()){
				System.out.println(AddressSendCount);
				AddressSendCount++;
					address.setSendMessageVerhalten(new LowBudgetVerhalten());	
					address.nachrichtSenden();
				}
			 }
			 else {
				 for(AbstractAddress address : addressList.getAddressList()){
					 System.out.println(AddressSendCount);
						AddressSendCount++;	
					 address.setSendMessageVerhalten(new HighBudgetVerhalten());	
							address.nachrichtSenden();
						}
		  }
		  AddressSendCount = 1;
		  }});
		strategyPanel.add(sendButton);

		// Low- und High-Budget Buttons exclusiv verbinden
		ButtonGroup group = new ButtonGroup();
		group.add(lowBudgetButton);
		group.add(highBudgetButton);

		this.add(strategyPanel,constraints);
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
