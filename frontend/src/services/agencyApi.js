const BASE_URL = 'http://localhost:8080/api';

const agencyApi = {
    addAgency: async (formData) => {
        const formDataWithCustomType = {
            ...formData,
            CustomType: "registrationAgency"
        };

        try {
            const response = await fetch(`${BASE_URL}/agency/add`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formDataWithCustomType),
                mode: 'cors',
                credentials: 'same-origin',
            });

            if (!response.ok) {
                throw new Error(`Errore nell'invio dei dati: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error('Errore durante l\'invio di dati al backend:', error.message);
            throw error; // Rilancia l'errore per gestirlo lato chiamante se necessario
        }
    },
    listAgencies: async () => {
        try {
            const response = await fetch(`${BASE_URL}/agency/getAll`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
                mode: 'cors',
                credentials: 'same-origin',
            });

            if (!response.ok) {
                throw new Error(`Errore nel recupero dei dati: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error('Errore durante il recupero dei dati dal backend:', error.message);
            throw error; // Rilancia l'errore per gestirlo lato chiamante se necessario
        }
    },
    deleteAgency: async (agencyId) => {
        try {
            const response = await fetch(`${BASE_URL}/agency/delete/${agencyId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
                mode: 'cors',
                credentials: 'same-origin',
            });
            console.log(response);
            if (!response.ok) {
                throw new Error(`Errore nell'eliminazione dell'agenzia: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error('Errore durante l\'eliminazione dell\'agenzia:', error.message);
            throw error; // Rilancia l'errore per gestirlo lato chiamante se necessario
        }
    },
    getIdAgencyByName: async (agencyName) => {
        try {
            const response = await fetch(`${BASE_URL}/theater/getAgencyIdByName`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                mode: 'cors',
                credentials: 'same-origin',
                body: JSON.stringify({ agencyName }),
            });

            if (!response.ok) {
                throw new Error(`Errore nel recupero dell'id dell'agenzia: ${response.status}`);
            }
            return await response.json();
        } catch (error) {
            console.log('Errore durante il recupero dell\'id dell\'agenzia:', error.message);
            throw error;
        }
    },/*
    updateTheater: async (theaterId, formData) => {
        const formDataWithCustomType = {
            ...formData,
            CustomType: "registrationTheater"
        };

        try {
            const response = await fetch(`${BASE_URL}/theater/update/${theaterId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formDataWithCustomType),
                mode: 'cors',
                credentials: 'same-origin',
            });

            if (!response.ok) {
                throw new Error(`Errore nell'invio dei dati: ${response.status}`);
            }

            const responseData = await response.json();
            return responseData;
        } catch (error) {
            console.error('Errore durante l\'invio di dati al backend:', error.message);
            throw error; // Rilancia l'errore per gestirlo lato chiamante se necessario
        }
    }*/
};

export default agencyApi;