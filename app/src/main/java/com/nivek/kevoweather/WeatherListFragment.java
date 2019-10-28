package com.nivek.kevoweather;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView ;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherListFragment extends Fragment {
    private NigerianState[] statesData ;
    private static final String TAG = "WeatherListFragment";
    private RecyclerView recyclerView ;

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
        new FetchWeatherDataTask().execute();
        Log.i(TAG,"An instance of the fragment was created") ;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_weather_list, container, false);
        recyclerView = view.findViewById(R.id.weather_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view ;
    }

    private void setupAdapter(){

        Log.i(TAG,"was at least called not sure if attached to activity") ;
        if(isAdded()){
            Log.i(TAG , "was called for a job to setup adapte after being attached to activity");
            recyclerView.setAdapter(new StateWeatherAdapater(statesData));
        }

    }

    //utility class to run the weather data fetching operation on another thread outside the main
    private class FetchWeatherDataTask extends AsyncTask<Void , Void , NigerianState[]>{
        @Override
        protected NigerianState[] doInBackground(Void... voids) {
            Log.i(TAG,"Run the Async thread") ;
            try{
                Log.i(TAG,"Tried getting inflated data");
                return  new WeatherDataFetcher().getInflatedData();
            }
            catch (JSONException jse){
                Log.e(TAG,"json error") ;
            }
           return  null ;
        }
        @Override
        protected void onPostExecute(NigerianState[] nigerianStates){
            statesData = nigerianStates ;
            setupAdapter();
            Log.i(TAG,"tried setting up adapter") ;
        }
    }

    //create view holder for the recycler view
    private class StateWeatherHolder extends RecyclerView.ViewHolder{
        private View view ;
        private TextView city  ;
        private TextView lowTemp ;
        private TextView highTemp ;
        private ImageView weatherIcon ;
        private TextView desc;

        public StateWeatherHolder(View view){
            super(view);
            this.view = view ;
            this.city = view.findViewById(R.id.city) ;
            this.lowTemp = view.findViewById(R.id.low_temp) ;
            this.highTemp = view.findViewById(R.id.high_temp) ;
            this.weatherIcon = view.findViewById(R.id.weather_icon) ;
            this.desc= view.findViewById(R.id.desc);
        }

        public void bindWeatherData(NigerianState state){
            city.setText(state.getName());
            lowTemp.setText(state.getLowTemp());
            highTemp.setText(state.getHighTemp());
            desc.setText(state.getDesc());
            //left here to attach the icon , this will be accounted for later
        }

    }

    //recycler view adapter for the view
    private class StateWeatherAdapater extends  RecyclerView.Adapter<StateWeatherHolder>{
        private NigerianState[] stateData ;
        public StateWeatherAdapater(NigerianState[] states){
            this.stateData = states ;
        }

        @NonNull
        @Override
        public StateWeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity()) ;
            View view = inflater.inflate(R.layout.weather_item , parent , false) ;
            return  new StateWeatherHolder(view) ;
        }

        @Override
        public void onBindViewHolder(@NonNull StateWeatherHolder holder, int position) {
            holder.bindWeatherData(this.stateData[position]);
        }

        @Override
        public int getItemCount() {
            return this.stateData.length;
        }
    }

}
