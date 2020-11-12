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
package org.springframework.data;

import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.data.mapping.PreferredConstructor;
import org.springframework.data.mapping.model.PreferredConstructorDiscoverer;

/**
 * @author Christoph Strobl
 * @since 2020/11
 */
public class TypeModel implements Iterable<PropertyModel> {

	private final Class<?> type;
	private final Set<AnnotationModel> annotations;
	private final Set<PropertyModel> properties;
	private ConstructorModel constructor;

	public TypeModel(Class<?> type) {

		this.type = type;
		this.annotations = new LinkedHashSet<>();
		this.properties = new LinkedHashSet<>();
	}

	List<Parameter> getConstructorArgs() {

		PreferredConstructor constructor = PreferredConstructorDiscoverer.discover(type);
		return constructor != null ? constructor.getParameters() : Collections.emptyList();
	}

	public Set<AnnotationModel> getAnnotations() {
		return annotations;
	}

	public TypeModel annotations(Set<AnnotationModel> annotations) {
		this.annotations.addAll(annotations);
		return this;
	}

	public void setConstructor(ConstructorModel constructor) {
		this.constructor = constructor;
	}

	TypeModel addProperty(PropertyModel propertyModel) {

		this.properties.add(propertyModel);
		return this;
	}

	Class<?> getType() {
		return type;
	}

	String getTypeName() {
		return type.getName();
	}


	public ConstructorModel getConstructor() {
		return constructor;
	}

	@Override
	public String toString() {
		return "TypeModel{" +
				"type=" + type +
				", annotations=" + annotations +
				", properties=" + properties +
				", constructor=" + constructor +
				'}';
	}

	@Override
	public Iterator<PropertyModel> iterator() {
		return this.properties.iterator();
	}
}
