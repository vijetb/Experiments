(function () {
    angular
        .module("Vijet_Server")
        .controller("ForgotPasswordController", ForgotPasswordControllerImpl);

    function ForgotPasswordControllerImpl(UserLoginDAOService) {
        var vm = this;
        vm.resetUserCredentials = resetUserCredentials;
       vm.visibility = false;
        vm.LoginVisibility = true;

        vm.status = "Please type your e-mail id and you will receive an email containing the information regarding resetting your password. If you have problems mail your queries to bvijet@gmail.com";

        function resetUserCredentials(userEmail) {
            //check for useremail
            console.log("reset");
            var emailId = userEmail;

            vm.status = "An email with the new credentials has been sent to "+ userEmail + ". Please follow the instructions as described in the mail.";
            vm.visibility = true;
            vm.LoginVisibility = false;
            console.log(vm.visibility);
            console.log(vm.LoginVisibility);

            // UserLoginDAOService
            //     .resetUserCredentails(userEmail)
            //     .then(function (response) {
            //         if(response.getData()==="success"){
            //             vm.response = "An email with the new credentials has been sent to "+ emailId + ". Please follow the instructions as described in the mail.";
            //             vm.visibility = true;
            //             vm.LoginVisibility = false;
            //         }
            //     });
        };
    }
})();
