package pe.projects.rappi.testrappi.repository.datasource;

import java.util.ArrayList;
import java.util.List;

import pe.projects.rappi.testrappi.data.entity.response.MoviesListResponse;
import pe.projects.rappi.testrappi.data.mapper.MovieListDataMapper;
import pe.projects.rappi.testrappi.domain.interactor.MoviesListCallback;
import pe.projects.rappi.testrappi.domain.model.ListMovieModel;
import pe.projects.rappi.testrappi.domain.model.MovieModel;
import pe.projects.rappi.testrappi.domain.repository.MoviesListRepository;
import pe.projects.rappi.testrappi.repository.RepositoryCallback;
import pe.projects.rappi.testrappi.repository.datasource.database.MovieListDatabaseDataStore;
import pe.projects.rappi.testrappi.repository.datasource.ws.MoviesListDataStore;

public class MoviesListDataRepository implements MoviesListRepository{

    private final MoviesListDataStoreFactory moviesListDataStoreFactory;
    private final MovieListDataMapper movieListDataMapper;

    public MoviesListDataRepository(MoviesListDataStoreFactory moviesListDataStoreFactory, MovieListDataMapper movieListDataMapper){
        this.moviesListDataStoreFactory = moviesListDataStoreFactory;
        this.movieListDataMapper = movieListDataMapper;
    }

    @Override
    public void getMovies(int categorySelected, int page, final MoviesListCallback moviesListCallback) {
        MoviesListDataStore moviesListDataStore = this.moviesListDataStoreFactory.createSource();
        moviesListDataStore.getMoviesList(categorySelected, page, new RepositoryCallback() {
            @Override
            public void onSuccess(Object object) {
                MoviesListResponse moviesListResponse = (MoviesListResponse) object;
                ListMovieModel listMovieModel = movieListDataMapper.transformMovieListModel(moviesListResponse);
                moviesListCallback.onGetMovieListSuccess(listMovieModel);
            }

            @Override
            public void onFailure(Exception e) {
                moviesListCallback.onMessageError();
            }

            @Override
            public void onFailure(Throwable throwable) {
                moviesListCallback.onMessageError();
            }

            @Override
            public void onCommonMessageError() {
                moviesListCallback.onMessageError();
            }
        });
    }

    @Override
    public void getMoviesOffline(int categorySelected, final MoviesListCallback moviesListCallback) {
        MovieListDatabaseDataStore movieListDatabaseDataStore = moviesListDataStoreFactory
                .createMovieDatabase();
        movieListDatabaseDataStore.getMoviesByCategory(categorySelected, new RepositoryCallback() {
            @Override
            public void onSuccess(Object object) {
                List<MovieModel> listMovieModel = (ArrayList<MovieModel>) object;
                moviesListCallback.onGetMovieListOfflineSuccess(listMovieModel);
            }

            @Override
            public void onFailure(Exception e) {
                moviesListCallback.onMessageError();
            }

            @Override
            public void onFailure(Throwable throwable) {
                moviesListCallback.onMessageError();
            }

            @Override
            public void onCommonMessageError() {
                moviesListCallback.onMessageError();
            }
        });

    }

    @Override
    public void saveMovies(List<MovieModel> movieModelList, int categorySelected) {
        MovieListDatabaseDataStore movieListDatabaseDataStore = moviesListDataStoreFactory
                .createMovieDatabase();
        movieListDatabaseDataStore.saveMovies(movieModelList, categorySelected);
    }
}
