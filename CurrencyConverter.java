import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter extends JFrame {
    private JTextField amountTextField;
    private JComboBox<String> fromCurrencyComboBox;
    private JComboBox<String> toCurrencyComboBox;
    private JLabel resultLabel;

    private String[] currencies = {"USD", "EUR", "GBP", "JPY"};
    private double[][] conversionRates = {
            {1.0, 0.85, 0.73, 109.34},
            {1.18, 1.0, 0.86, 129.31},
            {1.37, 1.16, 1.0, 149.64},
            {0.0091, 0.0077, 0.0067, 1.0}
    };

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel amountLabel = new JLabel("Amount:");
        amountTextField = new JTextField(10);
        JLabel fromCurrencyLabel = new JLabel("From:");
        fromCurrencyComboBox = new JComboBox<>(currencies);
        JLabel toCurrencyLabel = new JLabel("To:");
        toCurrencyComboBox = new JComboBox<>(currencies);
        JButton convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        add(amountLabel);
        add(amountTextField);
        add(fromCurrencyLabel);
        add(fromCurrencyComboBox);
        add(toCurrencyLabel);
        add(toCurrencyComboBox);
        add(convertButton);
        add(resultLabel);

        pack();
        setVisible(true);
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountTextField.getText());
            int fromIndex = fromCurrencyComboBox.getSelectedIndex();
            int toIndex = toCurrencyComboBox.getSelectedIndex();

            double fromRate = conversionRates[fromIndex][toIndex];
            double toRate = conversionRates[toIndex][fromIndex];

            double convertedAmount = amount * fromRate;

            String result = String.format("%.2f %s = %.2f %s", amount, currencies[fromIndex], convertedAmount, currencies[toIndex]);
            resultLabel.setText(result);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid amount");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverter();
            }
        });
    }
}
