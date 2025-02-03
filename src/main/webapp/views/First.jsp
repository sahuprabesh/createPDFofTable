<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mark Entry Form</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            background-color: #cce7f0;
            padding: 20px;
            border-radius: 10px;
        }
        .btn-custom {
            background-color: #bd8ce3;
            color: white;
            font-weight: bold;
            border-radius: 5px;
        }
        .btn-custom:hover {
            background-color: #d14893;
        }
        .report-section {
            background-color: #5d6971;
            padding: 15px;
            border-radius: 10px;
        }
        .table th {
            background-color: #ff8000;
            color: white;
        }
        .pdf-btn {
            background-color: #bd8ce3;
            color: white;
            font-weight: bold;
            padding: 10px;
            border-radius: 5px;
            text-align: center;
            display: block;
            width: 200px;
            margin: 10px auto;
        }
    </style>
</head>
<body>

<div class="container mt-4">
    <!-- Form Section -->
    <div class="form-container shadow-lg">
        <h4 class="text-center">Mark Entry Form</h4>
        <form id="markEntryForm" method="POST" action="formData">
            <div class="row mt-3">
                <div class="col-md-6">
                    <label class="fw-bold">Select Batch:</label>
                    <select id="batchSelect" class="form-select" onchange="myfun(this.value)" name="batchName">
                    
                        <option value="">Select Batch</option>
                        <c:forEach var="batch" items="${Batches }">
                        	<option value="${batch.batchId }">${ batch.batchName}</option>
                        </c:forEach>
                    </select>
                   
                </div>
                <div class="col-md-6">
                    <label class="fw-bold">Select Technology:</label>
                    <select class="form-control" id="getClass" onchange="myfunn(this.value)" name="techName">
            			<option value="">Select Class</option>
        			</select>
                </div>
                <div class="col-md-6 mt-3">
                    <label class="fw-bold">Select Employee Name:</label>
                    <select id="employeeSelect" class="form-select" onchange="myEmp(this.value)" name="empName">
                        <option value="">Select Employee</option>
                    </select>
                </div>
                <div class="col-md-6 mt-3">
                    <label class="fw-bold">Enter Mark:</label>
                    <input type="number" id="markInput" class="form-control" name="mark" placeholder="Enter Marks">
                </div>
            </div>
            <div class="text-center mt-4">
                <button type="submit" class="btn btn-custom px-4">Save</button>
            </div>
        </form>
        
        
        
        
    </div>

    <!-- Report Section -->
   <div class="mt-4 report-section text-white">
    <div class="d-flex justify-content-between">
        <form action="showTable" method="POST" class="w-100">
            <div class="d-flex align-items-center">
                <label class="fw-bold me-2">Select Batch:</label>
                <select id="batchReportSelect" class="form-select d-inline-block w-auto" name="batchNmae">
                    <option value="">Select Batch</option>
                    <c:forEach var="batch" items="${Batches }">
                        <option value="${batch.batchId }">${batch.batchName}</option>
                    </c:forEach>
                </select>
                <!-- Push the button to the right -->
                <button class="btn btn-custom px-4 ms-auto" type="submit">Show Report</button>
            </div>
        </form>
    </div>
</div>


    <!-- Table for Report -->
    <div class="mt-3">
    <%int count=1; %>
        <table class="table table-bordered text-center">
            <thead>
                <tr>
                    <th>Slno</th>
                    <th>Batch Name</th>
                    <th>Batch Start Date</th>
                    <th>Technology Name</th>
                    <th>Employee Name</th>
                    <th>Employee Phone</th>
                    <th>Mark</th>
                     <th>Grade</th>
                    <th>Status</th>
                </tr>
            </thead>
             <tbody id="reportTable">
             
             
             
            <c:forEach var="data" items="${allData}">
            <tr>
               <td><%= count++ %></td>
               <td>${data.batchId.batchName }</td>
               <td>${data.batchId.batchStartDate }</td>
               <td>${data.techId.techName }</td>
               <td>${data.empId.empName }</td>
               <td>${data.empId.empMob }</td>
               <td>${data.markk.mark }</td>
               
              <td>
            <c:choose>
                <c:when test="${data.markk.mark >= 90}">O</c:when>
                <c:when test="${data.markk.mark >= 80}">E</c:when>
                <c:when test="${data.markk.mark >= 60}">A</c:when>
                <c:when test="${data.markk.mark >= 35}">B</c:when>
                <c:otherwise>F</c:otherwise>
            </c:choose>
        </td>
        
        
                <td>
                <c:choose>
                	<c:when test="${data.markk.mark>=70 }">Eligible</c:when>
                	<c:otherwise>Not Eligible</c:otherwise>
                </c:choose>
                </td> 
               </tr>
               </c:forEach>
            </tbody> 
        </table>
    </div>

    <!-- Download PDF Button -->
    <div class="d-flex justify-content-center">
        <a class="btn btn-custom" href="pdf">Download In PDF Format</a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
function myfun(datavalue) {
	const selectElement = document.getElementById('getClass');

    // Clear any existing options
    selectElement.innerHTML = '';

    // Add the default "Select Class" option
    const defaultOption = document.createElement('option');
    defaultOption.value = '';
    defaultOption.text = 'Select Class';
    selectElement.appendChild(defaultOption);
    
    $.ajax({
        url: '/findTech',
        type: 'POST',
        data: { getData: datavalue },
        success: function(result) {
            console.log(result); // Debugging: See the returned JSON data
            
            let options = '<option value="">Select Class</option>'; // Default option
            result.forEach(function(cls) {
            	
            	console.log(cls);
            	const option = document.createElement('option');
                option.value = cls.techId;
                option.text = cls.techName;
                selectElement.appendChild(option);
            });
           
        },
        error: function(xhr, status, error) {
            console.error("Error fetching classes:", error);
        }
    });
}

//based on technology find employee
function myfunn(datavalue) {
	const selectElement = document.getElementById('employeeSelect');

    // Clear any existing options
    selectElement.innerHTML = '';

    // Add the default "Select Class" option
    const defaultOption = document.createElement('option');
    defaultOption.value = '';
    defaultOption.text = 'Select Class';
    selectElement.appendChild(defaultOption);
    
    $.ajax({
        url: '/findEmp',
        type: 'POST',
        data: { getData: datavalue },
        success: function(result) {
            console.log(result); // Debugging: See the returned JSON data
            
            let options = '<option value="">Select Class</option>'; // Default option
            result.forEach(function(cls) {
            	
            	console.log(cls);
            	const option = document.createElement('option');
                option.value = cls.empId;
                option.text = cls.empName;
                selectElement.appendChild(option);
            });
           
        },
        error: function(xhr, status, error) {
            console.error("Error fetching classes:", error);
        }
    });
}


//Employee Name Taking

function myEmp(datavalue) {
	const selectElement = document.getElementById('getClass');
    
    $.ajax({
        url: '/empData',
        type: 'POST',
        data: { getEmp: datavalue },
        success: function(result) {
            console.log(result); // Debugging: See the returned JSON data
            
            let options = '<option value="">Select Class</option>'; // Default option
            result.forEach(function(cls) {
            	
            	console.log(cls);
            	const option = document.createElement('option');
                option.value = cls.empId;
                option.text = cls;
                selectElement.appendChild(option);
            });
           
        },
        error: function(xhr, status, error) {
            console.error("Error fetching classes:", error);
        }
    });
}

</script>

</body>
</html>
