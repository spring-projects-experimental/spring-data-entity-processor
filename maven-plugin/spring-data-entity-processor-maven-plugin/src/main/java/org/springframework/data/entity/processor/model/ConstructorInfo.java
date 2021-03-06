/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.entity.processor.model;

import java.util.ArrayList;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.data.mapping.PreferredConstructor;
import org.springframework.data.mapping.PreferredConstructor.Parameter;
import org.springframework.data.mapping.model.PreferredConstructorDiscoverer;

/**
 * @author Christoph Strobl
 * @since 2020/11
 */
public class ConstructorInfo {

	Class<?> type;
	List<ParameterInfo> parameterList;

	public ConstructorInfo(Class<?> type) {

		this.type = type;

		PreferredConstructor constructor = PreferredConstructorDiscoverer.discover(type);
		this.parameterList = computeParameters(constructor);
	}

	public List<ParameterInfo> getParameterList() {
		return parameterList;
	}

	private List<ParameterInfo> computeParameters(PreferredConstructor constructor) {

		if (constructor == null) {
			return Collections.emptyList();
		}
		List<ParameterInfo> model = new ArrayList<>();
		constructor.getParameters().forEach(it -> {

			Parameter param = ((Parameter) it);
			model.add(new ParameterInfo(param.getName(), param.getRawType()));
		});
		return model;
	}

	public boolean isNoArgConstructor() {
		return parameterList.isEmpty();
	}

	public Class<?> getType() {
		return type;
	}

	@Override
	public String toString() {
		return "ConstructorModel{" +
				"type=" + type +
				", parameterList=" + parameterList +
				'}';
	}
}
