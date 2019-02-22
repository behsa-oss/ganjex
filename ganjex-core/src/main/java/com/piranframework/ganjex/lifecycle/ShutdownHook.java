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

package com.piranframework.ganjex.lifecycle;

import com.piranframework.ganjex.api.ServiceContext;

import java.util.function.Consumer;

/**
 * inner representation of the methods in the framework which annotated with the
 * {@link ShutdownHook} annotation
 *
 * @author hekmatof
 * @since 1.0
 */
public class ShutdownHook implements Comparable<ShutdownHook> {
  private final Consumer<ServiceContext> hook;
  private final Integer priority;

  public ShutdownHook(Consumer<ServiceContext> hook, Integer priority) {
    this.hook = hook;
    this.priority = priority == null ? 100 : priority;
  }

  Consumer<ServiceContext> hook() {
    return hook;
  }

  @Override
  public int compareTo(ShutdownHook o) {
    return priority.compareTo(o.priority);
  }

}
