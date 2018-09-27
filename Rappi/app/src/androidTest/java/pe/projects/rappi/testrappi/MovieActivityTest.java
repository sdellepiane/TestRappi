package pe.projects.rappi.testrappi;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import pe.projects.rappi.testrappi.app.ui.activity.MovieActivity;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieActivityTest {

    private String movieName = "Peloteros";
    private String movieDate = "2018-09-18";
    private String movieDescription = "Buena pelicula";
    private int votes = 4;
    private double averageVotes = 2.4;
    private double popularity = 300;

    @Rule
    public ActivityTestRule<MovieActivity> mActivityRule = new ActivityTestRule<>(MovieActivity.class);
}
