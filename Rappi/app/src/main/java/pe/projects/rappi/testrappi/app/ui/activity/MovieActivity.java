package pe.projects.rappi.testrappi.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.projects.rappi.testrappi.Constants;
import pe.projects.rappi.testrappi.R;
import pe.projects.rappi.testrappi.app.ui.core.BaseCompatActivity;
import pe.projects.rappi.testrappi.app.ui.util.DateUtil;
import pe.projects.rappi.testrappi.domain.model.MovieModel;

public class MovieActivity extends BaseCompatActivity {

    @BindView(R.id.iviPoster) AppCompatImageView iviPoster;
    @BindView(R.id.tviMovieName) AppCompatTextView tviMovieName;
    @BindView(R.id.tviMovieDate) AppCompatTextView tviMovieDate;
    @BindView(R.id.tviDescription) AppCompatTextView tviDescription;
    @BindView(R.id.tviVotes) AppCompatTextView tviVotes;
    @BindView(R.id.tviAverageVotes) AppCompatTextView tviAverageVotes;
    @BindView(R.id.tviPopularity) AppCompatTextView tviPopularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        Bundle bundle = getIntent().getExtras();
        MovieModel movieModel = (MovieModel) bundle.getSerializable(Constants.MOVIE_SELECTED);
        Picasso.with(this).load(movieModel.getBackdropPath()).fit().
                error(R.drawable.error_loading).
                placeholder(R.drawable.placeholder_loading).into(iviPoster);
        tviMovieName.setText(movieModel.getTitle());
        tviMovieDate.setText(DateUtil.formatDate(movieModel.getReleaseDate(), Constants.INPUT_FORMAT_DATE, Constants.OUTPUT_FORMAT_DATE));
        tviDescription.setText(movieModel.getOverview());
        tviVotes.setText(String.format(getString(R.string.sFormatTwoTexts), getString(R.string.sVotes), String.valueOf(movieModel.getVoteCount())));
        tviAverageVotes.setText(String.format(getString(R.string.sFormatTwoTexts), getString(R.string.sAverageVotes), String.valueOf(movieModel.getVoteAverage())));
        tviPopularity.setText(String.format(getString(R.string.sFormatTwoTexts), getString(R.string.sPopularity), String.valueOf(movieModel.getPopularity())));
    }

    @OnClick(R.id.iv_back)
    public void clickBack(){
        finish();
    }
}
