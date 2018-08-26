package mob.s4.cine.series.dao.utils;

import io.realm.RealmObject;

/**
 * Created by robsonmoreira on 26/07/18.
 */

public class RealmInt extends RealmObject {

    private Integer mInteger;

    public Integer getInteger() {
        return mInteger;
    }

    public void setInteger(Integer integer) {
        mInteger = integer;
    }

}
