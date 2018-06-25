package tw.edu.yzu.www.sunrise;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;


public class IntroductionPage extends Activity {

    public FragmentManager mFragmentMgr;
    public DetailFragment mDetailFragment;
    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lData = null;
    private Context mContext;
    private ExpandableListView expandlist;
    private MyBaseExpandableListAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction_page);

        mContext = IntroductionPage.this;
        expandlist = findViewById(R.id.rice_classify);

        mFragmentMgr = getFragmentManager();
        mDetailFragment = new DetailFragment();

        //資料準備
        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<Item>>();
        gData.add(new Group("香米"));
        gData.add(new Group("非香米"));
        gData.add(new Group("有色米"));

        //香米
        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.tauyuon3,"桃園3號"));
        lData.add(new Item(R.drawable.tauyuon3,"台中194"));
        lData.add(new Item(R.drawable.tauyuon3,"台農秈22"));
        iData.add(lData);

        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.tauyuon3,"桃園3號"));
        lData.add(new Item(R.drawable.tauyuon3,"台中194"));
        lData.add(new Item(R.drawable.tauyuon3,"台農秈22"));
        iData.add(lData);

        lData = new ArrayList<Item>();
        lData.add(new Item(R.drawable.tauyuon3,"桃園3號"));
        lData.add(new Item(R.drawable.tauyuon3,"台中194"));
        lData.add(new Item(R.drawable.tauyuon3,"台農秈22"));
        iData.add(lData);

        myAdapter = new MyBaseExpandableListAdapter(gData,iData,mContext);
        expandlist.setAdapter(myAdapter);


        expandlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mFragmentMgr.beginTransaction()
                        .replace(R.id.layout_introduction, mDetailFragment, "Introduction2Detail")
                        .addToBackStack(null)
                        .commit();
                return true;
            }
        });
    }
}
