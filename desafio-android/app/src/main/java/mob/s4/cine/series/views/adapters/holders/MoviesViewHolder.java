package mob.s4.cine.series.views.adapters.holders;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mob.s4.cine.series.R;
import mob.s4.cine.series.contracts.listeners.OnItemClickListener;
import mob.s4.cine.series.pojo.MoviePojo;
import mob.s4.cine.series.utils.MovieUtils;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public final class MoviesViewHolder extends GenericViewHolder<MoviePojo> {

    private View mView;
    private boolean mIsFavorite;
    private ImageView mImageViewMovie, mImageViewSave, mImageViewShare;
    private TextView mTextViewTitle, mTextViewAverage, mTextViewRelease, mTextViewCategory, mTextViewSummary;

    public MoviesViewHolder(View view, boolean isFavorite, OnItemClickListener listener) {
        super(view, listener);

        mView = view;
        mIsFavorite = isFavorite;

        mImageViewMovie = (ImageView) view.findViewById(R.id.imageViewMovie);
        mImageViewSave = (ImageView) view.findViewById(R.id.imageViewSave);
        mImageViewShare = (ImageView) view.findViewById(R.id.imageViewShare);

        mTextViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        mTextViewAverage = (TextView) view.findViewById(R.id.textViewAverage);
        mTextViewRelease = (TextView) view.findViewById(R.id.textViewRelease);
        mTextViewCategory = (TextView) view.findViewById(R.id.textViewCategory);
        mTextViewSummary = (TextView) view.findViewById(R.id.textViewSummary);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MoviePojo movie) {
        super.onBindViewHolder(movie);

        if (mIsFavorite) {
            mImageViewSave.setImageResource(R.drawable.ic_delete);
        } else {
            if (MovieUtils.movieIsSaved(movie.getId())) {
                mImageViewSave.setImageResource(R.drawable.ic_saved);
            } else {
                mImageViewSave.setImageResource(R.drawable.ic_save);
            }
        }

        Glide.with(mView.getContext()).load(movie.getImagePath()).into(mImageViewMovie);
        mTextViewTitle.setText(movie.getTitle());
        mTextViewAverage.setText(movie.getAverage().toString());
        mTextViewRelease.setText(MovieUtils.formatStringDate(movie.getRelease()));
        mTextViewCategory.setText(MovieUtils.getCategory(movie.getGenres()));
        mTextViewSummary.setText(movie.getSummary());

        mImageViewSave.setOnClickListener(this);
        mImageViewShare.setOnClickListener(this);
    }

}
