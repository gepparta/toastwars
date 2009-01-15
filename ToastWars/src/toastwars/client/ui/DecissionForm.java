package toastwars.client.ui;

import java.util.ArrayList;

import toastwars.client.slider.SliderBar;
import toastwars.client.slider.SliderChangeListener;
import toastwars.client.slider.SliderLabelFormatter;

import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

public class DecissionForm extends Panel {

	private Button[]				buttons;
	private ArrayList<NumberField>	fields	= new ArrayList<NumberField>();
	private NumberField				capital;

	public DecissionForm(Button[] buttons, NumberField capital) {
		this.buttons = buttons;
		this.capital = capital;

		setHeight(250);
		setWidth(970);
		setBorder(false);
		setLayout(new VerticalLayout(0));

		createFields();

		// add price field
		SliderBar priceSlider = new SliderBar(5, 20);
		configureSlider(priceSlider, 1, 10, 15, 5, " &euro;", fields.get(0));
		add(createSliderField(fields.get(0), priceSlider));

		Panel horPanel = new Panel();
		horPanel.setLayout(new HorizontalLayout(15));
		horPanel.setPaddings(0);

		FieldSet marketingFS = createMarketingFieldSet();
		horPanel.add(marketingFS);

		FieldSet researchFS = createResearchFieldSet();
		horPanel.add(researchFS);

		add(horPanel);
	}

	private void createFields() {
		// create all fields and put them in a list
		fields.add(createNumberField("Preis", "price", 10, 5, 20, true));
		fields.add(createNumberField("Zeitung", "mag", 0, 0, 0, false));
		fields.add(createNumberField("Radio", "radio", 0, 0, 0, false));
		fields.add(createNumberField("TV", "tv", 0, 0, 0, false));
		fields
				.add(createNumberField("Qualit&auml;t", "quality", 0, 0, 0,
						false));
		fields.add(createNumberField("Design", "design", 0, 0, 0, false));
		fields
				.add(createNumberField("&Ouml;kologie", "ecology", 0, 0, 0,
						false));
	}

	private FieldSet createMarketingFieldSet() {
		// add marketing fields
		FieldSet marketingFS = new FieldSet("Marketing");
		marketingFS.setPaddings(5, 5, 5, 0);

		SliderBar magSlider = new SliderBar(0, 100000);
		configureSlider(magSlider, 10000, 0, 10, 2, " &euro;", fields.get(1));
		marketingFS.add(createSliderField(fields.get(1), magSlider));

		SliderBar radioSlider = new SliderBar(0, 100000);
		configureSlider(radioSlider, 10000, 0, 10, 2, " &euro;", fields.get(2));
		marketingFS.add(createSliderField(fields.get(2), radioSlider));

		SliderBar tvSlider = new SliderBar(0, 100000);
		configureSlider(tvSlider, 10000, 0, 10, 2, " &euro;", fields.get(3));
		marketingFS.add(createSliderField(fields.get(3), tvSlider));

		return marketingFS;
	}

	private FieldSet createResearchFieldSet() {
		// add research fields
		FieldSet researchFS = new FieldSet("Forschung und Entwicklung");
		researchFS.setPaddings(5, 5, 5, 0);

		SliderBar qSlider = new SliderBar(0, 100000);
		configureSlider(qSlider, 10000, 0, 10, 2, " &euro;", fields.get(4));
		researchFS.add(createSliderField(fields.get(4), qSlider));

		SliderBar designSlider = new SliderBar(0, 100000);
		configureSlider(designSlider, 10000, 0, 10, 2, " &euro;", fields.get(5));
		researchFS.add(createSliderField(fields.get(5), designSlider));

		SliderBar ecoSlider = new SliderBar(0, 100000);
		configureSlider(ecoSlider, 10000, 0, 10, 2, " &euro;", fields.get(6));
		researchFS.add(createSliderField(fields.get(6), ecoSlider));

		return researchFS;
	}

	private Component createSliderField(NumberField field, SliderBar slider) {
		Panel panel = new Panel();
		panel.setLayout(new HorizontalLayout(15));
		panel.setBorder(false);

		FormPanel form = new FormPanel();
		form.setBorder(false);
		form.setWidth(110);
		form.setLabelWidth(50);
		form.add(field);

		panel.add(form);

		if (slider != null)
			panel.add(slider);

		return panel;
	}

	private void configureSlider(SliderBar slider, int stepSize, int value,
			int ticks, int labels, String postfix, NumberField field) {
		slider.setStepSize(stepSize);
		slider.setCurrentValue(value);
		slider.setNumTicks(ticks);
		slider.setNumLabels(labels);
		slider.setLabelFormatter(new SliderLabelFormatter(postfix));
		slider.addChangeListener(new SliderChangeListener(field, slider,
				capital));
	}

	private NumberField createNumberField(String text, String name, int value,
			int minvalue, int maxvalue, boolean decimal) {
		NumberField numField = new NumberField(text, name, 55, value);

		numField.setAllowNegative(false);

		if (maxvalue != 0)
			numField.setMaxValue(maxvalue);
		numField.setMinValue(minvalue);
		numField.setMinText("Der minimale Wert f&uuml;r dieses Feld ist: "
				+ minvalue);
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
				for (Button button : buttons)
					button.disable();
			}

			public void onValid(Field field) {
				super.onValid(field);
				if (areAllFieldsValid()) {
					for (Button button : buttons)
						button.enable();
				}
			}
		});

		return numField;
	}

	private boolean areAllFieldsValid() {
		for (NumberField field : fields) {
			if (!field.isValid())
				return false;
		}

		return true;
	}
}
