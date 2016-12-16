/*
 * Example2 for the Java 9 talk
 *
 * (c)2016, Ivan Krylov
 *
 */
 package com.azul.testpackage2;

public class A
{
  public static void main(String[] args)
  {
    System.out.println("Calling from com.azul.modules.module2.com.azul.testpackage2.A.m() ");
    com.azul.testpackage1.A.m();
  }
}
