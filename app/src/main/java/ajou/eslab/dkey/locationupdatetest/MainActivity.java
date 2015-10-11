package ajou.eslab.dkey.locationupdatetest;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    LocationManager mLocMan;
    TextView tvResult;
    Button btnStart;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLocMan = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(mOnClickListener
        );
        //mLocMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, mLocLis);
        //mLocMan.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, mLocLis);
        mLocMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 0, mLocLis);


        //findviebyid
        //setOnClickListener
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mLocMan.removeUpdates(mLocLis);
        Log.d("LOC.DKEY", "removeUpdates(listener)");
    }

    Button.OnClickListener mOnClickListener = new View.OnClickListener(){
        public void onClick (View v){
            switch(v.getId()){
                //requestlocationupdates
                case R.id.btnStart :
                    mLocMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, mLocLis);
                    break;
            }
        }
    };

    LocationListener mLocLis = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            tvResult.setText("["+count+"] LOC CH **"+location.toString());
            Log.d("LOC.DKEY", "[" + count + "] LOC CH **" + location.toString());
            count++;
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            tvResult.setText("STAT CH **");
        }
        @Override
        public void onProviderEnabled(String provider) {
            tvResult.setText("PRO EN **");
        }
        @Override
        public void onProviderDisabled(String provider) {
            tvResult.setText("PRO DIS **");
        }
    };



}
