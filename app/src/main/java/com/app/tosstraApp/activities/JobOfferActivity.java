package com.app.tosstraApp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.tosstraApp.R;
import com.app.tosstraApp.models.AllDriverNew;
import com.app.tosstraApp.models.GenricModel;
import com.app.tosstraApp.services.Interface;
import com.app.tosstraApp.utils.CommonUtils;
import com.app.tosstraApp.utils.PreferenceHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobOfferActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv_back_offer;

    TextView tvCompanyName, tvPickAddval, tvCity, tvState, tvZip, tvDropAddval, tvCityDrop,
            tvStateDrop, tvZipDrop, tvDateRange, tvToRange, tvStartTime, tvEndTime,tv_rate,tvCompanyNameVal,
            tvLocVal1,tvLocation;
    EditText et_add_info;
    Button btn_reject, btn_accept;
    String status_accept;
    int pos=0;
    String job_id, disp_id, dri_id, marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer);

        status_accept=getIntent().getStringExtra("status_accept");
        job_id = getIntent().getStringExtra("job_id");
        disp_id = getIntent().getStringExtra("disp_id");
        dri_id = getIntent().getStringExtra("dri_id");
        initUI();

        if(status_accept.equalsIgnoreCase("1")){
            btn_accept.setVisibility(View.GONE);
            btn_reject.setVisibility(View.GONE);
            tvLocation.setVisibility(View.VISIBLE);
            tvLocVal1.setVisibility(View.VISIBLE);

        }else {
            btn_accept.setVisibility(View.VISIBLE);
            btn_reject.setVisibility(View.VISIBLE);
            tvLocation.setVisibility(View.GONE);
            tvLocVal1.setVisibility(View.GONE);
        }
        Getinfluence();
    }


    private void initUI() {
        tvLocation=findViewById(R.id.tvLocation);
        tvLocVal1=findViewById(R.id.tvLocVal1);
        tvCompanyNameVal=findViewById(R.id.tvCompanyNameVal);
        et_add_info = findViewById(R.id.et_add_info);
        iv_back_offer = findViewById(R.id.iv_back_offer);
        tv_rate = findViewById(R.id.tvRate);
        tvPickAddval = findViewById(R.id.tvPickAddval);
        tvCity = findViewById(R.id.tvCity);
        tvState = findViewById(R.id.tvState);
        tvZip = findViewById(R.id.tvZip);
        tvDropAddval = findViewById(R.id.tvDropAddval);
        tvCityDrop = findViewById(R.id.tvCityDrop);
        tvStateDrop = findViewById(R.id.tvStateDrop);
        tvZipDrop = findViewById(R.id.tvZipDrop);
        tvDateRange = findViewById(R.id.tvDateRange);
        tvToRange = findViewById(R.id.tvToRange);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvEndTime = findViewById(R.id.tvEndTime);
        btn_reject = findViewById(R.id.btn_reject);
        btn_accept = findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(this);
        btn_reject.setOnClickListener(this);
        iv_back_offer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_offer:
                finish();
                break;
            case R.id.btn_accept:
                hitAcceptReject("1");
                break;
            case R.id.btn_reject:
                hitAcceptReject("0");
                break;
        }
    }

    private void hitAcceptReject(String status) {
        final Dialog dialog = AppUtil.showProgress(JobOfferActivity.this);
        Interface service = CommonUtils.retroInit();
        Call<GenricModel> call = service.acceptReject(PreferenceHandler.readString(JobOfferActivity.this,
                PreferenceHandler.USER_ID,""), job_id,
                status, disp_id);
        call.enqueue(new Callback<GenricModel>() {
            @Override
            public void onResponse(Call<GenricModel> call, Response<GenricModel> response) {
                GenricModel data = response.body();
                if (data!=null) {
                    if (data.getCode().equalsIgnoreCase("201")) {
                        dialog.dismiss();
                        Intent i = new Intent(JobOfferActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        CommonUtils.showLongToast(JobOfferActivity.this, data.getMessage());

                    } else {
                        dialog.dismiss();
                        CommonUtils.showLongToast(JobOfferActivity.this, data.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenricModel> call, Throwable t) {
                dialog.dismiss();
                CommonUtils.showSmallToast(JobOfferActivity.this, t.getMessage());
            }
        });
    }

    private void Getinfluence() {
        final Dialog dialog = AppUtil.showProgress(JobOfferActivity.this);
        try {
            //  progress.setVisibility(View.VISIBLE);
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit2 = new Retrofit.Builder()

                    .baseUrl("http://tosstra.tosstra.com/api/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            final Interface apiListener = retrofit2.create(Interface.class);
            Call<AllDriverNew> call = apiListener.single_job_detail1(job_id, disp_id, dri_id);
            call.enqueue(new Callback<AllDriverNew>() {
                @Override
                public void onResponse(Call<AllDriverNew> call, Response<AllDriverNew> response) {
                    AllDriverNew allJobsToDriver = response.body();
                    assert allJobsToDriver != null;
                    if (allJobsToDriver.getCode().equalsIgnoreCase("201")) {
                       dialog.dismiss();
                        tv_rate.setText("$ "+allJobsToDriver.getData().get(pos).getRate()+" "+allJobsToDriver.getData().get(pos).getRateType());
                        tvPickAddval.setText(allJobsToDriver.getData().get(pos).getPupStreet());
                        tvCity.setText(allJobsToDriver.getData().get(pos).getPupCity());
                        tvState.setText(allJobsToDriver.getData().get(pos).getPupState());
                        tvZip.setText(allJobsToDriver.getData().get(pos).getPupZipcode());
                        tvDropAddval.setText(allJobsToDriver.getData().get(pos).getDrpStreet());
                        tvCompanyNameVal.setText(allJobsToDriver.getData().get(pos).getCompanyName());
                        tvCityDrop.setText(allJobsToDriver.getData().get(pos).getDrpCity());
                        tvStateDrop.setText(allJobsToDriver.getData().get(pos).getDrpState());
                        tvZipDrop.setText(allJobsToDriver.getData().get(pos).getDrpZipcode());
                        tvDateRange.setText("Date From " + allJobsToDriver.getData().get(pos).getDateFrom());
                        tvToRange.setText("To " + allJobsToDriver.getData().get(pos).getDateTo());
                        tvStartTime.setText("Start Time " + allJobsToDriver.getData().get(pos).getStartTime());
                        tvEndTime.setText("End Time " + allJobsToDriver.getData().get(pos).getEndTime());
                        et_add_info.setText(allJobsToDriver.getData().get(pos).getAdditinalInstructions());
                        tvLocVal1.setText(allJobsToDriver.getData().get(pos).getDriveraddress());
                    } else {
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<AllDriverNew> call, Throwable t) {
                    Toast.makeText(JobOfferActivity.this, "Failed to load profile", Toast.LENGTH_SHORT).show();
                  dialog.dismiss();
                    //      progress.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("exception", e.getMessage());
        }
    }
}