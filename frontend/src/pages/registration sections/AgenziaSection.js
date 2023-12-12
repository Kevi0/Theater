import React, { useState, useEffect } from 'react';
import { parsePhoneNumberFromString } from 'libphonenumber-js'; //Viene usata per il controllo del numero di telefono

import {
  Button,
  Checkbox,
  Container,
  Grid,
  Paper,
  TextField,
  Typography,
  FormControlLabel,
} from '@mui/material';
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

const AgenziaSection = ({ currentSection, onSubmit, onUpdateAgenziaData }) => {
  const [agenzia, setAgenzia] = useState(false); //Se è presente un agenzia si dovranno inserire Referente, tel1, tel2 e email
  
  //Agenzia
  const [nomeAgenzia, setNomeAgenzia] = useState('');
  const [tel1Agenzia, setTel1Agenzia] = useState('');
  const [tel2Agenzia, setTel2Agenzia] = useState('');
  const [emailAgenzia, setEmailAgenzia] = useState('');
  const [referente, setReferente] = useState('');

  //TODO l'artista inserisce la mail del referente non il nome
  
  const [isFormComplete, setIsFormComplete] = useState(false);

  const isEmailValid = (email) => {
        // Regular expression for basic email validation
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        return emailPattern.test(email);
  };

  const isTelValid = (tel1) => {
        const parsedNumber = parsePhoneNumberFromString(tel1, 'ZZ');

        if (parsedNumber && parsedNumber.isValid()) {
        parsedNumber.formatInternational();

        console.log(parsedNumber && parsedNumber.isValid)
        return true;
        }
        else{
        console.log(parsedNumber)
        return false;
        }
    }
  
  useEffect(() => {
    let isComplete = false;

    if(!agenzia) {
      setNomeAgenzia('');
      setEmailAgenzia('');
      setReferente('');
      setTel1Agenzia('');
      setTel2Agenzia('');
    }

    if (agenzia === true)
    {
      if(nomeAgenzia && tel1Agenzia && emailAgenzia && referente){
        isComplete = true;
      }
      else {
        isComplete = false;
      }
    } 
    else {
      if(!nomeAgenzia && !tel1Agenzia && !emailAgenzia && !referente){
        isComplete = true;
      } 
      else {
        isComplete = false;
      }
   }
    setIsFormComplete(isComplete)
  }, [nomeAgenzia, tel1Agenzia, tel2Agenzia, emailAgenzia, referente, agenzia
  
  ]);


  const handleSubmit = (event) => {
    event.preventDefault();

    if(isFormComplete){
      const formData = {
        nomeAgenzia,
        tel1Agenzia,
        tel2Agenzia,
        emailAgenzia,
        referente,
        agenzia
      };
        onUpdateAgenziaData({ agenzia: formData });
      onSubmit();
    }
  };

  return (
    <Container maxWidth="md">
      <StyledPaper>
        <StyledForm onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Typography variant="h5" gutterBottom style={{ width: '100%'}}>
              Dati Agenzia
            </Typography>
            <Grid item xs={12} >
              <FormControlLabel
                control={
                  <Checkbox
                    name="Agenzia"
                    checked={agenzia}
                    onChange={() => setAgenzia((prevAgenzia) => !prevAgenzia)}
                  />
                }
                label="Agenzia"
                fullWidth
              />
            </Grid>
            {agenzia && (
              <>
                <Grid item xs={12} sm={4}>
                  <TextField
                    label="Nome Agenzia"
                    fullWidth
                    name="nomeAgenzia"
                    value={nomeAgenzia}
                    onChange={(event) => setNomeAgenzia(event.target.value)}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label="tel1Agenzia"
                    fullWidth
                    type="tel"
                    name="tel1Agenzia"
                    value={tel1Agenzia}
                    required
                    onChange={(event) => setTel1Agenzia(event.target.value)}
                    error={!isTelValid(tel1Agenzia) && tel1Agenzia.trim() !== ''}
                    helperText={
                      !isTelValid(tel1Agenzia) && tel1Agenzia.trim() !== ''
                        ? 'Inserisci un numero di telefono valido, ricordati di inserire il prefisso es. +39'
                        : ''
                    }
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label="tel2Agenzia"
                    fullWidth
                    type="tel"
                    name="tel2Agenzia"
                    value={tel2Agenzia}
                    onChange={(event) => setTel2Agenzia(event.target.value)}
                    error={!isTelValid(tel2Agenzia) && tel2Agenzia.trim() !== ''}
                    helperText={
                      !isTelValid(tel2Agenzia) && tel2Agenzia.trim() !== ''
                        ? 'Inserisci un numero di telefono valido, ricordati di inserire il prefisso es. +39'
                        : ''
                    }
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    label="Email Agenzia"
                    fullWidth
                    type="email"
                    name="emailAgenzia"
                    value={emailAgenzia}
                    required
                    onChange={(event) => setEmailAgenzia(event.target.value)}
                    error={!isEmailValid(emailAgenzia) && emailAgenzia.trim() !== ''}
                    helperText={
                      !isEmailValid(emailAgenzia) && emailAgenzia.trim() !== ''
                        ? 'Inserisci un indirizzo email valido'
                        : ''
                    }
                  />
                </Grid>
                {/*TODO gestire email Referente*/}
                <Grid item xs={12} sm={4}>
                  <TextField
                    label="Referente"
                    fullWidth
                    name="referente"
                    value={referente}
                    onChange={(event) => setReferente(event.target.value)}
                    required
                  />
                </Grid>                
              </>
            )}

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

export default AgenziaSection;
