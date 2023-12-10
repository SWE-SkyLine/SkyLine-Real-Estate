export class User{
    firstName:String | undefined;
    lastName:String | undefined;
    email:String | undefined;
    password:String | undefined;
    phone_number:String | undefined;
    userType:UserTypeEnum | undefined;
}


export enum UserTypeEnum {
    SUPERADMIN = 'SUPERADMIN',
    ADMIN = 'ADMIN',
    CLIENT = 'CLIENT',
    COMPANY = 'COMPANY',
    AGENT = 'AGENT',
}