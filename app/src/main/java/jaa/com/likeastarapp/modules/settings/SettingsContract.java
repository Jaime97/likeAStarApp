package jaa.com.likeastarapp.modules.settings;

import android.content.Context;

public interface SettingsContract {

    interface View {
    }

    interface Presenter {
        void start(View view, Context context);
        void stop();
    }

}
