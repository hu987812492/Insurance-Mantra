/**
 * 
 */
'use strict';
// Step 1: declare modules
 angular.module("authModule",[]);

 angular
 .module('appCoreModule', [
	 'ngRoute',
     'ngCookies'
 ]);
//Step 2: Register App
angular.module("app", 	
			['appCoreModule',
			'authModule'
		 ]);



