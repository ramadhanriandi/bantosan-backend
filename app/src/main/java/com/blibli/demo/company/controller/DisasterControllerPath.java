package com.blibli.demo.company.controller;

public interface DisasterControllerPath {
  String BASE_PATH = "/api/disasters";
  String GET_BY_ID = "/{disasterId}";
  String UPDATE_BY_ID = "/{disasterId}";
  String DELETE_BY_ID = "/{disasterId}";
}
