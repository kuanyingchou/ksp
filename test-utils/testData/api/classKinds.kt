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

// MODULE: lib2
// FILE: placeholder.kt
// FILE: lib2/A.java
package lib2;
public class A<T> {}
// FILE: lib2/B.java
package lib2;
public class B {}

// MODULE: lib1(lib2)
// FILE: placeholder.kt
// FILE: lib1/Test.java
package lib1;
import lib2.*;
import java.util.*;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import org.jetbrains.annotations.NotNull;
class Test {
    <T extends Object> void nullable() { }
    <T extends @NotNull Object> void notNull() { }
    <T extends B> void lib() { }
    <T extends @NotNull B> void notNullLib() { }
    <T extends A<String>> void genericLib() { }
    <T extends @NotNull A<String>> void notNullGenericLib() { }
    <T extends List<String>> void genericLibList() { }
    <T extends @NotNull List<String>> void notNullGenericLibList() { }
}


// MODULE: main(lib1, lib2)
// FILE: K.kt
class KC
interface KI
annotation class KA
object KO
enum class KE {
    ENTRY
}

// FILE: J.java
import org.jetbrains.annotations.NotNull;
import java.util.*;
class JC {}
interface JI {}
@interface JA {}
enum JE {
    ENTRY
}

class A<T> {}

class B {}
class Test {
    <T extends Object> void nullable() { }
    <T extends @NotNull Object> void notNull() { }
    <T extends A<String>> void generic() { }
    <T extends @NotNull A<String>> void notNullGeneric() { }
    <T extends lib2.B> void lib() { }
    <T extends @NotNull lib2.B> void notNullLib() { } // @NotNull is missing
    <T extends lib2.A<String>> void genericLib() { }
    <T extends @NotNull lib2.A<String>> void notNullGenericLib() { } // @NotNull is missing
    <T extends List<String>> void genericLibList() { }
    <T extends @NotNull List<String>> void notNullGenericLibList() { }
}
