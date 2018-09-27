package pe.projects.rappi.testrappi.domain.interactor;

import pe.projects.rappi.testrappi.domain.repository.MoviesListRepository;

public class MoviesListInteractor {

    private final MoviesListRepository moviesListRepository;

    public MoviesListInteractor(MoviesListRepository moviesListRepository) {
        this.moviesListRepository = moviesListRepository;
    }

    public void getMoviesList(int categorySelected, int page, final MoviesListCallback moviesListCallback){
        this.moviesListRepository.getMovies(categorySelected, page, moviesListCallback);
    }
}
