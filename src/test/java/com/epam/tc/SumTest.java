package com.epam.tc;

import com.epam.tat.module4.Calculator;
import com.epam.tc.utils.SumDataProvider;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class SumTest {

    @Test(groups = {"sum"},
          dataProviderClass = SumDataProvider.class, dataProvider = "correct sum data")
    public void sumOkTest(long a, long b, long res) {
        Calculator calculator = new Calculator();
        long actual = calculator.sum(a, b);
        assertThat(actual)
            .isEqualTo(res);
    }

    @Test(groups = {"sum"},
          dataProviderClass = SumDataProvider.class, dataProvider = "big sum data")
    public void sumBigTest(long a, long b, long res) {
        Calculator calculator = new Calculator();
        long actual = calculator.sum(a, b);
        assertThat(actual)
            .isEqualTo(res);
    }

    @Test(groups = {"sum"},
          dataProviderClass = SumDataProvider.class, dataProvider = "negative sum data")
    public void sumNegativeNumberTest(double a, double b, double res) {
        Calculator calculator = new Calculator();
        double actual = calculator.sum(a, b);
        assertThat(actual)
            .isEqualTo(res);
    }

}
