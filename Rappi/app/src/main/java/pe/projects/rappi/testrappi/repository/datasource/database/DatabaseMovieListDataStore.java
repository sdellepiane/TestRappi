package pe.projects.rappi.testrappi.repository.datasource.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.projects.rappi.testrappi.domain.model.MovieModel;
import pe.projects.rappi.testrappi.domain.storage.db.DaoFactory;

public class DatabaseMovieListDataStore implements MovieListDatabaseDataStore{

    private DaoFactory daoFactory;

    public DatabaseMovieListDataStore(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<MovieModel> getMoviesByCategory(int category) {
        List<MovieModel> movieModelList = new ArrayList<>();
        try {
            daoFactory.getMovieDao();
            movieModelList = daoFactory.getMovieListByCategory(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(movieModelList == null){
            return new ArrayList<>();
        }
        return movieModelList;
    }

    @Override
    public void saveMovies(List<MovieModel> movieModelList, int category) {
        try {
            daoFactory.getMovieDao();
            daoFactory.createUpdateMovies(movieModelList, category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
