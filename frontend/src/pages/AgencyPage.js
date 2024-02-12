import React from 'react';
import AddAgency from "../components/agency/AddAgency";
import DeleteAgency from "../components/agency/DeleteAgency";
import ListAgencies from "../components/agency/ListAgencies";
const AgencyPage = () => {
    return (
        <div>
            <AddAgency />
            <DeleteAgency />
            {/*<UpdateTheater />*/}
            <ListAgencies />
        </div>
    );
};

export default AgencyPage;
