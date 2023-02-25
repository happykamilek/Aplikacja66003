import java.sql.*;
import java.util.Scanner;

public class Main {
        static final String DB_URL = "jdbc:mysql://localhost:3306/";
        static final String DB_NAME = "aplikacja";
        static final String DB_USER = "root";
        static final String DB_PASSWORD = "";

        public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

                // Pytanie uzytkownika o haslo
                System.out.println("Login:");
                String login = scanner.nextLine();
                System.out.println("Hasło:");
                String passwordtwo = scanner.nextLine();

                // Sprawdzanie czy jest poprawnie
                if (login.equals("admin") && passwordtwo.equals("qwerty")) {
                        System.out.println("Zalogowano");
                } else {
                        System.out.println("Nieprawidłowe hasło");
                        return;
                }

                Connection conn = null;
                Statement stmt = null;
                try {
                        // sterownik
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // ustawiamy parametry połączenia
                        String url = "jdbc:mysql://localhost:3306/aplikacja?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                        String user = "root";
                        String password = "";

                        // nawiązujemy połączenie z bazą danych
                        conn = DriverManager.getConnection(url, user, password);
                        System.out.println("Połączenie z bazą");

                        int option = 0;
                        while (option != 5) {
                                // Prompt user for input
                                System.out.println("Wybierz opcje: \n1. Dodaj pojazd \n2. Wyswietl pojazd\n3. Usun pojazd\n4. Modyfikuj pojazd\n5. Wyjscie z programu");
                                option = scanner.nextInt();
                                switch (option) {
                                        case 1:
                                                // Wpisywanie
                                                scanner.nextLine();

                                                System.out.println("Marka:");
                                                String marka = scanner.nextLine();
                                                System.out.println("Model:");
                                                String model = scanner.nextLine();
                                                System.out.println("Nazwisko kierowcy:");
                                                String Nazwiskokierowcy = scanner.nextLine();
                                                System.out.println("Miejsce docelowe:");
                                                String Miejscedocelowe = scanner.nextLine();
                                                System.out.println("Miejsce aktualne:");
                                                String Miejsceaktualne = scanner.nextLine();
                                                System.out.println("Rok Produkcji:");
                                                int rokProdukcji = scanner.nextInt();
                                                System.out.println("Przebieg:");
                                                int Przebieg = scanner.nextInt();
                                                System.out.println("Numer tablicy:");
                                                int ID = scanner.nextInt();


                                                stmt = conn.createStatement();
                                                String sql = "INSERT INTO Pojazd (ID, marka, model, rokProdukcji, Przebieg, Miejscedocelowe, Miejsceaktualne, Nazwiskokierowcy) " +
                                                        "VALUES ( '" + ID + "','" + marka + "', '" + model + "',' " + rokProdukcji + "','" + Przebieg + "','" + Miejscedocelowe + "','" + Miejsceaktualne + "','" + Nazwiskokierowcy + "')";

                                                stmt.executeUpdate(sql);
                                                System.out.println("Wszystko dobrze");
                                                break;

                                        case 2:
                                                // Wyswietlanie rekordow
                                                stmt = conn.createStatement();
                                                sql = "SELECT * FROM Pojazd";
                                                ResultSet rs = stmt.executeQuery(sql);
                                                System.out.printf("%-10s%-15s%-15s%-15s%-15s%-20s%-20s%-20s\n", "ID", "Marka", "Model", "Rok produkcji", "Przebieg", "Miejsce docelowe", "Miejsce aktualne", "Nazwisko kierowcy");
                                                while (rs.next()) {
                                                        ID = rs.getInt("ID");
                                                        marka = rs.getString("marka");
                                                        model = rs.getString("model");
                                                        rokProdukcji = rs.getInt("RokProdukcji");
                                                        Przebieg = rs.getInt("Przebieg");
                                                        Miejscedocelowe = rs.getString("Miejscedocelowe");
                                                        Miejsceaktualne = rs.getString("Miejsceaktualne");
                                                        Nazwiskokierowcy = rs.getString("Nazwiskokierowcy");
                                                        System.out.printf("%-10d%-15s%-15s%-15d%-15d%-20s%-20s%-20s\n", ID, marka, model, rokProdukcji, Przebieg, Miejscedocelowe, Miejsceaktualne, Nazwiskokierowcy);
                                                }

                                                break;

                                        case 3:
                                                // Delete data from Pojazd table
                                                System.out.println("Podaj ID tablicę rejstracyjna");
                                                int idToDelete = scanner.nextInt();

                                                stmt = conn.createStatement();
                                                sql = "DELETE FROM Pojazd WHERE id=" + idToDelete;
                                                stmt.executeUpdate(sql);
                                                System.out.println("Wszystko dobrze");
                                                break;

                                        case 4:
                                                // Update Pojazd
                                                scanner.nextLine();

                                                System.out.println("Podaj tablicę rejstracyjna pojazdu do modyfikacji");
                                                int idToModify = scanner.nextInt();

                                                System.out.println("Wybierz pole, które chcesz zmodyfikować: \n1. Marka \n2. Model \n3. Nazwisko kierowcy \n4. Miejsce docelowe \n5. Miejsce aktualne \n6. Rok Produkcji \n7. Przebieg");
                                                int fieldToModify = scanner.nextInt();

                                                String columnName = null;
                                                switch (fieldToModify) {
                                                        case 1:
                                                                columnName = "marka";
                                                                break;
                                                        case 2:
                                                                columnName = "model";
                                                                break;
                                                        case 3:
                                                                columnName = "Nazwiskokierowcy";
                                                                break;
                                                        case 4:
                                                                columnName = "Miejscedocelowe";
                                                                break;
                                                        case 5:
                                                                columnName = "Miejsceaktualne";
                                                                break;
                                                        case 6:
                                                                columnName = "rokProdukcji";
                                                                break;
                                                        case 7:
                                                                columnName = "Przebieg";
                                                                break;
                                                        default:
                                                                System.out.println("Nieprawidłowa opcja");
                                                                return;
                                                }

                                                System.out.println("Podaj nową wartość");
                                                scanner.nextLine();
                                                String newValue = scanner.nextLine();

                                                stmt = conn.createStatement();
                                                String sqll = "UPDATE Pojazd SET " + columnName + "='" + newValue + "' WHERE id=" + idToModify;

                                                stmt.executeUpdate(sqll);
                                                System.out.println("Wszystko dobrze");
                                                break;

                                        case 5:
                                                System.out.println("Wyjscie z programu, do widzenia");
                                                System.exit(0); // funkcja kończy działanie programu
                                                break;


                                        default:
                                                System.out.println("Cos poszlo nie tak");
                                                break;
                                }
                        }
                        //polaczenie z baza danych
                } catch (SQLException se) {
                        se.printStackTrace();
                } catch (Exception e) {
                        e.printStackTrace();
                } finally {
                        try {
                                if (stmt != null)
                                        conn.close();
                        } catch (SQLException se) {
                        } // do nothing
                        try {
                                if (conn != null)
                                        conn.close();
                        } catch (SQLException se) {
                                se.printStackTrace();
                        }
                }
                System.out.println("Wyjscie z programu");
        }
}