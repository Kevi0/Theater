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

const BancaSection = ({ currentSection, onSubmit, onUpdateBancaData }) => {
    
    const [paeseContoOpen, setPaeseContoOpen] = useState(false);
    const [paeseConto, setPaeseConto] = useState('');
    
    const [iban, setIban] = useState('');
    const [nomeBeneficiario, setNomeBeneficiario] = useState('');
    const [nomeBanca, setNomeBanca] = useState('');
    const [abi, setAbi] = useState('');
    const [cab, setCab] = useState('');
    const [enableDates, setEnableDates] = useState(true);
    const [enableIban, setEnableIban] = useState(false);
    const [bic, setBic] = useState('');
    

    const abiCodes = [
        {
            "Nome": "AAREAL BANK AG",
            "Abi": "03097"
        },
        {
            "Nome": "ABC INTERNATIONAL BANK PLC",
            "Abi": "03561"
        },
        {
            "Nome": "ALETTI &amp; C. BANCA DI INVESTIMENTO MOBILIARE S.P.A. (IN FORMA ABBREVIATA BANCA ALETTI &amp; C. S.P.A.)",
            "Abi": "03102"
        },
        {
            "Nome": "ALLFUNDS BANK S.A.",
            "Abi": "03271"
        },
        {
            "Nome": "ALLIANZ BANK FINANCIAL ADVISORS S.P.A.",
            "Abi": "03589"
        },
        {
            "Nome": "ALPENBANK A.G.",
            "Abi": "03134"
        },
        {
            "Nome": "ALTO ADIGE BANCA S.P.A.",
            "Abi": "03374"
        },
        {
            "Nome": "ARTIGIANCASSA S.P.A.",
            "Abi": 10681
        },
        {
            "Nome": "ATTIJARIWAFA BANK EUROPE SEDE SECONDARIA ITALIANA",
            "Abi": "03415"
        },
        {
            "Nome": "B.C.C. DEL GARDA - BANCA DI CREDITO COOPERATIVO COLLI MORENICI DEL GARDA - SOCIETA' COOPERATIVA",
            "Abi": "08676"
        },
        {
            "Nome": "BANCA 5 S.P.A.",
            "Abi": "03385"
        },
        {
            "Nome": "BANCA ADRIA COLLI EUGANEI - CREDITO COOPERATIVO SOCIETA' COOPERATIVA",
            "Abi": "08982"
        },
        {
            "Nome": "BANCA AGRICOLA POPOLARE DI RAGUSA SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05036"
        },
        {
            "Nome": "BANCA AKROS S.P.A.",
            "Abi": "03045"
        },
        {
            "Nome": "BANCA ALBERTINI S.P.A. (IN FORMA ABBREVIATA BANCA ALBERTINI S.P.A.)",
            "Abi": "03258"
        },
        {
            "Nome": "BANCA ALPI MARITTIME CREDITO COOPERATIVO CARRU' - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "08450"
        },
        {
            "Nome": "BANCA ALTA TOSCANA CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08922"
        },
        {
            "Nome": "BANCA ALTO VICENTINO - CREDITO COOPERATIVO DI SCHIO, PEDEMONTE E ROANA - SOCIETA' COOPERATIVA",
            "Abi": "08669"
        },
        {
            "Nome": "BANCA CAMBIANO 1884 S.P.A.",
            "Abi": "08425"
        },
        {
            "Nome": "BANCA CAPASSO ANTONIO - S.P.A.",
            "Abi": "03047"
        },
        {
            "Nome": "BANCA CARIGE S.P.A. - CASSA DI RISPARMIO DI GENOVA E IMPERIA(IN FORMA ABBREVIATA CARIGE S.P.A.)  IN AMMINISTRAZIONE STRAORDINARIA",
            "Abi": "06175"
        },
        {
            "Nome": "BANCA CASSA DI RISPARMIO DI SAVIGLIANO S.P.A.",
            "Abi": "06305"
        },
        {
            "Nome": "BANCA CENTRO EMILIA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08509"
        },
        {
            "Nome": "BANCA CENTRO LAZIO CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08716"
        },
        {
            "Nome": "BANCA CENTROPADANA CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08324"
        },
        {
            "Nome": "BANCA CESARE PONTI S.P.A.",
            "Abi": "03084"
        },
        {
            "Nome": "BANCA CONSULIA S.P.A.",
            "Abi": "03159"
        },
        {
            "Nome": "BANCA CRAS CREDITO COOPERATIVO TOSCANO - SIENA - SOCIETA' COOPERATIVA",
            "Abi": "08885"
        },
        {
            "Nome": "BANCA CREMASCA E MANTOVANA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07076"
        },
        {
            "Nome": "BANCA DEI SIBILLINI-CREDITO COOPERATIVO DI CASAVECCHIA-SOCIETA' COOPERATIVA",
            "Abi": "08456"
        },
        {
            "Nome": "BANCA DEL CATANZARESE - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07091"
        },
        {
            "Nome": "BANCA DEL CILENTO DI SASSANO E VALLO DI DIANO E DELLA LUCANIA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08154"
        },
        {
            "Nome": "BANCA DEL FUCINO - S.P.A.",
            "Abi": "03124"
        },
        {
            "Nome": "BANCA DEL GRAN SASSO D'ITALIA, BANCA DI CREDITO COOPERATIVO SOCIETÀ COOPERATIVA PER AZIONI",
            "Abi": "07116"
        },
        {
            "Nome": "BANCA DEL MEZZOGIORNO - MEDIOCREDITO CENTRALE SPA (IN FORMA ABBREVIATA BDM - MCC S.P.A.)",
            "Abi": 10680
        },
        {
            "Nome": "BANCA DEL MONTE DI LUCCA S.P.A.",
            "Abi": "06915"
        },
        {
            "Nome": "BANCA DEL PICENO CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08474"
        },
        {
            "Nome": "BANCA DEL PIEMONTE S.P.A.",
            "Abi": "03048"
        },
        {
            "Nome": "BANCA DEL SUD S.P.A.",
            "Abi": "03353"
        },
        {
            "Nome": "BANCA DEL TERRITORIO LOMBARDO CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08735"
        },
        {
            "Nome": "BANCA DEL VALDARNO - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08811"
        },
        {
            "Nome": "BANCA DELL'ALTA MURGIA CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07056"
        },
        {
            "Nome": "BANCA DELL'ELBA CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07048"
        },
        {
            "Nome": "BANCA DELLA MARCA CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07084"
        },
        {
            "Nome": "BANCA DELLA NUOVA TERRA S.P.A.",
            "Abi": "03269"
        },
        {
            "Nome": "BANCA DELLA VALSASSINA CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08515"
        },
        {
            "Nome": "BANCA DI ANCONA E FALCONARA MARITTIMA CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08086"
        },
        {
            "Nome": "BANCA DI ANDRIA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA PER AZIONI A RESPONSABILITA' LIMITATA",
            "Abi": "07101"
        },
        {
            "Nome": "BANCA DI ANGHIARI E STIA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08345"
        },
        {
            "Nome": "BANCA DI BOLOGNA CREDITO COOPERATIVO SOCIETA' COOPERATIVA",
            "Abi": "08883"
        },
        {
            "Nome": "BANCA DI CARAGLIO, DEL CUNEESE E DELLA RIVIERA DEI FIORI - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08439"
        },
        {
            "Nome": "BANCA DI CIVIDALE SOCIETA' COOPERATIVA PER AZIONI O IN FORMA ABBREVIATA CIVIBANK",
            "Abi": "05484"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO 'G. TONIOLO' DI SAN CATALDO (CALTANISSETTA) SOCIETA' COOPERATIVA",
            "Abi": "08952"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO 'S. GIUSEPPE' DELLE MADONIE - SOCIETA' COOPERATIVA",
            "Abi": "08976"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO ABRUZZESE - CAPPELLE SUL TAVO SOCIETA' COOPERATIVA",
            "Abi": "08434"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO AGRIGENTINO - SOCIETA' COOPERATIVA",
            "Abi": "07108"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO AGROBRESCIANO - SOCIETA' COOPERATIVA",
            "Abi": "08575"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO BERGAMASCA E OROBICA - SOCIETA' COOPERATIVA",
            "Abi": "08940"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO BERGAMO E VALLI - SOCIETA' COOPERATIVA",
            "Abi": "08869"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO BRIANZA E LAGHI - SOCIETA' COOPERATIVA",
            "Abi": "08329"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DEGLI ULIVI - TERRA DI BARI - SOCIETA' COOPERATIVA",
            "Abi": "08988"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DEI CASTELLI E DEGLI IBLEI SOCIETA' COOPERATIVA",
            "Abi": "07078"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DEI CASTELLI ROMANI E DEL TUSCOLO - SOCIETA' COOPERATIVA",
            "Abi": "07092"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DEI COLLI ALBANI - SOCIETA' COOPERATIVA",
            "Abi": "08951"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DEL BASSO SEBINO - SOCIETA' COOPERATIVA",
            "Abi": "08437"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DEL CIRCEO E PRIVERNATE - SOCIETA' COOPERATIVA",
            "Abi": "07017"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DEL METAURO - SOCIETA' COOPERATIVA",
            "Abi": "08700"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DEL VELINO - COMUNE DI POSTA PROVINCIA DI RIETI - SOCIETA' COOPERATIVA",
            "Abi": "08743"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DELL'ADRIATICO TERAMANO - SOCIETA' COOPERATIVA",
            "Abi": "07086"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DELL'ALTO TIRRENO DELLA CALABRIA VERBICARO (PROVINCIA DI COSENZA) - SOCIETA' COOPERATIVA",
            "Abi": "08365"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DELL'OGLIO E DEL SERIO - SOCIETA' COOPERATIVA",
            "Abi": "08514"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DELLA PROVINCIA ROMANA SOCIETA' COOPERATIVA",
            "Abi": "08787"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DELLA ROMAGNA OCCIDENTALE - SOCIETA' COOPERATIVA",
            "Abi": "08462"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DELLA VALLE DEL FITALIA - SOCIETA' COOPERATIVA",
            "Abi": "08913"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DELLA VALLE DEL TRIGNO - SOCIETA' COOPERATIVA",
            "Abi": "08189"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI ALBEROBELLO E SAMMICHELE DI BARI - SOCIETA' COOPERATIVA",
            "Abi": "08338"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI ALTOFONTE E CACCAMO - SOCIETA' COOPERATIVA",
            "Abi": "08341"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI ANAGNI SOCIETA' COOPERATIVA (IN BREVE BANCANAGNI CREDITO COOPERATIVO)",
            "Abi": "08344"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI AQUARA SOCIETA' COOPERATIVA",
            "Abi": "08342"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI ARBOREA - SOCIETA' COOPERATIVA",
            "Abi": "08362"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI AVETRANA - SOCIETA' COOPERATIVA",
            "Abi": "08354"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI BARI - SOCIETA' COOPERATIVA",
            "Abi": "07012"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI BARLASSINA (MILANO) - SOCIETA' COOPERATIVA",
            "Abi": "08374"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI BASCIANO - SOCIETA' COOPERATIVA",
            "Abi": "08149"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI BELLEGRA SOCIETA' COOPERATIVA",
            "Abi": "08381"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI BORGHETTO LODIGIANO - SOCIETA' COOPERATIVA",
            "Abi": "08030"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI BRESCIA - SOCIETA' COOPERATIVA (ABBR. CREDITO COOPERATIVO DI BRESCIA - SOCIETA' COOP.)",
            "Abi": "08692"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI BUCCINO E DEI COMUNI CILENTANI - SOCIETA' COOPERATIVA",
            "Abi": "07066"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI BUONABITACOLO SOCIETA' COOPERATIVA",
            "Abi": "08409"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI BUSTO GAROLFO E BUGUGGIATE -SOCIETA' COOPERATIVA",
            "Abi": "08404"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CAGLIARI SOCIETA' COOPERATIVA",
            "Abi": "07096"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CANOSA - LOCONIA - SOCIETA' COOPERATIVA",
            "Abi": "08606"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CAPACCIO PAESTUM E SERINO- SOCIETA' COOPERATIVA",
            "Abi": "08431"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CARATE BRIANZA - SOCIETA' COOPERATIVA",
            "Abi": "08440"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CASALGRASSO E SANT'ALBANO STURA - SOCIETA' COOPERATIVA",
            "Abi": "08833"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CASSANO DELLE MURGE E TOLVE - SOCIETA' COOPERATIVA",
            "Abi": "08460"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CASTAGNETO CARDUCCI SOCIETA'COOPERATIVA PER AZIONI",
            "Abi": "08461"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CASTIGLIONE MESSER RAIMONDO E PIANELLA - SOCIETA' COOPERATIVA A RESPONSABILITA' LIMITATA",
            "Abi": "08473"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CHERASCO - SOCIETA' COOPERATIVA",
            "Abi": "08487"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CITTANOVA - SOCIETA' COOPERATIVA",
            "Abi": "08492"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI CONVERSANO - SOCIETA' COOPERATIVA",
            "Abi": "08503"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI FANO - SOCIETA' COOPERATIVA",
            "Abi": "08519"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI FLUMERI - SOCIETA' COOPERATIVA",
            "Abi": "08553"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI GAMBATESA - SOCIETA' COOPERATIVA",
            "Abi": "08572"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI GAUDIANO DI LAVELLO - SOCIETA' COOPERATIVA",
            "Abi": "08554"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI LEVERANO - SOCIETA' COOPERATIVA",
            "Abi": "08603"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI LEZZENO (COMO) - SOCIETA' COOPERATIVA A RESPONSABILITA' LIMITATA",
            "Abi": "08618"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI LOCOROTONDO CASSA RURALE E ARTIGIANA - SOCIETA' COOPERATIVA",
            "Abi": "08607"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI MARINA DI GINOSA - SOCIETA' COOPERATIVA",
            "Abi": "08688"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI MASSAFRA - SOCIETA' COOPERATIVA",
            "Abi": "07094"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI MILANO - SOCIETA' COOPERATIVA",
            "Abi": "08453"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI MONOPOLI - SOCIETA' COOPERATIVA",
            "Abi": "07027"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI MONTEPAONE SOCIETA' COOPERATIVA",
            "Abi": "08126"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI MOZZANICA - SOCIETA' COOPERATIVA",
            "Abi": "08679"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI NAPOLI SOCIETA' COOPERATIVA PER AZIONI (IN SIGLA B.C.C. DI NAPOLI)",
            "Abi": "07110"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI NETTUNO SOCIETA' COOPERATIVA",
            "Abi": "08693"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI OPPIDO LUCANO E RIPACANDIDA - SOCIETA' COOPERATIVA",
            "Abi": "08708"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI OSTRA E MORRO D'ALBA - SOCIETA' COOPERATIVA",
            "Abi": "08704"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI OSTRA VETERE SOCIETA' COOPERATIVA",
            "Abi": "08705"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI OSTUNI - SOCIETA' COOPERATIVA",
            "Abi": "08706"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI PACHINO - SOCIETA' COOPERATIVA",
            "Abi": "08713"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI PERGOLA E CORINALDO SOCIETA' COOPERATIVA",
            "Abi": "08731"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI PIANFEI E ROCCA DE' BALDI - SOCIETA' COOPERATIVA",
            "Abi": "08753"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI PONTASSIEVE - SOCIETA' COOPERATIVA",
            "Abi": "08736"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI PRATOLA PELIGNA (L'AQUILA) -SOCIETA' COOPERATIVA",
            "Abi": "08747"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI PUTIGNANO - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "07113"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI RECANATI E COLMURANO - SOCIETA' COOPERATIVA",
            "Abi": "08765"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI ROMA SOCIETA' COOPERATIVA",
            "Abi": "08327"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI SAN BIAGIO PLATANI, SOCIETA'COOPERATIVA",
            "Abi": "08800"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI SAN GIOVANNI ROTONDO - SOCIETA' COOPERATIVA",
            "Abi": "08810"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI SAN MARCO DEI CAVOTI E DEL SANNIO-CALVI - SOCIETA' COOPERATIVA",
            "Abi": "08997"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI SAN MARZANO DI SAN GIUSEPPE - TARANTO - SOCIETA' COOPERATIVA",
            "Abi": "08817"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI SANTERAMO IN COLLE - SOCIETA' COOPERATIVA",
            "Abi": "08844"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI SARSINA - SOCIETA' COOPERATIVA",
            "Abi": "08850"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI SCAFATI E CETARA SOCIETA' COOPERATIVA",
            "Abi": "08855"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI SPELLO E BETTONA - SOCIETA' COOPERATIVA",
            "Abi": "08871"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI SPINAZZOLA - SOCIETA' COOPERATIVA",
            "Abi": "08873"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI STARANZANO E VILLESSE - SOCIETA' COOPERATIVA",
            "Abi": "08877"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI TERRA D'OTRANTO - SOCIETA' COOPERATIVA",
            "Abi": "08445"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI TRIUGGIO E DELLA VALLE DEL LAMBRO - SOCIETA' COOPERATIVA",
            "Abi": "08901"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI TURRIACO - SOCIETA' COOPERATIVA",
            "Abi": "08903"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DI VENEZIA, PADOVA E ROVIGO - BANCA ANNIA SOCIETA' COOPERATIVA",
            "Abi": "08452"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO DON STELLA DI RESUTTANO - SOCIETA' COOPERATIVA",
            "Abi": "08947"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO LA RISCOSSA DI REGALBUTO - SOCIETA' COOPERATIVA",
            "Abi": "08954"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO LAUDENSE - LODI SOCIETA' COOPERATIVA",
            "Abi": "08794"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO MUTUO SOCCORSO DI GANGI - SOCIETA' COOPERATIVA",
            "Abi": "08958"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO PORDENONESE - SOCIETA' COOPERATIVA",
            "Abi": "08356"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO SAN GIUSEPPE DI MUSSOMELI - SOCIETA' COOPERATIVA",
            "Abi": "08975"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO SAN MICHELE DI CALTANISSETTA E PIETRAPERZIA - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "08979"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO SANGRO TEATINA DI ATESSA",
            "Abi": "08968"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO TERRA DI LAVORO - S. VINCENZO DE' PAOLI SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "08987"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO VALDOSTANA - COOPERATIVE DE CREDIT VALDOTAINE - SOCIETA' COOPERATIVA",
            "Abi": "08587"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO VALLE DEL TORTO - SOCIETA' COOPERATIVA",
            "Abi": "08601"
        },
        {
            "Nome": "BANCA DI CREDITO COOPERATIVO VICENTINO - POJANA MAGGIORE (VICENZA) - SOCIETA' COOPERATIVA",
            "Abi": "08732"
        },
        {
            "Nome": "BANCA DI CREDITO PELORITANO S.P.A.",
            "Abi": "03426"
        },
        {
            "Nome": "BANCA DI CREDITO POPOLARE - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05142"
        },
        {
            "Nome": "BANCA DI FILOTTRANO - CREDITO COOPERATIVO DI FILOTTRANO E CAMERANO - SOCIETA' COOPERATIVA",
            "Abi": "08549"
        },
        {
            "Nome": "BANCA DI IMOLA S.P.A.",
            "Abi": "05080"
        },
        {
            "Nome": "BANCA DI MONASTIER E DEL SILE - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07074"
        },
        {
            "Nome": "BANCA DI PESARO CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08826"
        },
        {
            "Nome": "BANCA DI PESCIA E CASCINA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08358"
        },
        {
            "Nome": "BANCA DI PIACENZA - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05156"
        },
        {
            "Nome": "BANCA DI PISA E FORNACETTE CREDITO COOPERATIVO - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "08562"
        },
        {
            "Nome": "BANCA DI RIPATRANSONE E DEL FERMANO - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08769"
        },
        {
            "Nome": "BANCA DI SASSARI S.P.A.",
            "Abi": "05676"
        },
        {
            "Nome": "BANCA DI SCONTO E CONTI CORRENTI DI SANTA MARIA CAPUA VETERE- S.P.A.",
            "Abi": "03235"
        },
        {
            "Nome": "BANCA DI TARANTO - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07087"
        },
        {
            "Nome": "BANCA DI UDINE CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08715"
        },
        {
            "Nome": "BANCA DI VERONA CREDITO COOPERATIVO CADIDAVID SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "08416"
        },
        {
            "Nome": "BANCA DON RIZZO - CREDITO COOPERATIVO DELLA SICILIA OCCIDENTALE - SOCIETA' COOPERATIVA",
            "Abi": "08946"
        },
        {
            "Nome": "BANCA EUROMOBILIARE S.P.A.",
            "Abi": "03250"
        },
        {
            "Nome": "BANCA FARMAFACTORING SPA",
            "Abi": "03435"
        },
        {
            "Nome": "BANCA FINANZIARIA INTERNAZIONALE S.P.A. IN BREVE BANCA FININT S.P.A.",
            "Abi": "03266"
        },
        {
            "Nome": "BANCA FINNAT EURAMERICA S.P.A. (IN FORMA ABBREVIATA BANCA FINNAT S.P.A. OVVERO BANCA EURAMERICA S.P.A.)",
            "Abi": "03087"
        },
        {
            "Nome": "BANCA GALILEO S.P.A.",
            "Abi": "03267"
        },
        {
            "Nome": "BANCA GENERALI - SOCIETA' PER AZIONI (IN FORMA ABBREVIATA GENERBANCA)",
            "Abi": "03075"
        },
        {
            "Nome": "BANCA IFIGEST - S.P.A.",
            "Abi": "03185"
        },
        {
            "Nome": "BANCA IFIS S.P.A. (OVVERO IFIS BANCA S.P.A. O IN FORMA ABBREVIATA IFIS S.P.A.)",
            "Abi": "03205"
        },
        {
            "Nome": "BANCA IMI S.P.A. (IN FORMA ABBREVIATA IMI S.P.A.)",
            "Abi": "03249"
        },
        {
            "Nome": "BANCA INTERMOBILIARE DI INVESTIMENTI E GESTIONI SOCIETA' PER AZIONI",
            "Abi": "03043"
        },
        {
            "Nome": "BANCA LAZIO NORD CREDITO COOPERATIVO - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "08931"
        },
        {
            "Nome": "BANCA MACERATA S.P.A. (IN FORMA ABBREVIATA BPRM S.P.A.)",
            "Abi": "03317"
        },
        {
            "Nome": "BANCA MALATESTIANA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07090"
        },
        {
            "Nome": "BANCA MEDIOCREDITO DEL FRIULI VENEZIA GIULIA S.P.A.",
            "Abi": 10640
        },
        {
            "Nome": "BANCA MEDIOLANUM S.P.A.",
            "Abi": "03062"
        },
        {
            "Nome": "BANCA MONTE DEI PASCHI DI SIENA S.P.A.",
            "Abi": "01030"
        },
        {
            "Nome": "BANCA MONTE PRUNO - CREDITO COOPERATIVO DI FISCIANO, ROSCIGNO E LAURINO- SOCIETA' COOPERATIVA",
            "Abi": "08784"
        },
        {
            "Nome": "BANCA NAZIONALE DEL LAVORO S.P.A. (IN FORMA CONTRATTA BNL S.P.A.)",
            "Abi": "01005"
        },
        {
            "Nome": "BANCA PASSADORE &amp; C. S.P.A.",
            "Abi": "03332"
        },
        {
            "Nome": "BANCA PATAVINA CREDITO COOPERATVO DI SANT'ELENA E PIOVE DI SACCO, SOCIETA' COOPERATIVA",
            "Abi": "08728"
        },
        {
            "Nome": "BANCA PATRIMONI SELLA &amp; C. S.P.A. (IN SIGLA B.P.A. S.P.A.)",
            "Abi": "03211"
        },
        {
            "Nome": "BANCA PER LO SVILUPPO DELLA COOPERAZIONE DI CREDITO S.P.A. E, IN FORMA ABBREVIATA 'BANCA SVILUPPO S.P.A.'",
            "Abi": "03139"
        },
        {
            "Nome": "BANCA PICCOLO CREDITO VALTELLINESE, SOCIETA' PER AZIONI",
            "Abi": "05216"
        },
        {
            "Nome": "BANCA POPOLARE DEL CASSINATE SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05372"
        },
        {
            "Nome": "BANCA POPOLARE DEL FRUSINATE SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05297"
        },
        {
            "Nome": "BANCA POPOLARE DEL LAZIO SOCIETA' COOPERATIVA",
            "Abi": "05104"
        },
        {
            "Nome": "BANCA POPOLARE DEL MEDITERRANEO SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05038"
        },
        {
            "Nome": "BANCA POPOLARE DELL'ALTO ADIGE SOCIETA' PER AZIONI (IN TEDESCO SUDTIROLER VOLKSBANK A.G.)",
            "Abi": "05856"
        },
        {
            "Nome": "BANCA POPOLARE DELLE PROVINCE MOLISANE - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05033"
        },
        {
            "Nome": "BANCA POPOLARE DI BARI - SOCIETA' COOPERATIVA PER AZIONI IN A.S.",
            "Abi": "05424"
        },
        {
            "Nome": "BANCA POPOLARE DI CORTONA - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05496"
        },
        {
            "Nome": "BANCA POPOLARE DI FONDI - SOCIETA' COOPERATIVA",
            "Abi": "05296"
        },
        {
            "Nome": "BANCA POPOLARE DI LAJATICO SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05232"
        },
        {
            "Nome": "BANCA POPOLARE DI PUGLIA E BASILICATA - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05385"
        },
        {
            "Nome": "BANCA POPOLARE DI SONDRIO, SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05696"
        },
        {
            "Nome": "BANCA POPOLARE ETICA - SOCIETA' COOPERATIVA PER AZIONI (IN FORMA ABBREVIATA BANCA ETICA O BPE)",
            "Abi": "05018"
        },
        {
            "Nome": "BANCA POPOLARE PUGLIESE - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05262"
        },
        {
            "Nome": "BANCA POPOLARE SANT'ANGELO, SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05772"
        },
        {
            "Nome": "BANCA POPOLARE VALCONCA - SOCIETA' PER AZIONI",
            "Abi": "05792"
        },
        {
            "Nome": "BANCA POPOLARE VESUVIANA SOCIETA' COOPERATIVA",
            "Abi": "05602"
        },
        {
            "Nome": "BANCA PREALPI SANBIAGIO CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08904"
        },
        {
            "Nome": "BANCA PRIVATA LEASING S.P.A.",
            "Abi": "03417"
        },
        {
            "Nome": "BANCA PROFILO S.P.A. (OVVERO PROFILOBANK S.P.A.)",
            "Abi": "03025"
        },
        {
            "Nome": "BANCA PROGETTO S.P.A.",
            "Abi": "05015"
        },
        {
            "Nome": "BANCA PROMOS S.P.A.",
            "Abi": "03265"
        },
        {
            "Nome": "BANCA PSA ITALIA S.P.A.",
            "Abi": "03628"
        },
        {
            "Nome": "BANCA REALE S.P.A.",
            "Abi": "03138"
        },
        {
            "Nome": "BANCA REGIONALE DI SVILUPPO S.P.A. IN FORMA ABBREVIATA 'BRS S.P.A.'",
            "Abi": "05023"
        },
        {
            "Nome": "BANCA SAN FRANCESCO CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08969"
        },
        {
            "Nome": "BANCA SAN GIORGIO QUINTO VALLE AGNO - CREDITO COOPERATIVO - SOC. COOP.",
            "Abi": "08807"
        },
        {
            "Nome": "BANCA SANTA GIULIA S.P.A.",
            "Abi": "03367"
        },
        {
            "Nome": "BANCA SELLA - S.P.A.",
            "Abi": "03268"
        },
        {
            "Nome": "BANCA SELLA HOLDING S.P.A.",
            "Abi": "03311"
        },
        {
            "Nome": "BANCA SICANA - CREDITO COOPERATIVO DI SOMMATINO, SERRADIFALCO E SAMBUCA DI SICILIA - SOCIETA' COOPERATIVA",
            "Abi": "08985"
        },
        {
            "Nome": "BANCA SIMETICA S.P.A.",
            "Abi": "03398"
        },
        {
            "Nome": "BANCA SISTEMA S.P.A.",
            "Abi": "03158"
        },
        {
            "Nome": "BANCA STABIESE - S.P.A.",
            "Abi": "03388"
        },
        {
            "Nome": "BANCA SVILUPPO TUSCIA S.P.A.",
            "Abi": "03441"
        },
        {
            "Nome": "BANCA TRANSILVANIA S.A.",
            "Abi": "03437"
        },
        {
            "Nome": "BANCA UBAE S.P.A.",
            "Abi": "03598"
        },
        {
            "Nome": "BANCA VALDICHIANA - CREDITO COOPERATIVO DI CHIUSI E MONTEPULCIANO - SOCIETA' COOPERATIVA",
            "Abi": "08489"
        },
        {
            "Nome": "BANCA VALSABBINA SOCIETA' COOPERATIVA PER AZIONI (IN BREVE LA VALSABBINA)",
            "Abi": "05116"
        },
        {
            "Nome": "BANCA VERONESE CREDITO COOPERATIVO DI CONCAMARISE SOCIETA' COOPERATIVA",
            "Abi": "08322"
        },
        {
            "Nome": "BANCA VERSILIA LUNIGIANA E GARFAGNANA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08726"
        },
        {
            "Nome": "BANCATER CREDITO COOPERATIVO FVG  - SOCIETA' COOPERATIVA",
            "Abi": "08631"
        },
        {
            "Nome": "BANCO BILBAO VIZCAYA ARGENTARIA S.A. (BBVA)",
            "Abi": "03576"
        },
        {
            "Nome": "BANCO BPM SOCIETA' PER AZIONI",
            "Abi": "05034"
        },
        {
            "Nome": "BANCO DELLE TRE VENEZIE S.P.A. (CON ACRONIMO B.T.V. SPA)",
            "Abi": "03365"
        },
        {
            "Nome": "BANCO DI CREDITO P.AZZOAGLIO - S.P.A.",
            "Abi": "03425"
        },
        {
            "Nome": "BANCO DI DESIO E DELLA BRIANZA - SOCIETA' PER AZIONI",
            "Abi": "03440"
        },
        {
            "Nome": "BANCO DI LUCCA E DEL TIRRENO S.P.A. (IN FORMA ABBREVIATA BANCO DI LUCCA S.P.A.)",
            "Abi": "03242"
        },
        {
            "Nome": "BANCO DI SARDEGNA S.P.A.",
            "Abi": "01015"
        },
        {
            "Nome": "BANCO DO BRASIL AG",
            "Abi": "03465"
        },
        {
            "Nome": "BANCO FIORENTINO - MUGELLO IMPRUNETA SIGNA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08325"
        },
        {
            "Nome": "BANCO MARCHIGIANO CREDITO COOPERATIVO",
            "Abi": "08491"
        },
        {
            "Nome": "BANCO SANTANDER S.A.",
            "Abi": "03389"
        },
        {
            "Nome": "BANK OF AMERICA MERRILL LYNCH INTERNATIONAL DESIGNATED ACTIVITY COMPANY",
            "Abi": "03380"
        },
        {
            "Nome": "BANK OF CHINA LIMITED - FILIALE DI MILANO O IN FORMA ABBREVIATA BANK OF CHINA - FILIALE DI MILANO",
            "Abi": "03093"
        },
        {
            "Nome": "BANK OF COMMUNICATIONS (LUXEMBOURG) S.A.",
            "Abi": "03623"
        },
        {
            "Nome": "BANK SEPAH",
            "Abi": "03381"
        },
        {
            "Nome": "BANQUE CHAABI DU MAROC - IN ABBREVIATO, B.C.D.M.",
            "Abi": "03387"
        },
        {
            "Nome": "BANQUE POPULAIRE MEDITERRANEE",
            "Abi": "03241"
        },
        {
            "Nome": "BARCLAYS BANK IRELAND PUBLIC LIMITED COMPANY",
            "Abi": "03051"
        },
        {
            "Nome": "BAYERISCHE LANDESBANK",
            "Abi": "03055"
        },
        {
            "Nome": "BCC BASILICATA - CREDITO COOPERATIVO DI LAURENZANA E COMUNI LUCANI - SOCIETA' COOPERATIVA",
            "Abi": "08597"
        },
        {
            "Nome": "BCC CAMPANIA CENTRO - CASSA RURALE ED ARTIGIANA - SOCIETA' COOPERATIVA",
            "Abi": "08378"
        },
        {
            "Nome": "BCC DI ALBA, LANGHE, ROERO E DEL CANAVESE S.C.",
            "Abi": "08530"
        },
        {
            "Nome": "BCC FELSINEA - BANCA DI CREDITO COOPERATIVO DAL1902 - SOCIETA' COOPERATIVA",
            "Abi": "08472"
        },
        {
            "Nome": "BCC UMBRIA CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07075"
        },
        {
            "Nome": "BENE BANCA CREDITO COOPERATIVO DI BENE VAGIENNA (CUNEO) - SOCIETA' COOPERATIVA",
            "Abi": "08382"
        },
        {
            "Nome": "BHW BAUSPARKASSE AG",
            "Abi": "03073"
        },
        {
            "Nome": "BINCKBANK N.V.",
            "Abi": "03423"
        },
        {
            "Nome": "BMW BANK GMBH",
            "Abi": "03434"
        },
        {
            "Nome": "BNP PARIBAS",
            "Abi": "03181"
        },
        {
            "Nome": "BNP PARIBAS LEASE GROUP",
            "Abi": "03179"
        },
        {
            "Nome": "BNP PARIBAS SECURITIES SERVICES",
            "Abi": "03479"
        },
        {
            "Nome": "BPCE LEASE S.A.",
            "Abi": "03305"
        },
        {
            "Nome": "BPER BANCA S.P.A.",
            "Abi": "05387"
        },
        {
            "Nome": "CA INDOSUEZ WEALTH (EUROPE)",
            "Abi": "03443"
        },
        {
            "Nome": "CA INDOSUEZ WEALTH (ITALY) S.P.A.",
            "Abi": "03126"
        },
        {
            "Nome": "CACEIS BANK",
            "Abi": "03438"
        },
        {
            "Nome": "CARREFOUR BANQUE",
            "Abi": "03421"
        },
        {
            "Nome": "CASSA CENTRALE BANCA - CREDITO COOPERATIVO ITALIANOSOCIETA' PER AZIONI (IN SIGLA CASSA CENTRALE BANCA)",
            "Abi": "03599"
        },
        {
            "Nome": "CASSA CENTRALE RAIFFEISEN DELL'ALTO ADIGE - RAIFFEISEN-LANDESBANK SUEDTIROL A.G.",
            "Abi": "03493"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI ASTI S.P.A. (IN FORMA ABBREVIATA BANCA C.R. ASTI S.P.A.)",
            "Abi": "06085"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI BIELLA E VERCELLI - BIVERBANCA SPA",
            "Abi": "06090"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI BOLZANO S.P.A. - SUDTIROLER SPARKASSE AG",
            "Abi": "06045"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI BRA S.P.A.",
            "Abi": "06095"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI CENTO S.P.A.",
            "Abi": "06115"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI FERMO S.P.A. (IN FORMA ABBREVIATA CARIFERMO S.P.A.)",
            "Abi": "06150"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI FOSSANO S.P.A.",
            "Abi": "06170"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI ORVIETO S.P.A.",
            "Abi": "06220"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI SALUZZO S.P.A.",
            "Abi": "06295"
        },
        {
            "Nome": "CASSA DI RISPARMIO DI VOLTERRA S.P.A.",
            "Abi": "06370"
        },
        {
            "Nome": "CASSA DI SOVVENZIONI E RISPARMIO FRA IL PERSONALE DELLA BANCA D'ITALIA - SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05824"
        },
        {
            "Nome": "CASSA DI TRENTO, LAVIS, MEZZOCORONA E VALLE DI CEMBRA - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08304"
        },
        {
            "Nome": "CASSA LOMBARDA - S.P.A.",
            "Abi": "03488"
        },
        {
            "Nome": "CASSA PADANA BANCA DI CREDITO COOPERATIVO SOCIETA' COOPERATIVA",
            "Abi": "08340"
        },
        {
            "Nome": "CASSA RAIFFEISEN ALTA PUSTERIA SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE HOCHPUSTERTAL GENOSSENSCHAFT)",
            "Abi": "08020"
        },
        {
            "Nome": "CASSA RAIFFEISEN ALTA VENOSTA SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE OBERVINSCHGAU GENOSSENSCHAFT)",
            "Abi": "08066"
        },
        {
            "Nome": "CASSA RAIFFEISEN BASSA ATESINA (IN LINGUA TEDESCA RAIFFEISENKASSE UNTERLAND)",
            "Abi": "08114"
        },
        {
            "Nome": "CASSA RAIFFEISEN BASSA VALL'ISARCO SOCIETA' COOPERATIVA (IN TEDESCO RAIFFEISENKASSE UNTEREISACKTAL GENOSSENSCHAFT)",
            "Abi": "08113"
        },
        {
            "Nome": "CASSA RAIFFEISEN BASSA VENOSTA SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE UNTERVINSCHGAU GENOSSENSCHAFT)",
            "Abi": "08157"
        },
        {
            "Nome": "CASSA RAIFFEISEN CAMPO DI TRENS SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE FREIENFELD GENOSSENSCHAFT)",
            "Abi": "08249"
        },
        {
            "Nome": "CASSA RAIFFEISEN CASTELROTTO - ORTISEI SOC. COOPERATIVA - RAIFFEISENKASSE KASTELRUTH - ST. ULRICH GENOSSENSCHAFT",
            "Abi": "08056"
        },
        {
            "Nome": "CASSA RAIFFEISEN DELLA VAL PASSIRIA SOCIETA' COOPERATIVA ( IN LINGUA TEDESCA RAIFFEISENKASSE PASSEIER GENOSSENSHAFT)",
            "Abi": "08998"
        },
        {
            "Nome": "CASSA RAIFFEISEN DELLA VALLE ISARCO SOCIETA' COOPERATIVA ( IN LINGUA TEDESCA RAIFFEISENKASSE EISACKTAL GENOSSENSHAFT)",
            "Abi": "08307"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI BRUNICO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE BRUNECK GENOSSENSCHAFT)",
            "Abi": "08035"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI DOBBIACO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE TOBLACH GENOSSENSHAFT)",
            "Abi": "08080"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI FUNES SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE VILLNOESS GENOSSENSCHAFT)",
            "Abi": "08094"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI LAGUNDO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE ALGUND GENOSSENSCHAFT)",
            "Abi": "08112"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI LASA SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE LAAS GENOSSENSCHAFT)",
            "Abi": "08117"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI MARLENGO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE MARLING GENOSSENSHAFT)",
            "Abi": "08134"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI MONGUELFO-CASIES-TESIDO SOCIETA' COOPERATIVA (RAIFFEISENKASSE WELSBERG-GSIES-TAISTEN GENOSSENSCHAFT)",
            "Abi": "08148"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI NOVA PONENTE-ALDINO SOCIETA' COOPERATIVA(IN TEDESCO RAIFFEISENKASSE DEUTSCHNOFEN-ALDEIN GEN.)",
            "Abi": "08162"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI PARCINES SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE PARTSCHINS GENOSSENSHAFT)",
            "Abi": "08175"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI SAN MARTINO IN PASSIRIA SOCIETA' COOPERATIVA (RAIFFEISENKASSE ST. MARTIN IN PASSEIER GENOSSENSCHAFT)",
            "Abi": "08226"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI SCENA SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE SCHENNA GENOSSENSCHAFT)",
            "Abi": "08234"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI TESIMO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE TISENS GENOSSENSCHAFT)",
            "Abi": "08273"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI VANDOIES SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE VINTL GENOSSENSHAFT)",
            "Abi": "08295"
        },
        {
            "Nome": "CASSA RAIFFEISEN DI VILLABASSA SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE NIEDERDORF GENOSSENSCHAFT)",
            "Abi": "08302"
        },
        {
            "Nome": "CASSA RAIFFEISEN ETSCHTAL SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE ETSCHTAL GENOSSENSCHAFT)",
            "Abi": "08269"
        },
        {
            "Nome": "CASSA RAIFFEISEN LACES SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE LATSCH GENOSSENSCHAFT)",
            "Abi": "08110"
        },
        {
            "Nome": "CASSA RAIFFEISEN LANA SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE LANA GENOSSENSCHAFT)",
            "Abi": "08115"
        },
        {
            "Nome": "CASSA RAIFFEISEN MERANO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE MERAN GENOSSENSCHAFT)",
            "Abi": "08133"
        },
        {
            "Nome": "CASSA RAIFFEISEN OLTRADIGE SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE UBERETSCH GENOSSENSCHAFT)",
            "Abi": "08255"
        },
        {
            "Nome": "CASSA RAIFFEISEN PRATO-TUBRE SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE PRAD-TAUFERS GENOSSENSHAFT)",
            "Abi": "08183"
        },
        {
            "Nome": "CASSA RAIFFEISEN SCHLERN - ROSENGARTEN SOCIETA' COOPERATIVA (IN TED. RAIFFEISENKASSE SCHLERN-ROSENGARTEN GENOSSENSCHAFT)",
            "Abi": "08065"
        },
        {
            "Nome": "CASSA RAIFFEISEN SILANDRO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE SCHLANDERS GENOSSENSCHAFT)",
            "Abi": "08244"
        },
        {
            "Nome": "CASSA RAIFFEISEN TIROLO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE TIROL GENOSSENSCHAFT)",
            "Abi": "08278"
        },
        {
            "Nome": "CASSA RAIFFEISEN TURES-AURINA SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE TAUFERER-AHRNTAL GENOSSENSCHAFT)",
            "Abi": "08285"
        },
        {
            "Nome": "CASSA RAIFFEISEN ULTIMO - S. PANCRAZIO - LAUREGNO SOCIETA' COOPERATIVA (RAIFFEISENKASSE ULTEN-ST.PANKRAZ-LAUREIN GEN.)",
            "Abi": "08231"
        },
        {
            "Nome": "CASSA RAIFFEISEN VAL BADIA SOCIETA' COOPERATIVA - GENOSSENSCHAFT",
            "Abi": "08010"
        },
        {
            "Nome": "CASSA RAIFFEISEN VAL GARDENA SOCIETA' COOPERATIVA (RAIFFEISENKASSE GRODEN GENOSSENSCHAFT - CASSA RAIFFEISEN GHERDEINA)",
            "Abi": "08238"
        },
        {
            "Nome": "CASSA RAIFFEISEN VAL SARENTINO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE SARNTAL GENOSSENSCHAFT)",
            "Abi": "08233"
        },
        {
            "Nome": "CASSA RAIFFEISEN WIPPTAL SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE WIPPTAL GENOSSENSCHAFT)",
            "Abi": "08182"
        },
        {
            "Nome": "CASSA RURALE  VAL DI FIEMME - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08184"
        },
        {
            "Nome": "CASSA RURALE - BANCA DI CREDITO COOPERATIVO DI TREVIGLIO - SOCIETA' COOPERATIVA",
            "Abi": "08899"
        },
        {
            "Nome": "CASSA RURALE ADAMELLO BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08024"
        },
        {
            "Nome": "CASSA RURALE ALTA VALLAGARINA E LIZZANA - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08305"
        },
        {
            "Nome": "CASSA RURALE ALTA VALSUGANA - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08178"
        },
        {
            "Nome": "CASSA RURALE ALTO GARDA - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08016"
        },
        {
            "Nome": "CASSA RURALE DI BOLZANO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE BOZEN GENOSSENSCHAFT)",
            "Abi": "08081"
        },
        {
            "Nome": "CASSA RURALE DI LEDRO - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08026"
        },
        {
            "Nome": "CASSA RURALE DI ROVERETO - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08210"
        },
        {
            "Nome": "CASSA RURALE DI SALORNO SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE SALURN GENOSSENSCHAFT)",
            "Abi": "08220"
        },
        {
            "Nome": "CASSA RURALE DOLOMITI DI FASSA PRIMIERO E BELLUNO - BANCA DICREDITO COOPERATIVO - SOCIETÀ COOPERATIVA",
            "Abi": "08140"
        },
        {
            "Nome": "CASSA RURALE E ARTIGIANA DI BRENDOLA CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08399"
        },
        {
            "Nome": "CASSA RURALE ED ARTIGIANA DELL'AGRO PONTINO - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08738"
        },
        {
            "Nome": "CASSA RURALE ED ARTIGIANA DI BINASCO - CREDITO COOPERATIVO SOCIETA' COOPERATIVA",
            "Abi": "08386"
        },
        {
            "Nome": "CASSA RURALE ED ARTIGIANA DI BORGO SAN GIACOMO (BRESCIA) - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08393"
        },
        {
            "Nome": "CASSA RURALE ED ARTIGIANA DI BOVES - BANCA DI CREDITO COOPERATIVO (BOVES-CUNEO) - SOCIETA' COOPERATIVA",
            "Abi": "08397"
        },
        {
            "Nome": "CASSA RURALE ED ARTIGIANA DI CANTU' BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08430"
        },
        {
            "Nome": "CASSA RURALE ED ARTIGIANA DI CASTELLANA GROTTE CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08469"
        },
        {
            "Nome": "CASSA RURALE ED ARTIGIANA DI RIVAROLO MANTOVANO (MANTOVA) CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08770"
        },
        {
            "Nome": "CASSA RURALE ED ARTIGIANA DI VESTENANOVA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08910"
        },
        {
            "Nome": "CASSA RURALE GIUDICARIE VALSABBIA PAGANELLA - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08078"
        },
        {
            "Nome": "CASSA RURALE NOVELLA E ALTA ANAUNIA - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08200"
        },
        {
            "Nome": "CASSA RURALE RENON SOCIETA' COOPERATIVA (IN LINGUA TEDESCA RAIFFEISENKASSE RITTEN GENOSSENSCHAFT)",
            "Abi": "08187"
        },
        {
            "Nome": "CASSA RURALE ROTALIANA E GIOVO - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08139"
        },
        {
            "Nome": "CASSA RURALE VAL DI NON - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08282"
        },
        {
            "Nome": "CASSA RURALE VAL DI SOLE - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08163"
        },
        {
            "Nome": "CASSA RURALE VALLAGARINA - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08011"
        },
        {
            "Nome": "CASSA RURALE VALSUGANA E TESINO - BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08102"
        },
        {
            "Nome": "CENTROMARCA BANCA - CREDITO COOPERATIVO DI TREVISO E VENEZIA, SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "08749"
        },
        {
            "Nome": "CENTROVENETO BASSANO BANCA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08590"
        },
        {
            "Nome": "CEREABANCA 1897 CREDITO COOPERATIVO SOCIETA' COOPERATIVA",
            "Abi": "08481"
        },
        {
            "Nome": "CHEBANCA! S.P.A.",
            "Abi": "03058"
        },
        {
            "Nome": "CHIANTIBANCA - CREDITO COOPERATIVO S.C.",
            "Abi": "08673"
        },
        {
            "Nome": "CHINA CONSTRUCTION BANK (EUROPE) SA",
            "Abi": "03620"
        },
        {
            "Nome": "CITIBANK EUROPE PLC",
            "Abi": "03061"
        },
        {
            "Nome": "CITIBANK N.A.",
            "Abi": "03566"
        },
        {
            "Nome": "CLAAS FINANCIAL SERVICES",
            "Abi": "03358"
        },
        {
            "Nome": "CNH INDUSTRIAL CAPITAL EUROPE",
            "Abi": "03227"
        },
        {
            "Nome": "CNH INDUSTRIAL FINANCIAL SERVICES S.A.",
            "Abi": "03349"
        },
        {
            "Nome": "COMMERZBANK AG",
            "Abi": "03095"
        },
        {
            "Nome": "COMPASS BANCA SPA",
            "Abi": 19275
        },
        {
            "Nome": "COOPERATIEVE RABOBANK U.A.",
            "Abi": "03482"
        },
        {
            "Nome": "CORTINABANCA - CREDITO COOPERATIVO - SOCIETÀ COOPERATIVA",
            "Abi": "08511"
        },
        {
            "Nome": "CREDIT AGRICOLE CORPORATE &amp; INVESTMENT BANK",
            "Abi": "03432"
        },
        {
            "Nome": "CREDIT AGRICOLE FRIULADRIA S.P.A.",
            "Abi": "05336"
        },
        {
            "Nome": "CREDIT SUISSE (ITALY) S.P.A.",
            "Abi": "03089"
        },
        {
            "Nome": "CREDIT SUISSE AG",
            "Abi": "03040"
        },
        {
            "Nome": "CREDIT SUISSE INTERNATIONAL",
            "Abi": "03629"
        },
        {
            "Nome": "CREDITO COOP.VO ROMAGNOLO - BCC DI CESENA E GATTEO - S.C.",
            "Abi": "07070"
        },
        {
            "Nome": "CREDITO COOPERATIVO - CASSA RURALE ED ARTIGIANA DEL FRIULI VENEZIA GIULIA (ABBREVIATO CASSA RURALE FVG) - SOCIETA' COOPERATIVA",
            "Abi": "08622"
        },
        {
            "Nome": "CREDITO COOPERATIVO CASSA RURALE ED ARTIGIANA DI ERCHIE - SOCIETA' COOPERATIVA",
            "Abi": "08537"
        },
        {
            "Nome": "CREDITO COOPERATIVO CASSA RURALE ED ARTIGIANA DI PALIANO SOCIETA' COOPERATIVA",
            "Abi": "08717"
        },
        {
            "Nome": "CREDITO COOPERATIVO CENTRO CALABRIA - SOCIETA' COOPERATIVA",
            "Abi": "08258"
        },
        {
            "Nome": "CREDITO COOPERATIVO DI CARAVAGGIO ADDA E CREMASCO - CASSA RURALE - SOCIETA' COOPERATIVA",
            "Abi": "08441"
        },
        {
            "Nome": "CREDITO COOPERATIVO DI SAN CALOGERO E MAIERATO - BCC DEL VIBONESE SOCIETA' COOPERATIVA",
            "Abi": "08887"
        },
        {
            "Nome": "CREDITO COOPERATIVO FRIULI (ABBREVIATO CREDIFRIULI) - SOCIETA' COOPERATIVA",
            "Abi": "07085"
        },
        {
            "Nome": "CREDITO COOPERATIVO MEDIOCRATI - SOCIETA' COOPERATIVA",
            "Abi": "07062"
        },
        {
            "Nome": "CREDITO COOPERATIVO RAVENNATE, FORLIVESE E IMOLESE SOCIETA' COOPERATIVA",
            "Abi": "08542"
        },
        {
            "Nome": "CREDITO COOPERATIVO VALDARNO FIORENTINO BANCA DI CASCIA - SOCIETA' COOPERATIVA",
            "Abi": "08457"
        },
        {
            "Nome": "CREDITO EMILIANO S.P.A.",
            "Abi": "03032"
        },
        {
            "Nome": "CREDITO ETNEO - BANCA DI CREDITO COOPERATIVO, SOCIETA' COOPERATIVA A RESPONSABILITA' LIMITATA",
            "Abi": "07080"
        },
        {
            "Nome": "CREDITO FONDIARIO S.P.A.",
            "Abi": 10312
        },
        {
            "Nome": "CREDITO LOMBARDO VENETO S.P.A.",
            "Abi": "03430"
        },
        {
            "Nome": "CREDITO PADANO BANCA DI CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08454"
        },
        {
            "Nome": "CREDITO TREVIGIANO - BANCA DI CREDITO COOPERATIVO - SOCIETA'COOPERATIVA",
            "Abi": "08917"
        },
        {
            "Nome": "CRÉDIT AGRICOLE ITALIA S.P.A.",
            "Abi": "06230"
        },
        {
            "Nome": "DE LAGE LANDEN INTERNATIONAL B.V. (DLL)",
            "Abi": "03362"
        },
        {
            "Nome": "DEPOBANK - BANCA DEPOSITARIA ITALIANA S.P.A.",
            "Abi": "05000"
        },
        {
            "Nome": "DEUTSCHE BANK AG",
            "Abi": "03298"
        },
        {
            "Nome": "DEUTSCHE BANK MUTUI S.P.A.",
            "Abi": "03150"
        },
        {
            "Nome": "DEUTSCHE BANK S.P.A.",
            "Abi": "03104"
        },
        {
            "Nome": "DEXIA CREDIOP S.P.A.",
            "Abi": "03030"
        },
        {
            "Nome": "EDMOND DE ROTHSCHILD (FRANCE)",
            "Abi": "03407"
        },
        {
            "Nome": "EFG BANK (LUXEMBOURG) SA",
            "Abi": "03433"
        },
        {
            "Nome": "EMIL BANCA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "07072"
        },
        {
            "Nome": "EUROPE ARAB BANK PLC - MILAN BRANCH",
            "Abi": "03006"
        },
        {
            "Nome": "EXTRABANCA S.P.A.",
            "Abi": "03399"
        },
        {
            "Nome": "FARBANCA S.P.A.",
            "Abi": "03110"
        },
        {
            "Nome": "FCA BANK SPA",
            "Abi": "03445"
        },
        {
            "Nome": "FCE BANK PLC",
            "Abi": "03009"
        },
        {
            "Nome": "FIDEURAM - INTESA SANPAOLO PRIVATE BANKING S.P.A.",
            "Abi": "03296"
        },
        {
            "Nome": "FINDOMESTIC BANCA SPA",
            "Abi": "03115"
        },
        {
            "Nome": "FINECOBANK BANCA FINECO S.P.A. (IN BREVE FINECOBANK S.P.A. OVVERO BANCA FINECO S.P.A. OVVERO FINECO BANCA S.P.A.)",
            "Abi": "03015"
        },
        {
            "Nome": "FRIULOVEST BANCA - CREDITO COOPERATIVO - SOCIETA' COOPERATIVA",
            "Abi": "08805"
        },
        {
            "Nome": "GBM BANCA S.P.A.",
            "Abi": "03323"
        },
        {
            "Nome": "GOLDMAN SACHS BANK EUROPE SE",
            "Abi": "03658"
        },
        {
            "Nome": "GRENKE BANK AG",
            "Abi": "03655"
        },
        {
            "Nome": "GUBER BANCA S.P.A.",
            "Abi": "03656"
        },
        {
            "Nome": "HSBC FRANCE",
            "Abi": "03021"
        },
        {
            "Nome": "HYPO ALPE-ADRIA-BANK S.P.A.",
            "Abi": "03011"
        },
        {
            "Nome": "HYPO TIROL BANK AG",
            "Abi": "03151"
        },
        {
            "Nome": "IBL ISTITUTO BANCARIO DEL LAVORO S.P.A. (IN FORMA ABBREVIATA IBL BANCA)",
            "Abi": "03263"
        },
        {
            "Nome": "ICCREA BANCA S.P.A. - ISTITUTO CENTRALE DEL CREDITO COOPERATIVO (IN FORMA ABBREVIATA: ICCREA BANCA S.P.A.)",
            "Abi": "08000"
        },
        {
            "Nome": "ICCREA BANCAIMPRESA S.P.A.",
            "Abi": "03123"
        },
        {
            "Nome": "IGEA BANCA S.P.A.",
            "Abi": "05029"
        },
        {
            "Nome": "ILLIMITY BANK S.P.A.",
            "Abi": "03395"
        },
        {
            "Nome": "IMPREBANCA S.P.A.",
            "Abi": "03403"
        },
        {
            "Nome": "INDUSTRIAL AND COMMERCIAL BANK OF CHINA (EUROPE) S.A. IN FORMA ABBREVIATA ICBC (EUROPE) S.A.",
            "Abi": "03411"
        },
        {
            "Nome": "ING BANK N.V.",
            "Abi": "03475"
        },
        {
            "Nome": "INTESA SANPAOLO PRIVATE BANKING S.P.A.",
            "Abi": "03239"
        },
        {
            "Nome": "INTESA SANPAOLO S.P.A.",
            "Abi": "03069"
        },
        {
            "Nome": "INVEST BANCA SOCIETA' PER AZIONI",
            "Abi": "03017"
        },
        {
            "Nome": "ISTITUTO PER IL CREDITO SPORTIVO - ENTE DI DIRITTO PUBBLICO",
            "Abi": 10630
        },
        {
            "Nome": "ISTITUTO PER LO SVILUPPO ECONOMICO DELL'ITALIA MERIDIONALE -(I.SV.E.I.MER.) - S.P.A.",
            "Abi": 10647
        },
        {
            "Nome": "IW BANK S.P.A.",
            "Abi": "03083"
        },
        {
            "Nome": "J.P. MORGAN AG",
            "Abi": "03633"
        },
        {
            "Nome": "J.P. MORGAN BANK LUXEMBOURG S.A.",
            "Abi": "03370"
        },
        {
            "Nome": "J.P. MORGAN SECURITIES PLC",
            "Abi": "03419"
        },
        {
            "Nome": "JCB FINANCE",
            "Abi": "03386"
        },
        {
            "Nome": "JPMORGAN CHASE BANK, NATIONAL ASSOCIATION",
            "Abi": "03494"
        },
        {
            "Nome": "KBC BANK N.V. ITALIA",
            "Abi": "03373"
        },
        {
            "Nome": "LA BCC DEL CROTONESE - CREDITO COOPERATIVO SOCIETA' COOPERATIVA",
            "Abi": "08595"
        },
        {
            "Nome": "LA CASSA DI RAVENNA S.P.A.",
            "Abi": "06270"
        },
        {
            "Nome": "LOMBARD ODIER (EUROPE) S.A.",
            "Abi": "03645"
        },
        {
            "Nome": "MAINFIRST BANK AKTIENGESELLSCHAFT",
            "Abi": "03394"
        },
        {
            "Nome": "MEDIOBANCA - BANCA DI CREDITO FINANZIARIO S.P.A.",
            "Abi": 10631
        },
        {
            "Nome": "MEDIOCREDITO TRENTINO-ALTO ADIGE - S.P.A. (IN LINGUA TEDESCAINVESTITIONSBANK TRENTINO-SUDTIROL - A.G.)",
            "Abi": 10638
        },
        {
            "Nome": "MIZUHO BANK LIMITED",
            "Abi": "03346"
        },
        {
            "Nome": "MONTE DEI PASCHI DI SIENA LEASING &amp; FACTORING, BANCA PER I SERVIZI FINANZIARI ALLE IMPRESE S.P.A.",
            "Abi": "03210"
        },
        {
            "Nome": "MORGAN STANLEY BANK INTERNATIONAL LIMITED - MILAN BRANCH",
            "Abi": "03162"
        },
        {
            "Nome": "MPS CAPITAL SERVICES BANCA PER LE IMPRESE SPA (IN FORMA ABBREVIATA MPS BANCA PER LE IMPRESE SPA)",
            "Abi": 10643
        },
        {
            "Nome": "MUFG BANK, LTD",
            "Abi": "03487"
        },
        {
            "Nome": "N26 BANK GMBH",
            "Abi": "03646"
        },
        {
            "Nome": "NATIONAL WESTMINSTER BANK PLC.",
            "Abi": "03377"
        },
        {
            "Nome": "NATIXIS S.A.",
            "Abi": "03189"
        },
        {
            "Nome": "NATWEST MARKETS N.V.",
            "Abi": "03657"
        },
        {
            "Nome": "OPEL BANK SA, SUCC. ITAL.",
            "Abi": "03659"
        },
        {
            "Nome": "PICTET &amp; CIE (EUROPE) S.A.",
            "Abi": "03343"
        },
        {
            "Nome": "POSTE ITALIANE",
            "Abi": "07601"
        },
        {
            "Nome": "POSTEPAY S.P.A.",
            "Abi": 36081
        },
        {
            "Nome": "PRADER BANK S.P.A. (IN LINGUA TEDESCA PRADER BANK AG)",
            "Abi": "03306"
        },
        {
            "Nome": "PRIMACASSA - CREDITO COOPERATIVO FVG - SOCIETA' COOPERATIVA",
            "Abi": "08637"
        },
        {
            "Nome": "RBC INVESTOR SERVICES BANK S.A.",
            "Abi": "03321"
        },
        {
            "Nome": "RCI BANQUE S.A.",
            "Abi": "03103"
        },
        {
            "Nome": "RIVIERABANCA - CREDITO COOPERATIVO DI RIMINI E GRADARA - SOCIETA' COOPERATIVA",
            "Abi": "08995"
        },
        {
            "Nome": "ROMAGNABANCA CREDITO COOPERATIVO ROMAGNA EST E SALA DI CESENATICO S.C.",
            "Abi": "08852"
        },
        {
            "Nome": "ROVIGOBANCA CREDITO COOPERATIVO - SOC. COOP",
            "Abi": "08986"
        },
        {
            "Nome": "SANFELICE 1893 BANCA POPOLARE SOCIETA' COOPERATIVA PER AZIONI",
            "Abi": "05652"
        },
        {
            "Nome": "SANTANDER CONSUMER BANK S.P.A.",
            "Abi": "03191"
        },
        {
            "Nome": "SAXO BANK A/S",
            "Abi": "03405"
        },
        {
            "Nome": "SMBC BANK EU AG",
            "Abi": "03337"
        },
        {
            "Nome": "SOCIETE GENERALE MUTUI ITALIA S.P.A. (IN FORMA ABBREVIATA S G MUTUI ITALIA SPA)",
            "Abi": "03378"
        },
        {
            "Nome": "SOCIETE' GENERALE",
            "Abi": "03593"
        },
        {
            "Nome": "SOCIETE' GENERALE SECURITIES SERVICES S.P.A. (IN FORMA ABBREVIATA SGSS S.P.A.)",
            "Abi": "03307"
        },
        {
            "Nome": "SOLUTION BANK S.P.A.",
            "Abi": "03273"
        },
        {
            "Nome": "STATE STREET BANK INTERNATIONAL GMBH SUCCURSALE ITALIA",
            "Abi": "03439"
        },
        {
            "Nome": "TERRE ETRUSCHE E DI MAREMMA C.C. - SOCIETA' COOPERATIVA",
            "Abi": "08851"
        },
        {
            "Nome": "THE BANK OF NEW YORK MELLON SA/NV",
            "Abi": "03351"
        },
        {
            "Nome": "TOYOTA MATERIAL HANDLING COMMERCIAL FINANCE AB",
            "Abi": "03654"
        },
        {
            "Nome": "UBS EUROPE SE",
            "Abi": "03041"
        },
        {
            "Nome": "UNICREDIT BANK AG",
            "Abi": "03081"
        },
        {
            "Nome": "UNICREDIT, SOCIETA' PER AZIONI",
            "Abi": "02008"
        },
        {
            "Nome": "UNION BANCAIRE PRIVEE (EUROPE) S.A.",
            "Abi": "03427"
        },
        {
            "Nome": "UNIONE DI BANCHE ITALIANE SOCIETA' PER AZIONI (IN FORMA ABBREVIATA UBI BANCA)",
            "Abi": "03111"
        },
        {
            "Nome": "VALPOLICELLA BENACO BANCA CREDITO COOPERATIVO (VERONA) SOCIETA' COOPERATIVA",
            "Abi": "08315"
        },
        {
            "Nome": "VIVAL BANCA - BANCA DI CREDITO COOPERATIVO DI MONTECATINI TERME, BIENTINA E S. PIETRO IN VINCIO S.C.",
            "Abi": "08003"
        },
        {
            "Nome": "VIVIBANCA S.P.A.",
            "Abi": "05030"
        },
        {
            "Nome": "VOLKSWAGEN BANK GMBH.",
            "Abi": "03105"
        },
        {
            "Nome": "WESTERN UNION INTERNATIONAL BANK GMBH",
            "Abi": "03446"
        },
        {
            "Nome": "WISE DIALOG BANK S.P.A.",
            "Abi": "03442"
        },
        {
            "Nome": "YOUNITED",
            "Abi": "03638"
        },
        {
            "Nome": "ZKB ZADRUŽNA KRAŠKA BANKA TRST GORICA ZADRUGA - ZKB CREDITO COOPERATIVO DI TRIESTE E GORIZIA SOCIETÀ COOPERATIVA IN FORMA ABBREVIATA ZKB",
            "Abi": "08928"
        }
    ];
  
    const [isFormComplete, setIsFormComplete] = useState(false);
    
    const isNameValid = (name) => {
        const namePattern = /^[a-zA-Z\s]*$/;
        return namePattern.test(name)
      };
  
    const ibanIsValid = (iban) => {
      const ibanRegex = /^IT\d{2}[A-Z]\d{10}[0-9A-Z]{12}$/;
  
      return ibanRegex.test(iban);
    };      
    
    const bicIsValid = (bic) => {
        const bicRegex = /[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}/;
        
        return bicRegex.test(bic);
    }
  
    const ibanExtract = (iban) => {
        console.log("Sono entrato nella funzione")
      // Estrai le informazioni dall'IBAN.
      const abi = iban.substring(5, 10);
      const cab = iban.substring(10, 15);
      const abiCode = abiCodes.find((item) => item.Abi === abi);

      // Se l'ABI è stato trovato, imposta il nome della banca nello stato.
      if (abiCode) {
        setNomeBanca(abiCode.Nome);
        setCab(cab);
        setAbi(abi);

        setEnableDates(false);
        setEnableIban(true);
        setEnableDates(true);
      } else {
        // Se l'ABI non è stato trovato, imposta un valore vuoto.
        return (
            <div className="error">
              <p>Iban Non valido</p>
            </div>
          );
        
      }
    };
    
    const handlePaeseContoOpen = () => {
        setPaeseContoOpen(true);
    };

    const handlePaeseContoClose = () => {
        setPaeseContoOpen(false);
    };
  
    useEffect(() => {
      if((paeseConto === 'Conto Italiano' &&
        nomeBeneficiario &&
        (iban && ibanIsValid(iban)) &&
        nomeBanca &&
        abi &&
        cab) ||
        (paeseConto === 'Conto Estero' &&
        nomeBeneficiario &&
        (iban && ibanIsValid(iban)) &&
        bic && bicIsValid(bic))
        ){
        console.log("Sono entrato nell'If")
        setIsFormComplete(true);
        }
       
    }, [paeseConto, nomeBeneficiario, nomeBanca, abi, cab, iban, bic]);   

    const handleGenerateClick = () => {
        if(ibanIsValid(iban))
            ibanExtract(iban);
        else{
            
        }
      };
    
  
    const handleSubmit = (event) => {
      event.preventDefault();
  
      if (isFormComplete) {
        const formData = {
            paeseConto,
            nomeBeneficiario,
            nomeBanca,
            abi,
            cab,
            iban
        };
        onUpdateBancaData({ banca: formData })
        onSubmit();
      }
    };
  
    return (
      <Container maxWidth="md">
        <StyledPaper>
          <StyledForm onSubmit={handleSubmit}>
            <Grid container spacing={2}>
                <Typography variant="h5" gutterBottom style={{ width: '100%' }}>
                Dati Bancari
                </Typography>
                <Grid item xs={12} sm={4}>
                    <FormControl sx={{ m: 1, minWidth: '13.7rem' }} style={{ margin: 'auto' }}>
                        <InputLabel id="paese-conto-label">Paese Conto</InputLabel>
                        <Select
                            labelId="paese-conto"
                            id="paese-conto-id"
                            open={paeseContoOpen}
                            onClose={handlePaeseContoClose}
                            onOpen={handlePaeseContoOpen}
                            name="paeseConto"
                            value={paeseConto}
                            label={'Paese Conto'}
                            onChange={(event) => setPaeseConto(event.target.value)}
                            required
                        >
                            <MenuItem value={'Conto Estero'}>Conto Estero</MenuItem>
                            <MenuItem value={'Conto Italiano'}>Conto Italiano</MenuItem>
                        </Select>
                    </FormControl>
                </Grid>
                {paeseConto === 'Conto Italiano' && (
                    <>
                    <Grid item xs={12} sm={4}>
                        <TextField
                            label="Nome Beneficiario"
                            fullWidth
                            name="nomeBeneficiario"
                            value={nomeBeneficiario}
                            onChange={(event) => setNomeBeneficiario(event.target.value)}
                            required
                            error={!isNameValid(nomeBeneficiario)}
                            helperText={!isNameValid(nomeBeneficiario) ? 'Inserisci un nome valido' : ''} />
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <TextField
                            label="ABI"
                            fullWidth
                            name="abi"
                            value={abi}
                            onChange={(event) => setAbi(event.target.value)}
                            required
                            disabled={enableDates}
                        />
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <TextField
                            label="CAB"
                            fullWidth
                            name="cab"
                            value={cab}
                            onChange={(event) => setCab(event.target.value)}
                            required
                            disabled={enableDates}
                        />
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <TextField
                            label="Nome Banca"
                            fullWidth
                            name="nomeBanca"
                            value={nomeBanca}
                            onChange={(event) => setNomeBanca(event.target.value)}
                            required
                            disabled={enableDates}
                        />
                    </Grid>
                    <Grid item xs={12} sm={4}>
                        <TextField
                            label="Iban"
                            fullWidth
                            name="iban"
                            value={iban}
                            onChange={(event) => setIban(event.target.value)}
                            required
                            error={!ibanIsValid(iban) && iban.trim() !== ''}
                            helperText={!ibanIsValid(iban) && iban.trim() !== ''
                                ? 'Inserisci un iban valido'
                                : ''}
                            disabled={enableIban}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Button
                            variant="contained"
                            color="secondary"
                            onClick={handleGenerateClick}
                            fullWidth
                            style={{ borderRadius: '4px', marginTop: '16px' }}
                        >
                            Generate
                        </Button>
                    </Grid>
                    </>
                )}
                {paeseConto === 'Conto Estero' && (
                    <>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Nome Beneficiario"
                                fullWidth
                                name="nomeBeneficiario"
                                value={nomeBeneficiario}
                                onChange={(event) => setNomeBeneficiario(event.target.value)}
                                required
                                error={!isNameValid(nomeBeneficiario)}
                                helperText={!isNameValid(nomeBeneficiario) ? 'Inserisci un nome valido' : ''} />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="Iban"
                                fullWidth
                                name="iban"
                                value={iban}
                                onChange={(event) => setIban(event.target.value)}
                                required
                                error={!ibanIsValid(iban) && iban.trim() !== ''}
                                helperText={!ibanIsValid(iban) && iban.trim() !== ''
                                    ? 'Inserisci un iban valido'
                                    : ''}
                            />
                        </Grid>
                        <Grid item xs={12} sm={4}>
                            <TextField
                                label="BIC/Swift"
                                fullWidth
                                name="bic"
                                value={bic}
                                onChange={(event) => setBic(event.target.value)}
                                required
                                error={!bicIsValid(bic) && bic.trim() !== ''}
                                helperText={!bicIsValid(bic) && bic.trim() !== ''
                                    ? 'Inserisci un BIC/Swift valido'
                                    : ''}
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
};
  

export default BancaSection;