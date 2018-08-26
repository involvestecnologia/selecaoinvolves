package mob.s4.cine.series.contracts;

import java.util.List;

import mob.s4.cine.series.pojo.MoviePojo;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public interface MoviesContract {

    interface MoviesModelContract {
        void getMovieList(int page);
        boolean isSaved(MoviePojo movie);
        void save(MoviePojo movie);
        void remove(MoviePojo movie);
        void searchMovie(String text);
    }

    interface MoviesViewContract {
        void updateMovieList(List<MoviePojo> movies, boolean loading);
        void showProgressBar(int visibility);
        void clearMovieList();
        void showNotNetwork();
        void showNotData();
    }

    interface MoviesPresenterContract {
        void getMovieList(int page);
        void updateMovieList(List<MoviePojo> movies);
        boolean isSaved(MoviePojo movie);
        void save(MoviePojo movie);
        void remove(MoviePojo movie);
        void searchMovie(String text);
    }

}
