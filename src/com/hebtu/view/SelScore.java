package com.hebtu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hebtu.model.Score;
import com.hebtu.model.User;
import com.hebtu.service.DatabaseService;
import com.hebtu.util.JDBCUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SelScore extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private User u;

	/**
	 * Create the frame.
	 */
	public SelScore(User u) {
		this.u = u;
		setTitle("\u67E5\u8BE2\u6210\u7EE9");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelScore.class.getResource("/img/java.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);// 居中
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 单关
		this.setVisible(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 64, 363, 171);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"\u59D3\u540D", "\u79D1\u76EE", "\u6210\u7EE9"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
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

		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u6210\u7EE9\u5355");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(146, 23, 120, 30);
		contentPane.add(lblNewLabel);

		this.tabledata();
	}

	private void tabledata() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		dtm.setRowCount(0);
		try {
			con = JDBCUtils.getConnection();
			if (u.getuType().equals("教师")) {
				ps = con.prepareStatement("select * from score");
			} else {
				ps = con.prepareStatement("select * from score where id=?");
				ps.setString(1, u.getuId());
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				Vector<Comparable> v = new Vector<Comparable>();
				v.add(rs.getString("name"));
				v.add(rs.getString("kemu"));
				v.add(rs.getInt("score"));
				dtm.addRow(v);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
