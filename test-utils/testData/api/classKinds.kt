/*
 * Copyright 2020 Google LLC
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// WITH_RUNTIME
// TEST PROCESSOR: ClassKindsProcessor
// EXPECTED:
// JA: ANNOTATION_CLASS
// JC: CLASS
// JE.ENTRY: ENUM_ENTRY
// JE: ENUM_CLASS
// JI: INTERFACE
// KA: ANNOTATION_CLASS
// KC: CLASS
// KE.ENTRY: ENUM_ENTRY
// KE: ENUM_CLASS
// KI: INTERFACE
// KO: OBJECT
// kotlin.Annotation: INTERFACE
// kotlin.Any: CLASS
// kotlin.Deprecated: ANNOTATION_CLASS
// kotlin.DeprecationLevel.WARNING: ENUM_ENTRY
// kotlin.DeprecationLevel: ENUM_CLASS
// kotlin.Double.Companion: OBJECT
// END
// FILE: Test.kt
interface Usage : Foo<Long, Integer> {
    fun foo(param: Foo<Double, Integer>): Foo<String, Integer>
}
interface Foo<V1, V2: Integer> : Bar<Baz<V1, Number>, V2> {}
interface Bar<U1, U2: Integer> : Baz<U1, U2> {}
interface Baz<T1, T2: Number> {
    fun method1(): T1
    fun method2(): T2
}

// FILE: K.kt
class KC
interface KI
annotation class KA
object KO
enum class KE {
    ENTRY
}

// FILE: J.java
class JC {}
interface JI {}
@interface JA {}
enum JE {
    ENTRY
}
