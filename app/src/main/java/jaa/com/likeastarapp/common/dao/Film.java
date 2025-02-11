package jaa.com.likeastarapp.common.dao;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jaa.com.likeastarapp.common.database.ListTypeConverter;

@Entity
public class Film implements Serializable {
    @NonNull
    @PrimaryKey
    @SerializedName("title")
    String title;
    @SerializedName("actor_1")
    String actor_1;
    @SerializedName("actor_2")
    String actor_2;
    @SerializedName("actor_3")
    String actor_3;
    @SerializedName("director")
    String director;
    @SerializedName("release_year")
    int release_year;
    @SerializedName("locations")
    String locations;
    @SerializedName("fun_facts")
    String fun_facts;
    @SerializedName("production_company")
    String production_company;
    @SerializedName("distributor")
    String distributor;

    @TypeConverters(ListTypeConverter.class)
    List<String> orderedLocations = new ArrayList<>();
    boolean favourite;
    boolean visited;

    public Film() {
        orderedLocations = new ArrayList<>();
    }


    public Film(String title, String director) {
        this.title = title;
        this.director = director;
        orderedLocations = new ArrayList<>();
    }

    public Film(String title, String actor_1, String actor_2, String actor_3, String director, int release_year, String locations, String fun_facts, String production_company, String distributor) {
        this.title = title;
        this.actor_1 = actor_1;
        this.actor_2 = actor_2;
        this.actor_3 = actor_3;
        this.director = director;
        this.release_year = release_year;
        this.locations = locations;
        this.fun_facts = fun_facts;
        this.production_company = production_company;
        this.distributor = distributor;
        orderedLocations = new ArrayList<>();
    }

    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof Film){
            Film ptr = (Film) v;
            retVal = ptr.title.equals(this.title);
        }

        return retVal;
    }

    public List<String> getOrderedLocations() {
        return orderedLocations;
    }

    public void setOrderedLocations(List<String> orderedLocations) {
        this.orderedLocations = orderedLocations;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getActor_1() {
        return actor_1;
    }

    public void setActor_1(String actor_1) {
        this.actor_1 = actor_1;
    }

    public String getActor_2() {
        return actor_2;
    }

    public void setActor_2(String actor_2) {
        this.actor_2 = actor_2;
    }

    public String getActor_3() {
        return actor_3;
    }

    public void setActor_3(String actor_3) {
        this.actor_3 = actor_3;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
        this.getOrderedLocations().add(locations);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public String getFun_facts() {
        return fun_facts;
    }

    public void setFun_facts(String fun_facts) {
        this.fun_facts = fun_facts;
    }

    public String getProduction_company() {
        return production_company;
    }

    public void setProduction_company(String production_company) {
        this.production_company = production_company;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

}
