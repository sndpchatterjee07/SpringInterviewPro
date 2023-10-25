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
package in.sandeep.SpringInterviewPro.parshvaHoldings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mongodb.client.*;
import in.sandeep.SpringInterviewPro.parshvaHoldings.model.Docket;
import in.sandeep.SpringInterviewPro.parshvaHoldings.utility.PurchaseOrderFileReader;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;


import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The type RoutingController.
 *
 * @author sandeep
 * @version 1.0
 */
@RestController
public class RoutingController implements ErrorController {

    private final ModelAndView modelAndView = new ModelAndView ();

    private PurchaseOrderFileReader purchaseOrderFileReader;

    private List<String> suppliers;

    private List<String> supplierToPurchaseOrderNumbers;

    private Docket docket;

    private final String connectionString = "mongodb+srv://RoomBookingAdmin:EGPm7N16AQXe2Lcw@cluster0.sp97mmv.mongodb.net";


    /**
     * Gets Docket Creation Form.
     *
     * @return the Docket Creation Form
     * @throws IOException the IOException
     */
    @RequestMapping(value = "/getDocketCreationForm", method = RequestMethod.GET)
    public ModelAndView getDocketCreationPage() throws IOException {
        purchaseOrderFileReader = new PurchaseOrderFileReader ();
        suppliers = purchaseOrderFileReader.getSuppliers ();

        docket = new Docket ();

        modelAndView.setViewName ("create_docket");
        modelAndView.addObject ("docketInfo", docket);
        modelAndView.addObject ("suppliers", suppliers);

        return modelAndView;
    }

    /**
     * Handle Error.
     *
     * @return the Error Page
     */
    @RequestMapping(value = "/error")
    public ModelAndView handleError() {
        modelAndView.setViewName ("error_page");
        return modelAndView;
    }

    /**
     * Create Docket and insert in MongoDB Database.
     *
     * @param docketInfo the docketInfo
     */
    @RequestMapping(value = "/createDocket", method = RequestMethod.POST)
    public ModelAndView createDocket(HttpServletRequest httpServletRequest) {

        // 1. FETCH THE FORM FIELDS...

        String name = httpServletRequest.getParameter ("name");

        String startTime = httpServletRequest.getParameter ("startTime");

        String endTime = httpServletRequest.getParameter ("endTime");

        Double hoursWorked = Double.parseDouble (httpServletRequest.getParameter ("hoursWorked"));

        Double ratePerHour = Double.parseDouble (httpServletRequest.getParameter ("ratePerHour"));

        String supplierName = httpServletRequest.getParameter ("supplierName");

        String purchaseOrders = httpServletRequest.getParameter ("purchaseOrders");


        // 2. POPULATE DOCKET TYPE TO BE SAVED IN THE DATABASE.
        docket.setName (name);
        docket.setStartTime (startTime);
        docket.setEndTime (endTime);
        docket.setHoursWorked (hoursWorked);
        docket.setRatePerHour (ratePerHour);
        docket.setSupplierName (supplierName);
        docket.setPurchaseOrders (purchaseOrders);


        // 3. CONNECT TO MONGODB CLIENT.
        MongoClient mongoClient = MongoClients.create (connectionString);
        MongoDatabase database = mongoClient.getDatabase ("RoomBookingSystem");
        MongoCollection<Document> collection = database.getCollection ("Dockets");

        // 4. INSERT THE DOCKET.
        Gson gson = new Gson ();  // Deserialize Docket object to Json String
        String json = gson.toJson (docket);
        Document document = Document.parse (json); // Parse to bson document and insert
        collection.insertOne (document);

        modelAndView.setViewName ("docket_creation_success");
        return modelAndView;
    }


    /**
     * Handle the asynchronous request to get the PONumber for the selected Supplier.
     *
     * @param param1 The selected Supplier.
     * @return The list of PONumbers as a JSON Array.
     * @throws IOException The IOException
     */
    @RequestMapping(value = "/getPONumber", method = RequestMethod.GET)
    public ArrayNode getPONumber(@QueryParam("param1") String param1) throws IOException {
        String selectedSupplierName = param1;
        ObjectMapper mapper = new ObjectMapper ();
        purchaseOrderFileReader = new PurchaseOrderFileReader ();
        supplierToPurchaseOrderNumbers = purchaseOrderFileReader.getPurchaseOrderNumbersBySupplier (selectedSupplierName);
        ArrayNode jsonArrayOfPONumbers = mapper.valueToTree (supplierToPurchaseOrderNumbers);

        return jsonArrayOfPONumbers;
    }

    /**
     * Gets Dockets.
     *
     * @return the Dockets
     */
    @RequestMapping(value = "/viewDockets", method = RequestMethod.GET)
    public void getDockets() {
        modelAndView.setViewName ("show_dockets");

        MongoClient mongoClient = MongoClients.create (connectionString);
        MongoDatabase database = mongoClient.getDatabase ("RoomBookingSystem");
        MongoCollection<Document> collection = database.getCollection ("Dockets");

        // Retrieve all documents
        MongoCursor<Document> cursor = collection.find ().iterator ();
        try {
            while (cursor.hasNext ()) {
                Document document = cursor.next ();
                System.out.println (document.toJson ());
            }
        } finally {
            cursor.close ();
        }
        mongoClient.close (); // Close the MongoDB connection
    }
}
