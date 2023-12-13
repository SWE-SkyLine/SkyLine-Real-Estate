export class User{
    firstName:String | undefined;
    lastName:String | undefined;
    email:String | undefined;
    password:String | undefined;
    phone_number:String | undefined;
    userRole:userRoleEnum | undefined;
}


export enum userRoleEnum {
    SUPERADMIN = 'SUPERADMIN',
    ADMIN = 'ADMIN',
    CLIENT = 'CLIENT',
    COMPANY = 'COMPANY',
    AGENT = 'AGENT',
}