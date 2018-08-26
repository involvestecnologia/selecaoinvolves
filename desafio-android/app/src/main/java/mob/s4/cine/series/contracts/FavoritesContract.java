package mob.s4.cine.series.contracts;

import java.util.List;

import mob.s4.cine.series.pojo.MoviePojo;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public interface FavoritesContract {

    interface FavoritesModelContract {
        void getMovieList();
        void remove(MoviePojo movie);
        void searchMovie(String text);
    }

    interface FavoritesViewContract {
        void updateMovieList(List<MoviePojo> movies);
        void showNotData();
        void clearMovieList();
    }

    interface FavoritesPresenterContract {
        void getMovieList();
        void updateMovieList(List<MoviePojo> movies);
        void remove(MoviePojo movie);
        void searchMovie(String text);
    }

}
