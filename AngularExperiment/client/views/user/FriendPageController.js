(function () {
    angular
        .module("Vijet_Server")
        .controller("FriendController", FriendControllerImpl);
    
    
    function FriendControllerImpl($routeParams,$scope,$uibModal,$location, FriendDAOService,UserLoginDAOService,LocalBasketService) {
        var vm = this;
        var friendProfile = null;
        vm.publishProfile = publishProfile;
        vm.unPublishProfile = unpublishProfile;
        vm.updateProfileSettings = updateProfileSettings;
        vm.updateProfile = updateProfile;

        if($routeParams.status != null){
            vm.msg = "Profile Updated!";
        }

        function init() {
            UserLoginDAOService.getShortUserProfile($routeParams.userId)
                .then(function (res) {
                    friendProfile = res.data;
                    console.log(friendProfile);
                    vm.friendId = friendProfile._id;
                    vm.name = friendProfile.name;
                    vm.noOfViews = friendProfile.noOfViews;
                    vm.lastLoginTime = friendProfile.lastLogin;
                    vm.publishStatus = friendProfile.publishStatus;
                });
        };



        init();

        $scope.$on("updateSettingsSuccessful", function(response) {
                vm.msg = "User Credentails updated !!!";
        });

        function publishProfile(friendId) {
            var pubUnpubReq = {
                _id:friendId,
                isPublish : true
            }
            UserLoginDAOService.publishProfileStatus(pubUnpubReq)
                .then(function (res) {
                    if(res.data.status === true){
                        vm.msg = "Your profile is published! Check the website to view your profile!";
                        vm.publishStatus = true;
                        return;
                    }else{
                        vm.error = "Error in publishing your profile!";
                        vm.publishStatus = false;
                        return;
                    }
                });
        };

        function unpublishProfile(friendId) {
                var pubUnpubReq = {
                _id:friendId,
                isPublish : false
            }
            UserLoginDAOService.publishProfileStatus(pubUnpubReq)
                .then(function (res) {
                    if(res.data.status === true){
                        vm.msg = "Your profile is unpublished from the website!";
                        vm.publishStatus = false;
                        return;
                    }else{
                        vm.error = "Error in unpublishing your profile!";
                        vm.publishStatus = true;
                        return;
                    }
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

        function updateProfile() {
            $location.url("/friendProfilePage/"+friendProfile._id);
        };
    };
})();


(function () {
    angular
        .module("Vijet_Server")
        .controller("UpdateFriendProfileSettingsController", updateFriendProfileControllerImpl);

    function updateFriendProfileControllerImpl($uibModalInstance,$rootScope,LocalBasketService,UserLoginDAOService) {
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
              userId: friendInfo._id,
              currentPassword:  currentPassword,
                newPassword : newPassword1
            };
            UserLoginDAOService.updateUserCredentials(updateCredentialsReq)
                .then(function (res) {
                    if(res.data.status === true){
                        $rootScope.$broadcast("updateSettingsSuccessful",true);
                        $uibModalInstance.close();
                    }else if(res.data.status === false){
                        vm.error = "Credentials seems to be wrong! Check the passwords!\n" +
                            "If the problem still persists contact Vijet";
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



