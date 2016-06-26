(function () {
    angular
        .module("Vijet_Server")
        .controller("FriendProfileController", FriendControllerImpl);

    function FriendControllerImpl($routeParams, $location, $uibModal, UserProfileDAOService) {
        var vm = this;
        var friendProfile = null;
        vm.updateProfile = updateProfile;
        vm.removePicButtonClick =  removeButtonClick;
        vm.addPicButtonClick = addButtonClick;
        vm.addFile = addNewFile;
        vm.addNewPic = addNewPic;
        vm.numberOfPics = [{url:'abc',uploaded:true}];

        console.log($routeParams.userId);
        function init() {
            setupAnimationForDisplayPicture();

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

        function addButtonClick() {
            console.log("Under Add button Click");
            var modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'client/views/user/UploadImageDialog.html',
                controller: 'UploadImageController',
                controllerAs: 'model'
            });
        };

        function removeButtonClick() {
            console.log("Under Remove Button Click");
        };

        function addNewFile() {
          console.log("Under new file");
            $("#firstFile").trigger('click');
        };
        
        function addNewPic(){
            console.log("Under add new pic");
        };


        function setupAnimationForDisplayPicture() {
            var circleHeight = $(".imageClass").height()/2;
            console.log(circleHeight);
            $(".deletePic").css({ top: circleHeight }).hide();
            $(".editPic").css({ top: circleHeight }).hide();

            $(".imageClass").mouseenter(function(){
                $(".deletePic").fadeIn().show();
                $(".editPic").fadeIn().show();
            });

            $(".imageClass").mouseleave(function() {
                $(".deletePic").fadeOut();
                $(".editPic").fadeOut();
            });
        };
    };

})();

(function(){
    angular.module('Vijet_Server')
        .controller("UploadImageController", uploadImageControllerImpl);

    function uploadImageControllerImpl($uibModalInstance, $scope) {
        var vm = this;

        vm.okButtonClick = okButtonClick;
        vm.cancelButtonClick = cancelButtonClick;
        vm.fileChange = handleFileSelect;
        vm.myCroppedImage = null;


        $scope.myImage='';
        $scope.myCroppedImage='';

        var handleFileSelect=function(evt) {
            console.log("Handle File Change");
            // var file=evt.currentTarget.files[0];
            // var reader = new FileReader();
            // reader.onload = function (evt) {
            //     $scope.$apply(function($scope){
            //         $scope.myImage=evt.target.result;
            //     });
            // };
            // reader.readAsDataURL(file);
        };
        angular.element(document.querySelector('#fileInput')).on('change',handleFileSelect);


        function okButtonClick() {

        };

        function cancelButtonClick() {
            $uibModalInstance.close();
        };
    };
})();