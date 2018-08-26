package mob.s4.cine.series.models;

import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.contracts.MoviesContract;
import mob.s4.cine.series.contracts.listeners.OnMovieSuccessListener;
import mob.s4.cine.series.dao.MovieDao;
import mob.s4.cine.series.pojo.MoviePojo;
import mob.s4.cine.series.services.tasks.MovieAsyncTask;
import mob.s4.cine.series.services.objects.MovieObject;
import mob.s4.cine.series.services.tasks.SearchMovieAsyncTask;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class MoviesModel implements MoviesContract.MoviesModelContract, OnMovieSuccessListener {

    private MoviesContract.MoviesPresenterContract mPresenter;

    public MoviesModel(MoviesContract.MoviesPresenterContract moviesPresenterContract) {
        mPresenter = moviesPresenterContract;
    }

    @Override
    public void getMovieList(int page) {
        MovieAsyncTask movieAsyncTask = new MovieAsyncTask(this);
        movieAsyncTask.execute(page);
    }

    @Override
    public boolean isSaved(MoviePojo movie) {
        return MovieDao.getMovieById(movie.getId()) != null;
    }

    @Override
    public void save(MoviePojo movie) {
        MovieDao.saveMovie(movie);
    }

    @Override
    public void remove(MoviePojo movie) {
        MovieDao.removeMovie(movie);
    }

    @Override
    public void searchMovie(String text) {
        SearchMovieAsyncTask searchMovieAsyncTask = new SearchMovieAsyncTask(this);
        searchMovieAsyncTask.execute(text);
    }

    @Override
    public void onSuccess(List<MovieObject> movies) {
        List<MoviePojo> movieList = new ArrayList<>();
        for (MovieObject object : movies) {
            MoviePojo moviePojo = new MoviePojo(
                    object.getId(),
                    object.getTitle(),
                    object.getAverage(),
                    object.getSummary(),
                    object.getRelease(),
                    object.getImagePath(),
                    object.getGenres());
            movieList.add(moviePojo);
        }
        mPresenter.updateMovieList(movieList);
    }

}
