package com.kiyotakeshi.junitPlayground.oreilly.recruitment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    private Profile profile;
    private BooleanQuestion question;
    // 複数の Criterion を保持
    private Criteria criteria;

    @BeforeEach
    void setUp() {
        profile = new Profile("Mike popcorn, Inc");
        question = new BooleanQuestion(1, "has Bonus?");
        criteria = new Criteria();
    }

    @Test
    @DisplayName("必須条件にマッチしない場合、 matches は false を返す")
    void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        // set up(arrange)
        profile.add(new Answer(Bool.FALSE, question));
        criteria.add(
                new Criterion(new Answer(Bool.TRUE, question), Weight.MustMatch));

        // exercise(act)
        boolean matches = profile.matches(criteria);

        // verify(assert)
        Assertions.assertFalse(matches);
    }

    @Test
    @DisplayName("不問の条件がある場合、 matches は true を返す")
    void matchAnswersTrueForAnyDontCareCriteria() {
        // set up(arrange)
        profile.add(new Answer(Bool.FALSE, question));
        criteria.add(
                new Criterion(new Answer(Bool.TRUE, question), Weight.DontCare));

        // exercise(act)
        boolean matches = profile.matches(criteria);

        // verify(assert)
        Assertions.assertTrue(matches);
    }
}
