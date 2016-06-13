(function () {
    angular
        .module("Vijet_Server")
        .factory("FriendDAOService", friendDAOServiceImpl);

    function friendDAOServiceImpl($http) {
        var api = {
            getFriendProfileInfo: getFriendProfileInfo
        };
        return api;

        function getFriendProfileInfo(friendId) {
            return $http.get("/friends/friendProfile/"+friendId);
        }

    }
})();
