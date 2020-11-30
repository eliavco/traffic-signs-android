package com.eliavco.trafficsigns;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Consumer;

public class SignsFilter {
    private ArrayList<Sign> baseSigns;
    private ArrayList<Sign> currentSigns;
    private SignsCatalogActivity ctx;
    String category;
    String term;

    public SignsFilter(ArrayList<Sign> signs, SignsCatalogActivity ctx) {
        this.baseSigns = this.currentSigns = signs;
        this.ctx = ctx;
    }

    private void updateList() {
        this.ctx.setSigns(this.currentSigns);
    }

    public void resetFilter() {
        this.category = null;
        this.currentSigns = this.baseSigns;
        this.updateList();
        if (this.term != null) { this.search(this.term); }
    }

    public void resetSearchFilter() {
        this.term = null;
        this.currentSigns = this.baseSigns;
        if (this.category != null) { this.currentSigns = this.filterCategoryArray(this.category); }
        this.updateList();
    }

    public void filterCategory(String st) {
        this.category = st.trim();
        this.currentSigns = filterCategoryArray(st);
        this.updateList();
        if (this.term != null) { this.search(this.term); }
    }

    public void search(String st) {
        this.term = st.trim();
        this.currentSigns = searchTermArray(st);
        this.updateList();
    }

    private ArrayList<Sign> searchTermArray(String st) {
        ArrayList<Sign> base = this.baseSigns;
        if (this.category != null) { base = this.filterCategoryArray(this.category); }

        ArrayList filtered = new ArrayList<Sign>();
        for (int o = 0; o < base.size(); o++) {
            Sign s = base.get(o);
            String[] d = s.getSearchableFields();
            if (this.searchAlgorithm(st.trim(), d)) {
                filtered.add(s);
            };
        }
        return filtered;
    }

    private boolean searchAlgorithm(String term, String[] data) {
        for (int p = 0; p < data.length; p++) {
            String field = data[p];
            if (field.contains(term)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Sign> getBaseSigns() {
        return this.baseSigns;
    }

    public ArrayList<Sign> getCurrentSigns() {
        return this.currentSigns;
    }

    private void setCurrentSigns(ArrayList<Sign> currentSigns) {
        this.currentSigns = currentSigns;
    }

    private ArrayList<Sign> filterCategoryArray(String st) {
        ArrayList<Sign> filtered = new ArrayList<Sign>();
        for (int o = 0; o < this.baseSigns.size(); o++) {
            Sign s = this.baseSigns.get(o);
            if (s.getCategory().trim().equals(st.trim())) {
                filtered.add(s);
            }
        }
        return filtered;
    }
}
