/*
 *
 *  *     Copyright 2023-2024 Sandeep Chatterjee @ https://sandeepc.in/
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *          http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package in.sandeep.SpringInterviewPro.parshvaHoldings.model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


/**
 * The type Docket.
 *
 * @author sandeep
 * @version 1.0
 */

@Document(collection = "Dockets")
public class Docket {

    @Id
    private String id;
    private String name;

    private String startTime;

    private String endTime;


    private Double hoursWorked;


    private Double ratePerHour;


    private String supplierName;

    private String purchaseOrders;


    // ACCESSORS & MUTATORS.


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Double getRatePerHour() {
        return ratePerHour;
    }

    public void setRatePerHour(Double ratePerHour) {
        this.ratePerHour = ratePerHour;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(String purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    @Override
    public String toString() {
        return "Docket{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", hoursWorked=" + hoursWorked +
                ", ratePerHour=" + ratePerHour +
                ", supplierName='" + supplierName + '\'' +
                ", purchaseOrders='" + purchaseOrders + '\'' +
                '}';
    }
}
