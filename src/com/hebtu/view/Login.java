package com.hebtu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hebtu.service.DatabaseService;
import com.hebtu.util.JDBCUtils;
import com.hebtu.util.useDate;
import com.hebtu.model.User;

import java.awt.Toolkit;

public class Login extends JFrame {

	private JPanel contentPane;

	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JRadioButton teacher;
	private JRadioButton student;

	private static Login frame;

	private String userType = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
	public Login() {
		setResizable(false);
		setTitle("\u6559\u52A1\u7CFB\u7EDF\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/java.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		this.setLocationRelativeTo(null);// 居中
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(310, 150, 120, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(310, 200, 120, 30);
		contentPane.add(passwordField);

		lblNewLabel = new JLabel("\u7528 \u6237 \u540D:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(206, 148, 94, 30);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("\u5BC6    \u7801:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(206, 198, 94, 30);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("**\u6B22\u8FCE\u767B\u5F55\u6559\u52A1\u7CFB\u7EDF**");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(201, 79, 249, 30);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("\u7528\u6237\u7C7B\u522B:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(206, 238, 94, 30);
		contentPane.add(lblNewLabel_3);

		teacher = new JRadioButton("\u6559\u5E08");
		teacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (teacher.isSelected()) {
					userType = teacher.getText();
				}
			}
		});
		teacher.setBounds(310, 243, 61, 23);
		contentPane.add(teacher);

		student = new JRadioButton("\u5B66\u751F");
		student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (student.isSelected()) {
					userType = student.getText();
				}
			}
		});
		student.setBounds(369, 243, 61, 23);
		contentPane.add(student);

		ButtonGroup group = new ButtonGroup();
		group.add(teacher);
		group.add(student);

		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseService ds = new DatabaseService();
				User user = new User();
				int flag=0;
				Set set = ds.findAll(user);// 获取所有用户
				String id = "", pwd = "", type = "";
				String txtId = "", txtpwd = "", txtType = "";
				Iterator it = set.iterator();
				while (it.hasNext()) {
					user = (User) it.next();
					id = user.getuId();
					pwd = user.getuPwd();
					type = user.getuType();
					txtId = textField.getText();
					txtpwd = passwordField.getText();
					txtType = userType;
//					System.out.println(type+"--"+ id+"--"+pwd);
					if (id.equals(txtId) && pwd.equals(txtpwd) && type.equals(txtType) ) {
						flag=1;
						break;
					}
				}
				if(flag==1) {
					new UserMain(user);
					Login.this.setVisible(false);
				}else  {
					JOptionPane pane = new JOptionPane("用户或密码错误");
					JDialog dialog = pane.createDialog("警告");
					dialog.show();
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(206, 302, 94, 30);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u6CE8\u518C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new register();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.setBounds(336, 302, 94, 30);
		contentPane.add(btnNewButton_1);
		getRootPane().setDefaultButton(btnNewButton);
		
		JLabel lblTime = new JLabel("");
		lblTime.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(201, 39, 249, 30);
		contentPane.add(lblTime);
		
		new useDate().interval(lblTime);
	}
}
