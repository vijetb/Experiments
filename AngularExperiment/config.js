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
            .when("/friendPage/:friendId", {
                templateUrl: "client/views/user/FriendPage.view.client.html",
                 controller: "FriendController",
                controllerAs: "model"
            })
            .when("/DeleteDialog", {
                templateUrl: "client/views/user/DeleteDialog.html"
                // controller: "LoginController",
                //controllerAs: "loginModel"
            })
            .when("/friendProfilePage/:friendId", {
                templateUrl:  "client/views/user/FriendProfile.view.client.html",
                controller:   "FriendProfileController",
                controllerAs: "model"
            });
        // .otherwise(
        //     redirectTo("./client/views/user/login.view.client.html")
        // )
        ;
    }
})();
