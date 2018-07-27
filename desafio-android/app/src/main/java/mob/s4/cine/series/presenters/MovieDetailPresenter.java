package mob.s4.cine.series.presenters;

import java.io.Serializable;
import java.util.List;

import mob.s4.cine.series.contracts.MovieDetailContract;
import mob.s4.cine.series.models.MovieDetailModel;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class MovieDetailPresenter implements MovieDetailContract.MovieDetailPresenterContract {

    private MovieDetailContract.MovieDetailViewContract mView;
    private MovieDetailModel mModel;

    public MovieDetailPresenter(MovieDetailContract.MovieDetailViewContract movieDetailViewContract) {
        mView = movieDetailViewContract;
        mModel = new MovieDetailModel(this);
    }

    @Override
    public void getExtras(Serializable movie) {
        mView.bindToolBar();
        mView.bindElmentes();

        mModel.setMovie(movie);
    }

    @Override
    public void getTrailers() {
        mView.showProgressBar(true);
        mModel.getTrailers();
    }

    @Override
    public void setTitle(String title, String imagePath) {
        mView.setTitle(title, imagePath);
    }

    @Override
    public void setProgress(Double progress) {
        mView.setProgress(progress);
    }

    @Override
    public void setAverage(Double average) {
        mView.setAverage(average);
    }

    @Override
    public void setGenre(List<Integer> genre) {
        mView.setGenre(genre);
    }

    @Override
    public void setRelease(String release) {
        mView.setRelease(release);
    }

    @Override
    public void setSummary(String summary) {
        mView.setSummary(summary);
    }

    @Override
    public void trailerSuccess(String trailer) {
        mView.showProgressBar(false);
        mView.intentTrailer(trailer);
    }

    @Override
    public void trailerError() {
        mView.showProgressBar(false);
        mView.showMesssageError();
    }

}
