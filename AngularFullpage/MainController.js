(function () {
    angular
        .module("test")
        .controller("TestController", TestController);
    
    function TestController() {
        console.log("UNDER TEST CONTROLLER");

        var vm = this;

        vm.mainOptions = {
            sectionsColor: ['#1bbc9b', '#4BBFC3', '#7BAABE'],
            anchors: ['homePage', 'aboutMePage', 'educationPage'],
            menu: '#menu'
        }
    }
})();
