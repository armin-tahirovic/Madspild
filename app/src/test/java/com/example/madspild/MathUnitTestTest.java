package com.example.madspild;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MathUnitTestTest {

  private MathUnitTest math;

  @Before
  public void setUp() {
    math = new MathUnitTest();
  }

  @Test
  public void result() {
    int result = math.result(5, 5);
    assertEquals(25, result);
  }
}
