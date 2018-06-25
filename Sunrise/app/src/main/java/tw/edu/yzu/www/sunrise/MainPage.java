package tw.edu.yzu.www.sunrise;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainPage extends Activity {

    public FragmentManager mFragmentMgr;
    public SproutFragment mSproutFragment;
    public HarvestFragment mHarvestFragment;

    private RelativeLayout layoutMain = null;
    private BitmapDrawable bmpDrawImg  = null;
    private ImageView sprout;
    private ImageView growth;
    private ImageView harvest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        sprout = findViewById(R.id.sprout);
        growth = findViewById(R.id.growth);
        harvest = findViewById(R.id.harvest);


        fnSetBackground(sprout,growth,harvest);//避免OOM 設定背景

        mFragmentMgr = getFragmentManager();
        mSproutFragment = new SproutFragment();
        mHarvestFragment = new HarvestFragment();

        sprout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentMgr.beginTransaction()
                        .replace(R.id.layout_main, mSproutFragment, "Main2Sprout")
                        .addToBackStack(null)
                        .commit();
            }
        });
        growth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), IntroductionPage.class);
                startActivity(intent);
                finish();
            }
        });
        harvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentMgr.beginTransaction()
                        .replace(R.id.layout_main, mHarvestFragment, "Main2Harvest")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void fnSetBackground(ImageView a,ImageView b,ImageView c){
        // 先取得RelativeLayout
        layoutMain = findViewById(R.id.layout_main);
        // 取得該張圖片，並放置在變數bmpDrawImg中
        Bitmap bitmap = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.main, layoutMain.getWidth(), layoutMain.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),bitmap);
        // 最後就是設定圖片
        layoutMain.setBackground(bmpDrawImg);

        Bitmap sprout = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.sprout, a.getWidth(), a.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),sprout);
        a.setBackground(bmpDrawImg);

        Bitmap growth = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.growth, b.getWidth(), b.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),growth);
        b.setBackground(bmpDrawImg);

        Bitmap harvest = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.harvest, c.getWidth(), c.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),harvest);
        c.setBackground(bmpDrawImg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 每個Drawable被加到VIEW上面都會產生一個callback，所以在recycle圖片之前，必須先把callback設成null
        // 設成null以後，背景圖片自然就會不見，就會變成黑的背景。bmpDrawImg的狀態就會是沒有被使用中。
        layoutMain.getBackground().setCallback(null);
        sprout.getBackground().setCallback(null);
        growth.getBackground().setCallback(null);
        harvest.getBackground().setCallback(null);

        // 先判斷bmpDrawImg 是否為null，如果不是null，且bmpDrawImg 還沒有被recycle的話就進行recycle
        if (null != bmpDrawImg && !bmpDrawImg.getBitmap().isRecycled()){
            bmpDrawImg.getBitmap().recycle();
        }
        System.gc();
    }
}
