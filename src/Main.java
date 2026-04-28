import java.sql.SQLException;

void main() throws SQLException {
    Scanner s = new Scanner(System.in);
    System.out.println("Login System");
    System.out.print("Email: ");
    String email = s.next();
    System.out.print("Password: ");
    String password = s.next();

    Auth.User user = Auth.login(email, password);

    if (user != null){
        System.out.println("Login Successful!");
        System.out.println(user.details());
    }else {
        System.out.println("U HACKER GO AWAY NOW");
    }
}