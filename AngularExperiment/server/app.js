module.exports = function (app) {

    require("./services/user.service.server.js")(app);

    // app.get("/user/:username/:password",function (req, res) {
    //     console.log(req.params['username']);
    //     console.log(req.params['password']);
    //     res.send("success");
    // })
}
