export class Auth{

    username!:string;
    password!:string;
    token!:string;
    message!:string;
    success!:boolean;

    constructor(username: any, password: any) {
        this.username = username;
        this.password = password;
    }

}