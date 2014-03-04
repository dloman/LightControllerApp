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
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class Fragment2 extends Fragment {

	SeekBar mFrequencyBar;
	Switch mVerticalSwitch, mHorizontalSwitch;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment2, container, false);
		Button StartSloshButton = (Button) rootView.findViewById(R.id.SloshButton);
	    StartSloshButton.setOnClickListener(StartSlosh);
		mFrequencyBar = (SeekBar) rootView.findViewById(R.id.seekBar1);
	    mVerticalSwitch = (Switch) rootView.findViewById(R.id.switch1);
	    mHorizontalSwitch = (Switch) rootView.findViewById(R.id.switch2);
	    
	    
	    return rootView;
	}
	
	View.OnClickListener StartSlosh = new View.OnClickListener(){

		@Override
		public void onClick(View v) 
		{
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
	   		nameValuePair.add(new BasicNameValuePair("Type", "Slosh"));
	   		nameValuePair.add(new BasicNameValuePair(
	   	       "Horizontal", 
	   	       Boolean.toString(mHorizontalSwitch.isChecked())));
	   		nameValuePair.add(new BasicNameValuePair(
	   		   "Vertical", 
	   		   Boolean.toString(mVerticalSwitch.isChecked())));
	   		nameValuePair.add(new BasicNameValuePair(
	   		   "Frequency", 
	   		   Integer.toString(mFrequencyBar.getProgress())));
	   		HttpPostWrapper httpPostWrapper = new HttpPostWrapper(MainActivity.GetUrl(), nameValuePair);

		}
		
	};
}
