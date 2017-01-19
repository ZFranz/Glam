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

	protected Shell shlPrenotati;
	private Table table;
	private ArrayList<Iscritto> iscritto = new ArrayList<Iscritto>();
	private Database d = new Database();
	DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
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
		shlPrenotati.open();
		shlPrenotati.layout();
		while (!shlPrenotati.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlPrenotati = new Shell();
		shlPrenotati.setSize(260, 300);
		shlPrenotati.setText("Prenotati");
		
		table = new Table(shlPrenotati, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 41, 225, 155);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNomi = new TableColumn(table, SWT.NONE);
		tblclmnNomi.setWidth(94);
		tblclmnNomi.setText("Nomi");
		
		TableColumn tblclmnData = new TableColumn(table, SWT.NONE);
		tblclmnData.setWidth(125);
		tblclmnData.setText("Data");
		
		Button btnVisualizza = new Button(shlPrenotati, SWT.NONE);
		btnVisualizza.addSelectionListener(new SelectionAdapter() {
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
		btnVisualizza.setBounds(10, 10, 225, 25);
		btnVisualizza.setText("Visualizza prenotati");
		
		
		

	}
}
