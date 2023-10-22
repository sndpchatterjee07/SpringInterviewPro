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

      //alert(response); // This response is the PO Number corresponding to the selected Supplier.

      var selectField = document.getElementById('selectpurchaseOrder');

      //alert(selectField.options.length);

      // Loop through the options and select the one that matches the new value
      /*for (var i = 0; i < selectField.options.length; i++) {
          if (selectField.options[i].value === newValue) {
              selectField.options[i].selected = true;
              break;
          }
      }*/
    }
  };
  // Send the GET request
  xhr.send();
}