export class Ticket{
    email: String | undefined;
    category: TicketCategoryEnum | undefined;
    reported: String | undefined;
    message: String | undefined;
}

export enum TicketCategoryEnum{
    PUBLISH = 'PUBLISH',
    REPORT = 'REPORT',
    OTHER = 'OTHER'
}