package pe.projects.rappi.testrappi.repository.datasource.ws;

import pe.projects.rappi.testrappi.repository.RepositoryCallback;

public interface MoviesListDataStore {

    void getMoviesList(int categorySelected, int page, RepositoryCallback repositoryCallback);
}
