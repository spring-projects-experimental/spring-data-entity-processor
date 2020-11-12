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

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * @author Christoph Strobl
 * @since 2020/11
 */
public class DomainTypes implements Iterable<TypeModel> {

	private final Set<TypeModel> domainTypes;

	public DomainTypes() {
		this(Collections.emptySet());
	}

	public DomainTypes(Set<TypeModel> typeModels) {
		this.domainTypes = new LinkedHashSet<>(typeModels);
	}

	boolean containsDomainTypeModelForClass(Class<?> type) {
		return getDomainTypeModelForClass(type).isPresent();
	}

	Optional<TypeModel> getDomainTypeModelForClass(Class<?> type) {
		return domainTypes.stream().filter(it -> it.getType().equals(type)).findFirst();
	}

	@Override
	public Iterator<TypeModel> iterator() {
		return domainTypes.iterator();
	}
}