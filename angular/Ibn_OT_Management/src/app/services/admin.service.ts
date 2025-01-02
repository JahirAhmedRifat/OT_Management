import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AddRoom } from '../models/add-room';
import { Observable } from 'rxjs';
import { Patient } from '../models/patient';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  //dev server
  // private roomApiUrl = "http://192.168.0.73:8090/ot_management/api/room";
  // private patientApiUrl = "http://192.168.0.73:8090/ot_management/api/patient";

   //ATI local server
  //  private roomApiUrl = "http://192.168.0.169:8080/ot_management/api/room";
  //  private patientApiUrl = "http://192.168.0.169:8080/ot_management/api/patient";

  //live server
  private roomApiUrl = "http://202.51.176.30:8080/ot_management/api/room";
  private patientApiUrl = "http://202.51.176.30:8080/ot_management/api/patient";

  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) {

  }

  // ------------------ Room Add --------------- 
  createRoom(roomObj: AddRoom): Observable<AddRoom> {
    return this.http.post<AddRoom>(this.roomApiUrl + "/create", roomObj);
  }
  // ----------------------- show all added Room----------------------
  showAllRoom(): Observable<AddRoom> {
    return this.http.get<AddRoom>(this.roomApiUrl + "/getAll");
  }
  // ------------- find by RoomId & get -------------------
  getDataById(id: any): Observable<any> {
    return this.http.get<AddRoom>(this.roomApiUrl + "/" + id);
  }
  // ------------------ Update Room --------------- 
  roomUpdate(roomObj: AddRoom, id: any): Observable<AddRoom> {
    return this.http.put<AddRoom>(this.roomApiUrl + "/updated/" + id, roomObj);
  }
  // ------------- delete Room added data -------------------
  deleteData(id: any): Observable<AddRoom> {
    return this.http.delete<AddRoom>(this.roomApiUrl + "/delete/" + id);
  }



  // ----------------------------- Patient part -------------



  // ------------------ Add Patient Serial--------------- 
  addPatientSerial(patientObj: Patient): Observable<Patient> {
    return this.http.post<Patient>(this.patientApiUrl + "/create", patientObj);
  }
  // ----------------------- show all Patient Serial Status ---------------------
  showAllPatientSerial(): Observable<Patient> {
    return this.http.get<Patient>(this.patientApiUrl + "/getAll");
  }
  // ------------- find patient data -------------------
  getPatientDataById(id: any): Observable<any> {
    return this.http.get<AddRoom>(this.patientApiUrl + "/" + id);
  }
  // ------------------ Update patient serial ---------------  
  UpdatePatientSerial(patientObj: Patient, id: any): Observable<Patient> {
    return this.http.put<Patient>(this.patientApiUrl + "/updated/" + id, patientObj);
  }
  // ----------------------- show all Patient Serial Status ---------------------
  getCompleteList(): Observable<Patient> {
    return this.http.get<Patient>(this.patientApiUrl + "/complete");
  }

  // ------------------ Update patient serial ---------------  
  updateFlag(id: any, newFlag: any) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(this.patientApiUrl + "/updateFlag/" + id + "?newFlag=" + newFlag, { headers, withCredentials: true });
  }

  searchData(fromDate:any,toDate:any){
    return this.http.get(this.patientApiUrl + "/byDate?fromDate="+fromDate+"&toDate="+toDate);
  }


}
