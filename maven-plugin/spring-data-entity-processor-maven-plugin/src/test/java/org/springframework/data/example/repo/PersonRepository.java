/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.example.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Christoph Strobl
 * @since 2020/11
 */
public interface PersonRepository extends CrudRepository<Person, String> {

	List<ListValueType> findByFirstname(String firstname);

	Slice<SliceValueType> findByFirstname(String firstname, Pageable page);

	Page<PageValueType> findByLastname(String lastname, Pageable page);

	GeoResults<GeoResultValueType> findByAddressNear(Point point);

	Long countAllBy();

	List<String> findGroupedLastnameByFirstname(String firstname);
}
