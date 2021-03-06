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
package org.raml.jaxrs.parser;

import org.raml.jaxrs.model.JaxRsApplication;
import org.raml.jaxrs.parser.analyzers.Analyzers;
import org.raml.jaxrs.parser.gatherers.JerseyGatherer;
import org.raml.jaxrs.parser.source.SourceParser;
import org.raml.jaxrs.parser.util.ClassLoaderUtils;
import org.raml.utilities.format.Joiners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

class JerseyJaxRsParser implements JaxRsParser {

  private static final Logger logger = LoggerFactory.getLogger(JerseyJaxRsParser.class);

  private final Path jaxRsResource;
  private final SourceParser sourceParser;
  private final Set<Class<? extends Annotation>> translatedAnnotations;
  private final String topPackage;


  private JerseyJaxRsParser(Path jaxRsResource, SourceParser sourceParser,
                            Set<Class<? extends Annotation>> translatedAnnotations, String topPackage) {
    this.jaxRsResource = jaxRsResource;
    this.sourceParser = sourceParser;
    this.translatedAnnotations = translatedAnnotations;
    this.topPackage = topPackage;
  }

  public static JerseyJaxRsParser create(Path classesPath, SourceParser sourceParser,
                                         Set<Class<? extends Annotation>> translatedAnnotations, String topPackage) {
    checkNotNull(classesPath);
    checkNotNull(sourceParser);

    return new JerseyJaxRsParser(classesPath, sourceParser, translatedAnnotations, topPackage);
  }

  @Override
  public JaxRsApplication parse() throws JaxRsParsingException {
    logger.info("parsing JaxRs resource: {}", jaxRsResource);

    Iterable<Class<?>> classes = getJaxRsClassesFor(jaxRsResource);

    return Analyzers.jerseyAnalyzerFor(classes, sourceParser, Utilities.getSupportedAnnotations(translatedAnnotations),
                                       topPackage).analyze();
  }

  private static Iterable<Class<?>> getJaxRsClassesFor(Path jaxRsResource)
      throws JaxRsParsingException {

    ClassLoader classLoader;
    try {
      classLoader = ClassLoaderUtils.classLoaderFor(jaxRsResource);
    } catch (MalformedURLException e) {
      throw new JaxRsParsingException(
                                      format("unable to create classloader from %s", jaxRsResource), e);
    }

    Set<Class<?>> classes =
        JerseyGatherer.builder().forApplications(jaxRsResource).withClassLoader(classLoader)
            .build().jaxRsClasses();

    if (logger.isDebugEnabled()) {
      logger.debug("found JaxRs related classes: \n{}",
                   Joiners.squareBracketsPerLineJoiner().join(classes));
    }

    return classes;
  }
}
