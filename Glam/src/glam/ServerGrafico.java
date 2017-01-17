package glam;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ServerGrafico {

	protected Shell shlServer;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerGrafico window = new ServerGrafico();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlServer.open();
		shlServer.layout();
		while (!shlServer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlServer = new Shell();
		shlServer.setSize(450, 300);
		shlServer.setText("Server");

		Button btnAvviaServer = new Button(shlServer, SWT.NONE);
		text = new Text(shlServer, SWT.BORDER);

		btnAvviaServer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				
				String temp = "";
				
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
						text.setText(temp);
						// invio il numero aumentato di uno
						/*PrintWriter out = new PrintWriter(s.getOutputStream(), true);
						out.println("ciao dal serverone!");*/
						s.close();
						// riparta
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAvviaServer.setBounds(10, 10, 75, 25);
		btnAvviaServer.setText("Avvia Server");

		text.setBounds(10, 41, 76, 21);

	}
}
