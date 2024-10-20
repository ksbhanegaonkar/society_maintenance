import './loginPage.css'
import nirmanAuraImage from '../../images/NirmanAuraImage.jpg'
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { axiosPrivate } from '../../api/axios';
import { BASE_URL, LOGIN_URL } from '../../config/config';
import useAuth from '../../hooks/useAuth';
function LoginPage() {
  const [phoneNo, setPhoneNo] = useState('');
  const [pin, setPin] = useState(''); 
  const [validationError, setValidationError] = useState('');
  const navigate = useNavigate();
  const { setAuth } = useAuth();
  const handleLogin = async () => {
    if(phoneNo === '' || pin === ''){
      setValidationError('Please enter valid phone no. and pin !!')
    }else{
      try{
        const response = await axiosPrivate.post(BASE_URL + LOGIN_URL,
          JSON.stringify({ username: phoneNo, password: pin }),
          {
              headers: { 'Content-Type': 'application/json' },
              withCredentials: true
          }
        );
        const accessToken = response?.data?.access_token;
        const roles = response?.data?.roles;
        const user = response?.data?.username;
        setAuth({roles, accessToken, user  });
        if(accessToken){
          console.log("redirection to home")
          navigate('/home')
        }else{
          setValidationError('Phone number or pin is incorrect !!')
        }
      }catch (err){
        if (!err?.response) {
          setValidationError('No Server Response');
        } else if (err.response?.status === 400) {
          setValidationError('Missing Username or Password');
        } else if (err.response?.status === 401) {
          setValidationError('Phone number or pin is incorrect !!');
        } else {
          setValidationError('Login Failed');
        }
      }
    }
  };
  const handlePhoneNoInputChange = (event) => {
    if(validationError !== ''){
      setValidationError('');
    }
    setPhoneNo(event.target.value);
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
          <input id="phoneNo" placeholder="Phone Number" className={'flat-number-input'} onChange={handlePhoneNoInputChange}/>
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