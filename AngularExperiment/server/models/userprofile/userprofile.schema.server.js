/**
 * This file defines the schema for the User model. This schema corrosponds to the collection
 * "User" in the database.
 */

module.exports = function() {
    var mongoose = require("mongoose");

    var UserProfileSchema = mongoose.Schema({
        _user:{ type: mongoose.Schema.Types.ObjectId, ref: 'user' },
        name:String,
        designation:String,
        company: String,
        email:String,
        telephoneNo:String,
        userComment:String,
        adminComment:String,
        dpURL:String,
        images:{ type : Array , "default" : [] }
    }, {collection: "userprofile"});

    return UserProfileSchema;
};