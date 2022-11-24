package com.mesbahhightech.qosqueuingalgorithms.fragments;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mesbahhightech.qosqueuingalgorithms.AlgorithmLibrary;
import com.mesbahhightech.qosqueuingalgorithms.R;
import com.mesbahhightech.qosqueuingalgorithms.data.AlgorithmViewModel;
import com.mesbahhightech.qosqueuingalgorithms.data.Example;
import com.mesbahhightech.qosqueuingalgorithms.data.ExampleViewModel;
import com.mesbahhightech.qosqueuingalgorithms.data.Queue;
import com.mesbahhightech.qosqueuingalgorithms.data.QueueViewModel;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static androidx.navigation.Navigation.findNavController;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewExampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewExampleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner algorithmsSpinner;
    private TableLayout tableLayout;
    private Button showButton;
    private Button addRowButton;
    private Button removeRowButton;
    private Button saveExampleButton;
    private AlgorithmLibrary algorithmLibrary;
    private ExampleViewModel exampleViewModel;
    private QueueViewModel queueViewModel;
    private AlgorithmViewModel algorithmViewModel;
    public NewExampleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewExampleFragment newInstance(String param1, String param2) {
        NewExampleFragment fragment = new NewExampleFragment();
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

        final View view = inflater.inflate(R.layout.fragment_new_example, container, false);
        algorithmsSpinner = (Spinner)view.findViewById(R.id.algorithmsSpinner);
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);
        showButton = (Button)view.findViewById(R.id.showButton);
        addRowButton = (Button)view.findViewById(R.id.addRowButton);
        removeRowButton = (Button)view.findViewById(R.id.removeRowButton);
        saveExampleButton = (Button)view.findViewById(R.id.saveExampleButton);
//        exampleViewModel = new ViewModelProvider(this).get(ExampleViewModel.class);
        exampleViewModel = new ExampleViewModel(getActivity().getApplication());
//        queueViewModel = new ViewModelProvider(this).get(QueueViewModel.class);
        queueViewModel = new QueueViewModel(getActivity().getApplication());
        algorithmViewModel = new AlgorithmViewModel(getActivity().getApplication());

        final ArrayList<String> algorithms = new ArrayList<>();
        algorithms.add("SPQ");
        algorithms.add("FQ");
        algorithms.add("WFQ");
        algorithms.add("RR");
        algorithms.add("WRR");
        algorithms.add("DRR");
        algorithms.add("DWRR");
//        ArrayList<String> algorithms = algorithmViewModel.getAllAlgorithms().
        ArrayAdapter<String> algorithmsAdapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                algorithms
        );

        algorithmsSpinner.setAdapter(algorithmsAdapter);

        algorithmsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),
                        algorithms.get(position) + " Selected",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* Create a new row to be added. */
        addRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow tr = new TableRow(getActivity().getApplicationContext());
                tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 0));
                tr.setBackgroundColor(Color.WHITE);
                EditText editText = new EditText(getActivity().getApplicationContext());
                Resources res = getResources();
                float value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, res.getDisplayMetrics());
                editText.setPadding((int)value,(int)value,(int)value,(int)value);
                editText.setHint("Time "+tableLayout.getChildCount());
                editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                editText.setTextColor(Color.BLACK);
                editText.setGravity(Gravity.CENTER);
                editText.setHintTextColor(Color.GRAY);
                editText.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                TableRow.LayoutParams p = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT);
                p.weight = 25;
                editText.setLayoutParams(p);
                tr.addView(editText);

                editText = new EditText(getActivity().getApplicationContext());
                res = getResources();
                value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, res.getDisplayMetrics());
                editText.setPadding((int)value,(int)value,(int)value,(int)value);
                editText.setHint("A"+tableLayout.getChildCount());
                editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                editText.setTextColor(Color.BLACK);
                editText.setGravity(Gravity.CENTER);
                editText.setHintTextColor(Color.GRAY);
                editText.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                p = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT);
                p.weight = 25;
                editText.setLayoutParams(p);
                tr.addView(editText);

                editText = new EditText(getActivity().getApplicationContext());
                res = getResources();
                value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, res.getDisplayMetrics());
                editText.setPadding((int)value,(int)value,(int)value,(int)value);
                editText.setHint("B"+tableLayout.getChildCount());
                editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                editText.setTextColor(Color.BLACK);
                editText.setGravity(Gravity.CENTER);
                editText.setHintTextColor(Color.GRAY);
                editText.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                p = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT);
                p.weight = 25;
                editText.setLayoutParams(p);
                tr.addView(editText);

                editText = new EditText(getActivity().getApplicationContext());
                res = getResources();
                value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, res.getDisplayMetrics());
                editText.setPadding((int)value,(int)value,(int)value,(int)value);
                editText.setHint("C"+tableLayout.getChildCount());
                editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                editText.setTextColor(Color.BLACK);
                editText.setGravity(Gravity.CENTER);
                editText.setHintTextColor(Color.GRAY);
                editText.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                p = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT);
                p.weight = 25;
                editText.setLayoutParams(p);
                tr.addView(editText);
                tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

                //if(editText.getText().toString().isEmpty())
                //{
                //    editText.setError("UserName Should not be blank");
                //}
            }
        });

        removeRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tableLayout.getChildCount() > 2) {
                    tableLayout.removeViewAt(tableLayout.getChildCount() - 1);
                }
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[][] queuesTable = new String[4][tableLayout.getChildCount()-1];
                queuesTable = gettingData();

                String algorithm = algorithmsSpinner.getSelectedItem().toString();
                switch (algorithm){
                    case "SPQ":
                        Toast.makeText(getActivity().getApplicationContext(),"SPQ Selected",
                                Toast.LENGTH_SHORT).show();
                        algorithmLibrary.SPQ();
                        break;
                    case "FQ":
                        Toast.makeText(getActivity().getApplicationContext(),"FQ Selected",
                                Toast.LENGTH_SHORT).show();
                        algorithmLibrary.FQ();
                        break;
                    case "WFQ":
                        Toast.makeText(getActivity().getApplicationContext(),"WFQ Selected",
                                Toast.LENGTH_SHORT).show();
                        algorithmLibrary.WFQ();
                        break;
                    case "RR":
                        Toast.makeText(getActivity().getApplicationContext(),
                                "RR Selected",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "WRR":
                        Toast.makeText(getActivity().getApplicationContext(),"WRR Selected",
                                Toast.LENGTH_SHORT).show();
                        algorithmLibrary.WRR();
                        break;
                    case "DRR":
                        Toast.makeText(getActivity().getApplicationContext(),"DRR Selected",
                                Toast.LENGTH_SHORT).show();
                        algorithmLibrary.DRR();
                        break;
                    case "DWRR":
                        Toast.makeText(getActivity().getApplicationContext(),"DWRR Selected",
                                Toast.LENGTH_SHORT).show();
                        algorithmLibrary.DWRR();
                        break;
                }
            }
        });

         saveExampleButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Example example = new Example("eg 01", 3,7);
                 Toast.makeText(getActivity().getApplicationContext(),
                         example.getName() + " Inserted, egId=" + example.getId(),
                         Toast.LENGTH_LONG).show();
                 long example_id=1;
                 try {
                     example_id = exampleViewModel.insert(example);
                 } catch (ExecutionException e) {
                     e.printStackTrace();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 Queue queue = new Queue("File A", "A;;A;B;;;;", (int)example_id);
                 try {
                     queueViewModel.insert(queue);
                 } catch (ExecutionException e) {
                     e.printStackTrace();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
//                 queue = new Queue("File B", "A;;A;B;;;;", example.getId());
//                 queueViewModel.insert(queue);
//                 queue = new Queue("File C", "A;;A;B;;;;", example.getId());
//                 queueViewModel.insert(queue);
                 // findNavController(view).navigate(R.id.homeFragment);
             }
         });

        return view;
    }
    public String[][] gettingData(){
        String[][] queuesTable = new String[4][tableLayout.getChildCount()-1];
        for (int i = 1; i < tableLayout.getChildCount()-1;i++){
            TableRow tr = (TableRow) tableLayout.getChildAt(i);
            EditText et = (EditText) tr.getChildAt(0);
            if(et.getText().toString().isEmpty())
            {
                et.setError("Time Should not be blank");
                return null;
            }
            queuesTable[0][i] = et.getText().toString();
            et = (EditText) tr.getChildAt(1);
            queuesTable[1][i] = et.getText().toString();
            et = (EditText) tr.getChildAt(2);
            queuesTable[2][i] = et.getText().toString();
            et = (EditText) tr.getChildAt(3);
            queuesTable[3][i] = et.getText().toString();
        }
        return queuesTable;
    }
}