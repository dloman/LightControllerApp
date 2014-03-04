package info.androidhive.tabsswipe;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
HttpResponse mResponse;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment1, container, false);

		ColorPicker picker = (ColorPicker) rootView.findViewById(R.id.picker);
		ValueBar valueBar = (ValueBar) rootView.findViewById(R.id.valuebar);
		OpacityBar opacityBar = (OpacityBar) rootView.findViewById(R.id.opacitybar);
		Button SendButton = (Button) rootView.findViewById(R.id.button1);
		final ToggleButton AutoSendButton = (ToggleButton) rootView.findViewById(R.id.toggleButton1);
		SendButton.setOnClickListener(SendButtonListener);
		picker.addValueBar(valueBar);
		picker.addOpacityBar(opacityBar);
		mColor = picker.getColor();
		picker.setOldCenterColor(picker.getColor());
		
		OnColorChangedListener ColorChangedListener = new OnColorChangedListener()
		{
			@Override
			public void onColorChanged(int color) {
				mColor = color;
				if ( AutoSendButton.isChecked())
				{
    				postData("http://0.0.0.0:8080",Color.alpha(mColor), Color.red(mColor), Color.green(mColor), Color.blue(mColor));
				}
			}
		};
		
		picker.setOnColorChangedListener(ColorChangedListener);

		//to turn of showing the old color
		picker.setShowOldCenterColor(false);
		
		return rootView;
	}
	
	View.OnClickListener SendButtonListener = new View.OnClickListener() {
		@Override
		@SuppressLint("ShowToast")
		public void onClick(View v) {
			// TODO Auto-generated method stub
	    Toast.makeText(getActivity(), "Sending Data", Toast.LENGTH_SHORT).show();
	    postData("http://dloman.dyndns.org:8080",Color.alpha(mColor), Color.red(mColor), Color.green(mColor), Color.blue(mColor));
		}
	};
	
	public void postData(final String Url, final int Alpha, final int Red, final int Green, final int Blue) {

		new Thread(new Runnable(){
		public void run(){
    	   
       try{
    	   HttpClient httpClient = new DefaultHttpClient();
   		// Creating HTTP Post
   		HttpPost httpPost = new HttpPost(Url);

   		// Building post parameters
   		// key and value pair
   		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
   		nameValuePair.add(new BasicNameValuePair("Type", "Color"));
   		nameValuePair.add(new BasicNameValuePair("Alpha", Integer.toString(Alpha)));
   		nameValuePair.add(new BasicNameValuePair("Red", Integer.toString(Red)));
   		nameValuePair.add(new BasicNameValuePair("Green", Integer.toString(Green)));
   		nameValuePair.add(new BasicNameValuePair("Blue", Integer.toString(Blue)));

   		// Url Encoding the POST parameters
   			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

   		// Making HTTP Request
   			mResponse = httpClient.execute(httpPost);
       }
		catch (Exception e)
		{Toast.makeText(getActivity(), "bummer shit broke", Toast.LENGTH_SHORT).show();}
	     }
       }).start();
		//Toast.makeText(getActivity(), mResponse.toString(), Toast.LENGTH_LONG).show();
	}
  
}