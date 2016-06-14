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
            updateFriendCredentails:updateFriendCredentails
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
            console.log(pubUnPubReq);
            return $http.post("/friends/profileStatus/",pubUnPubReq);
        };

        function updateFriendCredentails(updateCredenatilsReq){
            return $http.post("/friends/updateProfileSettings/",updateCredenatilsReq);
        }
    }
})();
