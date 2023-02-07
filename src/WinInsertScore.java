import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinInsertScore extends JDialog {
	private JTextField tfKor;
	private JTextField tfHistory;
	private JTextField tfTotal;
	private JTextField tfMath;
	private JTextField tfSocial;
	private JTextField tfAvg;
	private JTextField tfEng;
	private JTextField tfScience;
	private JScrollPane scrollPane;
	private JTextField tfNum;
	private JTextField tfName;
	private Component JTextField;
	private int kor, math, eng, social, history, science, total;
	private double average;
	private JButton btnAdd;
	private JComboBox cbYear;
	private JComboBox cbMonth;
	private String regExp = "^[0-9]+$";
	private JTable table;
	private DefaultTableModel dtm;
	String driverName = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/sqldb";
	String username = "root";
	String password = "1234";

	private JButton btnDel;
	private JButton btnDelDb;
	private JButton btnModify;
	private int num;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinInsertScore dialog = new WinInsertScore();
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
	public WinInsertScore() {
		Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH) + 1;
		setTitle("\uC131\uC801\uD45C \uC785\uB825");
		setBounds(100, 100, 647, 588);
		getContentPane().setLayout(null);
		
		JLabel lblKor = new JLabel("\uAD6D\uC5B4 : ");
		lblKor.setBounds(22, 53, 57, 15);
		getContentPane().add(lblKor);
		
		JLabel lblHistory = new JLabel("\uD55C\uAD6D\uC0AC : ");
		lblHistory.setBounds(22, 97, 57, 15);
		getContentPane().add(lblHistory);
		
		JLabel lblTotal = new JLabel("\uCD1D\uC810 :");
		lblTotal.setBounds(22, 141, 57, 15);
		getContentPane().add(lblTotal);
		
		JLabel lblMath = new JLabel("\uC218\uD559 :");
		lblMath.setBounds(219, 53, 57, 15);
		getContentPane().add(lblMath);
		
		JLabel lblSocial = new JLabel("\uC0AC\uD68C\uD0D0\uAD6C :");
		lblSocial.setBounds(219, 97, 57, 15);
		getContentPane().add(lblSocial);
		
		JLabel lblAge = new JLabel("\uD3C9\uADE0 :");
		lblAge.setBounds(219, 141, 57, 15);
		getContentPane().add(lblAge);
		
		JLabel lblEng = new JLabel("\uC601\uC5B4 :");
		lblEng.setBounds(416, 59, 57, 15);
		getContentPane().add(lblEng);
		
		JLabel lblScient = new JLabel("\uACFC\uD559\uD0D0\uAD6C :");
		lblScient.setBounds(416, 103, 57, 15);
		getContentPane().add(lblScient);
		
		tfKor = new JTextField();
		tfKor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfKor.setSelectionStart(0);
				tfKor.setSelectionEnd(tfKor.getText().length());
			}
		});
		tfKor.setText("0");
		tfKor.setHorizontalAlignment(SwingConstants.RIGHT);
		tfKor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(tfKor.getText().matches(regExp)) {
					scoreCalc();
					tfMath.requestFocus();
					}else {
					tfKor.setSelectionStart(0);
					tfKor.setSelectionEnd(tfKor.getText().length());
					}
				}	
			}
		});
		tfKor.setBounds(91, 50, 116, 21);
		getContentPane().add(tfKor);
		tfKor.setColumns(10);
		
		tfHistory = new JTextField();
		tfHistory.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfHistory.setSelectionStart(0);
				tfHistory.setSelectionEnd(tfHistory.getText().length());
			}
		});
		tfHistory.setText("0");
		tfHistory.setHorizontalAlignment(SwingConstants.RIGHT);
		tfHistory.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(tfHistory.getText().matches(regExp)) {
					scoreCalc();
					tfSocial.requestFocus();
					}else {
					tfHistory.setSelectionStart(0);
					tfHistory.setSelectionEnd(tfHistory.getText().length());
					}
				}	
			}
		});
		tfHistory.setColumns(10);
		tfHistory.setBounds(91, 94, 116, 21);
		getContentPane().add(tfHistory);
		
		tfTotal = new JTextField();
		tfTotal.setEditable(false);
		tfTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotal.setForeground(Color.red);
		tfTotal.setColumns(10);
		tfTotal.setBounds(91, 138, 116, 21);
		getContentPane().add(tfTotal);
		
		tfMath = new JTextField();
		tfMath.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfMath.setSelectionStart(0);
				tfMath.setSelectionEnd(tfMath.getText().length());
			}
		});
		tfMath.setText("0");
		tfMath.setHorizontalAlignment(SwingConstants.RIGHT);
		tfMath.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(tfMath.getText().matches(regExp)) {
					scoreCalc();
					tfEng.requestFocus();
					}else {
					tfMath.setSelectionStart(0);
					tfMath.setSelectionEnd(tfMath.getText().length());
					}
				}	
			}
		});
		tfMath.setColumns(10);
		tfMath.setBounds(288, 53, 116, 21);
		getContentPane().add(tfMath);
		
		tfSocial = new JTextField();
		tfSocial.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfSocial.setSelectionStart(0);
				tfSocial.setSelectionEnd(tfSocial.getText().length());
			}
		});
		tfSocial.setText("0");
		tfSocial.setHorizontalAlignment(SwingConstants.RIGHT);
		tfSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(tfSocial.getText().matches(regExp)) {
					scoreCalc();
					tfScience.requestFocus();
					}else {
					tfSocial.setSelectionStart(0);
					tfSocial.setSelectionEnd(tfSocial.getText().length());
					}
				}	
			}
		});
		tfSocial.setColumns(10);
		tfSocial.setBounds(288, 97, 116, 21);
		getContentPane().add(tfSocial);
		
		tfAvg = new JTextField();
		tfAvg.setEditable(false);
		tfAvg.setHorizontalAlignment(SwingConstants.RIGHT);
		tfAvg.setColumns(10);
		tfAvg.setForeground(Color.red);
		tfAvg.setBounds(288, 138, 116, 21);
		getContentPane().add(tfAvg);
		
		tfEng = new JTextField();
		tfEng.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfEng.setSelectionStart(0);
				tfEng.setSelectionEnd(tfEng.getText().length());
			}
		});
		tfEng.setText("0");
		tfEng.setHorizontalAlignment(SwingConstants.RIGHT);
		tfEng.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(tfEng.getText().matches(regExp)) {
					scoreCalc();
					tfHistory.requestFocus();
					}else {
					tfEng.setSelectionStart(0);
					tfEng.setSelectionEnd(tfEng.getText().length());
					}
				}	
			}
		});
		tfEng.setColumns(10);
		tfEng.setBounds(485, 53, 116, 21);
		getContentPane().add(tfEng);
		
		tfScience = new JTextField();
		tfScience.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfScience.setSelectionStart(0);
				tfScience.setSelectionEnd(tfScience.getText().length());
			}
		});
		tfScience.setText("0");
		tfScience.setHorizontalAlignment(SwingConstants.RIGHT);
		tfScience.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(tfScience.getText().matches(regExp)) {
					tfTotal.setEnabled(true);
					tfAvg.setEnabled(true);
					scoreCalc();
					btnAdd.requestFocus();
					}else {
					tfScience.setSelectionStart(0);
					tfScience.setSelectionEnd(tfScience.getText().length());
					}
				}
			}
		});
		tfScience.setColumns(10);
		tfScience.setBounds(485, 97, 116, 21);
		getContentPane().add(tfScience);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 203, 607, 281);
		getContentPane().add(scrollPane);
		
		String[] header = {"학번", "이름", "국어", "수학", "영어", "한국사", "사회탐구", "과학탐구", "총점", "평균", "연도", "학기"};
		dtm = new DefaultTableModel(header, 0);
		
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			int row = table.getSelectedRow();
			tfNum.setText(table.getValueAt(row, 0).toString());
			tfName.setText(table.getValueAt(row, 1).toString());
			tfKor.setText(table.getValueAt(row, 2).toString());
			tfMath.setText(table.getValueAt(row, 3).toString());
			tfEng.setText(table.getValueAt(row, 4).toString());
			tfHistory.setText(table.getValueAt(row, 5).toString());
			tfSocial.setText(table.getValueAt(row, 6).toString());
			tfScience.setText(table.getValueAt(row, 7).toString());
			tfTotal.setText(table.getValueAt(row, 8).toString());
			tfAvg.setText(table.getValueAt(row, 9).toString());
			cbYear.setSelectedItem(table.getValueAt(row, 10).toString());
			cbMonth.setSelectedItem(table.getValueAt(row, 11).toString());
			
			
		
			}
		});
		scrollPane.setViewportView(table);
		
		btnAdd = new JButton("\uCD94\uAC00");
		btnAdd.setForeground(new Color(0, 0, 255));
		btnAdd.setFont(new Font("굴림", Font.BOLD, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InsertRecord();
				//db저장  jtable 저장, 초기화
				AddRecord();
				
				ClearTextFields();
			}
		});
		btnAdd.setBounds(416, 137, 97, 23);
		getContentPane().add(btnAdd);
		
		JLabel lblNum = new JLabel("\uD559\uBC88 :");
		lblNum.setBounds(219, 17, 57, 15);
		getContentPane().add(lblNum);
		
		tfNum = new JTextField();
		tfNum.setEditable(false);
		tfNum.setFont(new Font("굴림", Font.BOLD, 12));
		tfNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfNum.setSelectionStart(0);
				tfNum.setSelectionEnd(tfNum.getText().length());
			}
		});
		tfNum.setHorizontalAlignment(SwingConstants.RIGHT);
		tfNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfName.requestFocus();
				}
			}
		});
		tfNum.setText("");
		tfNum.setBounds(288, 14, 116, 21);
		getContentPane().add(tfNum);
		tfNum.setColumns(10);
		
		
		JLabel lblName = new JLabel("\uC774\uB984 : ");
		lblName.setBounds(416, 17, 57, 15);
		getContentPane().add(lblName);
		
		tfName = new JTextField();
		tfName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfName.setSelectionStart(0);
				tfName.setSelectionEnd(tfName.getText().length());
			}
		});
		tfName.setHorizontalAlignment(SwingConstants.RIGHT);
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					tfKor.requestFocus();
					
				}
			}
		});
		tfName.setText("");
		tfName.setColumns(10);
		tfName.setBounds(485, 14, 116, 21);
		getContentPane().add(tfName);
		
		cbYear = new JComboBox();
		cbYear.setBounds(12, 6, 57, 23);
		getContentPane().add(cbYear);
		for(int y=2000; y<=2100;y++)
	       cbYear.addItem(y);
	      cbYear.setSelectedItem(year);
		
		cbMonth = new JComboBox();
		cbMonth.setModel(new DefaultComboBoxModel(new String[] {"1\uD559\uAE30 \uC911\uAC04\uACE0\uC0AC", "1\uD559\uAE30 \uAE30\uB9D0\uACE0\uC0AC", "\uC5EC\uB984\uD559\uAE30", "2\uD559\uAE30 \uC911\uAC04\uACE0\uC0AC", "2\uD559\uAE30 \uAE30\uB9D0\uACE0\uC0AC", "\uACA8\uC6B8\uD559\uAE30"}));
		cbMonth.setBounds(81, 6, 116, 23);
		getContentPane().add(cbMonth);
		
		JButton btnSelect = new JButton("\uC870\uD68C");
		btnSelect.setForeground(new Color(128, 0, 255));
		btnSelect.setFont(new Font("굴림", Font.BOLD, 12));
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbSelect();
			}
		});
		btnSelect.setBounds(522, 137, 97, 23);
		getContentPane().add(btnSelect);
		
		btnDel = new JButton("삭제");
		btnDel.setForeground(new Color(255, 0, 0));
		btnDel.setFont(new Font("굴림", Font.BOLD, 13));
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					dtm.removeRow(table.getSelectedRow());
					
					System.out.println("삭제 성공");
					}else {
						JOptionPane.showMessageDialog(table, "삭제할 레코드를 선택하시오");
					}
				
					
			}
		});
		btnDel.setBounds(416, 170, 97, 23);
		getContentPane().add(btnDel);
		
		btnDelDb = new JButton("DB\uC0AD\uC81C");
		btnDelDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index != -1) {
					num = Integer.parseInt(table.getValueAt(index, 0).toString());
					dtm.removeRow(index);
					delDbOk(num);
					System.out.println("삭제 성공");
					}else {
						JOptionPane.showMessageDialog(table, "삭제할 레코드를 선택하시오");
					}
					ClearTextFields();
			}
		});
		btnDelDb.setForeground(Color.RED);
		btnDelDb.setFont(new Font("굴림", Font.BOLD, 13));
		btnDelDb.setBounds(522, 170, 97, 23);
		getContentPane().add(btnDelDb);
		
		btnModify = new JButton("\uC815\uBCF4\uC218\uC815");
		btnModify.setFont(new Font("굴림", Font.BOLD, 12));
		btnModify.setForeground(new Color(255, 128, 64));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateRecord();
				dbSelect();
			}
		});
		btnModify.setBounds(307, 172, 97, 23);
		getContentPane().add(btnModify);


	}

	protected void UpdateRecord() {
		int num = Integer.parseInt(tfNum.getText()); 
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
                           
            
            //=============================================
            String sql = "UPDATE scoretbl SET name=?, kor=?, math=?, eng=?, history=?, social=?, science=? WHERE num=?";
           
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, tfName.getText());
            pstmt.setInt(2, Integer.parseInt(tfKor.getText()));
            pstmt.setInt(3, Integer.parseInt(tfMath.getText()));
            pstmt.setInt(4, Integer.parseInt(tfEng.getText()));
            pstmt.setInt(5, Integer.parseInt(tfHistory.getText()));
            pstmt.setInt(6, Integer.parseInt(tfSocial.getText()));
            pstmt.setInt(7, Integer.parseInt(tfScience.getText()));
            pstmt.setInt(8, num);

  
            int result = pstmt.executeUpdate();
            if(result == 1) {
            	System.out.println("수정 성공");
            }else {
            	System.out.println("수정 실패");
            }
            
            pstmt.close();
            conn.close();
			}catch (ClassNotFoundException e1) {
	            System.out.println("JDBC 드라이버 로드 에러");
	         } catch (SQLException e1) {
	            System.out.println("DB 연결 오류");
	         }
		
	}

	protected void delDbOk(int num) {
		

		String sql = "DELETE FROM scoretbl WHERE num = ?";
		
		Connection conn = null;
		PreparedStatement pstmt;
		
		try{
			Class.forName(driverName);//드라이버 불러오기	
			conn = DriverManager.getConnection(url, username, password);//DB 연동
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int result = pstmt.executeUpdate(); //삭제 성공하면 1 아니면 다른 값을 반환
			if(result == 1) {
				System.out.println("삭제성공");
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

	protected void dbSelect() {
		dtm.setRowCount(0);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM scoretbl";
		
		
		try {
	         Class.forName(driverName);//드라이버 불러오기
	         conn = DriverManager.getConnection(url, username, password);//DB 연동
	         
				pstmt = conn.prepareStatement(sql);
				
		 rs = pstmt.executeQuery(); //ResultSet 객체인 rs로 받기
		 
		 
		 
		 while(rs.next()) { // 각 레코드의 필드들을 읽어서 벡터에 저장한후. DefaultTableModel에 추가.
			 Vector<String> vector = new Vector<>();
			 vector.add(rs.getString("num"));
			 for(int i=4; i<=12; i++) 
				 vector.add(rs.getString(i));
				 vector.add(rs.getString(2));
				 vector.add(rs.getString(3));
				 dtm.addRow(vector);
			
		 }
		 	if(rs != null) {
		 		System.out.println("조회성공");
		 	}else {
		 		System.out.println("조회실패");
		 	}

	      } catch(Exception e) {
	         e.printStackTrace();
	      }	finally {
	    	  try {
	    		  if(rs != null) {
	    			  rs.close();
	    		  }
	    		  if(pstmt != null) {
	    			  pstmt.close();
	    		  }
	    		  if(conn != null) {
	    			  conn.close();
	    		  }
	    	  }catch(Exception e2) {
	    		  e2.printStackTrace();
	    	  }
	      }
				
	}

	protected void AddRecord() {
		Vector<String> vector = new Vector<String>();
		vector.add(tfNum.getText());
		vector.add(tfName.getText());
		vector.add(Integer.toString(kor));
		vector.add(Integer.toString(math));
		vector.add(Integer.toString(eng));
		vector.add(Integer.toString(history));
		vector.add(Integer.toString(social));
		vector.add(Integer.toString(science));
		vector.add(Integer.toString(total));
		vector.add(Double.toString(average));
		vector.add(cbYear.getSelectedItem().toString());
		vector.add(cbMonth.getSelectedItem().toString());
		
		dtm.addRow(vector);
	}

	protected void InsertRecord() {
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
                           
            
            //=============================================
            String sql = "insert into scoretbl values(?,?,?,?,?,?,?,?,?,?,?,?)";
           
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, Integer.parseInt(tfNum.getText()));
            pstmt.setInt(2, (int)cbYear.getSelectedItem());
            pstmt.setString(3, cbMonth.getSelectedItem().toString());
            pstmt.setString(4, tfName.getText());
            pstmt.setInt(5, kor);
            pstmt.setInt(6, math);
            pstmt.setInt(7, eng);
            pstmt.setInt(8, history);
            pstmt.setInt(9, social);
            pstmt.setInt(10, science);
            pstmt.setInt(11, total);
            pstmt.setString(12, String.format("%.2f" , average));
            
            int result = pstmt.executeUpdate();
            if(result == 1) {
            	System.out.println("입력 성공");
            }else {
            	System.out.println("입력 실패");
            }
            
            pstmt.close();
            conn.close();
			}catch (ClassNotFoundException e1) {
	            System.out.println("JDBC 드라이버 로드 에러");
	         } catch (SQLException e1) {
	            System.out.println("DB 연결 오류");
	         }
		
	}
	
	protected void ClearTextFields() {
		//텍스트 필드만 공백으로 변경하기
		Component [] componentList = getContentPane().getComponents();
	      for(Component c : componentList) {
			if(c instanceof JTextField)
	        	 ((JTextField) c).setText("");
	      }
		//String[] tf = {"tfKor", "tfHistory", "tfTotal", "tfMath", "tfSocial","tfAvg tfEng tfScience
		tfNum.requestFocus();
		
	}

	protected void scoreCalc() {
		total = 0;
		total = total + kor;
		total = total + math;
		total = total + eng;
		total = total + history;
		total = total + social;
		total = total + science;
		
		kor = Integer.parseInt(tfKor.getText());
		history = Integer.parseInt(tfHistory.getText());
		math = Integer.parseInt(tfMath.getText());
		social = Integer.parseInt(tfSocial.getText());
		eng = Integer.parseInt(tfEng.getText());
		science = Integer.parseInt(tfScience.getText());
		
		average = Math.round((double)total / 6 * 100)/100.;
		tfTotal.setText(Integer.toString(total));
		tfAvg.setText(String.format("%.2f" , average));
		
		
	}
}
