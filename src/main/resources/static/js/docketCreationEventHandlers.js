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

      console.log(jsonResponse); // This response is the PO Number corresponding to the selected Supplier.

    }
  };
  // Send the GET request
  xhr.send();
}