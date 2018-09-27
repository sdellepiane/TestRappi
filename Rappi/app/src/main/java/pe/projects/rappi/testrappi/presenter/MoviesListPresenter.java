package pe.projects.rappi.testrappi.presenter;

import java.util.List;

import pe.projects.rappi.testrappi.Constants;
import pe.projects.rappi.testrappi.app.ui.listener.MoviesListListener;
import pe.projects.rappi.testrappi.data.mapper.MovieListDataMapper;
import pe.projects.rappi.testrappi.domain.interactor.MoviesListCallback;
import pe.projects.rappi.testrappi.domain.interactor.MoviesListInteractor;
import pe.projects.rappi.testrappi.domain.model.ListMovieModel;
import pe.projects.rappi.testrappi.domain.model.MovieModel;
import pe.projects.rappi.testrappi.domain.repository.MoviesListRepository;
import pe.projects.rappi.testrappi.presenter.view.MoviesListView;
import pe.projects.rappi.testrappi.repository.datasource.MoviesListDataRepository;
import pe.projects.rappi.testrappi.repository.datasource.MoviesListDataStoreFactory;
import pe.projects.rappi.testrappi.repository.datasource.database.MovieListDatabaseDataStore;

public class MoviesListPresenter implements Presenter<MoviesListView>, MoviesListCallback {

    private MoviesListView view;
    private MoviesListRepository moviesListRepository;
    private MoviesListInteractor moviesListInteractor;
    private MoviesListDataStoreFactory moviesListDataStoreFactory;
    private MoviesListListener listener;

    public void getMovieList(int categorySelected, int page, MoviesListListener listener){
        if(page == Constants.INIT_PAGE){
            view.showLoading();
        }
        moviesListInteractor.getMoviesList(categorySelected, page, this);
        this.listener = listener;
    }

    public void saveMoviesOffline(List<MovieModel> movieModelList, int category){
        MovieListDatabaseDataStore movieListDatabaseDataStore = moviesListDataStoreFactory
                .createMovieDatabase();
        movieListDatabaseDataStore.saveMovies(movieModelList, category);
    }

    public List<MovieModel> getMovieListOffline(int category){
        MovieListDatabaseDataStore movieListDatabaseDataStore = moviesListDataStoreFactory
                .createMovieDatabase();
        return movieListDatabaseDataStore.getMoviesByCategory(category);
    }

    @Override
    public void onGetMovieListSuccess(Object object) {
        view.hideLoading();
        ListMovieModel listMovieModel = (ListMovieModel) object;
        listener.showMovieList(listMovieModel);
    }

    @Override
    public void onGetMovieListFailure(Object object) {
        view.hideLoading();
        listener.failedLoadData();
    }

    @Override
    public void onMessageError() {
        view.hideLoading();
    }

    @Override
    public void attachedView(MoviesListView view) {
        this.view = view;
        moviesListDataStoreFactory = new MoviesListDataStoreFactory();
        moviesListRepository = new MoviesListDataRepository(moviesListDataStoreFactory, new MovieListDataMapper());
        moviesListInteractor = new MoviesListInteractor(moviesListRepository);
    }

    @Override
    public void detachView() {
        view = null;
    }
}
