package tw.edu.yzu.www.sunrise;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class TestPage extends Activity {
    private ImageView a,b,question;
    private TextView qq,a1,a2;
    private int count = 1,t3=0,t9=0,black=0,red=0,showWidth,showHeight;
    public FragmentManager mFragmentMgr;
    public RecommendFragment mReccommendFragment;
    private BitmapDrawable bmpDrawImg  = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_page);

        mFragmentMgr = getFragmentManager();
        mReccommendFragment = new RecommendFragment();

        a=findViewById(R.id.a);
        b=findViewById(R.id.b);
        qq=findViewById(R.id.qq);
        a1=findViewById(R.id.a1);
        a2=findViewById(R.id.a2);
        question=findViewById(R.id.question);

        fnSetBackground(a,b);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==1){
                    t3+=1;
                    question.setImageResource(R.drawable.brace1);
                    qq.setText("吃飯時會選擇擁有天然好氣色還是強健身體的風格呢?");
                    a1.setText("天然好氣色");
                    a2.setText(" 強健身體");
                }
                if(count==2){
                    black+=1;
                    question.setImageResource(R.drawable.brace2);
                    qq.setText("常被中醫說脾胃不好還是常被西醫說容易感冒呢?");
                    a1.setText("脾胃不好");
                    a2.setText(" 容易感冒");
                }
                if(count==3){
                    black+=1;
                    question.setImageResource(R.drawable.brace3);
                    qq.setText("喜歡在飯裡加入雜糧或是糯米一起食用嗎?");
                    a1.setText("喜歡");
                    a2.setText(" 不喜歡");
                }
                if(count==4){
                    black+=1;
                    red+=1;
                    question.setImageResource(R.drawable.brace4);
                    qq.setText("喜歡純粹米香還是帶有芋頭香的米呢?");
                    a1.setText("純粹米香");
                    a2.setText(" 芋頭香");
                }
                if(count==5){
                    t9+=1;
                    Map<Integer, String> map = new HashMap<Integer, String>();
                    map.put(t3,"t3");
                    map.put(t9,"t9");
                    map.put(black,"black");
                    map.put(red,"red");

                    Bundle bundle = new Bundle();
                    bundle.putString("max", checkMAX(t3,t9,black,red,map));
                    Log.i("SayMAX",checkMAX(t3,t9,black,red,map));
                    mReccommendFragment.setArguments(bundle);

                    mFragmentMgr.beginTransaction()
                            .replace(R.id.test_page, mReccommendFragment, "Main2recommend")
                            .addToBackStack(null)
                            .commit();
                }
                count+=1;

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==1){
                    t9+=1;
                    question.setImageResource(R.drawable.brace1);
                    qq.setText("吃飯時會選擇擁有天然好氣色還是強健身體的風格呢?");
                    a1.setText("天然好氣色");
                    a2.setText(" 強健身體");
                }
                if(count==2){
                    red+=1;
                    question.setImageResource(R.drawable.brace2);
                    qq.setText("常被中醫說脾胃不好還是常被西醫說容易感冒呢?");
                    a1.setText("脾胃不好");
                    a2.setText(" 容易感冒");
                }
                if(count==3){
                    red+=1;
                    question.setImageResource(R.drawable.brace3);
                    qq.setText("喜歡在飯裡加入雜糧或是糯米一起食用嗎?");
                    a1.setText("喜歡");
                    a2.setText(" 不喜歡");
                }
                if(count==4){
                    t3+=1;
                    t9+=1;
                    question.setImageResource(R.drawable.brace4);
                    qq.setText("喜歡純粹米香還是帶有芋頭香的米呢?");
                    a1.setText("純粹米香");
                    a2.setText(" 芋頭香");
                }
                if(count==5){
                    t3+=1;
                    Map<Integer, String> map = new HashMap<Integer, String>();
                    map.put(t3,"t3");
                    map.put(t9,"t9");
                    map.put(black,"black");
                    map.put(red,"red");

                    Bundle bundle = new Bundle();
                    bundle.putString("max", checkMAX(t3,t9,black,red,map));
                    mReccommendFragment.setArguments(bundle);

                    mFragmentMgr.beginTransaction()
                            .replace(R.id.test_page, mReccommendFragment, "Main2recommend")
                            .addToBackStack(null)
                            .commit();
                }
                count+=1;
            }
        });

    }


    public String checkMAX(int a,int b,int c,int d,Map map){
        int max1=0 ,max2=0,max=0;

        if(a>b)
            max1=a;
        else
            max1=b;
        if(c>d)
            max2=a;
        else
            max2=b;

        if(max1>max2)
            max=max1;
        else
            max=max2;

        Log.i("max",(String)map.get(max));
        return (String)map.get(max);
    }

    public void fnSetBackground(ImageView a,ImageView b){

        Bitmap rice_A = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.rice_a, a.getWidth(), a.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),rice_A);
        a.setBackground(bmpDrawImg);


        Bitmap rice_B = BitmapUtils.getFitSampleBitmap(getApplicationContext(),R.drawable.rice_b, b.getWidth(), b.getHeight());
        bmpDrawImg = new BitmapDrawable(getResources(),rice_B);
        b.setBackground(bmpDrawImg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 每個Drawable被加到VIEW上面都會產生一個callback，所以在recycle圖片之前，必須先把callback設成null
        // 設成null以後，背景圖片自然就會不見，就會變成黑的背景。bmpDrawImg的狀態就會是沒有被使用中。

        a.getBackground().setCallback(null);
        b.getBackground().setCallback(null);

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