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
import agencyApi from "../../services/agencyApi";

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

const AddAgency = () => {
    const [name, setName] = useState('');
    const [tel1, setTel1] = useState('');
    const [tel2, setTel2] = useState('');
    const [email, setEmail] = useState('');
    const [pec, setPec] = useState('');
    const [website, setWebsite] = useState('');

    const [isFormComplete, setIsFormComplete] = useState(false);


    const isNameValid = (name) => {
        const namePattern = /^[a-zA-Z\s]*$/;
        return namePattern.test(name)
    };

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

    const sendData = async (formData) => {
        try {
            await agencyApi.addAgency(formData);
        } catch (error) {
            console.error('Errore durante l\'invio di dati al backend:', error.message);
        }
    };

    useEffect(() => {
            if (
                name &&
                tel1 &&
                email &&
                pec &&
                website
            ) {
                setIsFormComplete(true);
            } else {
                setIsFormComplete(false);
            }
        }, [name, tel1, tel2, email, pec, website]
    );

    const handleSubmit = (event) => {
        event.preventDefault();

        if (isFormComplete) {
            const formData = {
                name,
                tel1,
                email,
                pec,
                website,
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
                            Registra Agenzia
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
                                label="Telephone"
                                fullWidth
                                name="tel1"
                                value={tel1}
                                onChange={(event) => setTel1(event.target.value)}
                                required
                                error={!isTelValid(tel1)}
                                helperText={
                                    !isTelValid(tel1) ? 'Enter a valid telephone number' : ''
                                }
                            />
                        </Grid>
                        {/*TODO Controllare che tel2 sia non required ma comuneuq darla il pasto ala funzione di controllo*/}
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Telephone"
                                fullWidth
                                name="tel2"
                                value={tel2}
                                onChange={(event) => setTel2(event.target.value)}
                                /*error={!isTelValid(tel2)}
                                helperText={
                                    !isTelValid(tel2) ? 'Enter a valid telephone number' : ''
                                }*/
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
                                label="Website"
                                fullWidth
                                name="website"
                                value={website}
                                onChange={(event) => setWebsite(event.target.value)}
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
}

export default AddAgency;