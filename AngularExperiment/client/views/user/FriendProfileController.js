(function () {
    angular
        .module("Vijet_Server")
        .controller("FriendProfileController", FriendControllerImpl);

    function FriendControllerImpl($routeParams, FriendDAOService) {
        var vm = this;
        var friendProfile = null;
        
        function init() {
            FriendDAOService.getFriendProfileInfo($routeParams.friendId)
                .then(function (response) {
                    friendProfile = response.data;
                    console.log("Client:" + friendProfile);
                });
        };
        
        init();
        
    };

})();