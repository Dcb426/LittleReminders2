package cs316.project_1.littlereminders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cs316.project_1.littlereminders.Database.DataStudent;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context mContext;
    private List<DataStudent> notificationList;

    NoteAdapter(Context mContext, List<DataStudent> notificationList)
    {
        this.mContext = mContext;
        this.notificationList = notificationList;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.alarms_layout, null);
        return new NoteViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        //getting the product of the specified position
        DataStudent product = notificationList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getstudentTitle());
        holder.textViewShortDesc.setText(product.getstudentText());

    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc;

        NoteViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
        }
    }
}
