package com.example.ersinceylan.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MaterialAccEditMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterialAccEditMenu extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageButton backButton;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1,floatingActionButton2;

    private static MaterialClass input = new MaterialClass();


    public MaterialAccEditMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CariAccEditMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static MaterialAccEditMenu newInstance(MaterialClass param1, String param2) {
        MaterialAccEditMenu fragment = new MaterialAccEditMenu();
        Bundle args = new Bundle();
        input.setAciklama(param1.getAciklama());
        input.setKod(param1.getKod());
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_material_edit_menu, container, false);

        floatingActionButton1 = (FloatingActionButton) v.findViewById(R.id.backButtonEditMaterial);
        floatingActionButton2 = (FloatingActionButton) v.findViewById(R.id.matEditBill);

        EditText editTextInput = (EditText) v.findViewById(R.id.editMatAciklama);
        editTextInput.setText(input.getKod());

        EditText editMatAciklama = (EditText) v.findViewById(R.id.editMatKod);
        editMatAciklama.setText(input.getAciklama());

        getActivity().setTitle("Material Edit Menu");

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                SecondFragment secondFragment = SecondFragment.newInstance("1","1");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.relativeForFragment,secondFragment,secondFragment.getTag()).commit();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                /*SecondFragment secondFragment = SecondFragment.newInstance("1","1");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.relativeForFragment,secondFragment,secondFragment.getTag()).commit();*/

            }
        });


        return v;
    }

}
