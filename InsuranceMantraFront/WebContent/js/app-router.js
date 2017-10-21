/**
 * 
 */

var configModule = angular.module('app') // Please dont not use [], dependency 
.config(function($routeProvider) {	
//    $urlRouterProvider.otherwise('/login');
	$routeProvider
	
	.when("/", {
        templateUrl : "productpages/welcomeFirst.html",
        	 controller  : "topPolicyController"	
     })
     
     .when("/category", {
        templateUrl : "{{$location.path()}}"
     })
     
     .when("/brand", {
        templateUrl : "{{$location.path()}}"
     })
    
    .when("/seller", {
    	        //templateUrl : "productpages/login.html",
    		   templateUrl : "sellerpages/sellerLogin.html",
    		   controller  : "authController",
    		     controllerAs :'authCtrl'
    })
  
    
    .when('/sellerRegistration', {
		 templateUrl : 'sellerpages/sellerRegistration.html',
	     controller  : 'sellerRegistrationController',
	     controllerAs: 'sellerRegCtrl'
	})
    
    .when("/subCategoryList/:category", {
        templateUrl : "productpages/getAllSubCategoryList.html",
        controller  : "subCategoryController"
    })
    
    .when("/subCategoryListDetail/:getSubCat", {
        templateUrl : "productpages/getSubCategoryDetailWithProductList.html",
        controller  : "subCategoryProductController"
    })
    
    .when("/brandList/:brandName/:brandId", {
        templateUrl : "productpages/getBrandDetailsWithProductList.html",
       // controller  : "brandProductController"
    })
    
    .when("/planDescription/:planId", {
        templateUrl : "productpages/productDescription.html",
        controller  : "planDescriptionController"
    })  

   .when("/login", {
        //templateUrl : "productpages/login.html",
	   templateUrl : "productpages/userLogin.html",
	     controller  : "authController",
	     controllerAs :'authCtrl'
    })
    
    .when('/registration', {
		 templateUrl : 'productpages/UserRegistration.html',
	     controller  : 'registrationController',
	     controllerAs: 'regCtrl'
	})
    
     .when("/requestQuote/:brandId/:planId", {
        templateUrl : "productpages/requestQuote.html",
        //controller  : "requestQuoteController"
    })
 
    .when('/logout', {
		redirectTo: '/'
	})
   
	
	.when("/admindashbord", {
		resolve : {
			"check" : function($location, $rootScope){
				
				 if(!($rootScope.globals.userSession.role == 'admin')){
				    	$location.path('/');
				 }
			}
			
		},
        templateUrl : "adminpages/adminProfileLayout.html",
        controller  : "adminProfileController"
    })
    
    .when("/sellerdashbord", {
    	resolve : {
			"check" : function($location, $rootScope){
				
				 if(!($rootScope.globals.userSession.role == 'seller')){
				    	$location.path('/');
				 }
			}
			
		},
        templateUrl : "sellerpages/sellerProfileLayout.html",
        controller  : "sellerProfileController"
    })
    
    .when("/customerdashbord", {
    	resolve : {
			"check" : function($location, $rootScope){
				
				 if(!($rootScope.globals.userSession.role == 'customer')){
				    	$location.path('/');
				 }
			}
			
		},
        templateUrl : "userpages/userProfileLayout.html",
        controller  : "userProfileController"
    })
 
    
    
})

.run(
    function ($rootScope, $location, $cookieStore,$window, $http,AUTH_EVENTS,APP_CONSTANT) {
    	//Management 
    	$rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
    		console.log('THIS PATH HAS BEEN CLICKED = '+$location.path());
   	
    		var quotePath = []; 
    		quotePath = ($location.path()).match(/requestQuote/gi);
    		
    		if(( (quotePath !=null && quotePath[0] == 'requestQuote')
    			 || ($location.path() == '/admindashbord') 
    			 || ($location.path() == '/sellerdashbord') 
    			 || ($location.path() == '/customerdashbord') 
    			)
    			&& !$rootScope.globals.userSession){

    			$location.path('/login');
    				
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
		console.log('Login success + Check for redirection');
	    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
	    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;
	    
	    $rootScope.$emit("CallParentMethod", {});
	    if($rootScope.globals.userSession.role == 'customer'){
	    	$location.path('/customerdashbord');
	    }else if($rootScope.globals.userSession.role == 'admin'){
	    	$location.path('/admindashbord');
	    	
	    }else if($rootScope.globals.userSession.role == 'seller'){
	    	$location.path('/sellerdashbord');
	    }else{
	    	$location.path('/');
	    }
		
    });
    
    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.userSession) {
        $http.defaults.headers.common[APP_CONSTANT.AUTH_KEY] = $rootScope.globals.userSession.authKey; // jshint ignore:line
	    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
	    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;

    }

    

})



