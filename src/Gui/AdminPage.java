package Gui;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.EventQueue;
import javax.swing.table.DefaultTableModel;
import Program.InsertUpdateDelete;
import Program.Select;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.Color;

public class AdminPage {

	public JFrame frame;
	private int index,indexRowSelect;
	String colname = "";
	String rowname = "";
	String userValuePayment = "";
	String paymentdateValuePayment = "";
	private JTable tableMain;
	private JTable tableUser;
	private JTable tablePayment;
	private String RSroomdetail = "SELECT * FROM hotel.roomdetail";
	private String colNumroomdetail = "SELECT COUNT(*) FROM information_schema.columns WHERE table_schema = 'check' AND table_name = 'roomdetail'";
	private String columnRSroomdetail = "SELECT COLUMN_NAME FROM information_schema.columns WHERE table_schema = 'check' AND table_name = 'roomdetail' ORDER BY ORDINAL_POSITION ASC";
	private String RSusers = "SELECT * FROM hotel.users";
	private String colNumusers = "SELECT COUNT(*) FROM information_schema.columns WHERE table_schema = 'check' AND table_name = 'users'";
	private String columnRSusers = "SELECT COLUMN_NAME FROM information_schema.columns WHERE table_schema = 'check' AND table_name = 'users' ORDER BY ORDINAL_POSITION ASC";
	private String RSpayment = "SELECT * FROM hotel.paymentdata";
	private String colNumpayment = "SELECT COUNT(*) FROM information_schema.columns WHERE table_schema = 'check' AND table_name = 'paymentdata'";
	private String columnRSpayment = "SELECT COLUMN_NAME FROM information_schema.columns WHERE table_schema = 'check' AND table_name = 'paymentdata' ORDER BY ORDINAL_POSITION ASC";
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage window = new AdminPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminPage() {
		initialize();
		showTable(tableMain,RSroomdetail,colNumroomdetail,columnRSroomdetail);
		showTable(tableUser,RSusers,colNumusers,columnRSusers);
		showTable(tablePayment,RSpayment,colNumpayment,columnRSpayment);
	}
	
	public void showTable(JTable table, String RS, String colNum, String colRs) {
		ResultSet Rs = Select.getData(RS);
		ResultSet colNumRs = Select.getData(colNum);
		ResultSet columnRs = Select.getData(colRs);
		DefaultTableModel model = (DefaultTableModel)table.getModel(); 
		int cc = 0;
		model.setColumnCount(0);
		model.setRowCount(0);
				try {
				while (columnRs.next()) {
				    model.addColumn(columnRs.getString(1));
				    System.out.println(columnRs.getString(1));
				}
				
				while(colNumRs.next()) {
				cc = colNumRs.getInt(1);
				System.out.println(cc + " column now");
				}
				
				while(Rs.next()) {
					 Object[] row = new Object[cc];
					for (int i = 1; i <= cc; i++) {
				        row[i-1] = Rs.getObject(i);
				    }
				    model.addRow(row);
				}		
			}
			catch(Exception c) {
				JOptionPane.showMessageDialog(null, c);
				}
	}
	
	

	
	private void initialize() {
		frame = new JFrame();		
		frame.setBounds(100, 100, 984, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(0, 0, 968, 104);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnManageRoom = new JButton("Manage Room");
		
		btnManageRoom.setBounds(34, 29, 111, 48);
		panel_1.add(btnManageRoom);
		
		JButton btnUserData = new JButton("User Data");
		
		btnUserData.setBounds(155, 29, 111, 48);
		panel_1.add(btnUserData);
		
		JButton btnPayment = new JButton("Payment");
		
		btnPayment.setBounds(276, 29, 111, 48);
		panel_1.add(btnPayment);
		
		JPanel card = new JPanel();
		card.setBounds(0, 103, 968, 418);
		frame.getContentPane().add(card);
		card.setLayout(new CardLayout(0, 0));
		
		JPanel Main = new JPanel();
		card.add(Main, "Main");
		Main.setLayout(null);
		
		JScrollPane spMain = new JScrollPane();
		spMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spMain.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spMain.setBounds(31, 31, 653, 376);
		Main.add(spMain);
		
		tableMain = new JTable();
		
		tableMain.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		spMain.setViewportView(tableMain);
	
		
		JButton deleteBtn = new JButton("Delete");
		
		deleteBtn.setBounds(717, 31, 135, 48);
		Main.add(deleteBtn);
		
		JButton addNewDateBtn = new JButton("Add New Date");
		
		addNewDateBtn.setBounds(717, 90, 135, 48);
		Main.add(addNewDateBtn);
		
		JButton DeleteOldDateBtn = new JButton("Delete Old Date");
		
		DeleteOldDateBtn.setBounds(717, 151, 135, 48);
		Main.add(DeleteOldDateBtn);
		
		JPanel User = new JPanel();
		User.setLayout(null);
		card.add(User, "User");
		
		JScrollPane spUser = new JScrollPane();
		spUser.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spUser.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spUser.setBounds(33, 31, 653, 376);
		User.add(spUser);
		
		tableUser = new JTable();
		tableUser.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spUser.setViewportView(tableUser);
		
		JButton deleteBtn_1 = new JButton("Delete");
		deleteBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		deleteBtn_1.setBounds(721, 31, 135, 48);
		User.add(deleteBtn_1);
		
		CardLayout cardlay = (CardLayout)card.getLayout();
		
		JPanel Payment = new JPanel();
		Payment.setLayout(null);
		card.add(Payment, "Payment");
		
		JScrollPane spPayment = new JScrollPane();
		spPayment.setBounds(33, 31, 653, 376);
		Payment.add(spPayment);
		
		tablePayment = new JTable();
		
//		tablePayment.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		spPayment.setViewportView(tablePayment);
		
		JButton deleteBtn_1_1 = new JButton("Delete");
		deleteBtn_1_1.setBounds(721, 31, 135, 48);
		Payment.add(deleteBtn_1_1);
		
		JButton updateBtn = new JButton("Update Status");
		
		updateBtn.setBounds(721, 90, 135, 48);
		Payment.add(updateBtn);
		
		tableMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = tableMain.getSelectedColumn();
				colname = tableMain.getColumnName(index);
				System.out.println(colname);
			}
		});
		tablePayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				indexRowSelect = tablePayment.getSelectedRow();
				userValuePayment = (String)tablePayment.getValueAt(indexRowSelect, 0);
				paymentdateValuePayment = (String)tablePayment.getValueAt(indexRowSelect, 5);
				System.out.println(userValuePayment);
				System.out.println(paymentdateValuePayment);
			}
		});
		
		
		deleteBtn_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int  a = JOptionPane.showConfirmDialog(null,"Are you want to delete "+userValuePayment+" at paytime: "+paymentdateValuePayment, "Confirm delete", JOptionPane.YES_NO_OPTION);
				if(a == JOptionPane.YES_OPTION) {
				String query = String.format("DELETE FROM hotel.paymentdata WHERE user = '%s' and paymentdate = '%s'",userValuePayment,paymentdateValuePayment);
				InsertUpdateDelete.setData(query, "Delete Success");
				showTable(tablePayment,RSpayment,colNumpayment,columnRSpayment);
			}
			}});
		
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a =JOptionPane.showConfirmDialog(null, "Do you want to change status of "+userValuePayment+" at "+paymentdateValuePayment , "CONFIRM CHANGE", JOptionPane.YES_NO_OPTION, 2);
				if(a==0) {
				String query = String.format("UPDATE hotel.paymentdata SET Status = 'Approve' WHERE user = '%s' and paymentdate = '%s'", userValuePayment, paymentdateValuePayment);
			    InsertUpdateDelete.setData(query, "Update Success");		       
			    showTable(tablePayment,RSpayment,colNumpayment,columnRSpayment);
				}
				else {		    	
			}		
			}
		});
		
		deleteBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    int a =JOptionPane.showConfirmDialog(null, "Do you want to delete "+colname+" column", "CONFIRM DELETE", JOptionPane.YES_NO_OPTION, 2);
		    if(a==0) {
		
	        InsertUpdateDelete.setData("ALTER TABLE hotel.roomdetail DROP COLUMN `"+colname+"`", "Delete Success");		       
	        showTable(tableMain,RSroomdetail,colNumroomdetail,columnRSroomdetail);
		    }
		    else{}
		    }
		});
		addNewDateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet rs = Select.getData("SELECT COLUMN_NAME,ORDINAL_POSITION FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'check'AND TABLE_NAME ='roomdetail'ORDER BY ORDINAL_POSITION DESC LIMIT 1");
				String lastCol = "";
				try{
					rs.next(); 
					lastCol = rs.getString(1);
					System.out.println(lastCol);
				}
				catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		        LocalDate date = LocalDate.parse(lastCol, formatter);
		        LocalDate nextDate = date.plusDays(1);
		        String newCol = nextDate.format(formatter);
		        
		        
				InsertUpdateDelete.setData("ALTER TABLE hotel.roomdetail ADD COLUMN `"+newCol+"` VARCHAR(50)", "Add "+newCol+" Success");
//				System.out.println("The Date Added: " + newCol);
				showTable(tableMain,RSroomdetail,colNumroomdetail,columnRSroomdetail);

			}
		});
		DeleteOldDateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String deleteCol = "";
				ResultSet rs = Select.getData("SELECT COLUMN_NAME,ORDINAL_POSITION FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = 'check'AND TABLE_NAME ='roomdetail'ORDER BY ORDINAL_POSITION Limit 5,1");
				try{
					rs.next(); 
					deleteCol = rs.getString(1);
					System.out.println(deleteCol);
				}
				catch(Exception c) {
					JOptionPane.showMessageDialog(null, c);
				}
				int a =JOptionPane.showConfirmDialog(null, "Do you want to delete "+deleteCol+" column", "CONFIRM DELETE", JOptionPane.YES_NO_OPTION, 2);
				if(a==0) {
					InsertUpdateDelete.setData("ALTER TABLE hotel.roomdetail DROP COLUMN `"+deleteCol+"`", "Delete "+ deleteCol + " Success");		       
					showTable(tableMain,RSroomdetail,colNumroomdetail,columnRSroomdetail);
		        }
				else {
				}
			}
		});
		btnUserData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlay.show(card, "User");
			}
		});
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlay.show(card, "Payment");
			}
		});
		btnManageRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlay.show(card, "Main");
			}
		});
		
	}
}
