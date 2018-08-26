package mob.s4.cine.series;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.dao.MovieDao;
import mob.s4.cine.series.dao.entities.MovieEntity;
import mob.s4.cine.series.dao.utils.RealmInt;
import mob.s4.cine.series.models.MoviesModel;
import mob.s4.cine.series.pojo.MoviePojo;
import mob.s4.cine.series.services.MovieService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by robsonmoreira on 26/07/18.
 */

public class MoviesUnitTest {

    @Test
    public void getPopularMovies_notNull() throws Exception {
        assertNotNull(MovieService.getPopularMovies(1));
    }

    @Test
    public void isSaved_isTrue() throws Exception {
        MoviesModel model = new MoviesModel(null);
        MovieEntity movie = MovieDao.getMovieById(385360);

        List<Integer> genres = new ArrayList<>();
        for (RealmInt realmInt : movie.getGenres()) {
            genres.add(realmInt.getInteger());
        }

        MoviePojo moviePojo = new MoviePojo(
                movie.getId(),
                movie.getTitle(),
                movie.getAverage(),
                movie.getSummary(),
                movie.getRelease(),
                movie.getImagePath(),
                genres);

        assertTrue(model.isSaved(moviePojo));
    }

}
