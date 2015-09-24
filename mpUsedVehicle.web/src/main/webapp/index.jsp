<!DOCTYPE html>
<html ng-app="mainApp">
    <head>
        <%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
        <title>mpUsedVehicle</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://bootswatch.com/darkly/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
        <!--        <script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>-->
        <script src="underscore-min.js" type="text/javascript"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="http://vitalets.github.io/angular-xeditable/dist/css/xeditable.css" rel="stylesheet"/>
        <script src="https://code.angularjs.org/1.4.3/angular.min.js"></script>
        <script src="https://code.angularjs.org/1.4.3/angular-mocks.js"></script>
        <script src="https://code.angularjs.org/1.4.3/angular-route.min.js"></script>
        <script src="https://code.angularjs.org/1.4.3/angular-cookies.min.js"></script>
        <script src="https://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.12.1.min.js"></script>
        <script src="restangular.min.js" type="text/javascript"></script>  
        <!--        <script src="https://cdnjs.cloudflare.com/ajax/libs/restangular/1.4.0/restangular.min.js"></script>-->
        <script src="https://rawgit.com/recursosCSWuniandes/ng-crud/master/dist/ngcrud.min.js"></script>
        <script src="https://rawgit.com/recursosCSWuniandes/ng-crud/master/dist/ngcrud-mocks.min.js"></script>
        <script src="https://rawgit.com/recursosCSWuniandes/ng-crud-auth/master/dist/ngcrud-auth.min.js"></script>
        <script src="http://vitalets.github.io/angular-xeditable/dist/js/xeditable.min.js"></script>

        <script src="src/app.js"></script>

        <script src="src/modules/admin/user.mod.js"></script>
        <script src="src/modules/admin/user.svc.js"></script>
        <script src="src/modules/admin/user.ctrl.js" type="text/javascript"></script>

        <script src="src/modules/cartItem/cartItem.mod.js"></script>
        <script src="src/modules/cartItem/cartItem.svc.js"></script>
        <script src="src/modules/cartItem/cartItem.ctrl.js" type="text/javascript"></script>

        <script src="src/modules/client/client.mod.js"></script>
        <script src="src/modules/client/client.svc.js"></script>
        <script src="src/modules/client/client.ctrl.js"></script>

        <script src="src/modules/product/product.mod.js"></script>
        <script src="src/modules/product/product.svc.js"></script>
        <script src="src/modules/product/product.ctrl.js" type="text/javascript"></script>

        <script src="src/modules/messages/message.mod.js"></script>
        <script src="src/modules/messages/message.svc.js"></script>
        <script src="src/modules/messages/message.ctrl.js" type="text/javascript"></script>

        <script src="src/modules/provider/provider.mod.js"></script>
        <script src="src/modules/provider/provider.svc.js"></script>
        <script src="src/modules/provider/provider.ctrl.js"></script>

        <script src="src/modules/vehicle/vehicle.mod.js"></script>
        <script src="src/modules/vehicle/vehicle.svc.js"></script>
        <script src="src/modules/vehicle/vehicle.ctrl.js"></script>

        <script src="src/modules/checkout/checkout.mod.js"></script>
        <script src="src/modules/checkout/checkout.svc.js"></script>
        <script src="src/modules/checkout/checkout.ctrl.js"></script>

    </head>
    <body style="padding-top: 60px;">
        <div class="container-fluid">
            <div class="col-md-12">
                <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#main-bar">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#">mpUsedVehicle</a>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="main-bar">
                            <ul class="nav navbar-nav">
                                <li><a href="#/catalog">Catalog</a></li>
                            </ul>
                            <form novalidate class="navbar-form navbar-left" role="search" ng-submit="ctrl.searchByName(vehicle)" ng-controller="productCtrl as ctrl">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Search vehicles" ng-model="vehicleName" required>
                                </div>
                                <button type="submit" class="btn btn-default">Submit</button>
                            </form>
                            <ul class="nav navbar-nav navbar-right">
                                <li id="cart"><a href="#/shoppingCart"><span class="glyphicon glyphicon-shopping-cart" ></span> My Shopping Cart </a></li>
                                <shiro:hasRole name="administrator">
                                <li id="users"><a href="#/users"><span class="glyphicon glyphicon-user" ></span> Registered users </a></li>
                                </shiro:hasRole>

                                <li><login-button></login-button></li>
                            </ul>
                        </div> <!-- /.navbar-collapse -->
                    </div> <!-- /.container-fluid -->
                </nav>
            </div>
            <div ng-view></div>
        </div>
    </body>
</html>