package com.kiyotakeshi.junitPlayground;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// junit の assertion ではなく、third party の AssertJ を使用するための static import
import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.is;
//import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void 乗数結果が取得できる() {
        Calculator calculator = new Calculator();
        int expected = 12;
        int actual = calculator.multiply(3, 4);

        // 値を比較検証する宣言は assertion と呼ばれる
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void divideで3と2の除数結果が取得できる() {
        Calculator calculator = new Calculator();
        float expected = 1.5f;
        // 何を検証するべきかを意識し具体的な値を当てはめることでプログラムの潜在的な問題を見つけることができる
        float actual = calculator.divide(3, 2);
        Assertions.assertEquals(expected, actual);

        // Hamcrest などの third party の Matcher API を使用する場合、Hamcrest の assertThat を使用する
        // @see https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions-third-party
        // > Instead, developers are encouraged to use the built-in support for matchers provided by third-party assertion libraries.
        MatcherAssert.assertThat(actual, is(expected));

        // org.assertj.core.api.Assertions.assertThat() を static import したもの
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void divideで3と0のときIllegalArgumentException() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Calculator calculator = new Calculator();
            calculator.divide(3, 0);
        });

        Assertions.assertEquals("divide by zero.", exception.getMessage());
    }
}
