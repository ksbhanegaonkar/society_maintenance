"use strict";

import { Table } from "react-bootstrap";
import './maintenanceGrid.css';
function MyMaintenanceGrid() {
    return (
      <div> 
            <Table className="my-maintenance-grid">
                <thead>
                    <tr>
                    <th>Month</th>
                    <th>Amount</th>
                    <th>Status</th>
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

export default MyMaintenanceGrid;