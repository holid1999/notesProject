 package com.example.notesprojectdz3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewNoteFragment extends Fragment {

    private EditText etTitle, etDescription;
    private Button btnSave;
    private int position;
    private String date = new SimpleDateFormat("dd-MM-yyy HH:mm", Locale.getDefault()).format(new Date());

    public AddNewNoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_note, container, false);
        etTitle = view.findViewById(R.id.et_title);
        etDescription = view.findViewById(R.id.et_description);
        btnSave = view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("description", description);
                bundle.putString("date", date);
                getActivity().getSupportFragmentManager().setFragmentResult("newNote", bundle);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String title = etTitle.getText().toString();
//                String description = etDescription.getText().toString();
//                Bundle bundle = new Bundle();
//                bundle.putString("title", title);
//                bundle.putString("description", description);
//                bundle.putInt("position",position);
//                getActivity().getSupportFragmentManager().setFragmentResult("editNote", bundle);
//                getActivity().getSupportFragmentManager().popBackStack();
//            }
//        });
        chekIsEdit();
        return view;
    }

    private void chekIsEdit() {
        if (getArguments() != null) {
            if (!requireArguments().getString("title").isEmpty()
                    && !requireArguments().getString("description").isEmpty()) {
                etTitle.setText(requireArguments().getString("title"));
                etDescription.setText(requireArguments().getString("description"));
                int pos = requireArguments().getInt("position");
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title = etTitle.getText().toString();
                        String description = etDescription.getText().toString();
                        Bundle bundle = new Bundle();
                        bundle.putString("title", title);
                        bundle.putString("description", description);
                        bundle.putString("date", date);
                        bundle.putInt("position", pos);
                        getActivity().getSupportFragmentManager().setFragmentResult("editNote", bundle);
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });
            }
        }
    }
}