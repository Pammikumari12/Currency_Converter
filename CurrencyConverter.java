import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {
    JComboBox<String> fromCurrency, toCurrency;
    JTextField amountField;
    JLabel resultLabel;
    HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font inputFont = new Font("Arial", Font.PLAIN, 16);

        // Border Panel
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE, 2),
                "Currency Conversion",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18),
                Color.BLUE
        ));
        panel.setBackground(Color.WHITE);
        panel.setFont(labelFont);

        JLabel fromLabel = new JLabel("From Currency:");
        fromLabel.setFont(labelFont);
        fromCurrency = new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP", "JPY"});
        fromCurrency.setFont(inputFont);

        JLabel toLabel = new JLabel("To Currency:");
        toLabel.setFont(labelFont);
        toCurrency = new JComboBox<>(new String[]{"USD", "INR", "EUR", "GBP", "JPY"});
        toCurrency.setFont(inputFont);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(labelFont);
        amountField = new JTextField();
        amountField.setFont(inputFont);

        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 18));
        convertButton.setBackground(new Color(0, 102, 204));
        convertButton.setForeground(Color.WHITE);
        convertButton.addActionListener(this);

        resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(new Color(0, 128, 0));

        // Add to panel
        panel.add(fromLabel); panel.add(fromCurrency);
        panel.add(toLabel); panel.add(toCurrency);
        panel.add(amountLabel); panel.add(amountField);
        panel.add(new JLabel()); panel.add(convertButton);
        panel.add(new JLabel()); panel.add(resultLabel);

        add(panel);
        setVisible(true);

        // Mock exchange rates
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 83.0);
        exchangeRates.put("INR", 1.0);
        exchangeRates.put("EUR", 91.5);
        exchangeRates.put("GBP", 108.2);
        exchangeRates.put("JPY", 0.57);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            double inINR = amount * exchangeRates.get(from);
            double converted = inINR / exchangeRates.get(to);

            resultLabel.setText(String.format("Converted Amount: %.2f %s", converted, to));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
