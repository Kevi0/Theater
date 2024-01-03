import React from 'react';

import AddTheater from '../components/theater/AddTheater'
import DeleteTheater from '../components/theater/DeleteTheater'
import UpdateTheater from "../components/theater/UpdateTheater";
import ListTheater from "../components/theater/ListTheater";


const TheaterPage = () => {
    return (
        <div>
            <AddTheater />
            <DeleteTheater />
            <UpdateTheater />
            <ListTheater />
        </div>
    );
};

export default TheaterPage;
