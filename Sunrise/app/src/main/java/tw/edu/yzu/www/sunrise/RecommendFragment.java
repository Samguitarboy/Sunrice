package tw.edu.yzu.www.sunrise;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RecommendFragment extends Fragment {
    private ImageView photo,moreicon;
    private TextView fit,describe,more;
    private RelativeLayout background;
    private BitmapDrawable bmpDrawImg  = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView =inflater.inflate(R.layout.fragment_recommend, container, false);

        moreicon =(ImageView) rootView.findViewById(R.id.moreicon);
        background =(RelativeLayout) rootView.findViewById(R.id.recommend_background);
        photo =(ImageView)  rootView.findViewById(R.id.photo);
        fit =(TextView)  rootView.findViewById(R.id.fit);
        describe =(TextView) rootView.findViewById(R.id.describe);
        more =(TextView) rootView.findViewById(R.id.more);

        Bundle bundle = getArguments();
        String data = bundle.getString("max");

        //避免OOM 設定背景
        Bitmap bitmap = BitmapUtils.getFitSampleBitmap(getActivity().getApplicationContext(),R.drawable.ricebutton, moreicon.getWidth(), moreicon.getHeight());
        Drawable d = new BitmapDrawable(getResources(),bitmap);
        moreicon.setBackground(d);
        Bitmap bitmap1 = BitmapUtils.getFitSampleBitmap(getActivity().getApplicationContext(),R.drawable.brainbackground, background.getWidth(), background.getHeight());
        Drawable d1 = new BitmapDrawable(getResources(),bitmap1);
        background.setBackground(d1);

        if("t3".compareTo(data)==0){
            photo.setImageResource(R.drawable.t3plus);
            fit.setText("看來你很適合桃園3號噢!");
            describe.setText("Q彈有水分的口感"+
                    "拿來做家常炊飯，" +
                    "細細咀嚼還可以吃到" +
                    "隱藏在米飯中的神祕芋頭香。");
            more.setText("按這裡發現更多的\n" +
                    "桃園3號");
        }else if("t9".compareTo(data)==0){
            photo.setImageResource(R.drawable.tai9plus);
            fit.setText("看來你情有獨鍾台梗9號噢!");
            describe.setText("紅遍全台的台梗9號"+
                    "口感粒粒分明" +
                    "最適合做壽司及炒飯，" +
                    "每一口咀嚼都可以嚐到"+
            "最天然的米飯清香。");
            more.setText("按這裡發現更多的\n" +
                    "台梗9號");
        }
        else if("black".compareTo(data)==0){
            photo.setImageResource(R.drawable.blackplus);
            fit.setText("快來嚐嚐台灣原生黑米!");
            describe.setText("富含花青素的原生黑米"+
                    "帶給你天然的好氣色，" +
                    "糯性較低的它" +
                    "輾除不好入口的黑色種皮後，"+
                    "不需浸泡也可煮出口感極佳的黑米飯。"+
                    "夏天時，能調節你虛弱的脾胃。");
            more.setText("按這裡發現更多的\n" +
                    "台灣原生黑米");
        }
        else if("red".compareTo(data)==0){
            photo.setImageResource(R.drawable.redplus);
            fit.setText("你一定沒吃過的台灣紅藜!");
            describe.setText("隱藏在台灣原住民部落的紅寶石，"+
                    "富含8種人體無法自主形成的氨基酸，" +
                    "更有強化免疫、增強代謝的功力。" );
            more.setText("按這裡發現更多的\n" +
                    "台灣紅藜");
        }

        return rootView;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        // 每個Drawable被加到VIEW上面都會產生一個callback，所以在recycle圖片之前，必須先把callback設成null
        // 設成null以後，背景圖片自然就會不見，就會變成黑的背景。bmpDrawImg的狀態就會是沒有被使用中。
        moreicon.getBackground().setCallback(null);
        background.getBackground().setCallback(null);

        // 先判斷bmpDrawImg 是否為null，如果不是null，且bmpDrawImg 還沒有被recycle的話就進行recycle
        if (null != bmpDrawImg && !bmpDrawImg.getBitmap().isRecycled()){
            bmpDrawImg.getBitmap().recycle();
        }
        System.gc();
    }
}



