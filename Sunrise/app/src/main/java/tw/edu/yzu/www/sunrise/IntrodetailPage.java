package tw.edu.yzu.www.sunrise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class IntrodetailPage extends Activity {

    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        img = findViewById(R.id.img);
        Bundle bundle = getIntent().getExtras();
        String place = bundle.getString("where");
        if(place=="1")
        {img.setImageResource(R.drawable.a1);}
        if("2".compareTo(place)==0)
        {img.setImageResource(R.drawable.a2);}
        if("3".compareTo(place)==0)
        {img.setImageResource(R.drawable.a3);}
        if("4".compareTo(place)==0)
        {img.setImageResource(R.drawable.a4);}
        if("5".compareTo(place)==0)
        {img.setImageResource(R.drawable.a5);}
        if("6".compareTo(place)==0)
        {img.setImageResource(R.drawable.a6);}
        if("7".compareTo(place)==0)
        {img.setImageResource(R.drawable.a7);}
        if("8".compareTo(place)==0)
        {img.setImageResource(R.drawable.a9);}
        if("9".compareTo(place)==0)
        {img.setImageResource(R.drawable.a10);}
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), RiceListPage.class);
        startActivity(intent);
        finish();

    }
}