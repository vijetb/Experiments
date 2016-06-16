/**
 * DAO class for the User.
 */

module.exports = function() {

    var mongoose = require("mongoose");
    var UserProfileSchema = require("./userprofile.schema.server")();
    var UserProfile = mongoose.model("UserProfile", UserProfileSchema);

    var api = {
        updateProfile: updateProfile,
        getProfile:getProfile
    };
    return api;

    function getProfile(userId) {
        return UserProfile.findOne({_id:userId});
    };

    function updateProfile(userId,userProfile) {
        return UserProfile.update({_id:userId},userProfile);
    };

};
