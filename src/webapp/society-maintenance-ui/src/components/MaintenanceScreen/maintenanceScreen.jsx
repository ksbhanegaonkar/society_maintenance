import { Button, Dropdown, Form, Modal } from 'react-bootstrap';
import './maintenanceScreen.css'
import MyMaintenanceGrid from './myMaintenanceGrid';
import SocietyMaintenanceGrid from './societyMaintenanceGrid';
import { useState } from 'react';
import { useMutation } from 'react-query';
import { SaveMaintenanceDetail } from '../../data/fetchData';
function MaintenanceScreen() {
    const [period, setPeriod] = useState('');
    const [monthSelected, setMonthSelected] = useState('');
    const [modalShow, setModalShow] = useState(false);
    const periodDropdownList = {
        oneMonth: 'One Month',
        twoMonths: 'Two Months',
        threeMonths: 'Three Months',
        sixMonths: 'Six Months'
    };
    const monthDropdownData = {
        oneMonth: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'],
        twoMonths: ['Jan to Feb', 'Feb to Mar', 'Mar to Apr', 'Apr to May', 'May to Jun', 'Jun to Jul', 'Jul to Aug', 'Aug to Sept', 'Sept to Oct', 'Oct to Nov', 'Nov to Dec'],
        threeMonths: ['Jan to Mar', 'Feb to Apr', 'Mar to May', 'Apr to Jun', 'May to Jul', 'Jun to Aug', 'Jul to Sept', 'Aug to Oct', 'Sept to Nov', 'Oct to Dec'],
        sixMonths: ['Jan to Jun', 'Feb to Jul', 'Mar to Aug', 'Apr to Sept', 'May to Oct', 'Jun to Nov', 'Jul to Dec'],
    };
    const amountDropdownList = {
        oneMonth: '3000',
        twoMonths: '6000',
        threeMonths: '9000',
        sixMonths: '18,000'
    };
    const handlePeriodDropdownSelect = (eventKey) => {
        setPeriod(eventKey);
    };
    const handleMonthDropdownSelect = (eventKey) => {
        setMonthSelected(eventKey);
    };
    const { mutate, isLoading } = useMutation(SaveMaintenanceDetail, {
        onSuccess: data => {
          console.log(data);
          const message = "success"
        },
        onError: (e) => {
          alert("there was an error"+e)
        }
      });
    const handleReceiptSubmitForApproval = () => {
        mutate(period, monthSelected, amountDropdownList[period]);
        setModalShow(false);
    }
  return (
    <div className='maintenance-screen'>
        <div className='maintenance-screen-header'>
            Maintenance Details
        </div>
        <div className='maintenance-screen-body'>
            <div className='my-maintenance-detail'>
                <div className='my-maintenance-detail-header'>
                    My Maintenance Details
                </div>
                <div className='my-maintenance-detail-body'>
                    <MyMaintenanceGrid/>
                </div>
                <div className='my-maintenance-detail-footer'>
                    <button className='record-paid-maintenance-button' onClick={() => setModalShow(true)}>Record Paid Maintenance</button>
                    <div className='maintenance-footer-summary'>
                        <div>Total Maintenance For Year : 3000</div>
                        <div>Paid : 3000</div>
                        <div>Remaining : 3000</div>
                    </div>
                </div>
            </div>
            <div className='society-maintenance-detail'>
                <div className='society-maintenance-detail-header'>
                    Society Maintenance Details
                </div>
                <div className='society-maintenance-detail-body'>
                    <SocietyMaintenanceGrid/>
                </div>
                <div className='society-maintenance-detail-footer'>
                    <div>Total Maintenance For Year : 3000</div>
                    <div>Paid : 3000</div>
                    <div>Remaining : 3000</div>
                </div>
            </div>
        </div>
        {modalShow && 
        <div className='show record-paid-maintenance-modal'>
            <div className='show record-paid-maintenance-modal-content'>
                <div className='maintenance-modal-period-dropdown'>
                    <div className='maintenance-modal-period-dropdown-header'>Period : </div>
                    <div  className='maintenance-modal-period-dropdown-content'>
                        <Dropdown onSelect={handlePeriodDropdownSelect}>
                        <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                            {period !== '' ? periodDropdownList[period] : 'Select Period'}
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            <Dropdown.Item eventKey='oneMonth'>{periodDropdownList['oneMonth']}</Dropdown.Item>
                            <Dropdown.Item eventKey='twoMonths'>{periodDropdownList['twoMonths']}</Dropdown.Item>
                            <Dropdown.Item eventKey='threeMonths'>{periodDropdownList['threeMonths']}</Dropdown.Item>
                            <Dropdown.Item eventKey='sixMonths'>{periodDropdownList['sixMonths']}</Dropdown.Item>
                        </Dropdown.Menu>
                        </Dropdown>
                    </div>
                </div>
                <div className='maintenance-modal-period-dropdown'>
                    <div className='maintenance-modal-period-dropdown-header'>Month(s) : </div>
                    <div  className='maintenance-modal-period-dropdown-content'>
                        <Dropdown onSelect={handleMonthDropdownSelect}>
                        <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                            {monthSelected !== '' ? monthSelected : 'Select Timeline'}
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            {period && period !== '' && monthDropdownData[period] && monthDropdownData[period].map(p => <Dropdown.Item id={p} eventKey={p}>{p}</Dropdown.Item>)}
                        </Dropdown.Menu>
                        </Dropdown>
                    </div>
                </div>
                <div className='maintenance-modal-period-dropdown'>
                    <div className='maintenance-modal-period-dropdown-header'>Amount : </div>
                    <div  className='maintenance-modal-period-dropdown-content'>
                        <Dropdown disabled>
                        <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                            {period && period !== '' && amountDropdownList[period] ? 'Rs. ' + amountDropdownList[period] + '/-' : 'Select Amount'}
                        </Dropdown.Toggle>
                        </Dropdown>
                    </div>
                </div>
                <div className='maintenance-modal-period-dropdown'>
                    <div className='maintenance-modal-period-dropdown-header'>Upload Receipt : </div>
                    <div  className='maintenance-modal-period-dropdown-content'>
                        <Form.Group controlId="formFile" className="mb-3">
                            <Form.Control type="file" />
                        </Form.Group>
                    </div>
                </div>
                <div className='maintenance-modal-period-dropdown'>
                    <button className='submit-maintenance-button' onClick={handleReceiptSubmitForApproval}>Submit Receipts For Approval</button>
                </div>
            </div>
        </div>
        }
    </div>
  );
}

export default MaintenanceScreen;