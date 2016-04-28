package GUI;
import model.*;
import java.lang.Class;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ClassSelector extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ArrayList<model.Class> classes;
	private Member member;
	private JLabel lblSelectOneOr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClassSelector dialog = new ClassSelector();
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
			//TODO: verify null
			//TODO: append member full name to label
			//TODO: put class fields in table
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
				
				table.setModel(new ClassTableModel());
				
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
