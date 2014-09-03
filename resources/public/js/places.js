$(document).ready(function (){
  $(".deletePlace").click(deletePlace);
});

function deletePlace(){
var closestTr = $(this).closest('tr');
var placeId = $(closestTr).children()[0].innerText;
   alert(placeId);
  $.ajax({
    url: '/remove-place',
    type: 'DELETE',
    data: {id: placeId},
    success: function(result) {
         window.location.href = "/place";
    }
});
};
