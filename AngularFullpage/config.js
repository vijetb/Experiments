//iffy design pattern

(function () {
    angular
        .module("test")
        .config(Config);

    function Config($routeProvider) {
     $routeProvider
            .when("/test", {
                templateUrl: "Test1.html",
                controller: "TestController",
                controllerAs: 'vm'
            })
    }
})();
