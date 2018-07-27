package mob.s4.cine.series.services.tasks;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.contracts.listeners.OnMovieSuccessListener;
import mob.s4.cine.series.pojo.GenrePojo;
import mob.s4.cine.series.services.GenreService;
import mob.s4.cine.series.services.MovieService;
import mob.s4.cine.series.services.objects.GenreObject;
import mob.s4.cine.series.services.objects.MovieObject;
import mob.s4.cine.series.dao.GenreDao;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class MovieAsyncTask extends AsyncTask<Integer, Void, List<MovieObject>> {

    private OnMovieSuccessListener mOnMovieSuccessListener;

    public MovieAsyncTask(OnMovieSuccessListener onMovieSuccessListener) {
        mOnMovieSuccessListener = onMovieSuccessListener;
    }

    @Override
    protected List<MovieObject> doInBackground(Integer... pages) {
        List<MovieObject> movieList = new ArrayList<>();
        try {
            movieList = MovieService.getPopularMovies(pages[0]);

            List<GenrePojo> pojoList = new ArrayList<>();
            for (GenreObject object : GenreService.getGenreList()) {
                pojoList.add(new GenrePojo(object.getId(), object.getName()));
            }
            GenreDao.saveGenreList(pojoList);

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
