/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.commons.lang3

import spock.lang.Title

@Title("org.apache.commons.lang3.StringUtilsStartsEndsWithTest")
class StringUtilsStartsEndsWithSpec extends AbstractLangSpec {

    static final String foo    = "foo";
    static final String bar    = "bar";
    static final String foobar = "foobar";
    static final String FOO    = "FOO";
    static final String BAR    = "BAR";
    static final String FOOBAR = "FOOBAR";

    /**
     * Test StringUtils.startsWith()
     */
    def "testStartsWith"() {
        expect:
        StringUtils.startsWith(null, null)
        StringUtils.startsWith(FOOBAR, "")
        StringUtils.startsWith(foobar, foo)
        StringUtils.startsWith(FOOBAR, FOO)

        !StringUtils.startsWith(null, FOO)
        !StringUtils.startsWith(FOOBAR, null)
        !StringUtils.startsWith(foobar, FOO)
        !StringUtils.startsWith(FOOBAR, foo)
        !StringUtils.startsWith(foo, foobar)
        !StringUtils.startsWith(bar, foobar)
        !StringUtils.startsWith(foobar, bar)
        !StringUtils.startsWith(FOOBAR, BAR)
        !StringUtils.startsWith(foobar, BAR)
        !StringUtils.startsWith(FOOBAR, bar)
    }

    /**
     * Test StringUtils.testStartsWithIgnoreCase()
     */
    def "testStartsWithIgnoreCase"() {
        expect:
        StringUtils.startsWithIgnoreCase(null, null)
        StringUtils.startsWithIgnoreCase(FOOBAR, "")
        StringUtils.startsWithIgnoreCase(foobar, foo)
        StringUtils.startsWithIgnoreCase(FOOBAR, FOO)
        StringUtils.startsWithIgnoreCase(foobar, FOO)
        StringUtils.startsWithIgnoreCase(FOOBAR, foo)

        !StringUtils.startsWithIgnoreCase(FOOBAR, null)
        !StringUtils.startsWithIgnoreCase(null, FOO)
        !StringUtils.startsWithIgnoreCase(foo, foobar)
        !StringUtils.startsWithIgnoreCase(bar, foobar)
        !StringUtils.startsWithIgnoreCase(foobar, bar)
        !StringUtils.startsWithIgnoreCase(FOOBAR, BAR)
        !StringUtils.startsWithIgnoreCase(foobar, BAR)
        !StringUtils.startsWithIgnoreCase(FOOBAR, bar)
    }

    def "testStartsWithAny"() {
        expect:
        StringUtils.startsWithAny("abcxyz", null, "xyz", "abc")
        StringUtils.startsWithAny("abcxyz", "")
        StringUtils.startsWithAny("abcxyz", "abc")

        StringUtils.startsWithAny("abcxyz", new StringBuilder("xyz"), new StringBuffer("abc")) // "StringUtils.startsWithAny(abcxyz, StringBuilder(xyz), StringBuffer(abc))"
        StringUtils.startsWithAny(new StringBuffer("abcxyz"), new StringBuilder("xyz"), new StringBuffer("abc")) // "StringUtils.startsWithAny(StringBuffer(abcxyz), StringBuilder(xyz), StringBuffer(abc))"

        !StringUtils.startsWithAny(null, (String[]) null)
        !StringUtils.startsWithAny(null, "abc")
        !StringUtils.startsWithAny("abcxyz", (String[]) null)
        !StringUtils.startsWithAny("abcxyz")
        !StringUtils.startsWithAny("abcxyz", null, "xyz", "abcd")
        !StringUtils.startsWithAny("abcxyz", null, "xyz", "ABCX")
        !StringUtils.startsWithAny("ABCXYZ", null, "xyz", "abc")
    }


    /**
     * Test StringUtils.endsWith()
     */
    def "testEndsWith"() {
        expect:
        StringUtils.endsWith(null, null)
        StringUtils.endsWith(FOOBAR, "")

        StringUtils.endsWith(foobar, bar)
        StringUtils.endsWith(FOOBAR, BAR)

        !StringUtils.endsWith(FOOBAR, null)
        !StringUtils.endsWith(null, FOO)

        !StringUtils.endsWith(foobar, foo)
        !StringUtils.endsWith(FOOBAR, FOO)
        !StringUtils.endsWith(foobar, FOO)
        !StringUtils.endsWith(FOOBAR, foo)

        !StringUtils.endsWith(foo, foobar)
        !StringUtils.endsWith(bar, foobar)

        !StringUtils.endsWith(foobar, BAR)
        !StringUtils.endsWith(FOOBAR, bar)

        // "alpha, beta, gamma, delta".endsWith("delta")
        StringUtils.endsWith("\u03B1\u03B2\u03B3\u03B4", "\u03B4")
        // "alpha, beta, gamma, delta".endsWith("gamma, DELTA")
        !StringUtils.endsWith("\u03B1\u03B2\u03B3\u03B4", "\u03B3\u0394")
    }

    /**
     * Test StringUtils.endsWithIgnoreCase()
     */
    def "testEndsWithIgnoreCase"() {
        expect:
        StringUtils.endsWithIgnoreCase(null, null)
        StringUtils.endsWithIgnoreCase(FOOBAR, "")

        !StringUtils.endsWithIgnoreCase(FOOBAR, null)
        !StringUtils.endsWithIgnoreCase(null, FOO)

        !StringUtils.endsWithIgnoreCase(foobar, foo)
        !StringUtils.endsWithIgnoreCase(FOOBAR, FOO)
        !StringUtils.endsWithIgnoreCase(foobar, FOO)
        !StringUtils.endsWithIgnoreCase(FOOBAR, foo)

        !StringUtils.endsWithIgnoreCase(foo, foobar)
        !StringUtils.endsWithIgnoreCase(bar, foobar)

        StringUtils.endsWithIgnoreCase(foobar, bar)
        StringUtils.endsWithIgnoreCase(FOOBAR, BAR)
        StringUtils.endsWithIgnoreCase(foobar, BAR)
        StringUtils.endsWithIgnoreCase(FOOBAR, bar)

        // javadoc
        StringUtils.endsWithIgnoreCase("abcdef", "def")
        StringUtils.endsWithIgnoreCase("ABCDEF", "def")
        !StringUtils.endsWithIgnoreCase("ABCDEF", "cde")

        // "alpha, beta, gamma, delta".endsWith("DELTA")
        StringUtils.endsWithIgnoreCase("\u03B1\u03B2\u03B3\u03B4", "\u0394")
        // "alpha, beta, gamma, delta".endsWith("GAMMA")
        !StringUtils.endsWithIgnoreCase("\u03B1\u03B2\u03B3\u03B4", "\u0393")
    }

    def "testEndsWithAny"() {
        expect:
        StringUtils.endsWithAny("abcXYZ", "def", "YZ")
        StringUtils.endsWithAny("abcxyz", "")
        StringUtils.endsWithAny("abcxyz", "xyz")
        StringUtils.endsWithAny("abcxyz", null, "xyz", "abc")
        StringUtils.endsWithAny("abcXYZ", "def", "XYZ")

        !StringUtils.endsWithAny(null, (String) null)
        !StringUtils.endsWithAny(null, "abc")
        !StringUtils.endsWithAny("abcxyz", (String) null)
        !StringUtils.endsWithAny("defg", null, "xyz", "abc")
        !StringUtils.endsWithAny("abcXYZ", "def", "xyz")

        /*
         * Type null of the last argument to method endsWithAny(CharSequence, CharSequence...)
         * doesn't exactly match the vararg parameter type.
         * Cast to CharSequence[] to confirm the non-varargs invocation,
         * or pass individual arguments of type CharSequence for a varargs invocation.
         *
         * assertFalse(StringUtils.endsWithAny("abcXYZ", null)); // replace with specific types to avoid warning
         */
        !StringUtils.endsWithAny("abcXYZ", (CharSequence) null)
        !StringUtils.endsWithAny("abcXYZ", (CharSequence[]) null)
        StringUtils.endsWithAny("abcXYZ", "")

        StringUtils.endsWithAny("abcxyz", new StringBuilder("abc"), new StringBuffer("xyz"))
        StringUtils.endsWithAny(new StringBuffer("abcxyz"), new StringBuilder("abc"), new StringBuffer("xyz"))
    }

}
