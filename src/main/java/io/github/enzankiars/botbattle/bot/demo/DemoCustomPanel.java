package io.github.enzankiars.botbattle.bot.demo;

import javax.swing.JPanel;

import io.github.enzankiars.botbattle.bot.Bot;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DemoCustomPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7789593242324312199L;

	/**
	 * Create the panel.
	 */
	public DemoCustomPanel(final Bot bot) {
		super();
		setLayout(new BorderLayout(0, 0));
		
		JButton btnForward = new JButton("Forward");
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bot.forward();
			}
		});
		add(btnForward, BorderLayout.NORTH);
		
		JButton btnRotateLeft = new JButton("Rotate Left");
		btnRotateLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bot.addBodyRotation(-2);
			}
		});
		add(btnRotateLeft, BorderLayout.WEST);
		
		JButton btnRotateRight = new JButton("Rotate Right");
		btnRotateRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bot.addBodyRotation(2);
			}
		});
		add(btnRotateRight, BorderLayout.EAST);
		
		JButton btnBackward = new JButton("Backward");
		btnBackward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bot.backward();
			}
		});
		add(btnBackward, BorderLayout.SOUTH);
		
		JButton btnFire = new JButton("Fire");
		btnFire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Not implemented");
			}
		});
		add(btnFire, BorderLayout.CENTER);

	}
}
