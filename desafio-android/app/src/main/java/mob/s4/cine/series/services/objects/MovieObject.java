package mob.s4.cine.series.services.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class MovieObject {

    private static final String URI_IMAGE = "https://image.tmdb.org/t/p/w500";

    @SerializedName("id") private Integer mId;
    @SerializedName("title") private String mTitle;
    @SerializedName("vote_average") private Double mAverage;
    @SerializedName("overview") private String mSummary;
    @SerializedName("release_date") private String mRelease;
    @SerializedName("backdrop_path") private String mImagePath;
    @SerializedName("genre_ids") private List<Integer> mGenres;
    private boolean mIsSaved;

    public MovieObject(Integer id, String title, Double average, String summary, String year, String imagePath, List<Integer> genres) {
        this.mId = id;
        this.mTitle = title;
        this.mAverage = average;
        this.mSummary = summary;
        this.mRelease = year;
        this.mImagePath = imagePath;
        this.mGenres = genres;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Double getAverage() {
        return mAverage;
    }

    public String getSummary() {
        return mSummary;
    }

    public String getRelease() {
        return mRelease;
    }

    public String getImagePath() {
        return (mImagePath != null) ? URI_IMAGE.concat(mImagePath) : "";
    }

    public List<Integer> getGenres() {
        return mGenres;
    }

    public boolean isSaved() {
        return mIsSaved;
    }

    public void setSaved(boolean saved) {
        mIsSaved = saved;
    }
}
