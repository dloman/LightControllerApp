package info.androidhive.tabsswipe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.ColorPicker.OnColorChangedListener;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.ValueBar;

public class Fragment1 extends Fragment {
int mColor;
long mTime;
ToggleButton AutoSendButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment1, container, false);

		mTime = System.currentTimeMillis();
		ColorPicker picker = (ColorPicker) rootView.findViewById(R.id.picker);
		ValueBar valueBar = (ValueBar) rootView.findViewById(R.id.valuebar);
		OpacityBar opacityBar = (OpacityBar) rootView.findViewById(R.id.opacitybar);
		Button SendButton = (Button) rootView.findViewById(R.id.SendColor);
		AutoSendButton = (ToggleButton) rootView.findViewById(R.id.toggleButton1);
		SendButton.setOnClickListener(SendButtonListener);
		picker.addValueBar(valueBar);
		picker.addOpacityBar(opacityBar);
		mColor = picker.getColor();
		
		picker.setOldCenterColor(picker.getColor());		
		picker.setOnColorChangedListener(ColorChangedListener);

		//to turn of showing the old color
		picker.setShowOldCenterColor(false);
		
		return rootView;
	}
	
	OnColorChangedListener ColorChangedListener = new OnColorChangedListener()
	{
		@Override
		@SuppressLint("ShowToast")
		public void onColorChanged(int color) 
		{
			mColor = color;
			if ( AutoSendButton.isChecked())
			{
				if (System.currentTimeMillis() - mTime > 75)
				{
				  postColor(MainActivity.GetUrl(), mColor,"j");
				  mTime = System.currentTimeMillis();
				}
			}
		}
	};
	
	
	View.OnClickListener SendButtonListener = new View.OnClickListener() 
	{
		@Override
		@SuppressLint("ShowToast")
		public void onClick(View v) {
			// TODO Auto-generated method stub
	    Toast.makeText(getActivity(), "Sending Data", Toast.LENGTH_SHORT).show();
	    postColor(MainActivity.GetUrl(), mColor,"f");
		}
	};
	
	public long GetTimeLastSent()
	{
		return mTime;
	}
	
	public void postColor(final String Url, final int InputColor,final String Method)
	{
		// Building post parameters
   		// key and value pair
   		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
   		nameValuePair.add(new BasicNameValuePair("Type", Method+"Color"));
   		nameValuePair.add(new BasicNameValuePair("Alpha", Integer.toString(Color.alpha(InputColor))));
   		nameValuePair.add(new BasicNameValuePair("Red", Integer.toString(Color.red(InputColor))));
   		nameValuePair.add(new BasicNameValuePair("Green", Integer.toString(Color.green(InputColor))));
   		nameValuePair.add(new BasicNameValuePair("Blue", Integer.toString(Color.blue(InputColor))));
   		HttpPostWrapper httpPostWrapper = new HttpPostWrapper(Url, nameValuePair);
	}
}