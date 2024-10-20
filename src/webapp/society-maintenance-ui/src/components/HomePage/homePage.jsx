import { useState } from 'react';
import './homePage.css'
import menuButtonImg from '../../images/menu-button.svg'
import MaintenanceScreen from '../MaintenanceScreen/maintenanceScreen';
import WaterScreen from '../WaterScreen/waterScreen';
import SocietyIssueScreen from '../SocietyIssueScreen/societyIssueScreen';
import MyIssueScreen from '../MyIssueScreen/myIssueScreen';
import NoticeBoard from '../NoticeBoard/noticeBoard';
import TenentsScreen from '../TenentsScreen/tenentsScreen';
import SocietyActivityScreen from '../SocietyActivityScreen/societyActivityScreen';
import { useQuery } from 'react-query';
import { FetchHello} from '../../data/fetchData';
function HomePage() {
  const [sideBarStatus, setSideBarStatus] = useState("open");
  const [sideBarOption,setSideBarOption] = useState("maintenanceScreen")
  const handleSideBarOpenClose = () => {
        if(sideBarStatus === "close"){
            setSideBarStatus("open");
        }else{
            setSideBarStatus("close");
        }
  };
  const handleSideBarOptionChange = (option) => {
    setSideBarOption(option);
  };

  const helloQuery = useQuery([], FetchHello());

  return (
    <div className="main-window">
        <div className='main-window-header'>
            Our Nirman Aura Society
        </div>
        <div className='body'>
            <div className='side-bar'>
            <img src={menuButtonImg} onClick={handleSideBarOpenClose} className='menu-button'/>
                <div className={sideBarStatus === "close"? "side-bar-content side-bar-collapsed" : "side-bar-content side-bar-open"}>
                    <div className='button-pannel'>
                        <button className={'maintenanceScreen' === sideBarOption ? 'option-selected' : ''} onClick={() => handleSideBarOptionChange('maintenanceScreen')}>Maintenance</button>
                        <button className={'waterScreen' === sideBarOption ? 'option-selected' : ''} onClick={() => handleSideBarOptionChange('waterScreen')}>Water</button>
                        <button className={'societyIssues' === sideBarOption ? 'option-selected' : ''} onClick={() => handleSideBarOptionChange('societyIssues')}>Society Issues</button>
                        <button className={'myIssues' === sideBarOption ? 'option-selected' : ''} onClick={() => handleSideBarOptionChange('myIssues')}>My Issues</button>
                        <button className={'noticeBoard' === sideBarOption ? 'option-selected' : ''} onClick={() => handleSideBarOptionChange('noticeBoard')}>Notice Board</button>
                        <button className={'tenents' === sideBarOption ? 'option-selected' : ''} onClick={() => handleSideBarOptionChange('tenents')}>Tenents</button>
                        <button className={'societyActivity' === sideBarOption ? 'option-selected' : ''} onClick={() => handleSideBarOptionChange('societyActivity')}>Society Activities</button>
                    </div>
                </div>
            </div>
            <div className='main-content'>
                {"maintenanceScreen" === sideBarOption && <MaintenanceScreen/>}
                {"waterScreen" === sideBarOption && <WaterScreen/>}
                {"societyIssues" === sideBarOption && <SocietyIssueScreen/>}
                {"myIssues" === sideBarOption && <MyIssueScreen/>}
                {"noticeBoard" === sideBarOption && <NoticeBoard/>}
                {"tenents" === sideBarOption && <TenentsScreen/>}
                {"societyActivity" === sideBarOption && <SocietyActivityScreen/>}
            </div>
        </div>
    </div>
  );
}

export default HomePage;