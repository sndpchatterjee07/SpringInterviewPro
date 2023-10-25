# SpringInterviewPro

Welcome to SpringInterviewPro, a comprehensive resource to help you master Java Spring Boot and excel in your software engineering interviews.

## Table of Contents
- [Introduction](#introduction)
- [Getting Started](#getting-started)
- [Challenges](#challenges)

## Introduction

Welcome to SpringInterviewPro, your one-stop resource for mastering Java Spring Boot and acing your software engineering interviews.

## Getting Started

To get started with SpringInterviewPro, simply clone this repository to your local machine.

```
git clone https://github.com/sndpchatterjee07/SpringInterviewPro.git
```

## Challenges

- **[Parshva Screening Challenge](https://docs.google.com/document/d/1QwSIQ6TqZdZv9iet4Ppw5T8Yj2fbNHjld487c3pNNLk/edit)**.

- **Solution**

  - All the codebase for this challenge is located within package `in.sandeep.SpringInterviewPro.parshvaHoldings`.

  - Rest API Endpoint to access the **Docket Creation Form**: `http://localhost:8080/getDocketCreationForm`

    ![Screenshot from 2023-10-24 03-28-58](https://github.com/sndpchatterjee07/SpringInterviewPro/assets/3818950/dbb0d03a-b8de-41a4-a610-e06bde7ee014)



  - List of **Dockets** saved in **MongoDB** Database:
 
    ![docket-list](https://github.com/sndpchatterjee07/SpringInterviewPro/assets/3818950/afe7a087-8333-4506-b9af-0c69cce8d940)


  - Rest API Endpoint to view all **Dockets** saved in database: `http://localhost:8080/viewDockets`
 
    ![img](https://github.com/sndpchatterjee07/SpringInterviewPro/assets/3818950/c19d31c0-dffc-4b43-ab6a-f5ee42e1df0c)

     
  


**Java Code to present the Docket Creation Form:**

  ```
  InputStream inputStream = new FileInputStream ("/resources/static/assets/export29913_FINAL.xls");
  
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
  ``` 


**Java Code to handle the asynchronous request to get the PO number for the selected Supplier as a JSON Array:** 


```
var url = "http://localhost:8080/getPONumber";
var param = "param1="+supplier;
url = url + "?" + param;
xhr.open("GET", url, true);

xhr.onreadystatechange = function() {
   ...
}   

xhr.send();

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
```

