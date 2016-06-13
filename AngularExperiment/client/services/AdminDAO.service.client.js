(function () {
    angular
        .module("Vijet_Server")
        .factory("AdminDAOService", adminDAOServiceImpl);

    function adminDAOServiceImpl($http) {
        var api = {
            getAllFriends: getAllFriends,
            addFriend:addFriend,
            removeFriend: removeFriend,
            inviteFriend: inviteFriend

        };
        return api;

        function getAllFriends() {
            return $http.get("/friends/friendList")
        }

        function addFriend(name,emailId){
            var newFriend = {
                emailId: emailId,
                name:name
            }
            return $http.post("/friends/addFriend",newFriend);
        }

        function removeFriend(unFriendReq){
            return $http.post("/friends/removeFriend",unFriendReq);
        }

        function inviteFriend(inviteReq){
            return $http.post("/friends/inviteFriend",inviteReq);
        }


    }
})();
