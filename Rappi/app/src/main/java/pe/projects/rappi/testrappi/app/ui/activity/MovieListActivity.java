package pe.projects.rappi.testrappi.app.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.projects.rappi.testrappi.Constants;
import pe.projects.rappi.testrappi.R;
import pe.projects.rappi.testrappi.app.ui.adapter.MoviesAdapter;
import pe.projects.rappi.testrappi.app.ui.core.BaseCompatActivity;
import pe.projects.rappi.testrappi.app.ui.listener.MoviesListListener;
import pe.projects.rappi.testrappi.app.ui.util.NetworkUtils;
import pe.projects.rappi.testrappi.app.ui.util.RecyclerViewDecorator;
import pe.projects.rappi.testrappi.domain.model.ListMovieModel;
import pe.projects.rappi.testrappi.domain.model.MovieModel;
import pe.projects.rappi.testrappi.presenter.MoviesListPresenter;
import pe.projects.rappi.testrappi.presenter.view.MoviesListView;

public class MovieListActivity extends BaseCompatActivity implements MoviesListView, MoviesListListener{

    @BindView(R.id.tviPopular) AppCompatTextView tviPopular;
    @BindView(R.id.tviTopRated) AppCompatTextView tviTopRated;
    @BindView(R.id.tviUpcoming) AppCompatTextView tviUpcoming;
    @BindView(R.id.rviMovies) RecyclerView rviMovies;
    @BindView(R.id.vLoadMoreMovies) ProgressBar vLoadMoreMovies;
    @BindView(R.id.vLoading) View vLoading;
    @BindView(R.id.eteSearch) AppCompatEditText eteSearch;
    @BindView(R.id.llaSearch) LinearLayout llaSearch;

    private MoviesListListener listener;
    private MoviesListPresenter presenter;
    private List<MovieModel> movieModelList;
    private int page = Constants.INIT_PAGE;
    private int categorySelected = Constants.CATEGORY_POPULAR_SELECTED;
    private MoviesAdapter moviesAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        init();
        sendViewToChangeBackgroundColorAndTextColor();
        if(NetworkUtils.isOnline(this)) {
            loadListTeams(categorySelected);
        } else {
            loadListItemsOffline();
        }
    }

    private void init(){
        listener = this;
        validateSearchBarNetwork();
        ButterKnife.bind(this);
        presenter = new MoviesListPresenter();
        presenter.attachedView(this);
        iniRecyclerView();
        eteSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().length() > 3){
                    moviesAdapter.getFilter().filter(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void validateSearchBarNetwork(){
        if(NetworkUtils.isOnline(this)){
            llaSearch.setVisibility(View.GONE);
        }
    }

    private void iniRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, Constants.GRID_COLUMNS_MOVIES);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spaces);
        rviMovies.setLayoutManager(mLayoutManager);
        rviMovies.setItemAnimator(new DefaultItemAnimator());
        rviMovies.addItemDecoration(new RecyclerViewDecorator(spacingInPixels));
    }

    private void loadListTeams(int optionSelected){
        presenter.getMovieList(optionSelected, page, listener);
    }

    @Override
    public void showLoading() {
        vLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vLoading.setVisibility(View.GONE);
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void goToMovieDetail(MovieModel movieModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.MOVIE_SELECTED, movieModel);
        nextData(MovieActivity.class, bundle, false);
    }

    @Override
    public void loadMoreMovies() {
        vLoadMoreMovies.setVisibility(View.VISIBLE);
        page++;
        categorySelected = Constants.CATEGORY_POPULAR_SELECTED;
        loadListTeams(categorySelected);
    }

    @Override
    public void showMovieList(ListMovieModel listMovieModel) {
        if(page > 1){
            vLoadMoreMovies.setVisibility(View.GONE);
            movieModelList.addAll(listMovieModel.getResults());
            moviesAdapter.notifyDataSetChanged();
        } else {
            movieModelList = listMovieModel.getResults();
            moviesAdapter = new MoviesAdapter(this, this, movieModelList, rviMovies);
            rviMovies.setAdapter(moviesAdapter);
        }
        presenter.saveMoviesOffline(listMovieModel.getResults(), categorySelected);
        moviesAdapter.setLoaded();
    }

    @Override
    public void failedLoadData() {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @OnClick(R.id.tviPopular)
    public void clickPopularMovies(){
        page = 1;
        categorySelected = Constants.CATEGORY_POPULAR_SELECTED;
        sendViewToChangeBackgroundColorAndTextColor();
        validateChannelToGetMovies();
    }

    @OnClick(R.id.tviTopRated)
    public void clickTopRatedMovies(){
        page = 1;
        categorySelected = Constants.CATEGORY_TOP_RATED_SELECTED;
        sendViewToChangeBackgroundColorAndTextColor();
        movieModelList.clear();
        validateChannelToGetMovies();
    }

    @OnClick(R.id.tviUpcoming)
    public void clickUpcomingMovies(){
        page = 1;
        categorySelected = Constants.CATEGORY_UPCOMING_SELECTED;
        sendViewToChangeBackgroundColorAndTextColor();
        movieModelList.clear();
        validateChannelToGetMovies();
    }

    private void sendViewToChangeBackgroundColorAndTextColor(){
        switch(categorySelected){
            case Constants.CATEGORY_POPULAR_SELECTED:
                changeBackgroundAndTextColorOptions(tviPopular, tviTopRated, tviUpcoming,
                        Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE);
                break;
            case Constants.CATEGORY_TOP_RATED_SELECTED:
                changeBackgroundAndTextColorOptions(tviTopRated, tviPopular, tviUpcoming,
                        Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE);
                break;
            case Constants.CATEGORY_UPCOMING_SELECTED:
                changeBackgroundAndTextColorOptions(tviUpcoming, tviPopular, tviTopRated,
                        Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE);
                break;
        }
    }

    private void changeBackgroundAndTextColorOptions(AppCompatTextView tviOn,
                                                     AppCompatTextView tviOffOne,
                                                     AppCompatTextView tviOffTwo,
                                                     int colorOn, int colorOff, int backgroundOn,
                                                     int backgroundOff){
        tviOn.setTextColor(colorOn);
        tviOn.setBackgroundColor(backgroundOn);
        tviOffOne.setTextColor(colorOff);
        tviOffOne.setBackgroundColor(backgroundOff);
        tviOffTwo.setTextColor(colorOff);
        tviOffTwo.setBackgroundColor(backgroundOff);
    }

    private void validateChannelToGetMovies(){
        if (NetworkUtils.isOnline(this)) {
            loadListTeams(categorySelected);
        } else {
            loadListItemsOffline();
        }
    }

    private void loadListItemsOffline() {
        List<MovieModel> movieModelList = presenter.getMovieListOffline(categorySelected);
        if(moviesAdapter != null){
            this.movieModelList.clear();
            this.movieModelList = movieModelList;
            moviesAdapter.notifyDataSetChanged();
        } else {
            moviesAdapter = new MoviesAdapter(this, this, movieModelList, rviMovies);
            rviMovies.setAdapter(moviesAdapter);
        }
    }
}
