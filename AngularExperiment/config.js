//iffy design pattern

(function () {
    angular
        .module("Vijet_Server")
        .config(Config);

    function Config($routeProvider) {
        $routeProvider.
            when("/login",{
            templateUrl:"public/views/common/login.html",
            controller: "LoginController",
            controllerAs:"loginModel"
        });
    }
})();
