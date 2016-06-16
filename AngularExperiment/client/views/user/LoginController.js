(function () {
    angular
        .module("Vijet_Server")
        .controller("LoginController",LoginControllerImpl);


    function LoginControllerImpl($location,UserLoginDAOService, $uibModal) {
        var vm = this;
        vm.validate = validateUserCredentails,
        vm.loginHelp = loginHelp;

        function validateUserCredentails(userEmail, password){
            if(userEmail == null || password == null){
                vm.error = "Enter valid email/password";
                return;
            }

            var userCredentials = {
                email:userEmail,
                password:password
            };
            UserLoginDAOService
                .validateUser(userCredentials)
                .then(function (res) {
                    if(res.data==null){
                        vm.error = "User doesn't exist!";
                        return;
                    }
                    if(res.data.isAdmin === true){
                        $location.url("/adminPage");
                    }else{
                        $location.url("/friendPage/"+res.data._id);
                    }
                });
        };

        function loginHelp() {
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'client/views/user/LoginHelpDialog.html',
                controller: "LoginHelpController",
                controllerAs: 'model'
            });
        };

    }
})();

(function() {
    angular.module('Vijet_Server')
        .controller("LoginHelpController", LoginHelpControllerImpl);

        function LoginHelpControllerImpl($uibModalInstance) {
           var vm = this;
            vm.closeDialog = closeDialog;

            function closeDialog() {
                $uibModalInstance.close();
            };
        }

})();