import './App.css';
import './pages/RegisterUser'
import RegistrationForm from './pages/RegisterUser';
import Footer from './components/Footer';

function App() {
 
  return (
    <div id='container'>
      <div id="content">
        <h1>Dynamic Form with React</h1>
        <RegistrationForm />

      </div>

      <div id='footer'>
        <Footer />
      </div>
    </div>
  );
}

export default App;