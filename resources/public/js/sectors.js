$(document).ready(function (){
  $(".deleteSector").click(deleteSector);

  $(".editable").dblclick(function (){
    var OriginalContent = $(this).text();
    $(this).addClass("cellEditing");
    $(this).html("<input type='text' value='" + OriginalContent + "' />");
    $(this).children().first().focus();
    $(this).children().first().keypress(function (e){
      if (e.which == 13){
        var newContent = $(this).val();
        $(this).parent().text(newContent);
        $(this).parent().removeClass("cellEditing"); } });

    $(this).children().first().blur(function(){
      $(this).parent().text(OriginalContent);
      $(this).parent().removeClass("cellEditing"); }); });
});

function deleteSector(){
var closestTr = $(this).closest('tr');
var sectorId = $(closestTr).children()[0].innerText;
  $.ajax({
    url: '/remove-sector',
    type: 'DELETE',
    data: {id: sectorId},
    success: function(result) {
         window.location.href = "/sector";
    }
});
};

$(".updateSector").click(function (){
  var closestTr = $(this).closest('tr');
  var sectorId = $(closestTr).children()[0].innerText;
  var newName = $(closestTr).children()[1].innerText;
   $.ajax({
    url: '/update-sector',
    type: 'POST',
    data: {id: sectorId,
           name: newName
          },
    success: function(result) {
        window.location.href = "/sector";
    }
});
});
