package pe.projects.rappi.testrappi.data.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import pe.projects.rappi.testrappi.data.entity.MovieEntity;
import pe.projects.rappi.testrappi.data.entity.response.MoviesListResponse;
import pe.projects.rappi.testrappi.domain.model.ListMovieModel;
import pe.projects.rappi.testrappi.domain.model.MovieModel;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MovieListDataMapperTest {

    private static final double FAKE_POPULARITY = 1.1;
    private static final int FAKE_ID = 1;
    private static final boolean FAKE_VIDEO = false;
    private static final int FAKE_VOTE_COUNT = 2;
    private static final double FAKE_VOTE_AVERAGE = 1.1;
    private static final String FAKE_TITLE = "titulo";
    private static final String FAKE_RELEASE_DATE = "12/12/2020";
    private static final String FAKE_ORIGINAL_LANGUAGE = "es";
    private static final String FAKE_ORIGINAL_TITLE = "original title";
    private static final Integer[] FAKE_GENRE_IDS = new Integer[2];
    private static final String FAKE_BACKDROP_PATH = "backdrop path";
    private static final boolean FAKE_ADULT = false;
    private static final String FAKE_OVERVIEW = "overview";
    private static final String FAKE_POSTER_PATH = "poster path";

    private static final int FAKE_PAGE = 1;
    private static final int FAKE_TOTAL_PAGES = 2;
    private static final int FAKE_TOTAL_RESULTS = 3;

    private MovieListDataMapper movieListDataMapper;

    @Before
    public void setup() throws Exception {
        movieListDataMapper = new MovieListDataMapper();
    }

    @Test
    public void testTransformMovieEntity(){
        MovieEntity movieEntity = createFakeMovieEntity();
        MovieModel movieModel = movieListDataMapper.transformMovieEntity(movieEntity);

        assertThat(movieModel, is(instanceOf(MovieModel.class)));
        assertThat(movieModel.getId(), is(String.valueOf(FAKE_ID)));
        assertThat(movieModel.getPopularity(), is(FAKE_POPULARITY));
    }

    @Test
    public void testTransformListMovies() {
        MovieEntity mockMovieEntityOne = mock(MovieEntity.class);
        MovieEntity mockMovieEntityTwo = mock(MovieEntity.class);

        List<MovieEntity> moviesEntityList = new ArrayList<>(5);
        moviesEntityList.add(mockMovieEntityOne);
        moviesEntityList.add(mockMovieEntityTwo);
        MoviesListResponse moviesListResponse = createFakeMoviesListResponse(moviesEntityList);
        ListMovieModel listMovieModel = movieListDataMapper.transformMovieListModel(moviesListResponse);

        assertThat(listMovieModel.getResults().get(0), is(instanceOf(MovieModel.class)));
        assertThat(listMovieModel.getResults().get(1), is(instanceOf(MovieModel.class)));
        assertThat(listMovieModel.getResults().size(), is(2));
    }

    private MovieEntity createFakeMovieEntity() {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setPopularity(FAKE_POPULARITY);
        movieEntity.setId(FAKE_ID);
        movieEntity.setVideo(FAKE_VIDEO);
        movieEntity.setVote_count(FAKE_VOTE_COUNT);
        movieEntity.setVote_average(FAKE_VOTE_AVERAGE);
        movieEntity.setTitle(FAKE_TITLE);
        movieEntity.setRelease_date(FAKE_RELEASE_DATE);
        movieEntity.setOriginal_language(FAKE_ORIGINAL_LANGUAGE);
        movieEntity.setOriginal_title(FAKE_ORIGINAL_TITLE);
        movieEntity.setGenre_ids(FAKE_GENRE_IDS);
        movieEntity.setBackdrop_path(FAKE_BACKDROP_PATH);
        movieEntity.setAdult(FAKE_ADULT);
        movieEntity.setOverview(FAKE_OVERVIEW);
        movieEntity.setPoster_path(FAKE_POSTER_PATH);
        return movieEntity;
    }

    private MoviesListResponse createFakeMoviesListResponse(List<MovieEntity> movieEntityList) {
        MoviesListResponse moviesListResponse = new MoviesListResponse();
        moviesListResponse.setPage(FAKE_PAGE);
        moviesListResponse.setTotal_pages(FAKE_TOTAL_PAGES);
        moviesListResponse.setTotal_results(FAKE_TOTAL_RESULTS);
        moviesListResponse.setResults(movieEntityList);
        return moviesListResponse;
    }
}
