package mob.s4.cine.series.services.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class TrailerObject {

    @SerializedName("name") private String mName;
    @SerializedName("key") private String mKey;

    public String getName() {
        return mName;
    }

    public String getKey() {
        return mKey;
    }

}
