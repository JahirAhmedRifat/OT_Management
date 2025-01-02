export class Patient {
    id: any;
    patientName: any;
    gender: any;
    age: any;
    surgeonName: any;
    operationName: any;
    otRoom: any;
    status: any;
    remarks: any;
    flag: any;
    
    // constructor(id: any, patientName: any, gender: any, age: any, surgeonName: any, operationName: any, otRoomAssign: any, status: any, remarks: any) {
    //     this.id = id;
    //     this.patientName = patientName;
    //     this.gender = gender;
    //     this.age = age;
    //     this.surgeonName = surgeonName;
    //     this.operationName = operationName;
    //     this.otRoomAssign = otRoomAssign;
    //     this.status = status;
    //     this.remarks = remarks;
    // }

    constructor(patientName: any, gender: any, age: any, surgeonName: any, operationName: any, otRoom: any, status: any, remarks: any) {
        this.patientName = patientName;
        this.gender = gender;
        this.age = age;
        this.surgeonName = surgeonName;
        this.operationName = operationName;
        this.otRoom = otRoom;
        this.status = status;
        this.remarks = remarks;
    }

}
