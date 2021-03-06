/*
 * Copyright 2013-2018 (c) MuleSoft, Inc.
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
package org.raml.jaxrs.examples.resources;

import org.raml.jaxrs.common.Example;
import org.raml.jaxrs.common.ExampleCases;
import org.raml.pojotoraml.plugins.RamlGenerator;
import org.raml.jaxrs.handlers.SimpleJaxbClassParser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Jean-Philippe Belanger on 3/26/17. Just potential zeroes and ones
 */
@XmlRootElement
@RamlGenerator(parser = SimpleJaxbClassParser.class)
@ExampleCases({"one", "two"})
public class ProducedValue {

  @XmlElement
  @Example(useCase = "one", value = "hello")
  private String name;

  @XmlElement
  private int id;

  @XmlElement
  private List<SubType> subtypes;

}
