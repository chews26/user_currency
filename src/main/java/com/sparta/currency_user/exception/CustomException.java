package com.sparta.currency_user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.naming.AuthenticationException;

@Getter
@RequiredArgsConstructor
public class CustomException extends Exception {
    private final CustomErrorCode  errorCode;
}
