package com.hebtu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.hebtu.dao.ConcreteDao;
import com.hebtu.dao.DatabaseDAO;
import com.hebtu.model.Score;
import com.hebtu.model.course;
import com.hebtu.util.JDBCUtils;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class UpdateScore extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel label;
	private JComboBox comboBox;


	/**
	 * Create the frame.
	 */
	public UpdateScore() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		this.setLocationRelativeTo(null);// 居中

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 单关
		this.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 86, 600, 331);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null } },
				new String[] { "\u59D3\u540D", "\u6210\u7EE9" }) {
			boolean[] columnEditables = new boolean[] { false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);

		table.getTableHeader().setReorderingAllowed(false);// 表头不可拖动
		table.getTableHeader().setResizingAllowed(false);// 列大小不可改变
		table.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		table.getTableHeader().setResizingAllowed(false); // 不可拉动表格

		JLabel lblNewLabel = new JLabel("\u6210\u7EE9\u5F55\u5165");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(274, 10, 120, 30);
		contentPane.add(lblNewLabel);

		label = new JLabel("\u8BFE\u7A0B\u540D\u79F0:");
		label.setBounds(40, 48, 64, 15);
		contentPane.add(label);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabledata(comboBox.getSelectedItem().toString().trim());
			}
		});
		comboBox.setBounds(114, 45, 113, 21);
		contentPane.add(comboBox);
		
//		System.out.println(comboBox.getSelectedItem().toString());
		
		JButton btnNewButton = new JButton("\u4FDD\u5B58");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < table.getRowCount(); i++) {
					String kemu = comboBox.getSelectedItem().toString();
					String name = table.getValueAt(i, 0).toString();
					int score = Integer.parseInt((String) table.getValueAt(i, 1));
					update(name, kemu, score);
				}
			}
		});
		btnNewButton.setBounds(459, 44, 93, 23);
		contentPane.add(btnNewButton);
		
		
		this.findCname();
//		this.tabledata();

	}

	private int update(String name, String kemu, int score) {
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		int flag = 1;
		try {
			con = JDBCUtils.getConnection();
			ps = con.prepareStatement("update score set score=? where name=? and kemu=?");
			ps.setString(3, kemu);
			ps.setInt(1, score);
			ps.setString(2, name);
//			System.out.println(ps);
			flag = ps.executeUpdate();
//			System.out.println(flag);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return flag;
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

	private void tabledata(String kumu) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		dtm.setRowCount(0);
		try {
			con = JDBCUtils.getConnection();
			ps = con.prepareStatement("select * from score where kemu=?");
			ps.setString(1, kumu);
			System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector<Comparable> v = new Vector<Comparable>();
				v.add(rs.getString("name"));
				v.add(rs.getString("score"));
				dtm.addRow(v);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
