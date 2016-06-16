(function () {
    angular
        .module("Vijet_Server")
        .factory("UserProfileDAOService", userDAOServiceImpl);

    function userDAOServiceImpl($http) {
        var api = {
            getUserProfile: getUserProfile,
            updateUserProfile: updateUserProfile
        };
        return api;

        /**
         * Returns the user if exists, otherwise returns null.
         */
        function getUserProfile(userId) {
            return $http.get("/user/userProfile/"+userId);
        };

        function updateUserProfile(userProfile) {
            console.log(userProfile);
            return $http.put("/user/updateUserProfile/",userProfile);
        };


    }
})();
