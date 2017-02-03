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
package org.raml.jaxrs.plugin;

import java.nio.file.Path;

import static com.google.common.base.Preconditions.checkNotNull;

class PluginConfiguration {

  private final Path inputPaths;
  private final Path sourceDirectory;
  private final Path outputDirectory;
  private final Path ramlFileName;

  private PluginConfiguration(Path inputPaths, Path sourceDirectory, Path outputDirectory,
                              Path ramlFileName) {
    this.inputPaths = inputPaths;
    this.sourceDirectory = sourceDirectory;
    this.outputDirectory = outputDirectory;
    this.ramlFileName = ramlFileName;
  }

  public static PluginConfiguration create(Path inputPath, Path sourceDirectory,
                                           Path outputDirectory, Path ramlFileName) {
    checkNotNull(inputPath);
    checkNotNull(sourceDirectory);
    checkNotNull(outputDirectory);
    checkNotNull(ramlFileName);

    return new PluginConfiguration(inputPath, sourceDirectory, outputDirectory, ramlFileName);
  }

  public Path getInput() {
    return inputPaths;
  }

  public Path getOutputDirectory() {
    return outputDirectory;
  }

  public Path getRamlFileName() {
    return ramlFileName;
  }

  public Path getSourceDirectory() {
    return sourceDirectory;
  }
}
