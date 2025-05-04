package calories;

public class User {
    public String username, password;
    public static User[] users = new User[100];
    public static int userCount = 0;
    private static int userIndex = 0;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //for register
    public static String register(String username, String password) {
        if (password.length() < 6) {
            return "Password must be at least 6 characters!";
        }

        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username)) {
                return "Username already exists!";
            }
        }

        if (userCount < users.length) {
            users[userCount] = new User(username, password);
            userCount++;
            return "SUCCESS";
        } else {
            return "User array is full!";
        }
    }

    //for login
    public static boolean login(String username, String password) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username) && users[i].password.equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public static int getUserSize() {
        return userCount;
    }
    
    public static User[] getUsers() {
        return users;
    }
    
     public static void deleteItem(int index) {
    if (index >= 0 && index < userCount) {
        for (int i = index; i < userCount - 1; i++) {
            users[i] = users[i + 1];
        }
        userCount--;
        users[userCount] = null; // Clear the last element
    }
    }
     public static void updateItem(int index, String newUsername, String newPassword) {
        if (index >= 0 && index < userCount) {
            users[index].username = newUsername;
            users[index].password = newPassword;
        }
    }

}
