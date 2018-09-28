package pe.projects.rappi.testrappi.domain.interactor;

public interface MoviesListCallback<T> {

    void onGetMovieListSuccess(T object);
    void onGetMovieListOfflineSuccess(T object);
    void onGetMovieListFailure(T object);
    void onGetMovieListOfflineFailure(T object);
    void onMessageError();
}
