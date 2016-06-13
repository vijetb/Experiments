
(function () {
    angular
        .module("Vijet_Server")
        .controller("AdminPageController", AdminPageControllerImpl);

    function AdminPageControllerImpl($scope, AdminDAOService, LocalBasketService,$uibModal, $log) {
        var vm = this;
        vm.addFriend = addNewFriend;
        vm.removeFriend = removeFriend;
        vm.inviteFriend = inviteFriend;
       // vm.viewFriendProfile = viewFriendProfile;

        function init() {
            AdminDAOService
                .getAllFriends()
                .then(function (res) {
                    vm.friends = res.data;
                    console.log(vm.friends);
                    console.log("RECEIVING UPDATES FROM SERVER");
                });
        }

        init();

        $scope.$on("refreshFriendList", function(response) {
            console.log("Event received");
            AdminDAOService
                .getAllFriends()
                .then(function (res) {
                    vm.friends = res.data;
                });
            vm.success = "Friend Deleted!";
        });

        $scope.$on("refreshFriendListInvited", function(response) {
            console.log("Event received");
            AdminDAOService
                .getAllFriends()
                .then(function (res) {
                    console.log(res.data);
                    vm.friends = res.data;
                });
            vm.success = "Friend Invited";
        });

        function addNewFriend(emailId,name){
            if(emailId==null || name==null) {
                vm.error = "Check parameters ";
                return;
            }
            vm.error = null;
            AdminDAOService
                .addFriend(emailId,name)
                .then(function (res) {
                    if(res.data.status == false){
                        vm.error = "Friend already exists!!!";
                    }else{
                        vm.friends = res.data;
                        vm.error = "New Friend created successfully!";
                    }
                });
        }



        function removeFriend(index) {
            console.log(vm.friends[index]);
            LocalBasketService.setUnfriendModel(vm.friends[index]);
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'client/views/user/DeleteDialog.html',
                controller: 'DeleteDialogController',
                controllerAs: 'model'
            });
        }

        function inviteFriend(index) {
            console.log("Under inviteFriend");
            LocalBasketService.setinviteFriendModel(vm.friends[index]);
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'client/views/user/InviteDialog.html',
                controller: 'InviteDialogController',
                controllerAs: 'model'
            });
            // AdminDAOService
            //     .inviteFriend()
            //     .then(function (res) {
            //        console.log("friend invited");
            //     });

        };
    }
})();
// BASKET SERVICE
(function () {
    angular.module('Vijet_Server')
        .factory('LocalBasketService', basketServiceImpl);

    function basketServiceImpl(){
        var unFriendModel;
        var inviteFriend;

        var api = {
            getUnfriendModel : getUnfriendModel,
            setUnfriendModel : setUnfriendModel,
            getinviteFriendModel : getInvitefriendModel,
            setinviteFriendModel : setInvitefriendModel
        };
        return api;

        function getUnfriendModel() {
            return unFriendModel;
        };

        function setUnfriendModel(unFriendDetails){
            unFriendModel = unFriendDetails;
        };

        function getInvitefriendModel() {
            return inviteFriend;
        };

        function setInvitefriendModel(invFriendModel){
            inviteFriend = invFriendModel;
        };
    }
})();

// DELETE DIALOG HANDLER
(function(){
    angular.module('Vijet_Server')
        .controller("DeleteDialogController", deleteDialogControllerImpl);

    function deleteDialogControllerImpl($scope, $rootScope, $uibModalInstance, AdminDAOService,LocalBasketService) {
        var vm = this;
        vm.deleteDialogOkButtonClick = okButtonClickListener;
        vm.deleteDialogCancelButtonClick = cancelButtonClickListener;
        var model = LocalBasketService.getUnfriendModel();
        vm.name = model.name;
        vm.email = model.email;
        vm.updatedFriends = null;

        function okButtonClickListener(adminUserName, adminPassword) {
            console.log(adminUserName+adminPassword);
            console.log("OK Button");
            if(adminUserName==null || adminPassword == null){
                vm.error = "Admin Username or Password is null";
                return;
            }
            var unFriendReq = {
                adminUsername: adminUserName,
                adminPassword: adminPassword,
                _id:model._id
            };

            AdminDAOService
                .removeFriend(unFriendReq)
                .then(function (res) {
                    if(res.data.status == false){
                        vm.error = "Invalid Admin Credentials";
                    }else{
                        $rootScope.$broadcast("refreshFriendList",res.data);
                        $uibModalInstance.close();
                    }
                });
        }

        function cancelButtonClickListener() {
            $uibModalInstance.close();
        }
    };
})();

//INVITE DIALOG CONTROLLER

(function(){
    angular.module('Vijet_Server')
        .controller("InviteDialogController", inviteDialogControllerImpl);

    function inviteDialogControllerImpl($scope, $rootScope, $uibModalInstance, AdminDAOService,LocalBasketService) {
        var vm = this;
        vm.inviteDialogOkButtonClick = okButtonClickListener;
        vm.inviteDialogCancelButtonClick = cancelButtonClickListener;
        var model = LocalBasketService.getinviteFriendModel();
        console.log(model);
        vm.name = model.name;
        vm.email = model.email;

        function okButtonClickListener(adminUserName, adminPassword, inviteMsg) {
            console.log(adminUserName+adminPassword);
            if(adminUserName==null || adminPassword == null){
                vm.error = "Admin Username or Password is null";
                return;
            }
                
            var inviteReq = {
                _id:model._id,
                inviteMsg : inviteMsg,
                adminUserName:adminUserName,
                adminPassword: adminPassword
            };

            AdminDAOService
                .inviteFriend(inviteReq)
                .then(function (res) {
                    console.log(res.data);
                    if(res.data.status == false){
                        vm.error = "Invalid Admin Credentials";
                    }else{
                        $rootScope.$broadcast("refreshFriendListInvited",res.data);
                        $uibModalInstance.close();
                    }
                });
        }

        function cancelButtonClickListener() {
            $uibModalInstance.close();
        }
    };
})();