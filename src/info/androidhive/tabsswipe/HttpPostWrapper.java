package info.androidhive.tabsswipe;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.widget.Toast;

public class HttpPostWrapper {
    public HttpPostWrapper(final String Url, final List<NameValuePair> nameValuePair)
    {
		Thread RequestThread = new Thread(new Runnable()
		{
		  public void run()
		  {
	    	   
	        try
	        {
	    	     HttpClient httpClient = new DefaultHttpClient();
	   		     // Creating HTTP Post
	   		     HttpPost httpPost = new HttpPost(Url);

	   		     // Url Encoding the POST parameters
	   			 httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

	   		     // Making HTTP Request
	   			 httpClient.execute(httpPost);
	        }
			catch (Exception e)
			{
				MainActivity.showToast();
			}
		 }
	    });
		RequestThread.start();
    }
}
