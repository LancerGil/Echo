package com.example.echo.views.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> fragLiveData = new MutableLiveData<>();

    public void setFragLiveData(String str){
        fragLiveData.setValue(str);
    }

    public LiveData<String> getFragLiveData(){
        return fragLiveData;
    }
}
