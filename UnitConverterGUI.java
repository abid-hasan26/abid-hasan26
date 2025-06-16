import javax.swing.*;
import java.awt.*;

public class UnitConverterGUI extends JFrame {

    private JComboBox<String> conversionType;
    private JTextField inputField, resultField;
    private JButton convertButton, updateButton, clearButton;

    public UnitConverterGUI() {
        setTitle("Unit Converter System");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] options = {
            "Centimeters to Inches",
            "Inches to Centimeters",
            "Kilograms to Pounds",
            "Pounds to Kilograms",
            "Meters to Feet",
            "Feet to Meters",
            "Celsius to Fahrenheit",
            "Fahrenheit to Celsius"
        };

        conversionType = new JComboBox<>(options);
        inputField = new JTextField(10);
        resultField = new JTextField(15);
        resultField.setEditable(false);

        convertButton = new JButton("Convert");
        updateButton = new JButton("Update Input");
        clearButton = new JButton("Clear");

        JPanel panel = new JPanel(new GridLayout(6, 3, 18, 18));
        panel.setBorder(BorderFactory.createEmptyBorder(36, 36, 36, 36));

        panel.add(new JLabel("Conversion Type:"));
        panel.add(conversionType);
        panel.add(new JLabel("Input Value:"));
        panel.add(inputField);
        panel.add(new JLabel("Result:"));
        panel.add(resultField);
        panel.add(convertButton);
        panel.add(updateButton);
        panel.add(clearButton);

        add(panel);

        convertButton.addActionListener(e -> convert());
        updateButton.addActionListener(e -> updateInput());
        clearButton.addActionListener(e -> clearFields());
    }

    private void convert() {
        try {
            double input = Double.parseDouble(inputField.getText());
            double result = 0;
            int index = conversionType.getSelectedIndex();

            switch (index) {
                case 0: result = input / 2.54; break;
                case 1: result = input * 2.54; break;
                case 2: result = input * 2.20462; break;
                case 3: result = input / 2.20462; break;
                case 4: result = input * 3.28084; break;
                case 5: result = input / 3.28084; break;
                case 6: result = (input * 9 / 5) + 32; break;
                case 7: result = (input - 32) * 5 / 9; break;
            }

            resultField.setText(String.format("%.4f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateInput() {
        String newValue = JOptionPane.showInputDialog(this, "Enter new input value:");
        if (newValue != null && !newValue.trim().isEmpty()) {
            inputField.setText(newValue);
        }
    }

    private void clearFields() {
        inputField.setText("");
        resultField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UnitConverterGUI().setVisible(true));
    }
}

