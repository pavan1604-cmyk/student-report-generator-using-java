import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentReportCardGUI extends JFrame implements ActionListener {
    JTextField nameField, rollField;
    JTextField[] markFields = new JTextField[5];
    JButton generateButton;

    public StudentReportCardGUI() {
        setTitle("🎓 Student Report Card Generator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(true);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name field
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        panel.add(nameField, gbc);

        // Roll Number
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Roll Number:"), gbc);
        gbc.gridx = 1;
        rollField = new JTextField(15);
        panel.add(rollField, gbc);

        // Marks input
        for (int i = 0; i < 5; i++) {
            gbc.gridx = 0;
            gbc.gridy++;
            panel.add(new JLabel("Subject " + (i + 1) + " Marks:"), gbc);
            gbc.gridx = 1;
            markFields[i] = new JTextField(15);
            panel.add(markFields[i], gbc);
        }

        // Generate Button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        generateButton = new JButton("Generate Report Card");
        generateButton.setBackground(new Color(34, 139, 34));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        generateButton.addActionListener(this);
        panel.add(generateButton, gbc);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        int[] marks = new int[5];
        int total = 0;

        try {
            for (int i = 0; i < 5; i++) {
                marks[i] = Integer.parseInt(markFields[i].getText().trim());
                if (marks[i] < 0 || marks[i] > 100) {
                    throw new NumberFormatException("Invalid mark");
                }
                total += marks[i];
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "⚠️ Please enter valid marks (0-100).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double percentage = total / 5.0;
        char grade;

        if (percentage >= 90)
            grade = 'A';
        else if (percentage >= 80)
            grade = 'B';
        else if (percentage >= 70)
            grade = 'C';
        else if (percentage >= 60)
            grade = 'D';
        else
            grade = 'F';

        StringBuilder result = new StringBuilder();
        result.append("🎓 Report Card\n");
        result.append("------------------------\n");
        result.append("Name       : " + name + "\n");
        result.append("Roll No.   : " + roll + "\n");
        result.append("Marks      : ");
        for (int m : marks) {
            result.append(m + " ");
        }
        result.append("\nTotal      : " + total);
        result.append("\nPercentage : " + String.format("%.2f", percentage) + "%");
        result.append("\nGrade      : " + grade);

        JTextArea textArea = new JTextArea(result.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "📄 Report Card", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentReportCardGUI::new);
    }
}
