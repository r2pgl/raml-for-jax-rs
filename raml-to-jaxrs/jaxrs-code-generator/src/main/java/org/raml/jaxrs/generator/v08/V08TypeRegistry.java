/*
 * Copyright ${licenseYear} (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.jaxrs.generator.v08;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jean-Philippe Belanger on 1/5/17. Just potential zeroes and ones
 */
public class V08TypeRegistry {

  private final Map<String, V08GType> knownTypes = new HashMap<>();

  public V08GType fetchType(String name) {
    return knownTypes.get(name);
  }

  public void addType(V08GType type) {
    knownTypes.put(type.name(), type);
  }
}
