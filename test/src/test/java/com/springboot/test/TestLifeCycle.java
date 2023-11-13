package com.springboot.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {
    @BeforeAll
    static void beforeAll(){
        System.out.println("## BeforeAll Annotation 호출 ##");
        System.out.println();
    }

    @AfterAll
    static void afterAll(){
        System.out.println("## AfterAll Annotation 호출 ##");
        System.out.println();
    }

    @BeforeEach
    static void beforeEach(){
        System.out.println("## BeforeEach Annotation 호출 ##");
        System.out.println();
    }

    @AfterEach
    static void afterEach(){
        System.out.println("## AfterEach Annotation 호출 ##");
        System.out.println();
    }

    @Test
    static void test1(){
        System.out.println("## test1 시작 ##");
        System.out.println();
    }

    @Test
    @DisplayName("Test Case 2!!")
    static void test2(){
        System.out.println("## test2 시작 ##");
        System.out.println();
    }

    @Test
    @Disabled
    static void test3(){
        System.out.println("## test3 시작 ##");
        System.out.println();
    }
}
