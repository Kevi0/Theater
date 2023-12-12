import React, { useState } from 'react';
import './css/RegisterUser.css'
import { Element, scroller } from 'react-scroll';
import '../configs/colors.css'
import { LinearProgress, Box } from '@mui/material';

import AnagraficaSection from './registration sections/AnagraficaSection';
import CittadinanzaSection from './registration sections/CittadinanzaSection'
import ContattiSection from './registration sections/ContattiSection'
import ResidenzaSection from './registration sections/ResidenzaSection'
import ArtistaSection from './registration sections/ArtistaSection'
import AgenziaSection from './registration sections/AgenziaSection';
import BancaSection from './registration sections/BancaSection';
import FiscoSection from "./registration sections/FiscoSection";

const RegisterUser = () => {
    const [currentSection, setCurrentSection] = useState(0);
    const totalSections = 8;
    const [formData, setFormData] = useState({
        // Inizializza gli altri campi con i valori di default

    });

    //Funzioni update

    const handleUpdateAnagraficaData  = (anagraficaData) => {
        setFormData((prevData) => ({
            ...prevData,
            anagrafica: {
                ...prevData.anagrafica,
                ...anagraficaData,
            },
        }));
    };

    const handleUpdateCittadinanzaData  = (cittadinanzaData) => {
        setFormData((prevData) => ({
            ...prevData,
            cittadinanza: {
                ...prevData.cittadinanza,
                ...cittadinanzaData,
            },
        }));
    }

    const handleUpdateContattiData  = (contattiData) => {
        setFormData((prevData) => ({
            ...prevData,
            contatti: {
                ...prevData.contatti,
                ...contattiData,
            },
        }));
    }

    const handleUpdateResidenzaData  = (residenzaData) => {
        setFormData((prevData) => ({
            ...prevData,
            residenza: {
                ...prevData.residenza,
                ...residenzaData,
            },
        }));
    }

    const handleUpdateArtistaData  = (artistaData) => {
        setFormData((prevData) => ({
            ...prevData,
            artista: {
                ...prevData.artista,
                ...artistaData,
            },
        }));
    }

    const handleUpdateAgenziaData  = (agenziaData) => {
        setFormData((prevData) => ({
            ...prevData,
            agenzia: {
                ...prevData.agenzia,
                ...agenziaData,
            },
        }));
    }

    const handleUpdateBancaData  = (bancaData) => {
        setFormData((prevData) => ({
            ...prevData,
            banca: {
                ...prevData.banca,
                ...bancaData,
            },
        }));
    }

    const handleUpdateFiscoData  = (fiscoData) => {
        setFormData((prevData) => ({
            ...prevData,
            fisco: {
                ...prevData.fisco,
                ...fiscoData,
            },
        }));
    }

    const handleSectionSubmit = () => {
    // Logica di validazione e passaggio alla sezione successiva
    // Esegui la validazione della sezione corrente
    // Se i dati sono validi, passa alla prossima sezione
    if (currentSection < totalSections) {
      const nextSection = currentSection + 1; // Calcola la prossima sezione
      setCurrentSection(nextSection, () => {
        // Esegui lo scroll alla prossima sezione
        scroller.scrollTo(`section${nextSection}`, {
          duration: 500,
          smooth: true,
          offset: -100, // Puoi regolare questo valore per l'offset dello scroll
        });
      });
    }
        console.log(formData)
    };

    const progressPercent = (currentSection / totalSections) * 100;

    return (
    <div>
      <Box display="flex" alignItems="center">
        <LinearProgress
          variant="determinate"
          value={progressPercent}
          sx={{ flexGrow: 1 }}
          style={{ backgroundColor: 'white', height: '1vh', borderRadius: '3px' }}
        />
      </Box>
      {/* Sezione 1 */}
      <Element name="section1">
        {currentSection === 0 && <AnagraficaSection currentSection={currentSection} onSubmit={handleSectionSubmit}  onUpdateAnagraficaData={handleUpdateAnagraficaData}/>}
      </Element>

      {/* Sezione 2 */}
      <Element name="section2">
        {currentSection === 1 && <CittadinanzaSection currentSection={currentSection} onSubmit={handleSectionSubmit} onUpdateCittadinanzaData={handleUpdateCittadinanzaData}/>}
      </Element>

      {/* Sezione 3 */}
      <Element name="section3">
        {currentSection === 2 && <ContattiSection currentSection={currentSection} onSubmit={handleSectionSubmit} onUpdateContattiData={handleUpdateContattiData}/>}
      </Element>

      {/* Sezione 4 */}
      <Element name="section4">
        {currentSection === 3 && <ResidenzaSection currentSection={currentSection} onSubmit={handleSectionSubmit} onUpdateResidenzaData={handleUpdateResidenzaData} />}
      </Element>

      {/* Sezione 5 */}
      <Element name="section5">
        {currentSection === 4 && <ArtistaSection currentSection={currentSection} onSubmit={handleSectionSubmit} onUpdateArtistaData={handleUpdateArtistaData} />}
      </Element>

      {/* Sezione 6 */}
      <Element name="section6">
        {currentSection === 5 && <AgenziaSection currentSection={currentSection} onSubmit={handleSectionSubmit} onUpdateAgenziaData = {handleUpdateAgenziaData} />}
      </Element>

      {/* Sezione 7 */}
      <Element name="section7">
        {currentSection === 6 && <BancaSection currentSection={currentSection} onSubmit={handleSectionSubmit} onUpdateBancaData = {handleUpdateBancaData} />}
      </Element>

      <Element name="section8">
        {currentSection === 7 && <FiscoSection currentSection={currentSection} onSubmit={handleSectionSubmit} onUpdateFiscoData = {handleUpdateFiscoData} />}
      </Element>
    </div>
    );
};

export default RegisterUser;
