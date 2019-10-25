package com.nivek.kevoweather;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView ;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherListFragment extends Fragment {
    private static final String TAG = "WeatherListFragment";

    /**
     * Creates and return an instace of the fragment
     * @return an instance of wheatherlist fragment
     * */
    public static WeatherListFragment newInsance(){
        return  new WeatherListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG,"An instance of the fragment was created") ;
        new FetchWeatherDataTask().execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_weather_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.weather_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view ;
    }

    //utility class to run the weather data fetching operation on another thread outside the main
    private class FetchWeatherDataTask extends AsyncTask<Void , Void , Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            new WeatherDataFetcher().getWeatherData("2172797");
            Log.i(TAG,"Ran the Async thread") ;
            return null;
        }
    }

}
