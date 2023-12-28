'use client'
import React, { useEffect, useState } from 'react';
import style from './page.module.css'; 
import {sendRate, getRate, getAvgRate} from '../Services/RatingService';
import { Rate, RatingEnum } from '../objects/Rate';

const Rating  = (props: { targetId: Number; userId: Number }) => {
    const [rating, setRating] = useState(0); 
    const [avgRate, setAvgRate] = useState(0);

    const setRateEnum = (value : number) :RatingEnum =>{
        if(value === 5)
            return RatingEnum.FIVE
        if(value === 4)
            return RatingEnum.FOUR
        if(value === 3)
            return RatingEnum.THREE
        if(value === 2)
            return RatingEnum.TWO
        return RatingEnum.ONE
    }

    useEffect(() => {
        const getDefault = async () => {
        const defaultRating = await getRate(props.userId, props.targetId);
        setRating(defaultRating);
        };
        getDefault();
    }, []);

    useEffect(() => {
        const getAvg = async () => {
        const defaultRating = await getAvgRate(props.targetId);
        setAvgRate(defaultRating)
        };
        getAvg();
    }, []);

    const handleRatingChange = async (value: number) => {
        // create rate object   
        let rating = new Rate();
        rating.userId = props.userId 
        rating.targetId = props.targetId 
        rating.rate = setRateEnum(value)

        const res = await sendRate(rating);
        if(res === "Rate not sent")
            alert("Rate not sent")
        else if(res.status == 200){
            setRating(value);
        }
            
    };

    return (
        <div>
            <p className={style.avgRate}>Average Rat: {avgRate}</p>
            {props.userId !== undefined && props.targetId !== props.userId &&<div className={style.rating}>
                {[5,4,3,2,1].map((value) => (
                    <React.Fragment key={value}>
                    <input
                        type="radio"
                        id={`star${value}`}
                        name="rating"
                        value={value}
                        checked={rating === value}
                        onChange={() => handleRatingChange(value)}
                    />
                    <label htmlFor={`star${value}`}></label>
                    </React.Fragment>
                ))}
            </div>}
        </div>
    );
};

export default Rating;
