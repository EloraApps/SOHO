package asac.pacojh.soho;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class Principal extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    String instr;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        
        LinearLayout eit1;
        LinearLayout eit2;
        LinearLayout eit3;
        LinearLayout eit4;
        LinearLayout sdo;
        LinearLayout mag;
        LinearLayout c2;
        LinearLayout c3;
	
        eit1 = (LinearLayout) findViewById (R.id.eit171);
        eit2 = (LinearLayout) findViewById (R.id.eit195);
        eit3 = (LinearLayout) findViewById (R.id.eit284);
        eit4 = (LinearLayout) findViewById (R.id.eit304);
        sdo = (LinearLayout) findViewById (R.id.hmi);
        mag = (LinearLayout) findViewById (R.id.mag);
        c2 = (LinearLayout) findViewById (R.id.c2);
        c3 = (LinearLayout) findViewById (R.id.c3);
        
        eit1.setOnClickListener(this);
        eit2.setOnClickListener(this);
        eit3.setOnClickListener(this);
        eit4.setOnClickListener(this);
        sdo.setOnClickListener(this);
        mag.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String cam = null;
		
		switch(v.getId()){
			case R.id.eit171:
				cam = "http://soho.nascom.nasa.gov/data/realtime/eit_171/512/latest.jpg";
				instr = "eit171";
				Log.i("HA PRESIONADO: ", cam);
				break;
			case R.id.eit195:
				instr = "eit195";
				cam = "http://soho.nascom.nasa.gov/data/realtime/eit_195/512/latest.jpg";
				Log.i("HA PRESIONADO: ", cam);
				break;
			case R.id.eit284:
				instr = "eit284";
				cam = "http://soho.nascom.nasa.gov/data/realtime/eit_284/512/latest.jpg";
				Log.i("HA PRESIONADO: ", cam);
				break;
			case R.id.eit304:
				 instr = "eit304";
				cam = "http://soho.nascom.nasa.gov/data/realtime/eit_304/512/latest.jpg";
				Log.i("HA PRESIONADO: ", cam);
				break;
			case R.id.hmi:
		        instr = "sdo/hmi";
		        cam = "http://soho.nascom.nasa.gov/data/realtime/hmi_igr/512/latest.jpg";
				Log.i("HA PRESIONADO: ", cam);
				break;
			case R.id.mag:
		        instr = "magnetogram";
				cam = "http://soho.nascom.nasa.gov/data/realtime/hmi_mag/512/latest.jpg";
				Log.i("HA PRESIONADO: ", cam);
				break;
			case R.id.c2:
		        instr = "Lasco C2";
				cam = "http://soho.nascom.nasa.gov/data/realtime/c2/512/latest.jpg";
				Log.i("HA PRESIONADO: ", cam);
				break;
			case R.id.c3:
		        instr = "Lasco C3";
				cam = "http://soho.nascom.nasa.gov/data/realtime/c3/512/latest.jpg";
				Log.i("HA PRESIONADO: ", cam);
				break;
				
		}
		
		Intent i = new Intent(this, VerImagen.class);
			i.putExtra("url", cam);
			i.putExtra("instrumento", instr);
		startActivity(i);
	}
	

}