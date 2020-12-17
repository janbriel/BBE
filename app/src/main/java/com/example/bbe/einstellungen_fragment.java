package com.example.bbe;

import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import android.widget.Toast;

public class einstellungen_fragment extends PreferenceFragmentCompat implements
        Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        Toast.makeText(getContext(), "Schritt 2: Das SettingsFragment wurde gestartet.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        return true;
    }
}