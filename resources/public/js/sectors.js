$(document).ready(function (){
  $(".deleteSector").click(deleteSector);
});

function deleteSector(){
var closestTr = $(this).closest('tr');
var sectorId = $(closestTr).children()[0].innerText;
   alert(sectorId);
  $.ajax({
    url: '/remove-sector',
    type: 'DELETE',
    data: {id: sectorId},
    success: function(result) {
         window.location.href = "/sector";
    }
});
};
