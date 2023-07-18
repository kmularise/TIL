package hash;


import java.util.HashMap;
import java.util.Objects;

public class User {

    private long id;
    private String name;
    private String email;

    // standard getters/setters/constructors


    public User(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

//    @Override
//    public int hashCode() {
//        return 1;
//    }


//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, email);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null) return false;
//        if (this.getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return id == user.id;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    public static void main(String[] args) {
        HashMap<User, Long> dict = new HashMap<>();
        User user = new User(1, "test", "test");
        dict.put(user, 0L);
        int userHash3 = user.hashCode();
        System.out.println("userHash3 = " + userHash3);
        User user2 = new User(1, "test2", "test");
        System.out.println("user.equals(user2) = " + user.equals(user2));
        dict.put(user2, 1L);
        System.out.println("dict.size() = " + dict.size());
        int userHash = user.hashCode();
        int userHash2 = user2.hashCode();
        System.out.println("userHash = " + userHash);
        System.out.println("userHash2 = " + userHash2);
    }

    // getters and setters here
}
