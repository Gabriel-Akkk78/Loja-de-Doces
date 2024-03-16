import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LojadeDoces extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JLabel[] candyLabels;
    private JTextField[] quantityFields;
    private final JButton orderButton;
    private double[] prices = {3.00, 2.00, 1.00}; // Preços dos doces

    public LojadeDoces() {
        setTitle("Loja de Doces");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 700);
        setLayout(new GridLayout(8,2));

        titleLabel = new JLabel("Loja de Doces");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);

        candyLabels = new JLabel[3];
        quantityFields = new JTextField[3];

        // Adicionando os doces
        String[] candyImagePaths = {"Cupcake.png", "Cookie.png", "Pudim.png"};
        for (int i = 0; i < 3; i++) {
            try {
                BufferedImage originalImage = ImageIO.read(getClass().getResource(candyImagePaths[i]));
                BufferedImage resizedImage = resizeImage(originalImage, 100, 100); // Tamanho desejado
                ImageIcon candyIcon = new ImageIcon(resizedImage);
                candyLabels[i] = new JLabel(candyIcon);
                JPanel candyPanel = new JPanel(new GridLayout(1, 2));
                candyPanel.add(candyLabels[i]);
                candyPanel.add(new JLabel("R$" + prices[i]));
                add(candyPanel);
            } catch (IOException e) {
                e.printStackTrace();
            }

            quantityFields[i] = new JTextField(5);
            quantityFields[i].setPreferredSize(new Dimension(1, 1)); // Define o tamanho preferido do JTextField
            add(quantityFields[i]);
        }

        orderButton = new JButton("Pedir");
        orderButton.addActionListener(this);
        add(orderButton);

        
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return resizedImage;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == orderButton) {
            double total = 0;
            for (int i = 0; i < 3; i++) {
                int quantity = Integer.parseInt(quantityFields[i].getText());
                total += prices[i] * quantity;
            }
            JOptionPane.showMessageDialog(this, "Total da compra: $" + total);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LojadeDoces::new);
    }
}