(function (ng) {
    var mod = ng.module('messageModule');

    mod.controller('messageCtrl', ['CrudCreator', '$scope', 'messageService', 'messageModel','$location', 'authService', 'userService' , function (CrudCreator, $scope, svc, model, $location, authSvc, svcUser) {
            CrudCreator.extendController(this, svc, $scope, model, 'message');   
        
        this.answer='';
        this.user='';
        this.data1 = [];
        this.userlist = []
        var usersarr = [];
        this.mes = [];
        this.ReplyUserName = '';
        
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
        
        $scope.getMessagesByProvider =  function () {
            svc.getMessagesByClient(authSvc.getCurrentUser().id).then(function (Messages){
                   
                    $scope.getmessagesbyprovider = Messages;
                    //$scope.records = Messages;
                    
                    
                });
            };
            
        
            
        $scope.getMessagesByClient =  function () {
            svc.getMessagesByProvider(authSvc.getCurrentUser().id).then(function (Messages){
                   
                  $scope.getmessagesbyuser = Messages;
                    //$scope.records = Messages;
                });
            };
        
        this.messagesLoad = function (){
                userlist = [];
                if (userlist == "")
                {
                
                svc.getClients().then(function (Clients){
                    
                    for (var i = 0; i < Clients.length; i++)
                    {  
                        var users = {id: Clients[i].id, name: Clients[i].name,type: "Client"}
                        usersarr[i]= users;
                        
                    }
                    userlist = usersarr;
                    
                });
                }
                
                svc.getProviders().then(function (Providers){
                    
                    for (var i = 0; i < Providers.length; i++)
                    {  
                        var users = {id: Providers[i].id, name: Providers[i].name,type: "Provider"}
                        
                        userlist.push(users);
                    }
                    
                });
                
                svcUser.api.one('currentUser').get().then(function(user){
                    
                    if (user.role === 'provider'){
                        $scope.getMessagesByProvider;
                   svc.getMessagesByProvider(authSvc.getCurrentUser().id).then(function (Messages){
                   
                    mes = Messages;
                    
                });
                       
                        
                    }else{
                        $scope.getMessagesByClient
                    svc.getMessagesByClient(authSvc.getCurrentUser().id).then(function (Messages){
                   
                    mes = Messages;
                    
                    
                    
                });
                   
                    }
                });
                
                
                
            };
        
        
        
        this.answerQuestion = function(record){            
           //Tmp answer
           record.answer=this.answer;
           svc.answerQuestion(record);
            //clean question
            this.answer='';      
        };
        
        this.getName = function(idreceiver, type){
            for (var i = 0; i <userlist.length; i++)
                    {
                        if ((userlist[i].id === idreceiver) && (userlist[i].type === type)){
                        var name = userlist[i].name;
                    }
                    }
                    return name;
        };

        this.messagesSearch = function (){
                    var names = [];
                    $('#MessageInbox').modal('show');
                    for (var i = 0; i <userlist.length; i++)
                    {
                        names.push(userlist[i].name);
                    }
                    
                $(document).ready(function () {
                    $('input.typeahead').typeahead({
                        name: 'accounts',
                        local: names,
                    });
                }); 
                    
                    
                        
                    for (var i = 0; i < mes.length; i++)
                           {
                             check = (typeof mes[i].client).toString();
                                if (check === 'undefined'){
                                    user = mes[i].provider;
                                }else
                                {
                                    user = mes[i].client;
                                }
                             var n = (i+1).toString();
                             var $Usuario = $("#Usuario"+n);
                             $Usuario.find('label').remove();
                             $Usuario.append("<label>"+user.name+": "+"</label>");
                             

                             var $Asunto = $("#Asunto"+n);
                             $Asunto.find('label').remove();
                             $Asunto.append("<label>"+mes[i].subject+" - " +"</label>");
                             
                             var $Preview = $("#Preview"+n);
                             $Preview.find('label').remove();
                             $Preview.append(" "+"<label>"+mes[i].question.substr(0,20)+"</label>");

                             var $Fecha = $("#Date"+n);
                             $Fecha.find('label').remove();
                             $Fecha.append("<label >"+mes[i].date+"</label>");
                           }
                
                
                
                
            };
            
            
        this.messagesSend = function (){
                $('#WriteMessage').modal('show');
            };
            
        this.messagesRead = function (i){
                $('#ReadMessage').modal('show');
                
                check = (typeof mes[i].client).toString();
                                if (check === 'undefined'){
                                    user = mes[i].provider;
                                }else
                                {
                                    user = mes[i].client;
                                }
                
                var $Title = $("#MessageTitle");
                $Title.find('label').remove();
                $Title.append("<label>"+mes[i].subject+"</label>");
                
                var $From = $("#MessageFrom");
                $From.find('label').remove();
                $From.append("<label class='col-sm-11' >"+ user.name +"</label>");
                console.log(user.name);
                ReplyUserName = user.name;
                
                var $Message = $("#MessageRead");
                $Message.find('label').remove();
                $Message.append("<label>"+mes[i].question+"</label>");
                
                var $Date = $("#MessageDate");
                $Date.find('label').remove();
                $Date.append("<label>Received "+mes[i].date+"</label>");
                
                var $Subject = $("#ReplySubject");
                $Subject.find('input').remove();
                $Subject.append("<input type='text' id='newSubject' class='col-sm-9' value='RE: "+mes[i].subject+"'>");
                
            };
            
        

        
        
        svcUser.api.one('currentUser').get().then(function(user) {
            
            $scope.role=user.role;
            if($scope.role==="provider"){
               $scope.getMessagesByProvider()
               $scope.getQuestionsByProvider();
                this.user="provider";
            }else{
                $scope.getMessagesByClient()
                $scope.getQuestionsByUser();
                this.user="client";
            }
            
        });
        
        
        this.message='';
        this.sendMessage = function(){
                
                newMessage={
                    question: this.message,
                    product:$scope.tmpRecord,
                    provider:$scope.tmpRecord.provider,
                    client:authSvc.getCurrentUser()
                };
                messageSvc.askQuestion(newQuestion);
                //Hide modal
                $('#myModalNorm').modal('hide');
                //clean question
                this.question='';
            };
        
        
        this.sendNewMessage =  function (){
                        svcUser.api.one('currentUser').get().then(function(user){
                            
                            var UserId, UserType, message, subject, receivertype, providerreceiver, clientreceiver, provider, client;
                            message= $("#message").val();
                            subject= $("#Subject").val();
                            var UserName = $("#UserName").val();
                            
                            for (var i = 0; i <userlist.length; i++)
                            {
                                if (userlist[i].name === UserName){
                                   UserId = userlist[i].id;
                                   UserType = userlist[i].type;
                                   
                                   break;
                                }
                            }
                            
                            
                          
                            if (user.role === 'provider'){
                                if (UserType.toString() === 'provider'){
                                   
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype: UserType.toString(),
                                    providerreceiver: UserId,
                                    provider: authSvc.getCurrentUser(),
                                         
                                    };
                                }
                                else{
                                    
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype: UserType.toString(),
                                    clientreceiver: UserId,
                                    provider: authSvc.getCurrentUser(),
                                          
                            };
                                    }
                                
                            }
                             else
                             {
                                if (UserType.toString() === 'provider'){
                                    
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype:  UserType.toString(),
                                    providerreceiver: UserId,
                                    client: authSvc.getCurrentUser(),       
                            };
                                     providerreceiver= UserId;
                                        clientreceiver= '';
                                        provider='';   
                                        client=authSvc.getCurrentUser();
                                    }
                                
                                else
                                {   
                                    
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype:  UserType.toString(),
                                    clientreceiver: UserId,
                                    client: authSvc.getCurrentUser(),       
                                    };
                            
                                }
                             }   
                            
                            svc.sendMessage(newMessage);
                            //Hide modal
                            $('#WriteMessage').modal('hide');
                            
                        
                    });
                    };
                
                
    

    this.sendReply =  function (){
                        svcUser.api.one('currentUser').get().then(function(user){
                            
                            var UserId, UserType, message, subject, receivertype, providerreceiver, clientreceiver, provider, client;
                            message= $("#reply").val();
                            subject= $("#newSubject").val();
                            var UserName = ReplyUserName;
                            
                            
                            for (var i = 0; i <userlist.length; i++)
                            {
                                if (userlist[i].name === UserName){
                                   UserId = userlist[i].id;
                                   UserType = userlist[i].type;
                                   
                                   break;
                                }
                            }
                            
                            
                          
                            if (user.role === 'provider'){
                                if (UserType.toString() === 'provider'){
                                    
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype: UserType.toString(),
                                    providerreceiver: UserId,
                                    provider: authSvc.getCurrentUser(),
                                         
                                    };
                                }
                                else{
                                    
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype: UserType.toString(),
                                    clientreceiver: UserId,
                                    provider: authSvc.getCurrentUser(),
                                          
                            };
                                    }
                                
                            }
                             else
                             {
                                if (UserType.toString() === 'provider'){
                                    
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype:  UserType.toString(),
                                    providerreceiver: UserId,
                                    client: authSvc.getCurrentUser(),       
                            };
                                     providerreceiver= UserId;
                                        clientreceiver= '';
                                        provider='';   
                                        client=authSvc.getCurrentUser();
                                    }
                                
                                else
                                {   
                                    
                                    newMessage = {
                                    question: message,
                                    subject: subject,
                                    receivertype:  UserType.toString(),
                                    clientreceiver: UserId,
                                    client: authSvc.getCurrentUser(),       
                                    };
                            
                                }
                             }   
                            
                            svc.sendMessage(newMessage);
                            //Hide modal
                            $('#ReadMessage').modal('hide');
                            
                        
                    });
                    };
                    
                    
                
    }]);


})(window.angular);
