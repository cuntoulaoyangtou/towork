package com.java.toworkservice.entity;


import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Table(name="dept")
public class Dept {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long did;
  private String dname;


  public long getDid() {
    return did;
  }

  public void setDid(long did) {
    this.did = did;
  }


  public String getDname() {
    return dname;
  }

  public void setDname(String dname) {
    this.dname = dname;
  }

}
