(function(){
    angular.module("MyDirectives",[])
        .directive("deldialog", deleteDialog);

    function deleteDialog(){
        return {
            template:"HelloWorld"
        }
    }
})();