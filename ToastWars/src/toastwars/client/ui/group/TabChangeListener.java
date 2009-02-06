package toastwars.client.ui.group;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.event.TabPanelListenerAdapter;

public class TabChangeListener extends TabPanelListenerAdapter {

	private Sound	sound;
	private boolean	firstTime;

	public TabChangeListener() {
		SoundController soundController = new SoundController();
		sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_X_WAV,
				"sounds/saber_swing.wav");
		sound.setVolume(50);
		firstTime = true;
	}

	@Override
	public void onTabChange(TabPanel source, Panel tab) {
		super.onTabChange(source, tab);

		if (!firstTime)
			sound.play();

		firstTime = false;
	}
}
