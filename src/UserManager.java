import java.util.HashMap;

public class UserManager {
    private final HashMap<String, User> users = new HashMap<>();

    public UserManager() {
        users.put("admin", new User("admin", "1234"));
    }

    public boolean signUp(String username, String password) {
        if (users.containsKey(username)) return false;
        users.put(username, new User(username, password));
        return true;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.verifyPassword(password)) {
            return user;
        }
        return null;
    }

    public boolean updatePassword(String username, String oldPass, String newPass) {
        User user = users.get(username);
        if (user != null && user.verifyPassword(oldPass)) {
            users.put(username, new User(username, newPass));
            return true;
        }
        return false;
    }

    public boolean deleteUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.verifyPassword(password)) {
            users.remove(username);
            return true;
        }
        return false;
    }
}