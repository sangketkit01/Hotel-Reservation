package Gui;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import Program.InsertUpdateDelete;
import Program.Select;

import java.awt.event.*;
import java.sql.ResultSet;
import java.time.YearMonth;
import java.util.regex.*;
import java.awt.*;

public class Account implements MouseListener, FocusListener, ActionListener {

    public JFrame frame;
    private JTextField UsernameField, UserTextfield, EmailTextfield,
            nameField, surnameField, phoneField, address1Field, address2Field;

    private JPasswordField PasswordField, PasswordTextfield, ConfirmedPassword;
    private CardLayout cardLayOut, cardLayout;
    private JPanel panel, MainPanel, barPanel, LoginPanel, InformationPanel, CreatePanel, ZeroPanel,
            FirstImagePanel, SecondImagePanel, ThirdImagePanel, FourthImagePanel, FifthImagePanel;
    private JLabel Username, UsernameLabel, InvalidLabel, EmailLabel, passwordLabel, ConfirmLabel, lengthPassword,
            EqualPassword,
            upperCasePassword, HotelNameLabel, noAccountLabel, createLabel, name, surname, phoneNumber,
            EmailCorrectLabel, EmailLabelCheck, UserCorrectLabel, PasswordCorrectLabel, PhoneLabelCheck,
            PhoneCorrectLabel, address1, address2, district, province, hotel, hotel0, hotel2,
            hotel3, hotel4, hotel5, BirthdayLabel;
    private JButton LoginButton, nextButton, backButton, backButton1, finishButton, EyeOpen0, EyeClose0, EyeOpen1,
            EyeOpen2, EyeClose1,
            EyeClose2, ExitButton, MinimizeButton;
    private JComboBox<String> districtField, provinceField, DateBox, MonthBox, YearBox;
    private Pattern pattern;
    private Matcher matcher;

    private String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private int upperCaseCount = 0;
    private int NumberCount = 0;
    private int DateCount = 0;

    private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };

    private String[] Province = { "Bangkok", "Amnat Charoen", "Ang Thong", "Bueng Kan", "Buriram", "Chachoengsao",
            "Chai Nat", "Chaiyapum", "Chantaburi", "Chiang Mai", "Chiang Rai", "Chonburi", "Chumphon", "Kalasin",
            "Kamphaeng Phet", "Kanchanaburi", "Khon Kaen", "Krabi", "Lampang", "Lamphun", "Loei", "Lopburi",
            "Mae Hong Son", "Maha Sarakham", "Mukdahan", "Nakhon Nayok", "Nakhon Pathom", "Nakhon Ratchasima",
            "Nakhon Sawan", "Nakhon Si Thammarat", "Nan", "Narathiwat", "Nong Bua Lamphu", "Nong Khai", "Nonthaburi",
            "Pathum Thani", "Pattani", "Phang Nga", "Phatthalung", "Phayao", "Phetchaburi", "Phetchabun", "Phichit",
            "Phitsanulok", "Phra Nakhon Si Ayutthaya", "Phrae", "Phuket", "Prachinburi", "Phachuap Khiri Khan",
            "Ranong", "Ratchaburi", "Rayong", "Roi Et", "Sa Kaeo", "Sakon Nakhon", "Samut Prakan", "Samut Sakhon",
            "Samut SongKhram", "Saraburi", "Satun", "Sing Buri", "Sisaket", "Songkhla", "Sukhothai", "Suphan Buri",
            "Surat Thani",
            "Surin", "Tak", "Trang", "Trat", "Ubon Ratchathani", "Ubon Thani", "Uthai Thani", "Uttaradit", "Yala",
            "Yasothon" };
    private String[] BangkokDistricts = { "Bang Bon", "Bang Kapi", "Bang Khae", "Bang Khen", "Bang Kho Laem",
            "Bang Khun Thian", "Bang Lamphu Lang", "Bang Na", "Bang Phlat",
            "Bang Rak", "Bang Sue", "Bueng Kum", "Chatuchak", "Chom Thong",
            "Din Daeng", "Don Mueang", "Dusit", "Huai Khwang", "Khan Na Yao",
            "Khlong Sam Wa", "Khlong San", "Khlong Toei", "Lak Si", "Lat Krabang",
            "Lat Phrao", "Min Buri", "Nong Chok", "Nong Khaem", "Pathum Wan",
            "Phasi Charoen", "Phaya Thai", "Phra Khanong", "Phra Nakhon",
            "Pom Prap Sattru Phai", "Prawet", "Rat Burana", "Ratchathewi",
            "Sai Mai", "Samphanthawong", "Saphan Sung", "Sathon", "Suan Luang",
            "Taling Chan", "Thawi Watthana", "Thon Buri", "Thung Khru", "Wang Thonglang", "Wattana", "Yan Nawa" };
    private String[] AmnatCharoenDistricts = { "Mueang Amnat Charoen", "Chanuman", "Pathum Ratchawongsa", "Phana",
            "Senangkhanikhom", "Hua Taphan", "Lue Amnat", "Khwao Sinarin", "Phathumrat", "Lathai", "Bueng Bun",
            "Bung Khla", "Sanong Thong" };
    private String[] AngThongDistricts = { "Mueang Ang Thong", "Chaiyo", "Pa Mok", "Pho Thong", "Sawaeng Ha",
            "Wiset Chai Chan" };
    private String[] BuengKanDistricts = { "Mueang Bueng Kan", "Seka", "Sangkhom", "Si Wilai", "Bueng Khong Long",
            "Bung Khla",
            "Phon Charoen", "So Phisai", "Lao Suea Kok", "Phon Thong", "Pak Khat", "Boon Rueang", "Sai Mun", "Phu Wua",
            "Phibun Rak", "Bung Khla", "Bueng Khong Long", "Phon Charoen", "Seka", "Si Wilai", "Sangkhom",
            "Mueang Bueng Kan" };
    private String[] BuriramDistricts = { "Mueang Buriram", "Nang Rong", "Lahan Sai", "Satuek", "Pakham", "Nong Ki",
            "Na Pho",
            "Phutthaisong", "Krasang", "Chaloem Phra Kiat", "Ban Kruat", "Non Din Daeng", "Chamni", "Huai Rat",
            "Khu Mueang", "Non Suwan", "Prakhon Chai", "Chatturat", "Nong Hong", "Phlapphla Chai" };
    private String[] ChaChoengSaoDistricts = { "Bang Khla", "Bang Nam Priao", "Bang Pakong", "Ban Pho", "Chachoengsao",
            "Klong Khuean",
            "Mueang Chachoengsao", "Phanom Sarakham", "Plaeng Yao", "Ratchasan", "Sanam Chai Khet", "Tha Takiap",
            "Khlong Tamru", "Phanat Nikhom" };
    private String[] ChaiNatDistricts = { "Mueang Chai Nat", "Manorom", "Wat Sing", "Sapphaya", "Nong Mamong", "Hankha",
            "Sankhaburi", "Nong Khayang", "Noen Kham", "Wang Sai Phun", "Siamrat", "Santana Nikhom" };
    private String[] ChaiyaphumDistricts = { "Mueang Chaiyaphum", "Kaset Sombun", "Khon Sawan", "Phu Khieo",
            "Chatturat",
            "Bamnet Narong",
            "Nong Bua Daeng", "Thep Sathit", "Nong Bua Rawe", "Chuen Chom", "Kaeng Khro", "Ban Khwao", "Khon San",
            "Phakdi Chumphon", "Noen Sa-nga", "Sap Yai", "Sri Chomphu", "Thung Khao Luang",
            "Phu Laen Kha National Park", "Mancha Khiri", "Theppharat", "Bamnet Narong", "Bua Lai",
            "Chaturaphak Phiman", "Nong Bua Rawe", "Nong Song Hong", "Phakdi Chumphon", "Phu Khiao", "Sikhoraphum",
            "Sri Racha", "Ban Thaen", "Kaeng Khro", "Khon San", "Khon Sawan", "Mueang Chumphae", "Nong Bua Daeng",
            "Phu Pha Man National Park", "Sap Yai", "Thap Sai", "Wichian Buri" };
    private String[] ChantaburiDistricts = { "Mueang Chanthaburi", "Khlung", "Pong Nam Ron", "Makham", "Laem Sing",
            "Soi Dao",
            "Na Yai Am", "Tha Mai", "Kaeng Hang Maeo", "Khao Khitchakut" };
    private String[] ChiangMaiDistricts = { "Mueang Chiang Mai", "Chom Thong", "Mae Chaem", "Chiang Dao", "Doi Saket",
            "Mae Taeng",
            "Mae Rim", "Samoeng", "Fang", "San Pa Tong", "San Kamphaeng", "San Sai", "Hang Dong", "Hot", "Doi Tao",
            "Omkoi", "Saraphi" };
    private String[] ChaingRaiDistricts = { "Mueang Chiang Rai", "Mae Chan", "Mae Sai", "Mae Suai", "Chiang Khong",
            "Tha Bo",
            "Phan",
            "Pa Daet", "Wieng Kaen", "Khun Tan", "Phaya Mengrai", "Wiang Pa Pao", "Chiang Saen", "Doi Luang",
            "Mae Fa Luang", "Thung Chang", "Mae Lao", "Pa Sang", "Mae Suai", "Phaya Mengrai" };
    private String[] ChonburiDistricts = { "Mueang Chonburi", "Ban Bueng", "Nong Yai", "Bang Lamung", "Phan Thong",
            "Phanat Nikhom", "Sattahip", "Ko Chan", "Bo Thong", "Ko Sichang" };
    private String[] ChumPhonDistricts = { "Mueang Chumphon", "Tha Sae", "Lang Suan", "Phato", "Sawi", "Thung Tako",
            "Pathio",
            "Phunphin", "Bang Saphan Noi", "Bang Saphan", "Phato" };
    private String[] KalasinDistricts = { "Mueang Kalasin", "Na Mon", "Huai Mek", "Kamalasai", "Kuchinarai",
            "Khao Wong",
            "Yang Talat", "Sahatsakhan", "Tha Khantho", "Somdet", "Nong Kung Si", "Kham Muang", "Phu Khiao" };
    private String[] KamPhaengPhetDistricts = { "Mueang Khampheng Phet", "Khanu Woralaksaburi", "Khanu Kammuang",
            "Sai Ngam",
            "Khlong Lan",
            "Kosamphi Nakhon", "Phran Kratai", "Wang Chao", "Bueng Na Rang", "Ping Phu", "Lan Krabue",
            "Sai Thong Watthana" };
    private String[] KanchanaburiDistricts = { "Mueang Kanchanaburi", "Sai Yok", "Bo Phloi", "Sangkhla Buri",
            "Tha Maka",
            "Tha Muang", "Thong Pha Phum", "Si Sawat", "Lao Khwan", "Dan Makham Tia", "Nong Prue", "Huai Krachao",
            "Tha Lo", "Pang Sila Thong" };
    private String[] KhonKaenDistricts = { "Mueang Khon Kaen", "Ban Fang", "Phra Yuen", "Nong Ruea", "Chum Phae",
            "Si Chomphu",
            "Nam Phong", "Phu Wiang", "Mancha Khiri", "Chonnabot", "Khao Suan Kwang", "Phu Pha Man", "Wang Yai",
            "Waeng Noi", "Pueai Noi", "Nong Song Hong", "Phon", "Ban Phai", "Phu Kradueng", "Sam Sung", "Khok Pho Chai",
            "Phra Thaen", "Nong Na Kham", "Non Sila", "Khao Wong", "Phu Ruea", "Chum Saeng", "Nam Kliang", "Ubolratana",
            "Kranuan", "Phu Khan Khoi", "Phon Thong", "Wiang Kao", "Phon Sai", "Nong Kung Si", "Ubolrat Dam" };
    private String[] KrabiDistricts = { "Mueang Krabi", "Khao Phanom", "Khlong Thom", "Plai Phraya", "Lam Thap",
            "Ao Luek",
            "Khao Phanom", "Nuea Khlong" };
    private String[] LampangDistricts = { "Mueang Lamphang", "Mae Mo", "Chae Hom", "Wieng Nong Long", "Ban Hong",
            "Thoen",
            "Mae Tha",
            "Sop Prap", "Mae Waen", "Hang Chat" };
    private String[] LamphunDistricts = { "Mueang Lamphun", "Mae Tha", "Ban Hong", "Li", "Thung Hua Chang", "Pa Sang",
            "Ban Thi",
            "Wiang Nong Long", "Ban Klang", "San Pa Tong", "Nong Chang", "Mae Tha", "Mae On", "Ban Mae Tha", "Li",
            "Sop Prap", "Mae Tha", "Thung Hua Chang", "Pa Sang" };
    private String[] LoeiDistricts = { "Mueang Loei", "Na Duang", "Chiang Khan", "Pa O Don Chai", "Dan Sai", "Na Haeo",
            "Phu Ruea", "Pha Khao", "Wang Saphung", "Tha Li", "Phu Kradueng" };
    private String[] LopburiDistricts = { "Mueang Lopburi", "Phatthana Nikhom", "Khok Samrong", "Tha Wung", "Ban Mi",
            "Chai Badan",
            "Tha Luang", "Sa Bot", "Khok Charoen", "Thung Khao Luang", "Lam Sonthi", "Nong Muang", "Khlong Maduea",
            "Wat Bot", "Tha Sala" };
    private String[] MaeHongSonDistricts = { "Mueang Mae Hong Son", "Pai", "Khun Yuam", "Mae Sariang", "Mae La Noi",
            "Sop Moei" };
    private String[] MahaSaraKhamdistricts = { "Mueang Maha Sarakham", "Kae Dam", "Kantharawichai", "Borabue", "Na Dun",
            "Yuhong",
            "Chiang Yuen", "Kosum Phisai", "Phayakkhaphum Phisai", "Wapi Pathum", "Na Chueak", "Chuen Chom", "Nong Bua",
            "Yang Sisurat", "Na Khu", "Mueang Na Kae", "Chiang Khruea", "Huai Phueng", "Bua Lai", "Kae Chan", "Kosum",
            "Na Mon", "Phra Na Kho", "Na Um", "Wiang Kao" };
    private String[] MukdahanDistricts = { "Mueang Mukdahan", "Don Tan", "Nikhom Kham Soi", "Dong Luang", "Khamcha-i",
            "Wan Yai",
            "Nong Sung", "Ban Dung", "Kut Khaopun" };
    private String[] NakhonNayokDistricts = { "Mueang Nakhon Nayok", "Pak Phli", "Ban Na", "Ongkharak", "Baan Na",
            "Khao Phra",
            "Wang Ta Phra", "Klong  Yong", "Prachantakham", "Nong Suea", "Ban Sang", "Bo Thong" };
    private String[] NakhonPathomDistricts = { "Mueang Nakhon Pathom", "Kamphaeng Saen", "Nakhon Chai Si", "Don Tum",
            "Bang Len",
            "Sam Phran", "Phutthamonthon" };
    private String[] NakhonRatchasimaDistricts = { "Mueang Nakhon Ratchasima", "Khon Buri", "Soeng Sang", "Khong",
            "Phimai",
            "Chum Phuang",
            "Kham Sakaesaeng", "Huai Thalaeng", "Bua Yai", "Prathai", "Pak Thong Chai", "Nong Bun Mak", "Kut Bak",
            "Non Daeng", "Wang Nam Khiao", "Thepharak", "Sida", "Chok Chai", "Si Khio", "Dan Khun Thot",
            "Kaeng Sanam Nang", "Non Sung", "Prasat", "Nong Khai", "Sung Noen", "Khok Sung", "Pak Chong",
            "Nong Nam Daeng", "Mueang Yang", "Phra Thong Kham", "Chaloem Phra Kiat", "Kham Thale So", "Sikhio",
            "Pak Thong Chai" };
    private String[] NakhonSawanDistricts = { "Amphoe Mueang Nakhon Sawan", "Amphoe Krok Phra", "Amphoe Chum Saeng",
            "Amphoe Nong Bua", "Amphoe Banphot Phisai", "Amphoe Phaisali", "Amphoe Tha Tako", "Amphoe Tak Fa",
            "Amphoe Mae Wong", "Amphoe Mae Poen", "Amphoe Chum Ta Bong", "Amphoe Takhli", "Amphoe Phayuha Khiri",
            "Amphoe Lat Yao", "Amphoe Kao Liao", "Amphoe Phop Phra", "Amphoe Tha Chang", "Amphoe Phichai",
            "Amphoe Nong Muang Khai", "Amphoe Phayakkhaphum Phisai", "Amphoe Chalerm Phra Kiat", "Mueang Pak",
            "Amphoe Hua Taphan", "Amphoe Nong Khae", "Amphoe Mae Wong" };
    private String[] NakhonSiThammaratDistricts = { "Mueang Nakhon Si Thammarat", "Phrom Khiri", "Lan Saka", "Chawang",
            "Chian Yai", "Cha-uat", "Tha Sala", "Nopphitam", "Chaloem Phra Kiat", "Ron Phibun", "Sichon", "Khanom",
            "Hua Sai", "Bang Khan", "Thung Song", "Na Bon", "Chulabhorn", "Phipun", "Chang Klang", "Thung Yai",
            "Phra Phrom", "Chawang", "Pak Phanang" };
    private String[] NanDistricts = { "Mueang Nan", "Bo Kluea", "Chaloem Phra Kiat", "Na Muen", "Na Noi", "Pua",
            "Santi Suk",
            "Song Khwae", "Tha Wang Pha", "Wiang Sa" };
    private String[] NarathiwatDistricts = { "Muang Narathiwat", "Tak Bai", "Bacho", "Yi-ngo", "Waeng", "Su-ngai Kolok",
            "Si Sakhon",
            "Rangae", "Ra-ngae", "Chanae", "Cho-airong", "Sukhirin", "Waeng", "Sukhirin", "Si Sakhon", "Rueso",
            "Rangae", "Ra-ngae", "Mae Lan", "Thai Ban", "Muang", "Sungai Padi", "Sungai Kolok", "Su-ngai Kolok",
            "Sungai Padi", "Sukhirin" };
    private String[] NongBuaLumphuDistricts = { "Mueang Nong Bua Lamphu", "Na Klang", "Non Sang", "Si Bun Rueang",
            "Na Wang",
            "Suwannakhuha",
            "Non Than", "Chaloem Phra Kiat" };
    private String[] NongkhaiDistricts = { "Mueang Nong Khai", "Tha Bo", "Sri Chiang Mai", "Sangkhom", "Fao Rai",
            "Rattanawapi",
            "Pho Tak", "Phon Phisai", "Si Wilai", "Bueng Kan", "Phibun Rak", "Sakhrai", "Beung Khong Long",
            "Pone Pisai", "So Phisai", "Khao Wong", "Tha Li", "Na Kae", "Rattanawongsa", "Ban Dung" };
    private String[] NonthaburiDistricts = { "Bang Kruai", "Bang Yai", "Bang Bua Thong", "Sai Noi", "Pak Kret",
            "Nonthaburi" };
    private String[] PathumThaniDistricts = { "Mueang Pathum Thani", "Khlong Luang", "Thanyaburi", "Nong Suea",
            "Lam Luk Ka",
            "Sam Khok", "Sam Khok", "Ban Phaeo", "Phra Nakhon Si Ayutthaya", "Lat Yao", "Sena", "Bang Pa-in",
            "Bang Sai", "Bang Ban",
            "Wang Noi", "Phak Hai", "Phachi", "Maha Rat", "Uthai", "Ban Mi", "Bang Kruai Sithamma", "Lahan Sai",
            "Khlong Khlung", "Thung Khru",
            "Rangsit", "Prachathipat", "Tha Ruea", "Sai Noi", "Bueng Yitho", "Ongkharak", "Don Tum", "Nong Khae",
            "Nong Yai", "Khu Khot", "Tha Khlong",
            "Bang Pa-in", "Bang Bua Thong", "Bang Yai", "Bang Kadi", "Bang Phli Yai", "Bang Kruai",
            "Klong Si Maha Phot", "Khlong Phra Udom", "Sao Thong Hin",
            "Phra Pradaeng", "Talat Khwan", "Pak Kret", "Khlong Sam Wa", "Chiang Rak Noi", "Lat Lum Kaeo",
            "Lat Sawai" };
    private String[] PattaniDistricts = { "Mueang Pattani", "Khok Pho", "Nong Chik", "Panare", "Mayo",
            "Thung Yang Daeng",
            "Yaring", "Yarang", "Kapho", "Saiburi", "Mai Kaen", "Khok Phra", "Nong Jik", "Yala", "Bannang Sata",
            "Krong Pinang", "Yarom", "Panang Tae", "Thepa", "Sai Buri" };
    private String[] PhangNgaDistricts = { "Mueang Phang-nga", "Kura Buri", "Takua Pa", "Khao Lak", "Takua Thung",
            "Thai Mueang", "Kapong", "Takua Pa", "Ko Yao" };
    private String[] PhattalungDistrict = { "Mueang Pattalung", "Khuan Khanun", "Sai Khao", "Sripanwa", "Panang Tung",
            "Bang Kaeo", "Kantang", "Thung Song", "Pa Bon", "Chaloem Phra Kiat", "Kong Ra", "Khao Chaison", "Tamot",
            "King Amphoe Bang Nara" };
    private String[] PhayaoDistricts = { "Muang Phayao", "Chun", "Chiang Kham", "Chiang Muan", "Dok Khamtai", "Pong",
            "Mae Chai", "Phu Sang", "Phu Kamyao", "Chomthong", "Sila Phet", "Sung Men", "Mueang Phrae" };
    private String[] PhetchaburiDistricts = { "Mueang Phetchaburi", "Ban Laem", "Cha-am", "Tha Yang", "Ban Pong",
            "Khao Yoi",
            "Nong Ya Plong", "Tha Mai Ruak", "Kaeng Krachan" };
    private String[] PhetchabunDistricts = { "Mueang Phetchabun", "Chon Daen", "Lom Kao", "Lom Sak", "Lom Son",
            "Noen Maprang",
            "Si Thep",
            "Wang Pong", "Nam Nao", "Bueng Sam Phan", "Wichian Buri", "Nong Phai", "Bung Sam Phan Nuea", "Khao Kho",
            "Thung Salaeng Luang", "Phu Kradueng" };
    private String[] PhichitDistricts = { "Mueang Phichit", "Wang Sai Phun", "Pho Prathap Chang", "Taphan Hin",
            "Bang Mun Nak",
            "Bueng Na Rang", "Dong Charoen", "Wachirabarami", "Sam Ngam", "Pho Thale", "Bang Krathum", "Sak Lek",
            "Noen Maprang", "Dong Mafai", "Pho Chai", "Thap Khlo", "Wang Pong", "Chai Prakan", "Santana Nikhom" };
    private String[] PhitsanulokDistricts = { "Mueang Phitsanulok", "Nakhon Thai", "Chat Trakan", "Bang Rakam",
            "Phrom Phiram",
            "Wat Bot",
            "Wang Thong", "Sai Ngam", "Noen Maprang", "Pho Prathap Chang", "Wang Sai Phun", "Phichai", "Bueng Charoen",
            "Lom Sak", "Nan Maprang", "Nam Pat", "Khao Kho", "Wachirabarami", "Wang Pong", "Ban Krang", "Phaisali",
            "Sak Lek", "Phu Thong", "Thap Khlo", "Bang Krathum", "Dok Khamtai", "Phrom Khiri", "Nong Muang Khai",
            "Sung Men", "Nam Nao", "Chon Daen" };
    private String[] AyutthayaDistricts = { "Phra Nakhon Si Ayutthaya", "Tha Ruea", "Nakhon Luang", "Bang Sai",
            "Bang Ban",
            "Bang Pa-In",
            "Phak Hai", "Phachi", "Lat Bua Luang", "Wang Noi", "Sena", "Bang Pa-in", "Uthai", "Maha Rat", "Ban Phraek",
            "Bang Sai", "Bang Pahan", "Phraeksa", "Bang Ban", "Bang Sai", "Bang Pa-in",
            "Lat Bua Luang", "Wang Noi" };
    private String[] PhraeDistricts = { "Muang", "Song", "Long", "Rong Kwang", "Wang Chin", "Den Chai", "Sung Men",
            "Nong Muang Khai", "Phu Kamyao", "Pha Daeng", "Wang Sai Phun", "Rong Pa", "Nong Muang Aen", "Sung Beng",
            "Mueang Mo", "Nong Ruea", "Na Noi", "Si Satchanalai" };
    private String[] PhuketDistricts = { "Mueang Phuket", "Kathu", "Thalang" };
    private String[] PrachinburiDistricts = { "Mueang Prachinburi", "Kabin Buri", "Na Di", "Prachantakham",
            "Si Mahosot",
            "Ban Sang", "Prachin Buri" };
    private String[] PhachuapKhriKhanDistricts = { "Mueang Prachuap Khiri Khan", "Kui Buri", "Thap Sakae",
            "Bang Saphan",
            "Bang Saphan Noi",
            "Pran Buri", "Hua Hin", "Sam Roi Yot", "Kuang Wora Ratnakhon", "Tha Yang", "Prachuap Khiri Khan" };
    private String[] RanongDistricts = { "Mueang Ranong", "La-un", "Papoe", "Kra Buri", "Suk Samran", "Kapoe", "Sawi" };
    private String[] RatchaburiDistricts = { "Mueang Ratchaburi", "Chom Bueng", "Suan Phueng", "Sangkhla Buri",
            "Pak Tho",
            "Wat Phleng",
            "Ban Pong", "Bang Phae", "Photharam", "Damnoen Saduak", "Ban Kha", "Na Chom Thian", "Ban Pong",
            "Bang Phae Yai", "Chom Bung" };
    private String[] RayongDistricts = { "Mueang Rayong", "Klaeng", "Ban Khai", "Wang Chan", "Nikhom Phatthana",
            "Pluak Daeng",
            "Khao Chamao", "Banthamai Chaiyaphot", "Tha Pradu", "Klaeng" };
    private String[] RoiEtDistricts = { "Mueang Roi Et", "Kaset Wisai", "Pathum Rat", "Chaturaphak Phiman",
            "Thawat Buri",
            "Changhan", "Phanom Phrai", "Pho Chai", "Selaphum", "Nong Hi", "At Samat", "Moei Wadi", "Phon Thong",
            "Pho Than", "Chiang Khwan", "Thung Khao Luang", "Sawang Wirawong", "Phon Sai", "Phon Na Kaeo", "Si Somdet",
            "Sung Noen", "Non Sang", "Khwao Sinarin", "Phon Charoen", "Phon Rang", "Nong Phok", "Mueang Suang" };
    private String[] SaKaeoDistricts = { "Muang Sa Kaeo", "Wang Nam Yen", "Aranyaprathet", "Khao Chakan",
            "Watthana Nakhon",
            "Ta Phraya", "Wang Sombun", "Khlong Hat", "Khok Sung", "Khok Soong", "Ban Kruat", "Phanom Dong Rak",
            "Khao Khitchakut", "Khao Saming", "Bo Thong", "Panthong", "Soi Dao", "Na Di", "Khok Pho Chai", "Wang Hin",
            "Nong Ya Plong", "Mueang Rom", "Khlung", "Buri Ram Rat", "Ta Phraya (Pong Nam Ron)", "Khlong Khuean",
            "Khao Chakan (Boran)" };
    private String[] SakonNakhonDistricts = { "Mueang Sakon Nakhon", "Kusuman", "Kut Bak", "Phang Khon",
            "Phanna Nikhom",
            "Wanon Niwat", "Sawang Daen Din", "Song Dao", "Tao Ngoi", "Akathiyawong", "Charoen Sin", "Khok Si Suphan",
            "Kham Ta Kla", "Nikhom Nam Un", "Waritchaphum" };
    private String[] SamutPrakanDistricts = { "Bang Bo", "Bang Phli", "Bang Sao Thong", "Phra Pradaeng",
            "P hra Samut Chedi", "Samut Prakan", "Thepharak" };

    private String[] SamutSakhonDistricts = { "Mueang Samut Sakhon", "Krathum Baen", "Ban Phaeo", "Bang Sao Thong",
            "Phanthai Norasing",
            "Suan Luang Ratchapruek", "Don Tum", "Bang Khun Thian", "Om Noi", "Sam Phran", "Bang Len", "Nong Khaem",
            "Bang Khae Noi", "Khok Kham", "Mahachai", "Kratom Thong", "Bang Yaple", "Bang Nam Chuet", "Bang Pla",
            "Bang Ya Phraek", "Tha Chin", "Lum Luk Ka", "Bang Khun Thian", "Ban Khlong Suan", "Phan Thong",
            "Klong Luang", "Phra Pradaeng" };
    private String[] SamutSongKhramDistricts = { "Mueang Samut Songkhram", "Ban Laem", "Amphawa", "Bang Khonthi",
            "Phan Thong",
            "Don Kai Dee",
            "Bang Nok Khwaek", "Khun Thale", "Krathum Baen", "Lao Si Chong Hua", "Bang Khem", "Tha Chalom",
            "Chang Hua Rat", "Phu Phiang", "Nang Yai" };
    private String[] SaraburiDistricts = { "Mueang Saraburi", "Nong Don", "Wihan Daeng", "Nong Saeng", "Ban Mo",
            "Nong Khae",
            "Kaeng Khoi", "Muak Lek", "Wang Muang", "Chaloem Phra Kiat", "Nong Noi", "Phra Phutthabat", "Don Phut",
            "Bueng Sam Phan", "Sao Hai", "Khao Yoi" };
    private String[] SatunDistricts = { "Mueang Satun", "Khuan Don", "Thung Wa", "La-ngu", "Tha Phae", "Manang",
            "Sikao",
            "Ko Lanta",
            "Pa Phayom", "Pak Phayun" };
    private String[] SingBuriDistricts = { "Mueang Sing Buri", "Bang Rachan", "Khai Bang Rachan", "Tha Chang",
            "In Buri",
            "Phrom Buri" };
    private String[] SisaketDistricts = { "Kanthararom", "Kantharawichai", "Kantharawiphawadi", "Khukhan", "Muang Chan",
            "Nam Kliang",
            "Non Khun", "Phayu", "Phrai Bueng", "Phu Sing", "Rasi Salai", "Si Rattana", "Uthumphon Phisai",
            "Wang Hin" };
    private String[] SongKhlaDistricts = { "Mueang Songkhla", "Sathing Phra", "Singhanakhon", "Kho Hong", "Hat Yai",
            "Na Mom",
            "Na Thawi", "Saba Yoi", "Ranot", "Rattaphum", "Khuan Niang", "Khlong Hoi Khong", "Bang Klam", "Chana",
            "Thepha", "Saba Yoi", "Na Thawi", "Saba Yoi" };
    private String[] SukhothaiDistricts = { "Mueang Sukhothai", "Ban Dan Lan Hoi", "Khiri Mat", "Kong Krailat",
            "Si Nakhon",
            "Si Samrong", "Si Satchanalai", "Thung Saliam" };
    private String[] SuphanBuriDistricts = { "Mueang Suphan Buri", "Dan Chang", "Bang Pla Ma", "Nong Ya Sai",
            "Sam Chuk",
            "Bang Mun Nak",
            "Si Prachan", "Don Chedi", "Song Phi Nong", "U Thong" };
    private String[] SuratThaniDistricts = { "Mueang Surat Thani", "Kanchanadit", "Don Sak", "Ko Samui", "Phunphin",
            "Chaiya",
            "Tha Chana", "Khian Sa", "Vibhavadi", "Ban Na Doem", "Ban Na San", "Ban Na Doem", "Ban Na San",
            "Ban Na Doem", "Ban Na San", "Ban Na Doem", "Ban Na San", "Ban Na Doem", "Ban Na San" };
    private String[] SurinDistricts = { "Mueang Surin", "Chumphon Buri", "Chom Phra", "Tha Tum", "Kap Choeng",
            "Sangkha",
            "Rattanaburi", "Sanom", "Prasat", "Lamduan", "Buachet", "Si Narong", "Phanom Dong Rak", "Phraya Si Racha",
            "Samrong Thap", "Non Narai", "Sikhoraphum" };
    private String[] TakDistricts = { "Mueang Tak", "Ban Tak", "Sam Ngao", "Mae Ramat", "Tha Song Yang", "Mae Sot",
            "Phop Phra",
            "Umphang" };
    private String[] TrangDistricts = { "Mueang Trang", "Kantang", "Huai Yot", "Yan Ta Khao", "Palian", "Sikao",
            "Wang Wiset",
            "Na Yong", "Ratsada", "Hat Samran" };
    private String[] TratDistricts = { "Mueang Trat", "Khlong Yai", "Khao Saming", "Bo Rai", "Laem Ngop", "Ko Chang",
            "Ko Kut",
            "Ko Mak" };
    private String[] UbonRatchaThaniDistricts = { "Mueang Ubon Ratchathani", "Det Udom", "Khueang Nai", "Na Chaluai",
            "Na Yia",
            "Trakan Phuet Phon", "Buntharik", "Don Mot Daeng", "Lao Suea Kok", "Nam Yuen", "Phibun Mangsahan",
            "Samrong", "Sawang Wirawong", "Si Mueang Mai", "Sirindhorn", "Tan Sum", "Thung Si Udom", "Warin Chamrap" };
    private String[] UbonThaniDistricts = { "Mueang Ubon Thani", "Khong Chiam", "Na Yung", "Nam Khun", "Nam Phong",
            "Phibun Rak", "Pho Sai", "Det Udom", "Si Mueang Mai", "Sawang Wirawong", "Warin Chamrap",
            "Trakan Phuet Phon" };
    private String[] UthaiThaniDistricts = { "Mueang Uthai Thani", "Thap Than", "Nong Chang", "Nong Khayang", "Ban Rai",
            "Huai Khot", "Sawang Arom", "Lan Sak", "Nong Pla Lai", "Chang Wang Mi", "Tron",
            "Khuean Srinagarindra National Park", "Hua Khao Khiao", "Tha Sung", "Pa Phayom", "Sawang Wirawong",
            "Dan Chang", "Bang Rachan", "Sawang Arom" };
    private String[] UttaraditDistricts = { "Mueang Uttaradit", "Tha Pla", "Nam Pat", "Fak Tha", "Ban Khok", "Phichai",
            "Tron",
            "Laplae",
            "Thong Saen Khan", "Wang Sam Mo", "Sirasak", "Den Chai", "Phrom Phiram" };
    private String[] YalaDistricts = { "Mueang Yala", "Betong", "Bannang Sata", "Than To", "Yaha", "Raman", "Kabang",
            "Krong Pinang", "Tha Takiap", "Yaha", "Saba Yoi" };
    private String[] YasothonDistricts = { "Mueang Yasothon", "Kut Chum", "Thai Charoen", "Chiang Kham",
            "Kham Khuean Kaeo",
            "Kho Wang", "Loeng Nok Tha", "Maha Chana Chai", "Pa Tio", "Sai Mun", "Sawan Daen Din", "Kumphawapi",
            "Takhli", "Udorn Thani", "Yasothon" };

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Account window = new Account();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public Account() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 984, 560);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Purple Horizon hotel");
        frame.setUndecorated(true);

        ImageIcon NextIcon = new ImageIcon(Account.class.getResource("/Project/right_arrow.png"));
        Image NextImage = NextIcon.getImage();
        Image newNextImage = NextImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon newNextImageIcon = new ImageIcon(newNextImage);

        ImageIcon left1Icon = new ImageIcon(Account.class.getResource("/Project/left_arrow.png"));
        Image left1Image = left1Icon.getImage();
        Image newLeft1Image = left1Image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon newLeft1ImageIcon = new ImageIcon(newLeft1Image);

        ImageIcon leftIcon = new ImageIcon(Account.class.getResource("/Project/left_arrow2.png"));
        Image leftImage = leftIcon.getImage();
        Image newLeftImage = leftImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon newLeftImageIcon = new ImageIcon(newLeftImage);

        ImageIcon RightIcon = new ImageIcon(Account.class.getResource("/Project/Right_arrow2.png"));
        Image RightImage = RightIcon.getImage();
        Image newRightImage = RightImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon newRightImageIcon = new ImageIcon(newRightImage);

        ImageIcon FinishIcon = new ImageIcon(Account.class.getResource("/Project/finish.jpg"));
        Image FinishImage = FinishIcon.getImage();
        Image newFinishImage = FinishImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon newFinishImageIcon = new ImageIcon(newFinishImage);

        ImageIcon hotelIcon = new ImageIcon(Account.class.getResource("/Project/hotel.jpg"));
        Image hotelImage = hotelIcon.getImage();
        Image newHotelImage = hotelImage.getScaledInstance(602, 600, Image.SCALE_SMOOTH);
        ImageIcon newHotelImageIcon = new ImageIcon(newHotelImage);

        ImageIcon HotelIcon1 = new ImageIcon(Account.class.getResource("/Project/hotel1.png"));
        Image HotelImage1 = HotelIcon1.getImage();
        Image newHotel1Image = HotelImage1.getScaledInstance(602, 600, Image.SCALE_SMOOTH);
        ImageIcon newHotel1ImageIcon = new ImageIcon(newHotel1Image);
        frame.setIconImage(newHotel1Image);

        ImageIcon hotel2Icon = new ImageIcon(Account.class.getResource("/Project/hotel2.png"));
        Image hotel2Image = hotel2Icon.getImage();
        Image newHotel2Image = hotel2Image.getScaledInstance(602, 600, Image.SCALE_SMOOTH);
        ImageIcon newHotel2ImageIcon = new ImageIcon(newHotel2Image);

        ImageIcon hotel3Icon = new ImageIcon(Account.class.getResource("/Project/hotel3.png"));
        Image hotel3Image = hotel3Icon.getImage();
        Image newHotel3Image = hotel3Image.getScaledInstance(602, 600, Image.SCALE_SMOOTH);
        ImageIcon newHotel3ImageIcon = new ImageIcon(newHotel3Image);

        ImageIcon hotel4Icon = new ImageIcon(Account.class.getResource("/Project/hotel4.png"));
        Image hotel4Image = hotel4Icon.getImage();
        Image newHotel4Image = hotel4Image.getScaledInstance(602, 600, Image.SCALE_SMOOTH);
        ImageIcon newHotel4ImageIcon = new ImageIcon(newHotel4Image);

        ImageIcon hotel5Icon = new ImageIcon(Account.class.getResource("/Project/hotel5.png"));
        Image hotel5Image = hotel5Icon.getImage();
        Image newHotel5Image = hotel5Image.getScaledInstance(602, 600, Image.SCALE_SMOOTH);
        ImageIcon newHotel5ImageIcon = new ImageIcon(newHotel5Image);

        ImageIcon EyeOpenIcon = new ImageIcon(Account.class.getResource("/Project/EyeOpen2.png"));
        Image EyeOpenImage = EyeOpenIcon.getImage();
        Image newEyeOpenImage = EyeOpenImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon newEyeOpenImageIcon = new ImageIcon(newEyeOpenImage);

        ImageIcon EyeCloseIcon = new ImageIcon(Account.class.getResource("/Project/EyeClose1.png"));
        Image EyeCloseImage = EyeCloseIcon.getImage();
        Image newEyeCloseImage = EyeCloseImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon newEyeCloseImageIcon = new ImageIcon(newEyeCloseImage);

        ImageIcon ExitIcon = new ImageIcon(Account.class.getResource("/Project/exit.png"));
        Image ExitImage = ExitIcon.getImage();
        Image newExitImage = ExitImage.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon newExitImageIcon = new ImageIcon(newExitImage);

        ImageIcon MinimizeIcon = new ImageIcon(Account.class.getResource("/Project/minimize.png"));
        Image MinimizeImage = MinimizeIcon.getImage();
        Image newMinimizeImage = MinimizeImage.getScaledInstance(32, 32,
                Image.SCALE_SMOOTH);
        ImageIcon newMinimizeImageIcon = new ImageIcon(newMinimizeImage);

        barPanel = new JPanel();
        barPanel.setBounds(0, 0, 984, 50);
        frame.getContentPane().add(barPanel);
        barPanel.setBackground(new Color(49, 35, 78));
        barPanel.setLayout(null);
        barPanel.addMouseMotionListener(new MouseMotionAdapter() {
            int x, y;

            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - x;
                int dy = e.getY() - y;
                int newX = frame.getLocation().x + dx;
                int newY = frame.getLocation().y + dy;
                frame.setLocation(newX, newY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

        });
        barPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                e.getX();
                e.getY();
            }
        });

        panel = new JPanel();
        panel.setBackground(new Color(83, 52, 112));
        panel.setBounds(0, 50, 602, 510);
        panel.setLayout(new CardLayout(0, 0));
        frame.getContentPane().add(panel);
        cardLayout = (CardLayout) panel.getLayout();

        ZeroPanel = new JPanel();
        ZeroPanel.setLayout(new GridBagLayout());
        ZeroPanel.setBounds(0, 50, panel.getWidth(), panel.getHeight());
        hotel0 = new JLabel(newHotelImageIcon);
        hotel0.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        hotel0.setBorder(null);
        ZeroPanel.add(hotel0);
        panel.add(ZeroPanel, "ZeroImage");

        FirstImagePanel = new JPanel();
        FirstImagePanel.setLayout(new GridBagLayout());
        FirstImagePanel.setBounds(0, 50, panel.getWidth(), panel.getHeight());
        FirstImagePanel.setLayout(new GridBagLayout());
        hotel = new JLabel(newHotel1ImageIcon);
        hotel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        hotel.setBorder(null);
        FirstImagePanel.add(hotel);
        panel.add(FirstImagePanel, "FirstImagePanel");

        SecondImagePanel = new JPanel();
        SecondImagePanel.setBounds(0, 50, panel.getWidth(), panel.getHeight());
        SecondImagePanel.setLayout(new GridBagLayout());
        hotel2 = new JLabel(newHotel2ImageIcon);
        hotel2.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        hotel2.setBorder(null);
        SecondImagePanel.add(hotel2);
        panel.add(SecondImagePanel, "SecondImagePanel");

        ThirdImagePanel = new JPanel();
        ThirdImagePanel.setBounds(0, 50, panel.getWidth(), panel.getHeight());
        ThirdImagePanel.setLayout(new GridBagLayout());
        hotel3 = new JLabel(newHotel3ImageIcon);
        hotel3.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        hotel3.setBorder(null);
        ThirdImagePanel.add(hotel3);
        panel.add(ThirdImagePanel, "ThirdImagePanel");

        FourthImagePanel = new JPanel();
        FourthImagePanel.setBounds(0, 50, panel.getWidth(), panel.getHeight());
        FourthImagePanel.setLayout(new GridBagLayout());
        hotel4 = new JLabel(newHotel4ImageIcon);
        hotel4.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        hotel4.setBorder(null);
        FourthImagePanel.add(hotel4);
        panel.add(FourthImagePanel, "FourthImagePanel");

        FifthImagePanel = new JPanel();
        FifthImagePanel.setBounds(0, 50, panel.getWidth(), panel.getHeight());
        FifthImagePanel.setLayout(new GridBagLayout());
        hotel5 = new JLabel(newHotel5ImageIcon);
        hotel5.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        hotel5.setBorder(null);
        FifthImagePanel.add(hotel5);
        panel.add(FifthImagePanel, "FifthImagePanel");

        MainPanel = new JPanel();
        MainPanel.setBounds(601, 50, 413, 550);
        frame.getContentPane().add(MainPanel);
        MainPanel.setLayout(new CardLayout(0, 0));

        LoginPanel = new JPanel();
        LoginPanel.setLayout(null);
        LoginPanel.setBackground(Color.WHITE);
        MainPanel.add(LoginPanel, "LoginPanel");

        Username = new JLabel("Username");
        Username.setHorizontalAlignment(SwingConstants.CENTER);
        Username.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
        Username.setBounds(129, 138, 140, 65);
        LoginPanel.add(Username);

        UsernameField = new JTextField();
        UsernameField.setText("Username");
        UsernameField.setFont(new Font("Tohama", Font.PLAIN, 12));
        UsernameField.setForeground(Color.BLACK);
        UsernameField.setColumns(10);
        UsernameField.setBorder(null);
        UsernameField.setBackground(Color.LIGHT_GRAY);
        UsernameField.setBounds(77, 214, 244, 35);
        LoginPanel.add(UsernameField);
        UsernameField.addFocusListener(this);
        UsernameField.addActionListener(this);

        PasswordCorrectLabel = new JLabel("Password must be at least 8 characters");
        PasswordCorrectLabel.setForeground(new Color(255, 0, 0));
        PasswordCorrectLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        PasswordCorrectLabel.setBounds(77, 301, 208, 14);
        PasswordCorrectLabel.setVisible(false);
        LoginPanel.add(PasswordCorrectLabel);

        PasswordField = new JPasswordField();
        PasswordField.setText("Password");
        PasswordField.setForeground(Color.BLACK);
        PasswordField.setColumns(10);
        PasswordField.setBorder(null);
        PasswordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        PasswordField.setBackground(Color.LIGHT_GRAY);
        PasswordField.setBounds(77, 260, 244, 35);
        LoginPanel.add(PasswordField);
        PasswordField.addFocusListener(this);
        PasswordField.addActionListener(this);
        PasswordField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLength();
                PasswordCorrect();
                InvalidLabel.setVisible(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLength();
                PasswordCorrect();
                InvalidLabel.setVisible(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLength();
                PasswordCorrect();
            }

            public void checkLength() {
                if (String.valueOf(PasswordField.getPassword()).length() == 20) {
                    PasswordField.setEditable(false);
                    PasswordField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                                phoneField.setEditable(true);
                            }
                        }
                    });
                }
            }

            void PasswordCorrect() {
                if (String.valueOf(PasswordField.getPassword()).length() < 8
                        && !String.valueOf(PasswordField.getPassword()).isEmpty()
                        && !String.valueOf(PasswordField.getPassword()).equals("Password")) {
                    PasswordCorrectLabel.setVisible(true);
                } else
                    PasswordCorrectLabel.setVisible(false);
            }

        });

        LoginButton = new JButton("Login");
        LoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        LoginButton.setForeground(Color.WHITE);
        LoginButton.setBorder(null);
        LoginButton.setBackground(new Color(83, 52, 112));
        LoginButton.setBounds(145, 329, 100, 75);
        LoginPanel.add(LoginButton);
        LoginButton.setIcon(newNextImageIcon);
        LoginButton.setBorder(BorderFactory.createEmptyBorder());
        LoginButton.setContentAreaFilled(false);
        LoginButton.addActionListener(this);

        InvalidLabel = new JLabel("Username or Password is invalidl");
        InvalidLabel.setForeground(new Color(255, 0, 0));
        InvalidLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        InvalidLabel.setBounds(77, 301, 192, 14);
        InvalidLabel.setVisible(false);
        LoginPanel.add(InvalidLabel);

        CreatePanel = new JPanel();
        MainPanel.add(CreatePanel, "createPanel");
        CreatePanel.setLayout(null);

        UsernameLabel = new JLabel("Username");
        UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        UsernameLabel.setBounds(93, 46, 100, 30);
        CreatePanel.add(UsernameLabel);

        UserTextfield = new JTextField();
        UserTextfield.setBackground(new Color(192, 192, 192));
        UserTextfield.setText("Enter your username");
        UserTextfield.setBounds(93, 87, 213, 30);
        UserTextfield.setFont(new Font("Tohama", Font.PLAIN, 12));
        CreatePanel.add(UserTextfield);
        UserTextfield.setBorder(null);
        UserTextfield.setColumns(10);
        UserTextfield.addFocusListener(this);
        UserTextfield.addActionListener(this);
        UserTextfield.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                UserCorrectLabel.setVisible(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                UserCorrectLabel.setVisible(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }

        });

        UserCorrectLabel = new JLabel("Username already exist");
        UserCorrectLabel.setForeground(new Color(255, 0, 0));
        UserCorrectLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        UserCorrectLabel.setBounds(93, 119, 166, 14);
        UserCorrectLabel.setVisible(false);
        CreatePanel.add(UserCorrectLabel);

        EmailTextfield = new JTextField();
        EmailTextfield.setBackground(new Color(192, 192, 192));
        EmailTextfield.setFont(new Font("Tohama", Font.PLAIN, 12));
        EmailTextfield.setText("Enter your email");
        EmailTextfield.setBounds(93, 176, 213, 30);
        CreatePanel.add(EmailTextfield);
        EmailTextfield.setBorder(null);
        EmailTextfield.setColumns(10);
        EmailTextfield.addFocusListener(this);
        EmailTextfield.addActionListener(this);

        EmailLabelCheck = new JLabel("Email is already exist");
        EmailLabelCheck.setForeground(new Color(255, 0, 0));
        EmailLabelCheck.setFont(new Font("Tahoma", Font.PLAIN, 11));
        EmailLabelCheck.setBounds(93, 207, 180, 14);
        EmailLabelCheck.setVisible(false);
        CreatePanel.add(EmailLabelCheck);

        EmailLabel = new JLabel("Email");
        EmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        EmailLabel.setBounds(93, 144, 55, 21);
        CreatePanel.add(EmailLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordLabel.setBounds(93, 229, 58, 14);
        CreatePanel.add(passwordLabel);

        PasswordTextfield = new JPasswordField();
        PasswordTextfield.setBackground(new Color(192, 192, 192));
        PasswordTextfield.setText("Password");
        PasswordTextfield.setFont(new Font("Tahoma", Font.PLAIN, 12));
        PasswordTextfield.setBounds(93, 254, 213, 30);
        CreatePanel.add(PasswordTextfield);
        PasswordTextfield.setBorder(null);
        PasswordTextfield.setColumns(10);
        PasswordTextfield.addFocusListener(this);
        PasswordTextfield.addActionListener(this);
        PasswordTextfield.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLength();
                checkCapable();
                checkEqualPassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLength();
                checkCapable();
                checkEqualPassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLength();
                checkCapable();
                checkEqualPassword();
            }

            public void checkLength() {
                if (String.valueOf(PasswordTextfield.getPassword()).length() == 20) {
                    PasswordTextfield.setEditable(false);
                    PasswordTextfield.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                                PasswordTextfield.setEditable(true);
                            }
                        }
                    });

                } else if (String.valueOf(PasswordTextfield.getPassword()).length() >= 8
                        && !String.valueOf(PasswordTextfield.getPassword()).equals("Password")) {
                    lengthPassword.setForeground(new Color(61, 190, 119));
                } else {
                    PasswordTextfield.setEditable(true);
                    lengthPassword.setForeground(Color.RED);
                }
            }

            public void checkCapable() {
                upperCaseCount = 0;
                for (int i = 0; i < PasswordTextfield.getPassword().length; i++) {
                    if (Character.isUpperCase(PasswordTextfield.getPassword()[i])) {
                        upperCaseCount++;
                    }
                }
                if (upperCaseCount >= 1
                        && !String.valueOf(PasswordTextfield.getPassword()).equalsIgnoreCase("Password")) {
                    upperCasePassword.setForeground(new Color(61, 190, 119));
                } else {
                    upperCasePassword.setForeground(Color.RED);
                }
            }

            public void checkEqualPassword() {
                if (String.valueOf(PasswordTextfield.getPassword())
                        .equals(String.valueOf(ConfirmedPassword.getPassword()))
                        && !String.valueOf(PasswordTextfield.getPassword()).equals("Password")
                        && !String.valueOf(ConfirmedPassword.getPassword()).equals("Password")
                        && !String.valueOf(PasswordTextfield.getPassword()).isEmpty()
                        && !String.valueOf(ConfirmedPassword.getPassword()).isEmpty()) {
                    EqualPassword.setForeground(new Color(61, 190, 119));
                } else {
                    EqualPassword.setForeground(Color.RED);
                }
            }

        });

        ConfirmLabel = new JLabel("Confirmed password");
        ConfirmLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ConfirmLabel.setBounds(93, 305, 137, 14);
        CreatePanel.add(ConfirmLabel);

        ConfirmedPassword = new JPasswordField();
        ConfirmedPassword.setBackground(new Color(192, 192, 192));
        ConfirmedPassword.setText("Password");
        ConfirmedPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        ConfirmedPassword.setBounds(93, 330, 213, 30);
        CreatePanel.add(ConfirmedPassword);
        ConfirmedPassword.setBorder(null);
        ConfirmedPassword.setColumns(10);
        ConfirmedPassword.addFocusListener(this);
        ConfirmedPassword.addActionListener(this);
        ConfirmedPassword.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLength();
                checkEqualPassword();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLength();
                checkEqualPassword();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLength();
                checkEqualPassword();
            }

            public void checkLength() {
                if (String.valueOf(ConfirmedPassword.getPassword()).length() == 20) {
                    ConfirmedPassword.setEditable(false);
                    ConfirmedPassword.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                                ConfirmedPassword.setEditable(true);
                            }
                        }
                    });

                } else {
                    ConfirmedPassword.setEditable(true);
                }
            }

            public void checkEqualPassword() {
                if (String.valueOf(PasswordTextfield.getPassword())
                        .equals(String.valueOf(ConfirmedPassword.getPassword()))
                        && !String.valueOf(PasswordTextfield.getPassword()).equals("Password")
                        && !String.valueOf(ConfirmedPassword.getPassword()).equals("Password")
                        && !String.valueOf(PasswordTextfield.getPassword()).isEmpty()
                        && !String.valueOf(ConfirmedPassword.getPassword()).isEmpty()) {
                    EqualPassword.setForeground(new Color(61, 190, 119));
                } else {
                    EqualPassword.setForeground(Color.RED);
                }
            }

        });

        nextButton = new JButton();
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.setBounds(220, 434, 100, 75);
        nextButton.setBorder(null);
        nextButton.setIcon(newRightImageIcon);
        nextButton.setBorder(BorderFactory.createEmptyBorder());
        nextButton.setContentAreaFilled(false);
        CreatePanel.add(nextButton);
        nextButton.addActionListener(this);
        nextButton.addMouseListener(this);

        backButton = new JButton();
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.setBounds(93, 434, 100, 75);
        backButton.setBorder(null);
        backButton.setIcon(newLeftImageIcon);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        CreatePanel.add(backButton);
        backButton.addActionListener(this);
        backButton.addMouseListener(this);

        lengthPassword = new JLabel("Password must be 8-20 characters");
        lengthPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lengthPassword.setBounds(93, 399, 225, 14);
        CreatePanel.add(lengthPassword);

        upperCasePassword = new JLabel("Password must be at least 1 uppercase");
        upperCasePassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
        upperCasePassword.setBounds(93, 424, 260, 14);
        CreatePanel.add(upperCasePassword);
        cardLayOut = (CardLayout) MainPanel.getLayout();

        EqualPassword = new JLabel("Password match");
        EqualPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
        EqualPassword.setBounds(93, 374, 225, 14);
        CreatePanel.add(EqualPassword);

        noAccountLabel = new JLabel("Don't have an account?");
        noAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        noAccountLabel.setBounds(129, 415, 140, 14);
        LoginPanel.add(noAccountLabel);

        createLabel = new JLabel("Create an account");
        createLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        createLabel.setBounds(139, 440, 105, 14);
        LoginPanel.add(createLabel);
        createLabel.addMouseListener(this);

        InformationPanel = new JPanel();
        MainPanel.add(InformationPanel, "InformationPanel");
        InformationPanel.setLayout(null);

        name = new JLabel("Name");
        name.setFont(new Font("Tahoma", Font.PLAIN, 14));
        name.setBounds(73, 21, 41, 23);
        InformationPanel.add(name);

        nameField = new JTextField("Enter your name");
        nameField.setBounds(73, 55, 110, 30);
        nameField.setBorder(null);
        nameField.setBackground(new Color(192, 192, 192));
        nameField.setFont(new Font("Tohama", Font.PLAIN, 12));
        InformationPanel.add(nameField);
        nameField.setColumns(10);
        nameField.addFocusListener(this);
        nameField.addActionListener(this);

        surnameField = new JTextField("Enter your surname");
        surnameField.setBounds(204, 55, 110, 30);
        surnameField.setFont(new Font("Tohama", Font.PLAIN, 12));
        surnameField.setBorder(null);
        surnameField.setBackground(new Color(192, 192, 192));
        InformationPanel.add(surnameField);
        surnameField.setColumns(10);
        surnameField.addFocusListener(this);
        surnameField.addActionListener(this);

        surname = new JLabel("Surname");
        surname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        surname.setBounds(204, 21, 70, 23);
        InformationPanel.add(surname);

        phoneNumber = new JLabel("Phone number");
        phoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        phoneNumber.setBounds(73, 96, 103, 14);
        InformationPanel.add(phoneNumber);

        PhoneLabelCheck = new JLabel("Phone number already exist");
        PhoneLabelCheck.setForeground(new Color(255, 0, 0));
        PhoneLabelCheck.setFont(new Font("Tahoma", Font.PLAIN, 11));
        PhoneLabelCheck.setBounds(73, 154, 164, 14);
        InformationPanel.add(PhoneLabelCheck);
        PhoneLabelCheck.setVisible(false);

        PhoneCorrectLabel = new JLabel("Phone number way incorrect");
        PhoneCorrectLabel.setForeground(new Color(255, 0, 0));
        PhoneCorrectLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        PhoneCorrectLabel.setBounds(73, 154, 164, 14);
        InformationPanel.add(PhoneCorrectLabel);
        PhoneCorrectLabel.setVisible(false);

        phoneField = new JTextField("Enter phone");
        phoneField.setBounds(73, 121, 121, 30);
        phoneField.setFont(new Font("Tohama", Font.PLAIN, 12));
        phoneField.setBorder(null);
        phoneField.setBackground(new Color(192, 192, 192));
        InformationPanel.add(phoneField);
        phoneField.setColumns(10);
        phoneField.addFocusListener(this);
        phoneField.addActionListener(this);

        address1Field = new JTextField("Enter your address");
        address1Field.setBounds(73, 243, 252, 30);
        address1Field.setBackground(new Color(192, 192, 192));
        address1Field.setFont(new Font("Tohama", Font.PLAIN, 12));
        address1Field.setBorder(null);
        InformationPanel.add(address1Field);
        address1Field.setColumns(10);
        address1Field.addFocusListener(this);
        address1Field.addActionListener(this);

        address1 = new JLabel("Address");
        address1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        address1.setBounds(73, 218, 52, 14);
        InformationPanel.add(address1);

        address2 = new JLabel("Address (If more)");
        address2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        address2.setBackground(new Color(240, 240, 240));
        address2.setBounds(73, 288, 121, 14);
        InformationPanel.add(address2);

        address2Field = new JTextField("Enter your address");
        address2Field.setBounds(73, 313, 252, 30);
        address2Field.setBackground(new Color(192, 192, 192));
        address2Field.setFont(new Font("Tohama", Font.PLAIN, 12));
        address2Field.setBorder(null);
        InformationPanel.add(address2Field);
        address2Field.setColumns(10);
        address2Field.addFocusListener(this);
        address2Field.addActionListener(this);

        district = new JLabel("District");
        district.setFont(new Font("Tahoma", Font.PLAIN, 14));
        district.setBounds(73, 365, 52, 14);
        InformationPanel.add(district);

        districtField = new JComboBox<String>();
        districtField.setBounds(73, 388, 121, 30);
        districtField.setBorder(BorderFactory.createEmptyBorder());
        districtField.setBackground(new Color(192, 192, 192));
        InformationPanel.add(districtField);
        districtField.addFocusListener(this);
        districtField.addActionListener(this);
        districtField.insertItemAt("Select District", 0);
        districtField.setSelectedIndex(0);
        districtField.setEditable(true);

        province = new JLabel("Province");
        province.setFont(new Font("Tahoma", Font.PLAIN, 14));
        province.setBounds(204, 365, 61, 14);
        InformationPanel.add(province);

        provinceField = new JComboBox<String>();
        provinceField.setBounds(204, 388, 121, 30);
        provinceField.setBackground(new Color(192, 192, 192));
        provinceField.setBorder(BorderFactory.createEmptyBorder());
        InformationPanel.add(provinceField);
        provinceField.addFocusListener(this);
        provinceField.addActionListener(this);

        for (String provinces : Province) {
            provinceField.addItem(provinces);
        }
        provinceField.insertItemAt("Select Province", 0);
        provinceField.setSelectedIndex(0);

        finishButton = new JButton();
        finishButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        finishButton.setBounds(142, 422, 100, 35);
        InformationPanel.add(finishButton);
        finishButton.setBorder(null);
        finishButton.setIcon(newFinishImageIcon);
        finishButton.setBackground(Color.WHITE);
        finishButton.setBorder(BorderFactory.createEmptyBorder());
        finishButton.setContentAreaFilled(false);
        finishButton.addActionListener(this);
        finishButton.addMouseListener(this);

        backButton1 = new JButton();
        backButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton1.setBounds(142, 462, 100, 40);
        InformationPanel.add(backButton1);
        backButton1.setBorder(null);
        backButton1.setIcon(newLeft1ImageIcon);
        backButton1.setBorder(BorderFactory.createEmptyBorder());
        backButton1.setContentAreaFilled(false);
        backButton1.addActionListener(this);
        backButton1.addMouseListener(this);

        PasswordField.setEchoChar('\u25cf');
        PasswordTextfield.setEchoChar('\u25cf');
        ConfirmedPassword.setEchoChar('\u25cf');

        EyeOpen0 = new JButton();
        EyeOpen0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        EyeOpen0.setBounds(314, 270, 46, 21);
        LoginPanel.add(EyeOpen0);
        EyeOpen0.setVisible(true);
        EyeOpen0.setBorder(BorderFactory.createEmptyBorder());
        EyeOpen0.setContentAreaFilled(false);
        EyeOpen0.setIcon(newEyeOpenImageIcon);

        EyeClose0 = new JButton();
        EyeClose0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        EyeClose0.setBounds(314, 270, 46, 21);
        LoginPanel.add(EyeClose0);
        EyeClose0.setVisible(false);
        EyeClose0.setBorder(BorderFactory.createEmptyBorder());
        EyeClose0.setContentAreaFilled(false);
        EyeClose0.setIcon(newEyeCloseImageIcon);

        EyeOpen0.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(PasswordField.getPassword()).equals("Password")) {
                    PasswordField.setText("");
                }
                EyeOpen0.setVisible(false);
                EyeClose0.setVisible(true);
                PasswordField.setEchoChar((char) 0);
            }

        });
        EyeClose0.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(PasswordField.getPassword()).isEmpty()) {
                    PasswordField.setText("Password");
                }
                EyeClose0.setVisible(false);
                EyeOpen0.setVisible(true);
                PasswordField.setEchoChar('\u25cf');
            }

        });

        EyeOpen1 = new JButton();
        EyeOpen1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        EyeOpen1.setBounds(311, 259, 21, 21);
        EyeOpen1.setVisible(true);
        EyeOpen1.setBorder(BorderFactory.createEmptyBorder());
        EyeOpen1.setContentAreaFilled(false);
        EyeOpen1.setIcon(newEyeOpenImageIcon);
        CreatePanel.add(EyeOpen1);

        EyeClose1 = new JButton();
        EyeClose1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        EyeClose1.setBounds(311, 259, 21, 21);
        EyeClose1.setVisible(false);
        EyeClose1.setBorder(BorderFactory.createEmptyBorder());
        EyeClose1.setContentAreaFilled(false);
        EyeClose1.setIcon(newEyeCloseImageIcon);
        CreatePanel.add(EyeClose1);

        EyeOpen1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(PasswordTextfield.getPassword()).equals("Password")) {
                    PasswordTextfield.setText("");
                }
                EyeOpen1.setVisible(false);
                EyeClose1.setVisible(true);
                PasswordTextfield.setEchoChar((char) 0);
            }

        });
        EyeClose1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(PasswordTextfield.getPassword()).isEmpty()) {
                    PasswordTextfield.setText("Password");
                }
                EyeClose1.setVisible(false);
                EyeOpen1.setVisible(true);
                PasswordTextfield.setEchoChar('\u25cf');
            }

        });

        EyeOpen2 = new JButton();
        EyeOpen2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        EyeOpen2.setBounds(311, 334, 21, 21);
        EyeOpen2.setVisible(true);
        EyeOpen2.setBorder(BorderFactory.createEmptyBorder());
        EyeOpen2.setContentAreaFilled(false);
        EyeOpen2.setIcon(newEyeOpenImageIcon);
        CreatePanel.add(EyeOpen2);

        EyeClose2 = new JButton();
        EyeClose2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        EyeClose2.setIcon(newEyeCloseImageIcon);
        EyeClose2.setBorder(BorderFactory.createEmptyBorder());
        EyeClose2.setContentAreaFilled(false);
        EyeClose2.setBounds(311, 334, 21, 21);
        EyeClose2.setVisible(false);
        CreatePanel.add(EyeClose2);

        EmailCorrectLabel = new JLabel("Email is way incorrect");
        EmailCorrectLabel.setForeground(new Color(255, 0, 0));
        EmailCorrectLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
        EmailCorrectLabel.setBounds(93, 207, 144, 14);
        EmailCorrectLabel.setVisible(false);
        CreatePanel.add(EmailCorrectLabel);
        EyeOpen2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(ConfirmedPassword.getPassword()).equals("Password")) {
                    ConfirmedPassword.setText("");
                }
                EyeOpen2.setVisible(false);
                EyeClose2.setVisible(true);
                ConfirmedPassword.setEchoChar((char) 0);
            }

        });
        EyeClose2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(ConfirmedPassword.getPassword()).isEmpty()) {
                    ConfirmedPassword.setText("Password");
                }
                EyeClose2.setVisible(false);
                EyeOpen2.setVisible(true);
                ConfirmedPassword.setEchoChar('\u25cf');
            }

        });

        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(panel);
            }
        });
        timer.start();

        ExitButton = new JButton();
        ExitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ExitButton.setBorder(BorderFactory.createEmptyBorder());
        ExitButton.setContentAreaFilled(false);
        ExitButton.setBounds(930, 11, 54, 23);
        ExitButton.setIcon(newExitImageIcon);
        barPanel.add(ExitButton);
        ExitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure to exit?",
                        "Exit application", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

        });

        MinimizeButton = new JButton();
        MinimizeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        MinimizeButton.setBorder(BorderFactory.createEmptyBorder());
        MinimizeButton.setContentAreaFilled(false);
        MinimizeButton.setBounds(884, 11, 54, 23);
        MinimizeButton.setIcon(newMinimizeImageIcon);
        barPanel.add(MinimizeButton);
        MinimizeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setExtendedState(Frame.ICONIFIED);

            }

        });

        HotelNameLabel = new JLabel("Purple Horizon Hotel");
        HotelNameLabel.setForeground(Color.WHITE);
        HotelNameLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD | Font.ITALIC, 15));
        HotelNameLabel.setBounds(10, 15, 229, 14);
        barPanel.add(HotelNameLabel);

        MonthBox = new JComboBox<String>();
        MonthBox.setBounds(142, 189, 88, 22);
        InformationPanel.add(MonthBox);
        MonthBox.insertItemAt("Month", 0);
        MonthBox.setSelectedItem("Month");
        MonthBox.setBorder(BorderFactory.createEmptyBorder());
        MonthBox.setBackground(new Color(192, 192, 192));

        DateBox = new JComboBox<String>();
        DateBox.setBounds(73, 189, 61, 22);
        InformationPanel.add(DateBox);
        DateBox.setBorder(BorderFactory.createEmptyBorder());
        DateBox.setBackground(new Color(192, 192, 192));
        DateBox.insertItemAt("Date", 0);
        DateBox.setSelectedItem("Date");

        YearBox = new JComboBox<String>();
        YearBox.setBounds(240, 189, 74, 22);
        InformationPanel.add(YearBox);
        YearBox.setBorder(BorderFactory.createEmptyBorder());
        YearBox.setBackground(new Color(192, 192, 192));
        YearBox.insertItemAt("Year", 0);
        YearBox.setSelectedItem("Year");

        BirthdayLabel = new JLabel("Birthday");
        BirthdayLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        BirthdayLabel.setBounds(73, 168, 157, 23);
        InformationPanel.add(BirthdayLabel);

        for (String month : months) {
            MonthBox.addItem(month);
        }
        int currentYear = YearMonth.now().getYear();
        for (int year = currentYear; year >= 1945; year--) {
            YearBox.addItem(String.valueOf(year));
        }
        if (MonthBox.getSelectedItem().equals("February")) {
            if (isLeapYear((int) YearBox.getSelectedItem())) {
                DateCount = 28;
            } else
                DateCount = 29;
        } else if (MonthBox.getSelectedItem().equals("January") || MonthBox.getSelectedItem().equals("March")
                || MonthBox.getSelectedItem().equals("May") || MonthBox.getSelectedItem().equals("July")
                || MonthBox.getSelectedItem().equals("August") || MonthBox.getSelectedItem().equals("October")
                || MonthBox.getSelectedItem().equals("December")) {
            DateCount = 31;
        } else
            DateCount = 30;

        for (int date = 1; date <= DateCount; date++) {
            DateBox.addItem(String.valueOf(date));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userName = UserTextfield.getText();
        String phoneIndex = phoneField.getText();
        String EmailCheck = EmailTextfield.getText().trim();
        String PhoneCheck = phoneField.getText();
        pattern = Pattern.compile("[0-9]");
        matcher = pattern.matcher(PhoneCheck);

        while (matcher.find()) {
            NumberCount++;
        }
        for (int i = 0; i < PasswordTextfield.getPassword().length; i++) {
            if (String.valueOf(PasswordTextfield.getPassword()).matches(".*[A-Z].*")) {
                upperCaseCount++;
            }
        }

        if (e.getSource() == backButton) {
            UsernameField.setText("Username");
            PasswordField.setText("Password");
            cardLayOut.show(MainPanel, "LoginPanel");
        }
        if (e.getSource() == backButton1) {
            cardLayOut.show(MainPanel, "createPanel");
            PasswordTextfield.setText("");
            ConfirmedPassword.setText("");
        }
        if (e.getSource() == nextButton) {
            MonthBox.setSelectedItem("Month");
            DateBox.setSelectedItem("Date");
            YearBox.setSelectedItem("Year");
            nameField.setText("Enter your name");
            surnameField.setText("Enter your surname");
            address1Field.setText("Enter your address");
            address2Field.setText("Enter your address");
            String queryUser = String.format("SELECT * FROM hotel.users WHERE UserName = '%s'", userName);
            ResultSet rs = Select.getData(queryUser);
            boolean userCheck = false;
            try {
                userCheck = rs.next();
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, a);
            }
            String queryEmail = String.format("SELECT * FROM hotel.users WHERE Email = '%s'", EmailCheck);
            ResultSet RS = Select.getData(queryEmail);
            boolean emailCheck = false;
            try {
                emailCheck = RS.next();
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, a);
            }

            if (UserTextfield.getText().equals("Enter your username")
                    || EmailTextfield.getText().equals("Enter your email")
                    || String.valueOf(PasswordTextfield.getPassword()).equals("Password")
                    || String.valueOf(ConfirmedPassword.getPassword()).equals("Password")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please full fill in all text fields",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
            } else if (UserTextfield.getText().isEmpty()
                    || EmailTextfield.getText().isEmpty()
                    || String.valueOf(PasswordTextfield.getPassword()).isEmpty()
                    || String.valueOf(ConfirmedPassword.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please full fill in all text fields",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
            } else if (PasswordTextfield.getPassword().length < 8) {
                JOptionPane.showMessageDialog(
                        null,
                        "Password length must be at least 8 characters",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
            } else if (!String.valueOf(PasswordTextfield.getPassword())
                    .equals(String.valueOf(ConfirmedPassword.getPassword()))) {
                JOptionPane.showMessageDialog(
                        null,
                        "Password do not match",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
            } else if (upperCaseCount < 1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please correct password",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
            } else if (!EmailCheck.matches(emailRegex)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please correct email",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
            } else if (userCheck && emailCheck) {
                UserCorrectLabel.setVisible(true);
                EmailLabelCheck.setVisible(true);
                EmailCorrectLabel.setVisible(false);
            } else if (userCheck) {
                UserCorrectLabel.setVisible(true);
            } else if (emailCheck) {
                EmailLabelCheck.setVisible(true);
                EmailCorrectLabel.setVisible(false);
            } else {
                cardLayOut.show(MainPanel, "InformationPanel");
            }
        }
        if (e.getSource() == finishButton) {
            String queryPhone = String.format("SELECT * FROM hotel.users WHERE Phone = '%s'", phoneField.getText());
            ResultSet RR = Select.getData(queryPhone);
            boolean phoneCheck = false;
            try {
                phoneCheck = RR.next();
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, a);
            }
            if (nameField.getText().equals("Enter your name")
                    || surnameField.getText().equals("Enter your surname")
                    || phoneField.getText().equals("Enter phone")
                    || address1Field.getText().equals("Enter you address")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please full fill in all text fields",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
            } else if (nameField.getText().isEmpty()
                    || surnameField.getText().isEmpty()
                    || phoneField.getText().isEmpty()
                    || address1Field.getText().isEmpty()) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please full fill in all text fields",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
            } else if ((NumberCount < 10 || !String.valueOf(phoneIndex.charAt(0)).equals("0"))
                    || (!String.valueOf(phoneIndex.charAt(1)).equals("6")
                            && !String.valueOf(phoneIndex.charAt(1)).equals("8")
                            && !String.valueOf(phoneIndex.charAt(1)).equals("9"))) {

                JOptionPane.showMessageDialog(
                        null,
                        "Please correct phone number",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);

            } else if (provinceField.getSelectedItem().equals("Select Province")
                    || districtField.getSelectedItem().equals("Select District")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please Select Province and District",
                        "Error",
                        JOptionPane.PLAIN_MESSAGE,
                        null);
            } else if (phoneCheck) {
                PhoneLabelCheck.setVisible(true);
            } else {
                int option = JOptionPane.showConfirmDialog(
                        null,
                        "You confirmed your information correctly?",
                        "Confirmed",
                        JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    String Insertquery = String.format(
                            "INSERT INTO hotel.users (UserName,Email,Password,Name,Surname,Phone,"
                                    + "Address,AddressPlus,District,Province) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            userName, EmailCheck, String.valueOf(PasswordTextfield.getPassword()), nameField.getText(),
                            surnameField.getText(), phoneField.getText(), address1Field.getText(),
                            address2Field.getText(), districtField.getSelectedItem(), provinceField.getSelectedItem());
                    InsertUpdateDelete.setData(Insertquery, "Create account success");
                    cardLayOut.show(MainPanel, "LoginPanel");
                }

            }
        }
        if (e.getSource() == backButton1 || e.getSource() == createLabel) {
            upperCaseCount = 0;
        }
        if (e.getSource() == LoginButton) {
            String queryLogin = String.format("SELECT * FROM hotel.users WHERE UserName = '%s' and Password = '%s'",
                    UsernameField.getText(), String.valueOf(PasswordField.getPassword()));
            ResultSet Rs = Select.getData(queryLogin);
            boolean loginCheck = false;
            try {
                if (Rs.next()) {
                    loginCheck = true;
                }
            } catch (Exception a) {
                JOptionPane.showMessageDialog(null, a);
            }
            if (loginCheck) {
                HPage obj = new HPage(UsernameField.getText());
                obj.frame.setVisible(true);
                frame.dispose();
            } else {
                InvalidLabel.setVisible(true);
                PasswordCorrectLabel.setVisible(false);
            }

        }
        if (e.getSource() == provinceField) {

            if (provinceField.getSelectedIndex() == 0) {
                districtField.setEditable(false);
            } else if (provinceField.getSelectedItem().equals("Bangkok")) {
                districtField.removeAllItems();
                for (String district : BangkokDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Amnat Charoen")) {
                districtField.removeAllItems();
                for (String district : AmnatCharoenDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Ang Thong")) {
                districtField.removeAllItems();
                for (String district : AngThongDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Bueng Kan")) {
                districtField.removeAllItems();
                for (String district : BuengKanDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Buriram")) {
                districtField.removeAllItems();
                for (String district : BuriramDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Chachoengsao")) {
                districtField.removeAllItems();
                for (String district : ChaChoengSaoDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Chai Nat")) {
                districtField.removeAllItems();
                for (String district : ChaiNatDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Chaiyapum")) {
                districtField.removeAllItems();
                for (String district : ChaiyaphumDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Chantaburi")) {
                districtField.removeAllItems();
                for (String district : ChantaburiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Chiang Mai")) {
                districtField.removeAllItems();
                for (String district : ChiangMaiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Chiang Rai")) {
                districtField.removeAllItems();
                for (String district : ChaingRaiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Chonburi")) {
                districtField.removeAllItems();
                for (String district : ChonburiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Chumphon")) {
                districtField.removeAllItems();
                for (String district : ChumPhonDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Kalasin")) {
                districtField.removeAllItems();
                for (String district : KalasinDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Kampheng Phet")) {
                districtField.removeAllItems();
                for (String district : KamPhaengPhetDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Kanchanaburi")) {
                districtField.removeAllItems();
                for (String district : KanchanaburiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Khon Kaen")) {
                districtField.removeAllItems();
                for (String district : KhonKaenDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Krabi")) {
                districtField.removeAllItems();
                for (String district : KrabiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Lampang")) {
                districtField.removeAllItems();
                for (String district : LampangDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Lamphun")) {
                districtField.removeAllItems();
                for (String district : LamphunDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Loei")) {
                districtField.removeAllItems();
                for (String district : LoeiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Lopburi")) {
                districtField.removeAllItems();
                for (String district : LopburiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Mae Hong Son")) {
                districtField.removeAllItems();
                for (String district : MaeHongSonDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Maha Sarakham")) {
                districtField.removeAllItems();
                for (String district : MahaSaraKhamdistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Mukdahan")) {
                districtField.removeAllItems();
                for (String district : MukdahanDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Nakhon Nayok")) {
                districtField.removeAllItems();
                for (String district : NakhonNayokDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Nakhon Pathom")) {
                districtField.removeAllItems();
                for (String district : NakhonPathomDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Nakhon Ratchasima")) {
                districtField.removeAllItems();
                for (String district : NakhonRatchasimaDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Nakhon Sawan")) {
                districtField.removeAllItems();
                for (String district : NakhonSawanDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Makhon Si Thammarat")) {
                districtField.removeAllItems();
                for (String district : NakhonSiThammaratDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Nan")) {
                districtField.removeAllItems();
                for (String district : NanDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Narathiwat")) {
                districtField.removeAllItems();
                for (String district : NarathiwatDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Nong Bua Lamphu")) {
                districtField.removeAllItems();
                for (String district : NongBuaLumphuDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Nong Khai")) {
                districtField.removeAllItems();
                for (String district : NongkhaiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Nonthaburi")) {
                districtField.removeAllItems();
                for (String district : NonthaburiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Pathum Thani")) {
                districtField.removeAllItems();
                for (String district : PathumThaniDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Pattani")) {
                districtField.removeAllItems();
                for (String district : PattaniDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phang Nga")) {
                districtField.removeAllItems();
                for (String district : PhangNgaDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phatthalung")) {
                districtField.removeAllItems();
                for (String district : PhattalungDistrict) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phayao")) {
                districtField.removeAllItems();
                for (String district : PhayaoDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phetchaburi")) {
                districtField.removeAllItems();
                for (String district : PhetchaburiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phetchabun")) {
                districtField.removeAllItems();
                for (String district : PhetchabunDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phichit")) {
                districtField.removeAllItems();
                for (String district : PhichitDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phitsanulok")) {
                districtField.removeAllItems();
                for (String district : PhitsanulokDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phra Nakhon Si Ayutthaya")) {
                districtField.removeAllItems();
                for (String district : AyutthayaDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phrae")) {
                districtField.removeAllItems();
                for (String district : PhraeDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phuket")) {
                districtField.removeAllItems();
                for (String district : PhuketDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Prachinburi")) {
                districtField.removeAllItems();
                for (String district : PrachinburiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Phachuap Khiri Khan")) {
                districtField.removeAllItems();
                for (String district : PhachuapKhriKhanDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Ranong")) {
                districtField.removeAllItems();
                for (String district : RanongDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Ratchaburi")) {
                districtField.removeAllItems();
                for (String district : RatchaburiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Rayong")) {
                districtField.removeAllItems();
                for (String district : RayongDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Roi Et")) {
                districtField.removeAllItems();
                for (String district : RoiEtDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Sa Kaeo")) {
                districtField.removeAllItems();
                for (String district : SaKaeoDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Sakon Nakhon")) {
                districtField.removeAllItems();
                for (String district : SakonNakhonDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Samut Prakan")) {
                districtField.removeAllItems();
                for (String district : SamutPrakanDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Samut Sakhon")) {
                districtField.removeAllItems();
                for (String district : SamutSakhonDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Samut Songkhram")) {
                districtField.removeAllItems();
                for (String district : SamutSongKhramDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Saraburi")) {
                districtField.removeAllItems();
                for (String district : SaraburiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Satun")) {
                districtField.removeAllItems();
                for (String district : SatunDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Sing Buri")) {
                districtField.removeAllItems();
                for (String district : SingBuriDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Sisaket")) {
                districtField.removeAllItems();
                for (String district : SisaketDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("SongKhla")) {
                districtField.removeAllItems();
                for (String district : SongKhlaDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Sukhothai")) {
                districtField.removeAllItems();
                for (String district : SukhothaiDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Suphan Buri")) {
                districtField.removeAllItems();
                for (String district : SuphanBuriDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Surat Thani")) {
                districtField.removeAllItems();
                for (String district : SuratThaniDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Surin")) {
                districtField.removeAllItems();
                for (String district : SurinDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Tak")) {
                districtField.removeAllItems();
                for (String district : TakDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Trang")) {
                districtField.removeAllItems();
                for (String district : TrangDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Trat")) {
                districtField.removeAllItems();
                for (String district : TratDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Ubon Ratchathani")) {
                districtField.removeAllItems();
                for (String district : UbonRatchaThaniDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Ubon Thani")) {
                districtField.removeAllItems();
                for (String district : UbonThaniDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Uthai Thani")) {
                districtField.removeAllItems();
                for (String district : UthaiThaniDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Uttaradit")) {
                districtField.removeAllItems();
                for (String district : UttaraditDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Yala")) {
                districtField.removeAllItems();
                for (String district : YalaDistricts) {
                    districtField.addItem(district);
                }
            } else if (provinceField.getSelectedItem().equals("Yasothon")) {
                districtField.removeAllItems();
                for (String district : YasothonDistricts) {
                    districtField.addItem(district);
                }
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == UsernameField) {
            if (UsernameField.getText().equals("Username")) {
                UsernameField.setText("");
            }
        } else if (e.getSource() == PasswordField) {
            if (String.valueOf(PasswordField.getPassword()).equals("Password")) {
                PasswordField.setText("");
            }
        } else if (e.getSource() == UserTextfield) {
            if (UserTextfield.getText().equals("Enter your username")) {
                UserTextfield.setText("");
            }
        } else if (e.getSource() == EmailTextfield) {
            if (EmailTextfield.getText().equals("Enter your email")) {
                EmailTextfield.setText("");
                EmailTextfield.getDocument().addDocumentListener(new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        EmailCorrecting();
                        EmailLabelCheck.setVisible(false);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        EmailCorrecting();
                        EmailLabelCheck.setVisible(false);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        EmailCorrecting();
                    }

                    public void EmailCorrecting() {
                        if (!EmailTextfield.getText().trim().matches(emailRegex)
                                && !EmailTextfield.getText().isEmpty()
                                && !EmailTextfield.getText().equals("Enter your email")) {
                            EmailCorrectLabel.setVisible(true);
                        } else {
                            EmailCorrectLabel.setVisible(false);
                        }
                    }

                });
            }
        } else if (e.getSource() == PasswordTextfield) {
            if (String.valueOf(PasswordTextfield.getPassword()).equals("Password")) {
                PasswordTextfield.setText("");
            }
        } else if (e.getSource() == ConfirmedPassword) {
            if (String.valueOf(ConfirmedPassword.getPassword()).equals("Password")) {
                ConfirmedPassword.setText("");
            }
        } else if (e.getSource() == nameField) {
            if (nameField.getText().equals("Enter your name")) {
                nameField.setText("");
            }
        } else if (e.getSource() == surnameField) {
            if (surnameField.getText().equals("Enter your surname")) {
                surnameField.setText("");
            }
        } else if (e.getSource() == address1Field) {
            if (address1Field.getText().equals("Enter your address")) {
                address1Field.setText("");
            }
        } else if (e.getSource() == address2Field) {
            if (address2Field.getText().equals("Enter your address")) {
                address2Field.setText("");
            }
        } else if (e.getSource() == phoneField && (phoneField.getText().equals("Enter phone"))) {
            phoneField.setText("");
            phoneField.setDocument(new NumericDocument());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (UsernameField.getText().isEmpty()) {
            UsernameField.setText("Username");
        } else if (String.valueOf(PasswordField.getPassword()).isEmpty()) {
            PasswordField.setText("Password");
        } else if (UserTextfield.getText().isEmpty()) {
            UserTextfield.setText("Enter your username");
        } else if (EmailTextfield.getText().isEmpty()) {
            EmailTextfield.setText("Enter your email");
        } else if (String.valueOf(PasswordTextfield.getPassword()).isEmpty()) {
            PasswordTextfield.setText("Password");
        } else if (String.valueOf(ConfirmedPassword.getPassword()).isEmpty()) {
            ConfirmedPassword.setText("Password");
        } else if (nameField.getText().isEmpty()) {
            nameField.setText("Enter your name");
        } else if (surnameField.getText().isEmpty()) {
            surnameField.setText("Enter your surname");
        } else if (!phoneField.getText().matches("\\d+")) {
            phoneField.setText("Enter phone");
        } else if (address1Field.getText().isEmpty()) {
            address1Field.setText("Enter your address");
        } else if (address2Field.getText().isEmpty()) {
            address2Field.setText("Enter your address");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == createLabel) {
            cardLayOut.show(MainPanel, "createPanel");
            UserTextfield.setText("Enter your username");
            EmailTextfield.setText("Enter your email");
            PasswordTextfield.setText("Password");
            ConfirmedPassword.setText("Password");
        } else if (e.getSource() == phoneField) {
            phoneField.setEditable(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == createLabel) {
            createLabel.setForeground(new Color(192, 192, 192));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == createLabel) {
            createLabel.setForeground(new Color(0, 0, 0));
        }
    }

    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public class NumericDocument extends PlainDocument {
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null || str.isEmpty()) {
                return;
            }

            StringBuilder builder = new StringBuilder(getText(0, getLength()));
            builder.insert(offset, str);

            if (isNumeric(builder.toString())) {
                super.insertString(offset, str, attr);
                phoneField.getDocument().addDocumentListener(new DocumentListener() {

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        checkLength();
                        PhoneLabelCheck.setVisible(false);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        checkLength();
                        PhoneLabelCheck.setVisible(false);
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        checkLength();
                        PhoneLabelCheck.setVisible(false);
                    }

                    public void checkLength() {
                        String phoneIndex = phoneField.getText();
                        if (phoneField.getText().length() == 10) {
                            phoneField.setEditable(false);
                            phoneField.addKeyListener(new KeyAdapter() {
                                @Override
                                public void keyPressed(KeyEvent e) {
                                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                                        phoneField.setEditable(true);
                                    }
                                }
                            });

                        } else {
                            phoneField.setEditable(true);
                        }
                        if (phoneField.getText().length() < 10 || !String.valueOf(phoneIndex.charAt(0)).equals("0")
                                || (!String.valueOf(phoneIndex.charAt(1)).equals("6")
                                        && !String.valueOf(phoneIndex.charAt(1)).equals("8")
                                        && !String.valueOf(phoneIndex.charAt(1)).equals("9"))) {
                            PhoneCorrectLabel.setVisible(true);
                        } else
                            PhoneCorrectLabel.setVisible(false);
                    }
                });
            }
        }

        private boolean isNumeric(String str) {
            return str.matches("\\d+");
        }
    }
}
