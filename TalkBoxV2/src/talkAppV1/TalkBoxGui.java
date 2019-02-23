package talkAppV1;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TalkBoxGui extends JFrame {
    /**
     *
     */
    private TalkBox talkbox;
    private String[][] audioFileNames;
    private JButton[][] audioFileButtons;
    private ButtonGroup btngroup;
    private Sound sound;
    private int currentBtnSet = 0, width, height;

    private JLabel display;
    private JButton btnExit, btnConfigure, btnSwap, btnStop, btnVolUp, btnVolDown;
    private JPanel contentPane, top;
    Font font;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TalkBoxGui frame = new TalkBoxGui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create default Simulator the frame.
     */
    public TalkBoxGui() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        setResizable(true);
        setAlwaysOnTop(true);
        setTitle("Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        setResizable(false);
        font = new Font("Stencil", Font.BOLD, width / 50);

        getSetting();
        init();
        putButtons(currentBtnSet);
    }

    public void reset() {
        getSetting();
        init();
        putButtons(currentBtnSet);
    }

    /*
     * Get .tbc settings and initiate sound, buttongroup, talkbox object
     */
    private void getSetting() {
        try {
            sound = new Sound();
            btngroup = new ButtonGroup();
            FileInputStream fileInputStream = new FileInputStream("TalkBoxV2/TalkBoxData/configure.tbc");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            talkbox = (TalkBox) objectInputStream.readObject();
            audioFileNames = talkbox.getAudioFileNames();
            audioFileButtons = new JButton[talkbox.getNumberOfAudioSets()][talkbox.getNumberOfAudioButtons()];
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        int gap = height / 30, btnWidth = (width - 8 * gap) / 6;
        btnSwap = new JButton("Swap");
        btnSwap.setFont(font);
        btnSwap.setBounds(gap + 1 * btnWidth + 1 * gap, height / 3, btnWidth, height / 6);
        contentPane.add(btnSwap);

        btnStop = new JButton("Stop");
        btnStop.setFont(font);
        btnStop.setBounds(gap + 2 * btnWidth + 2 * gap, height / 3, btnWidth, height / 6);
        contentPane.add(btnStop);

        btnVolUp = new JButton("Vol Up");
        btnVolUp.setFont(font);
        btnVolUp.setBounds(gap + 3 * btnWidth + 3 * gap, height / 3, btnWidth, height / 6);
        contentPane.add(btnVolUp);

        btnVolDown = new JButton("Vol Down");
        btnVolDown.setFont(font);
        btnVolDown.setBounds(gap + 4 * btnWidth + 4 * gap, height / 3, btnWidth, height / 6);
        contentPane.add(btnVolDown);

        display = new JLabel("BUTTON PRESSED!");
        contentPane.add(display);
        display.setHorizontalAlignment(SwingConstants.CENTER);
        display.setFont(font);
        display.setBounds(width / 2 - width / 8, height / 5 * 3, width / 4, height / 10);

        btnExit = new JButton("Exit");
        btnExit.setFont(font);
        btnExit.setBounds(width / 30 * 23, height / 4 * 3, width / 30 * 5, height / 8);
        contentPane.add(btnExit);

        btnConfigure = new JButton("Configure");
        btnConfigure.setFont(font);
        btnConfigure.setBounds(width / 30, height / 4 * 3, width / 30 * 21, height / 8);
        contentPane.add(btnConfigure);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnConfigure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.stopSound();
                createConfigure();
            }
        });

        btnSwap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.stopSound();
                for (int i = 0; i < talkbox.getNumberOfAudioButtons(); i++) {
                    JButton btn = audioFileButtons[currentBtnSet][i];
                    if (btn != null)
                        getContentPane().remove(btn);
                }
                contentPane.repaint();
                currentBtnSet = (currentBtnSet + 1) % talkbox.getNumberOfAudioSets();
                putButtons(currentBtnSet);
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.stopSound();
            }
        });
    }

    /*
     * create button in button set setNumber
     */
    private void putButtons(int setNumber) {

        for (int i = 0; i < talkbox.getNumberOfAudioButtons(); i++) {
            String fileName = audioFileNames[setNumber][i];
            if (fileName != null) {
                int gap = height / 30, btnWidth = (width - (talkbox.getNumberOfAudioButtons() + 2) * gap)
                        / talkbox.getNumberOfAudioButtons();
                JButton btn = new JButton(
                        fileName.substring(fileName.indexOf("TalkBoxData/") + 13, fileName.indexOf(".")).replaceAll("_", " "));
                btn.setFont(font);
                btn.setBounds(gap + i * btnWidth + i * gap, height / 30, btnWidth, height / 5);
                btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        sound.playSound(fileName);
                    }
                });
                btngroup.add(btn);
                audioFileButtons[setNumber][i] = btn;
                contentPane.add(btn);
            }
        }
    }

    private void createConfigure() {
        ConfigurationGUI gui = new ConfigurationGUI(this);
    }
}
