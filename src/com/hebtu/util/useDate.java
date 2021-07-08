package com.hebtu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class useDate {

	public void interval(JLabel lbl) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			public void run() {
//				if (time == 0) {
//					System.out.println("时间到了，该交卷了");
//				}
//				time--;
				Date date=new Date();
				SimpleDateFormat ft =  new SimpleDateFormat ("yyyy.MM.dd '--' hh:mm:ss");
				lbl.setText(ft.format(date));
			}
		}, 0, 1000);
	}
}
