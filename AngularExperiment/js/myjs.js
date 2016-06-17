$(document).ready(function () {
    console.log("under ready function");

    var circleHeight = $(".imageClass").height()/2;
    console.log(circleHeight);
    $(".deletePic").css({ top: circleHeight }).hide();
    $(".editPic").css({ top: circleHeight }).hide();

    $(".imageClass").mouseenter(function(){
        $(".deletePic").fadeIn().show();
        $(".editPic").fadeIn().show();
    });

    $(".imageClass").mouseleave(function(){
        $(".deletePic").fadeOut();
        $(".editPic").fadeOut();
    });
});