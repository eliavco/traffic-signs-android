package com.eliavco.trafficsigns;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SignsCatalogActivity extends AppCompatActivity {
    SignsFilter signsF;
    GridView signsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signs_catalog);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // INITIALIZE BACK BUTTON
        getSupportActionBar().setTitle(getString(R.string.app_name_catalog)); // INITIALIZE NEW TITLE

        this.forceRTLIfSupported();

        Dal dal = new Dal(this);
        this.signsF = new SignsFilter(dal.getAllSigns(), this);

        this.signsView = findViewById(R.id.gridSigns);
        this.signsView.setEmptyView(findViewById(R.id.emptyElementSignsCatalog));

        this.setSigns(this.signsF.getCurrentSigns());

        this.setCategories(dal.getSignsCategories());
        this.setSearch();
    }

    public void setSigns(ArrayList<Sign> signsList) {
        SignsAdapterHome adap = new SignsAdapterHome(this, R.layout.sign_layout, signsList);
        this.signsView.setAdapter(adap);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    protected void setSearch() {
        final EditText searchBox = findViewById(R.id.searchSignFilterTextCatalog);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = searchBox.getText().toString();
                if (text.length() == 0) { signsF.resetSearchFilter(); }
                else { signsF.search(text); }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    protected void setCategories(final ArrayList<String> categoriesList) {
        Spinner dropdown = findViewById(R.id.dropdownCategorySelectCatalog);
        categoriesList.add(0, getString(R.string.select_everything));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoriesList);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) { signsF.resetFilter(); }
                else { signsF.filterCategory(categoriesList.get(position)); }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}