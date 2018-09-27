package pe.projects.rappi.testrappi.domain.repository;


import pe.projects.rappi.testrappi.domain.interactor.MoviesListCallback;

public interface MoviesListRepository {

    void getMovies(int categorySelected, int page, MoviesListCallback moviesListCallback);
}
