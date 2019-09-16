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
 * Use the {@link BillAddMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillAddMenu extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText date;
    EditText billNo;
    EditText kind;
    EditText client;
    EditText amount;
    EditText place;
    EditText chapter;

    FloatingActionButton floatingActionButton1;


    public BillAddMenu() {
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
    public static BillAddMenu newInstance(String param1, String param2) {
        BillAddMenu fragment = new BillAddMenu();
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

        View v = inflater.inflate(R.layout.fragment_bill_add_menu, container, false);

        getActivity().setTitle("BILL ADDING");
        Button addButton = (Button)v.findViewById(R.id.addBillButton);
        final EditText billDateAdd = (EditText) v.findViewById(R.id.billDateAdd);
        final EditText billBillNoAdd = (EditText) v.findViewById(R.id.billBillNoAdd);
        final EditText billKindAdd = (EditText) v.findViewById(R.id.billKindAdd);
        final EditText billClientAdd = (EditText) v.findViewById(R.id.billClientAdd);
        final EditText billAmountAdd = (EditText) v.findViewById(R.id.billAmountAdd);
        final EditText billPlaceAdd = (EditText) v.findViewById(R.id.billPlaceAdd);
        final EditText billChapterAdd = (EditText) v.findViewById(R.id.billChapterAdd);

        floatingActionButton1 = (FloatingActionButton) v.findViewById(R.id.backButtonAddMaterial);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(isEmpty(billDateAdd)!=false || isEmpty(billBillNoAdd)!=false
                        || isEmpty(billKindAdd)!=false || isEmpty(billClientAdd)!=false
                        || isEmpty(billAmountAdd)!=false || isEmpty(billPlaceAdd)!=false
                        || isEmpty(billChapterAdd)!=false)
                {
                    Toast.makeText(getActivity(),"Boş Bırakılan yerler var",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "Kayıt Yapıldı", Toast.LENGTH_SHORT).show();

                    billDateAdd.setText("");
                    billBillNoAdd.setText("");
                    billKindAdd.setText("");
                    billClientAdd.setText("");
                    billAmountAdd.setText("");
                    billPlaceAdd.setText("");
                    billChapterAdd.setText("");

                }
            }
        });

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                BillFragment secondFragment = BillFragment.newInstance("1","1");
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
