import React, { useState, useEffect } from 'react';
import {
    Container,
    Grid,
    Paper,
    Table, TableBody, TableCell, TableContainer, TableHead, TableRow,
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

const ListAgencies = () => {
    const [agencies, setAgencies] = useState([]);

    useEffect(() => {
        fetchAgencies();
    }, []);

    const fetchAgencies = async () => {
        try {
            const response = await agencyApi.listAgencies();
            setAgencies(response);
        } catch (error) {
            console.error('Errore durante il recupero delle agenzie:', error.message);
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
                                        <TableCell>Email</TableCell>
                                        <TableCell>PEC</TableCell>
                                        <TableCell>Telephone 1</TableCell>
                                        <TableCell>Telephone </TableCell>
                                        <TableCell>Website</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {agencies.map((agency) => (
                                        <TableRow
                                            key={agency.id}
                                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                                        >
                                            <TableCell component="th" scope="row">
                                                {agency.name}
                                            </TableCell>
                                            <TableCell>{agency.email}</TableCell>
                                            <TableCell>{agency.pec}</TableCell>
                                            <TableCell>{agency.tel1}</TableCell>
                                            <TableCell>{agency.tel2}</TableCell>
                                            <TableCell>{agency.website}</TableCell>
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

export default ListAgencies;
