package mob.s4.cine.series.views.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import mob.s4.cine.series.contracts.listeners.OnItemClickListener;

/**
 * Created by robsonmoreira on 24/07/18.
 */

public class GenericViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private T mItem;
    private View mRootView;
    private OnItemClickListener mListener;

    GenericViewHolder(View itemView) {
        this(itemView, null);
    }

    GenericViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);

        mRootView = itemView;
        mListener = listener;
    }

    public void onBindViewHolder(final T item) {
        mItem = item;
        mRootView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClick(view, mItem);
        }
    }

}
