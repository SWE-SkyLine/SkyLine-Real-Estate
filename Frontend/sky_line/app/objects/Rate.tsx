export class Rate{
    userId : Number | undefined;
    targetId : Number | undefined;
    rate : RatingEnum | undefined;
}

export enum RatingEnum{
    ONE = "ONE", 
    TWO = "TWO", 
    THREE = "THREE", 
    FOUR = "FOUR", 
    FIVE = "FIVE"
}