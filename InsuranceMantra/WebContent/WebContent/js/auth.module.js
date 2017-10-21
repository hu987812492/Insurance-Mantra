/**
 * 
 */

var authModule = angular.module("authModule");

authModule
.controller('authController', function($scope,$rootScope,authService,AUTH_EVENTS) {
	var authCtrl = this; // variable should be Same as controllerAs: 'authCtrl'
	authCtrl.message = "";
	authCtrl.error = false;
	
	authCtrl.credentials = {
		username : '',
		password : ''
	};
	
	authCtrl.clearCredentials = function() {
		authService.clearCredentials();
	}
	
	authCtrl.login = function() {
		authService.login(authCtrl.credentials,callbackSuccess,callbackError);
	};
	
	var callbackSuccess = function(data,headers) { // Status Code:200
        if(data.success) {
			authCtrl.message = "Login Success";
			authService.setCredentials(data.user,headers);
    			$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);

        } 

    };
    
    var callbackError = function(data,headers) {
    		authCtrl.message = data.message;
		authCtrl.error = true;
		$rootScope.$broadcast(AUTH_EVENTS.loginFailed);
       
    };
    
});


authModule.factory('authService', function($rootScope,$http,$timeout,$cookieStore,$window,APP_CONSTANT,AUTH_EVENTS) {
		var authService = {};
		
		authService.login = function (data, callback,callbackError) {
			if(APP_CONSTANT.DEMO){
			
            /* Dummy authentication for testing, uses $timeout to simulate api call
             ----------------------------------------------*/
            $timeout(function(){
            	
            		var response;
            		if(data.username ==='test' && data.password==='test'){
            			response = { success:true,
            						 user:{
            					  			id:'01',
            					  			name:'Ashwin',
            					  			role:'customer'            					  		  
            					  		   }
            						};
            		}else{
                    response = {message:'Username or password is incorrect'};
            		}
     
                callback(response);
            }, 1000);
			}else{

            /* Use this for real authentication
             ----------------------------------------------*/
            $http.post(
            			APP_CONSTANT.REMOTE_HOST+'/auth',
            			{ 
            				username: data.username,
            				password: data.password 
            			}
            			
            			)
            			//On Success of $http call
            			.success(function (data, status, headers, config) {
            					callback(data,headers);
            			})
            			.error(function (data, status, headers, config) { // IF STATUS CODE NOT 200
            						callbackError(data,headers);
            			});
			}

        };


        authService.setCredentials = function (data,headers) {
        	 //Setting of Auth ID
         var authdata = data.name + ':' + data.id;
  	    
         var authKey= APP_CONSTANT.DEMO?'dummy':headers('AUTH_KEY');
         
         $rootScope.globals = {
        		 					userSession: {
				                    username: data.name,
				                    role:data.role,
				                    authdata: authdata,
				                    authKey: authKey
        		 				 }
         };
 	     $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
   	     $http.defaults.headers.common['AUTH_KEY'] = authKey; // jshint ignore:line            
 	     $cookieStore.put('globals', $rootScope.globals);
 	     //$window.sessionStorage.setItem("globals", angular.toJson($rootScope.globals));
 	     //$window.sessionStorage.globals = $rootScope.globals;
    };
 
    authService.clearCredentials = function () {
    		console.log('Logout clearCredentials');
        $rootScope.globals = {};
        $cookieStore.remove('globals');
        $http.defaults.headers.common.Authorization = 'Basic ';
        $rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);

    };		


	authService.isAuthenticated = function() {
		return !!$rootScope.globals.userSession.authKey;
	};

	authService.isAuthorized = function(authorizedRoles) {
		if (!angular.isArray(authorizedRoles)) {
			authorizedRoles = [ authorizedRoles ];
		}
		return (authService.isAuthenticated() && 
				authorizedRoles.indexOf(Session.userRole) !== -1);
	};

	return authService;
});
