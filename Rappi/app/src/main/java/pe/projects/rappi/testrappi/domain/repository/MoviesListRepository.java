package pe.projects.rappi.testrappi.domain.repository;


import java.util.List;

import pe.projects.rappi.testrappi.domain.interactor.MoviesListCallback;
import pe.projects.rappi.testrappi.domain.model.MovieModel;

public interface MoviesListRepository {

    void getMovies(int categorySelected, int page, MoviesListCallback moviesListCallback);
    void getMoviesOffline(int categorySelected, MoviesListCallback moviesListCallback);
    void saveMovies(List<MovieModel> movieModelList, int categorySelected);
}
