package pe.projects.rappi.testrappi.data.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pe.projects.rappi.testrappi.BuildConfig;
import pe.projects.rappi.testrappi.data.entity.response.MoviesListResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiClient {

    public LaLigaInterface getTMDBInterface() {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(BuildConfig.HOST)
                .client(getBasicClientInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(LaLigaInterface.class);
    }

    public interface LaLigaInterface {

        @GET("movie/popular")
        Call<MoviesListResponse> getPopularMovies(@Query("api_key") String api_key,
                                                     @Query("language") String language,
                                                     @Query("page") int page);

        @GET("movie/top_rated")
        Call<MoviesListResponse> getTopRatedMovies(@Query("api_key") String api_key,
                                                     @Query("language") String language,
                                                     @Query("page") int page);

        @GET("movie/upcoming")
        Call<MoviesListResponse> getUpcomingMovies(@Query("api_key") String api_key,
                                                     @Query("language") String language,
                                                     @Query("page") int page);
    }

    public static OkHttpClient getBasicClientInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(logging);
        builder.readTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        return client;
    }
}
