package mob.s4.cine.series.models;

import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.contracts.FavoritesContract;
import mob.s4.cine.series.dao.MovieDao;
import mob.s4.cine.series.dao.entities.MovieEntity;
import mob.s4.cine.series.dao.utils.RealmInt;
import mob.s4.cine.series.pojo.MoviePojo;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class FavoritesModel implements FavoritesContract.FavoritesModelContract {

    private FavoritesContract.FavoritesPresenterContract mPresenter;

    public FavoritesModel(FavoritesContract.FavoritesPresenterContract presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getMovieList() {
        List<MoviePojo> movieList = new ArrayList<>();
        List<MovieEntity> movies = MovieDao.getMovies();
        for (MovieEntity entity : movies) {
            List<Integer> genres = new ArrayList<>();
            for (RealmInt realmInt : entity.getGenres()) {
                genres.add(realmInt.getInteger());
            }
            MoviePojo moviePojo = new MoviePojo(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getAverage(),
                    entity.getSummary(),
                    entity.getRelease(),
                    entity.getImagePath(),
                    genres);
            movieList.add(moviePojo);
        }
        mPresenter.updateMovieList(movieList);
    }

    @Override
    public void remove(MoviePojo movie) {
        MovieDao.removeMovie(movie);
    }

    @Override
    public void searchMovie(String text) {
        List<MoviePojo> movieList = new ArrayList<>();
        List<MovieEntity> movies = MovieDao.searchMovies(text);
        for (MovieEntity entity : movies) {
            List<Integer> genres = new ArrayList<>();
            for (RealmInt realmInt : entity.getGenres()) {
                genres.add(realmInt.getInteger());
            }
            MoviePojo moviePojo = new MoviePojo(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getAverage(),
                    entity.getSummary(),
                    entity.getRelease(),
                    entity.getImagePath(),
                    genres);
            movieList.add(moviePojo);
        }
        mPresenter.updateMovieList(movieList);
    }

}
