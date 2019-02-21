/*
 * Copyright (c) 2018 Isa Hekmatizadeh.
 *
 *   This file is part of Ganjex.
 *
 *    Ganjex is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    Ganjex is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with Ganjex.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.piran.ganjex.api;

import java.lang.annotation.*;

/**
 * The {@link StartupHook} annotated elements are used by the framework to register their hooks
 * to be executed right after a new service is loaded.
 * <p>
 * Note that if a library is changed, all the elements annotated with {@link ShutdownHook} in all services
 * would be invoked, libraries would be reloaded and then all the elements annotated with {@link StartupHook}
 * in all services would be invoked again.
 * <p>
 * The {@link StartupHook} annotated methods should be surrounded by a class
 * containing a default constructor with only one parameter of {@link ServiceContext} type.
 * This parameter indicates information about the service and contains the service classLoader
 * used by the hooks to find elements in the services.
 *
 * @author hekmatof
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StartupHook {
    /**
     * Indicates the priority of the hook. The lower the number, the higher the priority of the execution.
     * It is recommended not to use 0 or any number lower than 10 if it is not vital.
     *
     * @return the priority of the hook.
     */
    int priority() default 100;
}
