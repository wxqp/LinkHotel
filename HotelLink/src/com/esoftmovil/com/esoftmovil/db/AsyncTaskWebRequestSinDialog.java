package com.esoftmovil.com.esoftmovil.db;

/**
 * Created by Raul on 9/10/13.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AsyncTaskWebRequestSinDialog extends AsyncTask<String, Void, String>{
    private static final int REGISTRATION_TIMEOUT = 10 * 1000;
    private static final int WAIT_TIMEOUT = 30 * 1000;
    private final HttpClient httpclient = new DefaultHttpClient();

    final HttpParams params = httpclient.getParams();
    HttpResponse response;
    private String content =  null;
    private boolean error = false;
    public String Valor;
    public Context AppContext;

    public List<NameValuePair> postParameters;

    //Listeners
    private onPostExecuteListener postExecuteListener;

    public void setPostExecuteListener(onPostExecuteListener postExecuteListener) {
        this.postExecuteListener = postExecuteListener;
    }
    public AsyncTaskWebRequestSinDialog(Context ctx, List<NameValuePair> prPostParameters)
	{
		this.AppContext=ctx;
        this.postParameters = prPostParameters;
    }
    public AsyncTaskWebRequestSinDialog(Context ctx)
    {
    	this.postParameters = new ArrayList<NameValuePair>();
        this.AppContext=ctx;

    }
    public AsyncTaskWebRequestSinDialog()
    {
        this.postParameters=null;
    }
    
    protected String doInBackground(String... urls) {

        String URL = null;
        try {

            Thread.sleep(500);


            //URL passed to the AsyncTask
            URL = urls[0];
            HttpConnectionParams.setConnectionTimeout(params, REGISTRATION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, WAIT_TIMEOUT);
            ConnManagerParams.setTimeout(params, WAIT_TIMEOUT);


            HttpPost httpPost = new HttpPost(URL);

            //Any other parameters you would like to set
            if(this.postParameters != null && !this.postParameters.isEmpty())
                httpPost.setEntity(new UrlEncodedFormEntity(this.postParameters));

            //Response from the Http Request
            response = httpclient.execute(httpPost);

            StatusLine statusLine = response.getStatusLine();
            //Check the Http Request for success
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                content = out.toString();
            }
            else{
                //Closes the connection.
                Log.w("HTTP1:",statusLine.getReasonPhrase());
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }


        }catch (InterruptedException e) { Log.i("Interrumpido",e.toString());}
        catch (ClientProtocolException e) {
            Log.w("HTTP2:",e );
            content = e.getMessage();
            error = true;
            cancel(true);
        } catch (IOException e) {
            Log.w("HTTP3:",e );
            content = e.getMessage();
            error = true;
            cancel(true);
        }catch (Exception e) {
            Log.w("HTTP4:",e );
            content = e.getMessage();
            error = true;
            cancel(true);
        }

        return content;
    }

    protected void onCancelled()
    {
        Log.d("AsyncTaskWebRequest", "Proceso cancelado");
        if(this.postExecuteListener!=null) this.postExecuteListener.onPostExecute(null,true);
    }

    protected void onPostExecute(String content) {

        if(this.postExecuteListener!=null)
        {
            this.postExecuteListener.onPostExecute(content,error);
        }

    }

    public interface onPostExecuteListener 
    {
        public void onPostExecute(String content, Boolean error);
    }
    @Override
    protected void onPreExecute() 
    {
        super.onPreExecute();
    }

    protected void onProgressUpdate(){

    }
}
