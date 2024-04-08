package Gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import Program.*;

public class Reservation {

	public JFrame frame;
	private JTextField textField5, textField6, textField7, textField1, textField2, textField3, textField4,
			textFieldTotal, textField9, textField10, textField11, textField12, textField13, textField14, RoomConfirm,
			TypeConfirm, BedConfirm, TotalConfirm, CheckInConfirm, CheckOutConfirm;
	private JComboBox<String> comboBoxBed = new JComboBox<String>();
	private JComboBox<String> comboBoxType = new JComboBox<String>();
	private JComboBox<String> comboBoxSelectRoom = new JComboBox<String>();
	private int days, x, y;
	private String ress, users;
	private Date d1, d2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservation window = new Reservation(null, null, null, 0, null);
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
	public Reservation(Date d1, Date d2, String users, int days, String ress) {
		this.days = days;
		this.ress = ress;
		this.users = users;
		this.d1 = d1;
		this.d2 = d2;

		initialize();
		roomSelect();
		setUserData();

		textField5.setText(DateForUse.DateConvert(d1));
		textField6.setText(DateForUse.DateConvert(d2));
		textField7.setText((days) + " Days, " + (days - 1) + " Nights");

	}

	public void setUserData() {
		String Query = String.format("SELECT * FROM hotel.users WHERE UserName = '%s'", users);
		try {
			ResultSet rs = Select.getData(Query);
			rs.next();

			textField1.setText(rs.getString(4) + " " + rs.getString(5));
			textField2.setText(rs.getString(2));
			textField3.setText(rs.getString(7));
			textField4.setText(rs.getString(6));
			textField9.setText(users);
			textField10.setText(rs.getString(4) + " " + rs.getString(5));
			textField11.setText(rs.getString(2));
			textField12.setText(rs.getString(6));
			textField13.setText(rs.getString(7));
			textField14.setText(rs.getString(8));

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);

		}
	}

	public void roomSelect() {

		comboBoxSelectRoom.removeAllItems();
		textFieldTotal.setBackground(new Color(240, 240, 240));
		textFieldTotal.setText(null);
		String bed = (String) comboBoxBed.getSelectedItem();
		String roomType = (String) comboBoxType.getSelectedItem();

		try {
			String qury = String.format("SELECT * FROM hotel.roomdetail WHERE Bed = '%s' and Type = '%s' and %s", bed,
					roomType, ress);
			ResultSet rs = Select.getData(qury);
			while (rs.next()) {
				comboBoxSelectRoom.addItem(rs.getString(1));
				textFieldTotal.setText(Integer.toString((rs.getInt(2) * (days - 1))));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 984, 560);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel Titlebar = new JPanel();
		Titlebar.setLayout(null);
		Titlebar.setBackground(new Color(49, 35, 78));
		Titlebar.setBounds(0, 0, 984, 50);
		frame.getContentPane().add(Titlebar);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
		lblNewLabel_2_1.setIcon(new ImageIcon(Reservation.class.getResource("/images/icons8_close_28px_2.png")));
		lblNewLabel_2_1.setBounds(946, 11, 28, 28);
		Titlebar.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1
				.setIcon(new ImageIcon(Reservation.class.getResource("/images/icons8_minimize_window_28px_1.png")));
		lblNewLabel_2_1_1.setBounds(908, 11, 28, 28);
		Titlebar.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_4 = new JLabel("Moss check Reservation");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_4.setBounds(10, 11, 147, 28);
		Titlebar.add(lblNewLabel_4);

		JPanel card = new JPanel();
		card.setBounds(0, 50, 984, 510);
		frame.getContentPane().add(card);
		card.setLayout(new CardLayout(0, 0));

		JPanel panel1 = new JPanel();
		card.add(panel1, "panel1");
		panel1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name - Surname");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(45, 71, 142, 19);
		panel1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Email Address");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(45, 160, 142, 19);
		panel1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(45, 241, 142, 30);
		panel1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Phone number");
		lblNewLabel_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(45, 332, 142, 25);
		panel1.add(lblNewLabel_3);

		textField5 = new JTextField();
		textField5.setText((String) null);
		textField5.setEditable(false);
		textField5.setColumns(10);
		textField5.setBounds(344, 100, 257, 30);
		panel1.add(textField5);

		textField6 = new JTextField();
		textField6.setText((String) null);
		textField6.setEditable(false);
		textField6.setColumns(10);
		textField6.setBounds(344, 189, 257, 30);
		panel1.add(textField6);

		JLabel lblCheckIn = new JLabel("Check In");
		lblCheckIn.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblCheckIn.setBounds(344, 71, 142, 19);
		panel1.add(lblCheckIn);

		JLabel lblCheckOut = new JLabel("Check Out");
		lblCheckOut.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblCheckOut.setBounds(344, 159, 102, 19);
		panel1.add(lblCheckOut);

		textField7 = new JTextField();
		textField7.setText("1 Days, 0 Nights");
		textField7.setEditable(false);
		textField7.setColumns(10);
		textField7.setBounds(344, 276, 185, 36);
		panel1.add(textField7);

		textField1 = new JTextField();
		textField1.setEditable(false);
		textField1.setColumns(10);
		textField1.setBounds(45, 101, 257, 30);
		panel1.add(textField1);

		textField2 = new JTextField();
		textField2.setEditable(false);
		textField2.setColumns(10);
		textField2.setBounds(45, 190, 257, 30);
		panel1.add(textField2);

		textField3 = new JTextField();
		textField3.setEditable(false);
		textField3.setColumns(10);
		textField3.setBounds(45, 280, 257, 30);
		panel1.add(textField3);

		textField4 = new JTextField();
		textField4.setEditable(false);
		textField4.setColumns(10);
		textField4.setBounds(45, 368, 257, 30);
		panel1.add(textField4);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(708, 0, 276, 510);
		panel1.add(panel);
		panel.setLayout(null);

		comboBoxBed.setBounds(27, 102, 70, 30);
		panel.add(comboBoxBed);
		comboBoxBed.setBorder(null);
		comboBoxBed.setBackground(SystemColor.menu);
		comboBoxBed.setModel(new DefaultComboBoxModel(new String[] { "1", "2" }));

		comboBoxType.setBounds(153, 102, 70, 30);
		panel.add(comboBoxType);
		comboBoxType.setBorder(null);
		comboBoxType.setBackground(SystemColor.menu);
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] { "Duluxe", "Superior", "Suite", "Exclusive" }));

		comboBoxSelectRoom.setBounds(27, 191, 102, 30);
		panel.add(comboBoxSelectRoom);
		comboBoxSelectRoom.setBorder(null);
		comboBoxSelectRoom.setBackground(SystemColor.menu);

		textFieldTotal = new JTextField();
		textFieldTotal.setBounds(27, 278, 185, 36);
		panel.add(textFieldTotal);
		textFieldTotal.setText("1 Days, 0 Nights");
		textFieldTotal.setEditable(false);
		textFieldTotal.setColumns(10);
		textFieldTotal.setBackground(SystemColor.menu);

		JButton Checkin_btn = new JButton("Check in");

		Checkin_btn.setBounds(27, 361, 115, 49);
		panel.add(Checkin_btn);
		Checkin_btn.setBackground(SystemColor.menu);

		JButton Clear_btn = new JButton("Clear");
		Clear_btn.setBounds(153, 361, 115, 49);
		panel.add(Clear_btn);
		Clear_btn.setBackground(SystemColor.menu);

		JLabel lblBed = new JLabel("Bed");
		lblBed.setForeground(Color.BLACK);
		lblBed.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblBed.setBounds(25, 76, 50, 19);
		panel.add(lblBed);

		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.BLACK);
		lblType.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblType.setBounds(153, 76, 70, 19);
		panel.add(lblType);

		JLabel lblSelectedRoom = new JLabel("Selected Room");
		lblSelectedRoom.setForeground(Color.BLACK);
		lblSelectedRoom.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblSelectedRoom.setBounds(25, 161, 156, 19);
		panel.add(lblSelectedRoom);

		JLabel lblTotalbath = new JLabel("Total (Bath) ");
		lblTotalbath.setForeground(Color.BLACK);
		lblTotalbath.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblTotalbath.setBounds(25, 247, 156, 19);
		panel.add(lblTotalbath);

		JPanel panel2 = new JPanel();
		card.add(panel2, "panel2");
		panel2.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(47, 58, 230, 10);
		panel2.add(panel_1);

		JLabel lblNewLabel_5 = new JLabel("User");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(47, 82, 82, 29);
		panel2.add(lblNewLabel_5);

		textField9 = new JTextField();
		textField9.setEditable(false);
		textField9.setBounds(47, 109, 133, 20);
		panel2.add(textField9);
		textField9.setColumns(10);

		JLabel lblNewLabel_5_1 = new JLabel("Name - Surname");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_1.setBounds(190, 82, 133, 29);
		panel2.add(lblNewLabel_5_1);

		textField10 = new JTextField();
		textField10.setEditable(false);
		textField10.setColumns(10);
		textField10.setBounds(190, 109, 133, 20);
		panel2.add(textField10);

		JLabel lblNewLabel_5_2 = new JLabel("Email");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_2.setBounds(47, 134, 82, 29);
		panel2.add(lblNewLabel_5_2);

		textField11 = new JTextField();
		textField11.setEditable(false);
		textField11.setColumns(10);
		textField11.setBounds(47, 161, 133, 20);
		panel2.add(textField11);

		JLabel lblNewLabel_5_3 = new JLabel("Phone");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_3.setBounds(190, 134, 82, 29);
		panel2.add(lblNewLabel_5_3);

		textField12 = new JTextField();
		textField12.setEditable(false);
		textField12.setColumns(10);
		textField12.setBounds(190, 161, 133, 20);
		panel2.add(textField12);

		JLabel lblNewLabel_5_4 = new JLabel("Address");
		lblNewLabel_5_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_4.setBounds(47, 192, 82, 29);
		panel2.add(lblNewLabel_5_4);

		textField13 = new JTextField();
		textField13.setEditable(false);
		textField13.setColumns(10);
		textField13.setBounds(47, 219, 133, 20);
		panel2.add(textField13);

		JLabel lblNewLabel_5_5 = new JLabel("Address+");
		lblNewLabel_5_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_5.setBounds(190, 192, 82, 29);
		panel2.add(lblNewLabel_5_5);

		textField14 = new JTextField();
		textField14.setEditable(false);
		textField14.setColumns(10);
		textField14.setBounds(190, 219, 133, 20);
		panel2.add(textField14);

		JLabel lblNewLabel_5_4_1 = new JLabel("Room");
		lblNewLabel_5_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_4_1.setBounds(47, 303, 82, 29);
		panel2.add(lblNewLabel_5_4_1);

		RoomConfirm = new JTextField();
		RoomConfirm.setEditable(false);
		RoomConfirm.setColumns(10);
		RoomConfirm.setBounds(47, 330, 133, 20);
		panel2.add(RoomConfirm);

		JLabel lblNewLabel_5_4_2 = new JLabel("Type");
		lblNewLabel_5_4_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_4_2.setBounds(190, 303, 82, 29);
		panel2.add(lblNewLabel_5_4_2);

		TypeConfirm = new JTextField();
		TypeConfirm.setEditable(false);
		TypeConfirm.setColumns(10);
		TypeConfirm.setBounds(190, 330, 133, 20);
		panel2.add(TypeConfirm);

		JLabel lblNewLabel_5_4_3 = new JLabel("Bed");
		lblNewLabel_5_4_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_4_3.setBounds(47, 361, 82, 29);
		panel2.add(lblNewLabel_5_4_3);

		BedConfirm = new JTextField();
		BedConfirm.setEditable(false);
		BedConfirm.setColumns(10);
		BedConfirm.setBounds(47, 388, 133, 20);
		panel2.add(BedConfirm);

		JLabel lblNewLabel_5_4_4 = new JLabel("Total(Bath)");
		lblNewLabel_5_4_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_4_4.setBounds(190, 361, 82, 29);
		panel2.add(lblNewLabel_5_4_4);

		TotalConfirm = new JTextField();
		TotalConfirm.setEditable(false);
		TotalConfirm.setColumns(10);
		TotalConfirm.setBounds(190, 388, 133, 20);
		panel2.add(TotalConfirm);

		JLabel lblNewLabel_5_6 = new JLabel("Please confirm your check in");
		lblNewLabel_5_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_6.setBounds(47, 31, 204, 29);
		panel2.add(lblNewLabel_5_6);

		JButton Confirm_btn = new JButton("Confirm");

		Confirm_btn.setBounds(47, 423, 89, 46);
		panel2.add(Confirm_btn);

		JButton Cancle2_btn = new JButton("Cancle");

		Cancle2_btn.setBounds(190, 423, 89, 46);
		panel2.add(Cancle2_btn);

		JLabel lblNewLabel_5_7 = new JLabel("");
		lblNewLabel_5_7.setIcon(new ImageIcon(Reservation.class.getResource("/images/Untitled design.png")));
		lblNewLabel_5_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_7.setBounds(375, 0, 609, 510);
		panel2.add(lblNewLabel_5_7);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(367, 0, 10, 510);
		panel2.add(panel_2);

		JLabel lblNewLabel_5_4_1_1 = new JLabel("Check in");
		lblNewLabel_5_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_4_1_1.setBounds(47, 250, 82, 29);
		panel2.add(lblNewLabel_5_4_1_1);

		CheckInConfirm = new JTextField();
		CheckInConfirm.setEditable(false);
		CheckInConfirm.setColumns(10);
		CheckInConfirm.setBounds(47, 277, 133, 20);
		panel2.add(CheckInConfirm);

		JLabel lblNewLabel_5_4_2_1 = new JLabel("Check out");
		lblNewLabel_5_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5_4_2_1.setBounds(190, 250, 82, 29);
		panel2.add(lblNewLabel_5_4_2_1);

		CheckOutConfirm = new JTextField();
		CheckOutConfirm.setEditable(false);
		CheckOutConfirm.setColumns(10);
		CheckOutConfirm.setBounds(190, 277, 133, 20);
		panel2.add(CheckOutConfirm);

		CardLayout cardlay = (CardLayout) card.getLayout();

		comboBoxBed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomSelect();
			}
		});

		comboBoxType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomSelect();
			}
		});
		Titlebar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();
				frame.setLocation(xx - x, yy - y);
			}
		});
		Titlebar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}

		});
		Checkin_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RoomConfirm.setText((String) comboBoxSelectRoom.getSelectedItem());
				TypeConfirm.setText((String) comboBoxType.getSelectedItem());
				BedConfirm.setText((String) comboBoxBed.getSelectedItem());
				TotalConfirm.setText(textFieldTotal.getText());
				textField9.setText(users);
				CheckInConfirm.setText(textField5.getText());
				CheckOutConfirm.setText(textField6.getText());
				cardlay.show(card, "panel2");
			}
		});
		Confirm_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateForUse OBJ = new DateForUse(d1, d2);
				String today = new Date().toString();
				InsertUpdateDelete.setData(
						"UPDATE hotel.roomdetail SET" + OBJ.setStringforUpdate(users) + " WHERE RoomNum = '"
								+ RoomConfirm.getText() + "'",
						"Please make a payment to "
								+ "413-137615-8 SCB (you can see this in your user menu) in 24 hours");
				String payQuery = String.format(
						"INSERT INTO hotel.paymentdata (user,roomnum,total,checkin,checkout,paymentdate) VALUES ('%s','%s','%s','%s','%s','%s')",
						users, RoomConfirm.getText(), TotalConfirm.getText(), CheckInConfirm.getText(),
						CheckOutConfirm.getText(), today);
				InsertUpdateDelete.setData(payQuery, "Check In success");
				HPage obj = new HPage(users);
				frame.setVisible(false);
				obj.frame.setVisible(true);
			}
		});
		Cancle2_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HPage obj = new HPage(users);
				frame.setVisible(false);
				obj.frame.setVisible(true);
			}
		});
	}
}
