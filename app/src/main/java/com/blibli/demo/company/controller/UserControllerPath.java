package com.blibli.demo.company.controller;

public interface UserControllerPath {
  String BASE_PATH = "/api/users";
  String LOGIN = "/login";
  String REGISTER = "/register";
  String UPDATE_BY_ID = "/{userId}";
  String LOGOUT = "/logout";
}
