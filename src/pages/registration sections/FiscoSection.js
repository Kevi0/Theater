import React, { useState, useEffect } from 'react';
import {
    Button,
    Checkbox,
    Container,
    Grid,
    InputLabel,
    Paper,
    Select,
    TextField,
    Typography,
    FormControl,
    FormControlLabel,
    MenuItem,
} from '@mui/material';

import './css/style.css'
import styled from '@mui/material/styles/styled';

const StyledPaper = styled(Paper)(({ theme }) => ({
    padding: theme.spacing(6),
    //maxWidth: '600px',
    margin: '2rem',
    //marginTop: theme.spacing(3),
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

const FiscoSection = ({ currentSection, onSubmit, onUpdateFiscoData}) => {
    //TODO funzione validazione matricola Inps
    //Dati fiscali
    const [fiscalCode, setFiscalCode] = useState('');
    const [isIva, setIsIva] = useState(false);
    const [regime, setRegime] = useState('');
    const [regimeOpen, setRegimeOpen] = React.useState(false);
    const [nIva, setNIva] = useState('');
    const [occupation, setOccupation] = useState('');
    const [occupationOpen, setOccupationOpen] = React.useState(false);
    const [employmentAllowance, setEmploymentAllowance] = useState('');
    const [employmentAllowanceOpen, setEmploymentAllowanceOpen] = useState(false);
    const [expiration, setExpiration] = useState('');
    const [employer, setEmployer] = useState('');
    const [revenue, setRevenue] = useState('');
    const [revenueOpen, setRevenueOpen] = useState(false);
    const [degree, setDegree] = useState('');
    const [age, setAge] = useState('');
    const [ageOpen, setAgeOpen] = useState(false);
    const [matrInps, setMatrInps] = useState('');
    const [iscrizioneInps, setIscrizioneInps] = useState('');
    const [iscrizioneOpen, setIscrizioneOpen] = useState(false);

    const [isFormComplete, setIsFormComplete] = useState(false);

    const isFiscalCodeValid = (fiscalCode) => {
        const fiscalCodeRegex = /^(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$/i;

        return fiscalCodeRegex.test(fiscalCode);
    }

    const isIvaValid = (nIva) => {
        const ivaRegex = /^[a-zA-Z0-9]*$/;

        return ivaRegex.test(nIva);
    }

    const isExp = (expiration) => {
        const currentDate = new Date();
        const currentExp = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
        const inputDate = new Date(expiration);

        return inputDate >= currentExp;
    }

    useEffect(() => {
        if (
            fiscalCode && isFiscalCodeValid(fiscalCode) &&
            ((isIva && nIva && isIvaValid(nIva) && regime) ||
            (!isIva)) &&
            ((occupation === 'Nessuna Occupazione' && employmentAllowance) ||
            (occupation === 'Contratto di Lavoro a tempo determinato' && expiration && isExp(expiration) && employer && revenue) ||
            (occupation === 'Contratto di Lavoro a tempo indeterminato' && employer && revenue) ||
            (occupation === 'Giovane fino a 18 anni') ||
            (occupation === 'Studente fino a 25 anni' && degree) ||
            (occupation === 'Pensionato' && age)) &&
            iscrizioneInps
        ) {
            setIsFormComplete(true);
            console.log('Form complete')
        } else {
            setIsFormComplete(false);
            console.log('Form incomplete')
        }
    }, [
        fiscalCode, isIva, nIva, regime, occupation, employmentAllowance, expiration, employer, degree, age, revenue, iscrizioneInps, matrInps
    ]);

    const handleSubmit = (event) => {
        event.preventDefault();

        if (isFormComplete) {
            const formData = {
                fiscalCode,
                isIva,
                regime,
                nIva,
                occupation,
                employmentAllowance,
                expiration,
                employer,
                degree,
                age,
                revenue,
                iscrizioneInps,
                matrInps
            };
            onUpdateFiscoData({ fisco: formData});
            onSubmit();
        } else {
            setIsFormComplete(false);
        }
    };

    //Close Select Regime
    const handleRegimeClose = () => {
        setRegimeOpen(false);
    };

    //Open Select Regime
    const handleRegimeOpen = () => {
        setRegimeOpen(true);
    };

    //Close Select Occupation
    const handleCloseOccupation = () => {
        setOccupationOpen(false);
    }

    //Open Select Occupation
    const handleOpenOccupation = () => {
        setOccupationOpen(true);
    }

    //Close Select Employment Allowance
    const handleCloseEmploymentAllowance = () => {
        setEmploymentAllowanceOpen(false);
    }

    //Open Select Employment Allowance
    const handleOpenEmploymentAllowance = () => {
        setEmploymentAllowanceOpen(true);
    }

    const handleRevenueClose = () => {
        setRevenueOpen(false);
    }

    const handleRevenueOpen = () => {
        setRevenueOpen(true);
    }

    const handleAgeClose = () => {
        setAgeOpen(false);
    }

    const handleAgeOpen = () => {
        setAgeOpen(true);
    }

    const handleIscrizioneClose = () => {
        setIscrizioneOpen(false);
    }

    const handleIscrizioneOpen = () => {
        setIscrizioneOpen(true);
    }

    return (
        <Container maxWidth="md">
            <StyledPaper>
                <StyledForm onSubmit={handleSubmit}>
                    <Grid container spacing={2}>
                        <Typography variant="h5" gutterBottom style={{ width: '100%'}}>
                            Dati Fiscali
                        </Typography>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Codice Fiscale"
                                fullWidth
                                name="fiscalCode"
                                value={fiscalCode}
                                onChange={(event) => setFiscalCode(event.target.value)}
                                required
                                error={!isFiscalCodeValid(fiscalCode)}
                                helperText={
                                    !isFiscalCodeValid(fiscalCode) ? 'Inserisci un codice fiscale valido' : ''
                                }
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <FormControlLabel
                                control={
                                    <Checkbox
                                        name="isIva"
                                        checked={isIva}
                                        onChange={(event) => setIsIva(event.target.checked)}
                                    />
                                }
                                label="Iva"
                            />
                        </Grid>
                        {isIva && (
                            <>
                                <Grid item xs={12} sm={4}>
                                    <FormControl sx={{ m: 1, minWidth: '13.7rem', maxWidth: '13.8rem' }} style={{ margin: 'auto' }}>
                                        <InputLabel id="regime-label">Regime Economico</InputLabel>
                                        <Select
                                            labelId="regime"
                                            id="regime-id"
                                            open={regimeOpen}
                                            onClose={handleRegimeClose}
                                            onOpen={handleRegimeOpen}
                                            name="regime"
                                            value={regime}
                                            label={'Regime Economico'}
                                            onChange={(event) => setRegime(event.target.value)}
                                            required
                                        >
                                            <MenuItem value={'Regime Semplificato'}>Regime Semplificato</MenuItem>
                                            <MenuItem value={'Regime Forfettario'}>Regime Forfettario</MenuItem>
                                        </Select>
                                    </FormControl>
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="N. IVA"
                                        fullWidth
                                        name="nIva"
                                        value={nIva}
                                        onChange={(event) => setNIva(event.target.value)}
                                        required
                                        error={!isIvaValid(nIva)}
                                        helperText={!isIvaValid(nIva) ? 'Inserisci un numero di partita IVA valido' : ''}
                                    />
                                </Grid>
                            </>
                        )}
                        <Grid item xs={12} sm={4}>
                            <FormControl sx={{ m: 1, minWidth: '13.7rem', maxWidth: '13.8rem' }} style={{ margin: 'auto' }}>
                                <InputLabel id="Occupazione Attuale">Occupazione Attuale</InputLabel>
                                <Select
                                    labelId="occupazione-attuale"
                                    id="occupazione-attuale-id"
                                    open={occupationOpen}
                                    onClose={handleCloseOccupation}
                                    onOpen={handleOpenOccupation}
                                    name="occupazione"
                                    value={occupation}
                                    label={'Occupazione Attuale'}
                                    onChange={(event) => setOccupation(event.target.value)}
                                    required
                                >
                                    <MenuItem value={'Nessuna Occupazione'}>Nessuna Occupazione</MenuItem>
                                    <MenuItem value={'Contratto di Lavoro a tempo determinato'}>Contratto di Lavoro a tempo determinato</MenuItem>
                                    <MenuItem value={'Contratto di Lavoro a tempo indeterminato'}>Contratto di Lavoro a tempo indeterminato</MenuItem>
                                    <MenuItem value={'Giovane fino a 18 anni'}>Giovane fino a 18 anni</MenuItem>
                                    <MenuItem value={'Studente fino a 25 anni'}>Studente fino a 25 anni</MenuItem>
                                    <MenuItem value={'Pensionato'}>Pensionato</MenuItem>
                                </Select>
                            </FormControl>
                        </Grid>
                        {occupation === 'Nessuna Occupazione'  && (
                            <>
                                <Grid item xs={12} sm={4}>
                                    <FormControl sx={{ width: '100%', mb: 2 }}>
                                        <InputLabel id="Indennità di Disoccupazione">Indennità di Disoccupazione</InputLabel>
                                        <Select
                                            labelId="indennita-label"
                                            id="indennita-select"
                                            fullWidth
                                            open={employmentAllowanceOpen}
                                            onClose={handleCloseEmploymentAllowance}
                                            onOpen={handleOpenEmploymentAllowance}
                                            name="indennita"
                                            value={employmentAllowance}
                                            label="Indennita"
                                            onChange={(event) => setEmploymentAllowance(event.target.value)}
                                            required
                                        >
                                            <MenuItem value={'si'}>Si</MenuItem>
                                            <MenuItem value={'no'}>No</MenuItem>
                                        </Select>
                                    </FormControl>
                                </Grid>
                            </>
                        )}
                        {occupation === 'Contratto di Lavoro a tempo determinato' && (
                            <>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Scadenza Determinato"
                                        fullWidth
                                        type="date"
                                        value={expiration}
                                        error={!isExp(expiration)}
                                        helperText={
                                            !isExp(expiration) ? 'Inserisci una data di scadenza valida' : ' '
                                        }
                                        required
                                        placeholder={''}
                                        InputLabelProps={{
                                            shrink: true,
                                        }}
                                        InputProps={{
                                            value: expiration, // This will control the value of the input
                                            onChange: (event) => setExpiration(event.target.value),
                                            placeholder: ',', // This will remove any placeholder text
                                        }}
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Datore di Lavoro"
                                        fullWidth
                                        name="datoreLavoro"
                                        value={employer}
                                        onChange={(event) => setEmployer(event.target.value)}
                                        required
                                    />
                                </Grid>
                            </>
                        )}
                        {occupation === 'Contratto di Lavoro a tempo indeterminato' && (
                            <>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Datore di Lavoro"
                                        fullWidth
                                        name="datoreLavoro"
                                        value={employer}
                                        onChange={(event) => setEmployer(event.target.value)}
                                        required
                                    />
                                </Grid>
                            </>
                        )}
                        {(occupation === 'Contratto di Lavoro a tempo indeterminato' || occupation === 'Contratto di Lavoro a tempo determinato') && (
                            <>
                                <Grid item xs={12} sm={4}>
                                    <FormControl sx={{ width: '100%', mb: 2 }}>
                                        <InputLabel id="revenue-label" >
                                            Fatturato
                                        </InputLabel>
                                        <Select
                                            labelId="fatturato-label"
                                            id="fatturato-select"
                                            fullWidth
                                            open={revenueOpen}
                                            onClose={handleRevenueClose}
                                            onOpen={handleRevenueOpen}
                                            name="fatturato"
                                            value={revenue}
                                            label="Fatturato"
                                            onChange={(event) => setRevenue(event.target.value)}
                                            required
                                        >
                                            <MenuItem value={'>= 5000'}> >= 5000</MenuItem>
                                            <MenuItem value={'< 5000'}> &lt; 5000</MenuItem>
                                        </Select>
                                    </FormControl>
                                </Grid>
                            </>
                        )}
                        {(occupation === 'Studente fino a 25 anni') && (
                            <>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Corso di Studi"
                                        fullWidth
                                        name="degree"
                                        value={degree}
                                        onChange={(event) => setDegree(event.target.value)}
                                        required
                                    />
                                </Grid>
                            </>
                        )}
                        {(occupation === 'Pensionato') && (
                            <>
                                <Grid item xs={12} sm={4}>
                                    <FormControl sx={{ width: '100%', mb: 2 }}>
                                        <InputLabel id="age-label" >
                                            Anni
                                        </InputLabel>
                                        <Select
                                            labelId="age-label"
                                            id="age-id"
                                            fullWidth
                                            open={ageOpen}
                                            onClose={handleAgeClose}
                                            onOpen={handleAgeOpen}
                                            name="age"
                                            value={age}
                                            label="Anni"
                                            onChange={(event) => setAge(event.target.value)}
                                            required
                                        >
                                            <MenuItem value={'>= 65>'}> > 65 </MenuItem>
                                            <MenuItem value={'< 65'}> &lt; 65 </MenuItem>
                                        </Select>
                                    </FormControl>
                                </Grid>
                            </>
                        )}
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Matricola Inps"
                                fullWidth
                                name="matrInps"
                                value={matrInps}
                                onChange={(event) => setMatrInps(event.target.value)}
                           />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <FormControl sx={{ width: '100%', mb: 2 }}>
                                <InputLabel id="iscrizione-label" >
                                    Iscrizione Inps
                                </InputLabel>
                                <Select
                                    labelId="iscrizione-label"
                                    id="iscrizione-id"
                                    fullWidth
                                    open={iscrizioneOpen}
                                    onClose={handleIscrizioneClose}
                                    onOpen={handleIscrizioneOpen}
                                    name="iscrizione"
                                    value={iscrizioneInps}
                                    label="Iscrizione Inps"
                                    onChange={(event) => setIscrizioneInps(event.target.value)}
                                    required
                                >
                                    <MenuItem value={'>= 95>'}> > 95 </MenuItem>
                                    <MenuItem value={'< 95'}> &lt; 95 </MenuItem>
                                </Select>
                            </FormControl>
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
}

export default FiscoSection;