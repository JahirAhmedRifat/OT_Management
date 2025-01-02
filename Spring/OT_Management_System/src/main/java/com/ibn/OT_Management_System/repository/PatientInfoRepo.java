package com.ibn.OT_Management_System.repository;

import com.ibn.OT_Management_System.entity.PatientInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientInfoRepo extends JpaRepository<PatientInfo,Long> {

    @Query(value = "SELECT * FROM patient_info WHERE flag IN ('OP', 'OI', 'OC') ORDER BY id DESC", nativeQuery = true)
    List<PatientInfo> getAllPatientInfo();

    @Query(value = "SELECT * FROM patient_info WHERE flag IN ('OI', 'OC') ORDER BY id DESC", nativeQuery = true)
    List<PatientInfo> getAllOIAndOCPatientInfo();

    @Query(value = "SELECT * FROM patient_info WHERE flag IN ('OO') ORDER BY id DESC", nativeQuery = true)
    List<PatientInfo> completeListPatientInfo();

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE patient_info SET flag = :newFlag WHERE id = :id", nativeQuery = true)
//    void updatePatientFlag(@Param("id") Long id, @Param("newFlag") String newFlag);

    @Modifying
    @Transactional
    @Query(value = "UPDATE patient_info SET flag = :newFlag, last_modified_date = :newDate WHERE id = :id", nativeQuery = true)
    void updatePatientFlag(@Param("id") Long id, @Param("newFlag") String newFlag,  @Param("newDate") Date newDate);

    @Query(value = "SELECT * FROM patient_info " +
            "WHERE last_modified_date >= TO_DATE(:fromDate, 'YYYY-MM-DD') " +
            "AND last_modified_date < TO_DATE(:toDate, 'YYYY-MM-DD') + 1 " +
            "AND flag IN ('OO') " +
            "ORDER BY id DESC",
            nativeQuery = true)
    List<PatientInfo> searchPatientInfoByDate(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);



}
