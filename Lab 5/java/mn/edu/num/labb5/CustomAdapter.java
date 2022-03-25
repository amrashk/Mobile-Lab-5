package mn.edu.num.labb5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    private ArrayList word_id, eng_word, mon_word;


    CustomAdapter(Activity activity, Context context, ArrayList word_id, ArrayList eng_word,
                  ArrayList mon_word){
        this.activity = activity;
        this.context = context;
        this.word_id = word_id;
        this.eng_word = eng_word;
        this.mon_word = mon_word;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.word_id_txt.setText(String.valueOf(word_id.get(position)));
        holder.eng_word_txt.setText(String.valueOf(eng_word.get(position)));
        holder.mon_word_txt.setText(String.valueOf(mon_word.get(position)));
        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(word_id.get(position)));
                intent.putExtra("eword", String.valueOf(eng_word.get(position)));
                intent.putExtra("mword", String.valueOf(mon_word.get(position)));
                activity.startActivityForResult(intent, 1);
                return false;
            }

        });
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(word_id.get(position)));
                intent.putExtra("eword", String.valueOf(eng_word.get(position)));
                intent.putExtra("mword", String.valueOf(mon_word.get(position)));
                activity.startActivityForResult(intent, 1);
            }

        });
    }

    @Override
    public int getItemCount() {
        return word_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView word_id_txt, eng_word_txt, mon_word_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word_id_txt = itemView.findViewById(R.id.word_id_txt);
            eng_word_txt = itemView.findViewById(R.id.eng_word_txt);
            mon_word_txt = itemView.findViewById(R.id.mon_word_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
