package mob.s4.cine.series.models;

import java.io.Serializable;
import java.util.List;

import mob.s4.cine.series.contracts.MovieDetailContract;
import mob.s4.cine.series.contracts.listeners.OnTrailerSuccessListener;
import mob.s4.cine.series.pojo.MoviePojo;
import mob.s4.cine.series.services.tasks.TrailerAsyncTask;
import mob.s4.cine.series.services.objects.MovieObject;
import mob.s4.cine.series.services.objects.TrailerObject;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class MovieDetailModel implements MovieDetailContract.MovieDetailModelContract, OnTrailerSuccessListener {

    private MovieDetailContract.MovieDetailPresenterContract mPresenter;
    private MoviePojo mMovie;

    public MovieDetailModel(MovieDetailContract.MovieDetailPresenterContract movieDetailPresenterContract) {
        mPresenter = movieDetailPresenterContract;
    }

    @Override
    public void setMovie(Serializable movie) {
        mMovie = (MoviePojo) movie;

        mPresenter.setTitle(mMovie.getTitle(), mMovie.getImagePath());
        mPresenter.setProgress(mMovie.getAverage());
        mPresenter.setAverage(mMovie.getAverage());
        mPresenter.setGenre(mMovie.getGenres());
        mPresenter.setRelease(mMovie.getRelease());
        mPresenter.setSummary(mMovie.getSummary());
    }

    @Override
    public void getTrailers() {
        TrailerAsyncTask trailerAsyncTask = new TrailerAsyncTask(this);
        trailerAsyncTask.execute(mMovie.getId());
    }

    @Override
    public void onSuccess(List<TrailerObject> trailers) {
        if (trailers.size() > 0) {
            mPresenter.trailerSuccess(trailers.get(0).getKey());
        } else {
            mPresenter.trailerError();
        }
    }

}
