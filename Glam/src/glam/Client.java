package glam;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.wb.swt.SWTResourceManager;

public class Client {

	protected Shell shlRegistarti;
	private Text text;
	private Label lblNickname;
	private Label lblResult;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Client window = new Client();
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
		shlRegistarti.open();
		shlRegistarti.layout();
		while (!shlRegistarti.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlRegistarti = new Shell();
		shlRegistarti.setSize(400, 380);
		shlRegistarti.setText("Registati");

		text = new Text(shlRegistarti, SWT.BORDER);
		text.setBounds(84, 10, 209, 21);
		
		lblNickname = new Label(shlRegistarti, SWT.NONE);
		lblNickname.setBounds(10, 13, 68, 15);
		lblNickname.setText("Nickname:");
		
		Label lblImage = new Label(shlRegistarti, SWT.NONE);
		lblImage.setImage(SWTResourceManager.getImage("img\\papagayo.jpeg"));
		lblImage.setBounds(10, 92, 364, 240);
		
		lblResult = new Label(shlRegistarti, SWT.NONE);
		lblResult.setBounds(10, 47, 364, 15);

		Button btnNewButton = new Button(shlRegistarti, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Invia dati al server
				try {
					Socket s = new Socket("localhost", 9999);
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);
					out.println(text.getText());

					// riceva del testo
					InputStreamReader isr = new InputStreamReader(s.getInputStream());
					BufferedReader in = new BufferedReader(isr);
					String temp = in.readLine();
					System.out.println("Il client riceve: " + temp);
					lblResult.setText(temp);

					s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(299, 8, 75, 25);
		btnNewButton.setText("Invia");
		
		

	}
}
