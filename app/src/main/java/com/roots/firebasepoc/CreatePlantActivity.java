package com.roots.firebasepoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.roots.firebasepoc.models.Constants;
import com.roots.firebasepoc.models.Plant;
import com.roots.firebasepoc.models.Species;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreatePlantActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText plantName;
    private EditText plantSpecies;
    private EditText plantAge;
    private EditText acquisitionDate;
    private Date userSelectedDate;
    private Switch isBonsaiAble;
    private EditText origin;
    private EditText height;
    private EditText container;
    private Switch isSaleable;
    private Button saveButton;
    private Button uploadPhoto;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plant);
        //Setting proper references
        acquisitionDate = findViewById(R.id.editPlantDate);
        saveButton = findViewById(R.id.btnSavePlant);
        //Adding listeners on those references to behave accordingly
        acquisitionDate.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSavePlant:
                createNewPlantEntry();
                break;
            case R.id.editPlantDate:
                showDatePickerDialog();
                break;
            default:
                break;
        }
    }

    private void createNewPlantEntry() {
        //Get all references from Activity
        bindViewElements();
        //Generate a new HashMap to store on Firebase
        Map<String, Object> plantToAdd = generateFirebaseObject();

        //1. Set the reference to the collection that we're going to store our data
        db.collection(Constants.PLANT_COLLECTION)
                //2. Add the previously created HashMap
                .add(plantToAdd)
                //3. OnSuccess we show a toast (Can navigate to home later)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CreatePlantActivity.this, "A new plant has been added", Toast.LENGTH_SHORT).show();
                    }
                })
                //4. Show a generic error
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreatePlantActivity.this, "An error has occured, please try again", Toast.LENGTH_SHORT).show();
                    }
                });

        Plant plant = new Plant(
                new Species(plantSpecies.getText().toString()),
                Integer.parseInt(plantAge.getText().toString()),
                userSelectedDate,
                isBonsaiAble.isChecked(),
                origin.getText().toString(),
                Integer.parseInt(height.getText().toString()),
                Integer.parseInt(container.getText().toString()),
                isSaleable.isChecked()
        );

        System.out.println(plant);
    }

    private void bindViewElements() {
        plantName = findViewById(R.id.editTextPlantName);
        plantSpecies = findViewById(R.id.editTextPlantSpecies);
        plantAge = findViewById(R.id.editPlantAge);
        isBonsaiAble = findViewById(R.id.switchBonsaiAble);
        origin = findViewById(R.id.editPlantOrigin);
        height = findViewById(R.id.editPlantHeight);
        container = findViewById(R.id.editPlantContainerType);
        isSaleable = findViewById(R.id.switchSellable);
    }

    private Map<String, Object> generateFirebaseObject() {
        //To store a new document in Firebase, a new HashMap is needed
        //Map<String, Object> newEntry = new HashMap<>();

        Map<String, Object> plantToAdd = new HashMap<>();
        Map<String, Object> speciesToAdd = new HashMap<>();

        //To add a property to the document, use put(nameOfTheProperty, ValueOfTheProperty
        speciesToAdd.put(Constants.SPECIES_NAME, plantSpecies.getText().toString());
        plantToAdd.put(Constants.PLANT_NAME, plantName.getText().toString());

        //This is a nested property in the document
        plantToAdd.put(Constants.PLANT_SPECIES, speciesToAdd);

        plantToAdd.put(Constants.PLANT_AGE, plantAge.getText().toString());
        plantToAdd.put(Constants.PLANT_REGISTRATION_DATE, new Timestamp(userSelectedDate));
        plantToAdd.put(Constants.PLANT_IS_BONSAI_ABLE, isBonsaiAble.isChecked());
        plantToAdd.put(Constants.PLANT_ORIGIN, origin.getText().toString());
        plantToAdd.put(Constants.PLANT_HEIGHT, height.getText().toString());
        plantToAdd.put(Constants.PLANT_CONTAINER, container.getText().toString());
        plantToAdd.put(Constants.PLANT_IS_SALEABLE, isSaleable.isChecked());
        return plantToAdd;
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = twoDigits(day) + "/" + twoDigits(month + 1) + "/" + year;
                acquisitionDate.setText(selectedDate);
                userSelectedDate = getUserSelectedDate(year, month, day);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private Date getUserSelectedDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }


}