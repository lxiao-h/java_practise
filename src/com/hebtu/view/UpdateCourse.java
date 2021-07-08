package com.hebtu.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hebtu.dao.ConcreteDao;
import com.hebtu.dao.DatabaseDAO;
import com.hebtu.model.course;

public class UpdateCourse extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField xuefen;
	private JTextField jieshao;
	private JTextField chazhao;
	private String type;
	DatabaseDAO ss = new ConcreteDao();
	course c = new course();

	/**
	 * Create the frame.
	 */
	public UpdateCourse() {
		setTitle("\u8BFE\u7A0B\u7BA1\u7406");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateCourse.class.getResource("/img/java.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);// 居中

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 单关
		this.setVisible(true);
		
		name = new JTextField();
		name.setBounds(139, 46, 95, 21);
		contentPane.add(name);
		name.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u540D\u79F0:");
		lblNewLabel.setBounds(74, 49, 64, 15);
		contentPane.add(lblNewLabel);

		xuefen = new JTextField();
		xuefen.setColumns(10);
		xuefen.setBounds(290, 46, 81, 21);
		contentPane.add(xuefen);

		JLabel label = new JLabel("\u5B66\u5206:");
		label.setBounds(244, 49, 36, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u6027\u8D28:");
		label_1.setBounds(74, 82, 64, 15);
		contentPane.add(label_1);

		JRadioButton bixiu = new JRadioButton("\u5FC5\u4FEE");
		bixiu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bixiu.isSelected()) {
					type = bixiu.getText();
				}
			}
		});
		bixiu.setBounds(149, 78, 64, 23);
		contentPane.add(bixiu);

		JRadioButton xuanxiu = new JRadioButton("\u9009\u4FEE");
		xuanxiu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (xuanxiu.isSelected()) {
					type = xuanxiu.getText();
				}
			}
		});
		xuanxiu.setBounds(216, 78, 64, 23);
		contentPane.add(xuanxiu);

		ButtonGroup group = new ButtonGroup();
		group.add(bixiu);
		group.add(xuanxiu);

		JLabel label_2 = new JLabel("\u8BFE\u7A0B\u4ECB\u7ECD:");
		label_2.setBounds(74, 108, 64, 15);
		contentPane.add(label_2);

		jieshao = new JTextField();
		jieshao.setBounds(74, 131, 297, 80);
		contentPane.add(jieshao);
		jieshao.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u8BFE\u7A0B\u7BA1\u7406");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel_1.setBounds(158, 10, 110, 26);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("\u65B0\u589E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.getText().equals("")||xuefen.getText().equals("")||jieshao.getText().equals("")) {
					return ;
					
				}
				c.setKcm(name.getText());
				c.setXf(Integer.parseInt(xuefen.getText()));
				c.setJj(jieshao.getText());
				c.setXz(type);
				if (ss.add(c)==1) {
					JOptionPane pane = new JOptionPane("新增成功");
					JDialog dialog = pane.createDialog("提示");
					dialog.show();
				} else {
					JOptionPane pane = new JOptionPane("新增失败");
					JDialog dialog = pane.createDialog("提示");
					dialog.show();
				}
			}
		});
		btnNewButton.setBounds(21, 228, 93, 23);
		contentPane.add(btnNewButton);

		JButton button = new JButton("\u5220\u9664");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.setKcm(name.getText());
				if (ss.delete(c) == 1) {
					JOptionPane pane = new JOptionPane("删除成功");
					JDialog dialog = pane.createDialog("提示");
					dialog.show();
				} else {
					JOptionPane pane = new JOptionPane("删除失败");
					JDialog dialog = pane.createDialog("提示");
					dialog.show();
				}
			}
		});
		button.setBounds(124, 228, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("\u67E5\u627E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chazhao.getText().equals("")) {
					return;
				}
				course find = (course) ss.find(c, chazhao.getText());
				name.setText(find.getKcm());
				xuefen.setText(find.getXf()+"");
				jieshao.setText(find.getJj());
				if(find.getXz().equals("选修")) {
					xuanxiu.setSelected(true);
				}else {
					bixiu.setSelected(true);
				}
			}
		});
		button_1.setBounds(331, 228, 93, 23);
		contentPane.add(button_1);

		chazhao = new JTextField();
		chazhao.setColumns(10);
		chazhao.setBounds(227, 229, 95, 21);
		contentPane.add(chazhao);

	}
}
