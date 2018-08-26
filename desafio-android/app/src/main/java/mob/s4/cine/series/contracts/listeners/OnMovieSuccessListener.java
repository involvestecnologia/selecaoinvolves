package mob.s4.cine.series.contracts.listeners;

import java.util.List;

import mob.s4.cine.series.services.objects.MovieObject;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public interface OnMovieSuccessListener {

    void onSuccess(List<MovieObject> movies);

}
