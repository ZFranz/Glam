package glam;

import java.sql.*;

public class Database {
	public void Iscritto(String nome, Date data) {
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
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsharing?user=root&password=");

			// ________________________________query
			sql = "INSERT INTO noleggi (auto, socio, inizio, fine, auto_in_uso, auto_restituita) VALUES ('" + targa
					+ "', '" + cf + "', '" + dataInizio + "', '" + dataFine + "', 0, 0);";
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
