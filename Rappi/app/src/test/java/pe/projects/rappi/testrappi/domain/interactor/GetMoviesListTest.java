package pe.projects.rappi.testrappi.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pe.projects.rappi.testrappi.domain.repository.MoviesListRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetMoviesListTest {

    private static final int FAKE_CATEGORY_SELECTED = 1;
    private static final int FAKE_PAGE = 2;

    @Mock private MoviesListCallback mockMoviesListCallback;

    @Mock private MoviesListRepository moviesListRepository;

    @Test
    public void testGetMovies(){
        moviesListRepository.getMovies(FAKE_CATEGORY_SELECTED, FAKE_PAGE, mockMoviesListCallback);
        verify(moviesListRepository).getMovies(FAKE_CATEGORY_SELECTED, FAKE_PAGE, mockMoviesListCallback);
    }
}
