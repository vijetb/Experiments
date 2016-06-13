//iffy design pattern

(function () {
    angular
        .module("Vijet_Server")
        .config(Config);

    function Config($routeProvider) {
        // $routeProvider.
        //     when("/login",{
        //     templateUrl:"public/views/common/login.view.client.html",
        //     controller: "LoginController",
        //     controllerAs:"loginModel"
        // });


        $routeProvider

        // .when("/", {
        //     templateUrl: "views/home.html"
        // })
            .when("/login", {
                templateUrl: "client/views/user/login.view.client.html",
                controller: "LoginController",
                controllerAs: "loginModel"
            })
            .when("/forgotPassword", {
                templateUrl: "client/views/user/forgot_passwordPage.html",  
                controller: "ForgotPasswordController",
                controllerAs: "forgotPasswordModel"
            })
            .when("/adminPage", {
                templateUrl: "client/views/user/AdminPage.view.client.html",
                controller: "AdminPageController",
                controllerAs: "model"
            })
            .when("/friendPage", {
                templateUrl: "client/views/user/FriendPage.view.client.html"
                // controller: "LoginController",
                //controllerAs: "loginModel"
            })
            .when("/DeleteDialog", {
                templateUrl: "client/views/user/DeleteDialog.html"
                // controller: "LoginController",
                //controllerAs: "loginModel"
            })
            .when("/friendDetailsPage", {
                templateUrl: "client/views/user/friendDetails.view.client.html"
                // controller: "AdminPageController",
                // controllerAs: "model"
            });
        // .otherwise(
        //     redirectTo("./client/views/user/login.view.client.html")
        // )
        ;
    }
})();
