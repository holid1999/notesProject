package com.example.notesprojectdz3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private RecyclerView rvNotes;
    private NotesAdapter adapter;
    private List<NoteModel> List = new ArrayList<>();
    private FloatingActionButton btnOpenAddNoteFragment;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        rvNotes = view.findViewById(R.id.rv_notes);
        btnOpenAddNoteFragment = view.findViewById(R.id.btn_open_add_note);
        rvNotes.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NotesAdapter(getActivity());
        adapter.setNotesList(List);
        rvNotes.setAdapter(adapter);


        btnOpenAddNoteFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new AddNewNoteFragment());
                transaction.addToBackStack("AddNoteFragment");
                transaction.commit();

            }
        });

        ListenNoteData();

        return view;
    }

    private void ListenNoteData() {
        getActivity().getSupportFragmentManager().setFragmentResultListener("newNote", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (requestKey.equals("newNote")) {
                    Toast.makeText(requireContext(), "Succes!!!", Toast.LENGTH_SHORT).show();
                    String title = result.getString("title");
                    String description = result.getString("description");
                    String date = result.getString("date");
                    adapter.addNewNote(new NoteModel(title,description,date));
                }
            }
        });

        getActivity().getSupportFragmentManager().setFragmentResultListener("editNote", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (requestKey.equals("editNote")){
                    String title = result.getString("title");
                    String description = result.getString("description");
                    String date = result.getString("date");
                    int position = result.getInt("position");

                    adapter.editNote(new NoteModel(title,description,date),position);
                }
            }
        });
    }
}