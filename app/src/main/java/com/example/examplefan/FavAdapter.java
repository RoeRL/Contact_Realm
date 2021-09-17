package com.example.examplefan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.security.auth.callback.Callback;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.DataViewHolder> {
    private List<Model> dataList;
    Context context;
    Realm realm;
    RealmHelper realmHelper;

    public FavAdapter(List<Model> models, Context context) {
        this.dataList = models;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_main, parent, false);
        return new FavAdapter.DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.DataViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.firstName.setText(dataList.get(position).getFirstName());
        holder.lastName.setText(dataList.get(position).getLastName());
        holder.email.setText(dataList.get(position).getEmail());
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("id", dataList.get(position).getId());
            intent.putExtra("first_name", dataList.get(position).getFirstName());
            intent.putExtra("last_name", dataList.get(position).getLastName());
            intent.putExtra("email", dataList.get(position).getEmail());
            context.startActivities(new Intent[]{intent});
        });
        holder.position = position;
    }

    @Override
    public int getItemCount() { return dataList.size(); }


    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        TextView firstName, lastName, email;
        CardView cardView;
        int position;


        public DataViewHolder(@NonNull View dataView) {
            super(dataView);
            firstName = dataView.findViewById(R.id.tFirstName);
            lastName = dataView.findViewById(R.id.tLastName);
            email = dataView.findViewById(R.id.tEmail);
            cardView = dataView.findViewById(R.id.ContactCardView);

            cardView.setOnCreateContextMenuListener(this);

            RealmConfiguration configuration = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
            realm = Realm.getInstance(configuration);
            realmHelper = new RealmHelper(realm);

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.option1:
                    Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.option2:
                    Log.d("data hapus ", "onMenuItemClick: "+position);
                    realmHelper.delete(dataList.get(position).getId());
                    Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();


            }
            return false;
        }


        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(R.menu.menu, R.id.option1, 1, "Edit").setOnMenuItemClickListener(this);
            contextMenu.add(R.menu.menu, R.id.option2, 2, "Delete").setOnMenuItemClickListener(this);
        }
    }


}
