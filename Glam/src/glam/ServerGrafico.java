package glam;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.mysql.fabric.xmlrpc.base.Data;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

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
		shlPrenotati.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		shlPrenotati.setSize(380, 300);
		shlPrenotati.setText("Prenotati");
		
		Label lblOraFinale = new Label(shlPrenotati, SWT.NONE);
		lblOraFinale.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 9, SWT.NORMAL));
		lblOraFinale.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblOraFinale.setBounds(188, 79, 80, 15);
		lblOraFinale.setText("Ora Finale");
		
		Label lblOraIniziale = new Label(shlPrenotati, SWT.NONE);
		lblOraIniziale.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 9, SWT.NORMAL));
		lblOraIniziale.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblOraIniziale.setBounds(10, 80, 86, 15);
		lblOraIniziale.setText("Ora Iniziale");
		
		DateTime oraI = new DateTime(shlPrenotati, SWT.BORDER | SWT.TIME);
		oraI.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		oraI.setBounds(102, 71, 80, 24);
		
		DateTime oraF = new DateTime(shlPrenotati, SWT.BORDER | SWT.TIME);
		oraF.setBounds(274, 71, 80, 24);
		
		Label lblDataI = new Label(shlPrenotati, SWT.NONE);
		lblDataI.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 9, SWT.NORMAL));
		lblDataI.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDataI.setBounds(10, 46, 86, 15);
		lblDataI.setText("Data Iniziale");
		
		DateTime dateI = new DateTime(shlPrenotati, SWT.BORDER);
		dateI.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		dateI.setBounds(102, 41, 80, 24);
		
		Label lblDataF = new Label(shlPrenotati, SWT.NONE);
		lblDataF.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 9, SWT.NORMAL));
		lblDataF.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDataF.setBounds(188, 46, 80, 15);
		lblDataF.setText("Data Finale");
		
		DateTime dateF = new DateTime(shlPrenotati, SWT.BORDER);
		dateF.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		dateF.setBounds(274, 41, 80, 24);
		
		table = new Table(shlPrenotati, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 9, SWT.NORMAL));
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		table.setBounds(10, 108, 225, 126);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNomi = new TableColumn(table, SWT.NONE);
		tblclmnNomi.setWidth(94);
		tblclmnNomi.setText("Nomi");
		
		TableColumn tblclmnData = new TableColumn(table, SWT.NONE);
		tblclmnData.setWidth(125);
		tblclmnData.setText("Data");
		
		Button btnVisualizza = new Button(shlPrenotati, SWT.NONE);
		btnVisualizza.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 9, SWT.NORMAL));
		btnVisualizza.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				iscritto = d.listaIscritti();
				table.removeAll();
				for(int i=0; i<iscritto.size(); i++){
					TableItem tableItem = new TableItem (table, SWT.NONE);
					tableItem.setText(0, iscritto.get(i).getNickname());
					String temp = df.format(iscritto.get(i).getData());
					tableItem.setText(1, temp);
				}
			}
		});
		btnVisualizza.setBounds(10, 10, 164, 25);
		btnVisualizza.setText("Visualizza prenotati");
		
		Button btnVisualizzaConFiltri = new Button(shlPrenotati, SWT.NONE);
		btnVisualizzaConFiltri.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 9, SWT.NORMAL));
		btnVisualizzaConFiltri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SimpleDateFormat dfg = new SimpleDateFormat("yyyy/MM/dd");
				SimpleDateFormat dfo = new SimpleDateFormat("HH:mm:ss");
				String ora1 = "";
				String ora2 = "";
				String giorno1 = "";
				String giorno2 = "";
				Date temp1 = null;
				Date temp2 = null;
				
				ora1 = oraI.getHours() + ":" + oraI.getMinutes() + ":" + oraI.getSeconds();
				ora2 = oraF.getHours() + ":" + oraF.getMinutes() + ":" + oraF.getSeconds();
				giorno1 = dateI.getYear()+ "/" + (dateI.getMonth() + 1) + "/" + dateI.getDay();
				giorno2 = dateF.getYear()+ "/" + (dateF.getMonth() + 1)+ "/" + dateF.getDay();
				try {
					temp1 = dfo.parse(ora1);
					temp2 = dfo.parse(ora2);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ora1 = dfo.format(temp1);
				ora2 = dfo.format(temp2);
				
				try {
					temp1 = dfg.parse(giorno1);
					temp2 = dfg.parse(giorno2);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				giorno1 = dfg.format(temp1);
				giorno2 = dfg.format(temp2);
				
				iscritto = d.listaIscrittiFiltro(giorno1, giorno2, ora1, ora2);
				table.removeAll();
				for(int i=0; i<iscritto.size(); i++){
					TableItem tableItem = new TableItem (table, SWT.NONE);
					tableItem.setText(0, iscritto.get(i).getNickname());
					String temp = df.format(iscritto.get(i).getData());
					tableItem.setText(1, temp);
				}
			}
		});
		btnVisualizzaConFiltri.setBounds(190, 10, 164, 25);
		btnVisualizzaConFiltri.setText("Visualizza con filtri");
	}
}
