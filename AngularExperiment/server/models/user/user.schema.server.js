/**
 * This file defines the schema for the User model. This schema corrosponds to the collection
 * "User" in the database.
 */

module.exports = function() {
    var mongoose = require("mongoose");

    var UserSchema = mongoose.Schema({
        email:{type:String, required:true},
        name:{type:String, required:true},
        password: String,
        isAdmin:{type: Boolean, default: false},
        lastLogin: {type: Date, default: Date.now},
        noOfViews: Number,
        publishStatus:{type: Boolean, default: false}
    }, {collection: "user"});

    return UserSchema;
};