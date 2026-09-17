package com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;

public class JFrameUI_1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameUI_1 frame = new JFrameUI_1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameUI_1() {
		setTitle("企業客戶專案報價與成本管理系統");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 720);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("企業客戶專案報價與成本管理系統");
		lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblTitle.setBounds(330, 10, 380, 35);
		contentPane.add(lblTitle);
		
		JPanel pnlBasic = new JPanel();
		pnlBasic.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u5C08\u6848\u57FA\u672C\u8CC7\u6599", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlBasic.setBounds(20, 55, 480, 250);
		contentPane.add(pnlBasic);
		pnlBasic.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("專案編號：");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		lblNewLabel.setBounds(25, 35, 100, 25);
		pnlBasic.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setText("1001");
		textField.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		textField.setBounds(125, 35, 120, 25);
		pnlBasic.add(textField);
		
	}
}
