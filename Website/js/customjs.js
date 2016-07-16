$(document).ready(function () {
    $("section").css("min-height", window.innerHeight);
    $("section").css("width", window.innerWidth);

    var $window = $(window).on('resize', function(){
        $("section").css("min-height", window.innerHeight);
        $("section").css("width", window.innerWidth);
    }).trigger('resize');

    // $('#mynavbar').on('activate.bs.scrollspy', function () {
    //     console.log("under console");
    //     var currentSection = $(".nav li.active > a").text();
    //     console.log("CUrrent Section : " + currentSection);
    //     $("#"+currentSection).click();
    // })

    $( ".project" ).click(function() {
        var projectName = $(this).attr('id');
        window.location.href = "projectDesc.html#"+projectName;
    });

    $( "#contactSendButton" ).click(function() {
        console.log("Contact button clicked");
        var event = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
        
        var contacteeName = $("#contacteeName").val();
        var contacteeEmail = $("#contacteeEmail").val();
        var contacteeMsg = $("#contacteeMsg").val();

        if(contacteeName === null || contacteeName===""){
            $('#contacteeName').addClass('animated shake').one(event,function () {
                $(this).removeClass('animated shake');
            });
        }else if(contacteeEmail === null ||!validateEmail(contacteeEmail) ){
            $('#contacteeEmail').addClass('animated shake').one(event,function () {
                $(this).removeClass('animated shake');
            });
        }else if(contacteeMsg === null || contacteeMsg ===""){
            $('#contacteeMsg').addClass('animated shake').one(event,function () {
                $(this).removeClass('animated shake');
            });
        }else{
            //send the message to vijet
            console.log("Send the messge to vijet");
            // $.post("https://script.google.com/macros/s/AKfycbyx0vAHKPrnEFv0wj1ZcF1feSUDpCwfS3p2rCGpaXuxRBaK-0oA/exec",
            //     {
            //         name: "Donald Duck",
            //         city: "Duckburg"
            //     },
            //     function(data, status){
            //         alert("Data: " + data + "\nStatus: " + status);
            //     });
           // $("#contact_form").submit();
           //  $("#contact_form").submit(function( event ) {
           //      console.log(event);
           //      console.log("sss");
           //      event.preventDefault();
           //  });
            // $.post("https://script.google.com/macros/s/AKfycbyx0vAHKPrnEFv0wj1ZcF1feSUDpCwfS3p2rCGpaXuxRBaK-0oA/exec","SampleData",function(data,status){
            //     console.log(data);
            //     console.log(status);
            // });
            $.ajax({
                url : "https://script.google.com/macros/s/AKfycbyx0vAHKPrnEFv0wj1ZcF1feSUDpCwfS3p2rCGpaXuxRBaK-0oA/exec",
                type: "POST",
                headers:{
                    "Access-Control-Allow-Credentials": "*",
                    "Access-Control-Allow-Headers": "Content-Type",
                    "Access-Control-Allow-Methods": "GET,POST,PUT,DELETE,OPTIONS",
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Origin":"*"
                },
                data : "vijet-badigannavar",
                success: function(data, textStatus, jqXHR)
                {
                    console.log(data);
                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    console.log(errorThrown);
                }
            });

            // var xhr = new XMLHttpRequest();
            // xhr.open("POST", "https://script.google.com/macros/s/AKfycbyx0vAHKPrnEFv0wj1ZcF1feSUDpCwfS3p2rCGpaXuxRBaK-0oA/exec", true);
            // xhr.setRequestHeader("Content-Type", "application/json");
            // xhr.setRequestHeader("Access-Control-Allow-Origin", "*");
            // xhr.send("{value:1}");
        }
        

    });

    function validateEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }

});