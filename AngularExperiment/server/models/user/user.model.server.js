/**
 * DAO class for the User.
 */

module.exports = function() {

    var mongoose = require("mongoose");
    var UserSchema = require("./user.schema.server")();
    var User = mongoose.model("User", UserSchema);

    var api = {
        validateUser: validateUser,
        resetUserPassword: resetUserPassword,
        getShortUserProfile: getShortUserProfile,
        getUserPassword:getUserPassword,
        updateUserSettings:updateUserSettings,
        updateUserProfilePublishStatus: updatePubUnpubProfileStatus
    };
    return api;

    function validateUser(emailId,password) {
        return User.findOne({email:emailId,password:password},{_id:1,isAdmin:1});
    };

    function resetUserPassword(email,password){
      return User.update({email:email},{$set:{password:password}});
    };

    function getShortUserProfile(userId){
        return User.findOne({_id:userId},{_id:1,name:1,noOfViews:1,lastLogin:1,publishStatus:1,email:1});
    };

    function getUserPassword(userId){
        return User.findOne({_id:userId},{password:1});
    };

    function updateUserSettings(userId,newPassword){
        return User.update({_id:userId},{$set:{password:newPassword}});
    };

    function updatePubUnpubProfileStatus(userId,status){
        return User.update({_id:userId},{$set:{publishStatus:status}});
    };
};
