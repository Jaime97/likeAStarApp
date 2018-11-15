package jaa.com.likeastarapp.modules.settings;

import android.content.Context;

import jaa.com.likeastarapp.modules.settings.model.SettingsModel;
import jaa.com.likeastarapp.modules.settings.model.SettingsModelInput;
import jaa.com.likeastarapp.modules.settings.model.SettingsModelOutput;

public class SettingsPresenter implements SettingsContract.Presenter, SettingsModelOutput {

    private SettingsContract.View userInterface;
    private SettingsModelInput model;

    @Override
    public void start(SettingsContract.View view, Context context) {
        userInterface = view;
        model = new SettingsModel(this, context);
    }


    @Override
    public void stop() {

    }
}
