package com.example.notesprojectdz3;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<NoteModel> notes = new ArrayList<>();
    private FragmentActivity activity;
    public NotesAdapter(FragmentActivity activity) {
        this.activity = activity;

    }

    public void addNewNote(NoteModel model) {
        this.notes.add(model);
        notifyDataSetChanged();
    }
    public void setNotesList(List<NoteModel> List){
        this.notes = List;
        notifyDataSetChanged();
    }
    public void editNote(NoteModel model, int position) {
        this.notes.set(position,model);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder,int position) {
        holder.txtTitle.setText(notes.get(holder.getAdapterPosition()).getTitle());
        holder.txtDescription.setText(notes.get(holder.getAdapterPosition()).getDescription());
        holder.txtDate.setText(notes.get(holder.getAdapterPosition()).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("title",notes.get(holder.getAdapterPosition()).getTitle());
                bundle.putString("description",notes.get(holder.getAdapterPosition()).getDescription());
                bundle.putInt("position",holder.getAdapterPosition());

            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,AddNewNoteFragment.class,bundle);
            transaction.addToBackStack("AddNoteFragmentEdit");
            transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription, txtDate;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.text_title);
            txtDescription = itemView.findViewById(R.id.text_description);
            txtDate = itemView.findViewById(R.id.text_date);
        }
    }
}