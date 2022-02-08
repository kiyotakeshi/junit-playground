package com.kiyotakeshi.junitPlayground.oreilly.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreCollectionTest {

    @Test
    @DisplayName("2つの数値の算術平均を返す")
    void answersArithmeticMeanOfTwoNumbers() {
        // set up
        ScoreCollection sut = new ScoreCollection();
        sut.add(() -> 5);
        sut.add(() -> 7);
        int expected = 6;

        // exercise
        int actual = sut.arithmeticMean();

        // verify
        assertThat(actual).isEqualTo(expected);
    }
}
