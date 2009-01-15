package toastwars.client.ui;

import toastwars.client.Controller;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class DecissionPanel extends Panel {

	private Button					btnSave;
	private Button					btnEnd;
	private NumberField				capital;
	private static DecissionPanel	decissionPanel;

	private DecissionPanel() {
		setTitle("Entscheidungen");
		setPaddings(15, 30, 0, 0);
		setSize(985, 400);
		setButtonAlign(Position.CENTER);
		setLayout(new VerticalLayout(5));

		createButtons();

		Panel middlePanel = createMiddlePanel();

		// Formular
		DecissionForm form = new DecissionForm(
				new Button[] { btnSave, btnEnd }, capital);

		// tab panel for multiple toaster types
		// form.setTitle("Typ 1");
		// TabPanel tabPanel = new TabPanel();
		// tabPanel.setTabPosition(Position.BOTTOM);
		// tabPanel.add(form);
		// tabPanel.activate(0);

		add(form);
		add(middlePanel);
		addButton(btnSave);
		addButton(btnEnd);
	}

	private Panel createMiddlePanel() {
		Panel horPanel = new Panel();
		horPanel.setPaddings(15);
		horPanel.setLayout(new HorizontalLayout(30));
		horPanel.setStyle("text-align: center;");
		horPanel.setSize(940, 55);
		horPanel.setBorder(true);

		// add capital field
		capital = new NumberField("Kapital", "capital", 70, 100000);
		capital.setAllowNegative(false);
		capital.setReadOnly(true);
		capital.setDecimalSeparator(",");
		capital.setStyle("text-align: right");

		Checkbox report = new Checkbox("Marktforschungsbericht");
		report.setHeight(20);

		Panel reportForm = new Panel();
		reportForm.setBorder(false);

		FormPanel capitalForm = new FormPanel();
		capitalForm.setBorder(false);
		capitalForm.setLabelWidth(50);

		reportForm.add(report);
		capitalForm.add(capital);

		horPanel.add(reportForm);
		horPanel.add(capitalForm);

		return horPanel;
	}

	public static DecissionPanel getInstance() {
		if (decissionPanel == null)
			decissionPanel = new DecissionPanel();
		return decissionPanel;
	}

	private void createButtons() {
		btnSave = new Button("Speichern", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				super.onClick(button, e);
				try {
					Controller.getInstance().save();
				} catch (Exception e1) {
					MessageBox.alert("Speichern fehlgeschlagen!");
				}
				MessageBox.alert("Daten gespeichert!");
			}
		});

		btnEnd = new Button("Runde abschlie&szlig;en",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						super.onClick(button, e);
						MessageBox.alert("Runde abschlie&szlig;en");
					}
				});
	}
}
