import React, { useEffect, useState } from 'react';
import {
    Button,
    Container,
    Grid,
    Paper,
    TextField,
    Typography,
} from "@mui/material";
import styled from "@mui/material/styles/styled";
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import Decimal from 'decimal.js';
import seasonApi from "../../services/seasonApi";

const StyledPaper = styled(Paper)(({ theme }) => ({
    padding: theme.spacing(6),
    margin: '2rem',
    backgroundColor: '#f5f5f5',
    borderRadius: '8px',
    boxShadow: '0px 4px 8px rgba(0, 0, 0, 0.1)',
}));

const StyledForm = styled('form')(({ theme }) => ({
    '& .MuiTextField-root': {
        //marginBottom: theme.spacing(2),
    },
    '& .MuiButton-root': {
        //marginTop: theme.spacing(2),
    },
}));

const AddSeason = ({ children }) => {
    const [title, setTitle] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [idTheater, setIdTheater] = useState('');
    const [artisticDirectorSocialCosts, setArtisticDirectorSocialCosts] = useState('');
    const [artisticDirectorCompensation, setArtisticDirectorCompensation] = useState('');
    const [artisticPersonnelGrossSalary, setArtisticPersonnelGrossSalary] = useState('');
    const [artisticPersonnelSocialCosts, setArtisticPersonnelSocialCosts] = useState('');
    const [technicalPersonnelSocialCosts, setTechnicalPersonnelSocialCosts] = useState('');
    const [technicalPersonnelGrossSalary, setTechnicalPersonnelGrossSalary] = useState('');
    const [administrativePersonnelGrossSalary, setAdministrativePersonnelGrossSalary] = useState('');
    const [administrativePersonnelSocialCosts, setAdministrativePersonnelSocialCosts] = useState('');
    const [artisticPersonnelPerDiem, setArtisticPersonnelPerDiem] = useState('');
    const [technicalPersonnelPerDiem, setTechnicalPersonnelPerDiem] = useState('');
    const [projectRelatedDailyExpenses, setProjectRelatedDailyExpenses] = useState('');
    const [travelTransportAccommodationCosts, setTravelTransportAccommodationCosts] = useState('');

    const [selectedDate, handleDateChange] = useState(new Date());

    const [isFormComplete, setIsFormComplete] = useState(false);

    const sendData = async (formData) => {
        try {
            await seasonApi.addSeason(formData);
        } catch (error) {
            console.error('Errore durante l\'invio di dati al backend:', error.message);
        }
    };

    const checkDecimalPattern = (value) => {
        const decimalRegex = /^[-+]?(\d+(\.\d*)?|\.\d+)([eE][-+]?\d+)?$/;

        try {
            return decimalRegex.test(value);
        } catch (error) {
            console.error('Errore durante la verifica del pattern decimale:', error.message);
            return false;
        }
    }

    useEffect(() => {
        if (
            title &&
            startDate &&
            endDate &&
            artisticDirectorSocialCosts && checkDecimalPattern(artisticDirectorSocialCosts) &&
            artisticDirectorCompensation && checkDecimalPattern(artisticDirectorCompensation) &&
            artisticPersonnelGrossSalary && checkDecimalPattern(artisticPersonnelGrossSalary) &&
            artisticPersonnelSocialCosts && checkDecimalPattern(artisticPersonnelSocialCosts) &&
            technicalPersonnelSocialCosts && checkDecimalPattern(technicalPersonnelSocialCosts) &&
            technicalPersonnelGrossSalary && checkDecimalPattern(technicalPersonnelGrossSalary) &&
            administrativePersonnelGrossSalary && checkDecimalPattern(administrativePersonnelGrossSalary) &&
            administrativePersonnelSocialCosts && checkDecimalPattern(administrativePersonnelSocialCosts) &&
            artisticPersonnelPerDiem && checkDecimalPattern(artisticPersonnelPerDiem) &&
            technicalPersonnelPerDiem && checkDecimalPattern(technicalPersonnelPerDiem) &&
            projectRelatedDailyExpenses && checkDecimalPattern(projectRelatedDailyExpenses) &&
            travelTransportAccommodationCosts && checkDecimalPattern(travelTransportAccommodationCosts)
        ) {
            setIsFormComplete(true);
        } else {
            setIsFormComplete(false);
        }
    }, [
        title,
        startDate,
        endDate,
        idTheater,
        artisticDirectorSocialCosts,
        artisticDirectorCompensation,
        artisticPersonnelGrossSalary,
        artisticPersonnelSocialCosts,
        technicalPersonnelSocialCosts,
        technicalPersonnelGrossSalary,
        administrativePersonnelGrossSalary,
        administrativePersonnelSocialCosts,
        artisticPersonnelPerDiem,
        technicalPersonnelPerDiem,
        projectRelatedDailyExpenses,
        travelTransportAccommodationCosts
    ]);

    const handleSubmit = (event) => {
        event.preventDefault();

        if (isFormComplete) {
            const formData = {
                title,
                startDate,
                endDate,
                artisticDirectorSocialCosts,
                artisticDirectorCompensation,
                artisticPersonnelGrossSalary,
                artisticPersonnelSocialCosts,
                technicalPersonnelSocialCosts,
                technicalPersonnelGrossSalary,
                administrativePersonnelGrossSalary,
                administrativePersonnelSocialCosts,
                artisticPersonnelPerDiem,
                technicalPersonnelPerDiem,
                projectRelatedDailyExpenses,
                travelTransportAccommodationCosts
            };
            console.log(formData);
            sendData(formData);
        } else {
            setIsFormComplete(false);
        }
    };

    return (
        <Container maxWidth="md">
            <StyledPaper>
                <StyledForm onSubmit={handleSubmit}>
                    <Grid container spacing={2}>
                        <Typography variant="h5" gutterBottom style={{ width: '100%' }}>
                            Registra Season
                        </Typography>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Title"
                                fullWidth
                                name="title"
                                value={title}
                                onChange={(event) => setTitle(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                                <DatePicker
                                    label="Inizio Stagione"
                                    value={startDate}
                                    onChange={(date) => setStartDate(date)}
                                />
                            </LocalizationProvider>
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                                <DatePicker
                                    label="Fine Stagione"
                                    value={endDate}
                                    onChange={(date) => setEndDate(date)}
                                />
                            </LocalizationProvider>
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Artistic Director Social Costs"
                                fullWidth
                                name="artisticDirectorSocialCosts"
                                value={artisticDirectorSocialCosts.toString()}
                                onChange={(event) => setArtisticDirectorSocialCosts(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Artistic Director Compensation"
                                fullWidth
                                name="artisticDirectorCompensation"
                                value={artisticDirectorCompensation.toString()}
                                onChange={(event) => setArtisticDirectorCompensation(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Artistic Personnel Gross Salary"
                                fullWidth
                                name="artisticPersonnelGrossSalary"
                                value={artisticPersonnelGrossSalary.toString()}
                                onChange={(event) => setArtisticPersonnelGrossSalary(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Artistic Personnel Social Costs"
                                fullWidth
                                name="artisticPersonnelSocialCosts"
                                value={artisticPersonnelSocialCosts.toString()}
                                onChange={(event) => setArtisticPersonnelSocialCosts(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Technical Personnel Social Costs"
                                fullWidth
                                name="technicalPersonnelSocialCosts"
                                value={technicalPersonnelSocialCosts.toString()}
                                onChange={(event) => setTechnicalPersonnelSocialCosts(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Technical Personnel Gross Salary"
                                fullWidth
                                name="technicalPersonnelGrossSalary"
                                value={technicalPersonnelGrossSalary.toString()}
                                onChange={(event) => setArtisticPersonnelGrossSalary(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Administrative Personnel Gross Salary"
                                fullWidth
                                name="administrativePersonnelGrossSalary"
                                value={administrativePersonnelGrossSalary.toString()}
                                onChange={(event) => setAdministrativePersonnelGrossSalary(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Administrative Personnel Social Costs"
                                fullWidth
                                name="administrativePersonnelSocialCosts"
                                value={administrativePersonnelSocialCosts.toString()}
                                onChange={(event) => setAdministrativePersonnelSocialCosts(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Artistic Personnel Per Diem"
                                fullWidth
                                name="artisticPersonnelPerDiem"
                                value={artisticPersonnelPerDiem.toString()}
                                onChange={(event) => setArtisticPersonnelPerDiem(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Technical Personnel Per Diem"
                                fullWidth
                                name="technicalPersonnelPerDiem"
                                value={technicalPersonnelPerDiem.toString()}
                                onChange={(event) => setTechnicalPersonnelPerDiem(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Project Related Daily Expenses"
                                fullWidth
                                name="projectRelatedDailyExpenses"
                                value={projectRelatedDailyExpenses.toString()}
                                onChange={(event) => setProjectRelatedDailyExpenses(event.target.value)}
                                required
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Travel Transport Accommodation Costs"
                                fullWidth
                                name="travelTransportAccommodationCosts"
                                value={travelTransportAccommodationCosts.toString()}
                                onChange={(event) => setTravelTransportAccommodationCosts(event.target.value)}
                                required
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <Button
                                type="submit"
                                variant="contained"
                                color="primary"
                                disabled={!isFormComplete}
                                fullWidth
                                style={{ borderRadius: '4px' }}
                            >
                                Registrati
                            </Button>
                        </Grid>
                    </Grid>
                </StyledForm>
            </StyledPaper>
        </Container>
    );
};

export default AddSeason;
