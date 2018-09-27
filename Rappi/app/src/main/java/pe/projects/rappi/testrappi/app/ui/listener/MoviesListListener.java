package pe.projects.rappi.testrappi.app.ui.listener;

import java.util.List;

import pe.projects.rappi.testrappi.domain.model.ListMovieModel;
import pe.projects.rappi.testrappi.domain.model.MovieModel;

public interface MoviesListListener {

    void goToMovieDetail(MovieModel movieModel);
    void loadMoreMovies();
    void showMovieList(ListMovieModel listMovieModel);
    void failedLoadData();
}
