package com.ibn.OT_Management_System.service;

import com.ibn.OT_Management_System.DTO.PatientInfoDto;

import java.util.Date;
import java.util.List;

public interface PatientInfoService {

    PatientInfoDto createPatientInfo(PatientInfoDto patientInfoDto);

    PatientInfoDto updatePatientInfo(PatientInfoDto patientInfoDto,Long id);

    List<PatientInfoDto> getAllPatientInfo();

    List<PatientInfoDto> getAllOIAndOCPatientInfo();

    List<PatientInfoDto> completeListPatientInfo();

    void updatePatientFlag(Long id, String newFlag);

    PatientInfoDto getSinglePatientInfo(Long id);

    List<PatientInfoDto> searchPatientInfoByDate(Date fromDate, Date toDate);



}
