package fr.dreregon.Discord_EMS.system;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.DropMode;
import java.awt.Dialog.ModalityType;

/**
Copyright 2018 Dreregon

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
public class Sys_Message {

	private JDialog messagef;
	private static Runnable runnable;

	/**
	 * Launch the application.
	 */
	public static void main(String message, Sys_MsgType type, Runnable newRunnable) {
		runnable = newRunnable;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sys_Message window = new Sys_Message(message, type);
					window.messagef.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void main(String message, Sys_MsgType type) {
		main(message, type, null);
	}

	/**
	 * Create the application.
	 */
	public Sys_Message(String message, Sys_MsgType type) {
		initialize(message, type);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String message, Sys_MsgType type) {
		/*System.out.println(message);
		if(message.contains("\n")) {
			message = "<html>"+message.concat("</html>").replace("\n", "<br/>");
			System.out.println(message);
		}*/
		
		messagef = new JDialog();
		messagef.setModal(true);
		messagef.setModalityType(ModalityType.APPLICATION_MODAL);
		messagef.setResizable(false);
		messagef.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		messagef.setTitle(type.getName());
		messagef.setIconImage(Toolkit.getDefaultToolkit().getImage(Sys_Message.class.getResource(type.getIcon())));
		messagef.getContentPane().setBackground(Sys_Util.discorddark);
		
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(Sys_Message.class.getResource(type.getIcon())));
		icon.setHorizontalAlignment(SwingConstants.CENTER);
		try {
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Sys_Util.discorddark);
		
		JPanel panel_1 = new JPanel();
		panel_1.setEnabled(false);
		panel_1.setBackground(Sys_Util.discorddark);
		panel_1.setBorder(null);
		GroupLayout groupLayout = new GroupLayout(messagef.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(icon, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(icon, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
		);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setDropMode(DropMode.INSERT);
		textPane.setText(message);
		textPane.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				StyledDocument doc = textPane.getStyledDocument();
				SimpleAttributeSet center = new SimpleAttributeSet();
				StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
				doc.setParagraphAttributes(0, doc.getLength(), center, false);
			}
		});
		textPane.setBackground(Sys_Util.discorddark);
		textPane.setForeground(Color.WHITE);
		scrollPane.setViewportView(textPane);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				messagef.dispose();
				if(runnable != null) {
					try {
					runnable.run();
					}catch(Exception ignored) {}
				}
			}
		});
		panel.add(btnNewButton);
		messagef.getContentPane().setLayout(groupLayout);
		messagef.setAlwaysOnTop(true);
		messagef.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		messagef.setBounds(100, 100, (int) textPane.getPreferredSize().getWidth()+50, (int) textPane.getPreferredSize().getHeight()+120);
		messagef.setLocationRelativeTo(null);
		
		
		
	}
}


