package pe.projects.rappi.testrappi.repository.datasource;

import pe.projects.rappi.testrappi.data.rest.ApiClient;
import pe.projects.rappi.testrappi.domain.storage.db.DaoFactory;
import pe.projects.rappi.testrappi.repository.datasource.database.DatabaseMovieListDataStore;
import pe.projects.rappi.testrappi.repository.datasource.database.MovieListDatabaseDataStore;
import pe.projects.rappi.testrappi.repository.datasource.ws.NetworkMoviesListDataStore;

public class MoviesListDataStoreFactory {

    public MoviesListDataStore createSource(){
        MoviesListDataStore moviesListDataStore = createNetworkDataStore();
        return moviesListDataStore;
    }

    public MoviesListDataStore createNetworkDataStore() {
        ApiClient restApi = new ApiClient();
        return new NetworkMoviesListDataStore(restApi);
    }

    public MovieListDatabaseDataStore createMovieDatabase(){
        MovieListDatabaseDataStore movieListDatabaseDataStore = new DatabaseMovieListDataStore(
                new DaoFactory());
        return movieListDatabaseDataStore;
    }
}
