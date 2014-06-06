package info.androidhive.tabsswipe;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Fragment2 extends Fragment {

	SeekBar mFrequencyBar;
	Switch mVerticalSwitch, mHorizontalSwitch;
	Spinner mSpinner;
	ToggleButton LaserToggle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment2, container, false);
		Button StartSloshButton = (Button) rootView.findViewById(R.id.SloshButton);
	    StartSloshButton.setOnClickListener(StartSlosh);
	    Button OffButton = (Button) rootView.findViewById(R.id.OffButton);
	    OffButton.setOnClickListener(LightsOff);
	    LaserToggle = (ToggleButton) rootView.findViewById(R.id.LaserToggle);
	    LaserToggle.setOnClickListener(LaserToggleListener);
		mFrequencyBar = (SeekBar) rootView.findViewById(R.id.seekBar1);

		mSpinner = (Spinner) rootView.findViewById(R.id.spinner1);
		ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(
	        getActivity(),
			R.array.ModeTypes,
			R.layout.spinner_item);
		Adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
		mSpinner.setAdapter(Adapter);

	    return rootView;
	}

	View.OnClickListener StartSlosh = new View.OnClickListener(){

		@Override
		public void onClick(View v)
		{
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
	   		nameValuePair.add(new BasicNameValuePair("Type", "Mode"));
	   		nameValuePair.add(new BasicNameValuePair(
	   		   "Frequency",
	   		   Integer.toString(mFrequencyBar.getProgress())));
	   		nameValuePair.add(new BasicNameValuePair(
	   			"Mode",
	   			mSpinner.getSelectedItem().toString()));
		    Toast.makeText(getActivity(), "Sending Mode Data", Toast.LENGTH_SHORT).show();
	   		@SuppressWarnings("unused")
			HttpPostWrapper httpPostWrapper = new HttpPostWrapper(MainActivity.GetUrl(), nameValuePair);

		}

	};

	View.OnClickListener LightsOff = new View.OnClickListener(){

		@Override
		public void onClick(View v)
		{
      for ( int i = 0; i < 3; i++)
      {
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
          nameValuePair.add(new BasicNameValuePair("Type", "fColor"));
          nameValuePair.add(new BasicNameValuePair("Alpha", "0"));
          nameValuePair.add(new BasicNameValuePair("Red", "0"));
          nameValuePair.add(new BasicNameValuePair("Green", "0"));
          nameValuePair.add(new BasicNameValuePair("Blue", "0"));
          @SuppressWarnings("unused")
          HttpPostWrapper httpPostWrapper = new HttpPostWrapper(MainActivity.GetUrl(), nameValuePair);
      }

		}

	};
  View.OnClickListener LaserToggleListener = new View.OnClickListener() {
	  
	@Override
	public void onClick(View v) {
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("Type", "LaserToggle"));
        nameValuePair.add(new BasicNameValuePair("Value", Boolean.toString(LaserToggle.isChecked())) );
        @SuppressWarnings("unused")
        HttpPostWrapper httpPostWrapper = new HttpPostWrapper(MainActivity.GetUrl(), nameValuePair);
        
	}
};
}
