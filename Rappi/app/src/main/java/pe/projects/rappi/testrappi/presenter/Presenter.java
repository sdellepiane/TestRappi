package pe.projects.rappi.testrappi.presenter;

public interface Presenter<V> {

    void attachedView(V view);
    void detachView();
}
