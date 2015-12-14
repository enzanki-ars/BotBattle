package io.github.enzankiars.botbattle.bot;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;

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

	/**
	 * Create the frame.
	 */
	public BotStatus(Bot bot) {
		this.bot = bot;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
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
	}
	
	public void update() {
		txtName.setText(this.bot.getName());
		txtBodyRotation.setText(String.valueOf(this.bot.getBodyRotation()));
		txtGunRotation.setText(String.valueOf(this.bot.getGunRotation()));
		txtX.setText(String.valueOf(this.bot.getX()));
		txtY.setText(String.valueOf(this.bot.getY()));
	}

}
