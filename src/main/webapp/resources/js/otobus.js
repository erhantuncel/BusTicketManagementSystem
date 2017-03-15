// Datepicker
$(document).ready(function() {
    $( "#seferTarih" ).datepicker({ 
      minDate: 0, 
      maxDate: "+1M", 
      dateFormat: "dd.mm.yy"
    });
});

$(document).ready(function() {
    $( "#dogumTarihi" ).datepicker({
      /*
      minDate: 0, 
      maxDate: "+1M", 
      */
      dateFormat: "dd.mm.yy"
    });
});

// Payment modal
$(document).ready(function () {
    $('body').on('click', '.odeme-btn', function (e) {
        $(this.form).submit();
        $('#odemeModal').modal('hide');
        alert('Ödeme Başarılı');
    });
});

// Adding row for passenger via checkbox
$(document).ready(function() {
    $("input[type='checkbox']").change(function() {
        var table = $("#yolcuBilgi");
        var id = $(this).val();

        if($(this).is(":checked")) { // checked
           
            var td = $(this).parent("td");
            var tdIndex = td.attr("data-col");

            var prot = null;
            if(tdIndex == 1) {
                // Single seat
                prot = table.find(".rowtemplate").clone();
            } else if(tdIndex == 2) {
                var pGender = td.next().attr("pGender");
                if(pGender == "m") {
                    prot = table.find(".rowtemplateForMale").clone();
                } else if(pGender == "f") {
                    prot = table.find(".rowtemplateForFemale").clone();
                } else {
                    prot = table.find(".rowtemplate").clone()
                }
            } else if(tdIndex == 3) {
                var pGender = td.prev().attr("pGender");
                if(pGender == "m") {
                    prot = table.find(".rowtemplateForMale").clone();
                } else if(pGender == "f") {
                    prot = table.find(".rowtemplateForFemale").clone();
                } else {
                    prot = table.find(".rowtemplate").clone()
                }
            }

            prot.attr("class", "")
            prot.find("#koltukNo").text(id);
            
            $(".koltukSecimUyari").addClass("hide");
            table.find("tbody").append(prot);
            $("#btnOdeme").removeAttr("disabled");


        } else { // unchecked
            var label = table.find("label");
            //alert("label size = " + label.length);

            label.each(function(){
                var labelText = $(this).text();
                if( labelText == id) {
                    
                    //alert("labelText " + labelText);
                    $(this).parents("tr").remove();
                }
            });
        }
    })
})