package com.azul.testpackage2;

public class A
{
  public static void main(String[] args)
  {
    System.out.println("  Calling from om.azul.modules.module2.com.azul.testpackage2.A.m() ");
    com.azul.testpackage1.A.m();
  }
}
