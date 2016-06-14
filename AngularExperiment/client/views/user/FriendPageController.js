(function () {
    angular
        .module("Vijet_Server")
        .controller("FriendController", FriendControllerImpl);
    
    
    function FriendControllerImpl($routeParams,FriendDAOService) {
        var vm = this;
        var friendProfile = null;
        vm.publishProfile = publishProfile;
        vm.unPublishProfile = unpublishProfile;
        function init() {
            FriendDAOService.getShortFriendProfile($routeParams.friendId)
                .then(function (res) {
                    friendProfile = res.data;
                    vm.name = friendProfile.name;
                    vm.noOfViews = friendProfile.noOfViews;
                    vm.lastLoginTime = friendProfile.lastLoginTime;
                    vm.publishStatus = friendProfile.publishStatus;
                });
        };



        init();


        function publishProfile(friendId) {
            var pubUnpubReq = {
                _id:friendId,
                isPublish : true
            }
            FriendDAOService.publishUnPublishProfile(pubUnpubReq)
                .then(function (res) {
                    console.log(res.data);
                });
        };

        function unpublishProfile(friendId) {
                var pubUnpubReq = {
                _id:friendId,
                isPublish : false
            }
            FriendDAOService.publishUnPublishProfile(pubUnpubReq)
                .then(function (res) {
                console.log(res.data);
            });
        };
    };
})();
