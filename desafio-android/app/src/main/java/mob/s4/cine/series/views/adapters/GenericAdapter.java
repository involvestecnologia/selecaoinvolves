package mob.s4.cine.series.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mob.s4.cine.series.R;
import mob.s4.cine.series.pojo.MoviePojo;
import mob.s4.cine.series.views.adapters.holders.GenericViewHolder;
import mob.s4.cine.series.views.adapters.holders.LoadingViewHolder;
import mob.s4.cine.series.views.adapters.holders.MoviesViewHolder;
import mob.s4.cine.series.views.adapters.holders.NoDataViewHolder;
import mob.s4.cine.series.contracts.listeners.OnItemClickListener;
import mob.s4.cine.series.views.adapters.holders.NoNetowrkViewHolder;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class GenericAdapter extends RecyclerView.Adapter<GenericViewHolder> {

    private List<Object> mItens;
    private boolean mIsFavorite;
    private OnItemClickListener mListener;

    public GenericAdapter(List<Object> item, boolean isFavorite, OnItemClickListener listener) {
        mItens = item;
        mIsFavorite = isFavorite;
        mListener = listener;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == GenericType.MOVIES.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_movies_itens, parent, false);
            return new MoviesViewHolder(view, mIsFavorite, mListener);
        } else if (viewType == GenericType.LOADING.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loading, parent, false);
            return new LoadingViewHolder(view);
        } else if (viewType == GenericType.NOT_DATA.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_not_data, parent, false);
            return new NoDataViewHolder(view);
        } else if (viewType == GenericType.NOT_NETWORK.ordinal()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_not_network, parent, false);
            return new NoNetowrkViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        Object object = mItens.get(position);
        holder.onBindViewHolder(object);
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mItens.get(position);
        if (item instanceof MoviePojo)
            return GenericType.MOVIES.ordinal();
        else if (item.toString().equals(GenericType.LOADING.getAbbreviation()))
            return GenericType.LOADING.ordinal();
        else if (item.toString().equals(GenericType.NOT_DATA.getAbbreviation()))
            return GenericType.NOT_DATA.ordinal();
        else if (item.toString().equals(GenericType.NOT_NETWORK.getAbbreviation()))
            return GenericType.NOT_NETWORK.ordinal();
        else return -1;
    }

    @Override
    public int getItemCount() {
        return mItens != null ? mItens.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public void updateList(List<Object> itens, boolean loading) {
        mItens = itens;
        if (loading) {
            if (mItens.size() > 0) {
                mItens.add(GenericType.LOADING.getAbbreviation());
            }
        }
        notifyDataSetChanged();
    }

    public void removeItemList(Object object) {
        mItens.remove(object);
        notifyDataSetChanged();
        if (mItens.isEmpty()) {
            showNotData();
        }
    }

    public void showNotData() {
        mItens.clear();
        mItens.add(GenericType.NOT_DATA.getAbbreviation());
        notifyDataSetChanged();
    }

    public void showNotNetowrk() {
        mItens.clear();
        mItens.add(GenericType.NOT_NETWORK.getAbbreviation());
        notifyDataSetChanged();
    }

    public void clearData() {
        mItens.clear();
        notifyDataSetChanged();
    }

}
