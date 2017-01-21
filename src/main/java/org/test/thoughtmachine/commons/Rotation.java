package org.test.thoughtmachine.commons;

import com.google.common.collect.ImmutableMap;

/**
 * Created by leszek on 21/01/17.
 */
public enum Rotation {
    N,
    E,
    S,
    W;

    public static ImmutableMap<Rotation, Rotation> leftOf = ImmutableMap.of(
            N, W,
            W, S,
            S, E,
            E, N
    );

    public static ImmutableMap<Rotation, Rotation> rightOf = ImmutableMap.of(
            N, E,
            E, S,
            S, W,
            W, N
    );
}
