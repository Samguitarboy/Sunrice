package tw.edu.yzu.www.sunrise;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainPage extends Activity {

    public FragmentManager mFragmentMgr;
    public SproutFragment mSproutFragment;
    public HarvestFragment mHarvestFragment;

    private BitmapDrawable bmpDrawImg  = null;
    private ImageView logo,sprout,growth,harvest;
    private int showWidth,showHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        logo = findViewById(R.id.logo);
        sprout = findViewById(R.id.sprout);
        growth = findViewById(R.id.growth);
        harvest = findViewById(R.id.harvest);

        fnSetBackground(logo,sprout,growth,harvest,dm);//避免OOM 設定背景

        clicklistener(logo,sprout,growth,harvest);

    }

    public void clicklistener(ImageView logo,ImageView a,ImageView b,ImageView c){

        mFragmentMgr = getFragmentManager();
        mSproutFragment = new SproutFragment();
        mHarvestFragment = new HarvestFragment();

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://sunrice1923.com/landing"));
                startActivity(intent);
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprout.setClickable(false);
                growth.setClickable(false);
                harvest.setClickable(false);

                mFragmentMgr.beginTransaction()
                        .replace(R.id.layout_main, mSproutFragment, "Main2Sprout")
                        .addToBackStack(null)
                        .commit();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprout.setClickable(false);
                growth.setClickable(false);
                harvest.setClickable(false);
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), RiceListPage.class);
                startActivity(intent);
                finish();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprout.setClickable(false);
                growth.setClickable(false);
                harvest.setClickable(false);
                mFragmentMgr.beginTransaction()
                        .replace(R.id.layout_main, mHarvestFragment, "Main2Harvest")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void fnSetBackground(ImageView logo,ImageView a,ImageView b,ImageView c, DisplayMetrics dm){
        showWidth = dm.widthPixels-400;   //螢幕的寬
        showHeight = dm.heightPixels/6;  //螢幕的高

        // 取得該張圖片，並放置在變數bmpDrawImg中
        Bitmap bitmap = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.logo1, logo.getWidth(), logo.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),bitmap);
        // 最後就是設定圖片
        logo.setBackground(bmpDrawImg);
        myimageviewsize(logo,showWidth,showHeight);


        showWidth = dm.widthPixels/3;
        showHeight = dm.heightPixels;

        Bitmap sprout = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.sprout, a.getWidth(), a.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),sprout);
        a.setBackground(bmpDrawImg);
        myimageviewsize(a,showWidth,showHeight);

        Bitmap growth = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.growth, b.getWidth(), b.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),growth);
        b.setBackground(bmpDrawImg);
        myimageviewsize(b,showWidth,showHeight);

        Bitmap harvest = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.harvest, c.getWidth(), c.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),harvest);
        c.setBackground(bmpDrawImg);
        myimageviewsize(c,showWidth,showHeight);

    }

    private void myimageviewsize(ImageView imgid, int evenWidth, int evenHight) {
        // TODO 自動產生的方法 Stub
        ViewGroup.LayoutParams params = imgid.getLayoutParams();  //需import android.view.ViewGroup.LayoutParams;
        params.width = evenWidth;
        params.height = evenHight;
        imgid.setLayoutParams(params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 每個Drawable被加到VIEW上面都會產生一個callback，所以在recycle圖片之前，必須先把callback設成null
        // 設成null以後，背景圖片自然就會不見，就會變成黑的背景。bmpDrawImg的狀態就會是沒有被使用中。
        logo.getBackground().setCallback(null);
        sprout.getBackground().setCallback(null);
        growth.getBackground().setCallback(null);
        harvest.getBackground().setCallback(null);

        // 先判斷bmpDrawImg 是否為null，如果不是null，且bmpDrawImg 還沒有被recycle的話就進行recycle
        if (null != bmpDrawImg && !bmpDrawImg.getBitmap().isRecycled()){
            bmpDrawImg.getBitmap().recycle();
        }
        System.gc();
    }

    @Override
    public void onBackPressed() {
        sprout.setClickable(true);
        growth.setClickable(true);
        harvest.setClickable(true);
        if(mFragmentMgr!=null)
            mFragmentMgr.popBackStack();
    }
}
