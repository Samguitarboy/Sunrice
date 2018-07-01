package tw.edu.yzu.www.sunrise;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class SellPage extends Activity {
    private ImageView logo,shop,fb,ig,medium,youtube;
    private BitmapDrawable bmpDrawImg  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_page);
        logo = findViewById(R.id.logo);

        fnSetBackground(logo,0);//避免OOM 設定背景
        shop = findViewById(R.id.shop);
        fnSetBackground(shop,1);//避免OOM 設定背景
        fb = findViewById(R.id.fb);
        fnSetBackground(fb,2);//避免OOM 設定背景
        ig = findViewById(R.id.ig);
        fnSetBackground(ig,3);//避免OOM 設定背景
        medium = findViewById(R.id.medium);
        fnSetBackground(medium,4);//避免OOM 設定背景
        youtube = findViewById(R.id.youtube);
        fnSetBackground(youtube,5);//避免OOM 設定背景
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://m.youtube.com/channel/UChnYKKF8nxC0qYIOL2Q9gWw"));
                startActivity(intent);
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/sunrice1923"));
                startActivity(intent);
            }
        });
        ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://instagram.com/sunrice1923"));
                startActivity(intent);
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://medium.com/sunrice1923"));
                startActivity(intent);
            }
        });
    }
    public void fnSetBackground(ImageView view,Integer count){
        List<Integer> choose = new ArrayList<Integer>();
        choose.add(R.drawable.logo);
        choose.add(R.drawable.shop);
        choose.add(R.drawable.fb);
        choose.add(R.drawable.ig);
        choose.add(R.drawable.medium);
        choose.add(R.drawable.youtube);
        // 取得該張圖片，並放置在變數bmpDrawImg中
        Bitmap bitmap = BitmapUtils.getFitSampleBitmap(getApplicationContext(),choose.get(count), view.getWidth(), view.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),bitmap);
        // 最後就是設定圖片
        view.setBackground(bmpDrawImg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 每個Drawable被加到VIEW上面都會產生一個callback，所以在recycle圖片之前，必須先把callback設成null
        // 設成null以後，背景圖片自然就會不見，就會變成黑的背景。bmpDrawImg的狀態就會是沒有被使用中。
        logo.getBackground().setCallback(null);
        shop.getBackground().setCallback(null);
        fb.getBackground().setCallback(null);
        ig.getBackground().setCallback(null);
        medium.getBackground().setCallback(null);
        youtube.getBackground().setCallback(null);

        // 先判斷bmpDrawImg 是否為null，如果不是null，且bmpDrawImg 還沒有被recycle的話就進行recycle
        if (null != bmpDrawImg && !bmpDrawImg.getBitmap().isRecycled()){
            bmpDrawImg.getBitmap().recycle();
        }
        System.gc();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), RiceListPage.class);
        startActivity(intent);
        finish();

    }
}
