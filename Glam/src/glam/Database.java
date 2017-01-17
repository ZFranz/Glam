package glam;

import java.sql.*;

public class Database {
	public void Iscritto(String nickname) {
		Connection cn;
		Statement st;
		String sql;

		// ________________________________connessione
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		try {
			// Creo la connessione al database
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/glam?user=root&password=");

			// ________________________________query
			sql = "INSERT INTO prenotazione (nickname) VALUES ('" + nickname + "');";
			System.out.println(sql);

			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			st.execute(sql); // faccio la query su uno statement
			cn.close(); // chiusura connessione
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
		} // fine try-catch
	}
}
