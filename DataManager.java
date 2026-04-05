import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.HashMap;
import java.util.Map;

public class DataManager {

    private final Firestore db;

    public DataManager() {
        this.db = FirebaseInitializer.getFirestore();
    }

    public boolean validateLogin(String email, String password) {
        try {
            ApiFuture<QuerySnapshot> query = db.collection("users")
                    .whereEqualTo("userMail", email)
                    .get();

            QuerySnapshot querySnapshot = query.get();

            if (querySnapshot.isEmpty()) {
                System.out.println("No user found with email: " + email);
                return false;
            }

            DocumentSnapshot document = querySnapshot.getDocuments().get(0);
            String storedPassword = document.getString("userPassword");

            System.out.println("User found: " + document.getData());

            return storedPassword != null && storedPassword.equals(password);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userExists(String email) {
        try {
            ApiFuture<QuerySnapshot> query = db.collection("users")
                    .whereEqualTo("userMail", email)
                    .get();

            return !query.get().isEmpty();

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean createUser(String userName, String userPassword, String userMail) {
        try {
            if (userExists(userMail)) {
                System.out.println("User already exists.");
                return false;
            }

            Map<String, Object> userData = new HashMap<>();
            userData.put("userName", userName);
            userData.put("userPassword", userPassword);
            userData.put("userMail", userMail);

            db.collection("users").add(userData).get();

            System.out.println("User created successfully.");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByEmail(String email) {
        try {
            ApiFuture<QuerySnapshot> query = db.collection("users")
                    .whereEqualTo("userMail", email)
                    .get();

            QuerySnapshot querySnapshot = query.get();

            if (querySnapshot.isEmpty()) {
                return null;
            }

            DocumentSnapshot document = querySnapshot.getDocuments().get(0);

            String userName = document.getString("userName");
            String userPassword = document.getString("userPassword");
            String userMail = document.getString("userMail");

            return new User(userName, userPassword, userMail);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printAllUsers() {
        try {
            ApiFuture<QuerySnapshot> future = db.collection("users").get();
            QuerySnapshot snapshot = future.get();

            for (DocumentSnapshot doc : snapshot.getDocuments()) {
                System.out.println(doc.getData());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}