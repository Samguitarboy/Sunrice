package tw.edu.yzu.www.sunrise;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class RiceListPage extends Activity {

    public FragmentManager mFragmentMgr;
    public DetailFragment mDetailFragment;
    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lData = null;
    private Context mContext;
    private ExpandableListView expandlist;
    private MyBaseExpandableListAdapter myAdapter = null;
    private ImageView logo,shop,fb,ig,medium,youtube;
    private BitmapDrawable bmpDrawImg  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricelist_page);

        mContext = RiceListPage.this;
        expandlist = findViewById(R.id.rice_classify);

        logo = findViewById(R.id.logo);
        fnSetBackground(logo,0);
        shop = findViewById(R.id.shop);
        fb = findViewById(R.id.fb);
        ig = findViewById(R.id.ig);
        medium = findViewById(R.id.medium);
        youtube = findViewById(R.id.youtube);

        clicklistener(shop,fb,ig,medium,youtube);
        expand();
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

    public void clicklistener(ImageView a,ImageView b,ImageView c,ImageView d,ImageView e){

        mFragmentMgr = getFragmentManager();
        mDetailFragment = new DetailFragment();

        fnSetBackground(a,1);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SellPage.class);
                startActivity(intent);
                finish();
            }
        });

        fnSetBackground(b,2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/sunrice1923"));
                startActivity(intent);
            }
        });

        fnSetBackground(c,3);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://instagram.com/sunrice1923"));
                startActivity(intent);
            }
        });

        fnSetBackground(d,4);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://medium.com/sunrice1923"));
                startActivity(intent);
            }
        });

        fnSetBackground(e,5);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://m.youtube.com/channel/UChnYKKF8nxC0qYIOL2Q9gWw"));
                startActivity(intent);
            }
        });
    }

    public void expand(){

        //資料準備
        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Item>>();
        gData.add(new Group(R.drawable.a));
        gData.add(new Group(R.drawable.b));
        gData.add(new Group(R.drawable.c));
        gData.add(new Group(R.drawable.d));
        gData.add(new Group(R.drawable.e));
        gData.add(new Group(R.drawable.f));
        gData.add(new Group(R.drawable.g));

        //香米
        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.t3,"桃園3號"));
        iData.add(lData);

        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.tai9,"台梗9號"));
        iData.add(lData);

        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.black,"台灣原生黑米"));
        iData.add(lData);

        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.red,"台灣紅藜"));
        iData.add(lData);

        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.sand,"清香號沙茶醬"));
        lData.add(new Item(R.drawable.soy,"源興甲等壺底油(膏)"));
        lData.add(new Item(R.drawable.tofu,"禧福手工釀豆腐乳"));
        iData.add(lData);

        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.box,"沙茶豬肉燴飯"));
        iData.add(lData);

        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.brain,"小測驗"));
        iData.add(lData);

        myAdapter = new MyBaseExpandableListAdapter(gData,iData,mContext);
        expandlist.setAdapter(myAdapter);
        expandlist.setGroupIndicator(null);


        expandlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.i("aaaa",groupPosition+"      "+childPosition+"     "+id);
                if(groupPosition==5) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), Funton.class);
                    startActivity(intent);
                    finish();
                }
                else if(groupPosition==6) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), TestPage.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    String place;
                    switch(groupPosition){
                        case 0:place="1"; break;
                        case 1:place="2"; break;
                        case 2:place="3"; break;
                        case 3:place="4"; break;
                        case 4:
                            if(childPosition==0)
                                place="5";
                            else if (childPosition==1)
                                place="6";
                            else
                                place="7";
                            break;
                        default:place = "888";break;
                    }
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), IntrodetailPage.class);
                    Log.i("aaaa",place);
                    Bundle bundle = new Bundle();
                    bundle.putString("where", place);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });
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
        intent.setClass(getApplicationContext(), MainPage.class);
        startActivity(intent);
        finish();

    }
}
