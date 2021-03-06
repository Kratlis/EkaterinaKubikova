package com.epam.tc.hw1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

import com.epam.tat.module4.Calculator;
import com.epam.tc.hw1.utils.SubtractDataProvider;
import org.testng.annotations.Test;

public class SubtractTest {

    @Test(groups = {"subtract"},
          dataProviderClass = SubtractDataProvider.class, dataProvider = "correct subtract data")
    public void subtractOkTest(long a, long b, long res) {
        Calculator calculator = new Calculator();
        long actual = calculator.sub(a, b);
        assertThat(actual)
            .isEqualTo(res);
    }

    @Test(groups = {"subtract"},
          dataProviderClass = SubtractDataProvider.class, dataProvider = "big subtract data")
    public void subtractBigTest(long a, long b, long res) {
        Calculator calculator = new Calculator();
        long actual = calculator.sub(a, b);
        assertThat(actual)
            .isEqualTo(res);
    }

    @Test(groups = {"subtract"},
          dataProviderClass = SubtractDataProvider.class, dataProvider = "double subtract data")
    public void subtractDoubleTest(double a, double b, double res) {
        Calculator calculator = new Calculator();
        double actual = calculator.sub(a, b);
        assertThat(actual)
            .isEqualTo(res, offset(1E-6));
    }

}
