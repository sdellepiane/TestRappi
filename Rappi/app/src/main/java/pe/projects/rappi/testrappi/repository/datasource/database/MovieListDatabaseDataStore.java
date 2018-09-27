package pe.projects.rappi.testrappi.repository.datasource.database;

import java.util.List;

import pe.projects.rappi.testrappi.domain.model.MovieModel;

public interface MovieListDatabaseDataStore {

    List<MovieModel> getMoviesByCategory(int category);
    void saveMovies(List<MovieModel> movieModelList, int category);
}
