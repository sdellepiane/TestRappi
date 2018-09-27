package pe.projects.rappi.testrappi.repository.datasource;

import pe.projects.rappi.testrappi.app.ui.RappiApplication;
import pe.projects.rappi.testrappi.data.entity.response.MoviesListResponse;
import pe.projects.rappi.testrappi.data.mapper.MovieListDataMapper;
import pe.projects.rappi.testrappi.domain.interactor.MoviesListCallback;
import pe.projects.rappi.testrappi.domain.model.ListMovieModel;
import pe.projects.rappi.testrappi.domain.repository.MoviesListRepository;
import pe.projects.rappi.testrappi.repository.RepositoryCallback;

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
}
