package pe.projects.rappi.testrappi.domain.storage.db;

import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;

import java.util.List;
import java.util.concurrent.Callable;

import pe.projects.rappi.testrappi.app.ui.RappiApplication;
import pe.projects.rappi.testrappi.domain.model.MovieModel;

public class DaoFactory {

    private DatabaseHelper db;
    private Dao<MovieModel, Integer> movieDAO;

    public DaoFactory() {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(RappiApplication.getAppContext());
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    public Dao<MovieModel, Integer> getMovieDao() throws java.sql.SQLException {
        if (movieDAO == null) {
            movieDAO = db.getDao(MovieModel.class);
        }
        return movieDAO;
    }

    public void createUpdateMovies(final List<MovieModel> movieModelList, final int category) {
        try {
            TransactionManager.callInTransaction(movieDAO.getConnectionSource(), new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    for(MovieModel guest : movieModelList){
                        guest.setCategory(category);
                        movieDAO.createOrUpdate(guest);
                    }
                    return null;
                }
            });
        }  catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MovieModel> getMovieListByCategory(int category) {
        try {
            List<MovieModel> movieModelList = movieDAO.queryBuilder().where().eq("category", category).query();
            return movieModelList;

        } catch (java.sql.SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }
}
