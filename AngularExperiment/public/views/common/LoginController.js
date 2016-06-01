(function () {
    angular
        .module("Vijet_Server")
        .controller("LoginController", LoginController);

    function LoginController() {
        var vm = this;
        console.log("under");



        vm.login = function (username, password) {
            console.log(username);
            console.log(password);
        }

    }
})();