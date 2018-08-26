package mob.s4.cine.series.presenters;

import android.view.View;

import java.util.List;

import mob.s4.cine.series.CineSeriesApplication;
import mob.s4.cine.series.contracts.MoviesContract;
import mob.s4.cine.series.pojo.MoviePojo;
import mob.s4.cine.series.models.MoviesModel;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class MoviesPresenter implements MoviesContract.MoviesPresenterContract {

    private MoviesContract.MoviesViewContract mView;
    private MoviesModel mModel;

    public MoviesPresenter(MoviesContract.MoviesViewContract moviesViewContract) {
        mView = moviesViewContract;
        mModel = new MoviesModel(this);
    }

    @Override
    public void getMovieList(int page) {
        mView.clearMovieList();
        if (CineSeriesApplication.isNetworkAvailable()) {
            if (page == 1) {
                mView.showProgressBar(View.VISIBLE);
            }
            mModel.getMovieList(page);
        } else {
            mView.showNotNetwork();
        }
    }

    @Override
    public void updateMovieList(List<MoviePojo> movies) {
        mView.showProgressBar(View.GONE);

        if (movies !=null && movies.size() > 0) {
            mView.updateMovieList(movies, movies != null);
        } else {
            mView.showNotData();
        }
    }

    @Override
    public boolean isSaved(MoviePojo movie) {
        return mModel.isSaved(movie);
    }

    @Override
    public void save(MoviePojo movie) {
        mModel.save(movie);
    }

    @Override
    public void remove(MoviePojo movie) {
        mModel.remove(movie);
    }

    @Override
    public void searchMovie(String text) {
        if (text != null && !text.isEmpty()) {
            mView.clearMovieList();
            mView.showProgressBar(View.VISIBLE);
        }
        mModel.searchMovie(text);
    }

}
