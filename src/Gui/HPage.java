package Gui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.net.URI;
import java.sql.*;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import Program.*;
import javax.swing.border.*;

public class HPage {
	public JFrame frame;
	private JPanel HP_btn = new JPanel();
	private JPanel RE_btn = new JPanel();
	private JPanel RM_btn = new JPanel();
	private JPanel Lc_btn = new JPanel();
	private JPanel Gal_btn = new JPanel();
	private JPanel panel_3_1 = new JPanel();
	private JPanel panel_3_2 = new JPanel();
	private JPanel panel_3_3 = new JPanel();
	private JPanel panel_3_4 = new JPanel();
	private JComboBox roomBox = new JComboBox();
	private JLabel checkinLabel = new JLabel("date1");
	private JLabel statusLabel = new JLabel("Wait for admin");
	private JLabel checkoutLabel = new JLabel("date1");
	private boolean hp_ms = false;
	private boolean rs_ms = true;
	private boolean rm_ms = true;
	private boolean lc_ms = true;
	private boolean gl_ms = true;
	private boolean us_ms = true;
	private boolean a;
	private int x;
	private int y;
	private String user;
	private Calendar cal = Calendar.getInstance();
	private Calendar cal2 = Calendar.getInstance();
	private JTextField dataNameField = new JTextField();
	private JTextField dataSurnameField = new JTextField();
	private JTextField dataPhoneField = new JTextField();
	private JTextField dataEmailField = new JTextField();
	private JTextField dataAddress1Field = new JTextField();
	private JTextField dataAddress2Field = new JTextField();
	private JTextField dataDistrictField = new JTextField();
	private JTextField dataProvinceField  = new JTextField();
	private JTextField textFieldNamenoUser;
	private JTextField textFieldSurnamenoUser;
	private JTextField textFieldPhonenoUser;
	private JTextField textFieldEmailnoUser;
	private JTextField textFieldAddressnoUser;
	private JTextField textFieldAddressPlusnoUser;
	private JTextField textFieldDistrictnoUser;
	private JTextField textFieldProvincenoUser;
	private JPanel card = new JPanel();
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HPage window = new HPage(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HPage(String user) {
		this.user = user;

		initialize();
		myRoom();
		setUserData();
	}
	
	public void myRoom() {
		
		roomBox.removeAllItems();
		String Query = String.format("SELECT * FROM hotel.paymentdata WHERE user = '%s'", user);	
		try {
			ResultSet check = Select.getData(Query);
			a = check.next();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		if(a) {
		try {
			ResultSet rs = Select.getData(Query);
			while(rs.next()) {
			roomBox.addItem(rs.getString(2));		
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		myRoomDetail();
		}
		
	}
	
	public void myRoomDetail() {
		String roomnum = (String)roomBox.getSelectedItem();
		String Query = String.format("SELECT * FROM hotel.paymentdata WHERE user = '%s' and roomnum = '%s'", user,roomnum);
		if(a) {
		try {
			ResultSet rs = Select.getData(Query);
			rs.next(); 
		    if(rs.getString(7) == null) {
		    	statusLabel.setText("Wait for admin");
		    	statusLabel.setForeground(Color.RED);
		    }else {
		    	statusLabel.setText(rs.getString(7));
		    	statusLabel.setForeground(new Color (9,232,36));		 
		    }	
			checkinLabel.setText(rs.getString(4));
			checkoutLabel.setText(rs.getString(5));
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}}
	}
	
	public void setUserData() {
		String Query = String.format("SELECT * FROM hotel.users WHERE UserName = '%s' ", user); 
		try {
			ResultSet rs = Select.getData(Query);
			rs.next();
			if(a) {		
			dataEmailField.setText(rs.getString(2));
			dataNameField.setText(rs.getString(4));
			dataSurnameField.setText(rs.getString(5));
			dataPhoneField.setText(rs.getString(6));
			dataAddress1Field.setText(rs.getString(7));
			dataAddress2Field.setText(rs.getString(8));
			dataDistrictField.setText(rs.getString(9));
			dataProvinceField.setText(rs.getString(10));}
			else {
			textFieldNamenoUser.setText(rs.getString(4));
			textFieldSurnamenoUser.setText(rs.getString(5));
			textFieldEmailnoUser.setText(rs.getString(2));
			textFieldPhonenoUser.setText(rs.getString(6));
			textFieldAddressnoUser.setText(rs.getString(7));
			textFieldAddressPlusnoUser.setText(rs.getString(8));
			textFieldDistrictnoUser.setText(rs.getString(9));
			textFieldProvincenoUser.setText(rs.getString(10));}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void setMouselist(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f) {
		hp_ms = a;
		rs_ms = b;
		rm_ms = c;
		lc_ms = d;
		gl_ms = e;
		us_ms = f;
		
	}
	
	public void setColor(JPanel jp) {
		jp.setBackground(new Color (255,255,255));
	}
	
	public void setCOLOR(JPanel jp) {
		jp.setBackground(new Color (248,248,248));
	}
	
	public void setcolor(JPanel jp) {
		jp.setBackground(Color.DARK_GRAY);
	}
	
	public void resetColor(JPanel jp) {
		jp.setBackground(new Color (240,240,240));
	}
			
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 984, 560);
		frame.setUndecorated(true); 
		frame.getContentPane().setLayout(null);	
				
	
		JPanel Titlebar = new JPanel();
		
		Titlebar.setBackground(new Color(49, 35, 78));
		Titlebar.setBounds(0, 0, 984, 50);
		frame.getContentPane().add(Titlebar);
		Titlebar.setLayout(null);
		
		JLabel CloseIcon = new JLabel("");
		CloseIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		CloseIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to exit?",
						"Exit application", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		CloseIcon.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_close_28px_2.png")));
		CloseIcon.setBounds(946, 11, 28, 28);
		Titlebar.add(CloseIcon);
		
		JLabel MinimizeIcon = new JLabel("");
		MinimizeIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		MinimizeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);

			}
		});
		MinimizeIcon.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_minimize_window_28px_1.png")));
		MinimizeIcon.setBounds(908, 11, 28, 28);
		Titlebar.add(MinimizeIcon);
		
		JLabel Textbar = new JLabel("Purple Horizon check");
		Textbar.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
		Textbar.setForeground(new Color(255, 255, 255));
		Textbar.setBounds(10, 11, 193, 28);
		Titlebar.add(Textbar);
		
		JPanel paneltab = new JPanel();
		paneltab.setBackground(new Color(97, 75, 154));
		paneltab.setBounds(0, 0, 232, 561);
		frame.getContentPane().add(paneltab);
		paneltab.setLayout(null);
		HP_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));				
		
		HP_btn.setBounds(0, 194, 232, 44);
		paneltab.add(HP_btn);
		HP_btn.setLayout(null);
		
		JLabel HPlb = new JLabel("Home Page");
		HPlb.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		HPlb.setHorizontalAlignment(SwingConstants.LEFT);
		HPlb.setBounds(67, 11, 96, 22);
		HP_btn.add(HPlb);
		HP_btn.setBackground(new Color(255, 255, 255));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		panel_3.setBounds(0, 0, 5, 44);
		HP_btn.add(panel_3);
		
		JLabel HPicon = new JLabel("");
		HPicon.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_home_22px.png")));
		HPicon.setHorizontalAlignment(SwingConstants.CENTER);
		HPicon.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		HPicon.setBounds(35, 11, 22, 22);
		HP_btn.add(HPicon);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(83, 52, 112));
		panel_1.setBounds(0, 0, 232, 226);
		paneltab.add(panel_1);
		
		JLabel LogoTab = new JLabel("");
		LogoTab.setIcon(new ImageIcon(HPage.class.getResource("/images/purple-horizon-high-resolution-logo-color-on-transparent-background (3).png")));
		LogoTab.setHorizontalAlignment(SwingConstants.CENTER);
		LogoTab.setBounds(0, 52, 232, 143);
		panel_1.add(LogoTab);
		RE_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		
		RE_btn.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		RE_btn.setBounds(0, 238, 232, 44);
		paneltab.add(RE_btn);
		RE_btn.setLayout(null);
		
		JLabel RElb = new JLabel("Reserve");
		RElb.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		RElb.setHorizontalAlignment(SwingConstants.LEFT);
		RElb.setBounds(69, 11, 96, 22);
		RE_btn.add(RElb);
		
		
		panel_3_1.setBackground(new Color(240, 240, 240));
		panel_3_1.setBounds(0, 0, 5, 44);
		RE_btn.add(panel_3_1);
		
		JLabel REicon = new JLabel("");
		REicon.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_hotel_check_in_24px.png")));
		REicon.setHorizontalAlignment(SwingConstants.CENTER);
		REicon.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		REicon.setBounds(35, 11, 22, 22);
		RE_btn.add(REicon);
		RM_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		
		RM_btn.setAlignmentY(1.0f);
		RM_btn.setBounds(0, 282, 232, 44);
		paneltab.add(RM_btn);
		RM_btn.setLayout(null);
		
		JLabel Roomlb = new JLabel("Room");
		Roomlb.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		Roomlb.setHorizontalAlignment(SwingConstants.LEFT);
		Roomlb.setBounds(69, 11, 96, 22);
		RM_btn.add(Roomlb);
		
		
		panel_3_2.setBackground(new Color(240, 240, 240));
		panel_3_2.setBounds(0, 0, 5, 44);
		RM_btn.add(panel_3_2);
		
		JLabel Roomicon = new JLabel("");
		Roomicon.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_room_24px.png")));
		Roomicon.setHorizontalAlignment(SwingConstants.CENTER);
		Roomicon.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		Roomicon.setBounds(35, 11, 22, 22);
		RM_btn.add(Roomicon);
		Lc_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		
		Lc_btn.setAlignmentY(1.0f);
		Lc_btn.setBounds(0, 325, 232, 44);
		paneltab.add(Lc_btn);
		Lc_btn.setLayout(null);
		
		JLabel Locationlb = new JLabel("Location");
		Locationlb.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		Locationlb.setHorizontalAlignment(SwingConstants.LEFT);
		Locationlb.setBounds(69, 11, 96, 22);
		Lc_btn.add(Locationlb);
		

		panel_3_3.setBackground(new Color(240, 240, 240));
		panel_3_3.setBounds(0, 0, 5, 44);
		Lc_btn.add(panel_3_3);
		
		JLabel Locationicon = new JLabel("");
		Locationicon.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_location_24px.png")));
		Locationicon.setHorizontalAlignment(SwingConstants.CENTER);
		Locationicon.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		Locationicon.setBounds(35, 11, 22, 22);
		Lc_btn.add(Locationicon);
		Gal_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		
		Gal_btn.setLayout(null);
		Gal_btn.setAlignmentY(1.0f);
		Gal_btn.setBounds(0, 369, 232, 44);
		paneltab.add(Gal_btn);
		
		JLabel Gallerylb = new JLabel("Gallery");
		Gallerylb.setHorizontalAlignment(SwingConstants.LEFT);
		Gallerylb.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		Gallerylb.setBounds(69, 11, 96, 22);
		Gal_btn.add(Gallerylb);
		

		panel_3_4.setBackground(SystemColor.menu);
		panel_3_4.setBounds(0, 0, 5, 44);
		Gal_btn.add(panel_3_4);
		
		JLabel Galleryicon = new JLabel("");
		Galleryicon.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_image_gallery_24px.png")));
		Galleryicon.setHorizontalAlignment(SwingConstants.CENTER);
		Galleryicon.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		Galleryicon.setBounds(35, 11, 22, 22);
		Gal_btn.add(Galleryicon);
		
		JLabel Logouticon = new JLabel("");
		
		Logouticon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Logouticon.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_logout_48px.png")));
		Logouticon.setHorizontalAlignment(SwingConstants.CENTER);
		Logouticon.setBounds(93, 504, 46, 46);
		paneltab.add(Logouticon);
		
		JLabel UserText = new JLabel("User :");
		UserText.setForeground(new Color(255, 255, 255));
		UserText.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		UserText.setBounds(10, 465, 192, 14);
		UserText.setText(String.format("Welcome %s", user));
		paneltab.add(UserText);
		
		JPanel User_btn = new JPanel();
		User_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		User_btn.setLayout(null);
		User_btn.setAlignmentY(1.0f);
		User_btn.setBounds(0, 413, 232, 44);
		paneltab.add(User_btn);
		
		JLabel Userlb = new JLabel("User");
		Userlb.setHorizontalAlignment(SwingConstants.LEFT);
		Userlb.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		Userlb.setBounds(69, 11, 96, 22);
		User_btn.add(Userlb);
		
		JPanel panel_3_5 = new JPanel();
		panel_3_5.setBackground(SystemColor.menu);
		panel_3_5.setBounds(0, 0, 5, 44);
		User_btn.add(panel_3_5);
		
		JLabel Usericon = new JLabel("");
		Usericon.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_user_24px.png")));
		Usericon.setHorizontalAlignment(SwingConstants.CENTER);
		Usericon.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		Usericon.setBounds(35, 11, 22, 22);
		User_btn.add(Usericon);
		cal2.add(Calendar.MONTH, 1);
		
		
		card.setBounds(242, 50, 742, 510);
		frame.getContentPane().add(card);
		card.setLayout(new CardLayout(0, 0));
		
		JPanel Homepage = new JPanel();
		Homepage.setLayout(null);
		Homepage.setBackground(SystemColor.menu);
		card.add(Homepage, "HomePage");
		
		JLabel HomepageImg = new JLabel("");
		HomepageImg.setIcon(new ImageIcon(HPage.class.getResource("/images/Picture Home.png")));
		HomepageImg.setHorizontalAlignment(SwingConstants.CENTER);
		HomepageImg.setFont(new Font("Tahoma", Font.BOLD, 18));
		HomepageImg.setBounds(0, 0, 742, 510);
		Homepage.add(HomepageImg);
		
		JPanel Reserve = new JPanel();
		Reserve.setLayout(null);
		Reserve.setBackground(SystemColor.menu);
		card.add(Reserve, "Reserve");
		
		JDateChooser CheckinChooser = new JDateChooser();
		CheckinChooser.getCalendarButton().setBackground(Color.LIGHT_GRAY);
		CheckinChooser.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		CheckinChooser.setDateFormatString("MM/dd/yy");
		CheckinChooser.setBorder(null);
		CheckinChooser.setBackground(new Color(97, 75, 154));
		CheckinChooser.setBounds(51, 253, 224, 53);
		Reserve.add(CheckinChooser);
		
		JPanel panel1RE = new JPanel();
		panel1RE.setLayout(null);
		panel1RE.setBackground(Color.LIGHT_GRAY);
		panel1RE.setBounds(0, -50, 742, 162);
		Reserve.add(panel1RE);
		
		JLabel lblNewLabel_5 = new JLabel("Reserve");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 47));
		lblNewLabel_5.setBounds(90, 61, 194, 90);
		panel1RE.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 47));
		lblNewLabel_5_1.setBounds(10, 75, 60, 60);
		panel1RE.add(lblNewLabel_5_1);
		
		JDateChooser CheckoutChooser = new JDateChooser();
		CheckoutChooser.getCalendarButton().setBackground(Color.LIGHT_GRAY);
		CheckoutChooser.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
		CheckoutChooser.setDateFormatString("MM/dd/yy");
		CheckoutChooser.setBorder(null);
		CheckoutChooser.setBackground(new Color(97, 75, 154));
		CheckoutChooser.setBounds(442, 253, 224, 53);
		Reserve.add(CheckoutChooser);
		
		JLabel Checkinlbl = new JLabel("Check In");
		Checkinlbl.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		Checkinlbl.setBounds(51, 210, 80, 32);
		Reserve.add(Checkinlbl);
		
		JLabel Checkoutlbl = new JLabel("Check Out");
		Checkoutlbl.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		Checkoutlbl.setBounds(442, 210, 95, 32);
		Reserve.add(Checkoutlbl);
		
		JPanel panel2RE = new JPanel();
		panel2RE.setBackground(Color.WHITE);
		panel2RE.setBounds(29, 164, 317, 10);
		Reserve.add(panel2RE);
		
		JLabel Choosedatelbl = new JLabel("Choose a date with calendar");
		Choosedatelbl.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		Choosedatelbl.setBounds(29, 131, 246, 32);
		Reserve.add(Choosedatelbl);
		
		JLabel lblNewLabel_6_3 = new JLabel("");
		lblNewLabel_6_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 17));
		lblNewLabel_6_3.setBounds(264, 130, 32, 32);
		Reserve.add(lblNewLabel_6_3);
		
		JLabel mmddyy1lbl = new JLabel("* MM/DD/YY");
		mmddyy1lbl.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		mmddyy1lbl.setBounds(51, 307, 80, 14);
		Reserve.add(mmddyy1lbl);
		
		JLabel mmddyy2lbl = new JLabel("* MM/DD/YY");
		mmddyy2lbl.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		mmddyy2lbl.setBounds(442, 307, 80, 14);
		Reserve.add(mmddyy2lbl);
		
		JButton nextBtn = new JButton("NEXT");
		
		nextBtn.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 13));
		nextBtn.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		nextBtn.setBackground(Color.LIGHT_GRAY);
		nextBtn.setBounds(283, 369, 152, 53);
		Reserve.add(nextBtn);
		
		JPanel Location = new JPanel();
		card.add(Location, "Location");
		Location.setLayout(null);
		
		JButton googleMapBtn = new JButton("Google Map");
		
		googleMapBtn.setBounds(570, 439, 132, 36);
		Location.add(googleMapBtn);
		
		JLabel Map_pic = new JLabel("");
		Map_pic.setIcon(new ImageIcon(HPage.class.getResource("/images/maplink.png")));
		Map_pic.setBounds(0, 0, 742, 403);
		Location.add(Map_pic);
		
		JLabel iconLoca = new JLabel("");
		iconLoca.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_location_24px.png")));
		iconLoca.setHorizontalAlignment(SwingConstants.CENTER);
		iconLoca.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		iconLoca.setBounds(37, 428, 22, 22);
		Location.add(iconLoca);
		
		JLabel iconContract = new JLabel("");
		iconContract.setIcon(new ImageIcon(HPage.class.getResource("/images/icons8_phone_22px.png")));
		iconContract.setHorizontalAlignment(SwingConstants.CENTER);
		iconContract.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		iconContract.setBounds(37, 461, 22, 22);
		Location.add(iconContract);
		
		JLabel lblChalongKrung = new JLabel("1 Chalong Krung 1 Alley, Lat Krabang, Bangkok 10520");
		lblChalongKrung.setHorizontalAlignment(SwingConstants.LEFT);
		lblChalongKrung.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblChalongKrung.setBounds(69, 428, 336, 22);
		Location.add(lblChalongKrung);
		
		JLabel lblChalongKrung_1 = new JLabel("02 329 8000");
		lblChalongKrung_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblChalongKrung_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblChalongKrung_1.setBounds(69, 461, 336, 22);
		Location.add(lblChalongKrung_1);
		
		JPanel User = new JPanel();
		card.add(User, "User");
		User.setLayout(null);
		
		JPanel panel1User = new JPanel();
		panel1User.setLayout(null);
		panel1User.setBackground(Color.LIGHT_GRAY);
		panel1User.setBounds(0, 0, 742, 112);
		User.add(panel1User);
		
		JLabel lblNewLabel_5_2 = new JLabel("User Data");
		lblNewLabel_5_2.setForeground(Color.BLACK);
		lblNewLabel_5_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 47));
		lblNewLabel_5_2.setBounds(57, 11, 222, 90);
		panel1User.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("");
		lblNewLabel_5_1_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 47));
		lblNewLabel_5_1_1.setBounds(10, 75, 60, 60);
		panel1User.add(lblNewLabel_5_1_1);
		
		
		roomBox.setBounds(43, 123, 85, 31);
		User.add(roomBox);
		
		JLabel yourroonlbl = new JLabel("Your room");
		yourroonlbl.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		yourroonlbl.setBounds(138, 123, 85, 26);
		User.add(yourroonlbl);
		
		JLabel lblNewLabel_9_1 = new JLabel("Payment Status :");
		lblNewLabel_9_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_9_1.setBounds(22, 176, 124, 26);
		User.add(lblNewLabel_9_1);
		
	
		statusLabel.setForeground(new Color(255, 0, 0));
		statusLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		statusLabel.setBounds(158, 176, 124, 26);
		User.add(statusLabel);
		
		JLabel lblNewLabel_9_1_2 = new JLabel("Check In :");
		lblNewLabel_9_1_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_9_1_2.setBounds(73, 201, 69, 26);
		User.add(lblNewLabel_9_1_2);
		
		JLabel lblNewLabel_9_1_2_1 = new JLabel("Check Out :");
		lblNewLabel_9_1_2_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblNewLabel_9_1_2_1.setBounds(59, 227, 85, 26);
		User.add(lblNewLabel_9_1_2_1);
		
	
		checkinLabel.setForeground(new Color(0, 0, 0));
		checkinLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		checkinLabel.setBounds(156, 201, 124, 26);
		User.add(checkinLabel);
		

		checkoutLabel.setForeground(Color.BLACK);
		checkoutLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		checkoutLabel.setBounds(156, 227, 126, 26);
		User.add(checkoutLabel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(22, 162, 309, 5);
		User.add(panel_4);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(Color.WHITE);
		panel_4_1.setBounds(22, 264, 309, 5);
		User.add(panel_4_1);
		dataNameField.setEditable(false);
		
		
		dataNameField.setBounds(22, 301, 140, 20);
		User.add(dataNameField);
		dataNameField.setColumns(10);
		dataSurnameField.setEditable(false);
		
		
		dataSurnameField.setColumns(10);
		dataSurnameField.setBounds(190, 301, 140, 20);
		User.add(dataSurnameField);
		dataPhoneField.setEditable(false);
		
		
		dataPhoneField.setColumns(10);
		dataPhoneField.setBounds(190, 350, 140, 20);
		User.add(dataPhoneField);
		dataEmailField.setEditable(false);
		
		
		dataEmailField.setColumns(10);
		dataEmailField.setBounds(22, 350, 140, 20);
		User.add(dataEmailField);
		dataAddress1Field.setEditable(false);
		
		
		dataAddress1Field.setColumns(10);
		dataAddress1Field.setBounds(22, 400, 140, 20);
		User.add(dataAddress1Field);
		dataAddress2Field.setEditable(false);
		
		
		dataAddress2Field.setColumns(10);
		dataAddress2Field.setBounds(190, 400, 140, 20);
		User.add(dataAddress2Field);
		dataDistrictField.setEditable(false);
		
		
		dataDistrictField.setColumns(10);
		dataDistrictField.setBounds(22, 449, 140, 20);
		User.add(dataDistrictField);
		dataProvinceField.setEditable(false);
		
		
		dataProvinceField.setColumns(10);
		dataProvinceField.setBounds(190, 449, 140, 20);
		User.add(dataProvinceField);
		
		JLabel lblNewLabel_10 = new JLabel("Name");
		lblNewLabel_10.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(22, 280, 69, 14);
		User.add(lblNewLabel_10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Surname");
		lblNewLabel_10_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_1.setBounds(190, 280, 69, 14);
		User.add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_10_2 = new JLabel("@Email");
		lblNewLabel_10_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_2.setBounds(22, 332, 69, 14);
		User.add(lblNewLabel_10_2);
		
		JLabel lblNewLabel_10_3 = new JLabel("Phone");
		lblNewLabel_10_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_3.setBounds(190, 332, 69, 14);
		User.add(lblNewLabel_10_3);
		
		JLabel lblNewLabel_10_4 = new JLabel("Address");
		lblNewLabel_10_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_4.setBounds(22, 381, 69, 14);
		User.add(lblNewLabel_10_4);
		
		JLabel lblNewLabel_10_5 = new JLabel("Address(optional)");
		lblNewLabel_10_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_5.setBounds(190, 381, 108, 14);
		User.add(lblNewLabel_10_5);
		
		JLabel lblNewLabel_10_6 = new JLabel("District");
		lblNewLabel_10_6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_6.setBounds(22, 431, 69, 14);
		User.add(lblNewLabel_10_6);
		
		JLabel lblNewLabel_10_7 = new JLabel("Province");
		lblNewLabel_10_7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_7.setBounds(190, 431, 69, 14);
		User.add(lblNewLabel_10_7);
		
		JLabel pic_user = new JLabel("");
		pic_user.setIcon(new ImageIcon(HPage.class.getResource("/images/Untitled design (1).png")));
		pic_user.setBounds(383, 111, 359, 399);
		User.add(pic_user);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.setBounds(373, 111, 10, 399);
		User.add(panel_5);
		
		JLabel lblNewLabel_8 = new JLabel("413-137615-8 SCB");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_8.setBounds(277, 184, 97, 14);
		User.add(lblNewLabel_8);
		
	
		CheckinChooser.setMinSelectableDate(new Date());
		CheckinChooser.setDate(cal.getTime());
		CheckoutChooser.setMinSelectableDate(new Date());
		CheckoutChooser.setDate(cal2.getTime());
		
		JPanel UserNoRoom = new JPanel();
		UserNoRoom.setLayout(null);
		card.add(UserNoRoom, "UserNoRoom");
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setLayout(null);
		panel_1_1_1_1.setBackground(Color.LIGHT_GRAY);
		panel_1_1_1_1.setBounds(0, 0, 742, 112);
		UserNoRoom.add(panel_1_1_1_1);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("User Data");
		lblNewLabel_5_2_1.setForeground(Color.BLACK);
		lblNewLabel_5_2_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 47));
		lblNewLabel_5_2_1.setBounds(38, 22, 222, 90);
		panel_1_1_1_1.add(lblNewLabel_5_2_1);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 47));
		lblNewLabel_5_1_1_1.setBounds(10, 75, 60, 60);
		panel_1_1_1_1.add(lblNewLabel_5_1_1_1);
		
		JLabel lblYouHaveNo = new JLabel("you have no room yet");
		lblYouHaveNo.setForeground(Color.BLACK);
		lblYouHaveNo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 15));
		lblYouHaveNo.setBounds(99, 179, 175, 26);
		UserNoRoom.add(lblYouHaveNo);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setBackground(Color.WHITE);
		panel_4_1_1.setBounds(22, 264, 309, 5);
		UserNoRoom.add(panel_4_1_1);
		
		textFieldNamenoUser = new JTextField();
		textFieldNamenoUser.setEditable(false);
		textFieldNamenoUser.setColumns(10);
		textFieldNamenoUser.setBounds(22, 301, 140, 20);
		UserNoRoom.add(textFieldNamenoUser);
		
		textFieldSurnamenoUser = new JTextField();
		textFieldSurnamenoUser.setEditable(false);
		textFieldSurnamenoUser.setColumns(10);
		textFieldSurnamenoUser.setBounds(190, 301, 140, 20);
		UserNoRoom.add(textFieldSurnamenoUser);
		
		textFieldPhonenoUser = new JTextField();
		textFieldPhonenoUser.setEditable(false);
		textFieldPhonenoUser.setColumns(10);
		textFieldPhonenoUser.setBounds(190, 350, 140, 20);
		UserNoRoom.add(textFieldPhonenoUser);
		
		textFieldEmailnoUser = new JTextField();
		textFieldEmailnoUser.setEditable(false);
		textFieldEmailnoUser.setColumns(10);
		textFieldEmailnoUser.setBounds(22, 350, 140, 20);
		UserNoRoom.add(textFieldEmailnoUser);
		
		textFieldAddressnoUser = new JTextField();
		textFieldAddressnoUser.setEditable(false);
		textFieldAddressnoUser.setColumns(10);
		textFieldAddressnoUser.setBounds(22, 400, 140, 20);
		UserNoRoom.add(textFieldAddressnoUser);
		
		textFieldAddressPlusnoUser = new JTextField();
		textFieldAddressPlusnoUser.setEditable(false);
		textFieldAddressPlusnoUser.setColumns(10);
		textFieldAddressPlusnoUser.setBounds(190, 400, 140, 20);
		UserNoRoom.add(textFieldAddressPlusnoUser);
		
		textFieldDistrictnoUser = new JTextField();
		textFieldDistrictnoUser.setEditable(false);
		textFieldDistrictnoUser.setColumns(10);
		textFieldDistrictnoUser.setBounds(22, 449, 140, 20);
		UserNoRoom.add(textFieldDistrictnoUser);
		
		textFieldProvincenoUser = new JTextField();
		textFieldProvincenoUser.setEditable(false);
		textFieldProvincenoUser.setColumns(10);
		textFieldProvincenoUser.setBounds(190, 449, 140, 20);
		UserNoRoom.add(textFieldProvincenoUser);
		
		JLabel lblNewLabel_10_8 = new JLabel("Name");
		lblNewLabel_10_8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_8.setBounds(22, 280, 69, 14);
		UserNoRoom.add(lblNewLabel_10_8);
		
		JLabel lblNewLabel_10_1_1 = new JLabel("Surname");
		lblNewLabel_10_1_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_1_1.setBounds(190, 280, 69, 14);
		UserNoRoom.add(lblNewLabel_10_1_1);
		
		JLabel lblNewLabel_10_2_1 = new JLabel("@Email");
		lblNewLabel_10_2_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_2_1.setBounds(22, 332, 69, 14);
		UserNoRoom.add(lblNewLabel_10_2_1);
		
		JLabel lblNewLabel_10_3_1 = new JLabel("Phone");
		lblNewLabel_10_3_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_3_1.setBounds(190, 332, 69, 14);
		UserNoRoom.add(lblNewLabel_10_3_1);
		
		JLabel lblNewLabel_10_4_1 = new JLabel("Address");
		lblNewLabel_10_4_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_4_1.setBounds(22, 381, 69, 14);
		UserNoRoom.add(lblNewLabel_10_4_1);
		
		JLabel lblNewLabel_10_5_1 = new JLabel("Address(optional)");
		lblNewLabel_10_5_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_5_1.setBounds(190, 381, 108, 14);
		UserNoRoom.add(lblNewLabel_10_5_1);
		
		JLabel lblNewLabel_10_6_1 = new JLabel("District");
		lblNewLabel_10_6_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_6_1.setBounds(22, 431, 69, 14);
		UserNoRoom.add(lblNewLabel_10_6_1);
		
		JLabel lblNewLabel_10_7_1 = new JLabel("Province");
		lblNewLabel_10_7_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblNewLabel_10_7_1.setBounds(190, 431, 69, 14);
		UserNoRoom.add(lblNewLabel_10_7_1);
		
		JLabel lblNewLabel_11_1 = new JLabel("");
		lblNewLabel_11_1.setIcon(new ImageIcon(HPage.class.getResource("/images/Untitled design (1).png")));
		lblNewLabel_11_1.setBounds(383, 111, 359, 399);
		UserNoRoom.add(lblNewLabel_11_1);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBackground(Color.WHITE);
		panel_5_1.setBounds(373, 111, 10, 399);
		UserNoRoom.add(panel_5_1);
		
		JPanel RoomPage = new JPanel();
		card.add(RoomPage, "RoomPage");
		RoomPage.setLayout(null);
		
		JButton ShowroomDQR = new JButton("Show More");
		ShowroomDQR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		ShowroomDQR.setBounds(265, 226, 85, 23);
		RoomPage.add(ShowroomDQR);
		
		JButton ShowroomEQR = new JButton("Show More");
		ShowroomEQR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		ShowroomEQR.setBounds(265, 353, 85, 23);
		RoomPage.add(ShowroomEQR);
		
		JButton ShowroomETR = new JButton("Show More");
		ShowroomETR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		ShowroomETR.setBounds(636, 353, 85, 23);
		RoomPage.add(ShowroomETR);
		
		JButton ShowroomDTR = new JButton("Show More");
		ShowroomDTR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		ShowroomDTR.setBounds(636, 226, 85, 23);
		RoomPage.add(ShowroomDTR);
		
		JButton ShowroomHSTR = new JButton("Show More");
		ShowroomHSTR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		ShowroomHSTR.setBounds(636, 480, 85, 23);
		RoomPage.add(ShowroomHSTR);
		
		JButton ShowroomHSQR = new JButton("Show More");
		ShowroomHSQR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		ShowroomHSQR.setBounds(265, 480, 85, 23);
		RoomPage.add(ShowroomHSQR);
		
		JButton ShowroomSTR = new JButton("Show More");
		ShowroomSTR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		ShowroomSTR.setBounds(636, 99, 85, 23);
		RoomPage.add(ShowroomSTR);
		
		JButton ShowroomSQR = new JButton("Show More");
		ShowroomSQR.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		ShowroomSQR.setBounds(265, 99, 85, 23);
		RoomPage.add(ShowroomSQR);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(HPage.class.getResource("/imgRoom/showroom.jpg")));
		lblNewLabel_1.setBounds(0, 0, 742, 510);
		RoomPage.add(lblNewLabel_1);		
		
		JPanel HSQR = new JPanel();
		card.add(HSQR, "HSQR");
		HSQR.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel Pic_HSQR = new JLabel("");
		Pic_HSQR.setIcon(new ImageIcon(HPage.class.getResource("/imgRoom/horizon_suite_queen_room.jpg")));
		Pic_HSQR.setBounds(0, 0, 742, 510);
		HSQR.add(Pic_HSQR);
		
		JPanel HSTWR = new JPanel();
		card.add(HSTWR, "HSTWR");
		HSTWR.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel Pic_HSTWR = new JLabel("");
		Pic_HSTWR.setIcon(new ImageIcon(HPage.class.getResource("/imgRoom/horizon_suite_twin_room.jpg")));
		Pic_HSTWR.setBounds(0, 0, 742, 510);
		HSTWR.add(Pic_HSTWR);
		
		JPanel DQR = new JPanel();
		DQR.setBounds(242, 50, 742, 511);
		card.add(DQR, "DQR");
		DQR.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel Pic_DQR = new JLabel("");
		Pic_DQR.setIcon(new ImageIcon(HPage.class.getResource("/imgRoom/deluxe_queen_room.jpg")));
		Pic_DQR.setBounds(0, 0, 742, 510);
		DQR.add(Pic_DQR);
		
		JPanel DTR = new JPanel();
		DTR.setBounds(242, 50, 742, 511);	
		DTR.setLayout(new GridLayout(1, 0, 0, 0));
		card.add(DTR,"DTR");
		
		JLabel Pic_DTR = new JLabel("");
		Pic_DTR.setIcon(new ImageIcon(HPage.class.getResource("/imgRoom/deluxe_twin_room.jpg")));
		Pic_DTR.setBounds(0, 0, 742, 510);
		DTR.add(Pic_DTR);
		
		JPanel EQR = new JPanel();
		EQR.setBounds(242, 50, 742, 511);
		EQR.setLayout(new GridLayout(1, 0, 0, 0));
		card.add(EQR,"EQR");
		
		JLabel Pic_EQR = new JLabel("");
		Pic_EQR.setIcon(new ImageIcon(HPage.class.getResource("/imgRoom/Executive_queen_room.jpg")));
		Pic_EQR.setBounds(0, 0, 742, 510);
		EQR.add(Pic_EQR);
		
		JPanel STR = new JPanel();
		STR.setBounds(242, 50, 742, 511);
		card.add(STR,"STR");
		STR.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel Pic_STR = new JLabel("");
		Pic_STR.setIcon(new ImageIcon(HPage.class.getResource("/imgRoom/superior_twin_room.jpg")));
		Pic_STR.setBounds(0, 0, 742, 510);
		STR.add(Pic_STR);
		
		JPanel ETR = new JPanel();
		ETR.setBounds(242, 50, 742, 511);
		ETR.setLayout(new GridLayout(1, 0, 0, 0));
		card.add(ETR,"ETR");
		
		JLabel Pic_ETR = new JLabel("");
		Pic_ETR.setIcon(new ImageIcon(HPage.class.getResource("/imgRoom/Executive_twin_room.jpg")));
		Pic_ETR.setBounds(0, 0, 742, 510);
		ETR.add(Pic_ETR);
			
		JPanel SQR = new JPanel();
		SQR.setBounds(242, 50, 742, 511);
		card.add(SQR,"SQR");
		SQR.setLayout(null);
		
		JLabel Pic_SQR = new JLabel("");
		Pic_SQR.setIcon(new ImageIcon(HPage.class.getResource("/imgRoom/superior_queen_room.jpg")));
		Pic_SQR.setBounds(0, 0, 742, 510);
		SQR.add(Pic_SQR);
		
		 	JPanel Gallery = new JPanel();
	        Gallery.setBounds(242, 50, 742, 511);
	        card.add(Gallery,"Gallery");
	        Gallery.setLayout(null);
	        
	        JPanel panel_2Gallery = new JPanel();
	        panel_2Gallery.setBackground(new Color(83, 52, 112));
	        panel_2Gallery.setBounds(0, 0, 742, 37);
	        Gallery.add(panel_2Gallery);
	        panel_2Gallery.setLayout(null);
	        
	        JLabel pageGallery = new JLabel("Gallery");
	        pageGallery.setBounds(0, 0, 742, 37);
	        pageGallery.setHorizontalAlignment(SwingConstants.CENTER);
	        pageGallery.setForeground(Color.WHITE);
	        pageGallery.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 22));
	        panel_2Gallery.add(pageGallery);
	        
	        JPanel panel_3Gallery = new JPanel();
	        panel_3Gallery.setBackground(new Color(83, 52, 112));
	        panel_3Gallery.setBounds(0, 0, 21, 37);
	        panel_2Gallery.add(panel_3Gallery);
	        
	        JScrollPane scrollPaneGallery = new JScrollPane();
	        scrollPaneGallery.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPaneGallery.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        scrollPaneGallery.setBounds(0, 37, 742, 474);
	        Gallery.add(scrollPaneGallery);
	        
	        JPanel AllGallery = new JPanel();
	        AllGallery.setPreferredSize(new Dimension(742, 1816));
	        AllGallery.setLayout(null);
	        scrollPaneGallery.setViewportView(AllGallery);
	        
	        JPanel G1Gallery = new JPanel();
	        G1Gallery.setLayout(null);
	        G1Gallery.setBounds(32, 30, 320, 194);
	        AllGallery.add(G1Gallery);
	        
	        JLabel NewLabel1Gallery = new JLabel("");
	        NewLabel1Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p1.jpg")));
	        NewLabel1Gallery.setBounds(0, 0, 320, 194);
	        G1Gallery.add(NewLabel1Gallery);
	        
	        JPanel G2Gallery = new JPanel();
	        G2Gallery.setLayout(null);
	        G2Gallery.setBounds(382, 30, 320, 194);
	        AllGallery.add(G2Gallery);
	        
	        JLabel NewLabel2Gallery = new JLabel("");
	        NewLabel2Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p2.jpg")));
	        NewLabel2Gallery.setBounds(0, 0, 320, 194);
	        G2Gallery.add(NewLabel2Gallery);
	        
	        JPanel G3Gallery = new JPanel();
	        G3Gallery.setLayout(null);
	        G3Gallery.setBounds(32, 250, 320, 194);
	        AllGallery.add(G3Gallery);
	        
	        JLabel NewLabel3Gallery = new JLabel("");
	        NewLabel3Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p3.jpg")));
	        NewLabel3Gallery.setBounds(0, 0, 320, 194);
	        G3Gallery.add(NewLabel3Gallery);
	        
	        JPanel G4Gallery = new JPanel();
	        G4Gallery.setLayout(null);
	        G4Gallery.setBounds(382, 250, 320, 194);
	        AllGallery.add(G4Gallery);
	        
	        JLabel NewLabel4Gallery = new JLabel("");
	        NewLabel4Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p4.jpg")));
	        NewLabel4Gallery.setBounds(0, 0, 320, 194);
	        G4Gallery.add(NewLabel4Gallery);
	        
	        JPanel G5Gallery = new JPanel();
	        G5Gallery.setLayout(null);
	        G5Gallery.setBounds(32, 470, 320, 194);
	        AllGallery.add(G5Gallery);
	        
	        JLabel NewLabel5Gallery = new JLabel("");
	        NewLabel5Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p5.jpg")));
	        NewLabel5Gallery.setBounds(0, 0, 320, 194);
	        G5Gallery.add(NewLabel5Gallery);
	        
	        JPanel G6Gallery = new JPanel();
	        G6Gallery.setLayout(null);
	        G6Gallery.setBounds(382, 470, 320, 194);
	        AllGallery.add(G6Gallery);
	        
	        JLabel NewLabel6Gallery = new JLabel("");
	        NewLabel6Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p6.jpg")));
	        NewLabel6Gallery.setBounds(0, 0, 320, 194);
	        G6Gallery.add(NewLabel6Gallery);
	        
	        JPanel G7Gallery = new JPanel();
	        G7Gallery.setLayout(null);
	        G7Gallery.setBounds(32, 690, 320, 194);
	        AllGallery.add(G7Gallery);
	        
	        JLabel NewLabel7Gallery = new JLabel("");
	        NewLabel7Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p7.jpg")));
	        NewLabel7Gallery.setBounds(0, 0, 320, 194);
	        G7Gallery.add(NewLabel7Gallery);
	        
	        JPanel G8Gallery = new JPanel();
	        G8Gallery.setLayout(null);
	        G8Gallery.setBounds(382, 690, 320, 194);
	        AllGallery.add(G8Gallery);
	        
	        JLabel NewLabel8Gallery = new JLabel("");
	        NewLabel8Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p8.jpg")));
	        NewLabel8Gallery.setBounds(0, 0, 320, 194);
	        G8Gallery.add(NewLabel8Gallery);
	        
	        JPanel G9Gallery = new JPanel();
	        G9Gallery.setLayout(null);
	        G9Gallery.setBounds(32, 910, 320, 194);
	        AllGallery.add(G9Gallery);
	        
	        JLabel NewLabel9Gallery = new JLabel("");
	        NewLabel9Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p9.jpg")));
	        NewLabel9Gallery.setBounds(0, 0, 320, 194);
	        G9Gallery.add(NewLabel9Gallery);
	        
	        JPanel G10Gallery = new JPanel();
	        G10Gallery.setLayout(null);
	        G10Gallery.setBounds(382, 910, 320, 194);
	        AllGallery.add(G10Gallery);
	        
	        JLabel NewLabel10Gallery = new JLabel("");
	        NewLabel10Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p10.jpg")));
	        NewLabel10Gallery.setBounds(0, 0, 320, 194);
	        G10Gallery.add(NewLabel10Gallery);
	        
	        JPanel G11Gallery = new JPanel();
	        G11Gallery.setLayout(null);
	        G11Gallery.setBounds(32, 1130, 320, 194);
	        AllGallery.add(G11Gallery);
	        
	        JLabel NewLabel11Gallery = new JLabel("");
	        NewLabel11Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p11.jpg")));
	        NewLabel11Gallery.setBounds(0, 0, 320, 194);
	        G11Gallery.add(NewLabel11Gallery);
	        
	        JPanel G12Gallery = new JPanel();
	        G12Gallery.setLayout(null);
	        G12Gallery.setBounds(382, 1130, 320, 194);
	        AllGallery.add(G12Gallery);
	        
	        JLabel NewLabel12Gallery = new JLabel("");
	        NewLabel12Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p12.jpg")));
	        NewLabel12Gallery.setBounds(0, 0, 320, 194);
	        G12Gallery.add(NewLabel12Gallery);
	        
	        JPanel G13Gallery = new JPanel();
	        G13Gallery.setLayout(null);
	        G13Gallery.setBounds(32, 1350, 320, 194);
	        AllGallery.add(G13Gallery);
	        
	        JLabel NewLabel13Gallery = new JLabel("");
	        NewLabel13Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p13.jpg")));
	        NewLabel13Gallery.setBounds(0, 0, 320, 194);
	        G13Gallery.add(NewLabel13Gallery);
	        
	        JPanel G14Gallery = new JPanel();
	        G14Gallery.setLayout(null);
	        G14Gallery.setBounds(382, 1350, 320, 194);
	        AllGallery.add(G14Gallery);
	        
	        JLabel NewLabel14Gallery = new JLabel("");
	        NewLabel14Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p14.jpg")));
	        NewLabel14Gallery.setBounds(0, 0, 320, 194);
	        G14Gallery.add(NewLabel14Gallery);
	        
	        JPanel G15Gallery = new JPanel();
	        G15Gallery.setLayout(null);
	        G15Gallery.setBounds(32, 1570, 320, 194);
	        AllGallery.add(G15Gallery);
	        
	        JLabel NewLabel15Gallery = new JLabel("");
	        NewLabel15Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p15.jpg")));
	        NewLabel15Gallery.setBounds(0, 0, 320, 194);
	        G15Gallery.add(NewLabel15Gallery);
	        
	        JPanel G16Gallery = new JPanel();
	        G16Gallery.setLayout(null);
	        G16Gallery.setBounds(382, 1570, 320, 194);
	        AllGallery.add(G16Gallery);
	        
	        JLabel NewLabel16Gallery = new JLabel("");
	        NewLabel16Gallery.setIcon(new ImageIcon(HPage.class.getResource("/AllGallery/p16.jpg")));
	        NewLabel16Gallery.setBounds(0, 0, 320, 194);
	        G16Gallery.add(NewLabel16Gallery);
	        
	    	CardLayout cardlayout = (CardLayout)card.getLayout();
			
		
	        ShowroomSQR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardlayout.show(card, "SQR");
				}
			});
	        ShowroomSTR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardlayout.show(card, "STR");
				}
			});
	        ShowroomDQR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardlayout.show(card, "DQR");
				}
			});
	        ShowroomDTR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardlayout.show(card, "DTR");
				}
			});
	        ShowroomEQR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardlayout.show(card, "EQR");
				}
			});
	        ShowroomETR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardlayout.show(card, "ETR");
				}
			});
	        ShowroomHSQR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardlayout.show(card, "HSQR");
				}
			});
	        ShowroomHSTR.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cardlayout.show(card, "HSTWR");
				}
			});
		
		
		HP_btn.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				setColor(HP_btn);
				resetColor(RE_btn);
				resetColor(RM_btn);
				resetColor(Lc_btn);
				resetColor(Gal_btn);
				resetColor(User_btn);
				resetColor(panel_3_1);
				resetColor(panel_3_2);
				resetColor(panel_3_3);
				resetColor(panel_3_4);
				resetColor(panel_3_5);
				setcolor(panel_3);
				setMouselist(false,true,true,true,true,true);
				cardlayout.show(card, "HomePage");
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(hp_ms) {
				setCOLOR(HP_btn);				
				setcolor(panel_3);
			}}
			@Override
			public void mouseExited(MouseEvent e) {
				if(hp_ms) {
					resetColor(HP_btn);
					resetColor(panel_3);
			}}
		});		
		
		RE_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setColor(RE_btn);
				resetColor(HP_btn);
				resetColor(RM_btn);
				resetColor(Lc_btn);
				resetColor(Gal_btn);
				resetColor(User_btn);
				resetColor(panel_3);
				resetColor(panel_3_2);
				resetColor(panel_3_3);
				resetColor(panel_3_4);
				resetColor(panel_3_5);
				setcolor(panel_3_1);
				setMouselist(true,false,true,true,true,true);
				cardlayout.show(card, "Reserve");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(rs_ms) {
				setcolor(panel_3_1);
				setCOLOR(RE_btn);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(rs_ms) {
				resetColor(RE_btn);	
				resetColor(panel_3_1);
			}}
		});
		RM_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setColor(RM_btn);
				resetColor(HP_btn);
				resetColor(RE_btn);
				resetColor(Lc_btn);
				resetColor(Gal_btn);
				resetColor(User_btn);
				resetColor(panel_3);
				resetColor(panel_3_1);
				resetColor(panel_3_3);
				resetColor(panel_3_4);
				resetColor(panel_3_5);
				setcolor(panel_3_2);
				setMouselist(true,true,false,true,true,true);
				cardlayout.show(card, "RoomPage");
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(rm_ms) {
					setCOLOR(RM_btn);
					setcolor(panel_3_2);
				}
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(rm_ms) {
					resetColor(RM_btn);
					resetColor(panel_3_2);
			}}
		});
		Lc_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setColor(Lc_btn);
				resetColor(HP_btn);
				resetColor(RE_btn);
				resetColor(RM_btn);
				resetColor(Gal_btn);
				resetColor(User_btn);
				resetColor(panel_3);
				resetColor(panel_3_1);
				resetColor(panel_3_2);
				resetColor(panel_3_4);
				resetColor(panel_3_5);
				setcolor(panel_3_3);				
				setMouselist(true,true,true,false,true,true);
				cardlayout.show(card, "Location");
			
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(lc_ms) {
				setCOLOR(Lc_btn);
				setcolor(panel_3_3);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(lc_ms) {
				resetColor(Lc_btn);
				resetColor(panel_3_3);}
			}
		}); 
		
		Gal_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setColor(Gal_btn);
				resetColor(HP_btn);
				resetColor(RE_btn);
				resetColor(RM_btn);
				resetColor(Lc_btn);
				resetColor(User_btn);
				resetColor(panel_3);
				resetColor(panel_3_1);
				resetColor(panel_3_2);
				resetColor(panel_3_3);
				resetColor(panel_3_5);
				setcolor(panel_3_4);
				setMouselist(true,true,true,true,false,true);
				cardlayout.show(card, "Gallery");
			
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(gl_ms) {
				setCOLOR(Gal_btn);
				setcolor(panel_3_4);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(gl_ms) {
				resetColor(Gal_btn);
				resetColor(panel_3_4);}
			}
		}); 
		User_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setColor(User_btn);
				resetColor(HP_btn);
				resetColor(RE_btn);
				resetColor(RM_btn);
				resetColor(Lc_btn);
				resetColor(Gal_btn);
				resetColor(panel_3);
				resetColor(panel_3_1);
				resetColor(panel_3_2);
				resetColor(panel_3_3);
				resetColor(panel_3_4);
				setcolor(panel_3_5);
				setMouselist(true,true,true,true,true,false);
				if(a)cardlayout.show(card, "User");
				else cardlayout.show(card, "UserNoRoom");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(us_ms) {
					setCOLOR(User_btn);
					setcolor(panel_3_5);
					}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(us_ms) {
					resetColor(User_btn);
					resetColor(panel_3_5);}
				}
			
		});
		roomBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myRoomDetail();
			}
		});
		googleMapBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {	                    
	                    Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
			}
		});
		Logouticon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Account obj = new Account();
				frame.dispose();
				obj.frame.setVisible(true);
			}
		});
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				DateForUse OBJ = new DateForUse(CheckinChooser.getDate(),CheckoutChooser.getDate());
				if(OBJ.checkDate() == -1) 
					JOptionPane.showMessageDialog(null, "Please choose valid day");
				else if(OBJ.checkDate() == 0)
					JOptionPane.showMessageDialog(null, "You can't choose the same day, Sorry");
				else {
				int a =	JOptionPane.showConfirmDialog(null, "Are You Confirm?", "confirm date", JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					System.out.println(OBJ.setStringforSelect());
					Reservation obj = new Reservation(CheckinChooser.getDate(),CheckoutChooser.getDate(),user,OBJ.getDayBetween(),OBJ.setStringforSelect());
					frame.dispose();
					obj.frame.setVisible(true);
				}							
			}
			}
		});
		Titlebar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xx = e.getXOnScreen();
				int yy = e.getYOnScreen();
				frame.setLocation(xx-x, yy-y);
			}
		});
		Titlebar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x= e.getX();
				y= e.getY();
			}
			
		});
	}
}
