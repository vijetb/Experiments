(function () {
    angular
        .module("Vijet_Server")
        .factory("UserLoginDAOService", userDAOServiceImpl);

    function userDAOServiceImpl($http) {
        var api = {
            validateUser: findUserByCredentials,
            resetUserCredentails: resetUserCredentials
        };
        return api;

        function findUserByCredentials(username, password) {
            console.log("under find user by credentials");
            var url = "/users/validateUser";
            var user = {
                username:username,
                password:password
            };
            return $http.post(url,user);
        }

        function resetUserCredentials(username) {

        }


    }
})();
