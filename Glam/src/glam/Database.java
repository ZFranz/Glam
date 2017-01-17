package glam;

import java.sql.*;
import java.util.ArrayList;

import org.eclipse.swt.widgets.MessageBox;

public class Database {
	public Boolean Iscritto(String nickname) {
		ArrayList<Iscritto> elenco = new ArrayList<Iscritto>();
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/glam?user=root&password=");

			// ________________________________query
			sql = "SELECT * FROM prenotazione;";
			System.out.println(sql); // stampa la query

			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			rs = st.executeQuery(sql); // faccio la query su uno statement
			while (rs.next() == true) {
				Iscritto i = new Iscritto(rs.getString("nickname"), rs.getTimestamp("data"));
				System.out.println(i);
				elenco.add(i);
			}

			cn.close(); // chiusura connessione
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
			e.printStackTrace();
		} // fine try-catch
		
		for(int i = 0; i< elenco.size(); i++) {
			if(elenco.get(i).getNickname().equals(nickname)) {
				return false;
			}
		}

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
		
		return true;
	}
	
	public ArrayList<Iscritto> listaIscritti() {
		ArrayList<Iscritto> elenco = new ArrayList<Iscritto>();
		Connection cn;
		Statement st;
		ResultSet rs;
		String sql;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		} // fine try-catch

		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/glam?user=root&password=");

			// ________________________________query
			sql = "SELECT * FROM prenotazione;";
			System.out.println(sql); // stampa la query

			st = cn.createStatement(); // creo sempre uno statement sulla
										// connessione
			rs = st.executeQuery(sql); // faccio la query su uno statement
			while (rs.next() == true) {
				Iscritto i = new Iscritto(rs.getString("nickname"), rs.getTimestamp("data"));
				System.out.println(i);
				elenco.add(i);
			}

			cn.close(); // chiusura connessione
		} catch (SQLException e) {
			System.out.println("errore:" + e.getMessage());
			e.printStackTrace();
		} // fine try-catch

		return elenco;
	}
}
