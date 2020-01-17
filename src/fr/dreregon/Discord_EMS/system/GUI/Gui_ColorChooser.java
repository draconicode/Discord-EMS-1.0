package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeListener;

import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import javax.swing.event.ChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui_ColorChooser {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	public static JFrame frmDiscordemsColorChooser;
	private static JTextField textField_R;
	private static JTextField textField_G;
	private static JTextField textField_B;
	private static Color color = Color.red;
	private static JTextField hexField;
	public static boolean opened = false;

	/**
	 * Launch the application.
	 */
	public static void main(Color color2) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				color = color2;
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				opened = true;
				Sys_Util.openedWindows.add("ColorChooser");
				Gui_ColorChooser window = new Gui_ColorChooser();
				window.frmDiscordemsColorChooser.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_ColorChooser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	static JSlider slider_R;
	static JSlider slider_G;
	static JSlider slider_B;
	static JPanel panel;
	private JTextField textField_H;
	private JTextField textField_S;
	private JTextField textField_L;

	private void initialize() {

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

		frmDiscordemsColorChooser = new JFrame();
		frmDiscordemsColorChooser.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_ColorChooser.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmDiscordemsColorChooser.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				opened = false;
			}
		});
		frmDiscordemsColorChooser.setResizable(false);
		frmDiscordemsColorChooser.setTitle(BUNDLE.getString("Gui_ColorChooser.frmDiscordemsColorChooser.title")); //$NON-NLS-1$
		frmDiscordemsColorChooser.setBounds(100, 100, 668, 310);
		frmDiscordemsColorChooser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDiscordemsColorChooser.getContentPane().setLayout(null);
		frmDiscordemsColorChooser.getContentPane().setBackground(Sys_Util.discorddark);
		frmDiscordemsColorChooser.setLocationRelativeTo(null);

		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();

		JButton btnNewButton = new JButton(BUNDLE.getString("Gui_ColorChooser.btnNewButton.text")); //$NON-NLS-1$
		btnNewButton.setBounds(10, 247, 642, 23);
		frmDiscordemsColorChooser.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int[] colorCode = {slider_R.getValue(), slider_G.getValue(), slider_B.getValue()};
				color = new Color(colorCode[0],colorCode[1],colorCode[2]);
				Gui_Main.colorPick.setBackground(color);
				Sys_Util.println("<Color Chooser> - Returned color : "+color.toString());
				frmDiscordemsColorChooser.dispose();
			}
		});

		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE));
		panel.setBounds(10, 212, 642, 33);
		frmDiscordemsColorChooser.getContentPane().add(panel);
		panel.setBackground(color);

		JPanel customColors = new JPanel();
		customColors.setBorder(new LineBorder(Color.WHITE));
		customColors.setBounds(0, 0, 662, 173);
		frmDiscordemsColorChooser.getContentPane().add(customColors);
		customColors.setForeground(Color.RED);
		customColors.setBackground(Sys_Util.discorddark);
		customColors.setLayout(null);

		slider_R = new JSlider();
		slider_R.setBorder(new LineBorder(Color.RED));
		slider_R.setForeground(Color.BLACK);
		slider_R.setOpaque(false);
		slider_R.setBounds(10, 10, 200, 26);
		customColors.add(slider_R);
		slider_R.setBackground(Color.RED);
		slider_R.setMaximum(255);
		slider_R.setValue(255);
		slider_R.setValue(panel.getBackground().getRed());

		slider_G = new JSlider();
		slider_G.setBorder(new LineBorder(Color.GREEN));
		slider_G.setOpaque(false);
		slider_G.setBounds(10, 47, 200, 26);
		customColors.add(slider_G);
		slider_G.setBackground(Color.GREEN);
		slider_G.setMaximum(255);
		slider_G.setValue(0);
		slider_G.setValue(panel.getBackground().getGreen());

		slider_B = new JSlider();
		slider_B.setBorder(new LineBorder(Color.BLUE));
		slider_B.setOpaque(false);
		slider_B.setBounds(10, 84, 200, 26);
		customColors.add(slider_B);
		slider_B.setBackground(Color.BLUE);
		slider_B.setMaximum(255);
		slider_B.setValue(0);
		slider_B.setValue(panel.getBackground().getBlue());

		textField_R = new JTextField();
		textField_R.setBounds(220, 10, 30, 26);
		customColors.add(textField_R);
		textField_R.setHorizontalAlignment(SwingConstants.CENTER);
		textField_R.setBorder(new LineBorder(Color.RED));
		textField_R.setBackground(Sys_Util.notblack);
		textField_R.setForeground(Color.WHITE);
		textField_R.setColumns(10);
		textField_R.setCaretColor(Color.white);
		textField_R.setText(""+r);

		textField_G = new JTextField();
		textField_G.setBounds(220, 47, 30, 26);
		customColors.add(textField_G);
		textField_G.setHorizontalAlignment(SwingConstants.CENTER);
		textField_G.setBorder(new LineBorder(Color.GREEN));
		textField_G.setBackground(Sys_Util.notblack);
		textField_G.setForeground(Color.WHITE);
		textField_G.setColumns(10);
		textField_G.setCaretColor(Color.white);
		textField_G.setText(""+g);

		textField_B = new JTextField();
		textField_B.setBounds(220, 84, 30, 26);
		customColors.add(textField_B);
		textField_B.setHorizontalAlignment(SwingConstants.CENTER);
		textField_B.setBorder(new LineBorder(Color.BLUE));
		textField_B.setBackground(Sys_Util.notblack);
		textField_B.setForeground(Color.WHITE);
		textField_B.setColumns(10);
		textField_B.setCaretColor(Color.white);
		textField_B.setText(""+b);

		hexField = new JTextField();
		hexField.setBounds(10, 136, 240, 26);
		customColors.add(hexField);
		hexField.setHorizontalAlignment(SwingConstants.CENTER);
		hexField.setColumns(10);
		hexField.setCaretColor(Color.WHITE);
		hexField.setBackground(Sys_Util.notblack);
		hexField.setForeground(Color.WHITE);

		hexField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hexa = hexField.getText().substring(1);
				if(hexa.length()<6) {
					for(int i = hexa.length(); i<6; i++) {
						hexa = hexa+"0";
					}
					hexField.setText("#"+hexa);
				}else if(hexa.length()>6) {
					for(int i = hexa.length(); i!=5; i--) {
						hexa = hexa.substring(0, i);
					}
					hexField.setText("#"+hexa);
				}

				if(hexa.length()==6) {

					String hR = hexa.substring(0, 2);
					String hG = hexa.substring(2, 4);
					String hB = hexa.substring(4, 6);
					int valueR = 0;
					int valueG = 0;
					int valueB = 0;
					try {
						valueR = Integer.parseInt(hR,16);
						valueG = Integer.parseInt(hG,16);
						valueB = Integer.parseInt(hB,16);
					} catch (NumberFormatException e1) {


					}
					slider_R.setValue(valueR);
					slider_G.setValue(valueG);
					slider_B.setValue(valueB);
				}
			}
		});
		hexField.setText("#"+Integer.toHexString(panel.getBackground().getRGB()).substring(2));

		JSeparator separator = new JSeparator();
		separator.setBounds(258, 10, 9, 152);
		customColors.add(separator);
		separator.setBackground(Sys_Util.notblack);
		separator.setForeground(Sys_Util.notblack);
		separator.setOrientation(SwingConstants.VERTICAL);

		JLabel huePan = new JLabel();
		huePan.setBounds(270, 10, 265, 26);
		huePan.setOpaque(false);
		customColors.add(huePan);
		huePan.setIcon(new ImageIcon(Gui_ColorChooser.class.getResource("/fr/dreregon/Discord_EMS/system/media/hue.png")));

		JLabel satPan = new JLabel();
		satPan.setBounds(270, 73, 265, 26);
		satPan.setOpaque(false);
		customColors.add(satPan);
		satPan.setIcon(new ImageIcon(Gui_ColorChooser.class.getResource("/fr/dreregon/Discord_EMS/system/media/sat.png")));

		JLabel ligPan = new JLabel();
		ligPan.setBounds(270, 136, 265, 26);
		ligPan.setOpaque(false);
		customColors.add(ligPan);
		ligPan.setIcon(new ImageIcon(Gui_ColorChooser.class.getResource("/fr/dreregon/Discord_EMS/system/media/lig.png")));

		JSlider hue = new JSlider();
		hue.setBorder(new LineBorder(panel.getBackground()));
		hue.setOpaque(false);
		hue.setBounds(270, 10, 263, 26);
		customColors.add(hue);

		hue.setValue(0);
		hue.setMaximum(359);

		JLabel lblHue = new JLabel(BUNDLE.getString("Gui_ColorChooser.lblHue.text"));
		lblHue.setHorizontalAlignment(SwingConstants.CENTER);
		lblHue.setForeground(Color.WHITE);
		lblHue.setBounds(581, 22, 66, 14);
		customColors.add(lblHue);

		JSlider sat = new JSlider();
		sat.setOpaque(false);
		sat.setBounds(270, 73, 263, 26);
		customColors.add(sat);
		sat.setBorder(new LineBorder(panel.getBackground()));
		sat.setValue(100);

		JLabel lblSaturation = new JLabel(BUNDLE.getString("Gui_ColorChooser.lblSaturation.text"));
		lblSaturation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaturation.setForeground(Color.WHITE);
		lblSaturation.setBounds(581, 84, 66, 14);
		customColors.add(lblSaturation);

		JSlider lig = new JSlider();
		lig.setOpaque(false);
		lig.setBounds(270, 136, 263, 26);
		customColors.add(lig);
		lig.setBorder(new LineBorder(panel.getBackground()));
		lig.setValue(100);

		JLabel lblLightness = new JLabel(BUNDLE.getString("Gui_ColorChooser.lblLightness.text"));
		lblLightness.setHorizontalAlignment(SwingConstants.CENTER);
		lblLightness.setForeground(Color.WHITE);
		lblLightness.setBounds(581, 145, 66, 14);
		customColors.add(lblLightness);

		textField_H = new JTextField();
		textField_H.setText(BUNDLE.getString("Gui_ColorChooser.textField_H.text")); //$NON-NLS-1$
		textField_H.setHorizontalAlignment(SwingConstants.CENTER);
		textField_H.setForeground(Color.WHITE);
		textField_H.setColumns(10);
		textField_H.setCaretColor(Color.WHITE);
		textField_H.setBorder(new LineBorder(Color.WHITE));
		textField_H.setBackground(Sys_Util.notblack);
		textField_H.setBounds(541, 10, 30, 26);
		customColors.add(textField_H);

		textField_S = new JTextField();
		textField_S.setText(BUNDLE.getString("Gui_ColorChooser.textField_S.text")); //$NON-NLS-1$
		textField_S.setHorizontalAlignment(SwingConstants.CENTER);
		textField_S.setForeground(Color.WHITE);
		textField_S.setColumns(10);
		textField_S.setCaretColor(Color.WHITE);
		textField_S.setBorder(new LineBorder(Color.WHITE));
		textField_S.setBackground(new Color(30, 33, 36));
		textField_S.setBounds(541, 73, 30, 26);
		customColors.add(textField_S);

		textField_L = new JTextField();
		textField_L.setText(BUNDLE.getString("Gui_ColorChooser.textField_L.text")); //$NON-NLS-1$
		textField_L.setHorizontalAlignment(SwingConstants.CENTER);
		textField_L.setForeground(Color.WHITE);
		textField_L.setColumns(10);
		textField_L.setCaretColor(Color.WHITE);
		textField_L.setBorder(new LineBorder(Color.WHITE));
		textField_L.setBackground(new Color(30, 33, 36));
		textField_L.setBounds(541, 136, 30, 26);
		customColors.add(textField_L);

		JPanel discordColors = new JPanel();
		discordColors.setBorder(new LineBorder(Color.WHITE));
		discordColors.setBounds(0, 0, 662, 173);
		frmDiscordemsColorChooser.getContentPane().add(discordColors);
		discordColors.setBackground(Sys_Util.discorddark);
		discordColors.setLayout(null);

		JButton discordD = new JButton(BUNDLE.getString("Gui_ColorChooser.btnNewButton_1.text"));
		discordD.setForeground(Color.BLACK);
		discordD.setBackground(Sys_Util.discorddark);
		discordD.setBorder(new LineBorder(Color.BLACK));
		discordD.setBounds(10, 11, 89, 23);
		discordColors.add(discordD);

		JPanel discordDP = new JPanel();
		discordDP.setBorder(new LineBorder(new Color(0, 0, 0)));
		discordDP.setBackground(Sys_Util.discorddark);
		discordDP.setBounds(98, 11, 23, 23);
		discordColors.add(discordDP);

		JPanel discordNBP = new JPanel();
		discordNBP.setBorder(new LineBorder(new Color(0, 0, 0)));
		discordNBP.setBackground(new Color(54, 57, 62));
		discordNBP.setBounds(219, 11, 23, 23);
		discordNBP.setBackground(Sys_Util.notblack);
		discordColors.add(discordNBP);

		JButton discordNB = new JButton(BUNDLE.getString("Gui_ColorChooser.discordNB.text"));
		discordNB.setForeground(Color.BLACK);
		discordNB.setBorder(new LineBorder(Color.BLACK));
		discordNB.setBackground(Sys_Util.notblack);
		discordNB.setBounds(131, 11, 89, 23);
		discordColors.add(discordNB);

		JPanel discordGPP = new JPanel();
		discordGPP.setBorder(new LineBorder(new Color(0, 0, 0)));
		discordGPP.setBackground(Sys_Util.greyple);
		discordGPP.setBounds(340, 11, 23, 23);
		discordColors.add(discordGPP);

		JButton discordGreyple = new JButton(BUNDLE.getString("Gui_ColorChooser.btnDiscordGreyple.text"));
		discordGreyple.setForeground(Color.BLACK);
		discordGreyple.setBorder(new LineBorder(Color.BLACK));
		discordGreyple.setBackground(Sys_Util.greyple);
		discordGreyple.setBounds(252, 11, 89, 23);
		discordColors.add(discordGreyple);

		JPanel discordBLP = new JPanel();
		discordBLP.setBorder(new LineBorder(new Color(0, 0, 0)));
		discordBLP.setBackground(Sys_Util.blurple);
		discordBLP.setBounds(461, 11, 23, 23);
		discordColors.add(discordBLP);

		JButton btnDiscordBlurple = new JButton(BUNDLE.getString("Gui_ColorChooser.btnDiscordBlurple.text"));
		btnDiscordBlurple.setForeground(Color.BLACK);
		btnDiscordBlurple.setBorder(new LineBorder(Color.BLACK));
		btnDiscordBlurple.setBackground(Sys_Util.blurple);
		btnDiscordBlurple.setBounds(373, 11, 89, 23);
		discordColors.add(btnDiscordBlurple);

		JPanel discordGP = new JPanel();
		discordGP.setBorder(new LineBorder(new Color(0, 0, 0)));
		discordGP.setBackground(Sys_Util.discordGreen);
		discordGP.setBounds(98, 45, 23, 23);
		discordColors.add(discordGP);

		JButton btnDiscordGreen = new JButton(BUNDLE.getString("Gui_ColorChooser.btnDiscordGreen.text"));
		btnDiscordGreen.setForeground(Color.BLACK);
		btnDiscordGreen.setBorder(new LineBorder(Color.BLACK));
		btnDiscordGreen.setBackground(Sys_Util.discordGreen);
		btnDiscordGreen.setBounds(10, 45, 89, 23);
		discordColors.add(btnDiscordGreen);

		JButton btnDiscordYellow = new JButton(BUNDLE.getString("Gui_ColorChooser.btnDiscordYellow.text"));
		btnDiscordYellow.setForeground(Color.BLACK);
		btnDiscordYellow.setBorder(new LineBorder(Color.BLACK));
		btnDiscordYellow.setBackground(Sys_Util.discordYellow);
		btnDiscordYellow.setBounds(131, 45, 89, 23);
		discordColors.add(btnDiscordYellow);

		JPanel discordYP = new JPanel();
		discordYP.setBorder(new LineBorder(new Color(0, 0, 0)));
		discordYP.setBackground(Sys_Util.discordYellow);
		discordYP.setBounds(219, 45, 23, 23);
		discordColors.add(discordYP);

		JButton btnDiscordRed = new JButton(BUNDLE.getString("Gui_ColorChooser.btnDiscordRed.text"));
		btnDiscordRed.setForeground(Color.BLACK);
		btnDiscordRed.setBorder(new LineBorder(Color.BLACK));
		btnDiscordRed.setBackground(Sys_Util.discordRed);
		btnDiscordRed.setBounds(252, 45, 89, 23);
		discordColors.add(btnDiscordRed);

		JPanel discordRP = new JPanel();
		discordRP.setBorder(new LineBorder(new Color(0, 0, 0)));
		discordRP.setBackground(Sys_Util.discordRed);
		discordRP.setBounds(340, 45, 23, 23);
		discordColors.add(discordRP);
		
		for(java.awt.Component c : discordColors.getComponents()) {
			c.setEnabled(false);
			c.setVisible(false);
		}
		discordColors.setOpaque(false);
		
		for(java.awt.Component c : customColors.getComponents()) {
			c.setEnabled(true);
			c.setVisible(true);
		}
		customColors.setOpaque(true);

		discordD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider_R.setValue(Sys_Util.discorddark.getRed());
				slider_G.setValue(Sys_Util.discorddark.getGreen());
				slider_B.setValue(Sys_Util.discorddark.getBlue());
			}
		});

		discordNB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider_R.setValue(Sys_Util.notblack.getRed());
				slider_G.setValue(Sys_Util.notblack.getGreen());
				slider_B.setValue(Sys_Util.notblack.getBlue());
			}
		});

		discordGreyple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider_R.setValue(Sys_Util.greyple.getRed());
				slider_G.setValue(Sys_Util.greyple.getGreen());
				slider_B.setValue(Sys_Util.greyple.getBlue());
			}
		});

		btnDiscordBlurple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider_R.setValue(Sys_Util.blurple.getRed());
				slider_G.setValue(Sys_Util.blurple.getGreen());
				slider_B.setValue(Sys_Util.blurple.getBlue());
			}
		});

		btnDiscordRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider_R.setValue(Sys_Util.discordRed.getRed());
				slider_G.setValue(Sys_Util.discordRed.getGreen());
				slider_B.setValue(Sys_Util.discordRed.getBlue());
			}
		});

		btnDiscordYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider_R.setValue(Sys_Util.discordYellow.getRed());
				slider_G.setValue(Sys_Util.discordYellow.getGreen());
				slider_B.setValue(Sys_Util.discordYellow.getBlue());
			}
		});

		btnDiscordGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				slider_R.setValue(Sys_Util.discordGreen.getRed());
				slider_G.setValue(Sys_Util.discordGreen.getGreen());
				slider_B.setValue(Sys_Util.discordGreen.getBlue());
			}
		});

		JLabel customBtnLbl = new JLabel(BUNDLE.getString("Gui_ColorChooser.customBtnLbl.text")); //$NON-NLS-1$
		customBtnLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for(java.awt.Component c : discordColors.getComponents()) {
					c.setEnabled(false);
					c.setVisible(false);
				}
				discordColors.setOpaque(false);
				
				for(java.awt.Component c : customColors.getComponents()) {
					c.setEnabled(true);
					c.setVisible(true);
				}
				customColors.setOpaque(true);
				customBtnLbl.setBackground(Sys_Util.discorddark);
			}
		});
		customBtnLbl.setBackground(Sys_Util.notblack);
		customBtnLbl.setOpaque(true);
		customBtnLbl.setHorizontalAlignment(SwingConstants.CENTER);
		customBtnLbl.setForeground(Color.WHITE);
		customBtnLbl.setBorder(new LineBorder(Color.WHITE));
		customBtnLbl.setBounds(0, 172, 63, 23);
		frmDiscordemsColorChooser.getContentPane().add(customBtnLbl);

		JLabel lblDiscord = new JLabel(BUNDLE.getString("Gui_ColorChooser.lblDiscord.text")); //$NON-NLS-1$
		
		lblDiscord.setOpaque(true);
		lblDiscord.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiscord.setForeground(Color.WHITE);
		lblDiscord.setBorder(new LineBorder(Color.WHITE));
		lblDiscord.setBackground(Sys_Util.discorddark);
		lblDiscord.setBounds(62, 172, 63, 23);
		frmDiscordemsColorChooser.getContentPane().add(lblDiscord);
		
		customBtnLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for(java.awt.Component c : discordColors.getComponents()) {
					c.setEnabled(false);
					c.setVisible(false);
				}
				discordColors.setOpaque(false);
				
				for(java.awt.Component c : customColors.getComponents()) {
					c.setEnabled(true);
					c.setVisible(true);
				}
				customColors.setOpaque(true);
				customBtnLbl.setBackground(Sys_Util.notblack);
				lblDiscord.setBackground(Sys_Util.discorddark);
			}
		});
		
		lblDiscord.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for(java.awt.Component c : customColors.getComponents()) {
					c.setEnabled(false);
					c.setVisible(false);
				}
				customColors.setOpaque(false);
				
				for(java.awt.Component c : discordColors.getComponents()) {
					c.setEnabled(true);
					c.setVisible(true);
				}
				discordColors.setOpaque(true);
				customBtnLbl.setBackground(Sys_Util.discorddark);
				lblDiscord.setBackground(Sys_Util.notblack);
			}
		});

		slider_R.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				changeColor(0);
			}
		});

		slider_G.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				changeColor(1);
			}
		});

		slider_B.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				changeColor(2);
			}
		});

		lig.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField_L.setText(""+lig.getValue());
				double [] jaj = calculateHSLtoRGB(hue.getValue(), (double)sat.getValue()/100, (double)lig.getValue()/100);
				slider_R.setValue((int)doubleMap(jaj[0], 0d,1d,0,255));
				slider_G.setValue((int)doubleMap(jaj[1], 0d,1d,0,255));
				slider_B.setValue((int)doubleMap(jaj[2], 0d,1d,0,255));
			}
		});

		sat.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField_S.setText(""+sat.getValue());
				double [] jaj = calculateHSLtoRGB(hue.getValue(), (double)sat.getValue()/100, (double)lig.getValue()/100);
				slider_R.setValue((int)doubleMap(jaj[0], 0d,1d,0,255));
				slider_G.setValue((int)doubleMap(jaj[1], 0d,1d,0,255));
				slider_B.setValue((int)doubleMap(jaj[2], 0d,1d,0,255));	
			}
		});
		hue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				textField_H.setText(""+hue.getValue());
				double [] jaj = calculateHSLtoRGB(hue.getValue(), (double)sat.getValue()/100, (double)lig.getValue()/100);
				double [] jaj2 = calculateHSLtoRGB(hue.getValue(), (double)1, (double)1);
				slider_R.setValue((int)doubleMap(jaj[0], 0d,1d,0,255));
				slider_G.setValue((int)doubleMap(jaj[1], 0d,1d,0,255));
				slider_B.setValue((int)doubleMap(jaj[2], 0d,1d,0,255));
				sat.setBorder(new LineBorder(new Color((int)doubleMap(jaj2[0], 0d,1d,0,255), (int)doubleMap(jaj2[1], 0d,1d,0,255), (int)doubleMap(jaj2[2], 0d,1d,0,255))));
				lig.setBorder(new LineBorder(new Color((int)doubleMap(jaj2[0], 0d,1d,0,255), (int)doubleMap(jaj2[1], 0d,1d,0,255), (int)doubleMap(jaj2[2], 0d,1d,0,255))));
			}
		});

		textField_H.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)&&!e.isAltDown()) e.consume();
			}
		});

		textField_S.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)&&!e.isAltDown()) e.consume();
			}
		});

		textField_L.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)&&!e.isAltDown()) e.consume();
			}
		});

		textField_H.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b = textField_H.getText();
				int value = 0;
				try {
					value = Integer.parseInt(b);
				} catch (NumberFormatException e1) {
					if(value>255) textField_H.setText(""+359);

				}
				if(value > 359) {
					textField_H.setText(""+359);
					hue.setValue(value);
				}else if(value < 360){
					hue.setValue(value);
				}else if(value < 0){
					textField_H.setText(""+0);
					hue.setValue(0);
				}
			}
		});

		textField_S.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b = textField_S.getText();
				int value = 0;
				try {
					value = Integer.parseInt(b);
				} catch (NumberFormatException e1) {
					if(value>100) textField_S.setText(""+100);

				}
				if(value > 100) {
					textField_S.setText(""+100);
					sat.setValue(value);
				}else if(value < 101){
					sat.setValue(value);
				}else if(value < 0){
					textField_S.setText(""+0);
					sat.setValue(0);
				}
			}
		});

		textField_L.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b = textField_L.getText();
				int value = 0;
				try {
					value = Integer.parseInt(b);
				} catch (NumberFormatException e1) {
					if(value>100) textField_L.setText(""+100);

				}
				if(value > 100) {
					textField_L.setText(""+100);
					lig.setValue(value);
				}else if(value < 101){
					lig.setValue(value);
				}else if(value < 0){
					textField_L.setText(""+0);
					lig.setValue(0);
				}
			}
		});

		textField_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String b = textField_B.getText();
				int value = 0;
				try {
					value = Integer.parseInt(b);
				} catch (NumberFormatException e1) {
					if(value>255) textField_B.setText(""+255);

				}
				if(value > 255) {
					textField_B.setText(""+255);
					slider_B.setValue(value);
				}else if(value < 256){
					slider_B.setValue(value);
				}else {

				}
			}
		});

		textField_B.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)&&!e.isAltDown()) e.consume();
			}
		});

		textField_G.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String g = textField_G.getText();
				int value = 0;
				try {
					value = Integer.parseInt(g);
				} catch (NumberFormatException e1) {
					if(value>255) textField_G.setText(""+255);

				}
				if(value > 255) {
					textField_G.setText(""+255);
					slider_G.setValue(value);
				}else if(value < 256){
					slider_G.setValue(value);
				}else {

				}
			}
		});

		textField_G.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)&&!e.isAltDown()) e.consume();
			}
		});

		textField_R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String r = textField_R.getText();
				int value = 0;
				try {
					value = Integer.parseInt(r);
				} catch (NumberFormatException e1) {

				}
				if(value > 255) {
					textField_R.setText(""+255);
					slider_R.setValue(value);
				}else if(value < 256){
					slider_R.setValue(value);
				}else {

				}
			}
		});

		textField_R.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLetter(c)&&!e.isAltDown()) e.consume();
			}
		});
	}

	public static Color getColor() {
		return color;
	}

	public static void changeColor(int whitch) {
		String b = "";
		switch(whitch) {
		default:
			b = ""+slider_R.getValue();
			textField_R.setText(b);
			break;
		case 0:
			b = ""+slider_R.getValue();
			textField_R.setText(b);
			break;
		case 1:
			b = ""+slider_G.getValue();
			textField_G.setText(b);
			break;
		case 2:
			b = ""+slider_B.getValue();
			textField_B.setText(b);
			break;
		}

		int[] colorCode = {slider_R.getValue(), slider_G.getValue(), slider_B.getValue()};
		panel.setBackground(new Color(colorCode[0],colorCode[1],colorCode[2]));
		String hex = "#"
				+(colorCode[0]<16?"0":"")
				+Integer.toHexString(colorCode[0])
				+(colorCode[1]<16?"0":"")
				+Integer.toHexString(colorCode[1])
				+(colorCode[2]<16?"0":"")
				+Integer.toHexString(colorCode[2]);

		hexField.setText(hex);
	}


	long map(long x, long in_min, long in_max, long out_min, long out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	double doubleMap(double x, double in_min, double in_max, double out_min, double out_max) {
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	public double[] calculateHSLtoRGB(double H, double S, double L) {
		double[] rgb = new double[3]; 
		double C = L*S;
		double h1 = H/60;
		double X = C*(1-Math.abs((h1%2-1)));

		double rp = 0;
		double gp = 0;
		double bp = 0;

		if(0<=h1 && h1<1) {
			rp = C;
			gp = X;
			bp = 0;
		}else if(1<=h1 && h1 <2) {
			rp = X;
			gp = C;
			bp = 0;
		}else if(2<=h1 && h1 <3) {
			rp = 0;
			gp = C;
			bp = X;
		}else if(3<=h1 && h1 <4) {
			rp = 0;
			gp = X;
			bp = C;
		}else if(4<=h1 && h1 <5) {
			rp = X;
			gp = 0;
			bp = C;
		}else if(5<=h1 && h1 <6) {
			rp = C;
			gp = 0;
			bp = X;
		}

		double m = (L-C);

		rgb[0] = (rp+m);
		rgb[1] = (gp+m);
		rgb[2] = (bp+m);
		return rgb;
	}
}