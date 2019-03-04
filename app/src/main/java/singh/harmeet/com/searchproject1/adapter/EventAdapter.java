package singh.harmeet.com.searchproject1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import singh.harmeet.com.searchproject.R;
import singh.harmeet.com.searchproject1.data.model.Event;
import singh.harmeet.com.searchproject1.data.model.Performer;
import singh.harmeet.com.searchproject1.util.AppUtil;
import singh.harmeet.com.searchproject1.util.CircleTransform;
import singh.harmeet.com.searchproject1.util.ItemClickListener;

/**
 * Created by harmeet.assi on 4/11/2018.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {
    private List<Event> eventList=new ArrayList<>();
    private Context context;
    private Picasso picasso;
    private ItemClickListener itemClickListener;

    @Inject
    public EventAdapter(Context context,Picasso picasso){
        this.context = context;
        this.picasso = picasso;

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
    @Override
    public EventAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new EventHolder(item_view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventHolder holder, int position) {
        Event event = eventList.get(position);
        holder.title.setText(event.title());
        holder.location.setText(event.venue().display_location());
        holder.date_time.setText(AppUtil.convertDate(event.datetime_local()));
        setImageView(event,holder);
    }

    //handle image selection
    private void setImageView(Event event,EventHolder holder) {
        if(event.performers().size()>0){
            for(Performer performer:event.performers())
                if(performer.images().huge()!=null){
                    picasso.load(performer.images().huge()).transform(new CircleTransform()).into(holder.image_venue);
                    break;
                }else{
                    picasso.load(R.drawable.broken_place).transform(new CircleTransform()).into(holder.image_venue);
                }
        }
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setList(List<Event> list) {
        this.eventList = list;
    }

    class EventHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_venue)
        ImageView image_venue;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.location)
        TextView location;
        @BindView(R.id.date_time)
        TextView date_time;
        private EventHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(eventList.get(getAdapterPosition()),getAdapterPosition());
                    }
                }
            });
        }
    }
}
