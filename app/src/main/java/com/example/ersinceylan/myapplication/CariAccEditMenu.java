package com.example.ersinceylan.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import static android.R.id.input;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CariAccEditMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CariAccEditMenu extends Fragment {
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

    private static CariAccClass input = new CariAccClass();

    public CariAccEditMenu() {
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
    public static CariAccEditMenu newInstance(CariAccClass param1, String param2) {
        CariAccEditMenu fragment = new CariAccEditMenu();
        Bundle args = new Bundle();
        input.setIsim(param1.getIsim());
        input.setKod(param1.getKod());
        input.setMevduat(param1.getMevduat());
        input.setUnvan(param1.getUnvan());
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

        View v = inflater.inflate(R.layout.fragment_cari_acc_edit_menu, container, false);

        floatingActionButton1 = (FloatingActionButton) v.findViewById(R.id.backButtonEditCari);
        floatingActionButton2 = (FloatingActionButton) v.findViewById(R.id.cariEditBill);

        EditText editTextInput = (EditText) v.findViewById(R.id.editCariAciklama);
        editTextInput.setText(input.getKod());

        EditText editCariUnvan= (EditText) v.findViewById(R.id.editCariUnvan);
        editCariUnvan.setText(input.getUnvan());

        EditText editCariIsim = (EditText) v.findViewById(R.id.editCariIsim);
        editCariIsim.setText(input.getIsim());

        EditText editCariMevduat = (EditText) v.findViewById(R.id.editCariKod);
        editCariMevduat.setText(input.getMevduat());

        getActivity().setTitle("Cari Edit Menu");

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                FirstFragment firstFragment = FirstFragment.newInstance("1","1");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.relativeForFragment,firstFragment,firstFragment.getTag()).commit();
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
