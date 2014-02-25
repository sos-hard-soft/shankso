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
/*package org.agoncal.application.petstore.exception;*/

import javax.ejb.ApplicationException;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 *         Thrown when data is not valid
 */

@ApplicationException(rollback = true)
public class ValidationException extends RuntimeException {

    // ======================================
    // =            Constructors            =
    // ======================================

    public ValidationException(String message) {
        super(message);
    }
}