package com.example.ersinceylan.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MaterialAccAddMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterialAccAddMenu extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText name;
    EditText unvan;
    EditText kod;
    EditText mevzuat;

    FloatingActionButton floatingActionButton1;


    public MaterialAccAddMenu() {
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
    public static MaterialAccAddMenu newInstance(String param1, String param2) {
        MaterialAccAddMenu fragment = new MaterialAccAddMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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

        View v = inflater.inflate(R.layout.fragment_material_acc_add_menu, container, false);

        getActivity().setTitle("Material Adding");
        Button addButton = (Button)v.findViewById(R.id.addCompanyButton);
        final EditText nameEdit = (EditText) v.findViewById(R.id.nameEditText);
        final EditText aciklamaEdit = (EditText) v.findViewById(R.id.aciklamaEditText);

        floatingActionButton1 = (FloatingActionButton) v.findViewById(R.id.backButtonAddMaterial);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(isEmpty(nameEdit)!=false || isEmpty(aciklamaEdit)!=false )
                {
                    Toast.makeText(getActivity(),"Boş Bırakılan yerler var",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Kayıt Yapıldı", Toast.LENGTH_SHORT).show();
                    nameEdit.setText("");
                    aciklamaEdit.setText("");
                }
            }
        });

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                SecondFragment secondFragment = SecondFragment.newInstance("1","1");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.relativeForFragment,secondFragment,secondFragment.getTag()).commit();

            }
        });

        return v;
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }

}
