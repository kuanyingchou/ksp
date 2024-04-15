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
// TEST PROCESSOR: AnnotationArgumentProcessor
// EXPECTED:
// Str
// 42
// File
// Local
// END
// MODULE: module1
// FILE: lib/a.kt
package lib

annotation class Bar(
    val argStr: String,
    val argInt: Int,
    val argClsLib: kotlin.reflect.KClass<*>,
    val argClsSrc: kotlin.reflect.KClass<*>,
)

@Bar(
    "Str",
    40 + 2,
    java.io.File::class,
    Local::class,
)
class Local

// MODULE: main(module1)
// FILE: placeholder.kt
