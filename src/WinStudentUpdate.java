import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class WinStudentUpdate extends JDialog {
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfPhone1;
	private JTextField tfJumin1;
	private JTextField tfEmail;
	private JTextField tfPhone2;
	private JTextField tfAddress;
	private JLabel lblDept;
	private JButton btnSelect;
	private String fileName;
	private JComboBox cbPhone;
	private JComboBox cbEmail;
	private String temp;
	private JPasswordField tfJumin2;
	private JLabel lblPic;
	private JButton btnUpdate;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinStudentUpdate dialog = new WinStudentUpdate();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public WinStudentUpdate() {
		getContentPane().setFont(new Font("굴림", Font.BOLD, 12));
		setTitle("\uD559\uC0DD \uC815\uBCF4 \uBCC0\uACBD\uCC3D");
		setBounds(100, 100, 606, 317);
		getContentPane().setLayout(null);
		
		lblPic = new JLabel();
//		ImageIcon imageIcon = new ImageIcon("D:/images/kil.jpg");
//		Image image = imageIcon.getImage();
//		image = image.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
//		ImageIcon img = new ImageIcon(image);
//		lblPic.setIcon(img);
		lblPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) { //더블클릭
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("그림파일", "jpg", "gif", "png");
					chooser.setFileFilter(filter);
					int ret = chooser.showOpenDialog(null); //0 open 1 cancel
					
					if(ret == JFileChooser.APPROVE_OPTION) {
					fileName = chooser.getSelectedFile().getPath();
					
					ImageIcon imageIcon = new ImageIcon(fileName);
					Image image = imageIcon.getImage();
					image = image.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
					ImageIcon img = new ImageIcon(image);
					lblPic.setIcon(img);
					}
				}
			}
		});
		lblPic.setBackground(new Color(128, 128, 128));
		lblPic.setToolTipText("더블클릭하여 선택하세요");
		lblPic.setBounds(20, 23, 150, 180);
		lblPic.setOpaque(true);
		getContentPane().add(lblPic);
		
		
		JLabel lblID = new JLabel("학번(ID) :");
		lblID.setFont(new Font("굴림", Font.BOLD, 12));
		lblID.setBounds(232, 38, 64, 15);
		getContentPane().add(lblID);
		
		JLabel lblName = new JLabel("이름 :");
		lblName.setFont(new Font("굴림", Font.BOLD, 12));
		lblName.setBounds(232, 71, 64, 15);
		getContentPane().add(lblName);
		
		JLabel lblJumin = new JLabel("주민번호 :");
		lblJumin.setFont(new Font("굴림", Font.BOLD, 12));
		lblJumin.setBounds(232, 138, 64, 15);
		getContentPane().add(lblJumin);
		
		JLabel lblPhone = new JLabel("전화번호 :");
		lblPhone.setFont(new Font("굴림", Font.BOLD, 12));
		lblPhone.setBounds(232, 105, 64, 15);
		getContentPane().add(lblPhone);
		
		JLabel lblEmail = new JLabel("이메일 :");
		lblEmail.setFont(new Font("굴림", Font.BOLD, 12));
		lblEmail.setBounds(232, 173, 64, 15);
		getContentPane().add(lblEmail);
		
		tfId = new JTextField();
		tfId.setEnabled(false);
		tfId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
		tfId.setBounds(335, 35, 116, 21);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		tfName = new JTextField();
		tfName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfName.setSelectionStart(0);
				tfName.setSelectionEnd(tfName.getText().length());
			}
		});
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfPhone1.requestFocus();
					
				}
			}
		});
		tfName.setColumns(10);
		tfName.setBounds(335, 68, 116, 21);
		getContentPane().add(tfName);
		
		tfPhone1 = new JTextField();
		tfPhone1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfPhone1.setSelectionStart(0);
				tfPhone1.setSelectionEnd(tfPhone1.getText().length());
			}
		});
		tfPhone1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String temp = tfPhone1.getText(); 
						tfPhone2.requestFocus();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
				String temp = tfPhone1.getText();
				if(temp.length() == 4 && isInteger(temp)) 
					tfPhone2.requestFocus();
				
				}
			
		});
		tfPhone1.setColumns(10);
		tfPhone1.setBounds(401, 101, 73, 21);
		getContentPane().add(tfPhone1);
		tfJumin1 = new JTextField();
		tfJumin1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfJumin1.setSelectionStart(0);
				tfJumin1.setSelectionEnd(tfJumin1.getText().length());
			}
		});
		tfJumin1.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				String temp = tfJumin1.getText();
				if(temp.length() == 6 && isInteger(temp)) 
					tfJumin2.requestFocus();
			}
		});
		tfJumin1.setColumns(10);
		tfJumin1.setBounds(335, 135, 95, 21);
		getContentPane().add(tfJumin1);
		
		tfEmail = new JTextField();
		tfEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfEmail.setSelectionStart(0);
				tfEmail.setSelectionEnd(tfEmail.getText().length());
			}
		});
		tfEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				cbEmail.requestFocus();
				}
			}
		});
		tfEmail.setColumns(10);
		tfEmail.setBounds(335, 170, 95, 21);
		getContentPane().add(tfEmail);
		
		tfPhone2 = new JTextField();
		tfPhone2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfPhone2.setSelectionStart(0);
				tfPhone2.setSelectionEnd(tfPhone2.getText().length());
			}
		});
		tfPhone2.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				
					String temp = tfPhone2.getText();
					if(temp.length() == 4 && isInteger(temp)) 
						tfJumin1.requestFocus();
					
				
			}
		});
		tfPhone2.setColumns(10);
		tfPhone2.setBounds(497, 101, 73, 21);
		getContentPane().add(tfPhone2);
		
		cbPhone = new JComboBox();
		cbPhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPhone1.requestFocus();
			}
		});
		cbPhone.setModel(new DefaultComboBoxModel(new String[] {"010", "02", "032", "031"}));
		cbPhone.setBounds(334, 100, 47, 23);
		getContentPane().add(cbPhone);
		
		JLabel lblNewLabel = new JLabel("@");
		lblNewLabel.setBounds(442, 173, 20, 15);
		getContentPane().add(lblNewLabel);
		
		cbEmail = new JComboBox();
		cbEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cbEmail.getSelectedItem().equals("직접입력")) {
					tfAddress.requestFocus();
				}else {
					cbEmail.setSelectedItem("");
					cbEmail.requestFocus();
				}
			}
		});
		cbEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					tfAddress.requestFocus();
						
				}
			}
		});
		cbEmail.setFont(new Font("굴림", Font.BOLD, 12));
		cbEmail.setEditable(true);
		cbEmail.setModel(new DefaultComboBoxModel(new String[] {"직접입력", "naver.com", "gmail.com", "daum.net"}));
		cbEmail.setBounds(466, 169, 104, 23);
		getContentPane().add(cbEmail);
		
		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setBounds(440, 138, 20, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("-");
		lblNewLabel_1_1.setBounds(388, 104, 20, 15);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("-");
		lblNewLabel_1_2.setBounds(482, 104, 20, 15);
		getContentPane().add(lblNewLabel_1_2);
		
		btnUpdate = new JButton("학생 정보 변경");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateStudentInfomation();
			}
		});
		btnUpdate.setFont(new Font("굴림", Font.BOLD, 12));
		btnUpdate.setBounds(453, 210, 117, 42);
		getContentPane().add(btnUpdate);
		
		JLabel lblAddress = new JLabel("주소 :");
		lblAddress.setFont(new Font("굴림", Font.BOLD, 12));
		lblAddress.setBounds(129, 224, 57, 15);
		getContentPane().add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfAddress.setSelectionStart(0);
				tfAddress.setSelectionEnd(tfAddress.getText().length());
				
			}
		});
		tfAddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					
					
					
						
				}
				
			}
		});
		tfAddress.setBounds(177, 221, 264, 21);
		getContentPane().add(tfAddress);
		tfAddress.setColumns(10);
		
		btnSelect = new JButton("조회...");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfId.setText(JOptionPane.showInputDialog(getContentPane(), "학번(ID)"));
				showStudentInfomation(); //해당 학번의 정보를 조회한다
				
			}
		});
		btnSelect.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					tfName.requestFocus();
						
				}
				
			}
		});
		btnSelect.setFont(new Font("굴림", Font.BOLD, 12));
		btnSelect.setBounds(472, 34, 97, 23);
		getContentPane().add(btnSelect);
		
		lblDept = new JLabel("학과 : ");
		lblDept.setForeground(new Color(255, 0, 0));
		lblDept.setFont(new Font("굴림", Font.BOLD, 12));
		lblDept.setBounds(470, 71, 100, 15);
		getContentPane().add(lblDept);
		
		tfJumin2 = new JPasswordField();
		tfJumin2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfJumin2.setSelectionStart(0);
				tfJumin2.setSelectionEnd(new String(tfJumin2.getPassword()).length());
			}
		});
		tfJumin2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String temp = new String(tfJumin2.getPassword()); //String class 생성자를 확인
				if(temp.length() >= 7 && isInteger(temp)) 
					tfEmail.requestFocus();
				
			}
		});
		tfJumin2.setBounds(459, 135, 110, 21);
		getContentPane().add(tfJumin2);
		disableComponent();
	}
	public WinStudentUpdate(String sId) {
		this();
		tfId.setText(sId);
		showStudentInfomation(); //해당 학번의 정보를 조회한다
	}

	private void disableComponent() {
		lblPic.setEnabled(false);
		tfName.setEnabled(false);
		cbEmail.setEnabled(false);
		tfPhone1.setEnabled(false);
		tfPhone2.setEnabled(false);
		tfJumin1.setEnabled(false);
		tfJumin2.setEnabled(false);
		tfEmail.setEnabled(false);
		tfAddress.setEnabled(false);
		cbPhone.setEnabled(false);
		btnUpdate.setEnabled(false);
		
	}

	protected void UpdateStudentInfomation() {
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
                           
            
            //=============================================
            String sql = "UPDATE studenttbl SET name=?, phone=?, jumin=?, email=?, pic=?, address=? WHERE id=?";
           
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            
            pstmt.setString(1, tfName.getText());
            pstmt.setString(2, cbPhone.getSelectedItem() + tfPhone1.getText() + tfPhone2.getText());
            pstmt.setString(3, tfJumin1.getText() + new String(tfJumin2.getPassword()));
            pstmt.setString(4, tfEmail.getText() + "@" + cbEmail.getSelectedItem());
            pstmt.setString(5, fileName);
            pstmt.setString(6, tfAddress.getText());
            pstmt.setString(7, tfId.getText());
            
            
            
            int result = pstmt.executeUpdate();
            
            if(result == 1) {
            	System.out.println("수정 성공");
            }else {
            	System.out.println("입력 실패");
            }
            

           
            pstmt.close();
            conn.close();
            // 전체 텍스트 필드만 공백으로 변경하기
            Component [] componentList = getContentPane().getComponents();
            
  	      	for(Component c : componentList) {
  			if(c instanceof JTextField)
  	        	 ((JTextField) c).setText("");
  	      }
  	      	cbPhone.setSelectedIndex(0);
  	      	cbEmail.setSelectedIndex(1);
  	      disableComponent();
			}catch (ClassNotFoundException e1) {
	            System.out.println("JDBC 드라이버 로드 에러");
	         } catch (SQLException e1) {
	            System.out.println("DB 연결 오류");
	            e1.printStackTrace();
	         }
		
	}

	protected void showStudentInfomation() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
            System.out.println("DB 연결 완료");               
            Statement statement=conn.createStatement();
            //=============================================
            String sql = "select * from studenttbl where id= ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, tfId.getText());
            ResultSet rs = pstmt.executeQuery();
            
            String[] record = new String[6];
            if(rs.next()){//중복
            	disableComponent();
           	 for(int i=0; i < record.length; i++) {
           		 record[i] = rs.getString(i+2); // name부터
           		 
           	 }
           	 	enableComponent();
           	  	tfName.setText(record[0]);
           	  	String sPhone = record[1];
           	  	if(sPhone.substring(0, 2).equals("02")){
           	  	cbPhone.setSelectedItem(sPhone.substring(0, 3));
           	  	tfPhone1.setText(sPhone.substring(2, sPhone.length()-4));
           	  	
           	  	}else {
           	  	cbPhone.setSelectedItem(sPhone.substring(0, 3));
           	  	tfPhone1.setText(sPhone.substring(3, sPhone.length()-4));
           	  	}
          
           	  	tfPhone2.setText(record[1].substring(record[1].length()-4, record[1].length()));
           	  	tfJumin1.setText(record[2].substring(0, 6));
           	  	tfJumin2.setText(record[2].substring(6, record[2].length()));
           	  	int email = record[3].indexOf("@");
           	  	tfEmail.setText(record[3].substring(0, email));
           	  	cbEmail.setSelectedItem(record[3].substring(email+1, record[3].length()));
           	  	
           	  	fileName = record[4];
           	  	
           	  	ImageIcon imageIcon = new ImageIcon(fileName);
				Image image = imageIcon.getImage();
				image = image.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
				ImageIcon img = new ImageIcon(image);
				lblPic.setIcon(img);
           	  	tfAddress.setText(record[5]);
           	  	
           	  	HashMap<String, String> dept = new HashMap<String, String>(); //2. HashMap 활용
				dept.put("01","국문학과");
				dept.put("02","영문학과");
				dept.put("10","컴퓨터학과");
				dept.put("11","전자공학과");
				dept.put("20","연극영화과");
				dept.put("21","음악과");
				
           	  	temp = tfId.getText();
           	  	
           	  	
				String sId = temp.substring(4, 6);
				lblDept.setText("학과 :" + dept.get(sId));
				System.out.println("정보 조회성공");
            }
            rs.close();
            pstmt.close();
            conn.close();
            //==============================================
        } catch (ClassNotFoundException e1) {
           System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e1) {
           System.out.println("DB 연결 오류");
        }
		
		
	}

	
	private void enableComponent() {
		lblPic.setEnabled(true);
		tfName.setEnabled(true);
		cbEmail.setEnabled(true);
		tfPhone1.setEnabled(true);
		tfPhone2.setEnabled(true);
		tfJumin1.setEnabled(true);
		tfJumin2.setEnabled(true);
		tfEmail.setEnabled(true);
		tfAddress.setEnabled(true);
		cbPhone.setEnabled(true);
		btnUpdate.setEnabled(true);
		
	}

	public static boolean isInteger(String strValue) {
	       try {
	         Integer.parseInt(strValue);
	         return true;
	       } catch (NumberFormatException ex) {
	         return false;
	       }
	   }
}
