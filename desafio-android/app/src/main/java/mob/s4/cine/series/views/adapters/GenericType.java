package mob.s4.cine.series.views.adapters;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public enum GenericType {

    LOADING("L"),
    NOT_DATA("ND"),
    MOVIES("M"),
    NOT_NETWORK("NN"),;

    private String abbreviation;

    GenericType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

}
