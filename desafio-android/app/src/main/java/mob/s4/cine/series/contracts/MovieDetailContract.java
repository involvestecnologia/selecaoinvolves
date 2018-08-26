package mob.s4.cine.series.contracts;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public interface MovieDetailContract {

    interface MovieDetailModelContract {
        void setMovie(Serializable movie);
        void getTrailers();
    }

    interface MovieDetailViewContract {
        void bindToolBar();
        void bindElmentes();
        void setTitle(String title, String imagePath);
        void setProgress(Double progress);
        void setAverage(Double average);
        void setGenre(List<Integer> genre);
        void setRelease(String release);
        void setSummary(String summary);
        void showProgressBar(boolean visibility);
        void intentTrailer(String trailer);
        void showMesssageError();
    }

    interface MovieDetailPresenterContract {
        void getExtras(Serializable movie);
        void getTrailers();
        void setTitle(String title, String imagePath);
        void setProgress(Double progress);
        void setAverage(Double average);
        void setGenre(List<Integer> genre);
        void setRelease(String release);
        void setSummary(String summary);
        void trailerSuccess(String trailer);
        void trailerError();
    }

}
