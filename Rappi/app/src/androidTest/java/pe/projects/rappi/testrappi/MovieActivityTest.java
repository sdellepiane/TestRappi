package pe.projects.rappi.testrappi;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatTextView;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import pe.projects.rappi.testrappi.app.ui.activity.MovieActivity;
import pe.projects.rappi.testrappi.app.ui.util.DateUtil;
import pe.projects.rappi.testrappi.domain.model.MovieModel;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieActivityTest {

    private String movieName = "Peloteros";
    private String movieDate = DateUtil.formatDate("2018-09-18", Constants.INPUT_FORMAT_DATE, Constants.OUTPUT_FORMAT_DATE);
    private String movieDescription = "Buena pelicula";
    private int votes = 4;
    private double averageVotes = 2.4;
    private double popularity = 300;
    private MovieModel movieModel;

    @Rule
    public ActivityTestRule<MovieActivity> mActivityRule = new ActivityTestRule<MovieActivity>(MovieActivity.class){
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            movieModel = new MovieModel();
            movieModel.setTitle(movieName);
            movieModel.setReleaseDate(movieDate);
            movieModel.setOverview(movieDescription);
            movieModel.setVoteCount(votes);
            movieModel.setVoteAverage(averageVotes);
            movieModel.setPopularity(popularity);
            Intent result = new Intent(targetContext, MovieActivity.class);
            result.putExtra(Constants.MOVIE_SELECTED, movieModel);
            return result;
        }
    };

    @Test
    public void testCheckDetailFields(){
        MovieActivity activity = mActivityRule.getActivity();
        AppCompatTextView tviMovieName = activity.findViewById(R.id.tviMovieName);
        Assert.assertEquals(tviMovieName.getText().toString(), movieModel.getTitle(), movieName);

        AppCompatTextView tviMovieDate = activity.findViewById(R.id.tviMovieName);
        Assert.assertEquals(tviMovieDate.getText().toString(), movieModel.getReleaseDate(), movieDate);

        AppCompatTextView tviDescription = activity.findViewById(R.id.tviDescription);
        Assert.assertEquals(tviDescription.getText().toString(), movieModel.getOverview(), movieDescription);

        AppCompatTextView tviVotes = activity.findViewById(R.id.tviVotes);
        Assert.assertEquals(tviVotes.getText().toString(), String.format("%s%s", "Votos\\n", String.valueOf(movieModel.getVoteCount())),
                String.format("%s%s", "Votos\\n", String.valueOf(votes)));

        AppCompatTextView tviAverageVotes = activity.findViewById(R.id.tviAverageVotes);
        Assert.assertEquals(tviAverageVotes.getText().toString(), String.format("%s%s", "Puntuación\\n", String.valueOf(movieModel.getVoteAverage())),
                String.format("%s%s", "Puntuación\\n", String.valueOf(averageVotes)));

        AppCompatTextView tviPopularity = activity.findViewById(R.id.tviPopularity);
        Assert.assertEquals(tviPopularity.getText().toString(), String.format("%s%s", "Popularidad\\n", String.valueOf(movieModel.getPopularity())),
                String.format("%s%s", "Popularidad\\n", String.valueOf(popularity)));
    }
}
