
"use client"
import React from 'react';
import retrievePostRequest from '../Services/PostRetrievalService';

const TestComponent: React.FC = () => {
    const handleClick = async () => {
        const res = await retrievePostRequest();
        console.log(res);

    };

    return (
        <div>
            <button onClick={handleClick}>Call API</button>
        </div>
    );
};

export default TestComponent;
