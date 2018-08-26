package mob.s4.cine.series.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mob.s4.cine.series.R;
import mob.s4.cine.series.pojo.MoviePojo;
import mob.s4.cine.series.views.adapters.EndlessRecyclerOnScrollListener;
import mob.s4.cine.series.views.adapters.GenericAdapter;
import mob.s4.cine.series.contracts.MoviesContract;
import mob.s4.cine.series.contracts.listeners.OnItemClickListener;
import mob.s4.cine.series.presenters.MoviesPresenter;
import mob.s4.cine.series.views.activities.MovieDetailActivity;

public class MoviesFragment extends Fragment implements MoviesContract.MoviesViewContract, OnItemClickListener {

    private View mView;
    private GenericAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.OnScrollListener mOnScrollListener;
    private LinearLayoutManager mLayoutManager;

    private List<Object> mMovieList = new ArrayList<>();
    private int mPage = 1;

    MoviesPresenter mMoviesPresenter;

    public MoviesFragment() {
    }

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_movies, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        refreshScrollListener(mRecyclerView);

        mAdapter = new GenericAdapter(mMovieList, false, this);
        mRecyclerView.setAdapter(mAdapter);

        if (mMoviesPresenter == null) {
            mMoviesPresenter = new MoviesPresenter(this);
        }
        mMoviesPresenter.getMovieList(mPage);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem search = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null && !query.isEmpty()) {
                    mMoviesPresenter.searchMovie(query);
                } else {
                    mMoviesPresenter.getMovieList(1);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    mMoviesPresenter.searchMovie(newText);
                } else {
                    mMoviesPresenter.getMovieList(1);
                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void updateMovieList(List<MoviePojo> movies, boolean loading) {
        if (mMovieList != null && mMovieList.size() > 0)
            mMovieList.remove(mMovieList.size() - 1);

        if (movies != null && mMovieList != null)
            mMovieList.addAll(movies);

        mAdapter.updateList(mMovieList, loading);
    }

    @Override
    public void showProgressBar(int visibility) {
        mView.findViewById(R.id.progress_bar).setVisibility(visibility);
    }

    @Override
    public void clearMovieList() {
        mAdapter.clearData();
    }

    @Override
    public void showNotNetwork() {
        mAdapter.showNotNetowrk();
    }

    @Override
    public void showNotData() {
        mAdapter.showNotData();
    }

    @Override
    public void onItemClick(View view, Object object) {
        switch (view.getId()) {
            case R.id.imageViewSave:
                boolean isSaved = mMoviesPresenter.isSaved((MoviePojo) object);
                if (!isSaved) {
                    mMoviesPresenter.save((MoviePojo) object);
                } else {
                    mMoviesPresenter.remove((MoviePojo) object);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.imageViewShare:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, ((MoviePojo) object).getSummary());
                startActivity(Intent.createChooser(share, "Selecione uma da opções"));
                break;
            default:
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE, (MoviePojo) object);
                startActivity(intent);
                break;
        }
    }

    private void refreshScrollListener(RecyclerView recyclerView) {
        if (mOnScrollListener != null) {
            recyclerView.removeOnScrollListener(mOnScrollListener);
        }
        mOnScrollListener = new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                mPage = current_page;
                mMoviesPresenter.getMovieList(mPage);
            }
        };
        recyclerView.addOnScrollListener(mOnScrollListener);
    }

}
