/**
 * 
 */
'use strict';
// Step 1: declare modules
 angular.module("authModule",[]);
 angular.module("registration.module", []);
 angular.module("seller.registration.module", []);
 
 angular
 .module('appCoreModule', [
	 'ngRoute',
     'ngCookies',
     'ui.bootstrap'
 ]);
//Step 2: Register App
angular.module("app", 	
			['appCoreModule',
			'authModule',
			'registration.module',
			'seller.registration.module'
		 ]);



