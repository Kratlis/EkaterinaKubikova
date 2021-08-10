package com.epam.tc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import com.epam.tat.module4.Calculator;
import com.epam.tc.utils.DivideDataProvider;
import org.testng.annotations.Test;

public class DivideTest {
    @Test(groups = {"divide"},
          dataProviderClass = DivideDataProvider.class, dataProvider = "correct divide data")
    public void sumOkTest(long a, long b, long res) {
        Calculator calculator = new Calculator();
        long actual = calculator.div(a, b);
        assertThat(actual)
            .isEqualTo(res);
    }

    @Test(groups = {"divide"},
          dataProviderClass = DivideDataProvider.class, dataProvider = "small divide data")
    public void divideSmallTest(double a, double b, double res) {
        Calculator calculator = new Calculator();
        double actual = calculator.div(a, b);
        assertThat(actual)
            .isEqualTo(res, offset(1E-9));
    }

    @Test(groups = {"divide"},
          dataProviderClass = DivideDataProvider.class, dataProvider = "double divide data")
    public void divideDoubleNumberTest(double a, double b, double res) {
        Calculator calculator = new Calculator();
        double actual = calculator.div(a, b);
        assertThat(actual)
            .isEqualTo(res, offset(1E-9));
    }
}
