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
package in.sandeep.SpringInterviewPro.controller;

import in.sandeep.SpringInterviewPro.model.Docket;
import in.sandeep.SpringInterviewPro.utility.PurchaseOrderFileReader;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
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

    private Map<String, String> suppliers;


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
        modelAndView.setViewName ("create_docket");
        modelAndView.addObject ("docketInfo", new Docket ());
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
     * Create Docket.
     *
     * @param docketInfo the docketInfo
     */
    @RequestMapping(value = "/createDocket", method = RequestMethod.POST)
    public void createDocket(@ModelAttribute Docket docketInfo) {
        modelAndView.addObject ("docketInfo", docketInfo);
        //System.out.println (docketInfo.getStartTime ());
    }

}
