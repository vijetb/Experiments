(function () {
    angular
        .module("Vijet_Server")
        .factory("FriendDAOService", friendDAOServiceImpl);

    function friendDAOServiceImpl($http) {
        var api = {
            getFriendProfileInfo: getFriendProfileInfo,
            updateFriendProfile: updateProfile,
            getShortFriendProfile: getShortFriendProfile,
            publishUnPublishProfile: publishUnpublishProfile,
        };
        return api;

        function getFriendProfileInfo(friendId) {
            return $http.get("/friends/friendProfile/"+friendId);
        };

        function updateProfile(friendProfile) {
            return $http.post("/friends/updateFriendProfile", friendProfile);
        };

        function getShortFriendProfile(friendId) {
            return $http.get("/friends/shortFriendProfile/"+friendId);
        };

        function publishUnpublishProfile(pubUnPubReq) {
            return $http.put("/friends/profileStatus/"+pubUnPubReq);
        };
    }
})();
