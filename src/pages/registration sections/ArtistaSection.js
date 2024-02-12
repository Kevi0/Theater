import React, { useState, useEffect } from 'react';

import {
  Button,
  Container,
  Grid,
  InputLabel,
  Paper,
  Select,
  TextField,
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

const ArtistaSection = ({ currentSection, onSubmit, onUpdateArtistaData }) => {
    //Artista
    const [nomeArte, setNomeArte] = useState('');
    const [stateTipologiaOpen, setStateTipologiaOpen] = useState(false);// Variabile che fa aprire la Select di tutte le tipologie
    const [tipologia, setTipologia] = useState(''); //Tipologia selezionata
    
    const [showStrumento, setShowStrumento] = useState(false);
    const [strumentoValue, setStrumentoValue] = useState(''); //Strumento selezionato

    const [showVoice, setShowVoice] = useState(false);
    const [voiceValue, setVoiceValue] = useState(''); //Voce selezionata

    const tipologieArtista = [
        "011 - Artisti lirici",
        "012 - Cantanti",
        "013 - Coristi e vocalisti",
        "014 - Maestri del coro, assistenti, aiuti (suggeritori del coro)",
        "021 - Attori di prosa (Mimi)",
        "022 - Attori cinematografici (*) e di audiovisivi (*)",
        "023 - Attori doppiatori (*)",
        "024 - Attori di operetta",
        "025 - Artisti di rivista, varieta' ed attrazioni (comici, fantasisti, soubrette)",
        "026 - Artisti del circo (acrobati, clown, domatori, fantasisti)",
        "027 - Attori di fotoromanzi (*)",
        "028 - Suggeritori teatrali, cinematografici (*) e di audiovisivi (*)",
        "029 - Generici e figuranti speciali (*)",
        "031 - Presentatori",
        "032 - Disc-jockey",
        "033 - Animatori in strutture ricettive connesse all'attivita' turistica",
        "041 - Registi teatrali, cinematografici (*) e di audiovisivi (*)",
        "042 - Aiuto registi teatrali, cinematografici (*) e di audiovisivi (*)",
        "043 - Sceneggiatori teatrali, cinematografici (*) e di audiovisivi (*)",
        "044 - Dialoghisti ed adattatori",
        "045 - Direttori della fotografia, light designer (*)",
        "046 - Soggettisti",
        "047 - Video-assist",
        "051 - Direttori di produzione",
        "052 - Ispettori di produzione",
        "053 - Segretari di produzione",
        "054 - Segretari di edizione",
        "055 - Cassieri di produzione",
        "056 - Organizzatori generali, location manager",
        "057 - Responsabili di edizione della produzione cinematografica e televisiva",
        "058 - Casting director",
        "059 - Documentalisti audiovisivi",
        "061 - Direttori di scena (*)",
        "062 - Direttori di doppiaggio (*)",
        "063 - Assistenti di scena e di doppiaggio (*)",
        "071 - Direttori d'orchestra",
        "072 - Sostituti direttori d'orchestra",
        "073 - Maestri suggeritori (maestri collaboratori)",
        "074 - Maestri di banda",
        "075 - Compositori",
        "081 - Concertisti e solisti",
        "082 - Professori d'orchestra",
        "083 - Orchestrali anche di musica leggera",
        "084 - Bandisti",
        "085 - Consulenti assistenti musicali",
        "091 - Coreografi (*) ed assistenti coreografi (*)",
        "092 - Ballerini e tersicorei",
        "093 - Indossatori",
        "094 - Figuranti lirici",
        "095 - Figuranti di sala",
        "096 - Fotomodelli",
        "097 - Cubisti",
        "098 - Spogliarellisti",
        "101 - Amministratori di formazione artistica",
        "102 - Amministratori di produzione cinematografica e audiovisiva",
        "103 - Organizzatori teatrali",
        "104 - Amministratori e segretari di compagnie teatrali",
        "111 - Tecnici del montaggio e del suono della produzione cinematografica",
        "112 - Tecnici del montaggio e del suono del teatro",
        "113 - Tecnici del montaggio e del suono di audiovisivi (*)",
        "114 - Tecnici del montaggio e del suono di altri settori dello spettacolo",
        "115 - Tecnici dello sviluppo e stampa, luci, scena ed altri tecnici della produzione cinematografica",
        "116 - Tecnici delle luci, scena ed altri tecnici del teatro",
        "117 - Tecnici delle luci, scena ed altri tecnici audiovisivi (*)",
        "118 - Tecnici dello sviluppo e stampa, luci, scena ed altri tecnici di fotoromanzi",
        "119 - Tecnici addetti alle manifestazioni di moda",
        "121 - Operatori di ripresa cinematografica e audiovisiva (*)",
        "122 - Aiuto operatori di ripresa cinematografica ed audiovisiva (*)",
        "123 - Maestranze cinematografiche",
        "124 - Maestranze teatrali",
        "125 - Maestranze delle imprese audiovisivi (*)",
        "126 - Fotografi di scena",
        "131 - Architetti (*), arredatori (*)",
        "132 - Costumisti (*), figurinisti (*), modiste (*)",
        "133 - Scenografi (*)",
        "134 - Bozzettista",
        "135 - Story board artist",
        "136 - Creatori di fumetti, illustrazioni e disegni finalizzati all'animazione",
        "141 - Truccatori (*)",
        "142 - Parrucchieri (*)",
        "154 - Artieri ippici",
        "155 - Operatori di cabina di sale cinematografiche",
        "156 - Maschere, custodi, guardarobieri, addetti alle pulizie e al facchinaggio",
        "157 - Maschere, custodi, guardarobieri, addetti alle pulizie e al facchinaggio dipendenti dagli enti ed imprese esercenti pubblici spettacoli",
        "158 - Maschere, custodi, guardarobieri, addetti alle pulizie e al facchinaggio dipendenti dalle imprese della produzione cinematografica, del doppiaggio e dello sviluppo e stampa",
        "201 - Impiegati amministrativi e tecnici dipendenti da imprese audiovisivi",
        "202 - Impiegati amministrativi e tecnici dipendenti da Enti ed imprese esercenti pubblici spettacolo (cassieri e Direttori di sala)",
    ];
    const strumenti = [
        "Violino",
        "Viola",
        "Violoncello",
        "Contrabbasso",
        "Flauto",
        "Oboe",
        "Clarinetto",
        "Fagotto",
        "Corno",
        "Tromba",
        "Trombone",
        "Tuba",
        "Arpa",
        "Pianoforte",
        "Timpani",
        "Xilofono",
        "Vibrafono",
        "Marimba",
        "Celesta",
        "Sassofono (soprano, contralto, tenore, baritono)",
        "Tromba bassa",
        "Tromba piccola",
        "Flauto dolce",
        "Ottavino",
        "Organo",
        "Chitarra classica",
        "Chitarra elettrica",
        "Basso elettrico",
        "Batteria",
        "Percussioni orchestrali (come tamburi, piatti, triangolo, castanets, ecc.)",        
    ];
    const voices = [
        "Soprano",
        "Mezzosoprano",
        "Contralto",
        "Tenore",
        "Baritono",
        "Basso",
    ];

    const [isFormComplete, setIsFormComplete] = useState(false);  
    
    useEffect(() => {
        if (tipologia) {
          if (tipologia === '081 - Concertisti e solisti' || tipologia === '082 - Professori d\'orchestra' || tipologia === '083 - Orchestrali anche di musica leggera' || tipologia === '084 - Bandisti') {
            if (strumentoValue) {
                setIsFormComplete(true)
            }
          }
          else if (tipologia === '011 - Artisti lirici') {
            if (voiceValue) {
                setIsFormComplete(true)
            }
          }
          else {
              setIsFormComplete(true);
          }
        }
    }, [nomeArte, tipologia, strumentoValue, voiceValue]);
    

    const handleSubmit = (event) => {
        event.preventDefault();
    
        if (isFormComplete) {
            const formData = {
                nomeArte,
                tipologia,
                strumentoValue,
                voiceValue
            };
            onUpdateArtistaData({ artista: formData });
          onSubmit();
        } else {
            setIsFormComplete(false);
        }
    };
    

    // Dentro la funzione handleTipologiaOpen
    const handleTipologiaOpen = () => {
        setStateTipologiaOpen(true);
        /* if (tipologia === "081 - Concertisti e solisti" || tipologia === "082 - Professori d'orchestra") {
            setShowStrumento(true);
        } */
    };
  
    // Dentro la funzione handleTipologiaClose
    const handleTipologiaClose = () => {
        setStateTipologiaOpen(false);
    };
  
    // Open Stato Strumento
    const handleStrumentoOpen = () => {
        setShowStrumento(true);
    };
    
    // Close Stato Strumento
    const handleStrumentoClose = () => {
        setShowStrumento(false);
    };

    const handleVoiceOpen = () => {
        setShowVoice(true);
    }

    const handleVoiceClose = () => {
        setShowVoice(false);
    }

  return (
    <Container maxWidth="md">
      <StyledPaper>
        <StyledForm onSubmit={handleSubmit}>
          <Grid container spacing={3}>
            <Typography variant="h5" gutterBottom style={{ width: '100%'}}>
              Dati Artistici
            </Typography>
            <Grid item xs={12} sm={4}>
              <TextField
                label="Nome D'Arte"
                fullWidth
                name="nomeArte"
                value={nomeArte}
                onChange={(event) => setNomeArte(event.target.value)}
              />
            </Grid>
            <Grid item xs={12} sm={4}>
            <FormControl sx={{ m: 1, minWidth: '13.7rem', maxWidth: '13.8rem' }} style={{ margin: 'auto' }}>
              <InputLabel id="tipologia-artista-label">Tipologia Artista</InputLabel>
              <Select
                labelId="tipologia-artista-label"
                id="tipologia-artista-id"
                open={stateTipologiaOpen}
                onClose={handleTipologiaClose}
                onOpen={handleTipologiaOpen}
                name="stateTipologia"
                value={tipologia}
                label={'Stato Tipologia'}
                onChange={(event) => setTipologia(event.target.value)}
                required
              >
                {tipologieArtista.map((tipologia) => (
                  <MenuItem key={tipologia} value={tipologia}>
                    {tipologia}
                  </MenuItem>
                ))}
              </Select>
            </FormControl>
          </Grid>
          {(tipologia === '081 - Concertisti e solisti' || tipologia === '082 - Professori d\'orchestra' || tipologia === '083 - Orchestrali anche di musica leggera' || tipologia === '084 - Bandisti') && (
              <Grid item xs={12} sm={4}>
                <FormControl sx={{ m: 1, minWidth: '13.7rem', maxWidth: '13.7rem' }} style={{ margin: 'auto' }}>
                    <InputLabel id="strumento-label">Strumento</InputLabel>
                    <Select
                        labelId="strumento-label"
                        id="strumento-id"
                        open={showStrumento}
                        onClose={handleStrumentoClose}
                        onOpen={handleStrumentoOpen}
                        name="stateStrumento"
                        value={strumentoValue}
                        label={'Stato Strumento'}
                        onChange={(event) => setStrumentoValue(event.target.value)}
                        required
                    >
                        {strumenti.map((strumento) => (
                        <MenuItem key={strumento} value={strumento}>
                            {strumento}
                        </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </Grid>
          )}
          {tipologia === '011 - Artisti lirici' && (
                <Grid item xs={12} sm={4}>
                    <FormControl sx={{ m: 1, minWidth: '13.7rem', maxWidth: '13.7rem' }} style={{ margin: 'auto' }}>
                        <InputLabel id="strumento-label">Voce</InputLabel>
                        <Select
                            labelId="voce-label"
                            id="voce-id"
                            open={showVoice}
                            onClose={handleVoiceClose}
                            onOpen={handleVoiceOpen}
                            name="stateVoice"
                            value={voiceValue}
                            label={'Voce'}
                            onChange={(event) => setVoiceValue(event.target.value)}
                            required
                        >
                            {voices.map((voice) => (
                                <MenuItem key={voice} value={voice}>
                                    {voice}
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
};

export default ArtistaSection;