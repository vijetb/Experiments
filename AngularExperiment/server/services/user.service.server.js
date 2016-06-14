var nodemailer = require('nodemailer');

module.exports = function (app) {
    //endpoints
    app.post("/users/validateUser", validateUser);
    app.get("/users/forgotPassword", resetUserCredentials);
    app.get("/friends/friendList", getAllFriendList);
    app.get("/friends/friendProfile/:friendId", getFriendProfile);
    app.post("/friends/addFriend", addFriend);
    app.post("/friends/removeFriend", removeFriend);
    app.post("/friends/inviteFriend", inviteFriend);
    app.post("/friends/updateFriendProfile", updateFriendProfile);
    app.post("/friends/profileStatus/", updateProfileStatusInfo);
    app.post("/friends/updateProfileSettings/", updateProfileSettings);
    app.get("/friends/shortFriendProfile/:friendId", getShortFriendProfile);

    var friendsList = [
        {_id:123,name:"Vijet Badigannavar",email:"bvijet@gmail.com",status:0,invited:0,password:"vijet"},
        {_id:234,name:"Kartik Chickkerur",email:"kartik@gmail.com",status:0,invited:0,password:"vvv"},
        {_id:345,name:"Shravan Jodalli",email:"shravan.jodalli@gmail.com",status:1,invited:0,password:"vvv"},
        {_id:456,name:"Nikhil Mahajanashetty",email:"nmshetty@gmail.com",status:2,invited:0,password:"vvv"},
    ];

    function validateUser(req,res) {
        // extract the username and password from the request
        // connect with the database and validate the username and password
        console.log("Under Validate User");
        if(req.body.username === 'vijet@gmail.com' && req.body.password ==='vijet'){
            var data = {
                _id: 111,
                status:true,
                isAdmin:true
            }
            res.send(data);
        }else if(req.body.username === 'bob@gmail.com' && req.body.password ==='bob'){
            var data = {
                _id: 111,
                status:true,
                isAdmin:false
            }
            res.send(data);
        }
        else{
            res.send({status:false})
        }

    }

    function resetUserCredentials(req, res){
        // extract the email from the request
        // generate the code to reset the password
        console.log("Under resetUserCredentials");
        res.send("success");
    }
    
    function getAllFriendList(req,res) {
        res.send(friendsList);
        return;
    }

    function addFriend(req,res) {
        var newFriend = req.body;
        //check if emailId is unique;
        //console.log(friendsList);
        console.log(req.body.emailId);
        for(var i in friendsList){
            if(friendsList[i].email === req.body.emailId){
                res.send({status:false});
                return;
            }
        }
        friendsList.push({_id: Math.floor((Math.random() * 10000) + 1), name:req.body.name, email:req.body.emailId,status:0,invited:0});
        //console.log(friendsList);
        res.send(friendsList);
        return;
    }

    function removeFriend(req,res) {
        //verify the username and password
        if(req.body.adminUsername === "vijet@gmail.com" && req.body.adminPassword === "vijet"){
            for(var i in friendsList){
                if(friendsList[i]._id === req.body._id){
                    friendsList.splice(i, 1);
                    res.send({status:true});
                    return;
                }
            }
        }
        res.send({status:false} );
        return;
    }

    function inviteFriend(req,res) {
        console.log("MSG TO SEND INVITE");
        console.log(req.body);

        var inviteReq = req.body;
        if(inviteReq.adminUserName === "vijet@gmail.com" && inviteReq.adminPassword==="vijet"){
            // fetch the details of the friend
            // generate the new password for the friend
            var randomPwd = null;
            // send invite to friend
            // send invite to vijet
            // update the response
            var friend = null;
            for(var i in friendsList){
                if(friendsList[i]._id === inviteReq._id){
                    friend = friendsList[i];
                    friendsList[i].status = 1;
                    friendsList[i].invited = 1;
                    randomPwd = randomPassword(friendsList[i].email.length);
                    friendsList[i].password = randomPwd;
                    break;
                }
            }
            //invite to friend
            sendFriendInvite(friend.email,inviteReq.message);
            sendMailToAdmin(friend.email,inviteReq.message);
            return res.json({"status":true});
        }else{
            res.json({"status":false});
        }
        // var nodemailer = require('nodemailer');
        //
        // var transporter = nodemailer.createTransport('smtps://user%40gmail.com:pass@smtp.gmail.com');
        //
        // // setup e-mail data with unicode symbols
        // var mailOptions = {
        //     from: '"Vijet" <bvijet@gmail.com>', // sender address
        //     to: 'bvijet@gmail.com', // list of receivers
        //     subject: 'Hello', // Subject line
        //     text: 'Hello world', // plaintext body
        //     html: '<b>Hello world</b>' // html body
        // };
        //
        // // send mail with defined transport object
        // transporter.sendMail(mailOptions, function(error, info){
        //     if(error){
        //         return console.log(error);
        //     }
        //     console.log('Message sent: ' + info.response);
        // });
        // return;
    };


    function randomPassword(length) {
        var chars = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()-+<>ABCDEFGHIJKLMNOP1234567890";
        var pass = "";
        for (var x = 0; x < length; x++) {
            var i = Math.floor(Math.random() * chars.length);
            pass += chars.charAt(i);
        }
        return pass;
    };

    function sendFriendInvite(emailId, inviteMsg) {
      console.log("Under send Invite method");

        var transporter = nodemailer.createTransport('smtps://victoriousvijet%40gmail.com:vvictory@smtp.gmail.com');

        // setup e-mail data with unicode symbols
        var mailOptions = {
            from: '"Vijet" <victoriousvijet@gmail.com>', // sender address
            to: 'bvijet@gmail.com', // list of receivers
            subject: 'Hello World Subject', // Subject line
            text: 'Hello World Text', // plaintext body
            html: '<b>HTML TOOOOO</b>' // html body
        };

        // send mail with defined transport object
        transporter.sendMail(mailOptions, function(error, info){
            if(error){
                return console.log(error);
            }
            console.log('Message sent: ' + info.response);
        });

    };

    function sendMailToAdmin(emailId, inviteMsg) {
        console.log("Under send Invite method");
        // var nodemailer = require('nodemailer');
        //
        // var transporter = nodemailer.createTransport('smtps://victoriousvijet%40gmail.com:vvictory@smtp.gmail.com');
        //
        // // setup e-mail data with unicode symbols
        // var mailOptions = {
        //     from: '"Vijet" <victoriousvijet@gmail.com>', // sender address
        //     to: 'bvijet@gmail.com', // list of receivers
        //     subject: 'Hello World Subject', // Subject line
        //     text: 'Hello World Text', // plaintext body
        //     html: '<b>HTML TOOOOO</b>' // html body
        // };
        //
        // // send mail with defined transport object
        // transporter.sendMail(mailOptions, function(error, info){
        //     if(error){
        //         return console.log(error);
        //     }
        //     console.log('Message sent: ' + info.response);
        // });

    };

    function getFriendProfile(req,res) {
        var pageId = req.params.friendId;
        console.log("GET FRIEND PROFILE"+ pageId);
        res.json({status:true});
    };

    function updateFriendProfile(req, res) {
        var friendProfile = req.body;
        console.log(friendProfile);
        return res.json({status:true});
    };

    function getShortFriendProfile(req,res) {
        var data = req.params.friendId;
        console.log("%%%"+ data);
        var friendPro = {
           _id: 555,
          name: 'Bob',
            email:"bob@gmail.com",
          lastLoginTime: new Date(),
            noOfViews : 23,
            publishStatus: true
        };

        return res.json(friendPro);
    };

    function updateProfileStatusInfo(req, res) {
        console.log(req.body);
      return res.json({status:true});
    };

    function updateProfileSettings(req,res) {
        console.log(req.body);
        return res.json({status:false});
    }
}
