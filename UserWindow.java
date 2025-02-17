import java.awt.*;
import java.util.List;
import javax.swing.*;

// Ventana principal de la aplicación
public class UserWindow extends JFrame {
    private JTextField nameField, idField, phoneField, emailField;
    private JTextArea outputArea;

    public UserWindow() {
        setTitle("User Management");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("User Information"));

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        inputPanel.add(createLabel("Name:", labelFont));
        nameField = createTextField(inputFont);
        inputPanel.add(nameField);

        inputPanel.add(createLabel("ID:", labelFont));
        idField = createTextField(inputFont);
        inputPanel.add(idField);

        inputPanel.add(createLabel("Phone Number:", labelFont));
        phoneField = createTextField(inputFont);
        inputPanel.add(phoneField);

        inputPanel.add(createLabel("Email:", labelFont));
        emailField = createTextField(inputFont);
        inputPanel.add(emailField);

        add(inputPanel, BorderLayout.NORTH);

        // Área de salida de datos
        outputArea = new JTextArea(6, 30);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));

        buttonPanel.add(createButton("Create", new Color(144, 238, 144), e -> createUser()));
        buttonPanel.add(createButton("Read", new Color(173, 216, 230), e -> readUsers()));
        buttonPanel.add(createButton("Update", new Color(192, 192, 192), e -> updateUser()));
        buttonPanel.add(createButton("Delete", new Color(255, 182, 193), e -> deleteAllUsers()));
        buttonPanel.add(createButton("Clear", new Color(255, 255, 102), e -> clearFields()));
        buttonPanel.add(createButton("Search by ID", new Color(255, 204, 153), e -> searchUserByID()));

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    // Método para crear una etiqueta con estilo
    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    // Método para crear un campo de texto con estilo
    private JTextField createTextField(Font font) {
        JTextField textField = new JTextField(15);
        textField.setFont(font);
        return textField;
    }

    // Método para crear un botón con color y acción
    private JButton createButton(String text, Color color, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.addActionListener(action);
        return button;
    }

    // Método para mostrar mensajes con JOptionPane
    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    // Métodos de acción
    private void createUser() {
        User user = new User(nameField.getText(), idField.getText(), phoneField.getText(), emailField.getText());
        String result = UserManager.createUser(user);
        showMessage(result, "Create User", JOptionPane.INFORMATION_MESSAGE);
    }

    private void readUsers() {
        List<User> users = UserManager.readUsers();
        if (users.isEmpty()) {
            outputArea.setText("No users found.");
            return;
        }

        outputArea.setText("");
        for (User user : users) {
            outputArea.append("Name: " + user.getName() + "\n" +
                    "ID: " + user.getId() + "\n" +
                    "Phone Number: " + user.getPhoneNumber() + "\n" +
                    "Email: " + user.getEmail() + "\n\n");
        }
    }

    private void updateUser() {
        String id = idField.getText();
        if (id.isEmpty()) {
            showMessage("Error: ID is required for updating a user.", "Update Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = new User(nameField.getText(), id, phoneField.getText(), emailField.getText());
        String result = UserManager.updateUser(user);
        showMessage(result, "Update User", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteAllUsers() {
        String result = UserManager.clearUsers();
        showMessage(result, "Delete Users", JOptionPane.WARNING_MESSAGE);
    }

    private void clearFields() {
        nameField.setText("");
        idField.setText("");
        phoneField.setText("");
        emailField.setText("");
        outputArea.setText("");
        showMessage("Fields cleared.", "Clear Fields", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchUserByID() {
        String id = idField.getText();
        if (id.isEmpty()) {
            showMessage("Please enter an ID to search.", "Search Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<User> users = UserManager.readUsers();
        for (User user : users) {
            if (user.getId().equals(id)) {
                outputArea.setText("User Found:\n" +
                        "Name: " + user.getName() + "\n" +
                        "ID: " + user.getId() + "\n" +
                        "Phone Number: " + user.getPhoneNumber() + "\n" +
                        "Email: " + user.getEmail());
                return;
            }
        }
        showMessage("User not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
    }
}