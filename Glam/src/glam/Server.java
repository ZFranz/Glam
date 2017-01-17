package glam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.eclipse.swt.widgets.MessageBox;

public class Server {
	public static void main(String[] args) {
		Database d = new Database();
		String temp = "";
		Boolean controllo = true;

		// Avvio il server
		ServerSocket ss;
		try {
			ss = new ServerSocket(9999);
			System.out.println("Server up and running.");
			while (true) {
				Socket s = ss.accept(); // Accetta la connessione
				InputStreamReader isr = new InputStreamReader(s.getInputStream());
				BufferedReader in = new BufferedReader(isr);
				temp = in.readLine();
				System.out.println("Il server riceve: " + temp);
				controllo = d.Iscritto(temp);
				if(controllo) {
					System.out.println("Prenotazione effettuata con successo.");
				} else {
					System.out.println("Nickname già presente.");
				}
				s.close();
				// riparta
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}