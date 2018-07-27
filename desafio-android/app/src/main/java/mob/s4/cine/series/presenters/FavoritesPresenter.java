package mob.s4.cine.series.presenters;

import java.util.List;

import mob.s4.cine.series.contracts.FavoritesContract;
import mob.s4.cine.series.models.FavoritesModel;
import mob.s4.cine.series.pojo.MoviePojo;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class FavoritesPresenter implements FavoritesContract.FavoritesPresenterContract {

    private FavoritesContract.FavoritesViewContract mView;
    private FavoritesModel mModel;

    public FavoritesPresenter(FavoritesContract.FavoritesViewContract view) {
        mView = view;
        mModel = new FavoritesModel(this);
    }

    @Override
    public void getMovieList() {
        mView.clearMovieList();
        mModel.getMovieList();
    }

    @Override
    public void updateMovieList(List<MoviePojo> movies) {
        if (movies != null && movies.size() > 0) {
            mView.updateMovieList(movies);
        } else {
            mView.showNotData();
        }
    }

    @Override
    public void remove(MoviePojo movie) {
        mModel.remove(movie);
    }

    @Override
    public void searchMovie(String text) {
        if (text != null && !text.isEmpty()) {
            mView.clearMovieList();
        }
        mModel.searchMovie(text);
    }

}
