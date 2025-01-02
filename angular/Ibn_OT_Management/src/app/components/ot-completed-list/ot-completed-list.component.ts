import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-ot-completed-list',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './ot-completed-list.component.html',
  styleUrl: './ot-completed-list.component.css'
})
export class OtCompletedListComponent implements OnInit {

 fromDate:any;
 toDate:any;

  getAllPatientData: any[] = [];
  
    constructor(private myservice: AdminService, private router: Router) {
      this.myservice.getCompleteList().subscribe((res: any) => {
        this.getAllPatientData = res.patientInfoList;
      });
    }

  ngOnInit(): void {
    
  }

  searchData(){
    this.myservice.searchData(this.fromDate,this.toDate).subscribe((res:any)=>{
      this.getAllPatientData = res.patientInfoList;
    })


  }



}
