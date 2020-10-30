package com.app.tosstraApp.adapters;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.app.tosstraApp.R;
import com.app.tosstraApp.interfaces.PassDriverIds;
import com.app.tosstraApp.interfaces.RefreshDriverList;
import com.app.tosstraApp.models.AllDrivers;

import java.util.ArrayList;
import java.util.List;

public class SeniorityAdapter extends RecyclerView.Adapter<SeniorityAdapter.SeniorityAdapterHolder> {
    Activity context;
    AllDrivers data;
    String driver_id;
    RefreshDriverList refreshDriverList;
    PassDriverIds passDriverIds;
    public static List<String> interestList_seniority=new ArrayList<>();

    public SeniorityAdapter(Activity context, AllDrivers data,RefreshDriverList refreshDriverList,PassDriverIds passDriverIds) {
        this.context = context;
        this.data = data;
        this.refreshDriverList=refreshDriverList;
        this.passDriverIds=passDriverIds;
    }

    @NonNull
    @Override
    public SeniorityAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seniority_item, parent, false);
        return new SeniorityAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeniorityAdapterHolder holder, int position) {

        holder.tv_header1.setText(data.getData().get(position).getCompanyName());
        holder.tv_header2.setText(data.getData().get(position).getFirstName() + " " + data.getData().get(position).getLastName());

    }

    @Override
    public int getItemCount() {
        return data.getData().size();
    }

    public class SeniorityAdapterHolder extends RecyclerView.ViewHolder {
        private TextView tv_header1, tv_header2;
        private ImageView ivFav;

        public SeniorityAdapterHolder(@NonNull View v) {
            super(v);
            tv_header1 = v.findViewById(R.id.tv_header1);
            tv_header2 = v.findViewById(R.id.tv_header2);
            ivFav = v.findViewById(R.id.ivFav);
            CheckBox chkBkHrt = v.findViewById(R.id.chkBkHrt);
            ivFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    driver_id = data.getData().get(getAdapterPosition()).getId();
                    refreshDriverList.favClick(driver_id);
                   // hitFavUnFav(driver_id);
                }
            });

            chkBkHrt.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    driver_id = data.getData().get(getAdapterPosition()).getId();
                    boolean checked = ((CheckBox) v).isChecked();
                    if (checked) {
                        interestList_seniority.add(driver_id+",");
                        passDriverIds.selectedDriverIdList(interestList_seniority);
                    } else {
                        interestList_seniority.remove(driver_id+",");
                        passDriverIds.selectedDriverIdList(interestList_seniority);
                    }
                }
            });
        }




      /*  @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ivFav:
                    driver_id = data.getData().get(getAdapterPosition()).getId();
                    hitFavUnFav();
                    break;
            }
        }*/
    }

}
