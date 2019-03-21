package com.qa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.qa.rest.WebMockTest;
import com.qa.sanity.SmokeTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   com.qa.rest.MockitoTest.class,
   com.qa.service.MockitoTest.class,WebMockTest.class,SmokeTest.class
})

public class Runner {

}
