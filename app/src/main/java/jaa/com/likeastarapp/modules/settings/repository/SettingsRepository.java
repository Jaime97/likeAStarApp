package jaa.com.likeastarapp.modules.settings.repository;

import android.content.Context;

public class SettingsRepository implements SettingsRepositoryInput {

    private SettingsRepositoryOutput output;
    private Context context;

    public SettingsRepository(SettingsRepositoryOutput output, Context context) {
        this.output = output;
        this.context = context;
    }

}
