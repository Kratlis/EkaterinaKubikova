package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.tat.module4.Calculator;
import com.epam.tc.hw1.utils.MultiplyDataProvider;
import org.testng.annotations.Test;

public class MultiplyTest {

    @Test(groups = {"multiply"},
          dataProviderClass = MultiplyDataProvider.class, dataProvider = "correct multiply data")
    public void multiplyOkTest(long a, long b, long res) {
        Calculator calculator = new Calculator();
        long actual = calculator.mult(a, b);
        assertThat(actual)
            .isEqualTo(res);
    }

    @Test(groups = {"multiply"},
          dataProviderClass = MultiplyDataProvider.class, dataProvider = "big multiply data")
    public void multiplyBigTest(long a, long b, double res) {
        Calculator calculator = new Calculator();
        double actual = calculator.mult(a, b);
        assertThat(actual)
            .isEqualTo(res);
    }

    @Test(groups = {"multiply"},
          dataProviderClass = MultiplyDataProvider.class, dataProvider = "double multiply data")
    public void multiplyDoubleTest(double a, double b, double res) {
        Calculator calculator = new Calculator();
        double actual = calculator.mult(a, b);
        assertThat(actual)
            .isEqualTo(res);
    }
}
