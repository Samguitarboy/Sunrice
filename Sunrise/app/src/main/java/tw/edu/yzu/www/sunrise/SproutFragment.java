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

public class SproutFragment extends Fragment {
    private ImageView photoBG;
    private BitmapDrawable bmpDrawImg  = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView =inflater.inflate(R.layout.fragment_sprout, container, false);

        photoBG = (ImageView) rootView.findViewById(R.id.photoBG);

        //避免OOM 設定背景
        Bitmap bitmap = BitmapUtils.getFitSampleBitmap(getActivity().getApplicationContext(),R.drawable.introduction, photoBG.getWidth(), photoBG.getHeight());
        Drawable d = new BitmapDrawable(getResources(),bitmap);
        photoBG.setBackground(d);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 每個Drawable被加到VIEW上面都會產生一個callback，所以在recycle圖片之前，必須先把callback設成null
        // 設成null以後，背景圖片自然就會不見，就會變成黑的背景。bmpDrawImg的狀態就會是沒有被使用中。
        photoBG.getBackground().setCallback(null);

        // 先判斷bmpDrawImg 是否為null，如果不是null，且bmpDrawImg 還沒有被recycle的話就進行recycle
        if (null != bmpDrawImg && !bmpDrawImg.getBitmap().isRecycled()){
            bmpDrawImg.getBitmap().recycle();
        }
        System.gc();
    }
}
