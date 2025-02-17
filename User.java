// Modelo que representa un usuario
public class User {
    private String name;
    private String id;
    private String phoneNumber;
    private String email;

    public User(String name, String id, String phoneNumber, String email) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters
    public String getName() { return name; }
    public String getId() { return id; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    // Formato para escribir en archivo separado por '-'
    public String toFileFormat() {
        return name + "-" + id + "-" + phoneNumber + "-" + email;
    }
}