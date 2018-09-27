package pe.projects.rappi.testrappi.repository.datasource.ws;

import pe.projects.rappi.testrappi.BuildConfig;
import pe.projects.rappi.testrappi.Constants;
import pe.projects.rappi.testrappi.data.entity.response.MoviesListResponse;
import pe.projects.rappi.testrappi.data.rest.ApiClient;
import pe.projects.rappi.testrappi.repository.RepositoryCallback;
import pe.projects.rappi.testrappi.repository.datasource.MoviesListDataStore;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkMoviesListDataStore implements MoviesListDataStore {

    private ApiClient apiClient;

    public NetworkMoviesListDataStore(ApiClient apiClient){
        this.apiClient = apiClient;
    }

    @Override
    public void getMoviesList(int categorySelected, int page, final RepositoryCallback repositoryCallback) {
        Call<MoviesListResponse> call;
        switch(categorySelected){
            case Constants.CATEGORY_POPULAR_SELECTED:
                call = apiClient.getTMDBInterface().getPopularMovies(BuildConfig.API_KEY, BuildConfig.language, page);
                break;
            case Constants.CATEGORY_TOP_RATED_SELECTED:
                call = apiClient.getTMDBInterface().getTopRatedMovies(BuildConfig.API_KEY, BuildConfig.language, page);
                break;
            default:
                call = apiClient.getTMDBInterface().getUpcomingMovies(BuildConfig.API_KEY, BuildConfig.language, page);
                break;
        }

        call.enqueue(new Callback<MoviesListResponse>() {
            @Override
            public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {
                if(response != null){
                    MoviesListResponse moviesListResponse = response.body();
                    repositoryCallback.onSuccess(moviesListResponse);
                } else {
                    repositoryCallback.onCommonMessageError();
                }
            }

            @Override
            public void onFailure(Call<MoviesListResponse> call, Throwable t) {
                repositoryCallback.onFailure(t);
            }
        });
    }
}
