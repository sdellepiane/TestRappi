package pe.projects.rappi.testrappi.data.mapper;

import java.util.ArrayList;
import java.util.List;

import pe.projects.rappi.testrappi.BuildConfig;
import pe.projects.rappi.testrappi.Constants;
import pe.projects.rappi.testrappi.data.entity.MovieEntity;
import pe.projects.rappi.testrappi.data.entity.response.MoviesListResponse;
import pe.projects.rappi.testrappi.domain.model.ListMovieModel;
import pe.projects.rappi.testrappi.domain.model.MovieModel;

public class MovieListDataMapper {

    public ListMovieModel transformMovieListModel(MoviesListResponse moviesListResponse){
        ListMovieModel listMovieModel = new ListMovieModel();
        List<MovieModel> movieModelList = new ArrayList<>();
        listMovieModel.setPage(moviesListResponse.getPage());
        listMovieModel.setTotalPages(moviesListResponse.getTotal_pages());
        listMovieModel.setTotalResults(moviesListResponse.getTotal_results());
        if(moviesListResponse.getResults() != null && moviesListResponse.getResults().size() > 0){
            for(MovieEntity movieEntity : moviesListResponse.getResults()){
                movieModelList.add(transformMovieEntity(movieEntity));
            }
        }
        listMovieModel.setResults(movieModelList);

        return listMovieModel;
    }

    public MovieModel transformMovieEntity(MovieEntity movieEntity){
        MovieModel movieModel = null;
        if(movieEntity != null){
            movieModel = new MovieModel();
            movieModel.setPopularity(movieEntity.getPopularity());
            movieModel.setId(String.valueOf(movieEntity.getId()));
            movieModel.setVideo(movieEntity.isVideo());
            movieModel.setVoteCount(movieEntity.getVote_count());
            movieModel.setVoteAverage(movieEntity.getVote_average());
            movieModel.setTitle(movieEntity.getTitle() != null ? movieEntity.getTitle().substring(0, 1).toUpperCase()
                    + movieEntity.getTitle().substring(1) : Constants.EMPTY_STRING);
            movieModel.setReleaseDate(movieEntity.getRelease_date() != null ? movieEntity.getRelease_date() : Constants.EMPTY_STRING);
            movieModel.setOriginalLanguage(movieEntity.getOriginal_language() != null ? movieEntity.getOriginal_language() : Constants.EMPTY_STRING);
            movieModel.setOriginalTitle(movieEntity.getOriginal_title() != null ? movieEntity.getOriginal_title() : Constants.EMPTY_STRING);
            movieModel.setBackdropPath(movieEntity.getBackdrop_path() != null ? BuildConfig.URL_IMAGE + movieEntity.getBackdrop_path() : null);
            movieModel.setAdult(movieEntity.isAdult());
            movieModel.setOverview(movieEntity.getOverview() != null ? movieEntity.getOverview() : Constants.EMPTY_STRING);
            movieModel.setPosterPath(movieEntity.getPoster_path() != null ? BuildConfig.URL_IMAGE + movieEntity.getPoster_path() : null);
        }
        return movieModel;
    }
}
