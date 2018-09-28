package pe.projects.rappi.testrappi.domain.interactor;

import java.util.List;

import pe.projects.rappi.testrappi.domain.model.MovieModel;
import pe.projects.rappi.testrappi.domain.repository.MoviesListRepository;

public class MoviesListInteractor {

    private final MoviesListRepository moviesListRepository;

    public MoviesListInteractor(MoviesListRepository moviesListRepository) {
        this.moviesListRepository = moviesListRepository;
    }

    public void getMoviesList(int categorySelected, int page, final MoviesListCallback moviesListCallback){
        this.moviesListRepository.getMovies(categorySelected, page, moviesListCallback);
    }

    public void getMoviesListOffline(int categorySelected, final MoviesListCallback moviesListCallback){
        this.moviesListRepository.getMoviesOffline(categorySelected, moviesListCallback);
    }

    public void saveMovies(List<MovieModel> movieModelList, int categorySelected){
        this.moviesListRepository.saveMovies(movieModelList, categorySelected);
    }
}
