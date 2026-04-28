import java.sql.Date;
import java.sql.SQLException;

void main() throws SQLException {
    Scanner s = new Scanner(System.in);
    System.out.println("Login System");
    System.out.println("Login: 1");
    System.out.println("Make Account: 2");
    int choice = s.nextInt();
    if (choice == 1){
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
    } else if (choice == 2) {
        System.out.print("Enter Email: ");
        String email = s.next();
        System.out.print("Enter Name: ");
        String name = s.next();
        System.out.print("Enter Last Name: ");
        String lName = s.next();
        System.out.print("Department ID: ");
        int depId = s.nextInt();
        System.out.print("Hire Date (YYYY-MM-DD): ");
        Date hireDate = Date.valueOf(s.next());
        System.out.print("Password: ");
        String password = s.next();
        Auth.MakeUser(email, name, lName, depId, hireDate, password);
    }
}