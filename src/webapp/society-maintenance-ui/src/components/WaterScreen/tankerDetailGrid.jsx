"use strict";

import { Table } from "react-bootstrap";
function TankerDetailGrid() {
    return (
      <div> 
            <Table className="my-maintenance-grid">
                <thead>
                    <tr>
                    <th>Date</th>
                    <th>Number Of Tanker Received</th>
                    <th>Total Amount</th>
                    <th>Total Capicity</th>
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

export default TankerDetailGrid;