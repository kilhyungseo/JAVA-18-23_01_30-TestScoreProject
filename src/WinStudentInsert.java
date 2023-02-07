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

public class WinStudentInsert extends JDialog {
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfPhone1;
	private JTextField tfJumin1;
	private JTextField tfEmail;
	private JTextField tfPhone2;
	private JTextField tfAddress;
	private JLabel lblDept;
	private JButton btnOverlapped;
	private String fileName;
	private JComboBox cbPhone;
	private JComboBox cbEmail;
	private String temp;
	private JPasswordField tfJumin2;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinStudentInsert dialog = new WinStudentInsert();
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
	public WinStudentInsert() {
		getContentPane().setFont(new Font("굴림", Font.BOLD, 12));
		setTitle("학생 정보 입력창");
		setBounds(100, 100, 606, 317);
		getContentPane().setLayout(null);
		
		JLabel lblPic = new JLabel();
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
		tfId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					temp = tfId.getText();
					if(temp.length() == 9 && isInteger(temp)) {
						checkId();
					
					}
					
					
						
					
//					String code[] = {"01", "02", "10", "11", "20", "21"}; 1. 배열을 활용한 방법
//					String dept[] = {"국문학과", "영문학과", "컴퓨터학과", "전자공학과", "연극영화과", "음악과"};
//					
//					String sId = tfId.getText().substring(4, 6);
//					int idx = 0;
//					for(int i=0; i < code.length; i++) {
//						if(sId.equals(code[i])) {
//							idx = i;
//							break;
//						}
//					}
//					lblDept.setText("학과 :" + dept[idx]);
				}
			}
		});
		tfId.setBounds(335, 35, 116, 21);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		tfName = new JTextField();
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
		
		JButton btnAdd = new JButton("학생추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isOk()) {
					InsertStudentInfomation();	
				}else {
					JOptionPane.showMessageDialog(null, "필수 입력을 꼭 확인하세요");
				}
			
			}
		});
		btnAdd.setFont(new Font("굴림", Font.BOLD, 12));
		btnAdd.setBounds(473, 210, 97, 42);
		getContentPane().add(btnAdd);
		
		JLabel lblAddress = new JLabel("주소 :");
		lblAddress.setFont(new Font("굴림", Font.BOLD, 12));
		lblAddress.setBounds(129, 224, 57, 15);
		getContentPane().add(lblAddress);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(177, 221, 264, 21);
		getContentPane().add(tfAddress);
		tfAddress.setColumns(10);
		
		btnOverlapped = new JButton("중복체크");
		btnOverlapped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkId();
				
			}
		});
		btnOverlapped.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					tfName.requestFocus();
						
				}
				
			}
		});
		btnOverlapped.setFont(new Font("굴림", Font.BOLD, 12));
		btnOverlapped.setBounds(472, 34, 97, 23);
		getContentPane().add(btnOverlapped);
		
		lblDept = new JLabel("학과 : ");
		lblDept.setForeground(new Color(255, 0, 0));
		lblDept.setFont(new Font("굴림", Font.BOLD, 12));
		lblDept.setBounds(470, 71, 100, 15);
		getContentPane().add(lblDept);
		
		tfJumin2 = new JPasswordField();
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

	}
	protected void checkId() {
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
             
             
             if(rs.next()){//중복
            	 tfId.requestFocus();
            	 tfId.setSelectionStart(0);
            	 tfId.setSelectionEnd(tfId.getText().length());
            	 JOptionPane.showMessageDialog(getContentPane(), "아이디가 중복되었습니다. 다시 확인해 주세요", "message", JOptionPane.ERROR_MESSAGE);
            	 
             }else {
            	 tfName.requestFocus();
            	 System.out.println("회원가입이 가능한 아이디 입니다.");
            	 HashMap<String, String> dept = new HashMap<String, String>(); //2. HashMap 활용
					dept.put("01","국문학과");
					dept.put("02","영문학과");
					dept.put("10","컴퓨터학과");
					dept.put("11","전자공학과");
					dept.put("20","연극영화과");
					dept.put("21","음악과");
					dept.put("22","행정학과");
					
					String sId = temp.substring(4, 6);
					lblDept.setText("학과 :" + dept.get(sId));
					btnOverlapped.requestFocus();
            	 
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

	protected boolean isOk() { // 레코드를 insert하기 전에 필수 입력값 확인하는 함수
		if(tfId.getText().length()==9 && !tfName.getText().equals("") 
				&& tfPhone1.getText().length()>=3 && tfPhone2.getText().length()==4
				&& tfJumin1.getText().length()==6 && new String(tfJumin2.getPassword()).length()==7)
			return true;
		else
			return false;
	}	
	protected void InsertStudentInfomation() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
                           
            
            //=============================================
            String sql = "insert into studenttbl values(?,?,?,?,?,?,?)";
           
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, tfId.getText());
            pstmt.setString(2, tfName.getText());
            pstmt.setString(3, cbPhone.getSelectedItem() + tfPhone1.getText() + tfPhone2.getText());
            pstmt.setString(4, tfJumin1.getText() + new String(tfJumin2.getPassword()));
            pstmt.setString(5, tfEmail.getText() + "@" + cbEmail.getSelectedItem());
            pstmt.setString(6, fileName);
            pstmt.setString(7, tfAddress.getText());
            
            
            
            int result = pstmt.executeUpdate();
            if(result == 1) {
            	JOptionPane.showMessageDialog(getContentPane(), tfId.getText() + " " + tfName.getText() + " " + "삽입되셨습니다");
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
  	      	cbPhone.setSelectedIndex(0); // 010
  	      	cbEmail.setSelectedIndex(0); // 직접 입력
  	      	tfId.requestFocus();
            
			}catch (ClassNotFoundException e1) {
	            System.out.println("JDBC 드라이버 로드 에러");
	         } catch (SQLException e1) {
	            System.out.println("DB 연결 오류");
	         }
		
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
