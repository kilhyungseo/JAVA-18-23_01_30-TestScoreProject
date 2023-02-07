import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinLookup extends JDialog {
	private JTable table;
	private DefaultTableModel dtm;
	private boolean type;
	private JMenuItem mnuStudentDetail;
	private JMenuItem mnuStudentDelete;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinLookup dialog = new WinLookup("홍","1");
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
	public WinLookup() {		
		
		setBounds(100, 100, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		String header[] = {"학번","이름","학과","전화번호"};
		dtm = new DefaultTableModel(header, 0);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		mnuStudentDelete = new JMenuItem("삭제...");
		mnuStudentDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String sID = table.getValueAt(row, 0).toString();
				//System.out.println(sID);
				
				setVisible(false); // 자세한 정보를 표시하는 WinStudentDelete 창을 열고, 이 창은 숨긴다.
				
				WinStudentDelete winStudentDelete = new WinStudentDelete(sID, false);
				winStudentDelete.setModal(true);
				winStudentDelete.setVisible(true);
			}
		});
		popupMenu.add(mnuStudentDelete);
		
		mnuStudentDetail = new JMenuItem("상세보기...");
		mnuStudentDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String sID = table.getValueAt(row, 0).toString();
				//System.out.println(sID);
				
				setVisible(false); // 자세한 정보를 표시하는 WinStudentDelete 창을 열고, 이 창은 숨긴다.
								
				WinStudentDelete winStudentDelete = new WinStudentDelete(sID, true);
				winStudentDelete.setModal(true);
				winStudentDelete.setVisible(true);
			}
		});
		popupMenu.add(mnuStudentDetail);
		scrollPane.setViewportView(table);

	}

	public WinLookup(String sName) {
		// TODO Auto-generated constructor stub
		this();
		// DB연결 select where 이름 like
		showLookupName(sName,""); // 이름으로 검색하여 테이블에 표시한다.	
		setTitle("삭제할 명단 표시");
		type = false;			
		mnuStudentDelete.setEnabled(true);  // 그때 그때 달라요? 프로그래머 마음
		mnuStudentDetail.setEnabled(false); // 그때 그때 달라요? 프로그래머 마음
	}

	public WinLookup(String sName, String sPhone) {
		// TODO Auto-generated constructor stub
		this();
		setTitle("조회할 명단 표시");
		showLookupName(sName, sPhone);
		type = true;
		mnuStudentDelete.setVisible(false); // 그때 그때 달라요? 프로그래머 마음
		mnuStudentDetail.setEnabled(true);	// 그때 그때 달라요? 프로그래머 마음
		
		
	}

	private void showLookupName(String sName, String sPhone) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sqlDB", "root","1234");
			
			//=============================================	
			String sql="";
			PreparedStatement pstmt=null;
			if(sPhone.equals("") && !sName.equals("")) {
				sql = "select * from studentTBL where name like ?";			
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%" + sName + "%");
			}else if(!sPhone.equals("") && sName.equals("")) {
				sql = "select * from studentTBL where phone like ?";			
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%" + sPhone + "%");
			}else if(sPhone.equals("") && sName.equals("")) {
				sql = "select * from studentTBL";			
				pstmt=con.prepareStatement(sql);
			}
			else {
				sql = "select * from studentTBL where name like ? and phone like ?";			
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, "%" + sName  + "%");
				pstmt.setString(2, "%" + sPhone + "%");
			}
			ResultSet rs = pstmt.executeQuery();			
			while(rs.next()) {  // 각 레코드의 필드들을 읽어서 벡터에 저장한 후, DefaultTableModel 에 추가한다.
				Vector<String> vector = new Vector<>();
				vector.add(rs.getString("id"));
				vector.add(rs.getString("name"));
				
				String sql2 = "select deptName from deptTBL where deptId=?";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, rs.getString("id").substring(4,6));
				
				
				ResultSet rs2 = pstmt2.executeQuery();
				if(rs2.next())
					vector.add(rs2.getString("deptName"));
				rs2.close();
				pstmt2.close();
				
				vector.add(rs.getString("phone"));
				dtm.addRow(vector);
			}
			rs.close();		
			pstmt.close();
			con.close();
			//==============================================
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e1) {
			System.out.println("DB 연결 오류");
		}				
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					JTable source = (JTable)e.getSource();
					int row = source.rowAtPoint(e.getPoint());
					int col = source.columnAtPoint(e.getPoint());
					if(!source.isRowSelected(row))
						source.changeSelection(row, col, false,false);	
					
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
