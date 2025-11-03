import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
public class DatabaseManager {
    public static void insertIntoDatabase( String drawNo, String drawDate, String winningLetter, int[] numbers )
    {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load database configuration: " + e.getMessage());
            return;
        }

        String url = props.getProperty("DB_URL");
        String user = props.getProperty("DB_USER");
        String password = props.getProperty("DB_PASSWORD");

        String query = "insert into mahajana_sampatha_results_thashmika (draw_no, draw_date, winning_letter, winning_number_1, winning_number_2, winning_number_3, winning_number_4, winning_number_5, winning_number_6) values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            SimpleDateFormat sdfInput = new SimpleDateFormat("EEEE MMMM dd, yyyy");
            SimpleDateFormat sdfDb = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdfInput.parse(drawDate);
            String formattedDate = sdfDb.format(parsedDate);

            stmt.setString(1, drawNo);
            stmt.setString(2, formattedDate);
            stmt.setString(3, winningLetter);
            for (int i = 0; i < 6; i++) {
                stmt.setInt(4 + i, numbers[i]);
            }

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Lottery results successfully inserted");
            } else {
                System.out.println("Error inserting data");
            }

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
