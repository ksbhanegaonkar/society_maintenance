import './App.css';
import LoginPage from './components/LoginPage/loginPage';
import { Routes, Route } from 'react-router-dom';
import HomePage from './components/HomePage/homePage';
import Layout from './components/Layout/Layout';
import RequireAuth from './components/LoginPage/RequireAuth';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route index element={<LoginPage />} />
        <Route path="login" element={<LoginPage />} />
        <Route element={<RequireAuth/>}>
          <Route path="home" element={<HomePage />} />
        </Route>
      </Route>
    </Routes>
  );
}

export default App;
