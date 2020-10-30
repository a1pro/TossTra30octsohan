package com.app.tosstraApp.fragments.driver;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.app.tosstraApp.R;
import com.app.tosstraApp.activities.AppUtil;
import com.app.tosstraApp.models.GenricModel;
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

public class LogBookFragment extends Fragment implements View.OnClickListener {
    private Button bt_submit;
    private EditText et_company_name, et_address, et_date, et_time, et_truck_number, et_odometer_reading,
            et_trailor_number, et_remarks, et_driver_signature, et_mech_signature,
            et_mec_date, et_drivers_signature, et_driver_date;

    private CheckBox chk_air_compressor, chk_suspention_system, chk_lights;
    private CheckBox chk_battery, chk_brake, chk_transmission, chk_muffer, chk_defroster;
    private CheckBox chk_windows, chk_on_board_recorder, chk_engine, chk_fifth_wheel, chk_front_axle;
    private CheckBox chk_fuel_tanks, chk_body, chk_brakes, chk_coupling_device, chk_fluid_level, chk_tires, chk_heater;
    private CheckBox chk_horn, chk_Air_lines, chk_starter, chk_steering, chk_mirrors, chk_clutch,
            chk_wheels, chk_oil_pressure, chk_drive_line, chk_windshield, chk_radiator, chk_rear_end,
            chk_refectors, chk_safey_equipments, chk_beltsnHoses, chk_brakesnServices, chk_exhaust,
            chk_frame, chk_tachograph, chk_other,chk_safe_operation,chk_defect_corrected,chk_condiotn_satif,
            chk_hitch,chk_brakes1,chk_tarpaulin,chk_wheelsnrims,chk_roof,chk_brake_connections,chk_landing_gear,chk_lightsall,chk_reflector,chk_doors,chk_coupling;
    List<String> values = new ArrayList<>();
    private int mYear, mMonth, mDay, mHour, mMinute;
    String selected_date;
    String finaldate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_book, container, false);
        initUI(view);


        chk_air_compressor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_air_compressor.isChecked()) {
                    values.add(chk_air_compressor.getText().toString());
                } else {
                    values.remove(chk_air_compressor.getText().toString());
                }
            }
        });

        chk_suspention_system.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_suspention_system.isChecked()) {
                    values.add(chk_suspention_system.getText().toString());
                } else {
                    values.remove(chk_suspention_system.getText().toString());
                }
            }
        });

        chk_lights.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_lights.isChecked()) {
                    values.add(chk_lights.getText().toString());
                } else {
                    values.remove(chk_lights.getText().toString());
                }
            }
        });

        chk_battery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_battery.isChecked()) {
                    values.add(chk_battery.getText().toString());
                } else {
                    values.remove(chk_battery.getText().toString());
                }
            }
        });

        chk_brake.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_brake.isChecked()) {
                    values.add(chk_brake.getText().toString());
                } else {
                    values.remove(chk_brake.getText().toString());
                }
            }
        });

        chk_transmission.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_transmission.isChecked()) {
                    values.add(chk_transmission.getText().toString());
                } else {
                    values.remove(chk_transmission.getText().toString());
                }
            }
        });


        chk_muffer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_muffer.isChecked()) {
                    values.add(chk_muffer.getText().toString());
                } else {
                    values.remove(chk_muffer.getText().toString());
                }
            }
        });


        chk_defroster.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_defroster.isChecked()) {
                    values.add(chk_defroster.getText().toString());
                } else {
                    values.remove(chk_defroster.getText().toString());
                }
            }
        });

        chk_windows.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_windows.isChecked()) {
                    values.add(chk_windows.getText().toString());
                } else {
                    values.remove(chk_windows.getText().toString());
                }
            }
        });

        chk_on_board_recorder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_on_board_recorder.isChecked()) {
                    values.add(chk_on_board_recorder.getText().toString());
                } else {
                    values.remove(chk_on_board_recorder.getText().toString());
                }
            }
        });

        chk_engine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_engine.isChecked()) {
                    values.add(chk_engine.getText().toString());
                } else {
                    values.remove(chk_engine.getText().toString());
                }
            }
        });

        chk_fifth_wheel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_fifth_wheel.isChecked()) {
                    values.add(chk_fifth_wheel.getText().toString());
                } else {
                    values.remove(chk_fifth_wheel.getText().toString());
                }
            }
        });

        chk_front_axle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_front_axle.isChecked()) {
                    values.add(chk_front_axle.getText().toString());
                } else {
                    values.remove(chk_front_axle.getText().toString());
                }
            }
        });


        chk_fuel_tanks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_fuel_tanks.isChecked()) {
                    values.add(chk_fuel_tanks.getText().toString());
                } else {
                    values.remove(chk_fuel_tanks.getText().toString());
                }
            }
        });

        chk_body.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_body.isChecked()) {
                    values.add(chk_body.getText().toString());
                } else {
                    values.remove(chk_body.getText().toString());
                }
            }
        });


        chk_brakes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_brakes.isChecked()) {
                    values.add(chk_brakes.getText().toString());
                } else {
                    values.remove(chk_brakes.getText().toString());
                }
            }
        });

        chk_coupling_device.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_coupling_device.isChecked()) {
                    values.add(chk_coupling_device.getText().toString());
                } else {
                    values.remove(chk_coupling_device.getText().toString());
                }
            }
        });

        chk_fluid_level.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_fluid_level.isChecked()) {
                    values.add(chk_fluid_level.getText().toString());
                } else {
                    values.remove(chk_fluid_level.getText().toString());
                }
            }
        });

        chk_tires.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_tires.isChecked()) {
                    values.add(chk_tires.getText().toString());
                } else {
                    values.remove(chk_tires.getText().toString());
                }
            }
        });

        chk_heater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_heater.isChecked()) {
                    values.add(chk_heater.getText().toString());
                } else {
                    values.remove(chk_heater.getText().toString());
                }
            }
        });

        chk_horn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_horn.isChecked()) {
                    values.add(chk_horn.getText().toString());
                } else {
                    values.remove(chk_horn.getText().toString());
                }
            }
        });


        chk_Air_lines.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_Air_lines.isChecked()) {
                    values.add(chk_Air_lines.getText().toString());
                } else {
                    values.remove(chk_Air_lines.getText().toString());
                }
            }
        });

        chk_coupling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_coupling.isChecked()) {
                    values.add(chk_coupling.getText().toString());
                } else {
                    values.remove(chk_coupling.getText().toString());
                }
            }
        });


        chk_starter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_starter.isChecked()) {
                    values.add(chk_starter.getText().toString());
                } else {
                    values.remove(chk_starter.getText().toString());
                }
            }
        });

        chk_brake_connections.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_brake_connections.isChecked()) {
                    values.add(chk_brake_connections.getText().toString());
                } else {
                    values.remove(chk_brake_connections.getText().toString());
                }
            }
        });





        chk_steering.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_steering.isChecked()) {
                    values.add(chk_steering.getText().toString());
                } else {
                    values.remove(chk_steering.getText().toString());
                }
            }
        });

        chk_mirrors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_mirrors.isChecked()) {
                    values.add(chk_mirrors.getText().toString());
                } else {
                    values.remove(chk_mirrors.getText().toString());
                }
            }
        });


        chk_clutch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_clutch.isChecked()) {
                    values.add(chk_clutch.getText().toString());
                } else {
                    values.remove(chk_clutch.getText().toString());
                }
            }
        });

        chk_wheels.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_wheels.isChecked()) {
                    values.add(chk_wheels.getText().toString());
                } else {
                    values.remove(chk_wheels.getText().toString());
                }
            }
        });
        chk_oil_pressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_oil_pressure.isChecked()) {
                    values.add(chk_oil_pressure.getText().toString());
                } else {
                    values.remove(chk_oil_pressure.getText().toString());
                }
            }
        });

        chk_drive_line.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_drive_line.isChecked()) {
                    values.add(chk_drive_line.getText().toString());
                } else {
                    values.remove(chk_drive_line.getText().toString());
                }
            }
        });
        chk_windshield.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_windshield.isChecked()) {
                    values.add(chk_windshield.getText().toString());
                } else {
                    values.remove(chk_windshield.getText().toString());
                }
            }
        });

        chk_radiator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_radiator.isChecked()) {
                    values.add(chk_radiator.getText().toString());
                } else {
                    values.remove(chk_radiator.getText().toString());
                }
            }
        });

        chk_rear_end.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_rear_end.isChecked()) {
                    values.add(chk_rear_end.getText().toString());
                } else {
                    values.remove(chk_rear_end.getText().toString());
                }
            }
        });

        chk_refectors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_refectors.isChecked()) {
                    values.add(chk_refectors.getText().toString());
                } else {
                    values.remove(chk_refectors.getText().toString());
                }
            }
        });

        chk_safey_equipments.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_safey_equipments.isChecked()) {
                    values.add(chk_safey_equipments.getText().toString());
                } else {
                    values.remove(chk_safey_equipments.getText().toString());
                }
            }
        });

        chk_beltsnHoses.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_beltsnHoses.isChecked()) {
                    values.add(chk_beltsnHoses.getText().toString());
                } else {
                    values.remove(chk_beltsnHoses.getText().toString());
                }
            }
        });

        chk_brakesnServices.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_brakesnServices.isChecked()) {
                    values.add(chk_brakesnServices.getText().toString());
                } else {
                    values.remove(chk_brakesnServices.getText().toString());
                }
            }
        });

        chk_reflector.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_reflector.isChecked()) {
                    values.add(chk_reflector.getText().toString());
                } else {
                    values.remove(chk_reflector.getText().toString());
                }
            }
        });



        chk_exhaust.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_exhaust.isChecked()) {
                    values.add(chk_exhaust.getText().toString());
                } else {
                    values.remove(chk_exhaust.getText().toString());
                }
            }
        });


        chk_lightsall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_lightsall.isChecked()) {
                    values.add(chk_lightsall.getText().toString());
                } else {
                    values.remove(chk_lightsall.getText().toString());
                }
            }
        });



        chk_frame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_frame.isChecked()) {
                    values.add(chk_frame.getText().toString());
                } else {
                    values.remove(chk_frame.getText().toString());
                }
            }
        });

        chk_tachograph.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_tachograph.isChecked()) {
                    values.add(chk_tachograph.getText().toString());
                } else {
                    values.remove(chk_tachograph.getText().toString());
                }
            }
        });

        chk_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_other.isChecked()) {
                    values.add(chk_other.getText().toString());
                } else {
                    values.remove(chk_other.getText().toString());
                }
            }
        });
        chk_doors.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_doors.isChecked()) {
                    values.add(chk_doors.getText().toString());
                } else {
                    values.remove(chk_doors.getText().toString());
                }
            }
        });



        chk_hitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_hitch.isChecked()) {
                    values.add(chk_hitch.getText().toString());
                } else {
                    values.remove(chk_hitch.getText().toString());
                }
            }
        });

        chk_brakes1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_brakes1.isChecked()) {
                    values.add(chk_brakes1.getText().toString());
                } else {
                    values.remove(chk_brakes1.getText().toString());
                }
            }
        });

        chk_tarpaulin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_tarpaulin.isChecked()) {
                    values.add(chk_tarpaulin.getText().toString());
                } else {
                    values.remove(chk_tarpaulin.getText().toString());
                }
            }
        });

        chk_wheelsnrims.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_wheelsnrims.isChecked()) {
                    values.add(chk_wheelsnrims.getText().toString());
                } else {
                    values.remove(chk_wheelsnrims.getText().toString());
                }
            }
        });

        chk_roof.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_roof.isChecked()) {
                    values.add(chk_roof.getText().toString());
                } else {
                    values.remove(chk_roof.getText().toString());
                }
            }
        });

        chk_landing_gear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_landing_gear.isChecked()) {
                    values.add(chk_landing_gear.getText().toString());
                } else {
                    values.remove(chk_landing_gear.getText().toString());
                }
            }
        });



        chk_safe_operation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_safe_operation.isChecked()) {
                    values.add("Not Defects");
                } else {
                    values.remove("Not Defects");
                }
            }
        });

        chk_defect_corrected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_defect_corrected.isChecked()) {
                    values.add("Defects");
                } else {
                    values.remove("Defects");
                }
            }
        });

        chk_condiotn_satif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_condiotn_satif.isChecked()) {
                    values.add("Condition");
                } else {
                    values.remove("Condition");
                }
            }
        });










        return view;
    }


    private void initUI(View view) {
        chk_brakesnServices = view.findViewById(R.id.chk_brakesnServices);
        chk_brakes = view.findViewById(R.id.chk_brakes);
        chk_coupling=view.findViewById(R.id.chk_coupling);
        chk_doors=view.findViewById(R.id.chk_doors);
        chk_reflector=view.findViewById(R.id.chk_reflector);
        chk_lightsall=view.findViewById(R.id.chk_lightsall);
        chk_landing_gear=view.findViewById(R.id.chk_landing_gear);
        chk_brake_connections=view.findViewById(R.id.chk_brake_connections);
        chk_roof=view.findViewById(R.id.chk_roof);
        chk_wheelsnrims=view.findViewById(R.id.chk_wheelsnrims);
        chk_tarpaulin=view.findViewById(R.id.chk_tarpaulin);
        chk_brakes1=view.findViewById(R.id.chk_brakes1);
        chk_hitch=view.findViewById(R.id.chk_hitch);
        chk_condiotn_satif=view.findViewById(R.id.chk_condiotn_satif);
        chk_defect_corrected=view.findViewById(R.id.chk_defect_corrected);
        chk_safe_operation=view.findViewById(R.id.chk_safe_operation);
        chk_air_compressor = view.findViewById(R.id.chk_air_compressor);
        chk_suspention_system = view.findViewById(R.id.chk_suspention_system);
        chk_lights = view.findViewById(R.id.chk_lights);
        chk_battery = view.findViewById(R.id.chk_battery);
        chk_brake = view.findViewById(R.id.chk_brake);
        chk_transmission = view.findViewById(R.id.chk_transmission);
        chk_muffer = view.findViewById(R.id.chk_muffer);
        chk_defroster = view.findViewById(R.id.chk_defroster);
        chk_windows = view.findViewById(R.id.chk_windows);
        chk_on_board_recorder = view.findViewById(R.id.chk_on_board_recorder);
        chk_engine = view.findViewById(R.id.chk_engine);
        chk_fifth_wheel = view.findViewById(R.id.chk_fifth_wheel);
        chk_front_axle = view.findViewById(R.id.chk_front_axle);
        chk_fuel_tanks = view.findViewById(R.id.chk_fuel_tanks);
        chk_body = view.findViewById(R.id.chk_body);

        chk_coupling_device = view.findViewById(R.id.chk_coupling_device);
        chk_fluid_level = view.findViewById(R.id.chk_fluid_level);
        chk_tires = view.findViewById(R.id.chk_tires);
        chk_heater = view.findViewById(R.id.chk_heater);
        chk_horn = view.findViewById(R.id.chk_horn);
        chk_Air_lines = view.findViewById(R.id.chk_Air_lines);
        chk_starter = view.findViewById(R.id.chk_starter);
        chk_steering = view.findViewById(R.id.chk_steering);
        chk_mirrors = view.findViewById(R.id.chk_mirrors);
        chk_clutch = view.findViewById(R.id.chk_clutch);
        chk_wheels = view.findViewById(R.id.chk_wheels);
        chk_oil_pressure = view.findViewById(R.id.chk_oil_pressure);
        chk_drive_line = view.findViewById(R.id.chk_drive_line);
        chk_windshield = view.findViewById(R.id.chk_windshield);
        chk_radiator = view.findViewById(R.id.chk_radiator);
        chk_rear_end = view.findViewById(R.id.chk_rear_end);
        chk_refectors = view.findViewById(R.id.chk_refectors);
        chk_safey_equipments = view.findViewById(R.id.chk_safey_equipments);
        chk_beltsnHoses = view.findViewById(R.id.chk_beltsnHoses);

        chk_exhaust = view.findViewById(R.id.chk_exhaust);
        chk_frame = view.findViewById(R.id.chk_frame);
        chk_tachograph = view.findViewById(R.id.chk_tachograph);
        chk_other = view.findViewById(R.id.chk_other);


        bt_submit = view.findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(this);
        et_company_name = view.findViewById(R.id.et_company_name);

        et_address = view.findViewById(R.id.et_address);
        et_date = view.findViewById(R.id.et_date);
        et_time = view.findViewById(R.id.et_time);
        et_truck_number = view.findViewById(R.id.et_truck_number);
        et_odometer_reading = view.findViewById(R.id.et_odometer_reading);
        et_trailor_number = view.findViewById(R.id.et_trailor_number);
        et_remarks = view.findViewById(R.id.et_remarks);
        et_driver_signature = view.findViewById(R.id.et_driver_signature);
        et_mech_signature = view.findViewById(R.id.et_mech_signature);
        et_mec_date = view.findViewById(R.id.et_mec_date);
        et_drivers_signature = view.findViewById(R.id.et_drivers_signature);
        et_driver_date = view.findViewById(R.id.et_driver_date);


        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        et_mec_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog1();
            }
        });


        et_driver_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog2();
            }
        });
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog();
            }
        });


    }

    private void showDatePickerDialog() {
// Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        //  et_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        SimpleDateFormat inputDate = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = null;
                        try {
                            date = inputDate.parse(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            assert date != null;
                            selected_date = inputDate.format(date);
                            finaldate = formatDate(selected_date, "dd-MM-yyyy", "dd-MMM-yyyy");
                            et_date.setText(finaldate);
                        } catch (Exception e) {

                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void showDatePickerDialog1() {
// Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        //  et_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        SimpleDateFormat inputDate = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = null;
                        try {
                            date = inputDate.parse(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            assert date != null;
                            String selected_date = inputDate.format(date);
                            String finaldate = formatDate(selected_date, "dd-MM-yyyy", "dd-MMM-yyyy");
                            et_mec_date.setText(finaldate);
                        } catch (Exception e) {

                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void showDatePickerDialog2() {
// Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        //  et_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        SimpleDateFormat inputDate = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = null;
                        try {
                            date = inputDate.parse(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            assert date != null;
                            String selected_date = inputDate.format(date);
                            String finaldate = formatDate(selected_date, "dd-MM-yyyy", "dd-MMM-yyyy");
                            et_driver_date.setText(finaldate);
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


    private void TimePickerDialog() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        et_time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_submit:
                String text = values.toString().replace("[", "").replace("]", "");
                String text2 = text.replace(", ", ",");
                hitLogBookAPI(text2);
                break;
        }
    }

    private void hitLogBookAPI(String text) {
        final Dialog dialog = AppUtil.showProgress(getActivity());
        Interface service = CommonUtils.retroInit();
        Call<GenricModel> call = service.logBook(et_company_name.getText().toString(),
                et_address.getText().toString(),
                et_date.getText().toString(),
                et_truck_number.getText().toString(),
                et_odometer_reading.getText().toString(),
                et_trailor_number.getText().toString(),
                et_remarks.getText().toString(),
                et_driver_signature.getText().toString(),
                et_mech_signature.getText().toString(),
                et_mec_date.getText().toString(),
                et_drivers_signature.getText().toString(),
                et_driver_date.getText().toString(),
                text,
                PreferenceHandler.readString(
                        getActivity(), PreferenceHandler.USER_ID, ""));
        call.enqueue(new Callback<GenricModel>() {
            @Override
            public void onResponse(Call<GenricModel> call, Response<GenricModel> response) {
                GenricModel data = response.body();
                if (data != null) {
                    if (data.getCode().equalsIgnoreCase("201")) {
                        dialog.dismiss();
                        CommonUtils.showSmallToast(getActivity(), data.getMessage());
                    } else {
                        CommonUtils.showSmallToast(getActivity(), data.getMessage());
                        dialog.dismiss();
                    }
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
