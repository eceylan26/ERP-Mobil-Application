package com.example.ersinceylan.myapplication;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private final String TAG = this.getClass().getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView listView;

    private File pdfFile;

    ArrayList<MaterialClass> listOfMaterials = new ArrayList<MaterialClass>();
    ArrayList<MaterialClass> resultSearchListOfMaterials = new ArrayList<MaterialClass>();

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2,floatingActionButton3;

    AutoCompleteTextView acSearch;
    AdapterMaterialList listViewAdapter;
    EditText searchItem;
    Button reStoreButton;
    int status=0;

    public TextView mContentEditText;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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

        getActivity().setTitle("Malzemeler");

        setMaterial();

        View view=inflater.inflate(R.layout.fragment_second,container,false);

        final ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.matAdd);
        floatingActionButton2 = (FloatingActionButton) view.findViewById(R.id.backButtonMaterial);
        floatingActionButton3 = (FloatingActionButton) view.findViewById(R.id.pdfButtonMaterial);

        listViewAdapter = new AdapterMaterialList(getActivity(),R.layout.list_view_style_material,listOfMaterials);
        listView.setAdapter(listViewAdapter);

        Button searchButton = (Button)view.findViewById(R.id.searchButton);
        searchItem = (EditText)view.findViewById(R.id.editMatSearch);
        Button reStoreButton = (Button)view.findViewById(R.id.reStoreButton);
        mContentEditText = (TextView) view.findViewById(R.id.headerPart3);

        if(listOfMaterials.size()<20)
        {
            for(int i =0;i<20-listOfMaterials.size();i++)
            {
                listOfMaterials.add(new MaterialClass("-", "-"));
            }
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                resultSearchListOfMaterials.clear();
                status=1;

                String value = searchItem.getText().toString();

                for (int i =0;i<listOfMaterials.size();i++)
                {
                    if(listOfMaterials.get(i).getAciklama().toString().trim().equals(value) ||
                            listOfMaterials.get(i).getKod().toString().trim().equals(value))
                    {
                        resultSearchListOfMaterials.add(listOfMaterials.get(i));
                    }
                }

                if(listOfMaterials.size()<20)
                {
                    for(int i =0;i<20-listOfMaterials.size();i++)
                    {
                        listOfMaterials.add(new MaterialClass("-", "-"));
                    }
                }

                listViewAdapter = new AdapterMaterialList(getActivity(),R.layout.list_view_style_material,resultSearchListOfMaterials);
                listView.setAdapter(listViewAdapter);

            }
        });

        reStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                status=0;
                resultSearchListOfMaterials.clear();
                searchItem.setText("");

                listViewAdapter = new AdapterMaterialList(getActivity(),R.layout.list_view_style_material,listOfMaterials);
                listView.setAdapter(listViewAdapter);
            }
        });

      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                if(status==0)
                {
                    MaterialAccEditMenu blankFragment = MaterialAccEditMenu.newInstance(listOfMaterials.get(i),"asd");
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.relativeForFragment,blankFragment,blankFragment.getTag()).commit();
                }
                else
                {
                    MaterialAccEditMenu blankFragment = MaterialAccEditMenu.newInstance(resultSearchListOfMaterials.get(i),"asd");
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.relativeForFragment,blankFragment,blankFragment.getTag()).commit();
                }

            }
        });

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                MaterialAccAddMenu blankFragment = MaterialAccAddMenu.newInstance("asd","asd");
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

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {

            }
        });

        return view;
    }

    public void setMaterial()
    {
        listOfMaterials.add(new MaterialClass("A1","Laptop"));
        listOfMaterials.add(new MaterialClass("A2","Televizyon"));
        listOfMaterials.add(new MaterialClass("B1","Koltuk Takımı"));
        listOfMaterials.add(new MaterialClass("B2","Klima"));
        listOfMaterials.add(new MaterialClass("A1","Laptop"));
        listOfMaterials.add(new MaterialClass("A2","Televizyon"));
        listOfMaterials.add(new MaterialClass("B1","Koltuk Takımı"));
        listOfMaterials.add(new MaterialClass("B2","Klima"));
        listOfMaterials.add(new MaterialClass("B1","Koltuk Takımı"));
        listOfMaterials.add(new MaterialClass("B2","Klima"));
        listOfMaterials.add(new MaterialClass("A1","Laptop"));
        listOfMaterials.add(new MaterialClass("A2","Televizyon"));
        listOfMaterials.add(new MaterialClass("B1","Koltuk Takımı"));
        listOfMaterials.add(new MaterialClass("B2","Klima"));
        listOfMaterials.add(new MaterialClass("B1","Koltuk Takımı"));
        listOfMaterials.add(new MaterialClass("B2","Klima"));
        listOfMaterials.add(new MaterialClass("A1","Laptop"));
        listOfMaterials.add(new MaterialClass("A2","Televizyon"));
        listOfMaterials.add(new MaterialClass("B1","Koltuk Takımı"));
        listOfMaterials.add(new MaterialClass("B2","Klima"));

    }

    private void createPdf() throws FileNotFoundException, DocumentException
    {
        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
        docsFolder.mkdir();
        Log.i(TAG, "Created a new directory for PDF");
        }


        pdfFile = new File(docsFolder.getAbsolutePath(),"HelloWorld.pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();
        document.add(new Paragraph(mContentEditText.getText().toString()));

        document.close();
        previewPdf();

        }

    private void previewPdf()
    {
        PackageManager packageManager =  this.getActivity().getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(pdfFile);
        intent.setDataAndType(uri, "application/pdf");

        startActivity(intent);
        }else{
        Toast.makeText(this.getActivity(),"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
}

}
