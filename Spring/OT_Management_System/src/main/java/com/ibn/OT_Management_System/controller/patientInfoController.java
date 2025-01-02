package com.ibn.OT_Management_System.controller;

import com.ibn.OT_Management_System.DTO.ApiResponse;
import com.ibn.OT_Management_System.DTO.PatientInfoDto;
import com.ibn.OT_Management_System.DTO.RoomDto;
import com.ibn.OT_Management_System.service.PatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class patientInfoController {

    @Autowired
    private PatientInfoService patientInfoService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/create")
    public ResponseEntity<PatientInfoDto> createCategory(@RequestBody PatientInfoDto patientInfoDto){
        PatientInfoDto patientInfo = this.patientInfoService.createPatientInfo(patientInfoDto);
        return new ResponseEntity<PatientInfoDto>(patientInfo, HttpStatus.CREATED);
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<PatientInfoDto> updateRoom(@RequestBody PatientInfoDto patientInfoDto,@PathVariable Long id){
        PatientInfoDto patientInfoDto1 = this.patientInfoService.updatePatientInfo(patientInfoDto,id);

        // Fetch updated patient data
        List<PatientInfoDto> allPatientInfos = this.patientInfoService.getAllOIAndOCPatientInfo();

        // Notify WebSocket clients with updated data
        messagingTemplate.convertAndSend("/topic/patientUpdates", allPatientInfos);

        return new ResponseEntity<PatientInfoDto>(patientInfoDto1, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse> getAllPatientInfo(){
        List<PatientInfoDto> allPatientInfos = this.patientInfoService.getAllPatientInfo();
        return new ResponseEntity<ApiResponse>(new ApiResponse("Data successfully get!",true,allPatientInfos),HttpStatus.OK);
    }

    @GetMapping("/getAllOIAndOC")
    public ResponseEntity<ApiResponse> getAllOIAndOCPatientInfo(){
        List<PatientInfoDto> allPatientInfos = this.patientInfoService.getAllOIAndOCPatientInfo();
        return new ResponseEntity<ApiResponse>(new ApiResponse("Data successfully get!",true,allPatientInfos),HttpStatus.OK);
    }

    @GetMapping("/complete")
    public ResponseEntity<ApiResponse> completeListPatientInfo(){
        List<PatientInfoDto> allPatientInfos = this.patientInfoService.completeListPatientInfo();
        return new ResponseEntity<ApiResponse>(new ApiResponse("Data successfully get!",true,allPatientInfos),HttpStatus.OK);
    }

//    @PutMapping("updateFlag/{id}")
//    public ResponseEntity<ApiResponse> updateFlag(
//            @PathVariable Long id,
//            @RequestParam String newFlag) {
//        this.patientInfoService.updatePatientFlag(id, newFlag);
//        return new ResponseEntity<ApiResponse>(new ApiResponse("Flag update successful!",true),HttpStatus.OK);
//    }

    // Update flag and notify WebSocket clients
    @PutMapping("updateFlag/{id}")
    public ResponseEntity<ApiResponse> updateFlag(
            @PathVariable Long id,
            @RequestParam String newFlag) {

        // Update the flag in the database
        this.patientInfoService.updatePatientFlag(id, newFlag);

        // Fetch updated patient data
        List<PatientInfoDto> allPatientInfos = this.patientInfoService.getAllOIAndOCPatientInfo();

        // Notify WebSocket clients with updated data
        messagingTemplate.convertAndSend("/topic/patientUpdates", allPatientInfos);

        return new ResponseEntity<>(
                new ApiResponse("Flag update successful!", true), HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<PatientInfoDto> getSingleRoom(@PathVariable Long id){
        PatientInfoDto singlePatientInfo = this.patientInfoService.getSinglePatientInfo(id);
        return new ResponseEntity<PatientInfoDto>(singlePatientInfo,HttpStatus.OK);
    }

    @GetMapping("/byDate")
    public ResponseEntity<ApiResponse> getPatientsByDateRangeAndFlag(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

        List<PatientInfoDto> allPatientInfos = this.patientInfoService.searchPatientInfoByDate(fromDate,toDate);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Data successfully get!",true,allPatientInfos),HttpStatus.OK);
    }


}
