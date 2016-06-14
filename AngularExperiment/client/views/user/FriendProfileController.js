(function () {
    angular
        .module("Vijet_Server")
        .controller("FriendProfileController", FriendControllerImpl);

    function FriendControllerImpl($routeParams, FriendDAOService) {
        var vm = this;
        var friendProfile = null;
        vm.updateProfile = updateProfile;
        vm.friend = false;
            function init() {
            FriendDAOService.getFriendProfileInfo($routeParams.friendId)
                .then(function (response) {
                    friendProfile = response.data;

                    console.log("Client:" + friendProfile);
                });
        };
        
        init();

        function updateProfile(friendImg,friendName,friendDesignation,friendCompany,friendEmail,friendPhoneNumber, friendComment, adminComment) {
            var friendMsg = {
                _id: friendProfile._id,
                imgUrl: friendImg,
                friendName:friendName,
                friendDesignation:friendDesignation,
                friendCompany:friendCompany,
                friendEmail:friendEmail,
                friendPhoneNumber:friendPhoneNumber,
                friendComment:friendComment,
                adminComment:adminComment
            };
            FriendDAOService.updateFriendProfile(friendMsg)
                .then(function (res) {
                    console.log(res.data.status);
                });
        };
        
    };

})();