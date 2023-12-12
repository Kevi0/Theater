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

const ResidenzaSection = ({ currentSection, onSubmit, onUpdateResidenzaData }) => {
  //Residenza
  const [residenza, setResidenza] = useState('');
  const [cap, setCap] = useState('');
  const [cittàResidenza, setCittàResidenza] = useState('');
  const [provinciaResidenza, setProvinciaResidenza] = useState('');
  const [regioneResidenza, setRegioneResidenza] = useState('');
  const [stateResidence, setStateResidence] = useState(''); //Stato selezionato
  const [stateResidenceOpen, setStateResidenceOpen] = useState(false); //Variabile che fa aprire la Select degli Stati per la residenza
  
  const [domicilioSameResidenza, setDomicilioSameResidenza] = useState(false); //Se spuntato le informazioni di domicilio sono le stesse di residenza altrimenti si dovranno reinserire
  
  //Domicilio
  const [domicilio, setDomicilio] = useState('');
  const [capDomicilio, setCapDomicilio] = useState('');
  const [cittàDomicilio, setCittàDomicilio] = useState('');
  const [provinciaDomicilio, setProvinciaDomicilio] = useState('');
  const [regioneDomicilio, setRegioneDomicilio] = useState('');
  const [stateDomicilio, setStateDomicilio] = useState('');
  const [stateDomicilioOpen, setStateDomicilioOpen] = useState(false); //Variabile che fa aprire la Select degli Stati per il domicilio

  const euCountries = [
    'Austria',
    'Belgio',
    'Bulgaria',
    'Cipro',
    'Croazia',
    'Danimarca',
    'Estonia',
    'Finlandia',
    'Francia',
    'Germania',
    'Grecia',
    'Irlanda',
    'Italia',
    'Lettonia',
    'Lituania',
    'Lussemburgo',
    'Malta',
    'Paesi Bassi',
    'Polonia',
    'Portogallo',
    'Repubblica Ceca',
    'Romania',
    'Slovacchia',
    'Slovenia',
    'Spagna',
    'Svezia',
    'Ungheria'
  ];
  const nonEuCountries = [
    'Afghanistan',
    'Albania',
    'Algeria',
    'Andorra',
    'Angola',
    'Antigua e Barbuda',
    'Arabia Saudita',
    'Argentina',
    'Armenia',
    'Australia',
    'Azerbaijan',
    'Bahamas',
    'Bahrain',
    'Bangladesh',
    'Barbados',
    'Belize',
    'Benin',
    'Bhutan',
    'Bielorussia',
    'Birmania (Myanmar)',
    'Bolivia',
    'Bosnia ed Erzegovina',
    'Botswana',
    'Brasile',
    'Brunei',
    'Burkina Faso',
    'Burundi',
    'Cambogia',
    'Camerun',
    'Canada',
    'Capo Verde',
    'Ciad',
    'Cile',
    'Cina',
    'Colombia',
    'Comore',
    'Congo',
    'Corea del Nord',
    'Corea del Sud',
    'Costa d\'Avorio',
    'Costa Rica',
    'Cuba',
    'Dominica',
    'Ecuador',
    'Egitto',
    'El Salvador',
    'Emirati Arabi Uniti',
    'Eritrea',
    'Etiopia',
    'Figi',
    'Filippine',
    'Gabon',
    'Gambia',
    'Georgia',
    'Ghana',
    'Giamaica',
    'Giappone',
    'Gibuti',
    'Giordania',
    'Grenada',
    'Guatemala',
    'Guinea',
    'Guinea Equatoriale',
    'Guinea-Bissau',
    'Guyana',
    'Haiti',
    'Honduras',
    'India',
    'Indonesia',
    'Iran',
    'Iraq',
    'Islanda',
    'Israele',
    'Kazakistan',
    'Kenya',
    'Kirghizistan',
    'Kiribati',
    'Kosovo',
    'Kuwait',
    'Laos',
    'Lesotho',
    'Libano',
    'Liberia',
    'Libia',
    'Liechtenstein',
    'Macedonia del Nord',
    'Madagascar',
    'Malawi',
    'Malaysia',
    'Maldive',
    'Mali',
    'Marocco',
    'Mauritania',
    'Mauritius',
    'Messico',
    'Micronesia',
    'Moldavia',
    'Monaco',
    'Mongolia',
    'Montenegro',
    'Mozambico',
    'Namibia',
    'Nauru',
    'Nepal',
    'Nicaragua',
    'Niger',
    'Nigeria',
    'Norvegia',
    'Nuova Zelanda',
    'Oman',
    'Pakistan',
    'Palau',
    'Panama',
    'Papua Nuova Guinea',
    'Paraguay',
    'Perù',
    'Qatar',
    'Regno Unito',
    'Repubblica Democratica del Congo',
    'Repubblica Dominicana',
    'Ruanda',
    'Russia',
    'Saint Kitts e Nevis',
    'Saint Lucia',
    'Saint Vincent e Grenadine',
    'Samoa',
    'San Marino',
    'Sao Tomé e Principe',
    'Senegal',
    'Serbia',
    'Seychelles',
    'Sierra Leone',
    'Singapore',
    'Siria',
    'Somalia',
    'Sri Lanka',
    'Sudafrica',
    'Sudan',
    'Sudan del Sud',
    'Suriname',
    'Swaziland',
    'Tagikistan',
    'Tanzania',
    'Thailandia',
    'Timor Est',
    'Togo',
    'Tonga',
    'Trinidad e Tobago',
    'Tunisia',
    'Turchia',
    'Turkmenistan',
    'Tuvalu',
    'Ucraina',
    'Uganda',
    'Uruguay',
    'Uzbekistan',
    'Vanuatu',
    'Venezuela',
    'Vietnam',
    'Yemen',
    'Zambia',
    'Zimbabwe'
  ];
  const countries = euCountries.concat(nonEuCountries)
  
  const [isFormComplete, setIsFormComplete] = useState(false);

  const handleCheckboxChange = (event) => {
    setDomicilioSameResidenza(event.target.checked);
    console.log(event.target.checked)
    if (event.target.checked === true) {
      setDomicilio(residenza);
      setCapDomicilio(cap);
      setCittàDomicilio(cittàResidenza);
      setProvinciaDomicilio(provinciaResidenza);
      setRegioneDomicilio(regioneResidenza);
      setStateDomicilio(stateResidence);

      console.log(domicilio)
    }
    else{
      setDomicilio('');
      setCapDomicilio('');
      setCittàDomicilio('');
      setProvinciaDomicilio('');
      setRegioneDomicilio('');
      setStateDomicilio();
    }
  };
  
  useEffect(() => {
    if (
      residenza &&
      cap &&
      cittàResidenza &&
      provinciaResidenza &&
      regioneResidenza &&
      stateResidence &&
      ((!domicilioSameResidenza && domicilio) || domicilioSameResidenza) && // Condizione per il domicilio
      ((!domicilioSameResidenza && capDomicilio) || domicilioSameResidenza) && // Condizione per il capDomicilio
      ((!domicilioSameResidenza && cittàDomicilio) || domicilioSameResidenza) && // Condizione per la cittàDomicilio
      ((!domicilioSameResidenza && provinciaDomicilio) || domicilioSameResidenza) && // Condizione per la provinciaDomicilio
      ((!domicilioSameResidenza && regioneDomicilio) || domicilioSameResidenza) && // Condizione per la regioneDomicilio
      ((!domicilioSameResidenza && stateDomicilio) || domicilioSameResidenza) // Condizione per lo stateDomicilio
    ) {
      setIsFormComplete(true);
    } else {
      setIsFormComplete(false);
    }
  }, [domicilioSameResidenza, residenza, cap, cittàResidenza, provinciaResidenza, regioneResidenza, stateResidence,
    domicilio, capDomicilio, cittàDomicilio, provinciaDomicilio, regioneDomicilio, stateDomicilio
]);


  const handleSubmit = (event) => {
    event.preventDefault();

    if (isFormComplete){
      const formData = {
        residenza,
        cap,
        cittàResidenza,
        provinciaResidenza,
        regioneResidenza,
        stateResidence,
        domicilio, 
        capDomicilio, 
        cittàDomicilio, 
        provinciaDomicilio, 
        regioneDomicilio, 
        stateDomicilio
      };
        onUpdateResidenzaData({ residenza: formData });
      onSubmit();
    } else {
      setIsFormComplete(false);
    }
  };

  //Close Stato Residenza
  const handleResidenceStateClose = () => {
    setStateResidenceOpen(false);
  };
  
  //Open Stato Residenza
  const handleResidenceStateOpen = () => {
    setStateResidenceOpen(true);
  };

  //Close Stato Domiclio
  const handleDomicilioStateClose = () => {
    setStateDomicilioOpen(false);
  };  
  
  //Open Stato Domiclio
  const handleDomicilioStateOpen = () => {
    setStateDomicilioOpen(true);
  };

  return (
    <Container maxWidth="md">
      <StyledPaper>
        <StyledForm onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Typography variant="h5" gutterBottom style={{ width: '100%'}}>
              Residenza
            </Typography>
            <Grid item xs={12} sm={4}>
              <TextField
                label="Residenza"
                fullWidth
                name="residenza"
                value={residenza}
                onChange={(event) => setResidenza(event.target.value)}
                required
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                label="Cap Residenza"
                fullWidth
                name="cap"
                value={cap}
                onChange={(event) => setCap(event.target.value)}
                required
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                label="Città Residenza"
                fullWidth
                name="cittàResidenza"
                value={cittàResidenza}
                onChange={(event) => setCittàResidenza(event.target.value)}
                required
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                label="Provincia Residenza"
                fullWidth
                name="provinciaResidenza"
                value={provinciaResidenza}
                onChange={(event) => setProvinciaResidenza(event.target.value)}
                required
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                label="Regione Residenza"
                fullWidth
                name="regioneResidenza"
                value={regioneResidenza}
                onChange={(event) => setRegioneResidenza(event.target.value)}
                required
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <FormControl sx={{ m: 1, minWidth: '13.7rem', maxWidth: '13.8rem' }} style={{ margin: 'auto' }}>
                <InputLabel id="state-residence-label">Stato di residenza</InputLabel>
                <Select
                  labelId="state-residence-label"
                  id="state-residence-label"
                  open={stateResidenceOpen}
                  onClose={handleResidenceStateClose}
                  onOpen={handleResidenceStateOpen}
                  name="stateResidence"
                  value={stateResidence}
                  label={'Stato Residenza'}
                  onChange={(event) => setStateResidence(event.target.value)}
                  required
                >
                {countries.map((country) => (
                  <MenuItem key={country} value={country}>
                    {country}
                  </MenuItem>
                ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} >
              <FormControlLabel
                control={
                  <Checkbox
                    name="domicilioSameResidenza"
                    checked={domicilioSameResidenza}
                    onChange={handleCheckboxChange}
                  />
                }
                label="Stesso Domicilio"
                fullWidth
              />
            </Grid>
            {!domicilioSameResidenza && (
              <>
                <Grid item xs={12} sm={4}>
                  <TextField
                    label="Domicilio"
                    fullWidth
                    name="domiclio"
                    value={domicilio}
                    onChange={(event) => setDomicilio(event.target.value)}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={4}>
                  <TextField
                    label="Cap Domicilio"
                    fullWidth
                    name="capDomicilio"
                    value={capDomicilio}
                    onChange={(event) => setCapDomicilio(event.target.value)}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={4}>
                  <TextField
                    label="Città Domicilio"
                    fullWidth
                    name="cittàDomicilio"
                    value={cittàDomicilio}
                    onChange={(event) => setCittàDomicilio(event.target.value)}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={4}>
                  <TextField
                    label="Provincia Domicilio"
                    fullWidth
                    name="provinciaDomicilio"
                    value={provinciaDomicilio}
                    onChange={(event) => setProvinciaDomicilio(event.target.value)}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={4}>
                  <TextField
                    label="Regione Domicilio"
                    fullWidth
                    name="regioneDomicilio"
                    value={regioneDomicilio}
                    onChange={(event) => setRegioneDomicilio(event.target.value)}
                    required
                  />
                </Grid>
                <Grid item xs={12} sm={4}>
                  <FormControl sx={{ m: 1, minWidth: '13.7rem', maxWidth: '13.8rem' }} style={{ margin: 'auto' }}>
                    <InputLabel id="state-domicilio-label">Stato di residenza</InputLabel>
                    <Select
                      labelId="state-domicilio-label"
                      id="state-domicilio-label"
                      open={stateDomicilioOpen}
                      onClose={handleDomicilioStateClose}
                      onOpen={handleDomicilioStateOpen}
                      name="stateDomicilio"
                      value={stateDomicilio}
                      label={'Stato Domicilio'}
                      onChange={(event) => setStateDomicilio(event.target.value)}
                      required
                    >
                    {countries.map((country) => (
                      <MenuItem key={country} value={country}>
                        {country}
                      </MenuItem>
                    ))}
                    </Select>
                  </FormControl>
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

export default ResidenzaSection;