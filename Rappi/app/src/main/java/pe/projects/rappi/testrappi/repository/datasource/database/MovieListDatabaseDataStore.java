package pe.projects.rappi.testrappi.repository.datasource.database;

import java.util.List;

import pe.projects.rappi.testrappi.domain.model.MovieModel;
import pe.projects.rappi.testrappi.repository.RepositoryCallback;

public interface MovieListDatabaseDataStore {

    void getMoviesByCategory(int category, RepositoryCallback repositoryCallback);
    void saveMovies(List<MovieModel> movieModelList, int category);
}
