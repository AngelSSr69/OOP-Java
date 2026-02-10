public class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getName() { return username; }


    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}

