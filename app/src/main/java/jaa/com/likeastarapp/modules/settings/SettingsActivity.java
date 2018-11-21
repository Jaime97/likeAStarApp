package jaa.com.likeastarapp.modules.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;

import jaa.com.likeastarapp.R;

public class SettingsActivity extends PreferenceActivity implements SettingsContract.View {

    private SettingsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.activity_settings);
        presenter = new SettingsPresenter();
        presenter.start(this, getApplicationContext());
    }

}
