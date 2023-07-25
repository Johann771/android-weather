package fr.johann.android.androidweatherapp.activities;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import fr.johann.android.androidweatherapp.R;
import fr.johann.android.androidweatherapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private TextView mTextViewCityName;
    private Button mButton2;
    private EditText mEditTextInput;
    private ActivityMainBinding binding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = this;
        mTextViewCityName = (TextView) findViewById(R.id.text_view_city_name);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            binding.linearLayoutBackground.setVisibility(View.VISIBLE);
        } else {
            binding.linearLayoutBackground.setVisibility(View.INVISIBLE);

        }



        Log.d("TAG", "MainActivity: onCreate()");
    }
    public void onButtonClick(View view) {
        Intent intent = new Intent(mContext, FavoriteActivity.class);
        intent.putExtra("key_message", binding.mEditTextInput.getText().toString());
        startActivity(intent);
    }
    View.OnClickListener onClickListenerButton2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Clic sur le bouton 2", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "FavoriteActivity: onDestroy()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "FavoriteActivity: onStart()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "FavoriteActivity: onRestart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "FavoriteActivity: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "FavoriteActivity: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "FavoriteActivity: onStop()");
    }
}