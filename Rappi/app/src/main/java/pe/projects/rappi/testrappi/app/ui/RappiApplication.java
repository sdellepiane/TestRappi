package pe.projects.rappi.testrappi.app.ui;

import android.app.Application;
import android.content.Context;

public class RappiApplication extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        RappiApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return RappiApplication.context;
    }
}
