import React, {useEffect, useState} from 'react';
import {
    Paper,
} from "@mui/material";
import AddTheater from '../components/theater/AddTheater'
import DeleteTheater from '../components/theater/DeleteTheater'


const TheaterPage = () => {
    return (
        <div>
            <AddTheater />
            <DeleteTheater />
        </div>
    );

};

export default TheaterPage;
