import React, {useEffect, useState} from 'react';
import {
    Button,
    Container,
    Grid,
    Paper,
    TextField,
    Typography
} from "@mui/material";
import {parsePhoneNumberFromString} from "libphonenumber-js";
import styled from "@mui/material/styles/styled";
import theaterApi from "../../services/theaterApi";

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

const AddTheater = () => {
    const [name, setName] = useState('');
    const [city, setCity] = useState('');
    const [tel, setTel] = useState('');
    const [email, setEmail] = useState('');
    const [pec, setPec] = useState('');
    const [website, setWebsite] = useState('');
    const [iva, setIva] = useState('');
    const [uniqueCode, setUniqueCode] = useState('');
    const [recipientCode, setRecipientCode] = useState('');

    const [isFormComplete, setIsFormComplete] = useState(false);


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

    const sendData = async (formData) => {
        try {
            await theaterApi.addTheater(formData);
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
                        <Typography variant="h5" gutterBottom style={{ width: '100%'}}>
                            Registra Teatro
                        </Typography>
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
                    </Grid>
                </StyledForm>
            </StyledPaper>
        </Container>

    );
}

export default AddTheater;