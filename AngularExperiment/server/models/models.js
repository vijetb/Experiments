/**
 * Top Models container that contains all the models.
 * All the servies should get hold of this model and interact with the Persistence storage
 * Created by Viji on 6/15/16.
 */

module.exports = function() {

    var userModel = require("./user/user.model.server.js")();
    var userProfileModel = require("./userprofile/userprofile.model.server.js")();

    var models = {
        userModel: userModel,
        userProfileModel : userProfileModel
    };

    return models;
};