/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

 // Derived from jdk/test//java/lang/Class/forName/modules/TestLayer.java
package com.azul.runpackage;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.reflect.Layer;
import java.lang.reflect.Method;
import java.lang.reflect.Module;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class Example05 {
    private static final Path MODS_DIR = Paths.get("target");
    private static final Set<String> modules = Set.of(
      "com.azul.modules.module1","com.azul.modules.module2");
      private static Object o=new Object();

    public static void main(String[] args) throws Exception {
        ModuleFinder finder = ModuleFinder.of(MODS_DIR);

        Configuration parent = Layer.boot().configuration();
        Configuration cf = parent.resolveRequiresAndUses(ModuleFinder.of(),
                                                         finder,
                                                         modules);
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        Layer layer = Layer.boot().defineModulesWithManyLoaders(cf, scl);

        Module m1 = layer.findModule("com.azul.modules.module1").get();
        Module m2 = layer.findModule("com.azul.modules.module2").get();

        // find exported and non-exported class from a named module
        Class<?> c1 = findClass(m1, "com.azul.testpackage.A");
        Class<?> c2 = findClass(m2, "com.azul.testpackage.A");

        Method mtd1 = c1.getDeclaredMethod("m", null);
        Method mtd2 = c2.getDeclaredMethod("m", null);
        mtd1.invoke(o);
        mtd2.invoke(o);

        ClassLoader cLoader = c1.getClassLoader();
        if (cLoader == null) {
           System.out.println("The default system class was used.");
        }
        else {
           // returns the class loader
           Class loaderClass = cLoader.getClass();
           System.out.println("Class associated with ClassLoader = " +
           loaderClass.getName() + ". toString = " + cLoader.toString());
        }

        cLoader = c2.getClassLoader();

        if (cLoader == null) {
           System.out.println("The default system class was used.");
        }
        else {
           // returns the class loader
           Class loaderClass = cLoader.getClass();
           System.out.println("Class associated with ClassLoader = " +
           loaderClass.getName() + ". toString = " + cLoader.toString());
        }
    }

    static Class<?> findClass(Module module, String cn) {
        Class<?> c = Class.forName(module, cn);
        if (c == null) {
            throw new RuntimeException(cn + " not found in " + module);
        }
        if (c.getModule() != module) {
            throw new RuntimeException(c.getModule() + " != " + module);
        }
        return c;
    }
}
