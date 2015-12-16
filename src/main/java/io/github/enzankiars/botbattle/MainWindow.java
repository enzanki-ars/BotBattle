package io.github.enzankiars.botbattle;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Canvas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

import java.awt.FlowLayout;
import java.awt.Graphics;
import io.github.enzankiars.botbattle.bot.Bot;
import io.github.enzankiars.botbattle.bot.demo.DemoBot;
import io.github.enzankiars.botbattle.util.JythonObjectFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainWindow {

	private static JFrame frmBotbattle;
	public static JFrame getFrmBotbattle() {
		return frmBotbattle;
	}

	private static Panel sidePanel;
	private static Canvas canvas;
	private static List<Bot> bots = Collections.synchronizedList(new ArrayList<Bot>());
	private static boolean isInGame = false;
	private static int frame = 0;
	private static JLabel lblTimeValue;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frmBotbattle.setVisible(true);
					
					init();
					
				    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
				    executorService.scheduleAtFixedRate(new Runnable() {
				        @Override
				        public void run() {
							if (isInGame) {
								runBots();
							}
				        }
				    }, 0, 40, TimeUnit.MILLISECONDS);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	private static void runBots() {
		//System.out.println(":)");
		frame++;
		canvas.repaint();
		synchronized (bots) {
			Iterator<Bot> j = bots.iterator(); // Must be in synchronized block
			while (j.hasNext()) {
				Bot n = j.next();
				n.run();
				n.updateBotStatusWindow();
				//System.out.println("[MainThread] " + n.getRotation());
			}
		}
		lblTimeValue.setText(String.valueOf(frame));
	}

	private static void init() {
		bots = Collections.synchronizedList(new ArrayList<Bot>());
		frame=0;
		
		bots.add(new Bot(Color.BLACK, Color.CYAN, "TestJavaBot") {
			@Override
			public void run() {
				addBodyRotation(1);
				//System.out.println(getBodyRotation());
			}
		});
		bots.add(new DemoBot(Color.GRAY, Color.GREEN, "DemoBot"));
		
		JythonObjectFactory pyBotFactory = new JythonObjectFactory(
	            Bot.class, "TestBot", "TestBot");

	    Bot pyBot = (Bot) pyBotFactory.createObject(Color.RED, Color.BLUE, "TestPythonBot");
	    
	    bots.add(pyBot);
	    setSidePanel();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private static void initialize() {
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
		
		sidePanel = new Panel();
		frmBotbattle.getContentPane().add(sidePanel, BorderLayout.EAST);
		sidePanel.setLayout(new GridLayout(16, 0, 0, 0));
		
		JSplitPane bottomPane = new JSplitPane();
		frmBotbattle.getContentPane().add(bottomPane, BorderLayout.SOUTH);
		
		JPanel leftPanel = new JPanel();
		bottomPane.setLeftComponent(leftPanel);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isInGame = true;
			}
		});
		leftPanel.add(btnStart);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isInGame = false;
			}
		});
		leftPanel.add(btnPause);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				isInGame = false;
				init();
				canvas.paint(canvas.getGraphics());
				frame = 0;
				lblTimeValue.setText(String.valueOf(frame));
			}
		});
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
		
		lblTimeValue = new JLabel("0");
		rightPanel.add(lblTimeValue);
		
		JMenuBar menuBar = new JMenuBar();
		frmBotbattle.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
	}
	
	public static void setSidePanel() {
		for (final Bot bot : bots) {
			JButton button = new JButton(bot.getName());
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					bot.getBotStatusWindow().setVisible(true);
				}
			});
			sidePanel.add(button);
			
		}
	}
	
	public static int getCanvasWidth() {
		return canvas.getWidth();
	};
	
	public static int getCanvasHeight() {
		return canvas.getHeight();
	};
	
}
