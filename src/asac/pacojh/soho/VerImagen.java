package asac.pacojh.soho;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class VerImagen extends Activity{
    /** Called when the activity is first created. */
	String webAddr;
	//TouchImageView tw1;
	TouchImageView tv;
	String instrumento;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_imagen);
        
        Bundle b = getIntent().getExtras();
        webAddr = b.getString("url");
        instrumento = b.getString("instrumento");
        tv = (TouchImageView) findViewById(R.id.foto);
        CargaImagenes nuevaTarea = new CargaImagenes();
        nuevaTarea.execute(webAddr);     
        
	}
	
	
	private class CargaImagenes extends AsyncTask<String, Void, Bitmap>{
		 
	    ProgressDialog pDialog;
	 
	    @Override
	    protected void onPreExecute() {
	        // TODO Auto-generated method stub
	        super.onPreExecute();
	         
	        pDialog = new ProgressDialog(VerImagen.this);
	        pDialog.setMessage("Loading image from: " + instrumento);
	        pDialog.setCancelable(true);
	        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pDialog.show();
	         
	    }
	 
	    @Override
	    protected Bitmap doInBackground(String... params) {
	        // TODO Auto-generated method stub
	        Log.i("doInBackground" , "Entra en doInBackground");
	        String url = params[0];
	        Bitmap imagen = descargarImagen(url);
	        return imagen;
	    }
	     
	    @Override
	    protected void onPostExecute(Bitmap result) {
	        // TODO Auto-generated method stub
	        super.onPostExecute(result);
	         
	        tv.setImageBitmap(result);
	        pDialog.dismiss();
	    }
	     
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.main_activity_actions,  menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.info:
	        	//Toast.makeText(this, "IMAGE USING: " + instrumento, Toast.LENGTH_LONG).show();
	            Dialog dial = new Dialog(this);
	            TextView tv;
	            String s = rellenaTextView(instrumento); 
	            dial.setContentView(R.layout.alertdialog);
	            dial.setTitle(instrumento);
	            tv = (TextView) dial.findViewById(R.id.dialogo);
	            tv.setText(s);
	            dial.show();	
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
		
	}

	private Bitmap descargarImagen (String imageHttpAddress){
	    URL imageUrl = null;
	    Bitmap imagen = null;
	    try{
	        imageUrl = new URL(imageHttpAddress);
	        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
	        conn.connect();
	        imagen = BitmapFactory.decodeStream(conn.getInputStream());
	    }catch(IOException ex){
	        ex.printStackTrace();
	    }
	     
	    return imagen;
	}
	
	public String rellenaTextView(String inst){
		String send=null;
		if (inst.contains("eit")){
			Log.i("ENTRA??: ", "LLEVA EIT");
			send = "EIT (Extreme ultraviolet Imaging Telescope) images the solar atmosphere at several wavelengths, and therefore, shows solar material at different temperatures. In the images taken at 304 Angstrom the bright material is at 60,000 to 80,000 degrees Kelvin. In those taken at 171 Angstrom, at 1 million degrees. 195 Angstrom images correspond to about 1.5 million Kelvin, 284 Angstrom to 2 million degrees. The hotter the temperature, the higher you look in the solar atmosphere.";
		}else if (inst.contains("C2")){
			send = "LASCO (Large Angle Spectrometric Coronagraph) is able to take images of the solar corona by blocking the light coming directly from the Sun with an occulter disk, creating an artificial eclipse within the instrument itself. The position of the solar disk is indicated in the images by the white circle. The most prominent feature of the corona are usually the coronal streamers, those nearly radial bands that can be seen both in C2 and C3. Occasionally, a coronal mass ejection can be seen being expelled away from the Sun and crossing the fields of view of both coronagraphs. The shadow crossing from the lower left corner to the center of the image is the support for the occulter disk. C2 images show the inner solar corona up to 8.4 million kilometers (5.25 million miles) away from the Sun.";
		}else if (inst.contains("C3")){
			send = "C3 images have a larger field of view: They encompass 32 diameters of the Sun. To put this in perspective, the diameter of the images is 45 million kilometers (about 30 million miles) at the distance of the Sun, or half of the diameter of the orbit of Mercury. Many bright stars can be seen behind the Sun.";
		}else if (inst.contains("hmi")){
			send = "The MDI (Michelson Doppler Imager) images shown here are taken in the continuum near the Ni I 6768 Angstrom line. The most prominent features are the sunspots. This is very much how the Sun looks like in the visible range of the spectrum (for example, looking at it using special 'eclipse' glasses: Remember, do not ever look directly at the Sun!). The magnetogram image shows the magnetic field in the solar photosphere, with black and white indicating opposite polarities.";
		}else if (inst.contains("magn")){
			send = "The MDI (Michelson Doppler Imager) images shown here are taken in the continuum near the Ni I 6768 Angstrom line. The most prominent features are the sunspots. This is very much how the Sun looks like in the visible range of the spectrum (for example, looking at it using special 'eclipse' glasses: Remember, do not ever look directly at the Sun!). The magnetogram image shows the magnetic field in the solar photosphere, with black and white indicating opposite polarities.";
		}
		
		return send;
		
	}

}