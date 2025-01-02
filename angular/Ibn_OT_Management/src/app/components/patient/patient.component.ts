import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Patient } from '../../models/patient';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-patient',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './patient.component.html',
  styleUrl: './patient.component.css'
})
export class PatientComponent implements OnInit {

  patientName: any;
  gender: any;
  age: any;
  surgeonName: any;
  operationName: any;
  otRoom: any;
  status: any = "Waiting";
  remarks: any;

  patientObj: any;
  getAllPatientData: any;
  getAllRoom:any;
  flag:any;

  constructor(private myservice: AdminService, private router: Router) {
    this.myservice.showAllPatientSerial().subscribe((res: any) => {
      this.getAllPatientData = res.patientInfoList;
      this.flag = this.getAllPatientData.flag;
    });

    this.myservice.showAllRoom().subscribe((res: any) => {
      this.getAllRoom = res;
    });
  }
  ngOnInit(): void {

  }
  // ----------------------- add patient serial ---------------------
  addPatientSerial() {
    this.patientObj = new Patient(this.patientName, this.gender, this.age, this.surgeonName, this.operationName, this.otRoom, this.status, this.remarks);
    this.myservice.addPatientSerial(this.patientObj).subscribe((res: any) => {
      if (res.id != null) {
        alert("Successfully Added");
        this.myservice.showAllPatientSerial().subscribe((res: any) => {
          this.getAllPatientData = res.patientInfoList;
        });

      } else {
        alert("Something is wrong!");
      }

    });
    this.clear();
  }
  // ----------------------- Update patient serial ---------------------
  getPatientName:any;
  getGender:any;
  getAge:any;
  getSurgeonName:any;
  getOperationName:any;
  getOtRoomAssign:any;
  getStatus:any;
  getRemarks:any;
  id: any;

  findById(id: any) {
    this.myservice.getPatientDataById(id).subscribe((res: any) => {
      this.getPatientName = res.patientName;
      this.getGender = res.gender;
      this.getAge = res.age;
      this.getSurgeonName = res.surgeonName;
      this.getOperationName = res.operationName;
      this.otRoom = res.otRoom;
      this.getStatus = res.status;
      this.getRemarks = res.remarks;
      this.id = res.id;
    });
  }

  updatePatientSerial() {
    this.patientObj = new Patient(this.getPatientName, this.getGender, this.getAge, this.getSurgeonName, this.getOperationName, this.otRoom, this.getStatus, this.getRemarks);
    this.myservice.UpdatePatientSerial(this.patientObj, this.id).subscribe((res: any) => {
      alert("Updated Successful");
      this.myservice.showAllPatientSerial().subscribe((res: any) => {
        this.getAllPatientData = res.patientInfoList;
      });
    });
  }

   updateFlag(id: any, newFlag:any) {
      if (confirm("Do you want to go "+newFlag+" ??")) {
        console.log(
          this.myservice.updateFlag(id,newFlag).subscribe((res)=>{
            this.myservice.showAllPatientSerial().subscribe((res: any) => {
              this.getAllPatientData = res.patientInfoList;
            });
          })
        )
      }

    }

  clear() {
    this.patientName = "";
    this.gender = "";
    this.age = "";
    this.surgeonName = "";
    this.operationName = "";
    this.otRoom = "";
    this.remarks = ""
  }


}
