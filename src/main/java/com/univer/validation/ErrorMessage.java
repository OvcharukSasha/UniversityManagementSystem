/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univer.validation;

/**
 *
 * @author Root
 */
public class ErrorMessage {
    private String fieldName;
	private String message;

	public ErrorMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}
}
