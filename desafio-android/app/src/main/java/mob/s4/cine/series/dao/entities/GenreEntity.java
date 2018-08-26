package mob.s4.cine.series.dao.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class GenreEntity extends RealmObject {

    @PrimaryKey
    private Integer mId;
    private String mName;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

}
