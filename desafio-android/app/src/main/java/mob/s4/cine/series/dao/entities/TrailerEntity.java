package mob.s4.cine.series.dao.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class TrailerEntity extends RealmObject {

    @PrimaryKey
    private String mName;
    private String mKey;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

}
