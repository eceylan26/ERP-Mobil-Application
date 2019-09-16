package com.example.ersinceylan.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<BillClass> listOfBills = new ArrayList<BillClass>();
    ArrayList<BillClass> resultSearchListOfBill = new ArrayList<BillClass>();

    FloatingActionButton floatingActionButton1, floatingActionButton2;

    AdapterBillList listViewAdapter;
    EditText searchItem;
    int status=0;

    public BillFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillFragment newInstance(String param1, String param2) {
        BillFragment fragment = new BillFragment();
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
        // Inflate the layout for this fragment
        getActivity().setTitle("Bills");
        setBills();

        final View view = inflater.inflate(R.layout.fragment_bill, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.listViewBill);

        listViewAdapter = new AdapterBillList(getActivity(), R.layout.list_view_style_bill,listOfBills);
        listView.setAdapter(listViewAdapter);

        Button searchButton = (Button)view.findViewById(R.id.searchButtonBill);
        searchItem = (EditText)view.findViewById(R.id.editMatSearchBill);
        Button reStoreButton = (Button)view.findViewById(R.id.reStoreButtonBill);

        if(resultSearchListOfBill.size()<20)
        {
            for(int i =0;i<20-listOfBills.size();i++)
            {
                listOfBills.add(new BillClass("-","-","-","-","-","-","-"));
            }
        }

        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.billAdd);
        floatingActionButton2 = (FloatingActionButton) view.findViewById(R.id.billBackButton);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                BillAddMenu blankFragment = BillAddMenu.newInstance("asd","asd");
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.relativeForFragment,blankFragment,blankFragment.getTag()).commit();
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                HomeFragment homeFragment = new HomeFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.relativeForFragment,homeFragment,homeFragment.getTag()).commit();
            }
        });


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                resultSearchListOfBill.clear();
                status=1;

                String value = searchItem.getText().toString();

                for (int i =0;i<listOfBills.size();i++)
                {
                    if(listOfBills.get(i).getAmount().toString().trim().equals(value) ||
                            listOfBills.get(i).getBillNo().toString().trim().equals(value) ||
                            listOfBills.get(i).getChapter().toString().trim().equals(value) ||
                            listOfBills.get(i).getClient().toString().trim().equals(value)||
                            listOfBills.get(i).getDate().toString().trim().equals(value)||
                            listOfBills.get(i).getKind().toString().trim().equals(value)||
                            listOfBills.get(i).getPlace().toString().trim().equals(value)
                            )
                    {
                        resultSearchListOfBill.add(listOfBills.get(i));
                    }
                }

                if(resultSearchListOfBill.size()<14)
                {
                    for(int i =0;i<14-resultSearchListOfBill.size();i++)
                    {
                        listOfBills.add(new BillClass("-","-","-","-","-","-","-"));
                    }
                }

                listViewAdapter = new AdapterBillList(getActivity(), R.layout.list_view_style_bill,resultSearchListOfBill);
                listView.setAdapter(listViewAdapter);
            }
        });

        reStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                status=0;
                resultSearchListOfBill.clear();
                searchItem.setText("");

                listViewAdapter = new AdapterBillList(getActivity(),R.layout.list_view_style_bill,listOfBills);
                listView.setAdapter(listViewAdapter);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(status==0)
                {
                    BillEditMenu blankFragment = BillEditMenu.newInstance(listOfBills.get(i), "asd");
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.relativeForFragment, blankFragment, blankFragment.getTag()).commit();
                }
                else
                {
                    BillEditMenu blankFragment = BillEditMenu.newInstance(resultSearchListOfBill.get(i), "asd");
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.relativeForFragment, blankFragment, blankFragment.getTag()).commit();
                }
            }
        });



        return view;
    }

    public void setBills()
    {
        listOfBills.add(new BillClass("8/22/17","1","aa","ersin","502","0","0"));
        listOfBills.add(new BillClass("8/22/17","2","bb","furkan","102","0","0"));
        listOfBills.add(new BillClass("8/22/17","3","cc","selim","302","0","0"));
        listOfBills.add(new BillClass("8/22/17","4","aa","ersin","202","0","0"));
        listOfBills.add(new BillClass("8/22/17","5","aa","ersin","402","0","0"));
    }

}
