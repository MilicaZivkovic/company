$( document ).ready(function() {
  $.ajaxSetup({ cache: false });
  $(".deleteEmployee").click(deleteEmployee);
  $(".insertEmployee").click(insertEmployee);
});

function deleteEmployee(){
  var closestTr = $(this).closest('tr');
  var emplId = $(closestTr).children()[0].innerText;
  $.ajax({
    url: '/remove-employee',
    type: 'DELETE',
    data: {id: emplId},
    success: function(result) {
         window.location.href = "/employee";
    }
});
};

function insertEmployee(){
  var id = $('#employeeId').val();
  var name = $('#employeeName').val();
  var surname = $('#employeeSurname').val();
  var yearsOfService = $('#employeeYearsOfService').val();
  var selectedPlace = $('#places :selected').val();
  var selectedSector = $('#sectors :selected').val();
  $.ajax({
    url: '/save-employee',
    type: 'POST',
    data: {id: id,
           name: name,
           surname: surname,
           yrs_of_svc: yearsOfService,
           place_id: selectedPlace,
           sector_id: selectedSector
          },
    success: function(result) {
       window.location.href = "/employee";
    }
 });
};




