(function () {
    angular
        .module("Vijet_Server")
        .controller("FriendController", FriendControllerImpl);
    
    
    function FriendControllerImpl($routeParams,$uibModal,FriendDAOService,LocalBasketService) {
        var vm = this;
        var friendProfile = null;
        vm.publishProfile = publishProfile;
        vm.unPublishProfile = unpublishProfile;
        vm.updateProfileSettings = updateProfileSettings;
        function init() {
            FriendDAOService.getShortFriendProfile($routeParams.friendId)
                .then(function (res) {
                    friendProfile = res.data;
                    vm.friendId = friendProfile._id;
                    vm.name = friendProfile.name;
                    vm.noOfViews = friendProfile.noOfViews;
                    vm.lastLoginTime = friendProfile.lastLoginTime;
                    vm.publishStatus = friendProfile.publishStatus;
                });
        };



        init();

        $scope.$on("updateSettingsSuccessful", function(response) {
            // show on alert that the update is successful.
        });

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

        function updateProfileSettings(friendId) {
            LocalBasketService.setFriendInfo(friendProfile);
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'client/views/user/UpdateProfileSettings.view.client.html',
                controller: 'UpdateFriendProfileSettingsController',
                controllerAs: 'model'
            });
        };
    };
})();


(function () {
    angular
        .module("Vijet_Server")
        .controller("UpdateFriendProfileSettingsController", updateFriendProfileControllerImpl);

    function updateFriendProfileControllerImpl($uibModalInstance,$rootScope,LocalBasketService,FriendDAOService) {
        var friendInfo = null;
        var vm = this;
        vm.okButtonClick = okButtonClickListener;
        vm.cancelButtonClick = cancelButtonClickListener;
        
        friendInfo = LocalBasketService.getFriendInfo();
        vm.data = friendInfo;

        function okButtonClickListener(currentPassword,newPassword1,newPassword2) {
            if(currentPassword == null || newPassword1 == null || newPassword2 ==null){
                vm.error = "Credentails cannot be empty";
                return;
            }

            if(newPassword1 !=newPassword2){
                vm.error = "Passwords don't match!";
                return;
            }

            vm.error = null;
            var updateCredentialsReq = {
              friendId: friendInfo._id,
              currentPassword:  currentPassword,
                newPassword : newPassword1
            };
            FriendDAOService.updateFriendCredentails(updateCredentialsReq)
                .then(function (res) {
                    if(res.data.status === true){
                        $rootScope.$broadcast("updateSettingsSuccessful",true);
                        $uibModalInstance.close();
                    }else if(res.data.status === false){
                        vm.error = "Credentials seems to be wrong! Check the passwords!\n" +
                            "If the problem still persists contact the admin";
                    }
                })

        };

        function cancelButtonClickListener() {
            $uibModalInstance.close();
        }
    };
})();

(function () {
    angular.module('Vijet_Server')
        .factory('LocalBasketService', basketServiceImpl);

    function basketServiceImpl(){
        var friendInfo;

        var api = {
            getFriendInfo : getFriendInfo,
            setFriendInfo : setFriendInfo
        };
        return api;

        function getFriendInfo() {
            return friendInfo;
        };

        function setFriendInfo(friendInfoModel){
            friendInfo = friendInfoModel;
        };
    }
})();



