package jaa.com.likeastarapp.modules.settings;

import android.content.Context;

import jaa.com.likeastarapp.modules.settings.repository.SettingsRepository;
import jaa.com.likeastarapp.modules.settings.repository.SettingsRepositoryInput;
import jaa.com.likeastarapp.modules.settings.repository.SettingsRepositoryOutput;

public class SettingsPresenter implements SettingsContract.Presenter, SettingsRepositoryOutput {

    private SettingsContract.View userInterface;
    private SettingsRepositoryInput repository;

    @Override
    public void start(SettingsContract.View view, Context context) {
        userInterface = view;
        repository = new SettingsRepository(this, context);
    }


    @Override
    public void stop() {

    }
}
