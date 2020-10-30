package com.app.tosstraApp.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.app.tosstraApp.R;
import com.app.tosstraApp.models.Datum;
import com.app.tosstraApp.models.LogBookModel;
import com.app.tosstraApp.services.Interface;
import com.app.tosstraApp.utils.CommonUtils;
import com.app.tosstraApp.utils.PreferenceHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogBookFilterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_company_name, et_address, et_date, et_time, et_truck_number, et_odometer_reading, et_trailor_number, et_remarks, et_driver_signature, et_mech_signature, et_mec_date, et_drivers_signature, et_driver_date, et_select_date;

    private CheckBox chk_air_compressor, chk_suspention_system, chk_lights;
    private CheckBox chk_battery, chk_brake, chk_transmission, chk_muffer, chk_defroster;
    private CheckBox chk_windows, chk_on_board_recorder, chk_engine, chk_fifth_wheel, chk_front_axle;
    private CheckBox chk_fuel_tanks, chk_body, chk_brakes, chk_coupling_device, chk_fluid_level, chk_tires, chk_heater;
    private CheckBox chk_horn, chk_Air_lines, chk_starter, chk_steering, chk_mirrors, chk_clutch,
            chk_wheels, chk_oil_pressure, chk_drive_line, chk_windshield, chk_radiator, chk_rear_end,
            chk_refectors, chk_safey_equipments, chk_beltsnHoses, chk_brakesnServices, chk_exhaust,
            chk_frame, chk_tachograph, chk_other, chk_brake_connections, chk_suspention_system1, chk_landing_gear,
            chk_coupling_devices, chk_tires1, chk_reflector, chk_doors, chk_others, chk_safe_operation, chk_defect_corrected, chk_condiotn_satif,
            chk_hitch,chk_brakes1,chk_tarpaulin,chk_wheelsnrims,chk_roof,chk_lightsall,chk_coupling;

    private int mYear, mMonth, mDay;
    private LinearLayout linear_main;
    String selected_date;
    private List<Datum> list = new ArrayList<>();
    private ImageView iv_back;
    String finaldate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book_filter);
        initUI();
        linear_main.setVisibility(View.GONE);
        //       HitGetLogBookAPI();

        et_select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }


    private void showDatePickerDialog() {
// Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(LogBookFilterActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        SimpleDateFormat inputDate = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = null;
                        try {
                            date = inputDate.parse(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            assert date != null;
                            selected_date = inputDate.format(date);
                            finaldate = formatDate(selected_date, "dd-MM-yyyy", "dd-MMM-yyyy");
                            et_select_date.setText(finaldate);
                            HitGetLogBookAPI(et_select_date.getText().toString());
                        } catch (Exception e) {

                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }


    public static String formatDate(String date, String initDateFormat, String endDateFormat) throws ParseException {
        Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
        String parsedDate = formatter.format(initDate);
        return parsedDate;
    }

    private void initUI() {
        chk_coupling=findViewById(R.id.chk_coupling);
        chk_lightsall=findViewById(R.id.chk_lightsall);
        chk_roof=findViewById(R.id.chk_roof);
        chk_wheelsnrims=findViewById(R.id.chk_wheelsnrims);
        chk_tarpaulin=findViewById(R.id.chk_tarpaulin);
        chk_brakes1=findViewById(R.id.chk_brakes1);
        chk_hitch=findViewById(R.id.chk_hitch);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        et_date = findViewById(R.id.et_date);
        chk_condiotn_satif = findViewById(R.id.chk_condiotn_satif);
        chk_defect_corrected = findViewById(R.id.chk_defect_corrected);
        chk_safe_operation = findViewById(R.id.chk_safe_operation);
        chk_brake_connections = findViewById(R.id.chk_brake_connections);
        chk_suspention_system1 = findViewById(R.id.chk_suspention_system1);
        chk_landing_gear = findViewById(R.id.chk_landing_gear);
        chk_coupling_devices = findViewById(R.id.chk_coupling_devices);
        chk_tires1 = findViewById(R.id.chk_tires1);
        chk_reflector = findViewById(R.id.chk_reflector);
        chk_doors = findViewById(R.id.chk_doors);
        chk_others = findViewById(R.id.chk_others);


        linear_main = findViewById(R.id.linear_main);
        et_select_date = findViewById(R.id.et_select_date);
        chk_air_compressor = findViewById(R.id.chk_air_compressor);
        chk_suspention_system = findViewById(R.id.chk_suspention_system);
        chk_lights = findViewById(R.id.chk_lights);
        chk_battery = findViewById(R.id.chk_battery);
        chk_brake = findViewById(R.id.chk_brake);
        chk_transmission = findViewById(R.id.chk_transmission);
        chk_muffer = findViewById(R.id.chk_muffer);
        chk_defroster = findViewById(R.id.chk_defroster);
        chk_windows = findViewById(R.id.chk_windows);
        chk_on_board_recorder = findViewById(R.id.chk_on_board_recorder);
        chk_engine = findViewById(R.id.chk_engine);
        chk_fifth_wheel = findViewById(R.id.chk_fifth_wheel);
        chk_front_axle = findViewById(R.id.chk_front_axle);
        chk_fuel_tanks = findViewById(R.id.chk_fuel_tanks);
        chk_body = findViewById(R.id.chk_body);
        chk_brakes = findViewById(R.id.chk_brakes);
        chk_coupling_device = findViewById(R.id.chk_coupling_device);
        chk_fluid_level = findViewById(R.id.chk_fluid_level);
        chk_tires = findViewById(R.id.chk_tires);
        chk_heater = findViewById(R.id.chk_heater);
        chk_horn = findViewById(R.id.chk_horn);
        chk_Air_lines = findViewById(R.id.chk_Air_lines);
        chk_starter = findViewById(R.id.chk_starter);
        chk_steering = findViewById(R.id.chk_steering);
        chk_mirrors = findViewById(R.id.chk_mirrors);
        chk_clutch = findViewById(R.id.chk_clutch);
        chk_wheels = findViewById(R.id.chk_wheels);
        chk_oil_pressure = findViewById(R.id.chk_oil_pressure);
        chk_drive_line = findViewById(R.id.chk_drive_line);
        chk_windshield = findViewById(R.id.chk_windshield);
        chk_radiator = findViewById(R.id.chk_radiator);
        chk_rear_end = findViewById(R.id.chk_rear_end);
        chk_refectors = findViewById(R.id.chk_refectors);
        chk_safey_equipments = findViewById(R.id.chk_safey_equipments);
        chk_beltsnHoses = findViewById(R.id.chk_beltsnHoses);
        chk_brakesnServices = findViewById(R.id.chk_brakesnServices);
        chk_exhaust = findViewById(R.id.chk_exhaust);
        chk_frame = findViewById(R.id.chk_frame);
        chk_tachograph = findViewById(R.id.chk_tachograph);
        chk_other = findViewById(R.id.chk_other);

        et_company_name = findViewById(R.id.et_company_name);

        et_address = findViewById(R.id.et_address);
        et_date = findViewById(R.id.et_date);
        et_time = findViewById(R.id.et_time);
        et_truck_number = findViewById(R.id.et_truck_number);
        et_odometer_reading = findViewById(R.id.et_odometer_reading);
        et_trailor_number = findViewById(R.id.et_trailor_number);
        et_remarks = findViewById(R.id.et_remarks);
        et_driver_signature = findViewById(R.id.et_driver_signature);
        et_mech_signature = findViewById(R.id.et_mech_signature);
        et_mec_date = findViewById(R.id.et_mec_date);
        et_drivers_signature = findViewById(R.id.et_drivers_signature);
        et_driver_date = findViewById(R.id.et_driver_date);
    }


    private void SetData(List<Datum> list) {
        et_address.setText(list.get(0).getAddress());
        et_company_name.setText(list.get(0).getCompanyName());
        et_truck_number.setText(list.get(0).getTruckNumber());
        et_odometer_reading.setText(list.get(0).getOdometerReading());
        et_remarks.setText(list.get(0).getRemarks());
        et_mech_signature.setText(list.get(0).getMechanicSignature());
        et_driver_signature.setText(list.get(0).getDriverSignature2());
        et_drivers_signature.setText(list.get(0).getDriverSignature2());
        et_trailor_number.setText(list.get(0).getTrailer());
        et_time.setText(list.get(0).getDateTime());
        et_mec_date.setText(list.get(0).getDateTimeM());
        et_driver_date.setText(list.get(0).getDateTimeD());

        if (list.get(0).getLogBookCheckBox().size() > 0) {
            List<String> checklist = list.get(0).getLogBookCheckBox();


            if (checklist.contains(chk_air_compressor.getText().toString())) {
                chk_air_compressor.setChecked(true);
            } else {
                chk_air_compressor.setChecked(false);
            }

            if (checklist.contains(chk_horn.getText().toString())) {
                chk_horn.setChecked(true);
            } else {
                chk_horn.setChecked(false);
            }

            if (checklist.contains(chk_coupling.getText().toString())) {
                chk_coupling.setChecked(true);
            } else {
                chk_coupling.setChecked(false);
            }


            if (checklist.contains(chk_suspention_system.getText().toString())) {
                chk_suspention_system.setChecked(true);
            } else {
                chk_suspention_system.setChecked(false);
            }

            if (checklist.contains(chk_lightsall.getText().toString())) {
                chk_lightsall.setChecked(true);
            } else {
                chk_lightsall.setChecked(false);
            }



            if (checklist.contains(chk_air_compressor.getText().toString())) {
                chk_air_compressor.setChecked(true);
            } else {
                chk_air_compressor.setChecked(false);
            }


            if (checklist.contains(chk_lights.getText().toString())) {
                chk_lights.setChecked(true);
            } else {
                chk_lights.setChecked(false);
            }

            if (checklist.contains(chk_starter.getText().toString())) {
                chk_starter.setChecked(true);
            } else {
                chk_starter.setChecked(false);
            }

            if (checklist.contains(chk_battery.getText().toString())) {
                chk_battery.setChecked(true);
            } else {
                chk_battery.setChecked(false);
            }

            if (checklist.contains(chk_steering.getText().toString())) {
                chk_steering.setChecked(true);
            } else {
                chk_steering.setChecked(false);
            }

            if (checklist.contains(chk_brake.getText().toString())) {
                chk_brake.setChecked(true);
            } else {
                chk_brake.setChecked(false);
            }

            if (checklist.contains(chk_transmission.getText().toString())) {
                chk_transmission.setChecked(true);
            } else {
                chk_transmission.setChecked(false);
            }

            if (checklist.contains(chk_muffer.getText().toString())) {
                chk_muffer.setChecked(true);
            } else {
                chk_muffer.setChecked(false);
            }

            if (checklist.contains(chk_defroster.getText().toString())) {
                chk_defroster.setChecked(true);
            } else {
                chk_defroster.setChecked(false);
            }

            if (checklist.contains(chk_windows.getText().toString())) {
                chk_windows.setChecked(true);
            } else {
                chk_windows.setChecked(false);
            }

            if (checklist.contains(chk_on_board_recorder.getText().toString())) {
                chk_on_board_recorder.setChecked(true);
            } else {
                chk_on_board_recorder.setChecked(false);
            }

            if (checklist.contains(chk_engine.getText().toString())) {
                chk_engine.setChecked(true);
            } else {
                chk_engine.setChecked(false);
            }

            if (checklist.contains(chk_fifth_wheel.getText().toString())) {
                chk_fifth_wheel.setChecked(true);
            } else {
                chk_fifth_wheel.setChecked(false);
            }

            if (checklist.contains(chk_front_axle.getText().toString())) {
                chk_front_axle.setChecked(true);
            } else {
                chk_front_axle.setChecked(false);
            }

            if (checklist.contains(chk_fuel_tanks.getText().toString())) {
                chk_fuel_tanks.setChecked(true);
            } else {
                chk_fuel_tanks.setChecked(false);
            }

            if (checklist.contains(chk_body.getText().toString())) {
                chk_body.setChecked(true);
            } else {
                chk_body.setChecked(false);
            }
            if (checklist.contains(chk_brakes.getText().toString())) {
                chk_brakes.setChecked(true);
            } else {
                chk_brakes.setChecked(false);
            }

            if (checklist.contains(chk_coupling_device.getText().toString())) {
                chk_coupling_device.setChecked(true);
            } else {
                chk_coupling_device.setChecked(false);
            }

            if (checklist.contains(chk_fluid_level.getText().toString())) {
                chk_fluid_level.setChecked(true);
            } else {
                chk_fluid_level.setChecked(false);
            }

            if (checklist.contains(chk_tires.getText().toString())) {
                chk_tires.setChecked(true);
            } else {
                chk_tires.setChecked(false);
            }

            if (checklist.contains(chk_heater.getText().toString())) {
                chk_heater.setChecked(true);
            } else {
                chk_heater.setChecked(false);
            }

            if (checklist.contains(chk_mirrors.getText().toString())) {
                chk_mirrors.setChecked(true);
            } else {
                chk_mirrors.setChecked(false);
            }

            if (checklist.contains(chk_clutch.getText().toString())) {
                chk_clutch.setChecked(true);
            } else {
                chk_clutch.setChecked(false);
            }
            if (checklist.contains(chk_wheels.getText().toString())) {
                chk_wheels.setChecked(true);
            } else {
                chk_wheels.setChecked(false);
            }

            if (checklist.contains(chk_oil_pressure.getText().toString())) {
                chk_oil_pressure.setChecked(true);
            } else {
                chk_oil_pressure.setChecked(false);
            }

            if (checklist.contains(chk_drive_line.getText().toString())) {
                chk_drive_line.setChecked(true);
            } else {
                chk_drive_line.setChecked(false);
            }

            if (checklist.contains(chk_windshield.getText().toString())) {
                chk_windshield.setChecked(true);
            } else {
                chk_windshield.setChecked(false);
            }
            if (checklist.contains(chk_radiator.getText().toString())) {
                chk_radiator.setChecked(true);
            } else {
                chk_windshield.setChecked(false);
            }

            if (checklist.contains(chk_rear_end.getText().toString())) {
                chk_rear_end.setChecked(true);
            } else {
                chk_rear_end.setChecked(false);
            }

            if (checklist.contains(chk_refectors.getText().toString())) {
                chk_refectors.setChecked(true);
            } else {
                chk_refectors.setChecked(false);
            }

            if (checklist.contains(chk_safey_equipments.getText().toString())) {
                chk_safey_equipments.setChecked(true);
            } else {
                chk_safey_equipments.setChecked(false);
            }

            if (checklist.contains(chk_beltsnHoses.getText().toString())) {
                chk_beltsnHoses.setChecked(true);
            } else {
                chk_beltsnHoses.setChecked(false);
            }

            if (checklist.contains(chk_brakesnServices.getText().toString())) {
                chk_brakesnServices.setChecked(true);
            } else {
                chk_brakesnServices.setChecked(false);
            }

            if (checklist.contains(chk_exhaust.getText().toString())) {
                chk_exhaust.setChecked(true);
            } else {
                chk_exhaust.setChecked(false);
            }

            if (checklist.contains(chk_frame.getText().toString())) {
                chk_frame.setChecked(true);
            } else {
                chk_frame.setChecked(false);
            }

            if (checklist.contains(chk_tachograph.getText().toString())) {
                chk_tachograph.setChecked(true);
            } else {
                chk_tachograph.setChecked(false);
            }

            if (checklist.contains(chk_other.getText().toString())) {
                chk_other.setChecked(true);
            } else {
                chk_other.setChecked(false);
            }

            if (checklist.contains(chk_brake_connections.getText().toString())) {
                chk_brake_connections.setChecked(true);
            } else {
                chk_brake_connections.setChecked(false);
            }

            if (checklist.contains(chk_suspention_system1.getText().toString())) {
                chk_suspention_system1.setChecked(true);
            } else {
                chk_suspention_system1.setChecked(false);
            }

            if (checklist.contains(chk_landing_gear.getText().toString())) {
                chk_landing_gear.setChecked(true);
            } else {
                chk_landing_gear.setChecked(false);
            }

            if (checklist.contains(chk_coupling_devices.getText().toString())) {
                chk_coupling_devices.setChecked(true);
            } else {
                chk_coupling_devices.setChecked(false);
            }

            if (checklist.contains(chk_tires1.getText().toString())) {
                chk_tires1.setChecked(true);
            } else {
                chk_tires1.setChecked(false);
            }

            if (checklist.contains(chk_reflector.getText().toString())) {
                chk_reflector.setChecked(true);
            } else {
                chk_reflector.setChecked(false);
            }

            if (checklist.contains(chk_doors.getText().toString())) {
                chk_doors.setChecked(true);
            } else {
                chk_doors.setChecked(false);
            }

            if (checklist.contains(chk_others.getText().toString())) {
                chk_others.setChecked(true);
            } else {
                chk_others.setChecked(false);
            }
            if (checklist.contains("Condition")) {
                chk_condiotn_satif.setChecked(true);
            } else {
                chk_condiotn_satif.setChecked(false);
            }

            if (checklist.contains("Defects")) {
                chk_defect_corrected.setChecked(true);
            } else {
                chk_defect_corrected.setChecked(false);
            }


            if (checklist.contains("Not Defects")) {
                chk_safe_operation.setChecked(true);
            } else {
                chk_safe_operation.setChecked(false);
            }


            if (checklist.contains(chk_Air_lines.getText().toString())) {
                chk_Air_lines.setChecked(true);
            } else {
                chk_Air_lines.setChecked(false);
            }

            if (checklist.contains(chk_Air_lines.getText().toString())) {
                chk_Air_lines.setChecked(true);
            } else {
                chk_Air_lines.setChecked(false);
            }

            if (checklist.contains(chk_brakes.getText().toString())) {
                chk_brakes.setChecked(true);
            } else {
                chk_brakes.setChecked(false);
            }




            if (checklist.contains(chk_hitch.getText().toString())) {
                chk_hitch.setChecked(true);
            } else {
                chk_hitch.setChecked(false);
            }

            if (checklist.contains(chk_brakes1.getText().toString())) {
                chk_brakes1.setChecked(true);
            } else {
                chk_brakes1.setChecked(false);
            }

            if (checklist.contains(chk_tarpaulin.getText().toString())) {
                chk_tarpaulin.setChecked(true);
            } else {
                chk_tarpaulin.setChecked(false);
            }


            if (checklist.contains(chk_wheelsnrims.getText().toString())) {
                chk_wheelsnrims.setChecked(true);
            } else {
                chk_wheelsnrims.setChecked(false);
            }

            if (checklist.contains(chk_roof.getText().toString())) {
                chk_roof.setChecked(true);
            } else {
                chk_roof.setChecked(false);
            }




        }

    }

    private void HitGetLogBookAPI(String date) {
        final Dialog dialog = AppUtil.showProgress(LogBookFilterActivity.this);
        Interface service = CommonUtils.retroInit();
        Call<LogBookModel> call = service.getlogbook(PreferenceHandler.readString(LogBookFilterActivity.this, PreferenceHandler.USER_ID, ""), date);
        call.enqueue(new Callback<LogBookModel>() {
            @Override
            public void onResponse(Call<LogBookModel> call, Response<LogBookModel> response) {
                LogBookModel data = response.body();
                if (response.isSuccessful()) {
                    list.clear();
                    if (data != null) {
                        if (data.getCode().equalsIgnoreCase("201")) {
                            dialog.dismiss();
                            list.addAll(data.getData());
                            linear_main.setVisibility(View.VISIBLE);

                            if (list.size() > 0) {
                                SetData(list);
                            }

                        } else {
                            CommonUtils.showSmallToast(LogBookFilterActivity.this, data.getMessage());
                            dialog.dismiss();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LogBookModel> call, Throwable t) {
                dialog.dismiss();
                CommonUtils.showSmallToast(LogBookFilterActivity.this, "" + t.getMessage());
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}