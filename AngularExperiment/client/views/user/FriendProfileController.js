(function () {
    angular
        .module("Vijet_Server")
        .controller("FriendProfileController", FriendControllerImpl);

    function FriendControllerImpl($routeParams, $location, UserProfileDAOService) {
        var vm = this;
        var friendProfile = null;
        vm.updateProfile = updateProfile;

        console.log($routeParams.userId);
        function init() {
            UserProfileDAOService.getUserProfile($routeParams.userId)
                .then(function (response) {
                    friendProfile = response.data;
                    vm.friendProfile = friendProfile;
                });
        };
        
        init();

        function updateProfile(dpURL,name,designation,company,email,telephoneNo, userComment, adminComment) {
            var updatedProfile = {
                _id: friendProfile._id,
                //_id: "576165eef3fc607402c70b8d",
                dpURL: dpURL,
                name:name,
                designation:designation,
                company:company,
                email:email,
                telephoneNo:telephoneNo,
                userComment:userComment,
                adminComment:friendProfile.adminComment

                //todo pictures
            };
            console.log(adminComment);
            UserProfileDAOService.updateUserProfile(updatedProfile)
                .then(function (res) {
                    if(res.data.status===1 || res.data.status===0){
                        $location.url("/friendPage/"+friendProfile._id+"/"+res.data.status);
                        return;
                    }else{
                        vm.error = "Error in saving the profile!"
                        return;
                    }
                });
        };
        
    };

})();