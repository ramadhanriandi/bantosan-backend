package com.blibli.demo.company.controller;

public interface AuthControllerPath {
  String BASE_PATH = "/api/auth";
  String LOGIN = "/login";
  String REGISTER = "/register";
  String UPDATE_BY_ID = "/{userId}";
  String LOGOUT = "/logout";
}
