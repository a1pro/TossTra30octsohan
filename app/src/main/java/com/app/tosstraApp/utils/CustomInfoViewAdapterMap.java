package com.app.tosstraApp.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.tosstraApp.R;
import com.app.tosstraApp.models.AllDrivers;
import com.app.tosstraApp.models.MarkerDetails;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomInfoViewAdapterMap implements GoogleMap.InfoWindowAdapter {
    LayoutInflater mInflater;
    private Context context;
    AllDrivers.Data data;



    public CustomInfoViewAdapterMap(LayoutInflater mInflater, Context context, AllDrivers.Data data) {
        this.mInflater = mInflater;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        final View popup = mInflater.inflate(R.layout.info_window_layout, null);
        try {
            TextView tv_company_name = popup.findViewById(R.id.tv_company_name);
            tv_company_name.setText(marker.getTitle());
            ((TextView) popup.findViewById(R.id.title)).setText(marker.getTitle());
            CircleImageView imageUser=popup.findViewById(R.id.iv_dis_img);
            MarkerDetails markerDetails=(MarkerDetails)marker.getTag();
//            imageUser.setImageResource(markerDetails.getImageView());


            Picasso.with(context).load("http://tosstra.tosstra.com/assets/usersImg/profileImg36euH.jpg").into(imageUser,new InfoWindowRefresher(marker));


            Log.e("imggg",data.getCompanyName());
           /* Glide
                    .with(context)
                    .load(markerDetails.getImageView())
                    .centerCrop()
                    .placeholder(R.mipmap.image)
                    .into(imageUser);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        return popup;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    public class InfoWindowRefresher implements Callback {
        private Marker markerToRefresh;

        public InfoWindowRefresher(Marker markerToRefresh) {
            this.markerToRefresh = markerToRefresh;
        }

        @Override
        public void onSuccess() {
            markerToRefresh.showInfoWindow();
        }

        @Override
        public void onError() {}
    }
}