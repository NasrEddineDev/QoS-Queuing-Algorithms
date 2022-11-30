package com.mesbahhightech.qosqueuingalgorithms.fragments;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mesbahhightech.qosqueuingalgorithms.AlgorithmLibrary;
import com.mesbahhightech.qosqueuingalgorithms.R;
import com.mesbahhightech.qosqueuingalgorithms.data.Example;
import com.mesbahhightech.qosqueuingalgorithms.data.ExampleViewModel;
import com.mesbahhightech.qosqueuingalgorithms.data.Queue;
import com.mesbahhightech.qosqueuingalgorithms.data.QueueViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static androidx.navigation.Navigation.findNavController;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewExampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenExampleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner algorithmsSpinner;
    private Spinner examplesSpinner;
    private TableLayout tableLayout;
    private Button showButton;
    private AlgorithmLibrary algorithmLibrary;
    private ExampleViewModel exampleViewModel;
    private QueueViewModel queueViewModel;
    public OpenExampleFragment() {
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

        final View view = inflater.inflate(R.layout.fragment_open_example, container, false);
        examplesSpinner = (Spinner) view.findViewById(R.id.examplesSpinner);

        algorithmsSpinner = (Spinner) view.findViewById(R.id.algorithmsSpinner);
        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);
        showButton = (Button) view.findViewById(R.id.showButton);
        exampleViewModel = new ExampleViewModel(getActivity().getApplication());
        queueViewModel = new QueueViewModel(getActivity().getApplication());

        List<Example> egs = exampleViewModel.getAllExamples1();

        ArrayList<String> examples = new ArrayList<>(egs.size());
        for (Example eg : egs) {
            examples.add(eg.getName());
        }

        ArrayAdapter<String> examplesAdapter = new ArrayAdapter<String>(
                getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                examples
        );

        examplesSpinner.setAdapter(examplesAdapter);

        examplesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),
                        examples.get(position) + " Selected",
                        Toast.LENGTH_SHORT).show();

                createTable(examples.get(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final ArrayList<String> algorithms = new ArrayList<>();
        algorithms.add("SPQ");
        algorithms.add("FQ");
        algorithms.add("WFQ");
        algorithms.add("RR");
        algorithms.add("WRR");
        algorithms.add("DRR");
        algorithms.add("DWRR");
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

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String algorithm = algorithmsSpinner.getSelectedItem().toString();
                switch (algorithm) {
                    case "SPQ":
                        algorithmLibrary.SPQ();
                        break;
                    case "FQ":
                        algorithmLibrary.FQ();
                        break;
                    case "WFQ":
                        algorithmLibrary.WFQ();
                        break;
                    case "RR":
                        algorithmLibrary.RR();
                        Toast.makeText(getActivity().getApplicationContext(),
                                "RR Selected",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case "WRR":
                        algorithmLibrary.WRR();
                        break;
                    case "DRR":
                        algorithmLibrary.DRR();
                        break;
                    case "DWRR":
                        algorithmLibrary.DWRR();
                        break;
                }
            }
        });

        return view;
    }

    private void createTable(String exampleName) {
        List<Queue> queues = queueViewModel.getQueuesByExampleName(exampleName);
        String[] time = queues.get(0).getContent().split(";");
        Log.d("this is my array", "time: " + Arrays.toString(time));
        String[] queueA = queues.get(1).getContent().split(";");
        Log.d("this is my array", "queueA: " + Arrays.toString(queueA));
        String[] queueB = queues.get(2).getContent().split(";");
        Log.d("this is my array", "queueB: " + Arrays.toString(queueB));
        String[] queueC = queues.get(3).getContent().split(";");
        Log.d("this is my array", "queueC: " + Arrays.toString(queueC));
        int childCount = tableLayout.getChildCount();
        if (childCount > 1) {
            tableLayout.removeViews(1, childCount - 1);
        }
        for(int i = 0; i < time.length; i++){
            TableRow tr = new TableRow(getActivity().getApplicationContext());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 0));
            tr.setBackgroundColor(Color.WHITE);
            tr.setBackgroundResource(R.drawable.row_border);
            tr.addView(textView(time[i]));
            tr.addView(textView(queueA[i]));
            tr.addView(textView(queueB[i]));
            tr.addView(textView(queueC[i]));
            tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    private TextView textView(String str) {
        TextView textView = new TextView(getActivity().getApplicationContext());
        Resources res = getResources();
        float value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, res.getDisplayMetrics());
        textView.setPadding((int)value,(int)value,(int)value,(int)value);
//        textView.setText("Time "+tableLayout.getChildCount());
        textView.setText(str);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
//        textView.setHintTextColor(Color.GRAY);
        textView.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
        TableRow.LayoutParams p = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT);
        p.weight = 25;
        textView.setLayoutParams(p);
        return textView;
    }
}