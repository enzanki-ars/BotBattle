package io.github.enzankiars.botbattle.bot;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BotStatus extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3165565300629739794L;
	private JPanel contentPane;
	private JTextField txtBodyRotation;
	private JTextField txtName;
	private JLabel lblX;
	private JTextField txtX;
	private JLabel lblY;
	private JTextField txtY;
	private Bot bot;
	private JLabel lblGunRotation;
	private JTextField txtGunRotation;
	private JLabel lblNormalizeAngles;
	private JCheckBox chckbxNormalize;
	private JLabel lblAngleFormat;
	private JRadioButton rdbtnDegrees;
	private JPanel panel;
	private JRadioButton rdbtnRadians;
	/**
	 * Create the frame.
	 */
	public BotStatus(Bot bot) {
		this.bot = bot;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblName = new JLabel("Name:");
		contentPane.add(lblName, "2, 2, right, default");
		
		txtName = new JTextField();
		txtName.setEditable(false);
		contentPane.add(txtName, "4, 2, fill, default");
		txtName.setColumns(10);
		
		JLabel lblBodyRotation = new JLabel("Body Rotation:");
		contentPane.add(lblBodyRotation, "2, 4, right, default");
		
		txtBodyRotation = new JTextField();
		txtBodyRotation.setEditable(false);
		contentPane.add(txtBodyRotation, "4, 4, fill, default");
		txtBodyRotation.setColumns(10);
		
		lblGunRotation = new JLabel("Gun Rotation:");
		contentPane.add(lblGunRotation, "2, 6, right, default");
		
		txtGunRotation = new JTextField();
		txtGunRotation.setEditable(false);
		contentPane.add(txtGunRotation, "4, 6, fill, default");
		txtGunRotation.setColumns(10);
		
		lblX = new JLabel("X:");
		contentPane.add(lblX, "2, 8, right, default");
		
		txtX = new JTextField();
		txtX.setEditable(false);
		contentPane.add(txtX, "4, 8, fill, default");
		txtX.setColumns(10);
		
		lblY = new JLabel("Y:");
		contentPane.add(lblY, "2, 10, right, default");
		
		txtY = new JTextField();
		txtY.setEditable(false);
		contentPane.add(txtY, "4, 10, fill, default");
		txtY.setColumns(10);
		
		txtName.setText(this.bot.getName());
		txtBodyRotation.setText(String.valueOf(this.bot.getBodyRotation()));
		txtGunRotation.setText(String.valueOf(this.bot.getGunRotation()));
		txtX.setText(String.valueOf(this.bot.getX()));
		txtY.setText(String.valueOf(this.bot.getY()));
		
		lblNormalizeAngles = new JLabel("Normalize Angles:");
		contentPane.add(lblNormalizeAngles, "2, 12, right, default");
		
		chckbxNormalize = new JCheckBox("Normalize");
		chckbxNormalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		contentPane.add(chckbxNormalize, "4, 12");
		
		lblAngleFormat = new JLabel("Angle Format:");
		contentPane.add(lblAngleFormat, "2, 14, right, default");
		
		panel = new JPanel();
		contentPane.add(panel, "4, 14, fill, fill");
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		rdbtnDegrees = new JRadioButton("Degrees");
		rdbtnDegrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		rdbtnDegrees.setSelected(true);
		panel.add(rdbtnDegrees);
		
		rdbtnRadians = new JRadioButton("Radians");
		rdbtnRadians.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		panel.add(rdbtnRadians);
		
		buttonGroup.add(rdbtnDegrees);
		buttonGroup.add(rdbtnRadians);
	}
	
	public void update() {
		txtName.setText(this.bot.getName());
		if (chckbxNormalize.isSelected()) {
			if (rdbtnDegrees.isSelected()) {
				txtBodyRotation.setText(String.valueOf(this.bot.getBodyRotation() % 360));
				txtGunRotation.setText(String.valueOf(this.bot.getGunRotation() % 360));
			}
			else if (rdbtnRadians.isSelected()) {
				txtBodyRotation.setText(String.valueOf(Math.toRadians(this.bot.getBodyRotation()) % 2*Math.PI));
				txtGunRotation.setText(String.valueOf(Math.toRadians(this.bot.getGunRotation()) % 2*Math.PI));
			}
			else {
				System.err.println("??? No Degree/Radian Selected");
			}
		}
		else if (!chckbxNormalize.isSelected()) {
			if (rdbtnDegrees.isSelected()) {
				txtBodyRotation.setText(String.valueOf(this.bot.getBodyRotation()));
				txtGunRotation.setText(String.valueOf(this.bot.getGunRotation()));
			}
			else if (rdbtnRadians.isSelected()) {
				txtBodyRotation.setText(String.valueOf(Math.toRadians(this.bot.getBodyRotation())));
				txtGunRotation.setText(String.valueOf(Math.toRadians(this.bot.getGunRotation())));
			}
			else {
				System.err.println("??? No Degree/Radian Selected");
			}
		}
		else {
			System.err.println("??? Normalize is acting weird");
		}
		txtX.setText(String.valueOf(this.bot.getX()));
		txtY.setText(String.valueOf(this.bot.getY()));
	}

	public void addCustomPanel(JPanel customPanel) {
		contentPane.add(customPanel, "4, 16, fill, fill");
	}
}
