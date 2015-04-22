package ee.sinchukov.sharedpreferencessample;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int currentRate;
    private int highestRate;
    private TextView currentRateView;
    private TextView highestRateView;

    static public final String PREFS_NAME = "sharedPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences storeData = getSharedPreferences(PREFS_NAME,0); // 0 means only for this app

        currentRate = storeData.getInt("currentRate",0);
        highestRate = storeData.getInt("highestRate",0);

        currentRateView = (TextView) findViewById(R.id.currentRateView);
        highestRateView = (TextView) findViewById(R.id.highestRateView);

        currentRateView.setText("Current Rate: "+ currentRate);
        highestRateView.setText("Highest Rate: "+ highestRate);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.saveRates();


    }

    public void addCurrentRate(View view){
        currentRate++;
        currentRateView.setText("Current Rate: "+ currentRate);
        if(highestRate<currentRate)
        {
            highestRate=currentRate;
        }
        highestRateView.setText("Highest Rate: "+ highestRate);
    }

    public void clearCurrentRate(View view){
        currentRate=0;
        currentRateView.setText("Current Rate: "+ currentRate);
        this.saveRates();

    }

    public void clearAllRates(View view){
        currentRate=0;
        highestRate=0;
        currentRateView.setText("Current Rate: "+ currentRate);
        highestRateView.setText("Highest Rate: "+ highestRate);
        this.saveRates();

    }

    protected void saveRates() {
        SharedPreferences storeData = getSharedPreferences(PREFS_NAME,0); // 0 means only for this app
        SharedPreferences.Editor storeDataEditor = storeData.edit();
        storeDataEditor.putInt("currentRate",currentRate);
        storeDataEditor.putInt("highestRate",highestRate);
        storeDataEditor.commit();
             }


}
