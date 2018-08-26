package mob.s4.cine.series.services.tasks;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.contracts.listeners.OnMovieSuccessListener;
import mob.s4.cine.series.services.MovieService;
import mob.s4.cine.series.services.objects.MovieObject;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class SearchMovieAsyncTask extends AsyncTask<String, Void, List<MovieObject>> {

    private OnMovieSuccessListener mOnMovieSuccessListener;

    public SearchMovieAsyncTask(OnMovieSuccessListener onMovieSuccessListener) {
        mOnMovieSuccessListener = onMovieSuccessListener;
    }

    @Override
    protected List<MovieObject> doInBackground(String... text) {
        List<MovieObject> movieList = new ArrayList<>();
        try {
            movieList = MovieService.searchPopular(text[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    @Override
    protected void onPostExecute(List<MovieObject> movies) {
        super.onPostExecute(movies);
        mOnMovieSuccessListener.onSuccess(movies);
    }

}
