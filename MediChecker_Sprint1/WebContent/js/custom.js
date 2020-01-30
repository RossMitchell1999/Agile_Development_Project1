function validateCode() {
    var code = document.getElementById("code");
    
    if (code.checked) {
        // alert("this works");
        
        var input = document.getElementById("inputSearchCriteria").value;
        var len = input.length;

        if (len == 3) {
            // alert("this works");            
            inputInt = parseInt(input);
            
            if (inputInt < 000 || inputInt > 999) {
                alert("Invalid Code");
                return false;
            }
            else {
                return true;
            }
        }
        else {
            alert("Invalid Code");
            return false;
        }
    }
}

function insertTable() {
    var table = document.getElementById("resultsTable");

    // var row = table.insertRow("1");
    // var cell = row.insertCell("0");
    // cell[0].innerHTML("newCell");

    for (var i=1; i<6; i++) {
        var row = table.insertRow("1");
    }
}
        
function test() {
    alert("this works");
}
