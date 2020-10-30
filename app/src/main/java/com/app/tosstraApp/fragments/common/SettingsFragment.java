package com.app.tosstraApp.fragments.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.tosstraApp.R;
import com.app.tosstraApp.activities.AppUtil;
import com.app.tosstraApp.activities.ChangePasswordActivity;
import com.app.tosstraApp.activities.ChooseSignupActivity;
import com.app.tosstraApp.activities.SigninActivity;
import com.app.tosstraApp.activities.WebViewActivity;
import com.app.tosstraApp.models.GenricModel;
import com.app.tosstraApp.services.Interface;
import com.app.tosstraApp.utils.CommonUtils;
import com.app.tosstraApp.utils.PreferenceHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.tosstraApp.activities.MainActivity.online_stauis;
import static com.app.tosstraApp.fragments.driver.AllJobsFragment.currentLatitude;
import static com.app.tosstraApp.fragments.driver.AllJobsFragment.currentLongitude;


public class SettingsFragment extends Fragment implements View.OnClickListener {
    private TextView tv_logout, tv_delete_ac, tv_change_pass, tvTerm, tvPrivacy,tvHelp,
    tvContactUs;
    private String userID;
    private Switch switch1;
    private RelativeLayout rl_online_staus;
    private String status;
    Intent i;
    Handler handler = new Handler();
    Runnable runnable;
    int delay = 10000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        initUI(view);
        if (PreferenceHandler.readString(getContext(),"userType","").equalsIgnoreCase("driver")) {
            rl_online_staus.setVisibility(View.GONE);
        } else {
            rl_online_staus.setVisibility(View.VISIBLE);
        }



        return view;
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                Toast.makeText(getContext(), "This method is run every 10 seconds",
                        Toast.LENGTH_SHORT).show();
            }
        }, delay);
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
    }

    private void initUI(View view) {
        userID = PreferenceHandler.readString(getContext(), PreferenceHandler.USER_ID, "");
        tv_logout = view.findViewById(R.id.tv_logout);
        tv_delete_ac = view.findViewById(R.id.tv_delete_ac);
        tv_change_pass = view.findViewById(R.id.tv_change_pass);
        tvTerm = view.findViewById(R.id.tvTerm);
        tvTerm.setOnClickListener(this);
        tvPrivacy = view.findViewById(R.id.tvPrivacy);
        tvHelp=view.findViewById(R.id.tvHelp);
        tvContactUs=view.findViewById(R.id.tvContactUs);
        tvContactUs.setOnClickListener(this);
        tvHelp.setOnClickListener(this);
        tvPrivacy.setOnClickListener(this);
        tv_delete_ac.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
        tv_change_pass.setOnClickListener(this);
        switch1 = view.findViewById(R.id.switch1);
        rl_online_staus = view.findViewById(R.id.rl_online_staus);
        if (online_stauis.equalsIgnoreCase("1")) {
            switch1.setChecked(true);
        } else {
            switch1.setChecked(false);
        }
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    hitOnlineStatus("1");
                } else {
                    hitOnlineStatus("0");
                }
            }
        });
    }

    private void hitOnlineStatus(final String s) {
        final Dialog dialog = AppUtil.showProgress(getActivity());
        Interface service = CommonUtils.retroInit();
        Call<GenricModel> call = service.onlone_status(PreferenceHandler.readString(getActivity(), PreferenceHandler.USER_ID, ""), s,
                currentLatitude,currentLongitude);
        call.enqueue(new Callback<GenricModel>() {
            @Override
            public void onResponse(Call<GenricModel> call, Response<GenricModel> response) {
                GenricModel data = response.body();
                assert data != null;
                if (data.getCode().equalsIgnoreCase("201")) {
                    dialog.dismiss();
                    CommonUtils.showSmallToast(getActivity(), data.getMessage());
                    if (s.equalsIgnoreCase("1")) {
                        PreferenceHandler.writeString(getActivity(), "online_status", "1");
                    } else {
                        PreferenceHandler.writeString(getActivity(), "online_status", "0");
                    }

                } else {
                    dialog.dismiss();
                    CommonUtils.showSmallToast(getContext(), data.getMessage());
                }
            }

            @Override
            public void onFailure(Call<GenricModel> call, Throwable t) {
                dialog.dismiss();
                CommonUtils.showSmallToast(getContext(), t.getMessage());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_logout:
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                // alertDialog.setTitle("NKA SERVICE");
                alertDialog.setMessage("Are you sure you want to logout?");
                alertDialog.setIcon(R.mipmap.call_icon);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        hitLogout();
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();

                break;


            case R.id.tv_delete_ac:

                final AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(getActivity());
                // alertDialog.setTitle("NKA SERVICE");
                alertDialog1.setMessage("Are you sure you want to delete your account?");
                alertDialog1.setIcon(R.mipmap.call_icon);
                alertDialog1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        hitDeleteAPI();
                    }
                });
                alertDialog1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog1.show();

                break;
            case R.id.tv_change_pass:
                hitChangePass();
                break;
            case R.id.tvTerm:
                i=new Intent(getContext(), WebViewActivity.class);
                i.putExtra("url","https://tosstra.com/terms-and-conditions");
                i.putExtra("title","Term & Conditions");
                startActivity(i);
                break;
            case R.id.tvPrivacy:
                i = new Intent(getContext(), WebViewActivity.class);
                i.putExtra("url","https://tosstra.com/privacy-policy");
                i.putExtra("title","Privacy Policy");
                startActivity(i);
                break;
            case R.id.tvContactUs:
                i = new Intent(getContext(), WebViewActivity.class);
                i.putExtra("url","https://tosstra.com/contact-us");
                i.putExtra("title","Contact Us");
                startActivity(i);
                break;
            case R.id.tvHelp:
                i = new Intent(getContext(), WebViewActivity.class);
                i.putExtra("url","https://tosstra.com/help");
                i.putExtra("title","Help");
                startActivity(i);
                break;
        }
    }

    private void hitChangePass() {
        Intent i = new Intent(getContext(), ChangePasswordActivity.class);
        startActivity(i);
    }

    private void hitLogout() {
        final Dialog dialog = AppUtil.showProgress(getActivity());
        Interface service = CommonUtils.retroInit();
        Call<GenricModel> call = service.logout(userID);
        call.enqueue(new Callback<GenricModel>() {
            @Override
            public void onResponse(Call<GenricModel> call, Response<GenricModel> response) {
                GenricModel data = response.body();
                assert data != null;
                if (data.getCode().equalsIgnoreCase("201")) {
                    dialog.dismiss();
                    PreferenceHandler.clearPref(getActivity());
                    CommonUtils.showLongToast(getContext(), data.getMessage());
                    Intent i = new Intent(getContext(), SigninActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<GenricModel> call, Throwable t) {
                dialog.dismiss();
                CommonUtils.showSmallToast(getContext(), t.getMessage());
            }
        });
    }

    private void hitDeleteAPI() {
        final Dialog dialog = AppUtil.showProgress(getActivity());
        Interface service = CommonUtils.retroInit();
        Call<GenricModel> call = service.deleteAccount(userID);
        call.enqueue(new Callback<GenricModel>() {
            @Override
            public void onResponse(Call<GenricModel> call, Response<GenricModel> response) {
                GenricModel data = response.body();
                assert data != null;
                if (data.getCode().equalsIgnoreCase("201")) {
                    dialog.dismiss();
                    PreferenceHandler.clearPref(getActivity());
                    CommonUtils.showLongToast(getContext(), data.getMessage());
                    Intent i = new Intent(getContext(), ChooseSignupActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<GenricModel> call, Throwable t) {
                dialog.dismiss();
                CommonUtils.showSmallToast(getContext(), t.getMessage());
            }
        });
    }
}