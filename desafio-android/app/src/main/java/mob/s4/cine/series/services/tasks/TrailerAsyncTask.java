package mob.s4.cine.series.services.tasks;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.contracts.listeners.OnTrailerSuccessListener;
import mob.s4.cine.series.services.TrailerService;
import mob.s4.cine.series.services.objects.TrailerObject;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class TrailerAsyncTask extends AsyncTask<Integer, Void, List<TrailerObject>> {

    private OnTrailerSuccessListener mOnTrailerSuccessListener;

    public TrailerAsyncTask(OnTrailerSuccessListener onTrailerSuccessListener) {
        mOnTrailerSuccessListener = onTrailerSuccessListener;
    }

    @Override
    protected List<TrailerObject> doInBackground(Integer... movieId) {
        List<TrailerObject> trailers = new ArrayList<TrailerObject>();
        try {
            trailers = TrailerService.getMovieTrailers(movieId[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trailers;
    }

    @Override
    protected void onPostExecute(List<TrailerObject> trailers) {
        super.onPostExecute(trailers);
        mOnTrailerSuccessListener.onSuccess(trailers);
    }

}
