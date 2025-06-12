// Calculator.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField display;
    String operator = "";
    double num1, num2, result;

    public Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String bText : buttons) {
            JButton button = new JButton(bText);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();

        if (text.matches("[0-9\\.]")) {
            display.setText(display.getText() + text);
        } else if (text.matches("[\\+\\-\\*/]")) {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = text;
                display.setText("");
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else if (text.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": result = num2 != 0 ? num1 / num2 : 0; break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else if (text.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}