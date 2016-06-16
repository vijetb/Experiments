module.exports = function (app,models) {
    var userProfileModel = models.userProfileModel;

    //endpoints
    app.get("/user/userProfile/:userId", getUserProfile);
    app.put("/user/updateUserProfile/", updateUserProfile);

    function getUserProfile(req,res) {
        var userId = req.params.userId;
        userProfileModel.getProfile(userId)
            .then(function (userProfile) {
                    res.json(userProfile);
                    return;
            });
    };

    function updateUserProfile(req,res){
        var userProfile = req.body;
        userProfileModel.updateProfile(userProfile._id,userProfile)
            .then(function (updatedStatus) {
                console.log(updatedStatus);
                    res.json({status:updatedStatus.nModified });
                    return;
            });
    };
}