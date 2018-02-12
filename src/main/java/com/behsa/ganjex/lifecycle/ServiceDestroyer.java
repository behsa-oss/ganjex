/*
 * Copyright 2018 Behsa Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.behsa.ganjex.lifecycle;

import com.behsa.ganjex.api.Ganjex;
import com.behsa.ganjex.api.ServiceContext;

import java.io.File;
import java.util.Objects;

/**
 * @author Esa Hekmatizadeh
 */
public class ServiceDestroyer {
	public File jar;

	public ServiceDestroyer(File jar) {
		this.jar = jar;
	}

	public void destroy(Ganjex app) {
		ServiceContext context = app.lifecycleManagement().findContext(jar.getName());
		if (Objects.nonNull(context))
			app.lifecycleManagement().serviceDestroyed(context);
	}
}
