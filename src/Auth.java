`
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

// Requires: org.mindrot:jbcrypt:0.4 in your build file

public class Auth {

    public static class User {
        private final String editedEmail;
        private final String first_name;
        private final String last_name;
        private final int department_id;
        private final String hired_date;

        public User(String editedEmail, String first_name, String last_name, int department_id, String hired_date) {
            this.editedEmail = editedEmail;
            this.first_name = first_name;
            this.last_name = last_name;
            this.department_id = department_id;
            this.hired_date = hired_date;
        }

        public String getEditedEmail() { return editedEmail; }
        public String getFirst_name() { return first_name; }
        public String getLast_name() { return last_name; }
        public int getDepartment_id() { return department_id; }
        public String getHired_date() { return hired_date; }

        public String details(){
            return "Email: " + editedEmail + "\nFirst Name: " + first_name + "\nLast Name: " + last_name + "\nDepartment: " + department_id + "\nHire Date: " + hired_date;
        }
    }

    public static User login(String editedEmail, String plainPassword) throws SQLException {
        String sql = "SELECT `id`, `editedEmail`, `first_name`, `last_name`, `department_id`, `hired_date`, `password` FROM `company`.`editedemployees` WHERE `editedEmail` = ? LIMIT 1";
        try (Connection conn = DriverManager.getConnection(Creds.getURL(), Creds.getUSER(), Creds.getPASS());
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, editedEmail);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;
            String hash = rs.getString("password");
            if (!BCrypt.checkpw(plainPassword, hash)) return null;
            return new User(
                    rs.getString("editedEmail"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("department_id"),
                    rs.getString("hired_date")
            );
        }
    }

    public static void MakeUser(String email, String name, String lastName, int departmentId, Date hireDate, String password) throws SQLException {
        String query = "INSERT INTO `company`.`editedemployees` (`editedEmail`, `first_name`, `last_name`, `department_id`, `hired_date`, `password`) VALUES (?, ?, ?, ?, ?, ?)";
        String hashedPassword = hashPassword(password);
        try (Connection conn = DriverManager.getConnection(Creds.getURL(), Creds.getUSER(), Creds.getPASS()); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setString(3, lastName);
            ps.setInt(4, departmentId);
            ps.setDate(5, hireDate);
            ps.setString(6, hashedPassword);
            ps.executeUpdate();
        }
    }

    public static String hashPassword(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt(12));
    }
}

