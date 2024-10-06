"use strict";

import { Table } from "react-bootstrap";
import './maintenanceGrid.css';
function SocietyMaintenanceGrid() {
    return (
      <div> 
            <Table className="my-maintenance-grid">
                <thead>
                    <tr>
                    <th>Month</th>
                    <th>Total Amount Paid</th>
                    <th>Percentage Paid</th>
                    <th>Show Details</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                    <td>1</td>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                    </tr>
                    <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                    </tr>
                </tbody>
            </Table>
      </div>
    );
}

export default SocietyMaintenanceGrid;