import React, { useState, useEffect } from 'react';
import {
    Container,
    Grid,
    Paper,
    Table, TableBody, TableCell, TableContainer, TableHead, TableRow,
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

const ListTheater = () => {
    const [theaters, setTheaters] = useState([]);

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

    return (
        <Container maxWidth="md">
            <StyledPaper>
                <StyledForm>
                    <Grid container spacing={2}>
                        <TableContainer component={Paper}>
                            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Name</TableCell>
                                        <TableCell>City</TableCell>
                                        <TableCell>Email</TableCell>
                                        <TableCell>IVA</TableCell>
                                        <TableCell>PEC</TableCell>
                                        <TableCell>Recipient Code</TableCell>
                                        <TableCell>Telephone</TableCell>
                                        <TableCell>Unique Code</TableCell>
                                        <TableCell>Website</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {theaters.map((theater) => (
                                        <TableRow
                                            key={theater.id}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">
                                                {theater.name}
                                            </TableCell>
                                            <TableCell>{theater.city}</TableCell>
                                            <TableCell>{theater.email}</TableCell>
                                            <TableCell>{theater.iva}</TableCell>
                                            <TableCell>{theater.pec}</TableCell>
                                            <TableCell>{theater.recipientCode}</TableCell>
                                            <TableCell>{theater.tel}</TableCell>
                                            <TableCell>{theater.uniqueCode}</TableCell>
                                            <TableCell>{theater.website}</TableCell>
                                            {/* Aggiungi altre celle secondo i dati del tuo teatro */}
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </Grid>
                </StyledForm>
            </StyledPaper>
        </Container>
    );
};

export default ListTheater;
