function getSelectedSupplier(supplier){

  //alert(supplier);

  // 1. Create an instance of XMLHttpRequest
  var xhr = new XMLHttpRequest();

  var url = "http://localhost:8080/getPONumber";

  var param = "param1="+supplier;

  url = url + "?" + param;

 // 2. Open the request with the GET method and parameters
  xhr.open("GET", url, true);

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {

      var response = xhr.responseText;

      var jsonResponse = JSON.parse(response);

      //console.log(jsonResponse); // This response is the PO Number corresponding to the selected Supplier, correctly as expected.

      var selectField = document.getElementById("selectpurchaseOrder");

      // Remove the previously populated Purchase Order Dropdown values.
      while (selectField.firstChild) {
        selectpurchaseOrder.removeChild(selectpurchaseOrder.firstChild);
      }

      // Populate the Purchase Order Dropdown with the currently selected Supplier.
      for (var i = 0; i < jsonResponse.length; i++) {
          var item = jsonResponse[i];
          var option = document.createElement("option");
          option.value = jsonResponse[i];
          option.text =  jsonResponse[i];
          selectField.appendChild(option);
      }
    }
  };
  // Send the GET request
  xhr.send();
}