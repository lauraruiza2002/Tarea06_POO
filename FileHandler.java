import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Maneja la lectura y escritura en el archivo "RegisteredUsers.txt"
public class FileHandler {
    private static final String FILE_NAME = "RegisteredUsers.txt";

    // Método para leer usuarios desde el archivo
    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_NAME);

        // Si el archivo no existe, retornar lista vacía
        if (!file.exists()) {
            return users;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");

                // Verificar que la línea tiene los 4 elementos requeridos
                if (parts.length == 4) {
                    users.add(new User(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Método para escribir usuarios en el archivo
    public static void writeUsers(List<User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                bw.write(user.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}