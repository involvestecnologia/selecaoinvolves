package mob.s4.cine.series.services.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class GenreObject {

    @SerializedName("id") private Integer mId;
    @SerializedName("name") private String mName;

    public Integer getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return "GenreObject{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                '}';
    }
}
