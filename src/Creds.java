public class Creds {
    private static String USER = "root";
    private static String PASS = "Gohabs03";
    private static String URL = "jdbc:mysql://localhost:3306/company";

    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String USER) {
        Creds.USER = USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static void setPASS(String PASS) {
        Creds.PASS = PASS;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        Creds.URL = URL;
    }
}
