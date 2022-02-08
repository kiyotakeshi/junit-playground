package com.kiyotakeshi.junitPlayground.oreilly.basic;

// スコアを返すという機能だけを持つ
@FunctionalInterface
public interface Scoreable {
    int getScore();
}
