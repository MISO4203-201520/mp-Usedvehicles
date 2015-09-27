(function (ng) {
    var mod = ng.module('messageModule');

    mod.controller('messageCtrl', ['CrudCreator', '$scope', 'messageService', 'messageModel','$location', 'authService', 'userService' , function (CrudCreator, $scope, svc, model, $location, authSvc, svcUser) {
            CrudCreator.extendController(this, svc, $scope, model, 'message');   
    
        this.answer='';
    
        $scope.getQuestionsByProvider = function () {
            svc.getQuestionsByProvider(authSvc.getCurrentUser().id).then(function (Questions) {
                    $scope.records = Questions;
            });
        };
        
        this.answerQuestion = function(record){            
           //Tmp answer
           record.answer=this.answer;
           svc.answerQuestion(record);
            //clean question
            this.answer='';      
        };
            
        $scope.getQuestionsByProvider();
        
        svcUser.getCurrentUser();
        

        }]);
})(window.angular);
