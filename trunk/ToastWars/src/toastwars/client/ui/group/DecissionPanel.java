package toastwars.client.ui.group;

/*
 * Author: Waldemar Geppart
 * */

import java.util.ArrayList;

import toastwars.client.Controller;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Function;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.event.TabPanelListenerAdapter;
import com.gwtext.client.widgets.form.Checkbox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.CheckboxListenerAdapter;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class DecissionPanel extends Panel {

	private Button					btnSave;
	private Button					btnEnd;
	private NumberField				capital;
	private Checkbox				report;
	private static DecissionPanel	decissionPanel;
	private DecissionForm			decissionFormType1;
	private DecissionForm			decissionFormType2;
	private DecissionForm			decissionFormType3;
	private ArrayList<Toaster>		newToasterList	= new ArrayList<Toaster>();
	private Game					game;

	private Group					group;
	private Sound					sound;

	public static DecissionPanel getInstance() {
		if (decissionPanel == null)
			decissionPanel = new DecissionPanel();
		return decissionPanel;
	}

	private DecissionPanel() {
		game = Controller.getInstance().getGame();
		group = (Group) Controller.getInstance().getUser();

		// load sounds
		String soundURL = "sounds/macht.mp3";
		String soundType = Sound.MIME_TYPE_AUDIO_MPEG;
		if (group.getStatus() == Status.INACTIVE) {
			soundURL = "sounds/hohoho.wav";
			soundType = Sound.MIME_TYPE_AUDIO_X_WAV;
		}

		SoundController soundController = new SoundController();
		sound = soundController.createSound(soundType, soundURL);

		setTitle("Entscheidungen");
		setPaddings(0, 15, 0, 0);
		setSize(985, 400);
		setButtonAlign(Position.CENTER);
		setLayout(new VerticalLayout(5));
		addListener(new TabPanelListenerAdapter() {
			@Override
			public void onActivate(Panel panel) {
				super.onActivate(panel);
				disableSlidersAndButtons();
			}
		});

		if (group.getStatus() == Status.INACTIVE) {
			createBurntPanel();
			return;
		}

		createButtons();

		Panel middlePanel = createMiddlePanel();

		add(createDecissionForms());
		add(middlePanel);
		addButton(btnSave);
		addButton(btnEnd);
	}

	private void createBurntPanel() {
		Panel burntPanel = new Panel();
		burntPanel.setLayout(new VerticalLayout(15));
		burntPanel.setStyle("text-align: center;");
		burntPanel.setWidth(965);

		Panel imagePanel = new Panel();
		imagePanel.setStyle("background: url(images/toast_transparent.gif) "
				+ "width: 300px;");
		imagePanel.setSize(300, 300);
		burntPanel.add(imagePanel);

		Label label = new Label("Sie sind abgebrannt!");
		label.setStyle("font:bold 14px tahoma,arial,verdana,sans-serif;"
				+ "text-align: center;");
		label.setWidth(300);
		burntPanel.add(label);

		add(burntPanel);

		sound.play();
	}

	private TabPanel createDecissionForms() {
		// tab panel for multiple toaster types
		TabPanel tabPanel = new TabPanel();
		tabPanel.setTabPosition(Position.BOTTOM);
		tabPanel.setPaddings(15);
		tabPanel.setSize(965, 310);
		tabPanel.addListener(new TabChangeListener());

		ArrayList<Button> buttons = new ArrayList<Button>();
		buttons.add(btnSave);
		buttons.add(btnEnd);

		// form for type 1
		Toaster toasterType1 = group.getCompany().getToasterList().get(0);
		decissionFormType1 = new DecissionForm(buttons, capital, toasterType1,
				newToasterList);
		decissionFormType1.setTitle(Type.TYPE1.getDescription());
		tabPanel.add(decissionFormType1);

		// form for type 2
		if (group.getCompany().getToasterList().size() > 1) {
			Toaster toasterType2 = group.getCompany().getToasterList().get(1);
			decissionFormType2 = new DecissionForm(buttons, capital,
					toasterType2, newToasterList);
		} else if (game.getCurrentRound() >= 5) {
			decissionFormType2 = new DecissionForm(buttons, capital,
					Type.TYPE2, newToasterList);
		}

		if (decissionFormType2 != null) {
			decissionFormType2.setTitle(Type.TYPE2.getDescription());
			tabPanel.add(decissionFormType2);
		}

		// form for type 3
		if (group.getCompany().getToasterList().size() > 2) {
			Toaster toasterType3 = group.getCompany().getToasterList().get(2);
			decissionFormType3 = new DecissionForm(buttons, capital,
					toasterType3, newToasterList);
		} else if (game.getCurrentRound() >= 10) {
			decissionFormType3 = new DecissionForm(buttons, capital,
					Type.TYPE3, newToasterList);
		}

		if (decissionFormType3 != null) {
			decissionFormType3.setTitle(Type.TYPE3.getDescription());
			tabPanel.add(decissionFormType3);
		}
		return tabPanel;
	}

	private Panel createMiddlePanel() {
		Panel horPanel = new Panel();
		horPanel.setPaddings(15);
		horPanel.setLayout(new HorizontalLayout(30));
		horPanel.setStyle("text-align: center;");
		horPanel.setSize(965, 55);
		horPanel.setBorder(true);

		// add extra report check box
		report = new Checkbox("Marktforschungsbericht");
		report.setHeight(20);
		report.setValue(group.getCompany().isMarketResearchReportON());
		report.addListener(new CheckboxListenerAdapter() {
			@Override
			public void onCheck(Checkbox field, boolean checked) {
				super.onCheck(field, checked);

				if (capital.getValue() == null)
					return;

				double value = capital.getValue().doubleValue();
				if (checked) {
					capital.setValue(value - 5000);
				} else
					capital.setValue(value + 5000);
				capital.fireEvent("change");
			}
		});

		Panel reportForm = new Panel();
		reportForm.setBorder(false);

		reportForm.add(report);

		// add capital field
		capital = new NumberField("Kapital in &euro;", "capital", 80);
		capital.setValue(group.getCompany().getCapital());
		capital.setAllowNegative(false);
		capital.setReadOnly(true);
		capital.setDecimalSeparator(",");
		capital.setStyle("text-align: right");
		capital.addListener("change", new Function() {
			public void execute() {
				double value = capital.getValue().doubleValue();
				if (value < 5000 && !report.getValue())
					report.setDisabled(true);
				else
					report.setDisabled(false);

				if (value == 0)
					capital.setStyle("background: red");
				else
					capital.setStyle("background: white");
			}
		});

		FormPanel capitalForm = new FormPanel();
		capitalForm.setBorder(false);
		capitalForm.setLabelWidth(70);

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
				decissionFormType1.updateToasterData();

				if (decissionFormType2 != null)
					decissionFormType2.updateToasterData();
				if (decissionFormType3 != null)
					decissionFormType3.updateToasterData();

				try {
					if (newToasterList.size() == 0)
						Controller.getInstance().save();
					else
						Controller.getInstance().createNewToaster(
								newToasterList);
				} catch (Exception e1) {
					MessageBox.alert("Speichern fehlgeschlagen!");
				}
			}
		});

		btnEnd = new Button("Runde abschlie&szlig;en",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						super.onClick(button, e);

						sound.play();

						setNewGroupData();
						group.setStatus(Status.COMPLETED);
						decissionFormType1.updateToasterData();
						if (decissionFormType2 != null)
							decissionFormType2.updateToasterData();
						if (decissionFormType3 != null)
							decissionFormType3.updateToasterData();

						try {
							if (newToasterList.size() == 0)
								Controller.getInstance().save();
							else
								Controller.getInstance().createNewToaster(
										newToasterList);
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
			group.setStarted();
			MessageBox.alert("Speichern fehlgeschlagen!");
		}
	}

	private void disableSlidersAndButtons() {
		if (group.getStatus() == Status.COMPLETED) {
			btnSave.setDisabled(true);
			btnEnd.setDisabled(true);
			report.setDisabled(true);
			decissionFormType1.disableSliders();
			decissionFormType2.disableSliders();
			decissionFormType3.disableSliders();
		}
	}

	public boolean isNewToaster(Type t) {
		for (Toaster newToaster : newToasterList) {
			if (t == newToaster.getType())
				return true;
		}
		return false;
	}
}
