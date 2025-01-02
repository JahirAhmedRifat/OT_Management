package com.ibn.OT_Management_System.service.Impl;

import com.ibn.OT_Management_System.DTO.PatientInfoDto;
import com.ibn.OT_Management_System.common.ResourceNotFoundException;
import com.ibn.OT_Management_System.entity.PatientInfo;
import com.ibn.OT_Management_System.repository.PatientInfoRepo;
import com.ibn.OT_Management_System.service.PatientInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientInfoServiceImpl implements PatientInfoService {

    @Autowired
    private PatientInfoRepo patientInfoRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientInfoDto createPatientInfo(PatientInfoDto patientInfoDto) {
        patientInfoDto.setFlag("OP");
        PatientInfo patientInfo = this.modelMapper.map(patientInfoDto, PatientInfo.class);
        PatientInfo patientInfoSave = this.patientInfoRepo.save(patientInfo);
        return this.modelMapper.map(patientInfoSave,PatientInfoDto.class);
    }

    @Override
    public PatientInfoDto updatePatientInfo(PatientInfoDto patientInfoDto, Long id) {
        PatientInfo patientInfo = this.patientInfoRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient","Id",id));
        patientInfo.setPatientName(patientInfoDto.getPatientName());
        patientInfo.setAge(patientInfoDto.getAge());
        patientInfo.setGender(patientInfoDto.getGender());
        patientInfo.setOperationName(patientInfoDto.getOperationName());
        patientInfo.setOtRoom(patientInfoDto.getOtRoom());
        patientInfo.setRemarks(patientInfoDto.getRemarks());
        patientInfo.setSurgeonName(patientInfoDto.getSurgeonName());
        patientInfo.setStatus(patientInfoDto.getStatus());
        PatientInfo save = this.patientInfoRepo.save(patientInfo);
        return this.modelMapper.map(save,PatientInfoDto.class);
    }

    @Override
    public List<PatientInfoDto> getAllPatientInfo() {
        List<PatientInfo> allPatientInfo = this.patientInfoRepo.getAllPatientInfo();
        List<PatientInfoDto> allPatientInfoDto = allPatientInfo.stream().map((patientInfo)-> this.modelMapper.map(patientInfo,PatientInfoDto.class)).collect(Collectors.toList());
        return allPatientInfoDto;
    }

    @Override
    public List<PatientInfoDto> getAllOIAndOCPatientInfo() {
        List<PatientInfo> allPatientInfo = this.patientInfoRepo.getAllOIAndOCPatientInfo();
        List<PatientInfoDto> allPatientInfoDto = allPatientInfo.stream().map((patientInfo)-> this.modelMapper.map(patientInfo,PatientInfoDto.class)).collect(Collectors.toList());
        return allPatientInfoDto;
    }

    @Override
    public List<PatientInfoDto> completeListPatientInfo() {
        List<PatientInfo> allPatientInfo = this.patientInfoRepo.completeListPatientInfo();
        List<PatientInfoDto> allPatientInfoDto = allPatientInfo.stream().map((patientInfo)-> this.modelMapper.map(patientInfo,PatientInfoDto.class)).collect(Collectors.toList());
        return allPatientInfoDto;
    }

    @Override
    public void updatePatientFlag(Long id, String newFlag) {
        Date lastModifiedDate = new Date();
        this.patientInfoRepo.updatePatientFlag(id,newFlag, lastModifiedDate);
    }

    @Override
    public PatientInfoDto getSinglePatientInfo(Long id) {
        PatientInfo patientInfo = this.patientInfoRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient","Id",id));
        PatientInfoDto patientInfoDto = this.modelMapper.map(patientInfo,PatientInfoDto.class);
        return patientInfoDto;
    }

    @Override
    public List<PatientInfoDto> searchPatientInfoByDate(Date fromDate, Date toDate) {
        List<PatientInfo> allPatientInfo = this.patientInfoRepo.searchPatientInfoByDate(fromDate,toDate);
        List<PatientInfoDto> allPatientInfoDto = allPatientInfo.stream().map((patientInfo)-> this.modelMapper.map(patientInfo,PatientInfoDto.class)).collect(Collectors.toList());
        return allPatientInfoDto;
    }

}
