module.exports = function (app) {

    var models = require("./models/models.js")();
    /**
     * This is a top level Service to interact with the User operations.
     * This service inturn communicates with the UserDAO through the models.
     */
    require("./services/user.service.server.js")(app,models);
    /**
     * This is a top level Service to interact with the UserProfile.
     * This service int urn communicates with the UserProfileDAO through the models.
     */
    require("./services/userprofile.service.server.js")(app,models);
}
