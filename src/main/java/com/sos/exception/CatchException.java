/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sos.exception;

/**
 *
 * @user mab.salhi
 */
/*package org.agoncal.application.petstore.web;*/

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
* Any controller using this interceptor binding will catch and display exceptions on the JSF page
*/
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface CatchException {
}