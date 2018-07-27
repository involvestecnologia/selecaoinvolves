package mob.s4.cine.series.views.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mob.s4.cine.series.R;
import mob.s4.cine.series.contracts.MovieDetailContract;
import mob.s4.cine.series.presenters.MovieDetailPresenter;
import mob.s4.cine.series.utils.MovieUtils;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.MovieDetailViewContract, View.OnClickListener {

    public static final String MOVIE = "movie";

    private ProgressBar mProgressBarAverage;
    private TextView mTextViewAverage;
    private TextView mTextViewGenre;
    private TextView mTextViewRelease;
    private TextView mTextViewSummary;
    private ProgressDialog mProgressDialog;

    MovieDetailPresenter mMovieDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (mMovieDetailPresenter == null) {
            mMovieDetailPresenter = new MovieDetailPresenter(this);
        }
        mMovieDetailPresenter.getExtras(getIntent().getExtras().getSerializable(MOVIE));
    }

    @Override
    public void bindToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void bindElmentes() {
        mProgressBarAverage = (ProgressBar) findViewById(R.id.progressBarAverage);
        mTextViewAverage = (TextView) findViewById(R.id.textViewAverage);
        mTextViewGenre = (TextView) findViewById(R.id.textViewGenre);
        mTextViewRelease = (TextView) findViewById(R.id.textViewRelease);
        mTextViewSummary = (TextView) findViewById(R.id.textViewSummary);

        mProgressDialog = new ProgressDialog(this, R.style.AlertDialogCustom);
        mProgressDialog.setMessage("Aguarde...");
        mProgressDialog.setCancelable(false);

        LinearLayout linearLayoutTrailer = (LinearLayout) findViewById(R.id.linearLayoutTrailer);
        linearLayoutTrailer.setOnClickListener(this);
    }

    @Override
    public void setTitle(String title, String imagePath) {
        setTitle(title);
        Glide.with(this).load(imagePath).into(((ImageView) findViewById(R.id.backdrop)));
    }

    @Override
    public void setProgress(Double progress) {
        mProgressBarAverage.setProgress((int) (progress * 10));
    }

    @Override
    public void setAverage(Double average) {
        mTextViewAverage.setText(getAverage(average));
    }

    @Override
    public void setGenre(List<Integer> genre) {
        mTextViewGenre.setText(MovieUtils.getCategory(genre));
    }

    @Override
    public void setRelease(String release) {
        mTextViewRelease.setText(release);
    }

    @Override
    public void setSummary(String summary) {
        mTextViewSummary.setText(summary);
    }

    @Override
    public void showProgressBar(boolean visibility) {
        if (visibility) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void intentTrailer(String trailer) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:".concat(trailer)));
        appIntent.putExtra("force_fullscreen", true);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=".concat(trailer)));
        webIntent.putExtra("force_fullscreen", true);
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    @Override
    public void showMesssageError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle("Erro");
        builder.setMessage("Não foi possível carregar o trailer para o filme");
        builder.setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearLayoutTrailer:
                mMovieDetailPresenter.getTrailers();
                break;
        }
    }

    private String getAverage(Double aDouble) {
        Double average = aDouble * 10;
        return String.valueOf(average.intValue()).concat("%");
    }

}
