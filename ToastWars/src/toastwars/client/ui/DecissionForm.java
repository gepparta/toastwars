package toastwars.client.ui;

import java.util.ArrayList;

import toastwars.client.Controller;
import toastwars.client.slider.SliderBar;
import toastwars.client.slider.SliderChangeListener;
import toastwars.client.slider.SliderLabelFormatter;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.Status;

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
	private ArrayList<SliderBar>	sliders	= new ArrayList<SliderBar>();
	private NumberField				capital;
	private Toaster					toaster;

	public DecissionForm(Button[] buttons, NumberField capital) {
		toaster = ((Group) Controller.getInstance().getUser()).getCompany()
				.getToasterList().get(0);

		this.buttons = buttons;
		this.capital = capital;

		setHeight(250);
		setWidth(970);
		setBorder(false);
		setLayout(new VerticalLayout(0));

		createFields();

		// add price field
		SliderBar priceSlider = new SliderBar(5, 20);
		configureSlider(priceSlider, 1, 15, 5, " &euro;", fields.get(0),
				toaster.getPrice());
		add(createSliderField(fields.get(0), priceSlider));

		Panel horPanel = new Panel();
		horPanel.setLayout(new HorizontalLayout(15));
		horPanel.setPaddings(0);

		FieldSet marketingFS = createMarketingFieldSet();
		horPanel.add(marketingFS);

		FieldSet researchFS = createResearchFieldSet();
		horPanel.add(researchFS);

		add(horPanel);

		if (((Group) Controller.getInstance().getUser()).getStatus() == Status.COMPLETED)
			disablePriceField();
	}

	private void createFields() {
		// create all fields and put them in a list
		fields.add(createNumberField("Preis", "price", toaster.getPrice(), 5,
				20, true));
		fields.add(createNumberField("Zeitung", "mag", toaster
				.getNewspaperInvestment(), 0, 0, false));
		fields.add(createNumberField("Radio", "radio", toaster
				.getRadioInvestment(), 0, 0, false));
		fields.add(createNumberField("TV", "tv", toaster.getTvInvestment(), 0,
				0, false));
		fields.add(createNumberField("Qualit&auml;t", "quality", toaster
				.getQualityInvestment(), 0, 0, false));
		fields.add(createNumberField("Design", "design", toaster
				.getDesignInvestment(), 0, 0, false));
		fields.add(createNumberField("&Ouml;kologie", "ecology", toaster
				.getEfficiencyInvestment(), 0, 0, false));
	}

	private FieldSet createMarketingFieldSet() {
		// add marketing fields
		FieldSet marketingFS = new FieldSet("Marketing");
		marketingFS.setPaddings(5, 5, 5, 0);

		SliderBar magSlider = new SliderBar(0, 100000);
		configureSlider(magSlider, 5000, 20, 2, " &euro;", fields.get(1),
				toaster.getNewspaperInvestment());
		marketingFS.add(createSliderField(fields.get(1), magSlider));

		SliderBar radioSlider = new SliderBar(0, 100000);
		configureSlider(radioSlider, 10000, 10, 2, " &euro;", fields.get(2),
				toaster.getRadioInvestment());
		marketingFS.add(createSliderField(fields.get(2), radioSlider));

		SliderBar tvSlider = new SliderBar(0, 120000);
		configureSlider(tvSlider, 20000, 6, 2, " &euro;", fields.get(3),
				toaster.getTvInvestment());
		marketingFS.add(createSliderField(fields.get(3), tvSlider));

		return marketingFS;
	}

	private FieldSet createResearchFieldSet() {
		// add research fields
		FieldSet researchFS = new FieldSet("Forschung und Entwicklung");
		researchFS.setPaddings(5, 5, 5, 0);

		SliderBar qSlider = new SliderBar(0, 100000);
		configureSlider(qSlider, 5000, 20, 2, " &euro;", fields.get(4), toaster
				.getQualityInvestment());
		researchFS.add(createSliderField(fields.get(4), qSlider));

		SliderBar designSlider = new SliderBar(0, 100000);
		configureSlider(designSlider, 5000, 20, 2, " &euro;", fields.get(5),
				toaster.getDesignInvestment());
		researchFS.add(createSliderField(fields.get(5), designSlider));

		SliderBar ecoSlider = new SliderBar(0, 100000);
		configureSlider(ecoSlider, 5000, 20, 2, " &euro;", fields.get(6),
				toaster.getEfficiencyInvestment());
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

	private void configureSlider(SliderBar slider, int stepSize, int ticks,
			int labels, String postfix, NumberField field, Number value) {
		slider.setStepSize(stepSize);
		slider.setCurrentValue(value.doubleValue());
		slider.setNumTicks(ticks);
		slider.setNumLabels(labels);
		slider.setLabelFormatter(new SliderLabelFormatter(postfix));
		slider.addChangeListener(new SliderChangeListener(field, slider,
				capital));
		sliders.add(slider);
	}

	private NumberField createNumberField(String text, String name,
			Number value, int minvalue, int maxvalue, boolean decimal) {
		NumberField numField = new NumberField(text, name, 55, value
				.floatValue());

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

		if (!name.equals("price"))
			numField.setReadOnly(true);

		return numField;
	}

	private boolean areAllFieldsValid() {
		for (NumberField field : fields) {
			if (!field.isValid())
				return false;
		}

		return true;
	}

	public void updateToasterData() {
		try {
			toaster.setPrice(fields.get(0).getValue().doubleValue());
			toaster.setNewspaperInvestment(fields.get(1).getValue()
					.doubleValue());
			toaster.setRadioInvestment(fields.get(2).getValue().doubleValue());
			toaster.setTvInvestment(fields.get(3).getValue().doubleValue());
			toaster
					.setQualityInvestment(fields.get(4).getValue()
							.doubleValue());
			toaster.setDesignInvestment(fields.get(5).getValue().doubleValue());
			toaster.setEfficiencyInvestment(fields.get(6).getValue()
					.doubleValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disablePriceField() {
		fields.get(0).setReadOnly(true);
		for (SliderBar slider : sliders) {
			slider.setVisible(false);
		}
	}
}
