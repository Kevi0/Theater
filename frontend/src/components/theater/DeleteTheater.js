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

const DeleteTheater = () => {
    const [theaters, setTheaters] = useState([]);
    const [selectedOption, setSelectedOption] = useState('');

    const handleChange = (event) => {
        setSelectedOption(event.target.value);
    };

    useEffect(() => {
        fetchTheaters();
    }, []);

    const fetchTheaters = async () => {
        try {
            const response = await theaterApi.listTheaters();
            setTheaters(response);
        } catch (error) {
            console.error('Errore durante il recupero dei teatri:', error.message);
        }
    };

    const handleDeleteTheater = async (theaterName) => {
        try {
            const idTheaterToDelete = theaterApi.getIdTheaterByName(theaterName).then(result => {
                theaterApi.deleteTheater(result);
            });

            await fetchTheaters();
        }
        catch (error) {
            console.error('Errore durante l\'eliminazione del teatro:', error.message);
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
                                labelId="theaters-label"
                                id="theaters"
                                value={selectedOption}
                                onChange={handleChange}
                            >
                                {theaters.map((theater) => (
                                    <MenuItem key={theater.name} value={theater.name}>
                                        {theater.name}
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>

                        <Button
                            variant="contained"
                            color="secondary"
                            onClick={() => handleDeleteTheater(selectedOption)}
                        >
                            Elimina
                        </Button>
                    </Grid>
                </StyledForm>
            </StyledPaper>
        </Container>
    );
};

export default DeleteTheater;
