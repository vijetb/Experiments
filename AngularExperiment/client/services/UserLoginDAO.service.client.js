(function () {
    angular
        .module("Vijet_Server")
        .factory("UserLoginDAOService", userDAOServiceImpl);

    function userDAOServiceImpl($http) {
        var api = {
            validateUser: findUserByCredentials,
            resetUserCredentials: resetUserCredentials,
            getShortUserProfile: getShortUserProfile,
            updateUserCredentials: updateUserCredentials,
            publishProfileStatus:publishProfileStatus
        };
        return api;

        /**
         * Returns the user if exists, otherwise returns null.
         */
        function findUserByCredentials(userCredentials) {
            return $http.post("/users/validateUser",userCredentials);
        };


        /**
         * Reset the credentials of the user
         * @param username
         */
        function resetUserCredentials(email) {
            return $http.put("/user/forgotPassword",email);
        };

        function getShortUserProfile(userId) {
            return $http.get("/user/shortProfile/"+userId);
        };
        
        function updateUserCredentials(userCredentials) {
            return $http.put("/user/userSettings/",userCredentials);
        };

        function publishProfileStatus(publishStatus) {
            return $http.put("user/publishStatus/",publishStatus);
        };


    }
})();
