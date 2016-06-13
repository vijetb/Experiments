(function () {
    angular
        .module("Vijet_Server")
        .controller("LoginController",LoginControllerImpl);


    function LoginControllerImpl($location,UserLoginDAOService) {
        var vm = this;
        vm.validate = validateUserCredentails;


        function validateUserCredentails(userEmail, password){
            if(userEmail == null || password == null){
                vm.error = "Empty credentials! Try again!!!";
                return;
            }

            //todo handle username, password
            UserLoginDAOService
                .validateUser(userEmail,password)
                .then(function (response) {
                    console.log("Received response from server");
                    //handle response
                    if(response.data.status === true && response.data.isAdmin === true){
                        $location.url("/adminPage");
                    }else if(response.data.status === true && response.data.isAdmin === false){
                        $location.url("/friendPage");
                    }
                });
        };

    }
})();
