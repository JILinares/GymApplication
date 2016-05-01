package GUI;
import model.*;
import java.lang.Class;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClassSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ArrayList<model.Class> classes;
	private ArrayList<model.Class> newEnroll; //contains changes in class list, if any
//	private HashMap<String,Integer> resolve;
	private Member member;
	private JLabel lblSelectOneOr;
	private ClassTableModel cm;
	
	public ArrayList<model.Class> getNewEnrollment() {return newEnroll;}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClassSelector dialog = new ClassSelector(null,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ClassSelector(Member member, ArrayList<model.Class> classes)
	{	this();
			this.member = member;
			this.classes = classes;
			cm = classes==null ? new ClassTableModel() : new ClassTableModel(classes);
			if (member != null) {
				cm.readEnrollmentOfMember(member);
				lblSelectOneOr.setText(
						lblSelectOneOr.getText() + " " + member.getfName() + " " + member.getlName()
						);}
			
			table.setModel(cm);
	
			//TODO: hashmap classnames to array indexes
			
	}
	
	/**
	 * Create the dialog.
	 */
	public ClassSelector() {
		setModal(true);
		setTitle("Gym");
		setBounds(100, 100, 576, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			lblSelectOneOr = new JLabel("Select one or more classes for: ");
			contentPanel.add(lblSelectOneOr, BorderLayout.NORTH);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setAutoscrolls(true);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new javax.swing.JTable();
				table.setAutoCreateRowSorter(true);
				
				
				
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						newEnroll = cm.getNewEnrollment();
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						newEnroll = null;
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
