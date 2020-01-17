package fr.dreregon.updater;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.dreregon.Discord_EMS.system.Sys_Message;
import fr.dreregon.Discord_EMS.system.Sys_MsgType;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import java.awt.Toolkit;
import java.awt.Font;

/**
Copyright 2019 Dreregon

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */



//COMMENTE TON CODE FDP
public class UpdateStatus {

	private static JFrame frmUpdateStatus;
	private static JProgressBar progressBar;
	private static JLabel readSize = new JLabel("Downloaded : 0 MB");
	private static JLabel totalSize = new JLabel("Total : 0 MB");
	private final JLabel lblProgress = new JLabel("Progress : 0%");
	private static float totalReceived;
	private static int i=0;
	private boolean speedCalcRunning = false;

	/**
	 * Launch the application.
	 */
	public static void main(String args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					UpdateStatus window = new UpdateStatus(args);
					window.frmUpdateStatus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateStatus(String newVer) {
		initialize(newVer);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String newVer) {

		if(Sys_Start.windows) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		}else {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		}



		frmUpdateStatus = new JFrame();
		frmUpdateStatus.setAlwaysOnTop(true);
		frmUpdateStatus.setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateStatus.class.getResource("/fr/dreregon/Discord_ES/system/media/Discord-EMS_32_Update.png")));
		frmUpdateStatus.setResizable(false);
		frmUpdateStatus.setTitle("Update Status");
		frmUpdateStatus.setBounds(100, 100, 450, 120);
		frmUpdateStatus.setLocationRelativeTo(null);
		frmUpdateStatus.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmUpdateStatus.getContentPane().setLayout(null);
		frmUpdateStatus.getContentPane().setBackground(Sys_Util.discorddark);
		if(Sys_Start.windows) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		}

		progressBar = new JProgressBar();
		progressBar.setString("0kb/s");
		progressBar.setStringPainted(true);
		progressBar.setBackground(Sys_Util.notblack);
		progressBar.setForeground(Sys_Util.discordGreen);
		progressBar.setBorderPainted(false);

		progressBar.setBounds(10, 54, 424, 26);
		frmUpdateStatus.getContentPane().add(progressBar);

		if(Sys_Start.windows) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		}

		JLabel lblDownloading = new JLabel("Downloading update...");
		lblDownloading.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDownloading.setBounds(10, 11, 145, 14);
		frmUpdateStatus.getContentPane().add(lblDownloading);
		readSize.setFont(new Font("Tahoma", Font.PLAIN, 11));
		readSize.setHorizontalAlignment(SwingConstants.RIGHT);
		readSize.setBounds(289, 11, 145, 14);
		lblDownloading.setForeground(Color.white);
		readSize.setForeground(Color.white);

		frmUpdateStatus.getContentPane().add(readSize);
		totalSize.setFont(new Font("Tahoma", Font.PLAIN, 11));

		totalSize.setForeground(Color.WHITE);
		totalSize.setHorizontalAlignment(SwingConstants.RIGHT);
		totalSize.setBounds(299, 29, 135, 14);
		frmUpdateStatus.getContentPane().add(totalSize);
		lblProgress.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblProgress.setForeground(Color.WHITE);
		lblProgress.setBounds(10, 29, 145, 14);

		frmUpdateStatus.getContentPane().add(lblProgress);



		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					DecimalFormat df = new DecimalFormat(".##");
					URL executable = new URL("https://raw.githubusercontent.com/Dreregon/Discord-EMS/master/DEMS_Installer-"+newVer+".exe");
					HttpURLConnection connection = (HttpURLConnection) executable.openConnection();

					int fileSize = connection.getContentLength();
					if(fileSize < 0) {
						//TODO throw error
						return;
					}
					double mb = (fileSize/1E6);
					progressBar.setMaximum(fileSize);
					totalSize.setText("Total : "+df.format(mb)+" MB");

					totalReceived = 0;

					BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
					FileOutputStream fos = new FileOutputStream("DEMS_Installer-"+newVer+".exe");
					BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);

					byte[] data = new byte[1024];
					i=0;

					Runnable speed = new Runnable() {

						@Override
						public void run() {
							DecimalFormat df = new DecimalFormat(".##");
							while(speedCalcRunning) {
								float speed = 0;
								float first = totalReceived;
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								float second = totalReceived;
								speed = ((second-first)/100f);
								if(speed > 1000) {
									progressBar.setString(df.format(speed/1000f)+"MB/s");
								}else {
									progressBar.setString(df.format(speed)+"kB/s");
								}
								
							}
						}
					};
					speedCalcRunning = true;

					Thread speedCalc = new Thread(speed);
					speedCalc.start();

					while((i=in.read(data,0,1024))>=0)
					{
						totalReceived=totalReceived+i;
						double mb2 = (totalReceived/1E6);
						bout.write(data, 0, i);
						progressBar.setValue((int)totalReceived);
						readSize.setText("Downloaded : "+df.format(mb2)+" MB");
						float Percent=(totalReceived*100)/fileSize;
						lblProgress.setText("Progress : "+(int)Percent+"%");
					}


					speedCalcRunning = false;
					bout.close();
					in.close();
					Runnable r3 = new Runnable() {
						@Override
						public void run() {
							frmUpdateStatus.dispose();
							File installer = new File("DEMS_Installer-"+newVer+".exe");
							if(installer.exists()) {
								try {
									Runtime.getRuntime().exec("cmd /c DEMS_Installer-"+newVer+".exe -elevate");
									System.exit(0);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					};

					Sys_Message.main("Download finished !\nNow launching the installer !", Sys_MsgType.INFO, r3);

				} catch (Exception e) {
					Runnable r2 = new Runnable() {
						@Override
						public void run() {
							frmUpdateStatus.dispose();
						}
					};
					Sys_Util.printerr("<Updater> - Error while downloading the update : "+e.getMessage());
					Sys_Message.main("Error while downloading update !\n"+e.getMessage(), Sys_MsgType.ERROR, r2);
				}

			}
		};

		Thread d = new Thread(r);

		d.setName("Updater");
		d.start();
	}

	public static void update(String newVer) {



	}
}
