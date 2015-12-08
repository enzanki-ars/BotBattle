package io.github.enzankiars.botbattle;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import java.awt.Graphics;
import io.github.enzankiars.botbattle.bot.Bot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MainWindow {

	private JFrame frmBotbattle;
	private static Canvas canvas;
	private static List<Bot> bots = Collections.synchronizedList(new ArrayList<Bot>());
	private static long time;
	private static long lastTime;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmBotbattle.setVisible(true);
					
					bots.add(new Bot(Color.BLACK, Color.CYAN));
					
					Thread run = new Thread() {
						public void run() {
							int frame = 0;
							do {
								lastTime = time;
								time = System.nanoTime();
								
								if ((time - lastTime) > 16666667) {
									frame++;
									canvas.repaint();
								
									synchronized (bots) {
										Iterator<Bot> j = bots.iterator(); // Must be in synchronized block
										while (j.hasNext()) {
											Bot n = j.next();
											n.setRotation(frame/360);
											//System.out.println("[MainThread] " + n.getRotation());
										}
									}
								}
							} while (true);
						}
					};
					run.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBotbattle = new JFrame();
		frmBotbattle.setTitle("BotBattle");
		frmBotbattle.setBounds(100, 100, 720, 480);
		frmBotbattle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBotbattle.getContentPane().setLayout(new BorderLayout(0, 0));
		
		canvas = new Canvas() {
			private static final long serialVersionUID = -1514620282447736707L;

			@Override
			public void paint(Graphics g) {
				// TODO Auto-generated method stub
				super.paint(g);
				synchronized (bots) {
					Iterator<Bot> i = bots.iterator(); // Must be in synchronized block
					while (i.hasNext()) {
						Bot c = i.next();
						c.drawFullImage(g);
						//System.out.println("[DrawThread] " + c.getRotation());
					}
				}
			}
		};
		canvas.setBackground(new Color(85, 107, 47));
		frmBotbattle.getContentPane().add(canvas, BorderLayout.CENTER);
		
		Panel sidePanel = new Panel();
		frmBotbattle.getContentPane().add(sidePanel, BorderLayout.EAST);
		sidePanel.setLayout(new GridLayout(16, 0, 0, 0));
		
		JButton btnTest = new JButton("Test");
		sidePanel.add(btnTest);
		JButton button = new JButton("Test");
		sidePanel.add(button);
		
		JSplitPane bottomPane = new JSplitPane();
		frmBotbattle.getContentPane().add(bottomPane, BorderLayout.SOUTH);
		
		JPanel leftPanel = new JPanel();
		bottomPane.setLeftComponent(leftPanel);
		
		JButton btnStart = new JButton("Start");
		leftPanel.add(btnStart);
		
		JButton btnPause = new JButton("Pause");
		leftPanel.add(btnPause);
		
		JButton btnRestart = new JButton("Restart");
		leftPanel.add(btnRestart);
		
		JPanel rightPanel = new JPanel();
		bottomPane.setRightComponent(rightPanel);
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblRound = new JLabel("Round:");
		rightPanel.add(lblRound);
		
		JLabel lblRoundValue = new JLabel("0");
		rightPanel.add(lblRoundValue);
		
		JLabel lblTime = new JLabel("Time:");
		rightPanel.add(lblTime);
		
		JLabel lblTimeValue = new JLabel("0");
		rightPanel.add(lblTimeValue);
		
		JMenuBar menuBar = new JMenuBar();
		frmBotbattle.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
	}
	
	public static int getCanvasWidth() {
		return canvas.getWidth();
	};
	
	public static int getCanvasHeight() {
		return canvas.getHeight();
	};
	
}
