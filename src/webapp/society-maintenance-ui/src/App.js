import logo from './logo.svg';
import './App.css';
import LoginPage from './components/LoginPage/loginPage';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import HomePage from './components/HomePage/homePage';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
            <Route index element={<LoginPage />} />
            <Route path="home" element={<HomePage />} />
        </Routes>
      </BrowserRouter>
    </div>
    
  );
}

export default App;
