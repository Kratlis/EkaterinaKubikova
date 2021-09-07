package com.epam.tc.hw5.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"})
public class RunTests extends AbstractTestNGCucumberTests {

    /*@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }*/
}
