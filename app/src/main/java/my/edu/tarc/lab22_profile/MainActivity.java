package my.edu.tarc.lab22_profile;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //just simply put a number
    private static final  int PROFILE_UPDATE_REQUEST = 5588;
    public static final String PROFILE_NAME = "my.edu.tarc.lab22_profile.name";
    public static final String PROFILE_EMAIL = "my.edu.tarc.lab22_profile.email";

    private TextView textViewName,textViewEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link
        textViewName=(TextView)findViewById(R.id.textViewName);
        textViewEmail=(TextView)findViewById(R.id.textViewEmail);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {// will return these parameter
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode= the uique code t indentify intent call
        //result code=the result of an intent call; either OK or cancel
        //intent data=the data returned by an intent call
        if(requestCode==PROFILE_UPDATE_REQUEST && resultCode==RESULT_OK){
            String name,email;
            name=data.getStringExtra(PROFILE_NAME);
            email=data.getStringExtra(PROFILE_EMAIL);
            textViewName.setText(getString(R.string.name)+": "+name);
            textViewEmail.setText(getString(R.string.email)+": "+email);
        }
    }

    //to handle update button event
    public void updateProfile(View view){
        Intent intent=new Intent(this,ProfileActivity.class);
        startActivityForResult(intent,PROFILE_UPDATE_REQUEST);
    }

    public void visitBAIT2073(View v){
        String uri="http://bait2073.000webhostapp.com/welcome.html";
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void showMain(View v){
        Intent intent=new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }
    public void showDial(View v){
        Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel: "+"0112345678"));
        startActivity(intent);
    }

 public void showSendTo(View v){
     Intent intent=new Intent(Intent.ACTION_SENDTO);
     intent.setData(Uri.parse("mailto: "+"seekt@tarc.edu.my"));

     //verify it resolves
     PackageManager packageManager=getPackageManager();
     List<ResolveInfo> activities=packageManager.queryIntentActivities(intent,0);
     boolean isInstantSafe=activities.size()>0;

     //Start an activity if it's safe
     if(isInstantSafe){
         startActivity(intent);
     }
 }
}
