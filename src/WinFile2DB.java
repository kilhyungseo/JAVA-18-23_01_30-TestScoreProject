import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class WinFile2DB extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTextArea tfBack;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WinFile2DB dialog = new WinFile2DB();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WinFile2DB() {
		setBounds(100, 100, 577, 441);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				tfBack = new JTextArea();
				tfBack.setForeground(new Color(255, 255, 255));
				tfBack.setBackground(new Color(128, 128, 128));
				
				
				
				scrollPane.setViewportView(tfBack);
				{
					JPopupMenu popupMenu = new JPopupMenu();
					addPopup(tfBack, popupMenu);
					{
						JMenuItem mntmNewMenuItem_3 = new JMenuItem("20");
						mntmNewMenuItem_3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								tfBack.setFont(new Font(tfBack.getFont().getFamily(), Font.BOLD, 20));
							}
						});
						
						popupMenu.add(mntmNewMenuItem_3);
					}
					{
						JMenuItem mntmNewMenuItem_4 = new JMenuItem("30");
						mntmNewMenuItem_4.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								tfBack.setFont(new Font(tfBack.getFont().getFamily(), Font.BOLD, 30));
							}
						});
						popupMenu.add(mntmNewMenuItem_4);
					}
					{
						JMenuItem mntmNewMenuItem_5 = new JMenuItem("40");
						mntmNewMenuItem_5.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								tfBack.setFont(new Font(tfBack.getFont().getFamily(), Font.BOLD, 40));
							}
						});
						popupMenu.add(mntmNewMenuItem_5);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			{
				JPopupMenu popupMenu = new JPopupMenu();
				popupMenu.setToolTipText("\uD3F0\uD2B8\uBCC0\uACBD");
				popupMenu.setLabel("\uD3F0\uD2B8\uBCC0\uACBD");
				addPopup(buttonPane, popupMenu);
				{
					JMenuItem mntmNewMenuItem_6 = new JMenuItem("MingLiU_HKSCS-ExtB");
					mntmNewMenuItem_6.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tfBack.setFont(new Font("MingLiU_HKSCS-ExtB", Font.BOLD, tfBack.getFont().getSize()));
						}
					});
					popupMenu.add(mntmNewMenuItem_6);
				}
				{
					JMenuItem mntmNewMenuItem_7 = new JMenuItem("Nirmala UI Semilight");
					mntmNewMenuItem_7.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tfBack.setFont(new Font("Nirmala UI Semilight", Font.BOLD, tfBack.getFont().getSize()));
						}
					});
					popupMenu.add(mntmNewMenuItem_7);
				}
				{
					JMenuItem mntmNewMenuItem_8 = new JMenuItem("Segoe UI Emoji");
					mntmNewMenuItem_8.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							tfBack.setFont(new Font("Segoe UI Emoji", Font.PLAIN, tfBack.getFont().getSize()));
						}
					});
					popupMenu.add(mntmNewMenuItem_8);
				}
			}
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Road");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				{
					JPopupMenu popupMenu = new JPopupMenu();
					addPopup(okButton, popupMenu);
					{
						JMenuItem mntmNewMenuItem = new JMenuItem("red");
						mntmNewMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								tfBack.setBackground(Color.red);
							}

						});
						popupMenu.add(mntmNewMenuItem);
					}
					{
						JMenuItem mntmNewMenuItem_1 = new JMenuItem("blue");
						mntmNewMenuItem_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								tfBack.setBackground(Color.blue);
							}
						});
						popupMenu.add(mntmNewMenuItem_1);
					}
					{
						JMenuItem mntmNewMenuItem_2 = new JMenuItem("black");
						mntmNewMenuItem_2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								tfBack.setBackground(Color.black);
							}
						});
						popupMenu.add(mntmNewMenuItem_2);
					}
				}
			}
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
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
