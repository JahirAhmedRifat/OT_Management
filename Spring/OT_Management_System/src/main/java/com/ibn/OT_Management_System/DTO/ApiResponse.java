package com.ibn.OT_Management_System.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {

    private String message;
    private boolean success;
    private List<PatientInfoDto> patientInfoList;
    private RoomDto roomDto;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse(String message, boolean success, List<PatientInfoDto> patientInfoList) {
        this.message = message;
        this.success = success;
        this.patientInfoList = patientInfoList;
    }

    public ApiResponse(String message, RoomDto roomDto, boolean success) {
        this.message = message;
        this.roomDto = roomDto;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PatientInfoDto> getPatientInfoList() {
        return patientInfoList;
    }

    public void setPatientInfoList(List<PatientInfoDto> patientInfoList) {
        this.patientInfoList = patientInfoList;
    }

    public RoomDto getRoomDto() {
        return roomDto;
    }

    public void setRoomDto(RoomDto roomDto) {
        this.roomDto = roomDto;
    }
}
