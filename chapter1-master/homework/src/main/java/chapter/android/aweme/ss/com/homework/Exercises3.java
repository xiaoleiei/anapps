package chapter.android.aweme.ss.com.homework;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;

import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;

/**
 * 大作业:实现一个抖音消息页面,所需资源已放在res下面
 */
public class Exercises3 extends AppCompatActivity {


    private static final String TAG = "wangyi";
    private static final int NUM_LIST_ITEMS = 100;

    private myadapter mAdapter;
    private RecyclerView mNumbersListView;

    private Toast mToast;
    List<Message> messages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "this0 " );
        setContentView(R.layout.activity_chatroom);
        Log.d(TAG, "this0 " );
        try{

            InputStream assetInput=getAssets().open("data.xml");
            messages=PullParser.pull2xml(assetInput);
        }catch(Exception exception){
            exception.printStackTrace();
        }

        Log.d(TAG, "this0 " +messages.toString());
        mNumbersListView = findViewById(R.id.rv_numbers);
        Log.d(TAG, "this1 " );
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNumbersListView.setLayoutManager(layoutManager);
        Log.d(TAG, "this2 " );
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
//        mNumbersListView.setHasFixedSize(true);

        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */

        mAdapter = new myadapter(messages,NUM_LIST_ITEMS,mNumbersListView.getHeight());
        Log.d(TAG, "this3 " );
        mNumbersListView.setAdapter(mAdapter);
        Log.d(TAG, "this4 " );
    }

    public void onListItemClick(int clickedItemIndex) {
        Log.d(TAG, "onListItemClick: ");
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }

}
