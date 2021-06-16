package com.mesbahhightech.qosqueuingalgorithms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mesbahhightech.qosqueuingalgorithms.data.ExampleViewModel;

public class MainActivity extends AppCompatActivity {
    private ExampleViewModel exampleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setupActionBarWithNavController();
//        exampleViewModel = new ViewModelProvider(this).get(ExampleViewModel.class);
//        exampleViewModel.getAllExamples().observe(this, new Observer<List<Example>>() {
//            @Override
//            public void onChanged(List<Example> examples) {
//                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Spinner spinner = (Spinner) findViewById(R.id.spinnerAlgorithms);
//        // Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);


        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//                Navigation.findNavController(this, R.id.nav_host_fragment));
    }

     public void onButtonShowClick(View view){
        Spinner spinnerAlgorithms = (Spinner) findViewById(R.id.spinnerAlgorithms);

    }
    public void onShowMessageButtonClick(View view){
        TextView firstMessageTextView = findViewById(R.id.firstMessageTextView);
        firstMessageTextView.setText("hello");
    }
}
