package jaa.com.likeastarapp.modules.settings.model;

import android.content.Context;

public class SettingsModel implements SettingsModelInput {

    private SettingsModelOutput output;
    private Context context;

    public SettingsModel(SettingsModelOutput output, Context context) {
        this.output = output;
        this.context = context;
    }

}
