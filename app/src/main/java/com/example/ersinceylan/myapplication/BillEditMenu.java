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
 * Use the {@link BillEditMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillEditMenu extends Fragment {
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

    private static BillClass input = new BillClass();


    public BillEditMenu() {
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
    public static BillEditMenu newInstance(BillClass param1, String param2) {
        BillEditMenu fragment = new BillEditMenu();
        Bundle args = new Bundle();
        input.setAmount(param1.getAmount());
        input.setBillNo(param1.getBillNo());
        input.setChapter(param1.getChapter());
        input.setClient(param1.getClient());
        input.setDate(param1.getDate());
        input.setKind(param1.getKind());
        input.setPlace(param1.getPlace());

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

        View v = inflater.inflate(R.layout.fragment_bill_edit_menu, container, false);

        floatingActionButton1 = (FloatingActionButton) v.findViewById(R.id.backButtonEditBill);

        EditText billDateEdit = (EditText) v.findViewById(R.id.billDateEdit);
        billDateEdit.setText(input.getDate());

        EditText billBillNoEdit = (EditText) v.findViewById(R.id.billBillNoEdit);
        billBillNoEdit.setText(input.getBillNo());

        EditText billKindEdit = (EditText) v.findViewById(R.id.billKindEdit);
        billKindEdit.setText(input.getKind());

        EditText billClientEdit = (EditText) v.findViewById(R.id.billClientEdit);
        billClientEdit.setText(input.getClient());

        EditText billAmountEdit = (EditText) v.findViewById(R.id.billAmountEdit);
        billAmountEdit.setText(input.getAmount());

        EditText billPlaceEdit = (EditText) v.findViewById(R.id.billPlaceEdit);
        billPlaceEdit.setText(input.getPlace());

        EditText billChapterEdit = (EditText) v.findViewById(R.id.billChapterEdit);
        billChapterEdit.setText(input.getChapter());

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                BillFragment firstFragment = BillFragment.newInstance("1","1");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.relativeForFragment,firstFragment,firstFragment.getTag()).commit();
            }
        });

        getActivity().setTitle("BILL EDITING");

        return v;
    }

}
