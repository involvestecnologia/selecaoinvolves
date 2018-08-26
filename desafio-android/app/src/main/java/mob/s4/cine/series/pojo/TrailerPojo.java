package mob.s4.cine.series.pojo;

import java.io.Serializable;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class TrailerPojo implements Serializable {

    private String mName;
    private String mKey;

    public TrailerPojo(String name, String key) {
        mName = name;
        mKey = key;
    }

    public String getName() {
        return mName;
    }

    public String getKey() {
        return mKey;
    }

}
