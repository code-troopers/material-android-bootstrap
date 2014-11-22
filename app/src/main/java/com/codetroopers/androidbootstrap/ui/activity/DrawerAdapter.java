package com.codetroopers.androidbootstrap.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codetroopers.androidbootstrap.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private OnItemClickListener mListener;
    private int mSelectedItem;

    public void setActive(int position) {
        mSelectedItem = position;
        notifyDataSetChanged();
    }

    public DrawerAdapter(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater vi = LayoutInflater.from(parent.getContext());
        View v = vi.inflate(R.layout.drawer_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.name.setText("menu " + position);
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v, position);
            }
        });
        viewHolder.itemView.setActivated(position == mSelectedItem);
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(android.R.id.text1)
        TextView name;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
