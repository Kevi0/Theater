import React, { useState, useEffect } from 'react';
import {
    Button,
    Container,
    FormControl,
    Grid,
    InputLabel,
    MenuItem,
    Paper,
    Select,
} from '@mui/material';
import styled from '@mui/system/styled';
import theaterApi from '../../services/theaterApi';
import agencyApi from "../../services/agencyApi";

const StyledPaper = styled(Paper)(({ theme }) => ({
    padding: theme.spacing(6),
    margin: '2rem',
    backgroundColor: '#f5f5f5',
    borderRadius: '8px',
    boxShadow: '0px 4px 8px rgba(0, 0, 0, 0.1)',
}));

const StyledForm = styled('form')(({ theme }) => ({
    '& .MuiTextField-root': {},
    '& .MuiButton-root': {},
}));

const DeleteAgency = () => {
    const [agencies, setAgencies] = useState([]);
    const [selectedOption, setSelectedOption] = useState('');

    const handleChange = (event) => {
        setSelectedOption(event.target.value);
    };

    useEffect(() => {
        fetchAgencies();
    }, []);

    const fetchAgencies = async () => {
        try {
            const response = await agencyApi.listAgencies()
            setAgencies(response);
        } catch (error) {
            console.error('Errore durante il recupero dei teatri:', error.message);
        }
    };

    const handleDeleteAgency = async (agencyName) => {
        try {
            const idAgencyToDelete = agencyApi.getIdAgencyByName(agencyName).then(result => {
                agencyApi.deleteAgency(result);
            });

            await fetchAgencies();
        }
        catch (error) {
            console.error('Errore durante l\'eliminazione dell\'agenzia:', error.message);
        }
    };

    return (
        <Container maxWidth="md">
            <StyledPaper>
                <StyledForm>
                    <Grid container spacing={2}>
                        <FormControl>
                            <InputLabel id="dropdown-label">Seleziona opzione</InputLabel>
                            <Select
                                labelId="agencies-label"
                                id="agencies"
                                value={selectedOption}
                                onChange={handleChange}
                            >
                                {agencies.map((agency) => (
                                    <MenuItem key={agency.name} value={agency.name}>
                                        {agency.name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>

                        <Button
                            variant="contained"
                            color="secondary"
                            onClick={() => handleDeleteAgency(selectedOption)}
                        >
                            Elimina
                        </Button>
                    </Grid>
                </StyledForm>
            </StyledPaper>
        </Container>
    );
};

export default DeleteAgency;