package com.app.tosstraApp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("logBookTime")
    @Expose
    private String logBookTime;
    @SerializedName("truckNumber")
    @Expose
    private String truckNumber;
    @SerializedName("odometerReading")
    @Expose
    private String odometerReading;
    @SerializedName("trailer")
    @Expose
    private String trailer;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("driverSignature")
    @Expose
    private String driverSignature;
    @SerializedName("mechanicSignature")
    @Expose
    private String mechanicSignature;
    @SerializedName("dateTimeM")
    @Expose
    private String dateTimeM;
    @SerializedName("driverSignature2")
    @Expose
    private String driverSignature2;
    @SerializedName("dateTimeD")
    @Expose
    private String dateTimeD;
    @SerializedName("logBookCheckBox")
    @Expose
    private List<String> logBookCheckBox = null;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLogBookTime() {
        return logBookTime;
    }

    public void setLogBookTime(String logBookTime) {
        this.logBookTime = logBookTime;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }

    public String getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(String odometerReading) {
        this.odometerReading = odometerReading;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDriverSignature() {
        return driverSignature;
    }

    public void setDriverSignature(String driverSignature) {
        this.driverSignature = driverSignature;
    }

    public String getMechanicSignature() {
        return mechanicSignature;
    }

    public void setMechanicSignature(String mechanicSignature) {
        this.mechanicSignature = mechanicSignature;
    }

    public String getDateTimeM() {
        return dateTimeM;
    }

    public void setDateTimeM(String dateTimeM) {
        this.dateTimeM = dateTimeM;
    }

    public String getDriverSignature2() {
        return driverSignature2;
    }

    public void setDriverSignature2(String driverSignature2) {
        this.driverSignature2 = driverSignature2;
    }

    public String getDateTimeD() {
        return dateTimeD;
    }

    public void setDateTimeD(String dateTimeD) {
        this.dateTimeD = dateTimeD;
    }

    public List<String> getLogBookCheckBox() {
        return logBookCheckBox;
    }

    public void setLogBookCheckBox(List<String> logBookCheckBox) {
        this.logBookCheckBox = logBookCheckBox;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
