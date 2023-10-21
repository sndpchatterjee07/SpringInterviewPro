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

  Rest API Endpoint : `http://localhost:8080/getDocketCreationForm`
    
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
        supplierToPurchaseOrderNumbersMap = purchaseOrderFileReader.getPurchaseOrderNumbersBySupplier ();

        docket = new Docket ();
        docket.setSupplierName (suppliers);
        docket.setPurchaseOrder (supplierToPurchaseOrderNumbersMap);

        modelAndView.setViewName ("create_docket");
        modelAndView.addObject ("docketInfo", docket);
        modelAndView.addObject ("suppliers", docket.getSupplierName ());
        modelAndView.addObject ("purchaseOrders", docket.getPurchaseOrder ().values ());

        return modelAndView;
    }
  ```  
  All the codebase for this challenge is located within package `in.sandeep.SpringInterviewPro.parshvaHoldings`.

