package mob.s4.cine.series.services.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class ParseObject<T> {

    @SerializedName("results") private List<T> mResults;
    @SerializedName("genres") private List<T> mGenres;

    public List<T> getResults() {
        return mResults;
    }

    public List<T> getGenres() {
        return mGenres;
    }

}
