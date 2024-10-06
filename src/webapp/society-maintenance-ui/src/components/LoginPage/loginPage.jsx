import './loginPage.css'
import nirmanAuraImage from '../../images/NirmanAuraImage.jpg'
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
function LoginPage() {
  const [flatNo, setFlatNo] = useState('');
  const [pin, setPin] = useState(''); 
  const [validationError, setValidationError] = useState('');
  const navigate = useNavigate();
  const handleLogin = () => {
    if(flatNo === '' || pin === ''){
      setValidationError('Please enter valid flat no. and pin !!')
    }else{
      navigate('/home')
    }
  };
  const handleFlatNoInputChange = (event) => {
    if(validationError !== ''){
      setValidationError('');
    }
    setFlatNo(event.target.value);
  }
  const handlePinInputChange = (event) => {
    if(validationError !== ''){
      setValidationError('');
    }
    setPin(event.target.value);
  }

  return (
    <div className="login-page-grid">
      <img className='nirman-aura-image' src={nirmanAuraImage}/>
      <div className="login-window">
          <input id="flatNo" placeholder="Flat No. (eg. B-101)" className={'flat-number-input'} onChange={handleFlatNoInputChange}/>
          <input id="pin" type='password' placeholder="Pin" className={'pin-input'} onChange={handlePinInputChange}/>
          {validationError !== '' && <div className='login-validation-error'>{validationError}</div>}
          <button id="loginButton" className='login-button' onClick={handleLogin}>Login</button>
          <div className='new-user-password-reset'>
            <button id="newUser" className='new-user-button'>New User?</button>
            <button id="forgotPassword" className='forgot-password-button'>Forgot Password?</button>
          </div>
      </div>
    </div>
  );
}

export default LoginPage;