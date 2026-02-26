import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class FortuneTellerFrame extends JFrame
{
    JPanel mainPanel, titlePanel, displayPanel, commandPanel;
    JLabel titleLabel;
    ImageIcon icon;
    JScrollPane scrollPane;
    JTextArea fortuneTA;
    JButton quitButton, fortuneButton;
    String[] fortunes = new String[15];

    int currentFortuneIndex = -1;

    public FortuneTellerFrame()
    {
        loadFortunes();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);
        createTitlePanel();
        createDisplayPanel();
        createControlPanel();

        setTitle("Fortune Teller");
        setSize(600, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadFortunes()
    {
        fortunes[0] = "You will have a bad time today.";
        fortunes[1] = "A certain Fortune Teller will key your car tomorrow.";
        fortunes[2] = "You could have a bad time today.";
        fortunes[3] = "You should have worn deodorant today";
        fortunes[4] = "Take a shower.";
        fortunes[5] = "Don't eat the spoiled eggs in the fridge.";
        fortunes[6] = "You will eat the spoiled eggs in your fridge.";
        fortunes[7] = "You will be hit by a red plane.";
        fortunes[8] = "The sun will disappear tonight.";
        fortunes[9] = "You may receive a bomb in the mail in the next year.";
        fortunes[10] = "You will be hit by a yellow car.";
        fortunes[11] = "Three dark pillars loom in the distance.";
        fortunes[12] = "You will have your fortune read sometime today,";
        fortunes[13] = "Sorry, I forgot what the fortune was.";
        fortunes[14] = "You will be hit by a red car.";
    }

    public void createTitlePanel()
    {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/FortuneTeller.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Downsizing the image by 4x (original size is W780, H1041)
        Image cat = img.getScaledInstance(195, 260, Image.SCALE_SMOOTH);

        titlePanel = new JPanel();
        icon = new ImageIcon(cat);
        titleLabel = new JLabel(icon);
        titleLabel.setText("Fortunes for free here!");
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));

        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
    }

    public void createDisplayPanel()
    {
        displayPanel = new JPanel();
        fortuneTA = new JTextArea(15, 50);
        fortuneTA.setEditable(false);
        fortuneTA.setLineWrap(true);
        fortuneTA.setFont(new Font("Serif", Font.PLAIN, 14));
        scrollPane = new JScrollPane(fortuneTA);
        displayPanel.add(scrollPane);
        mainPanel.add(displayPanel, BorderLayout.CENTER);
    }

    public void createControlPanel()
    {
        Random rand  = new Random();
        commandPanel = new JPanel();
        commandPanel.setLayout(new GridLayout(1, 2));
        fortuneButton = new JButton("Press for a Fortune!");
        quitButton = new JButton("Quit");
        fortuneButton.setFont(new Font("Serif", Font.BOLD, 14));
        quitButton.setFont(new Font("Serif", Font.BOLD, 14));

        quitButton.addActionListener((ActionEvent e) -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        fortuneButton.addActionListener((ActionEvent e) ->
        {
            int newDex = currentFortuneIndex;
            do
            {
                newDex = rand.nextInt(fortunes.length);
            }while(newDex == currentFortuneIndex);
            currentFortuneIndex = newDex;
            fortuneTA.append(fortunes[newDex] + "\n");
        });

        commandPanel.add(fortuneButton);
        commandPanel.add(quitButton);
        mainPanel.add(commandPanel, BorderLayout.SOUTH);
    }
}
