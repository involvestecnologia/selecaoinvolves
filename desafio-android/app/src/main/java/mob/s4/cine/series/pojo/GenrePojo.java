package mob.s4.cine.series.pojo;

import java.io.Serializable;

/**
 * Created by robsonmoreira on 25/07/18.
 */

public class GenrePojo implements Serializable {

    private Integer id;
    private String name;

    public GenrePojo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
