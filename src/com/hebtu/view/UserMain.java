package com.hebtu.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.hebtu.model.*;
import com.hebtu.service.DatabaseService;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class UserMain extends JFrame implements WindowListener {

	private JPanel contentPane;
	private String str;
	private JTextField nameT;
	private JTextField ageT;
	private JTextField findT;
	private JTextField sexT;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JTextField telT;
	private JLabel label_4;
	private JTextField idT;
	private User user;
	private JButton btnSelCou;
	private JButton btnSearchGrade;
	private JButton btnConCourse;
	private JButton btnConGrade;
	private JButton button;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private int curIndex = 0;
	private JLabel photo;
	private ArrayList<User> arrUser;
	private JComboBox comBoxType;

	/**
	 * Create the frame.
	 */
	public UserMain(User user) {

		this.user = user;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出后关闭
//		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 单关
		this.setVisible(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("退出了");
				System.exit(0);
			}
		});

		label = new JLabel("\u59D3\u540D");
		label.setBounds(52, 95, 54, 15);
		contentPane.add(label);

		label_1 = new JLabel("\u5E74\u9F84");
		label_1.setBounds(52, 131, 54, 15);
		contentPane.add(label_1);

		label_2 = new JLabel("\u6027\u522B");
		label_2.setBounds(52, 170, 54, 15);
		contentPane.add(label_2);

		label_3 = new JLabel("\u7535\u8BDD");
		label_3.setBounds(52, 206, 54, 15);
		contentPane.add(label_3);

		photo = new JLabel("");
		photo.setBounds(307, 59, 200, 200);
		contentPane.add(photo);

		nameT = new JTextField();
		nameT.setBounds(112, 92, 121, 21);
		contentPane.add(nameT);
		nameT.setColumns(10);

		ageT = new JTextField();
		ageT.setBounds(112, 128, 121, 21);
		contentPane.add(ageT);
		ageT.setColumns(10);

		findT = new JTextField();
		findT.setBounds(361, 397, 104, 30);
		contentPane.add(findT);
		findT.setColumns(10);

		sexT = new JTextField();
		sexT.setColumns(10);
		sexT.setBounds(112, 167, 121, 21);
		contentPane.add(sexT);

		DatabaseService ss = new DatabaseService();
		User s = user;

		btnNewButton = new JButton("\u65B0\u589E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txtid = idT.getText();
				String id = "";

				DatabaseService ds = new DatabaseService();
				User user = new User();
				int flag = 1;
				Set set = ds.findAll(user);// 获取所有用户
				Iterator it = set.iterator();
				while (it.hasNext()) {
					user = (User) it.next();
					id = user.getuId();
					if (id.equals(txtid)) {
						flag = 0;
						break;
					}
				}

				if (flag == 1) {
					User user1 = new User();
					user1.setuId(idT.getText());
					user1.setuName(nameT.getText());
					user1.setuAge(ageT.getText());
					user1.setuSex(sexT.getText());
					user1.setuTel(sexT.getText());
					user1.setuPwd("123");// 默认密码
					user1.setuType("教师");// 添加老师
//					System.out.println(photo.getIcon().toString());
//					user.setImg(photo.getIcon().toString());
					if (ss.add(user1) > 0) {
//						System.out.println("添加成功");
						alert("添加成功");
					}
				} else {
					alert("添加失败");
				}

			}
		});
		btnNewButton.setBounds(97, 357, 96, 30);
		contentPane.add(btnNewButton);
		// 更新
		btnNewButton_1 = new JButton("\u4FEE\u6539");

		btnNewButton_1.setBounds(232, 356, 108, 30);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("\u5220\u9664");
		btnNewButton_2.setBounds(361, 356, 104, 30);
		contentPane.add(btnNewButton_2);

//		通过名字查找  查找
		btnNewButton_3 = new JButton("\u67E5\u627E");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				find(new User(), findT.getText());
				if (findT.getText().equals("")) {

					alert("查询名字不能为空");
					return;
				}
				User u1 = (User) ss.find(new User(), findT.getText());
//			System.out.println(u1==null);
				if (u1 == null) {

					alert("查询无结果");
					return;
				}
				UserMain.this.set(u1);
			}
		});
		btnNewButton_3.setBounds(495, 396, 102, 30);
		contentPane.add(btnNewButton_3);

		btnNewButton_4 = new JButton("\u4E0A\u4E00\u4E2A");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				curIndex--;
				if (curIndex < 0) {
					curIndex = arrUser.size() - 1;
				}
				curIndex %= arrUser.size();
				UserMain.this.user = UserMain.this.getRandomU(curIndex);
				UserMain.this.set(UserMain.this.user);
			}
		});
		btnNewButton_4.setBounds(97, 397, 96, 30);
		contentPane.add(btnNewButton_4);

		btnNewButton_5 = new JButton("\u4E0B\u4E00\u4E2A");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				curIndex++;
				curIndex %= arrUser.size();
				UserMain.this.user = UserMain.this.getRandomU(curIndex);
				UserMain.this.set(UserMain.this.user);
			}
		});
		btnNewButton_5.setBounds(234, 396, 106, 30);
		contentPane.add(btnNewButton_5);

		// 随机点名
		btnNewButton_6 = new JButton("\u968F\u673A\u70B9\u540D");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserMain.this.user = UserMain.this.getRandomU(UserMain.this.randomNum());
				UserMain.this.set(UserMain.this.user);
			}
		});
		btnNewButton_6.setBounds(495, 356, 102, 30);
		contentPane.add(btnNewButton_6);

		button = new JButton("\u4E0A\u4F20\u7167\u7247");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 上传图片
				UserMain.this.setImg(UserMain.this.user);
			}
		});
		button.setBounds(348, 26, 93, 30);
		contentPane.add(button);

		telT = new JTextField();
		telT.setColumns(10);
		telT.setBounds(112, 203, 121, 21);
		contentPane.add(telT);

		label_4 = new JLabel("\u5B66\u53F7");
		label_4.setBounds(52, 62, 54, 15);
		contentPane.add(label_4);

		idT = new JTextField();
		idT.setBounds(111, 59, 122, 21);
		contentPane.add(idT);
		idT.setColumns(10);

		btnSelCou = new JButton("\u9009\u8BFE");
		btnSelCou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Xuanke(user);
			}
		});
		btnSelCou.setBounds(98, 312,  95, 30);
		contentPane.add(btnSelCou);

		btnSearchGrade = new JButton("\u67E5\u770B\u6210\u7EE9");
		btnSearchGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SelScore(user);
			}
		});
		btnSearchGrade.setBounds(232, 311, 108, 30);
		contentPane.add(btnSearchGrade);

		btnConCourse = new JButton("\u8BFE\u7A0B\u7BA1\u7406");
		btnConCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UpdateCourse();
			}
		});
		btnConCourse.setBounds(361, 311, 104, 30);
		contentPane.add(btnConCourse);

		btnConGrade = new JButton("\u6210\u7EE9\u7BA1\u7406");
		btnConGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateScore();
			}
		});
		btnConGrade.setBounds(495, 311, 102, 30);
		contentPane.add(btnConGrade);

		comBoxType = new JComboBox();
		comBoxType.setModel(new DefaultComboBoxModel(new String[] { "\u6559\u5E08", "\u5B66\u751F" }));
		comBoxType.setBounds(112, 238, 121, 21);
		contentPane.add(comBoxType);
		comBoxType.setEditable(false);
		JLabel lblNewLabel = new JLabel("\u89D2\u8272");
		lblNewLabel.setBounds(52, 244, 54, 15);
		contentPane.add(lblNewLabel);

		// 修改
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.setuId(idT.getText());
				s.setuName(nameT.getText());
				s.setuAge(ageT.getText());
				s.setuSex(sexT.getText());
				s.setuTel(telT.getText());
//				System.out.println(photo.);
				s.setImg(photo.getIcon() + "");
				s.setuType(comBoxType.getSelectedItem().toString());
				if (ss.update(s) > 0) {
					alert("修改成功");
				}
			}
		});
		this.set(s);
		this.setPower();
		this.getAllUser();

	}

	// 设置权限
	public void setPower() {
		if (this.user.getuType().equals("教师")) {
			btnSelCou.setEnabled(false);
			this.getAllUser();

		} else if (this.user.getuType().equals("学生")) {
			btnConCourse.setEnabled(false);
			btnConGrade.setEnabled(false);
			btnNewButton.setEnabled(false);
			btnNewButton_1.setEnabled(false);
			btnNewButton_2.setEnabled(false);
			btnNewButton_3.setEnabled(false);
			btnNewButton_4.setEnabled(false);
			btnNewButton_5.setEnabled(false);
			btnNewButton_6.setEnabled(false);
			comBoxType.setEnabled(false);
		}
	}

	// 设置文本框 内容 动态
	public void set(User user) {
		ageT.setText(user.getuAge());
		nameT.setText(user.getuName());
		idT.setText(user.getuId());
		telT.setText(user.getuTel());
		sexT.setText(user.getuSex());
		photo.setIcon(new ImageIcon(user.getImg()));
		comBoxType.setSelectedItem(user.getuType());
	}

	public void getAllUser() {
//		System.out.println("获取所有学生");
		User u = new User();
		DatabaseService ds = new DatabaseService();
		arrUser = new ArrayList();
		Set set = ds.findAll(user);// 获取所有用户
		Iterator it = set.iterator();
		while (it.hasNext()) {
			arrUser.add((User) it.next());
		}
	}

	public int randomNum() {
		int i = (int) Math.floor(Math.random() * arrUser.size());
//		System.out.println("随机数" + i);
		return i;
	}

	public User getRandomU(int i) {
		return arrUser.get(i);
	}

	public void setImg(User s) {
		JFileChooser jfc = new JFileChooser();
		FileInputStream fis = null;
		FileOutputStream fos = null;
		jfc.showOpenDialog(null);
		File f = jfc.getSelectedFile();
		if (f == null) {
			System.out.println("未选择图片");
			return;
		}
		String temp = "src/img/" + f.getName();
		try {
			fis = new FileInputStream(f);
			fos = new FileOutputStream(temp);
			int m;
			while ((m = fis.read()) != -1) {
				fos.write(m);
			}
			s.setImg(temp);
			photo.setIcon(new ImageIcon(temp));
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void alert(String str) {
		JOptionPane pane = new JOptionPane(str);
		JDialog dialog = pane.createDialog("hello");
		dialog.show();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("hll");
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("之后");
	}

	// 接口 窗体关闭监听事件
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		setDefaultCloseOperation(Login.EXIT_ON_CLOSE);
		System.out.println("推出了");
//		Login.EXIT_ON_CLOSE;
		System.exit(0);

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}
