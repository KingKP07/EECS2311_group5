package talkAppV1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class ConfigurationGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnBack;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private ArrayList<String> clips = new ArrayList<String>(), selected = new ArrayList<String>(),
			nonSelected = new ArrayList();
	private String name;
	private Toolkit t;
	private final int WIDTH, HEIGHT;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ConfigurationGUI frame = new
	 * ConfigurationGUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the frame.
	 */
	public ConfigurationGUI(ArrayList clips) {
		WIDTH = (int) t.getDefaultToolkit().getScreenSize().getWidth();
		HEIGHT = (int) t.getDefaultToolkit().getScreenSize().getHeight();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,WIDTH,HEIGHT);
		setLocationRelativeTo(null);
		setSize(WIDTH, HEIGHT);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(WIDTH,HEIGHT));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		this.clips = clips;
		addClips();
		init();
		addAction();
		setNames();
	}


	private void setNames() {
				
	}

	private void addClips() {
		for (int i = 0; i < clips.size(); i++) {
			if (i < 4)
				selected.add(clips.get(i));
			else
				nonSelected.add(clips.get(i));
		}
	}

	private void addAction() {
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TalkBoxApp();
			}
		});

	}

	protected void TalkBoxApp() {
		this.setVisible(false);
		new TalkBoxGui().setVisible(true);

	}

	private void init() {

		JToggleButton btnButton2 = new JToggleButton("STRONG NO");
		btnButton2.setBounds(9, 48, 217, 23);
		buttonGroup_1.add(btnButton2);
		btnButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSound(1);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnButton2);

		JButton btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.setBounds(354, 48, 37, 23);
		contentPane.add(btnNewButton_1);

		JToggleButton btnHello = new JToggleButton("HELLO");
		btnHello.setBounds(479, 48, 237, 23);
		buttonGroup.add(btnHello);
		contentPane.add(btnHello);

		JToggleButton btnButton3 = new JToggleButton("YES");
		btnButton3.setBounds(9, 77, 217, 23);
		btnButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSound(2);
			}
		});
		buttonGroup_1.add(btnButton3);
		contentPane.add(btnButton3);

		JButton btnRemove = new JButton("REMOVE");
		btnRemove.setBounds(354, 77, 37, 23);
		contentPane.add(btnRemove);

		JToggleButton btnCherie = new JToggleButton("HELLO CHERIE");
		btnCherie.setBounds(479, 77, 237, 23);
		buttonGroup.add(btnCherie);
		contentPane.add(btnCherie);

		JToggleButton btnButton4 = new JToggleButton("HELL YEAH");
		btnButton4.setBounds(9, 106, 217, 23);
		btnButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSound(3);
			}
		});
		buttonGroup_1.add(btnButton4);
		contentPane.add(btnButton4);

		JToggleButton btnHelloThere = new JToggleButton("HELLO THERE");
		btnHelloThere.setBounds(479, 106, 237, 23);
		buttonGroup.add(btnHelloThere);
		contentPane.add(btnHelloThere);

		JToggleButton btnBruh = new JToggleButton("BRUH");
		btnBruh.setBounds(479, 135, 237, 23);
		buttonGroup.add(btnBruh);
		contentPane.add(btnBruh);

		JToggleButton btnTadaah = new JToggleButton("TADAAH");
		btnTadaah.setBounds(479, 164, 237, 23);
		buttonGroup.add(btnTadaah);
		contentPane.add(btnTadaah);
		btnBack = new JButton("BACK");
		btnBack.setBounds(9, 700, 707, 29);
		btnBack.setFont(new Font("Stencil", Font.BOLD, 20));
		contentPane.add(btnBack);

		JToggleButton btnButton1 = new JToggleButton("NO");
		btnButton1.setBounds(9, 19, 217, 23);
		btnButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeSound(0);
			}
		});
		buttonGroup_1.add(btnButton1);
		contentPane.add(btnButton1);

		JToggleButton btnNewButton = new JToggleButton("BYE HAVE A BEAUTIFUL TIME");
		btnNewButton.setBounds(479, 19, 237, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSound(0);
			}
		});
		buttonGroup.add(btnNewButton);
		contentPane.add(btnNewButton);

	}

	protected void addSound(int i) {

	}

	protected void removeSound(int i) {

	}
	
	private void getName(String audioFile) {
		name = audioFile.substring(audioFile.indexOf("/") + 1, audioFile.indexOf("."));
	}
}
