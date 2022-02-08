package com.kiyotakeshi.junitPlayground.oreilly.recruitment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfileTest {

    private Profile profile;

    private Question questionBonus;
    private Answer answerYesBonus;
    private Answer answerNoBonus;

    private Question questionRelocation;
    private Answer answerYesRelocation;
    private Answer answerNoRelocation;

    private Question questionLongVacation;
    private Answer answerYesLongVacation;
    private Answer answerNoLongVacation;

    private Criteria criteria;

    @BeforeEach
    void createProfile() {
        profile = new Profile("Mike popcorn, Inc");
    }

    @BeforeEach
    void createCriteria() {
        criteria = new Criteria();
    }

    @BeforeEach
    void createQuestionAndAnswer() {
        questionBonus = new BooleanQuestion(1, "has Bonus?");
        answerYesBonus = new Answer(Bool.TRUE, questionBonus);
        answerNoBonus = new Answer(Bool.FALSE, questionBonus);

        questionRelocation = new BooleanQuestion(1, "has relocation?");
        answerYesRelocation = new Answer(Bool.TRUE, questionRelocation);
        answerNoRelocation = new Answer(Bool.FALSE, questionRelocation);

        questionLongVacation = new BooleanQuestion(1, "can take long vacation?");
        answerYesLongVacation = new Answer(Bool.TRUE,questionLongVacation);
        answerNoLongVacation = new Answer(Bool.FALSE, questionLongVacation);
    }

    @Test
    @DisplayName("必須条件にマッチしない場合、 matches は false を返す")
    void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        // set up(arrange)
        profile.add(answerNoBonus);
        criteria.add(
                new Criterion(answerYesBonus, Weight.MustMatch));

        // exercise(act)
        boolean matches = profile.matches(criteria);

        // verify(assert)
        Assertions.assertFalse(matches);
    }

    @Test
    @DisplayName("不問の条件がある場合、 matches は true を返す")
    void matchAnswersTrueForAnyDontCareCriteria() {
        // set up(arrange)
        profile.add(answerNoBonus);
        criteria.add(
                new Criterion(answerYesBonus, Weight.DontCare));

        // exercise(act)
        boolean matches = profile.matches(criteria);

        // verify(assert)
        Assertions.assertTrue(matches);
    }

    @Test
    public void matchAnswersTrueWhenAnyOfMultipleCriteriaMatch() {
        // set up(arrange)
        profile.add(answerYesRelocation);
        profile.add(answerYesLongVacation);
        criteria.add(new Criterion(answerYesRelocation, Weight.Important));
        criteria.add(new Criterion(answerYesLongVacation, Weight.Important));

        // exercise(act)
        boolean matches = profile.matches(criteria);

        // verify(assert)
        Assertions.assertTrue(matches);
    }

    @Test
    public void matchAnswersFalseWhenNoneOfMultipleCriteriaMatch() {
        profile.add(answerNoRelocation);
        profile.add(answerNoLongVacation);
        criteria.add(new Criterion(answerYesRelocation, Weight.Important));
        criteria.add(new Criterion(answerYesLongVacation, Weight.Important));

        boolean matches = profile.matches(criteria);

        Assertions.assertFalse(matches);
    }
}
