import React, { useState, useEffect } from 'react';

import {
  Button,
  Container,
  Grid,
  InputLabel,
  Paper,
  Select,
  Typography,
  FormControl,
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

const CittadinanzaSection = ({ currentSection, onSubmit, onUpdateCittadinanzaData }) => {
  //Cittadinanza
  const [euCitizen, setEuCitizen] = useState(false); //Appartiene all'Unione Europea?
  const [euCountryOpen, setEuCountryOpen] = useState(false); //Variabile che fa aprire la Select dei Paesi EU
  const [nonEuCountryOpen, setNonEuCountryOpen] = useState(false); //Variabile che fa aprire la Select dei Paesi non EU
  const [euCountry, setEuCountry] = useState(false); //Memorizza tutti i Paesi EU
  const [nonEuCountry, setNonEuCountry] = useState(false); //Memorizza tutti i Paesi non EU
  const [euCitizenOpen, setEuCitizenOpen] = useState(false);
  
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
  //const countries = euCountries.concat(nonEuCountries)

  const [isFormComplete, setIsFormComplete] = useState(false);

  useEffect(() => {
    if (
      (euCountry || nonEuCountry)
    ) {
      setIsFormComplete(true);
    } else {
      setIsFormComplete(false);
    }
  }, [euCountry, nonEuCountry
]);


  const handleSubmit = (event) => {
    event.preventDefault();

    if (isFormComplete) {
      const formData = {
        euCountry,
        nonEuCountry,
      };
      onUpdateCittadinanzaData({ cittadinanza: formData });
      onSubmit();
    } else {
      setIsFormComplete(false);
    }
  };

  // Funzione per gestire l'apertura della select EU Citizen
  const handleEuCitizenOpen = () => {
    setEuCitizenOpen(true);
  };

  // Funzione per gestire la chiusura della select EU Citizen
  const handleEuCitizenClose = () => {
    setEuCitizenOpen(false);
  };

  //Close Eu Country
  const handleEuCountryClose = () => {
    setEuCountryOpen(false);
  };

  //Open Eu Country
  const handleEuCountryOpen = () => {
    setEuCountryOpen(true);
  };

  //Close Non Eu Country
  const handleNonEuCountryClose = () => {
    setNonEuCountryOpen(false);
  };
  
  //Open Non Eu Country
  const handleNonEuCountryOpen = () => {
    setNonEuCountryOpen(true);
  };

  return (
    <Container maxWidth="md">
      <StyledPaper >
        <StyledForm onSubmit={handleSubmit} style={{ display: 'flex' }}>   
            <Grid container spacing={2} alignContent={"center"}>
                <Typography variant="h5" gutterBottom style={{ width: '100%'}}>
                    Cittadinanza
                </Typography>
                <Grid item xs={12} sm={4}>  
                    <FormControl sx={{ m: 1, minWidth: '13.7rem' }} style={{ margin: 'auto' }}>
                    <InputLabel id="eu-citizen-label">Appartiene alla Comunità Europea?</InputLabel>
                    <Select
                        labelId="eu-citizen-label"
                        id="eu-citizen-select"
                        open={euCitizenOpen}
                        onClose={handleEuCitizenClose}
                        onOpen={handleEuCitizenOpen}
                        name="euCitizen"
                        value={euCitizen}
                        label={'Appartiene alla Comunità Europea?'}
                        onChange={(event) => setEuCitizen(event.target.value)}
                        required
                    >
                        <MenuItem value={'Si'}>Si</MenuItem>
                        <MenuItem value={'No'}>No</MenuItem>
                    </Select>
                    </FormControl>
                </Grid>
                {euCitizen === 'Si' && (
                    <Grid item xs={12} sm={5}>
                    <FormControl sx={{ m: 1, minWidth: '13.7rem' }} style={{ margin: 'auto' }}>
                        <InputLabel id="eu-country-label">Stato dell'Unione Europea</InputLabel>
                        <Select
                        labelId="eu-country-label"
                        id="eu-country-select"
                        open={euCountryOpen}
                        onClose={handleEuCountryClose}
                        onOpen={handleEuCountryOpen}
                        name="euCountry"
                        value={euCountry}
                        label={"Stato dell'Unione Europea"}
                        onChange={(event) => setEuCountry(event.target.value)}
                        required
                        >
                        {euCountries.map((country) => (
                            <MenuItem key={country} value={country}>
                            {country}
                            </MenuItem>
                        ))}
                        </Select>
                    </FormControl>
                    </Grid>
                )}
                {euCitizen === 'No' && (
                    <Grid item xs={12} sm={4}>
                    <FormControl sx={{ m: 1, minWidth: '13.7rem' }} style={{ margin: 'auto' }}>
                        <InputLabel id="eu-country-label">Stato fuori dall'Unione Europea</InputLabel>
                        <Select
                            id="eu-non-country-select"
                            open={nonEuCountryOpen}
                            onClose={handleNonEuCountryClose}
                            onOpen={handleNonEuCountryOpen}
                            value={nonEuCountry}
                            label="Stato fuori dall'Unione Europea"
                            fullWidth
                            name="nonEuCountry"
                            onChange={(event) => setNonEuCountry(event.target.value)}
                            required
                        >
                            {nonEuCountries.map((country) => (
                            <MenuItem key={country} value={country}>
                                {country}
                            </MenuItem>
                            ))}
                        </Select>
                    </FormControl> 
                    </Grid>
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

export default CittadinanzaSection;