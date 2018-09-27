package pe.projects.rappi.testrappi.app.ui.activity;

import android.os.Bundle;
import android.os.Handler;

import pe.projects.rappi.testrappi.R;
import pe.projects.rappi.testrappi.app.ui.core.BaseCompatActivity;

public class SplashActivity extends BaseCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        executeSplash();
    }

    public void executeSplash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToTeamList();

            }
        }, SPLASH_SCREEN_DELAY);
    }

    public void goToTeamList(){
        nextActivity(MovieListActivity.class, true);
    }
}
