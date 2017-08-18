package fr.pictycompany.picty;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;

import static fr.pictycompany.picty.R.id.onePhoto;
import static fr.pictycompany.picty.R.layout.photos;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<String> mItemList = new ArrayList<>();


    public ImageAdapter(Context context, ArrayList<String> itemList) {
        mContext = context;
        mItemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(photos, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String img = mItemList.get(position);
        ImageView imageView = holder.imageView;

        GlideApp
                .with(mContext)
                .load(img)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);
            imageView = (ImageView) itemView.findViewById(onePhoto);
        }
    }

}
