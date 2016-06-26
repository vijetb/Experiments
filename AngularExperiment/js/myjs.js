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

    $('#fullpage').fullpage({
        // sectionsColor: ['#ff0000', '#4BBFC3', '#7BAABE', 'whitesmoke', '#ccddff'],
        anchors: ['homePage', 'aboutMePage', 'educationPage', 'skillsPage','projectPage', 'expPage','friendsPage','contactMePage'],
        menu: '#menu',
        scrollOverflow:true,
        autoScrolling:false,
        css3:true,
        scrollingSpeed: 1000
    });

    var height = $("#myimage").height()/2;
    $(".vijet-desc").css({'margin-top':height});

//     $('#app').fullpage({
//         //sectionsColor: ['#1bbc9b', '#4BBFC3', '#7BAABE', 'whitesmoke', '#ccddff'],
//         anchors: ['homePage', 'aboutMePage', 'educationPage', 'skillsPage','projectPage', 'expPage','friendsPage','contactMePage'],
//         menu: '#menu',
// //                verticalCentered:false,
//         scrollingSpeed: 1000
//
//     });
});