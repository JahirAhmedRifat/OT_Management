import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AddRoom } from '../../models/add-room';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-setup',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './setup.component.html',
  styleUrl: './setup.component.css'
})
export class SetupComponent implements OnInit {

  roomId: any;
  roomName: any;
  roomObj: any;

  getAllData: any = [];
  message: any;

  constructor(private myservice: AdminService, private router: Router) {
    this.myservice.showAllRoom().subscribe((res: any) => {
      this.getAllData = res;
    });
  }


  ngOnInit(): void {

  }

  addroom() {
    this.roomObj = new AddRoom(this.roomId, this.roomName);
    this.myservice.createRoom(this.roomObj).subscribe((res: any) => {
      this.message = res.success;
      if (this.message) {
        alert("Room added Successful");
        this.myservice.showAllRoom().subscribe((x) => {
          this.getAllData = x;
        });
      }else{
        alert("Room Id already exist!");
      }
     
    });
    this.clear();

  }

  clear() {
    this.roomId = '';
    this.roomName = '';
  }
  // ------------------- Room data get & Update --------------------

  getRoomId: any;
  getRoomName: any;
  id: any;

  update(id: any) {
    this.myservice.getDataById(id).subscribe((res: any) => {
      this.getRoomId = res.roomId;
      this.getRoomName = res.roomName;
      this.id = res.id;
    });
  }


  roomUpdate() {
    this.roomObj = new AddRoom(this.getRoomId, this.getRoomName);
    this.myservice.roomUpdate(this.roomObj, this.id).subscribe((res: any) => {
      alert("Updated Successful");
      this.myservice.showAllRoom().subscribe((x) => {
        this.getAllData = x;
      });
    });
  }

  // ------------------------------------- Room data Delete ----------------------------------------
  deleteData(id: any) {
    if (confirm("Do you want to delete this data?")) {
      console.log(
        this.myservice.deleteData(id).subscribe(() => {
          alert("Delete Successful");
          this.myservice.showAllRoom().subscribe((x) => {
            this.getAllData = x;
          });
        })

      )
    } else {
      // --------alert("Not delete")----------;
    }
  }

}
