/*package com.example.bbe;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

public class activity_settings extends AppCompatActivity implements
        PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {


        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            Context context = getPreferenceManager().getContext();
            PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

            SwitchPreferenceCompat notificationPreference = new SwitchPreferenceCompat(context);
            notificationPreference.setKey("notifications");
            notificationPreference.setTitle("Enable message notifications");

            PreferenceCategory notificationCategory = new PreferenceCategory(context);
            notificationCategory.setKey("notifications_category");
            notificationCategory.setTitle("Notifications");
            screen.addPreference(notificationCategory);
            notificationCategory.addPreference(notificationPreference);

            Preference feedbackPreference = new Preference(context);
            feedbackPreference.setKey("feedback");
            feedbackPreference.setTitle("Send feedback");
            feedbackPreference.setSummary("Report technical issues or suggest new features");

            PreferenceCategory helpCategory = new PreferenceCategory(context);
            helpCategory.setKey("help");
            helpCategory.setTitle("Help");
            screen.addPreference(helpCategory);
            helpCategory.addPreference(feedbackPreference);

            setPreferenceScreen(screen);
        }

  //return false;  @Override
   // public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {

    //}
}
}*/