/**
 * 
 */

var configModule = angular.module('app') // Please dont not use [], dependency 
.config(function($routeProvider) {	
//    $urlRouterProvider.otherwise('/login');
	$routeProvider
	
	
	.when("/", {
        templateUrl : "productpages/welcomeFirst.html"
     })
     
     .when("/category", {
        templateUrl : "{{$location.path()}}"
     })
     
     .when("/brand", {
        templateUrl : "{{$location.path()}}"
     })
    
    .when("/seller", {
        templateUrl : "productpages/sellerRegistration.html"
    })
    
    .when("/subCategoryList/:category", {
        templateUrl : "productpages/getAllSubCategoryList.html",
        controller  : "subCategoryController"
    })
    
    .when("/subCategoryListDetail/:getSubCat", {
        templateUrl : "productpages/getSubCategoryDetailWithProductList.html",
        controller  : "subCategoryProductController"
    })
    
    .when("/brandList/:brandDetail", {
        templateUrl : "productpages/getBrandDetailsWithProductList.html",
        controller  : "brandProductController"
    })
    
    .when("/planDescription/:planId", {
        templateUrl : "productpages/productDescription.html",
        controller  : "planDescriptionController"
    })  

   .when("/customerLoginRegistration", {
        templateUrl : "productpages/login.html",
	     controller  : "authController",
	     controllerAs :'authCtrl'
    })
    
     .when("/requestQuote", {
        templateUrl : "productpages/requestQuote.html",
        controller  : "requestQuoteController"
    })
 
    .when('/logout', {
		redirectTo: '/'
	})
   
/*	.when("/customerLoginRegistration", {
        templateUrl : "userpages/userProfileLayout.html",
        controller  : "userProfileController"
    })
   
    
   .when("/customerLoginRegistration", {
        templateUrl : "sellerpages/sellerProfileLayout.html",
        controller  : "sellerProfileController"
    })*/
    
/*   .when("/customerLoginRegistration", {
        templateUrl : "adminpages/adminProfileLayout.html",
        controller  : "adminProfileController"
    })*/
})

.run(
    function ($rootScope, $location, $cookieStore,$window, $http,AUTH_EVENTS,APP_CONSTANT) {
    	//Management 
    	$rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
    		console.log('Clicked on '+$location.path());
    		
    		if(($location.path() == '/requestQuote') && !$rootScope.globals.userSession){

    			$location.path('/customerLoginRegistration');
    				
    		}else if($location.path() == '/logout'){
            		$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
            }else{
            
        		console.log('Valid Path')

            }
        });
    	
	$rootScope.$on(AUTH_EVENTS.loginFailed, function(event, next){
    		console.log('Login failed');
        

    		//$scope.message = "Login failed";
    });
	
	$rootScope.$on(AUTH_EVENTS.logoutSuccess, function(event, next){
		console.log('Logout Success');
		$window.localStorage.removeItem("globals");
		$rootScope.userSession=null;
	    $rootScope.$emit("CallParentMethod", {});

		//$scope.message = "Login failed";
});
    
    $rootScope.$on(AUTH_EVENTS.loginSuccess, function(event, next){
		//$scope.message = "Login Success";
		console.log('Login success');
	    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
	    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;
	    
	    $rootScope.$emit("CallParentMethod", {});
		$location.path('/');
		
		
		
    });
    
    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.userSession) {
        $http.defaults.headers.common[APP_CONSTANT.AUTH_KEY] = $rootScope.globals.userSession.authKey; // jshint ignore:line
	    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
	    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;

    }

    

})



