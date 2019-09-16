package com.example.ersinceylan.myapplication;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.provider.DocumentFile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private String mParam1;
    private String mParam2;

    public static final int REQUEST_WRITE_STORAGE = 112;

    private final String TAG = this.getClass().getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<CariAccClass> listOfCariAcc = new ArrayList<CariAccClass>();
    ArrayList<CariAccClass> resultSearchListOfCari = new ArrayList<CariAccClass>();

    AdapterCarAccList listViewAdapter;

    FloatingActionButton floatingActionButton1, floatingActionButton2;

    EditText searchItem;
    int status=0;

    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
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

        getActivity().setTitle("Cari Hesaplar");
        setCariHesap();

        final View view = inflater.inflate(R.layout.fragment_first, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.listViewCari);

        Button searchButton = (Button)view.findViewById(R.id.searchButtonCari);
        searchItem = (EditText)view.findViewById(R.id.editMatSearchCari);
        Button reStoreButton = (Button)view.findViewById(R.id.reStoreButtonCari);

        if(listOfCariAcc.size()<9)
        {
            for(int i =0;i<9-listOfCariAcc.size();i++)
            {
                listOfCariAcc.add(new CariAccClass("-", "-", "-", "-"));
            }
        }

        listViewAdapter = new AdapterCarAccList(getActivity(), R.layout.list_view_style_cari,listOfCariAcc);
        listView.setAdapter(listViewAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                resultSearchListOfCari.clear();
                status=1;

                String value = searchItem.getText().toString();

                for (int i =0;i<listOfCariAcc.size();i++)
                {
                    if(listOfCariAcc.get(i).getIsim().toString().trim().equals(value) ||
                            listOfCariAcc.get(i).getMevduat().toString().trim().equals(value) ||
                    listOfCariAcc.get(i).getKod().toString().trim().equals(value) ||
                            listOfCariAcc.get(i).getUnvan().toString().trim().equals(value)
                            )
                    {
                        resultSearchListOfCari.add(listOfCariAcc.get(i));
                    }
                }

                if(resultSearchListOfCari.size()<9)
                {
                    for(int i =0;i<9-resultSearchListOfCari.size();i++)
                    {
                        listOfCariAcc.add(new CariAccClass("", "", "", ""));
                    }
                }

                listViewAdapter = new AdapterCarAccList(getActivity(), R.layout.list_view_style_cari, resultSearchListOfCari);
                listView.setAdapter(listViewAdapter);
            }
        });

        reStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                status=0;
                resultSearchListOfCari.clear();
                searchItem.setText("");

                listViewAdapter = new AdapterCarAccList(getActivity(),R.layout.list_view_style_cari,listOfCariAcc);
                listView.setAdapter(listViewAdapter);
            }
        });

        /////////////////////////////// LIST ITEMSSSSS //////////////////////////////////////

        ///////////////////////// BOOM MENU ITEMSSSSS //////////////////////////////////////

        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.cariAdd);
        floatingActionButton2 = (FloatingActionButton) view.findViewById(R.id.cariBackButton);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                requestPermission(getActivity());
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(status==0)
                {
                    CariAccEditMenu blankFragment = CariAccEditMenu.newInstance(listOfCariAcc.get(i), "asd");
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.relativeForFragment, blankFragment, blankFragment.getTag()).commit();
                }
                else
                {
                    CariAccEditMenu blankFragment = CariAccEditMenu.newInstance(resultSearchListOfCari.get(i), "asd");
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.relativeForFragment, blankFragment, blankFragment.getTag()).commit();
                }
            }
        });

        return view;
    }

    public void setCariHesap()
    {
        listOfCariAcc.add(new CariAccClass("Ceylan AŞ.", "Ticaret", "AAA", "111"));
        listOfCariAcc.add(new CariAccClass("Erdöl AŞ.", "Global", "BBB", "222"));
        listOfCariAcc.add(new CariAccClass("Logo AŞ.", "Yazılım", "CCC", "333"));
        listOfCariAcc.add(new CariAccClass("İnce AŞ.", "Logistic", "DDD", "444"));
    }


    private void createPdf(){

        // create a new document
        PdfDocument document = new PdfDocument();

        // crate a page description
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(100, 100, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawCircle(50, 50, 30, paint);

        document.finishPage(page);

        pageInfo = new PdfDocument.PageInfo.Builder(500, 500, 2).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(200, 200, 100, paint);
        document.finishPage(page);



        // write the document content
        String targetPdf = "/sdcard/test.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this.getActivity(), "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this.getActivity(), "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
    }

    private void requestPermission(Activity context)
    {
        boolean hasPermission = (ContextCompat.checkSelfPermission
                (context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission)
        {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
        else
        {
            createPdf();
        }
    }
}


