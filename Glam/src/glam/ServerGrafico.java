package glam;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.mysql.fabric.xmlrpc.base.Data;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ServerGrafico {

	protected Shell shell;
	private Table table;
	private ArrayList<Iscritto> iscritto = new ArrayList<Iscritto>();
	private Database d = new Database();
	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
	/**
	 * Launch the application.
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(71, 55, 201, 155);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNomi = new TableColumn(table, SWT.NONE);
		tblclmnNomi.setWidth(94);
		tblclmnNomi.setText("Nomi");
		
		TableColumn tblclmnData = new TableColumn(table, SWT.NONE);
		tblclmnData.setWidth(100);
		tblclmnData.setText("Data");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				iscritto = d.listaIscritti();
				for(int i=0; i<iscritto.size(); i++){
					TableItem tableItem = new TableItem (table, SWT.NONE);
					tableItem.setText(0, iscritto.get(i).getNickname());
					String temp = df.format(iscritto.get(i).getData());
					tableItem.setText(1, temp);
				}
			}
		});
		btnNewButton.setBounds(10, 10, 75, 25);
		btnNewButton.setText("New Button");
		
		
		

	}
}
