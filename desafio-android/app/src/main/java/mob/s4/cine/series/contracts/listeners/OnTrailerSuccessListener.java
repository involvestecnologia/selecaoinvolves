package mob.s4.cine.series.contracts.listeners;

import java.util.List;

import mob.s4.cine.series.services.objects.TrailerObject;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public interface OnTrailerSuccessListener {

    void onSuccess(List<TrailerObject> trailers);

}
