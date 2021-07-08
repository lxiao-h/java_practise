package com.hebtu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hebtu.model.User;
import com.hebtu.service.DatabaseService;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

public class register extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public register() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(register.class.getResource("/img/java.png")));
		setTitle("\u7528\u6237\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 单关
		this.setVisible(true);
		
		textField = new JTextField();
		textField.setBounds(310, 120, 120, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(310, 200, 120, 30);
		contentPane.add(passwordField);
		
		lblNewLabel = new JLabel("\u7528 \u6237 \u540D:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(206, 120, 94, 30);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("\u5BC6    \u7801:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(206, 200, 94, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u59D3    \u540D:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(206, 160, 94, 30);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(310, 160, 120, 30);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseService ds = new DatabaseService();
				User u=new User();
				u.setuType("学生");
				u.setuId(textField.getText());
				u.setuName(textField_1.getText());
				u.setuPwd(passwordField.getText());
				
				
				if (ds.add(u)==0) {
					JOptionPane pane = new JOptionPane("注册成功");
					JDialog dialog = pane.createDialog("提示");
					dialog.show();
					setVisible(false);
				}else {
					JOptionPane pane = new JOptionPane("注册失败");
					JDialog dialog = pane.createDialog("提示");
					dialog.show();
				}
				
			}
		});
		btnNewButton.setBounds(264, 275, 120, 30);
		contentPane.add(btnNewButton);
	}
}
