/*
Portable ZX-Spectrum emulator.
Copyright (C) 2001-2011 SMT, Dexus, Alone Coder, deathsoft, djdron, scor

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package app.usp;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.MenuItem;

public class Preferences extends PreferenceActivity implements OnSharedPreferenceChangeListener
{
	final static private String select_joystick_id = "joystick";
	final static private String sound_chip_id = "sound chip";
	final static private String sound_chip_stereo_id = "ay stereo";
	final static private String auto_play_image_id = "auto play image";
	final static private String save_slot_id = "save slot";
	final static private String save_file_id = "save file";
	final static private String select_drive_id = "drive";
	final static private String tape_id = "tape";
	final static private String tape_fast_id = "fast tape";
	final static private String mode_48k_id = "mode 48k";
	final static private String reset_to_service_rom_id = "reset to service rom";
	final static public String select_zoom_id = "zoom";
	final static public String filtering_id = "filtering";
	final static public String gigascreen_id = "gigascreen";
	final static public String black_and_white_id = "black and white";
	final static public String av_timer_sync_id = "av timer sync";
	final static public String skip_frames_id = "skip frames";
	final static public String use_sensor_id = "use sensor";
	final static public String use_keyboard_id = "use keyboard";
	private ListPreference select_joystick;
	private CheckBoxPreference use_sensor;
	private ListPreference sound_chip;
	private ListPreference sound_chip_stereo;
	private ListPreference save_slot;
	private Preference save_file;
	private ListPreference select_drive;
	private Preference tape;
	private CheckBoxPreference auto_play_image;
	private CheckBoxPreference tape_fast;
	private CheckBoxPreference mode_48k;
	private CheckBoxPreference reset_to_service_rom;
	private ListPreference select_zoom;
	private CheckBoxPreference filtering;
	private CheckBoxPreference gigascreen;
	private CheckBoxPreference black_and_white;
	private CheckBoxPreference av_timer_sync;
	private ListPreference skip_frames;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(Main.AbleActionBar())
			getActionBar().setDisplayHomeAsUpEnabled(true);
		addPreferencesFromResource(R.xml.preferences);
        select_joystick = (ListPreference)getPreferenceScreen().findPreference(select_joystick_id);
        use_sensor = (CheckBoxPreference)getPreferenceScreen().findPreference(use_sensor_id);
        sound_chip = (ListPreference)getPreferenceScreen().findPreference(sound_chip_id);
        sound_chip_stereo = (ListPreference)getPreferenceScreen().findPreference(sound_chip_stereo_id);
        auto_play_image = (CheckBoxPreference)getPreferenceScreen().findPreference(auto_play_image_id);
		save_slot = (ListPreference)getPreferenceScreen().findPreference(save_slot_id);
		save_file = (Preference)getPreferenceScreen().findPreference(save_file_id);
        select_drive = (ListPreference)getPreferenceScreen().findPreference(select_drive_id);
        tape = (Preference)getPreferenceScreen().findPreference(tape_id);
        tape_fast = (CheckBoxPreference)getPreferenceScreen().findPreference(tape_fast_id);
        mode_48k = (CheckBoxPreference)getPreferenceScreen().findPreference(mode_48k_id);
        reset_to_service_rom = (CheckBoxPreference)getPreferenceScreen().findPreference(reset_to_service_rom_id);
        select_zoom = (ListPreference)getPreferenceScreen().findPreference(select_zoom_id);
        filtering = (CheckBoxPreference)getPreferenceScreen().findPreference(filtering_id);
		gigascreen = (CheckBoxPreference)getPreferenceScreen().findPreference(gigascreen_id);
		black_and_white = (CheckBoxPreference)getPreferenceScreen().findPreference(black_and_white_id);
        av_timer_sync = (CheckBoxPreference)getPreferenceScreen().findPreference(av_timer_sync_id);
        skip_frames = (ListPreference)getPreferenceScreen().findPreference(skip_frames_id);
	}
	private void UpdateDescs()
	{
		select_joystick.setSummary(select_joystick.getEntry());
		sound_chip.setSummary(sound_chip.getEntry());
		sound_chip_stereo.setSummary(sound_chip_stereo.getEntry());
		save_slot.setSummary(save_slot.getEntry());
		select_drive.setSummary(select_drive.getEntry());
		switch(Emulator.the.TapeState())
		{
		case 0:	tape.setSummary(R.string.tape_na);		tape.setEnabled(false);	break;
		case 1:	tape.setSummary(R.string.tape_stopped);	tape.setEnabled(true);	break;
		case 2:	tape.setSummary(R.string.tape_started);	tape.setEnabled(true);	break;
		}
		save_file.setEnabled(Emulator.the.DiskChanged());
		select_zoom.setSummary(select_zoom.getEntry());
		skip_frames.setSummary(skip_frames.getEntry());
	}
	@Override
	protected void onResume()
	{
		super.onResume();
		select_joystick.setValueIndex(Emulator.the.GetOptionInt(select_joystick_id));
		use_sensor.setChecked(Emulator.the.GetOptionBool(use_sensor_id));
		sound_chip.setValueIndex(Emulator.the.GetOptionInt(sound_chip_id));
		sound_chip_stereo.setValueIndex(Emulator.the.GetOptionInt(sound_chip_stereo_id));
		auto_play_image.setChecked(Emulator.the.GetOptionBool(auto_play_image_id));
		save_slot.setValueIndex(Emulator.the.GetOptionInt(save_slot_id));
		select_drive.setValueIndex(Emulator.the.GetOptionInt(select_drive_id));
		tape_fast.setChecked(Emulator.the.GetOptionBool(tape_fast_id));
		mode_48k.setChecked(Emulator.the.GetOptionBool(mode_48k_id));
		reset_to_service_rom.setChecked(Emulator.the.GetOptionBool(reset_to_service_rom_id));
		select_zoom.setValueIndex(Emulator.the.GetOptionInt(select_zoom_id));
		filtering.setChecked(Emulator.the.GetOptionBool(filtering_id));
		gigascreen.setChecked(Emulator.the.GetOptionBool(gigascreen_id));
		black_and_white.setChecked(Emulator.the.GetOptionBool(black_and_white_id));
		av_timer_sync.setChecked(Emulator.the.GetOptionBool(av_timer_sync_id));
		skip_frames.setValueIndex(Emulator.the.GetOptionInt(skip_frames_id));
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		UpdateDescs();
	}
	@Override
	protected void onPause()
	{
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		Emulator.the.StoreOptions();
		super.onPause();
	}
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
		if(key.equals(select_joystick_id))
		{
			Emulator.the.SetOptionInt(select_joystick_id, Integer.parseInt(select_joystick.getValue()));
			UpdateDescs();
		}
		else if(key.equals(sound_chip_id))
		{
			Emulator.the.SetOptionInt(sound_chip_id, Integer.parseInt(sound_chip.getValue()));
			UpdateDescs();
		}
		else if(key.equals(sound_chip_stereo_id))
		{
			Emulator.the.SetOptionInt(sound_chip_stereo_id, Integer.parseInt(sound_chip_stereo.getValue()));
			UpdateDescs();
		}
		else if(key.equals(auto_play_image_id))
			Emulator.the.SetOptionBool(auto_play_image_id, auto_play_image.isChecked());
		else if(key.equals(save_slot_id))
		{
			Emulator.the.SetOptionInt(save_slot_id, Integer.parseInt(save_slot.getValue()));
			UpdateDescs();
		}
		else if(key.equals(select_drive_id))
		{
			Emulator.the.SetOptionInt(select_drive_id, Integer.parseInt(select_drive.getValue()));
			UpdateDescs();
		}
		else if(key.equals(mode_48k_id))
			Emulator.the.SetOptionBool(mode_48k_id, mode_48k.isChecked());
		else if(key.equals(reset_to_service_rom_id))
			Emulator.the.SetOptionBool(reset_to_service_rom_id, reset_to_service_rom.isChecked());
		else if(key.equals(tape_fast_id))
			Emulator.the.SetOptionBool(tape_fast_id, tape_fast.isChecked());
		else if(key.equals(select_zoom_id))
		{
			Emulator.the.SetOptionInt(select_zoom_id, Integer.parseInt(select_zoom.getValue()));
			UpdateDescs();
		}
		else if(key.equals(use_sensor_id))
			Emulator.the.SetOptionBool(use_sensor_id, use_sensor.isChecked());
		else if(key.equals(filtering_id))
			Emulator.the.SetOptionBool(filtering_id, filtering.isChecked());
		else if(key.equals(gigascreen_id))
			Emulator.the.SetOptionBool(gigascreen_id, gigascreen.isChecked());
		else if(key.equals(black_and_white_id))
			Emulator.the.SetOptionBool(black_and_white_id, black_and_white.isChecked());
		else if(key.equals(av_timer_sync_id))
			Emulator.the.SetOptionBool(av_timer_sync_id, av_timer_sync.isChecked());
		else if(key.equals(skip_frames_id))
		{
			Emulator.the.SetOptionInt(skip_frames_id, Integer.parseInt(skip_frames.getValue()));
			UpdateDescs();
		}
	}
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference)
	{
		if(preference.getKey().equals(tape_id))
		{
			Emulator.the.TapeToggle();
			UpdateDescs();
		}
		else if(preference.getKey().equals(save_file_id))
		{
			Emulator.the.SaveFile();
			UpdateDescs();
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(Main.AbleActionBar())
		{
			switch(item.getItemId())
			{
			case android.R.id.home:	onBackPressed(); break;
			}
		}
		return super.onOptionsItemSelected(item);
	}
}
