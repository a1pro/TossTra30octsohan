package com.app.tosstraApp.ViewPagerMap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.app.tosstraApp.R;
import com.app.tosstraApp.activities.AppUtil;
import com.app.tosstraApp.activities.JobDetailForDriver;
import com.app.tosstraApp.models.AllDrivers;
import com.app.tosstraApp.models.AllJobsToDriver;
import com.app.tosstraApp.services.Interface;
import com.app.tosstraApp.utils.CommonUtils;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPagerMapFragment extends Fragment implements View.OnClickListener {

    private TextView tv_amount, tv_company_name;
    AllDrivers data;
    int position;
    private CircleImageView iv_user;
    private RelativeLayout llTop;


    public ViewPagerMapFragment(int position, AllDrivers data) {
        this.data = data;
        this.position = position;
    }


    public static Fragment newInstance(int position, AllDrivers data) {
        ViewPagerMapFragment viewPagerFragment = new ViewPagerMapFragment(position, data);
        return viewPagerFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_pager_map_frag1, container, false);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        tv_amount = view.findViewById(R.id.tv_amount);
        tv_company_name = view.findViewById(R.id.tv_company_name);
        iv_user = view.findViewById(R.id.iv_user);

        tv_amount.setText(data.getData().get(position).getFirstName());
        tv_company_name.setText("Company Name - " + data.getData().get(position).getCompanyName());
        llTop = view.findViewById(R.id.llTop);
        llTop.setOnClickListener(this);


        Glide.with(getActivity())
                .load("http://tosstra.tosstra.com/assets/usersImg/" + data.getData().get(position).getProfileImg())
                .centerCrop()
                .placeholder(R.mipmap.image)
                .into(iv_user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llTop:
                Intent i = new Intent(getContext(), JobDetailForDriver.class);
                i.putExtra("job_id",data.getData().get(position).getJobId());
                i.putExtra("disp_id",data.getData().get(position).getDispatcherId());
                i.putExtra("dri_id",data.getData().get(position).getDriverId());
                i.putExtra("job_offer", data);
                i.putExtra("job_offer_pos", position);
                i.putExtra("map_vp_detail","2");
                startActivity(i);
                break;

        }
    }

    private void job_detail() {
        final Dialog dialog = AppUtil.showProgress(getActivity());
        Interface service = CommonUtils.retroInit();
        Call<AllJobsToDriver> call = service.single_job_detail(data.getData().get(position).getJobId(),
                data.getData().get(position).getDispatcherId(), data.getData().get(position).getDriverId());
        call.enqueue(new Callback<AllJobsToDriver>() {
            @Override
            public void onResponse(Call<AllJobsToDriver> call, Response<AllJobsToDriver> response) {
                AllJobsToDriver data = response.body();
                assert data != null;
                if (data.getCode().equalsIgnoreCase("201")) {
                    dialog.dismiss();
                    CommonUtils.showLongToast(getActivity(), data.getMessage());
                }
                else {
                    dialog.dismiss();
                    CommonUtils.showLongToast(getActivity(), data.getMessage());
                }
            }

            @Override
            public void onFailure(Call<AllJobsToDriver> call, Throwable t) {
                dialog.dismiss();
                CommonUtils.showSmallToast(getActivity(), t.getMessage());
            }
        });
    }
}




