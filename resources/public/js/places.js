$(".deletePlace").click(function (){
var closestTr = $(this).closest('tr');
var placeId = $(closestTr).children()[0].innerText;
  $.ajax({
    url: '/remove-place',
    type: 'DELETE',
    data: {id: placeId},
    success: function(result) {
         window.location.href = "/place";
    }
});
});

$(".updatePlace").click(function (){
  var closestTr = $(this).closest('tr');
  var placeId = $(closestTr).children()[0].innerText;
  var newName = $(closestTr).children()[1].innerText;
   $.ajax({
    url: '/update-place',
    type: 'POST',
    data: {id: placeId,
           name: newName
          },
    success: function(result) {
        window.location.href = "/place";
    }
});
});
