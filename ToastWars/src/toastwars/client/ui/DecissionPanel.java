package toastwars.client.ui;

import toastwars.client.Controller;
import toastwars.client.sliders.SliderBar;
import toastwars.client.sliders.SliderBar.LabelFormatter;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.layout.HorizontalLayout;

public class DecissionPanel extends Panel {

	private NumberField				price;
	private NumberField				marketing;
	private NumberField				research;
	private Button					btnSave;
	private Button					btnEnd;
	private static DecissionPanel	decissionPanel;

	private DecissionPanel() {
		setTitle("Entscheidungen");
		setPaddings(5);
		setSize(580, 290);

		Panel horizontalPanel = new Panel();
		horizontalPanel.setLayout(new HorizontalLayout(0));
		horizontalPanel.setPaddings(0);

		// Formular
		FormPanel form = new FormPanel();
		form.setWidth(250);
		// form.setBorder(false);
		form.setPaddings(30, 15, 0, 0);
		form.setLabelWidth(160);

		price = createNumberField("Preis in &euro;", "Preis", 55, 15, 300, true);
		marketing = createNumberField("Werbung", "Werbung", 55, 1, 100000,
				false);
		research = createNumberField("Forschung und Entwicklung", "FE", 55, 1,
				100000, false);

		form.add(price);
		form.add(marketing);
		form.add(research);

		btnSave = new Button("Speichern", new ButtonListenerAdapter() {
			public void onClick(Button button, EventObject e) {
				super.onClick(button, e);
				Controller.getInstance().save();
				MessageBox.alert("Daten speichern");
			}
		});
		form.addButton(btnSave);

		btnEnd = new Button("Runde abschlie&szlig;en",
				new ButtonListenerAdapter() {
					public void onClick(Button button, EventObject e) {
						super.onClick(button, e);
						MessageBox.alert("Runde abschlie&szlig;en");
					}
				});
		form.addButton(btnEnd);

		// Slider
		Panel sliderPanel = new Panel();
		sliderPanel.setSize(320, 200);
		sliderPanel.setStyle("vertical-align: top;");
		sliderPanel.setMargins(0, 0, 0, 0);
		// sliderPanel.setLayout(new VerticalLayout(0));
		// sliderPanel.setBorder(false);

		SliderBar slider = new SliderBar(0, 60);
		slider.setHeight("55");
		slider.setStepSize(1);
		slider.setCurrentValue(15);
		slider.setNumTicks(12);
		slider.setNumLabels(4);
		slider.setLabelFormatter(new LabelFormatter() {
			public String formatLabel(SliderBar slider, double value) {
				return (int) value + "";
			}
		});

		SliderBar slider2 = new SliderBar(0, 100000);
		slider2.setStepSize(10000);
		slider2.setCurrentValue(0);
		slider2.setNumTicks(10);
		slider2.setNumLabels(5);
		slider2.setLabelFormatter(new LabelFormatter() {
			public String formatLabel(SliderBar slider, double value) {
				return (int) value / 1000 + "k";
			}
		});

		sliderPanel.add(slider);
		sliderPanel.add(slider2);

		horizontalPanel.add(form);
		horizontalPanel.add(sliderPanel);

		add(horizontalPanel);
	}

	public static DecissionPanel getInstance() {
		if (decissionPanel == null)
			decissionPanel = new DecissionPanel();
		return decissionPanel;
	}

	private NumberField createNumberField(String text, String name, int width,
			int value, int maxvalue, boolean decimal) {
		NumberField numField = new NumberField(text, name, width, value);
		numField.setLabelStyle("height:50");
		numField.setAllowNegative(false);
		numField.setMaxValue(maxvalue);
		numField.setMaxText("Der maximale Wert f&uuml;r dieses Feld ist: "
				+ maxvalue);
		numField.setNanText("Ung&uuml;ltige Zahl");

		numField.setDecimalPrecision(2);
		numField.setDecimalSeparator(",");
		numField.setAllowDecimals(decimal);

		numField.setStyle("text-align:right");

		numField.addListener(new FieldListenerAdapter() {
			public void onInvalid(Field field, String msg) {
				super.onInvalid(field, msg);
				btnSave.disable();
				btnEnd.disable();
			}

			public void onValid(Field field) {
				super.onValid(field);
				if (price.isValid() && research.isValid()
						&& marketing.isValid()) {
					btnSave.enable();
					btnEnd.enable();
				}
			}
		});

		return numField;
	}
}
