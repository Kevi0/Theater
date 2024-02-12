const BASE_URL = 'http://localhost:8080/api';

const theaterApi = {
    addTheater: async (formData) => {
        const formDataWithCustomType = {
            ...formData,
            CustomType: "registrationTheater"
        };

        try {
            const response = await fetch(`${BASE_URL}/theater/add`, {
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

            const responseData = await response.json();
            return responseData;
        } catch (error) {
            console.error('Errore durante l\'invio di dati al backend:', error.message);
            throw error; // Rilancia l'errore per gestirlo lato chiamante se necessario
        }
    },
    listTheaters: async () => {
        try {
            const response = await fetch(`${BASE_URL}/theater/getAll`, {
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
    deleteTheater: async (theaterId) => {
        try {
            const response = await fetch(`${BASE_URL}/theater/delete/${theaterId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                },
                mode: 'cors',
                credentials: 'same-origin',
            });
            console.log(response);
            if (!response.ok) {
                throw new Error(`Errore nell'eliminazione del teatro: ${response.status}`);
            }

            return await response.json();
        } catch (error) {
            console.error('Errore durante l\'eliminazione del teatro:', error.message);
            throw error; // Rilancia l'errore per gestirlo lato chiamante se necessario
        }
    },
    getIdTheaterByName: async (theaterName) => {
        try {

            const response = await fetch(`${BASE_URL}/theater/getTheaterIdByName/${theaterName}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
                mode: 'cors',
                credentials: 'same-origin',
            });

            if (!response.ok) {
                throw new Error(`Errore nel recupero dell'id del teatro: ${response.status}`);
            }
            return await response.json();
        } catch (error) {
            console.log('Errore durante il recupero dell\'id del teatro:', error.message);
            throw error;
        }
    },
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
    }
};

export default theaterApi;