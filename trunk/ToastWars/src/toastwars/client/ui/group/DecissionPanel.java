package toastwars.client.ui.group;

import toastwars.client.Controller;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;

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
	private Checkbox				report;
	private static DecissionPanel	decissionPanel;
	private DecissionForm			decissionForm;

	private Group					group;

	public static DecissionPanel getInstance() {
		if (decissionPanel == null)
			decissionPanel = new DecissionPanel();
		return decissionPanel;
	}

	private DecissionPanel() {
		group = (Group) Controller.getInstance().getUser();

		setTitle("Entscheidungen");
		setPaddings(15, 30, 0, 0);
		setSize(985, 400);
		setButtonAlign(Position.CENTER);
		setLayout(new VerticalLayout(5));

		createButtons();

		Panel middlePanel = createMiddlePanel();

		// Formular
		decissionForm = new DecissionForm(new Button[] { btnSave, btnEnd },
				capital);

		// tab panel for multiple toaster types
		// form.setTitle("Typ 1");
		// TabPanel tabPanel = new TabPanel();
		// tabPanel.setTabPosition(Position.BOTTOM);
		// tabPanel.add(form);
		// tabPanel.activate(0);

		add(decissionForm);
		add(middlePanel);
		addButton(btnSave);
		addButton(btnEnd);

		disableSlidersAndButtons();
	}

	private Panel createMiddlePanel() {
		Panel horPanel = new Panel();
		horPanel.setPaddings(15);
		horPanel.setLayout(new HorizontalLayout(30));
		horPanel.setStyle("text-align: center;");
		horPanel.setSize(940, 55);
		horPanel.setBorder(true);

		// add capital field
		capital = new NumberField("Kapital", "capital", 70);
		capital.setValue(group.getCompany().getCapital());
		capital.setAllowNegative(false);
		capital.setReadOnly(true);
		capital.setDecimalSeparator(",");
		capital.setStyle("text-align: right");

		report = new Checkbox("Marktforschungsbericht");
		report.setHeight(20);
		report.setValue(group.getCompany().isMarketResearchReportON());

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

	private void createButtons() {
		btnSave = new Button("Speichern", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				super.onClick(button, e);

				setNewGroupData();
				group.setStatus(Status.EDITED);
				decissionForm.updateToasterData();

				try {
					Controller.getInstance().save();
				} catch (Exception e1) {
					MessageBox.alert("Speichern fehlgeschlagen!");
				}
			}
		});

		btnEnd = new Button("Runde abschlie&szlig;en",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						super.onClick(button, e);

						setNewGroupData();
						group.setStatus(Status.COMPLETED);
						decissionForm.updateToasterData();

						try {
							Controller.getInstance().save();
						} catch (Exception e1) {
							MessageBox
									.alert("Runde konnte nicht abgeschlossen werden!");
						}
					}
				});
	}

	private void setNewGroupData() {
		group.getCompany().setMarketResearchReportON(report.getValue());
		group.getCompany().setCapital(capital.getValue().doubleValue());
	}

	public void createUserMessage(boolean success) {
		if (success) {
			if (group.getStatus() == Status.EDITED)
				MessageBox.alert("Daten gespeichert!");
			else if (group.getStatus() == Status.COMPLETED) {
				MessageBox.alert("Runde abgeschlossen!");
				disableSlidersAndButtons();
			}

		} else {
			group.setStatus(Status.STARTED);
			MessageBox.alert("Speichern fehlgeschlagen!");
		}
	}

	private void disableSlidersAndButtons() {
		if (group.getStatus() == Status.COMPLETED) {
			btnSave.setDisabled(true);
			btnEnd.setDisabled(true);
			report.setReadOnly(true);
			decissionForm.disableSliders();
		}
	}
}
