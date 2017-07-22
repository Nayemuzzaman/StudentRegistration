package com.example.user.studentregistration;



import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CustomCursorAdapter extends RecyclerView.Adapter<CustomCursorAdapter.TheViewHolder> {

    LayoutInflater inflater;
    Context c;
    List<Person> lstModel = Collections.emptyList();

    public CustomCursorAdapter(Context c, List<Person> lstModel) {
        inflater = LayoutInflater.from(c);
        this.c = c;
        this.lstModel = lstModel;
    }

    @Override
    public CustomCursorAdapter.TheViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.row_item, parent, false);
        CustomCursorAdapter.TheViewHolder holder = new CustomCursorAdapter.TheViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomCursorAdapter.TheViewHolder holder, int position) {
        Person model = lstModel.get(position);
        holder.textViewPersonName.setText(model.getPerson_name());
        holder.textViewPersonEmail.setText(model.getPerson_email());
        holder.textViewPersonPhone.setText(model.getPerson_phone());
        holder.textViewPersonAddress.setText(model.getPerson_address());
    }

    @Override
    public int getItemCount() {
        return lstModel.size();
    }


    class TheViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPersonName, textViewPersonEmail, textViewPersonPhone, textViewPersonAddress;
        Button call;
        public TheViewHolder(final View itemView) {
            super(itemView);
            textViewPersonName = (TextView) itemView.findViewById(R.id.tv_person_name);
            textViewPersonEmail = (TextView) itemView.findViewById(R.id.tv_person_email);
            textViewPersonPhone = (TextView) itemView.findViewById(R.id.tv_person_phone);
            textViewPersonAddress = (TextView) itemView.findViewById(R.id.tv_person_address);
            call = (Button) itemView.findViewById(R.id.call_button);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerSync.onItemClick(textViewPersonPhone, lstModel, getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRecycleViewItemClick(itemView, lstModel, getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longlistener.onRecycleViewLongItemClick(itemView, lstModel, getAdapterPosition());
                    return false;
                }
            });
        }
    }

    public void removeItem(int pos) {
        lstModel.remove(pos);
        notifyItemRemoved(pos);
    }

    CustomCursorAdapter.OnRecycleViewItemClickListener listener;

    CustomCursorAdapter.OnItemClickListener listenerSync;

    public interface OnItemClickListener {
        void onItemClick(View v, List<Person> model, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listenerSync = listener;
    }

    public void setOnRecycleViewItemClickListener(CustomCursorAdapter.OnRecycleViewItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnRecycleViewItemClickListener {
        public void onRecycleViewItemClick(View v, List<Person> model, int position);
    }

    CustomCursorAdapter.OnRecycleViewLongItemClickListener longlistener;

    public void setOnRecycleViewLongItemClickListener(CustomCursorAdapter.OnRecycleViewLongItemClickListener listener) {

        this.longlistener = listener;
    }

    public interface OnRecycleViewLongItemClickListener {
        public void onRecycleViewLongItemClick(View v, List<Person> model, int position);
    }


}
