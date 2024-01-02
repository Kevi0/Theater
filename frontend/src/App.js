import './App.css';
import './pages/RegisterUser'
import RegistrationForm from './pages/RegisterUser';
import Footer from './components/Footer';
import TheaterPage from "./pages/TheaterPage";

function App() {
 
  return (
    <div id='container'>
      <div id="content">
        <h1>Dynamic Form with React</h1>
        <TheaterPage />


      </div>

      <div id='footer'>
        <Footer />
      </div>
    </div>
  );
}

export default App;