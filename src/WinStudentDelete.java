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
import java.util.Vector;

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

public class WinStudentDelete extends JDialog {
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfPhone1;
	private JTextField tfJumin1;
	private JTextField tfEmail;
	private JTextField tfPhone2;
	private JTextField tfAddress;
	private JLabel lblDept;
	private String fileName;
	private JComboBox cbPhone;
	private JComboBox cbEmail;
	private String temp;
	private JPasswordField tfJumin2;
	private JLabel lblPic;
	private JButton btnDel;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinStudentDelete dialog = new WinStudentDelete();
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
	 * @param type 
	 * @param sID 
	 */
	public WinStudentDelete() {
		getContentPane().setFont(new Font("굴림", Font.BOLD, 12));
		setTitle("\uD559\uC0DD \uC815\uBCF4 \uC0AD\uC81C\uCC3D");
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
		tfId.setEditable(false);
		tfId.setBounds(335, 35, 116, 21);
		getContentPane().add(tfId);
		tfId.setColumns(10);
		
		tfName = new JTextField();

		tfName.setColumns(10);
		tfName.setBounds(335, 68, 116, 21);
		getContentPane().add(tfName);
		
		tfPhone1 = new JTextField();

		tfPhone1.setColumns(10);
		tfPhone1.setBounds(401, 101, 73, 21);
		getContentPane().add(tfPhone1);
		tfJumin1 = new JTextField();

		tfJumin1.setColumns(10);
		tfJumin1.setBounds(335, 135, 95, 21);
		getContentPane().add(tfJumin1);
		
		tfEmail = new JTextField();

		tfEmail.setColumns(10);
		tfEmail.setBounds(335, 170, 95, 21);
		getContentPane().add(tfEmail);
		
		tfPhone2 = new JTextField();
		tfPhone2.setColumns(10);
		tfPhone2.setBounds(497, 101, 73, 21);
		getContentPane().add(tfPhone2);
		
		cbPhone = new JComboBox();

		cbPhone.setModel(new DefaultComboBoxModel(new String[] {"010", "02", "032", "031"}));
		cbPhone.setBounds(334, 100, 47, 23);
		getContentPane().add(cbPhone);
		
		JLabel lblNewLabel = new JLabel("@");
		lblNewLabel.setBounds(442, 173, 20, 15);
		getContentPane().add(lblNewLabel);
		
		cbEmail = new JComboBox();


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
		
		btnDel = new JButton();
		btnDel.setText("\uC0AD\uC81C");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ret = JOptionPane.showConfirmDialog(getContentPane(), "정말 삭제 하시겠습니까?", "경고", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				System.out.println(ret);
				if(ret == 0) {
					StudentDel();
				}else {
					
				}
				
			}
		});
		btnDel.setFont(new Font("굴림", Font.BOLD, 12));
		btnDel.setBounds(473, 210, 97, 42);
		getContentPane().add(btnDel);
		
		JLabel lblAddress = new JLabel("주소 :");
		lblAddress.setFont(new Font("굴림", Font.BOLD, 12));
		lblAddress.setBounds(129, 224, 57, 15);
		getContentPane().add(lblAddress);
		
		tfAddress = new JTextField();

		tfAddress.setBounds(177, 221, 264, 21);
		getContentPane().add(tfAddress);
		tfAddress.setColumns(10);
		
		lblDept = new JLabel("학과 : ");
		lblDept.setForeground(new Color(255, 0, 0));
		lblDept.setFont(new Font("굴림", Font.BOLD, 12));
		lblDept.setBounds(470, 71, 100, 15);
		getContentPane().add(lblDept);
		
		tfJumin2 = new JPasswordField();

		tfJumin2.setBounds(459, 135, 110, 21);
		getContentPane().add(tfJumin2);

	}
	protected void StudentDel() {
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sqldb";
		String username = "root";
		String password = "1234";
		
		String sql = "DELETE FROM studenttbl WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt;
		
		try{
			Class.forName(driverName);//드라이버 불러오기	
			conn = DriverManager.getConnection(url, username, password);//DB 연동
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tfId.getText());
			int result = pstmt.executeUpdate(); //삭제 성공하면 1 아니면 다른 값을 반환
			if(result == 1) {
				JOptionPane.showMessageDialog(getContentPane(), tfId.getText() + " " + tfName.getText() + " " + "삭제되었습니다");
			}else {
				System.out.println("삭제실패");
			}
			
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public WinStudentDelete(String sId) {
		this();
		showStudentInfomation(sId);
	}

	public WinStudentDelete(String sId, boolean type) {
		this(sId);
		if(type) {
			setTitle("학생 정보 상세창");
			btnDel.setVisible(false);
			
		}else {
			setTitle("학생 정보 삭제창");
		}
	}

	private void showStudentInfomation(String sId) {
		 try {
	         Class.forName("com.mysql.cj.jdbc.Driver");
	         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
	         //=============================================      
	         Statement stmt = con.createStatement();
	         String sql = "select * from studentTBL where id='" + sId + "'";
	         
	         
	         ResultSet rs = stmt.executeQuery(sql);
	         String record[] = new String[6];
	         if(rs.next()) {            
	            for(int i=0;i<record.length;i++) {
	               record[i] = rs.getString(i+2); // name부터 가져온다
	               //System.out.println(record[i]);
	            }
	            tfId.setText(sId); // 추가            
	            tfName.setText(record[0]);            
	            String sPhone = record[1];
	            if(sPhone.substring(0,2).equals("02")) {
	               cbEmail.setSelectedItem(sPhone.substring(0,2));
	               tfPhone1.setText(sPhone.substring(2,sPhone.length()-4));
	            }else {
	               cbEmail.setSelectedItem(sPhone.substring(0,3));
	               tfPhone1.setText(sPhone.substring(3,sPhone.length()-4));
	            }
	            tfPhone2.setText(record[1].substring(record[1].length()-4));               
	            tfJumin1.setText(record[2].substring(0,6)); // 주민번호 앞글자는 6글자
	            tfJumin2.setText(record[2].substring(6));  // 주민번호 뒷글자의 시작은 6번부터            
	            tfEmail.setText(record[3].substring(0, record[3].indexOf("@")));
	            cbEmail.setSelectedItem(record[3].substring(record[3].indexOf("@")+1));
	            tfAddress.setText(record[5]);            
	            fileName = record[4];
	            ImageIcon icon = new ImageIcon(fileName);
	            Image img = icon.getImage();
	            img = img.getScaledInstance(150, 180, Image.SCALE_SMOOTH);
	            ImageIcon image = new ImageIcon(img);
	            lblPic.setIcon(image);
	            
	         }
	         	HashMap<String, String> dept = new HashMap<String, String>(); //2. HashMap 활용
				dept.put("01","국문학과");
				dept.put("02","영문학과");
				dept.put("10","컴퓨터학과");
				dept.put("11","전자공학과");
				dept.put("20","연극영화과");
				dept.put("21","음악과");
				
        	  	temp = tfId.getText();
        	  	
        	  	
				String deptId = temp.substring(4, 6);
				lblDept.setText("학과 :" + dept.get(deptId));
	         
	         rs.close();
	         stmt.close();
	         con.close();
	         //==============================================
	      } catch (ClassNotFoundException e1) {
	         System.out.println("JDBC 드라이버 로드 에러");
	      } catch (SQLException e1) {
	         System.out.println("DB 연결 오류");
	      }      

		
	}

}
