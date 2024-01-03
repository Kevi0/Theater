import React, { useState, useEffect } from 'react';
import {
    Container,
    Grid,
    Paper,
    Typography,
    FormControl, InputLabel, Select, MenuItem, Button, TextField
} from '@mui/material';
import styled from '@mui/system/styled';
import theaterApi from '../../services/theaterApi';
import {parsePhoneNumberFromString} from "libphonenumber-js";

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
    const [selectedOption, setSelectedOption] = useState('');
    const [showUpdateForm, setShowUpdateForm] = useState(false);

    const [selectedTheaterId, setSelectedTheaterId] = useState('');
    let selectedTheaterData;

    // State per i dati del form di aggiornamento
    const [name, setName] = useState('');
    const [city, setCity] = useState('');
    const [tel, setTel] = useState('');
    const [email, setEmail] = useState('');
    const [pec, setPec] = useState('');
    const [iva, setIva] = useState('');
    const [website, setWebsite] = useState('');
    const [uniqueCode, setUniqueCode] = useState('');
    const [recipientCode, setRecipientCode] = useState('');
    const [isFormComplete, setIsFormComplete] = useState(false);

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

    const handleChange = (event) => {
        const selectedTheater = event.target.value;
        setSelectedOption(selectedTheater);
    };


    const isNameValid = (name) => {
        const namePattern = /^[a-zA-Z\s]*$/;
        return namePattern.test(name)
    };

    const isIvaValid = (iva) => {

        // Rimuovi spazi e trattini dalla partita IVA
        const cleanedIva = iva.replace(/\s|-/g, '');

        // Verifica se la lunghezza è corretta
        if (cleanedIva.length !== 11) {
            return false;
        }

        // Verifica se tutti i caratteri sono numeri
        if (!/^\d+$/.test(cleanedIva)) {
            return false;
        }

        // Calcola la cifra di controllo
        const checkDigit = parseInt(cleanedIva[10], 10);

        // Calcola la somma ponderata dei primi 10 caratteri
        let sum = 0;
        for (let i = 0; i < 10; i++) {
            let digit = parseInt(cleanedIva[i], 10);

            if (i % 2 === 1) {
                // Moltiplica per due i numeri in posizione dispari
                digit *= 2;

                // Se il risultato è maggiore di 9, sottrai 9
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
        }

        // Verifica se la somma modulo 10 è uguale a zero
        return (sum + checkDigit) % 10 === 0;
    }

    const isTelValid = (tel) => {
        if (typeof tel !== 'string') {
            return false;
        }

        const parsedNumber = parsePhoneNumberFromString(tel, 'ZZ');

        if (parsedNumber && parsedNumber.isValid()) {
            parsedNumber.formatInternational();

            console.log(parsedNumber && parsedNumber.isValid)
            return true;
        }
        else{
            console.log(parsedNumber)
            return false
        }
    }

    const isEmailValid = (email) => {
        // Regular expression for basic email validation
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        return emailPattern.test(email);
    };

    const isUniqueCodeValid = (uniqueCode) => {
        return /^\d{6}$/.test(uniqueCode);
    }

    const isRecipientCodeValid = (recipientCode) => {
        return /^\d{7}$/.test(recipientCode);
    }

    const handleUpdateClick = () => {
        // Nascondi il form di selezione e mostra il form di aggiornamento
        setShowUpdateForm(true);

        // Aggiorna l'ID del teatro selezionato
        selectedTheaterData = theaters.find((theater) => theater.name === selectedOption);

        console.log(selectedTheaterData);

        theaterApi.getIdTheaterByName(selectedOption).then(result => {
            setSelectedTheaterId(result);
        });

        // Seleziona i dati del teatro per il form di aggiornamento
        if (selectedTheaterData) {
            setName(selectedTheaterData.name);
            setCity(selectedTheaterData.city);
            setTel(selectedTheaterData.tel);
            setEmail(selectedTheaterData.email);
            setPec(selectedTheaterData.pec);
            setIva(selectedTheaterData.iva);
            setWebsite(selectedTheaterData.website);
            setUniqueCode(selectedTheaterData.uniqueCode);
            setRecipientCode(selectedTheaterData.recipientCode);
        }
    };

    const sendData = async (formData) => {
        try {
            await theaterApi.updateTheater(selectedTheaterId, formData);
        } catch (error) {
            console.error('Errore durante l\'invio di dati al backend:', error.message);
        }
    };

    useEffect(() => {
            if (
                name &&
                city &&
                tel &&
                email &&
                pec &&
                iva &&
                website &&
                uniqueCode &&
                recipientCode
            ) {
                setIsFormComplete(true);
            } else {
                setIsFormComplete(false);
            }
        }, [name, city, tel, email, pec, iva, website, uniqueCode, recipientCode]
    );

    const handleSubmit = (event) => {
        event.preventDefault();

        if (isFormComplete) {
            const formData = {
                name,
                city,
                tel,
                email,
                pec,
                iva,
                website,
                uniqueCode,
                recipientCode
            };
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
                        <Typography variant="h5" gutterBottom style={{ width: '100%'}}>
                            Modifica Teatro
                        </Typography>
                        {!showUpdateForm && (
                            <>
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
                                <Grid item xs={12}>
                                    <Button
                                        type="submit"
                                        variant="contained"
                                        color="primary"
                                        fullWidth
                                        style={{ borderRadius: '4px' }}
                                        onClick={handleUpdateClick}
                                    >
                                        Aggiorna
                                    </Button>
                                </Grid>
                            </>
                        )}
                        {showUpdateForm && (
                            <>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Nome"
                                        fullWidth
                                        name="name"
                                        value={name}
                                        onChange={(event) => setName(event.target.value)}
                                        required
                                        error={!isNameValid(name)}
                                        helperText={
                                            !isNameValid(name) ? 'Inserisci un nome valido' : ''
                                        }
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="City"
                                        fullWidth
                                        name="city"
                                        value={city}
                                        onChange={(event) => setCity(event.target.value)}
                                        required
                                        error={!isNameValid(city)}
                                        helperText={
                                            !isNameValid(city) ? 'Inserisci una città valida' : ''
                                        }
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Telephone"
                                        fullWidth
                                        name="tel"
                                        value={tel}
                                        onChange={(event) => setTel(event.target.value)}
                                        required
                                        error={!isTelValid(tel)}
                                        helperText={
                                            !isTelValid(tel) ? 'Enter a valid telephone number' : ''
                                        }
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Email"
                                        fullWidth
                                        name="email"
                                        value={email}
                                        onChange={(event) => setEmail(event.target.value)}
                                        required
                                        error={!isEmailValid(email)}
                                        helperText={
                                            !isEmailValid(email) ? 'Enter a valid email address' : ''
                                        }
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="PEC"
                                        fullWidth
                                        name="pec"
                                        value={pec}
                                        onChange={(event) => setPec(event.target.value)}
                                        required
                                        error={!isEmailValid(pec)}
                                        helperText={
                                            !isEmailValid(pec) ? 'Enter a valid PEC address' : ''
                                        }
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="IVA"
                                        fullWidth
                                        name="iva"
                                        value={iva}
                                        onChange={(event) => setIva(event.target.value)}
                                        required
                                        error={!isIvaValid(iva)}
                                        helperText={
                                            !isIvaValid(iva) ? 'Enter a valid iva' : ''
                                        }
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Website"
                                        fullWidth
                                        name="website"
                                        value={website}
                                        onChange={(event) => setWebsite(event.target.value)}
                                        required
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Unique Code"
                                        fullWidth
                                        name="uniqueCode"
                                        value={uniqueCode}
                                        onChange={(event) => setUniqueCode(event.target.value)}
                                        error={!isUniqueCodeValid(uniqueCode)}
                                        helperText={
                                            !isUniqueCodeValid(uniqueCode) ? 'Enter a valid unique code' : ''
                                        }
                                    />
                                </Grid>
                                <Grid item xs={12} sm={4}>
                                    <TextField
                                        label="Recipient Code"
                                        fullWidth
                                        name="recipientCode"
                                        value={recipientCode}
                                        onChange={(event) => setRecipientCode(event.target.value)}
                                        error={!isRecipientCodeValid(recipientCode)}
                                        helperText={
                                            !isRecipientCodeValid(recipientCode) ? 'Enter a valid recipient code' : ''
                                        }
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
                            </>
                        )}
                    </Grid>
                </StyledForm>
            </StyledPaper>
        </Container>
    );
};

export default ListTheater;
