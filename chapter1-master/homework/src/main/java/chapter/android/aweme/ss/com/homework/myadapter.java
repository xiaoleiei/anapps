package chapter.android.aweme.ss.com.homework;

import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import chapter.android.aweme.ss.com.homework.R;
import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder> {

    List<Message> mData;
    private int mNumberItems;
    private int Height;

    private static int viewHolderCount;
    private static final String TAG = "wangyi";
    public myadapter( List<Message> data,int numListItems,int height) {
        mNumberItems = numListItems;
       Height=height;
        viewHolderCount = 0;
        mData=data;
        Log.d(TAG, "this1111 " );
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        view.measure(0,0);
        view.getLayoutParams().height = view.getMeasuredHeight()/9*8;


        MyViewHolder viewHolder = new MyViewHolder(view);
        //int backgroundColorForViewHolder = ColorUtils
               // .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
       // viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);
        Log.d(TAG, "1111: "+ view.getMeasuredHeight()+"  "+Height);
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
               Message message=mData.get(position);
               myViewHolder.updateUI(message);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private  final  TextView tv_title;
        private  final  TextView tv_time;
        private  final  ImageView iv_avatar;
        private  final  TextView tv_item_number;
        private final TextView listItemNumberView;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tv_title=(TextView)itemView.findViewById(R.id.tv_title);
            tv_time=(TextView)itemView.findViewById(R.id.tv_time);
            iv_avatar=(CircleImageView) itemView.findViewById(R.id.iv_avatar);
            tv_item_number=(TextView)itemView.findViewById(R.id.tv_item_number);

            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_description);
        }
     public void updateUI(Message message)
     {
         tv_title.setText(message.getTitle());
         listItemNumberView.setText(message.getDescription());
         tv_time.setText(message.getTime());
         switch (message.getIcon()) {
             case "TYPE_ROBOT":
                 iv_avatar.setImageResource(R.drawable.session_robot);
                 break;
             case "TYPE_GAME":
                 iv_avatar.setImageResource(R.drawable.icon_micro_game_comment);
                 break;
             case "TYPE_SYSTEM":
                 iv_avatar.setImageResource(R.drawable.session_system_notice);
                 break;
             case "TYPE_STRANGER":
                 iv_avatar.setImageResource(R.drawable.session_stranger);
                 break;
         }
     }
    }


    public class NumberViewHolder extends RecyclerView.ViewHolder  {

        private final TextView viewHolderIndex;
        private final TextView listItemNumberView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
        }

        public void bind(int position) {
            listItemNumberView.setText(String.valueOf(position));


//            viewHolderIndex.setText(String.format("ViewHolder index: %s", getAdapterPosition()));
//            int backgroundColorForViewHolder = ColorUtils.
//                    getViewHolderBackgroundColorFromInstance(itemView.getContext(), getAdapterPosition() % 10);
//            itemView.setBackgroundColor(backgroundColorForViewHolder);
        }



    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
