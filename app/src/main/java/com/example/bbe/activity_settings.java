package com.example.bbe;

import android.os.Bundle;

import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class activity_settings extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.root_preferences);
        Preference testlistPref = findPreference(getString(R.string.preference_testlist_key));
        testlistPref.setOnPreferenceChangeListener(this);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String gespeicherteTestlist = sharedPrefs.getString(testlistPref.getKey(), "");
        onPreferenceChange(testlistPref, gespeicherteTestlist);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {

            preference.setSummary(value.toString());

            return true;
    }
}