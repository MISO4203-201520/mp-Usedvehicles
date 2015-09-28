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
        $scope.getQuestionsByUser = function () {
            svc.getQuestionsByUser(authSvc.getCurrentUser().id).then(function (Questions) {
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
            
        
        
        svcUser.api.one('currentUser').get().then(function(user) {
            console.log(user.role);
            $scope.role=user.role;
            if($scope.role==="provider"){
                console.log('getQuestionsByProvider');
               $scope.getQuestionsByProvider(); 
            }else{
                console.log('getQuestionsByUser');
                $scope.getQuestionsByUser();
            }
        });

        }]);
})(window.angular);
