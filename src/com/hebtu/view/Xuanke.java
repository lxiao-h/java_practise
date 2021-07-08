package com.hebtu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.hebtu.dao.ConcreteDao;
import com.hebtu.model.Score;
import com.hebtu.model.User;
import com.hebtu.util.JDBCUtils;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Xuanke extends JFrame {

	private JPanel contentPane;
	private String str;
	private JTextField textField;
	private JComboBox comboBox;
	private User u;
	/**
	 * Create the frame.
	 */
	public Xuanke(User u) {
		this.u = u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);// 居中

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 单关
		this.setVisible(true);

		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u9009\u8BFE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(146, 23, 120, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u53EF\u9009\u8BFE\u7A0B:");
		lblNewLabel_1.setBounds(29, 64, 54, 15);
		contentPane.add(lblNewLabel_1);

		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				findJieshao(comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setBounds(93, 61, 104, 21);
		contentPane.add(comboBox);

		JLabel label = new JLabel("\u8BFE\u7A0B\u4ECB\u7ECD:");
		label.setBounds(29, 94, 54, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(29, 119, 336, 91);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);

		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConcreteDao d=new ConcreteDao();
				Score s=new Score();
				s.setId(u.getuId());
				s.setName(u.getuName());
				s.setKemu(comboBox.getSelectedItem().toString());
				s.setScore(0);
				
				if(d.add(s)>0) {		
					JOptionPane pane = new JOptionPane("选课成功");
					JDialog dialog = pane.createDialog("提示");
					dialog.show();
					Xuanke.this.setVisible(false);
					
				}
			}
		});
		btnNewButton.setBounds(160, 220, 93, 23);
		contentPane.add(btnNewButton);

		this.findCname();
	}

	private void findCname() {
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con = JDBCUtils.getConnection();
			Statement stat = con.createStatement();
			rs = stat.executeQuery("select * from course");
			while (rs.next()) {
				comboBox.addItem(rs.getString("kcm"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void findJieshao(String s) {
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try {
			con = JDBCUtils.getConnection();
			ps = con.prepareStatement("select * from course where kcm=?");
			ps.setString(1, s);
			rs = ps.executeQuery();
			while (rs.next()) {
				textField.setText(rs.getString("jj"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
