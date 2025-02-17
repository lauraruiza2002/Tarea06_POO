import java.util.List;


// Clase que maneja las operaciones CRUD
public class UserManager {

    // Método para validar si un string contiene solo números
    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    // Método para validar si el email tiene @ y .com
    private static boolean isValidEmail(String email) {
        return email.contains("@") && email.endsWith(".com");
    }

    // Método para crear un usuario con validaciones
    public static String createUser(User newUser) {
        List<User> users = FileHandler.readUsers();

        // Validaciones
        if (!isNumeric(newUser.getId())) {
            return "Error: ID must contain only numbers.";
        }
        if (!isNumeric(newUser.getPhoneNumber())) {
            return "Error: Phone Number must contain only numbers.";
        }
        if (!isValidEmail(newUser.getEmail())) {
            return "Error: Invalid email format. Email must contain '@' and end with '.com'.";
        }

        // Verificar si el usuario ya existe
        for (User user : users) {
            if (user.getId().equals(newUser.getId())) {
                return "Error: ID already exists.";
            }
        }

        users.add(newUser);
        FileHandler.writeUsers(users);
        return "User created successfully.";
    }

    // Método para leer todos los usuarios
    public static List<User> readUsers() {
        return FileHandler.readUsers();
    }

    // Método para actualizar un usuario basado en el ID con validaciones
    public static String updateUser(User updatedUser) {
        List<User> users = FileHandler.readUsers();
        boolean found = false;

        // Validaciones
        if (!isNumeric(updatedUser.getId())) {
            return "Error: ID must contain only numbers.";
        }
        if (!isNumeric(updatedUser.getPhoneNumber())) {
            return "Error: Phone Number must contain only numbers.";
        }
        if (!isValidEmail(updatedUser.getEmail())) {
            return "Error: Invalid email format. Email must contain '@' and end with '.com'.";
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser); // Reemplazar con el nuevo usuario
                found = true;
                break;
            }
        }

        if (found) {
            FileHandler.writeUsers(users);
            return "User updated successfully.";
        } else {
            return "Error: User ID not found.";
        }
    }

    // Método para eliminar todos los usuarios
    public static String clearUsers() {
        FileHandler.writeUsers(List.of()); // Borra el contenido del archivo
        return "All users have been deleted.";
    }
}