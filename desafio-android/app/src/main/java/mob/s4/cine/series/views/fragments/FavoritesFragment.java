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
import mob.s4.cine.series.contracts.FavoritesContract;
import mob.s4.cine.series.contracts.listeners.OnItemClickListener;
import mob.s4.cine.series.pojo.MoviePojo;
import mob.s4.cine.series.presenters.FavoritesPresenter;
import mob.s4.cine.series.views.activities.MovieDetailActivity;
import mob.s4.cine.series.views.adapters.GenericAdapter;

public class FavoritesFragment extends Fragment implements FavoritesContract.FavoritesViewContract, OnItemClickListener {

    private View mView;
    private GenericAdapter mAdapter;
    private RecyclerView mRecyclerView;

    private List<Object> mMovieList = new ArrayList<>();

    FavoritesPresenter mFavoritesPresenter;

    public FavoritesFragment() {
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_favorites, container, false);

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));

        mAdapter = new GenericAdapter(new ArrayList<>(), true, this);
        mRecyclerView.setAdapter(mAdapter);

        if (mFavoritesPresenter == null) {
            mFavoritesPresenter = new FavoritesPresenter(this);
        }
        mFavoritesPresenter.getMovieList();

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
                    mFavoritesPresenter.searchMovie(query);
                } else {
                    mFavoritesPresenter.getMovieList();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    mFavoritesPresenter.searchMovie(newText);
                } else {
                    mFavoritesPresenter.getMovieList();
                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void updateMovieList(List<MoviePojo> movies) {
        if (mMovieList != null && mMovieList.size() > 0)
            mMovieList.remove(mMovieList.size() - 1);

        if (movies != null && mMovieList != null)
            mMovieList.addAll(movies);

        mAdapter.updateList(mMovieList, false);
    }

    @Override
    public void showNotData() {
        mAdapter.showNotData();
    }

    @Override
    public void clearMovieList() {
        mAdapter.clearData();
    }

    @Override
    public void onItemClick(View view, Object object) {
        switch (view.getId()) {
            case R.id.imageViewSave:
                mFavoritesPresenter.remove((MoviePojo) object);
                mAdapter.removeItemList(object);
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

}
