package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.dreregon.Discord_EMS.bot.Bot;
import fr.dreregon.Discord_EMS.system.Sys_Bot;
import fr.dreregon.Discord_EMS.system.Sys_Json;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui_BotListV2 extends JFrame {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JPanel panel;
	private static ArrayList<JLabel> lblList;
	private static ArrayList<Sys_Bot> botList = new ArrayList<>();
	private static int botNumber = 0;
	private static int selected = 0;
	public static boolean opened = false;
	private boolean launching = false;

	/**
	 * Launch the application.
	 */
	public static void main(int status) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				try {
					Gui_BotListV2 frame = new Gui_BotListV2(status);
					frame.setVisible(true);
					opened = true;
					Sys_Util.openedWindows.add("BotList");
					frame.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui_BotListV2(int status) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_BotListV2.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				opened = false;
				if(Sys_Util.openedWindows.size() == 1) {
					if(Sys_Util.openedWindows.get(0).equals("BotList") && !launching && status == 0) {
						System.exit(0);
					}
					launching = false;
				}
			}
		});
		setResizable(false);
		setTitle(BUNDLE.getString("Gui_BotListV2.this.title")); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Sys_Util.discorddark);
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		if(Sys_Start.windows) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}else {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBackground(Color.BLACK);

		panel = new JPanel();
		panel.setSize(new Dimension(0, 500));
		panel.setBounds(new Rectangle(0, 0, 0, 500));
		panel.setBackground(Sys_Util.notblack);
		scrollPane.setViewportView(panel);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 321, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1220, Short.MAX_VALUE)
				);
		panel.setLayout(gl_panel);
		panel.setPreferredSize(new Dimension(0, 40*botNumber+20));

		lblList = new ArrayList<>();

		File botFolder = new File("Discord-EMS/Bots/Bot");
		botNumber = botFolder.listFiles().length;

		File iconFolder = new File("Discord-EMS/Bots/Avatar");

		for(int i = 0; i<botNumber; i++) {
			File f = botFolder.listFiles()[i];
			Sys_Bot b = new Sys_Bot();
			b = Sys_Json.getBot(f.getAbsolutePath());
			botList.add(b);
			JLabel lbl = new JLabel(b.getName());
			lbl.setBounds(10, 40*i, 230, 40);

			File iconFile = new File(iconFolder+File.separator+b.getId()+".png");

			if(!iconFile.exists()) {
				URL url = null;
				try {
					url = new URL(b.getAvatarURL());
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				URLConnection connection = null;
				try {
					connection = url.openConnection();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				connection.setRequestProperty("User-Agent", url.toString());
				try {
					InputStream in = connection.getInputStream();
					Files.copy(in, Paths.get(iconFolder+File.separator+b.getId()+".png"));
					Sys_Util.println("<BotList> - Downloaded icon for bot \""+b.getName()+"\"");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			BufferedImage bf = null;
			try {
				bf = ImageIO.read(iconFile);
			} catch (IOException e1) {
				e1.printStackTrace();
				try {
					bf = ImageIO.read(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord_logo32x.png"));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			Image im = bf.getScaledInstance(32, 32, Image.SCALE_SMOOTH);

			if(im != null) {
				lbl.setIcon(new ImageIcon(im));
			}else {
				lbl.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord_logo32x.png"))));
			}

			lbl.setForeground(Color.white);
			panel.add(lbl);
			lblList.add(lbl);
		}

		for(JLabel j : lblList) {
			j.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					selected = lblList.indexOf(j);
					for(JLabel j2 : lblList) {
						j2.setBorder(null);
						j2.setBackground(null);
						j2.setOpaque(false);
						j.setOpaque(true);
						j.setBackground(Sys_Util.blurple);
					}
				}
			});
		}

		JButton btnNewButton = new JButton(BUNDLE.getString("Gui_BotListV2.btnNewButton.text")); //$NON-NLS-1$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launching = true;
				JLabel jl = lblList.get(selected);
				Sys_Bot b = botList.get(lblList.indexOf(jl));
				String tk = b.getToken();
				if(status == 1) {
					Bot.stopBot();
				}
				Bot.startBot(tk, status);
				dispose();
			}
		});

		JButton btnRemove = new JButton(BUNDLE.getString("Gui_BotListV2.btnRemove.text")); //$NON-NLS-1$
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel jl = lblList.get(selected);
				Sys_Bot b = botList.get(selected);
				File f = new File("Discord-EMS/Bots/Bot/");
				File fi = f.listFiles()[botList.indexOf(b)];
				try {
					fi.delete();
					Sys_Util.println("<BotList> - Deleted bot "+b.getName());
				} catch (Exception e1) {
					e1.printStackTrace();
					Sys_Util.println("<BotList> - Error while deleting bot file "+fi.getName());
				}
				panel.remove(jl);
				lblList.remove(jl);
				botList.remove(b);
				for(JLabel j : lblList) {
					panel.remove(j);
					j.setBounds(10, 40*lblList.indexOf(j), 230, 40);
					panel.add(j);
				}
				panel.repaint();
			}
		});

		JButton btnAdd = new JButton(BUNDLE.getString("Gui_BotListV2.btnAdd.text")); //$NON-NLS-1$
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sys_Util.println("<Bot List> - Launching token window.");
				Gui_Token.main(status);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
						.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemove)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAdd)
					.addContainerGap(195, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

	}
}
