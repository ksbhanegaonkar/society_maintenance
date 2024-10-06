import TankerDetailGrid from './tankerDetailGrid';
import './waterScreen.css'
function WaterScreen() {
  return (
    <div className='water-screen'>
      <div className='water-screen-header'>
            Water Details
      </div>
      <div className='water-screen-main-stats'>
        <div className='main-stat-left-content'>
            <div>Current water level : 3000</div>
            <div>Total capacity : 4000</div>
        </div>
        <div className='main-stat-right-content'>
          <div>Total Amount Spent Today : Rs. 3000</div>
          <div>Total Amount Spent This Month : Rs. 4000</div>
          <div>Total Amount Spent This Year : Rs. 4000</div>
        </div>
      </div>
      <div className='tanker-status'>
        <TankerDetailGrid/>
      </div>
    </div>
  );
}

export default WaterScreen;